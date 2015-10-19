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

import org.apache.uima.analysis_component.CasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;

import type.ConfMatrix;
import type.Metric;
import type.OutputAnnotation;
import type.ErrorAnalysisAnnotation;
import type.Question;
import util.AnalysisUtils;
import util.TypeUtils;

/**
 * A simple scoring annotator for PI3.
 * 
 * Expects each CAS to contain at least one NgramAnnotation.
 * Processes each NgramAnnotation by adding a corresponding AnswerScoringAnnotation to the CAS.
 * 
 * This annotator has no parameters and requires no initialization method.
 */

public class ErrorAnalysisOutputAnnotator extends CasAnnotator_ImplBase {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void process(CAS aCas) throws AnalysisEngineProcessException {
		
		JCas jcas;
		try {
			jcas = aCas.getJCas();
		} catch (CASException e) {
			throw new AnalysisEngineProcessException(e);
		}
		
		// Get the Scoring Annotations for each Test Element in the document
		FSIndex<ErrorAnalysisAnnotation> annotIndex = (FSIndex) (jcas.getAnnotationIndex(ErrorAnalysisAnnotation.type));

		// Iterate over them in sequence
		for(ErrorAnalysisAnnotation annot : annotIndex)
		{				
	        Question question = annot.getTestElement().getQuestion();
	        
			System.out.println("    Finalizing output for document " + question.getId() + ".");
	        
	        ///////////////////////////////////////////
	        // Extract annot metrics of interest
	        FSList metrics = annot.getMetrics();
	        
	        // Confusion matrix
	        int tp = 0;
	        int fn = 0;
	        int fp = 0;
	        ConfMatrix mat = ((ConfMatrix) TypeUtils.getFromFSList(metrics,ConfMatrix.class,AnalysisUtils.isConfMat));
	        if(mat != null)
	        {	
	        	tp = (int) mat.getTp().getValue();
	        	fn = (int) mat.getFn().getValue();
	        	fp = (int) mat.getFp().getValue();
	        }
	        else
	        {
	        	System.out.println("******Could not find confusion matrix output for document " + question.getId() + ".");
	        }
	        
	        //Precision
	        float prec = 0;
	        Metric precision = ((Metric) TypeUtils.getFromFSList(metrics,Metric.class,AnalysisUtils.isPrecision));
	        if(precision != null)
	        {
	        	prec = precision.getValue();
	        }
	        else
	        {
	        	System.out.println("******Could not find precision output for document " + question.getId() + ".");
	        }
	        
	        //Recall
	        float rec = 0;
	        Metric recall = ((Metric) TypeUtils.getFromFSList(metrics,Metric.class,AnalysisUtils.isRecall));
	        if(recall != null)
	        {
	        	rec = recall.getValue();
	        }
	        else
	        {
	        	System.out.println("******Could not find recall output for document " + question.getId() + ".");
	        }
	        
	        //F1-score
	        float f1 = 0;
	        Metric fmeasure = ((Metric) TypeUtils.getFromFSList(metrics,Metric.class,AnalysisUtils.isF1Meas));
	        if(fmeasure != null)
	        {
	        	f1 = fmeasure.getValue();
	        }
	        else
	        {
	        	System.out.println("******Could not find F1-score output for document " + question.getId() + ".");
	        }
	        
	        String text = String.format("%s,%d,%d,%d,%.3f,%.3f,%.3f", question.getId(), tp, fn, fp, prec, rec, f1);	        
	        
			OutputAnnotation output = new OutputAnnotation(jcas);
			output.setComponentId(this.getClass().getName());
			output.setBegin(annot.getBegin());
			output.setEnd(annot.getEnd());
			output.setOrig(annot.getTestElement());
			output.setText((annot.getTestElement()).getQuestion().getId());
			output.setOutput(text);
			output.addToIndexes();
			
			System.out.println("Finished output for document " + question.getId() + ".");
		}
	}
}
