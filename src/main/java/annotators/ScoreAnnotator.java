/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package annotators;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.CasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.EmptyFSList;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.NonEmptyFSList;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import rank.IRanker;
import rank.IRanker.IRankerBuilder;
import rank.NgramRanker.NgramRankerBuilder;
import rank.CharacterRanker.CharacterRankerBuilder;
import rank.WeightedAverageCompositeRanker.WeightedAverageCompositeRankerBuilder;
import type.Passage;
import type.Question;
import type.ScoredSpan;
import type.Scoring;
import type.TestElementAnnotation;
import type.Score;

/**
 * A simple scoring annotator for PI3.
 * 
 * Expects each CAS to contain at least one NgramAnnotation.
 * Processes each NgramAnnotation by adding a corresponding AnswerScoringAnnotation to the CAS.
 * 
 * TODO: It may be useful to implement arbitrary parameters for the rankers??
 */
public class ScoreAnnotator extends CasAnnotator_ImplBase {	

	IRankerBuilder rankerBuilder;

	/**
	 * Initialization method for the pi7-kmaki ScoreAnnotator class.
	 * Instantiates a composite ranker with NgramRanker and OtherRanker rankers
	 */
	public void initialize(UimaContext aContext) throws ResourceInitializationException
	{
		super.initialize(aContext);
		
		System.out.println(WeightedAverageCompositeRankerBuilder.class.getName());
		
		final String RANKER_BUILDER = (String) aContext.getConfigParameterValue("RankerBuilder");
		
		//Make the builder for the ranker
		try 
		{
			this.rankerBuilder = (IRankerBuilder) Class.forName(RANKER_BUILDER).newInstance();
		}
		catch(Throwable e)
		{
			Object[] args = new Object[1];
			args[0] = e;
			throw new ResourceInitializationException("Could not instantiate ranker builder " + RANKER_BUILDER +
						": check settings for configuration parameter RankerBuilder",args);
		}
		
		//TODO: Parameterize these configurations somehow?
		//At the very least, they could be put in the XML descriptor for this annotator...
		//Additionally, it may be possible to parse these more elegantly using Reflection. 
		if(this.rankerBuilder instanceof WeightedAverageCompositeRankerBuilder)
		{
			IRankerBuilder ngramRankerBuilder = new NgramRankerBuilder(); //Can also take checkMethods...
			IRankerBuilder otherRankerBuilder = new CharacterRankerBuilder();
			
			((WeightedAverageCompositeRankerBuilder) rankerBuilder).addRankerBuilder(ngramRankerBuilder);
			((WeightedAverageCompositeRankerBuilder) rankerBuilder).addRankerBuilder(otherRankerBuilder);
			((WeightedAverageCompositeRankerBuilder) rankerBuilder).addWeight((float) 1);
			((WeightedAverageCompositeRankerBuilder) rankerBuilder).addWeight((float) 1);
			System.out.println("FNORD");
		} 
		else if(this.rankerBuilder instanceof NgramRankerBuilder)
		{
			//Parameters for NgramRankerBuilder here
		} 
		else if(this.rankerBuilder instanceof CharacterRankerBuilder)
		{
			//Parameters for CharacterRankerBuilder here
		}
		
    }
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void process(CAS aCas) throws AnalysisEngineProcessException {
		JCas jcas;
		try {
			jcas = aCas.getJCas();
		} catch (CASException e) {
			throw new AnalysisEngineProcessException(e);
		}
		
		//Give the builder the current sofa jcas and build the ranker
		this.rankerBuilder.setJCas(jcas);
		IRanker ranker = rankerBuilder.instantiateRanker();
		
		// Get the Ngram Annotations for each Test Element in the document
		FSIndex<TestElementAnnotation> fs = (FSIndex) jcas.getAnnotationIndex(TestElementAnnotation.type);

		// Iterate over them in sequence
		for(TestElementAnnotation te : fs)
		{			
			//////////////////////
			// Handle the question
			Question question = te.getQuestion();
			
			//////////////////////
			// Handle the answers
			// Create a list to hold our scoring for each answer choice
			Scoring output = new Scoring(jcas);
			output.setComponentId(this.getClass().getName());
			output.setBegin(Math.min(question.getBegin(), getMinBegin(question.getPassages())));
			output.setEnd(Math.max(question.getEnd(), getMaxEnd(question.getPassages())));
			
			/////////////////////
			// Score each passage
			FSList passages = question.getPassages();
			FSList scores = new EmptyFSList(jcas);
			while(!(passages instanceof EmptyFSList))
			{
				Passage passage = (Passage) ((NonEmptyFSList) passages).getHead();
				NonEmptyFSList next = new NonEmptyFSList(jcas);
				ScoredSpan span = new ScoredSpan(jcas);
				span.setBegin(passage.getBegin());
				span.setEnd(passage.getEnd());
				span.setText(passage.getText());
				span.setOrig(passage);
				span.setComponentId(this.getClass().getName());
				
				Score score = ranker.score(question, passage);
				
				//System.out.println("Score: "+score.getScore());
				
				span.setScore(score);
				span.addToIndexes();
				next.setHead(span);
				next.setTail(scores);
				scores = next;
				passages = ((NonEmptyFSList) passages).getTail();
			}
			output.setScores(scores);
			output.setBegin(te.getBegin());
			output.setEnd(te.getEnd());
			output.setOrig(te);
			output.setComponentId(this.getClass().getName());	
			output.addToIndexes();
			System.out.println("    Scored document " +  question.getId() + ".");
		}
	}

	/**
	 * Auxiliary method to compute the smallest begin index of the Annotations in arr 
	 * @param arr the array of Annotations to look at
	 * @return the minimum begin index
	 */
	private int getMinBegin(FSList arr) {
		int min =  Integer.MAX_VALUE;
		while(!(arr instanceof EmptyFSList))
		{
			int begin = ((Annotation) ((NonEmptyFSList) arr).getHead()).getBegin();
			if(begin < min)
				min = begin;
			arr = ((NonEmptyFSList) arr).getTail();
		}
		return min;
	}

	/**
	 * Auxiliary method to compute the largest end index of the Annotations in arr 
	 * @param arr the array of Annotations to look at
	 * @return the maximum end index
	 */
	private int getMaxEnd(FSList arr) {
		int max = Integer.MIN_VALUE;
		while(!(arr instanceof EmptyFSList))
		{
			int end = ((Annotation) ((NonEmptyFSList) arr).getHead()).getBegin();
			if(end > max)
				max = end;
			arr = ((NonEmptyFSList) arr).getTail();
		}
		return max;
	}
}
