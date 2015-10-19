package util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSList;
import org.apache.uima.jcas.cas.NonEmptyFSList;

import type.ConfMatrix;
import type.Metric;
import type.PAtN;
import type.Passage;
import type.ScoredSpan;
import type.Scoring;
import util.ListReverser;

public class AnalysisUtils {

	/* The rank at which to compute PRF statistics and confusion matrix */
	private static final int CUTOFF = 5;
	
	public static Method getName = TypeUtils.instantiateMethod(PAtN.class, "getMetricName");
	public static Method getN = TypeUtils.instantiateMethod(PAtN.class, "getN");
	
	public static CheckMethod hasN1 = new CheckMethod(getN, 1);
	public static CheckMethod hasNamePAt1 = new CheckMethod(getName, "Precision at 1.");
	public static CheckMethod[] isPAt1 = new CheckMethod[]{hasN1, hasNamePAt1};
	
	public static 	CheckMethod hasN5 = new CheckMethod(getN, 5);
	public static CheckMethod hasNamePAt5 = new CheckMethod(getName, "Precision at 5.");
	public static CheckMethod[] isPAt5 = new CheckMethod[]{hasN5, hasNamePAt5};
	
	public static 	CheckMethod hasNameRR = new CheckMethod(getName, "Reciprocal rank");
	public static CheckMethod[] isRR = new CheckMethod[]{hasNameRR};
		
	public static CheckMethod hasNameAP = new CheckMethod(getName, "Average precision");
	public static CheckMethod[] isAP = new CheckMethod[]{hasNameAP};
	
	public static CheckMethod isConfMat = new CheckMethod(getName, "Confusion matrix");
	
	public static CheckMethod isPrecision = new CheckMethod(getName, "Precision");
	public static CheckMethod isRecall = new CheckMethod(getName, "Recall");
	public static CheckMethod isF1Meas = new CheckMethod(getName, "F1-score");
	
	/**
	 * Returns the reciprocal of the smallest rank which gives a correct score
	 * If no scores are correct, returns the reciprocal of one plus the number of ranks.
	 * 
	 * @param scores - list of values true or false indicating whether the ranking was correct or not 
	 * @return the reciprocal of the smallest correct rank, or one plus the number of ranks
	 */
	public static float reciprocalRank(List<Boolean> scores)
	{
		int index = 1;
		boolean sawCorrect = false;
		for(Boolean score : scores)
		{
			sawCorrect |= score;
			if(score)
				break;
			index++;
		}
		return sawCorrect ? ((float)1.0)/(index) : 0;
	
	}
	
	public static float averagePrecision(List<Boolean> scores)
	{
		float total = 0;
		for(int n=1; n <= scores.size(); n++)
		{
			total += precisionAtN(scores,n);
		}
		return ((float) total)/((float) scores.size());
	}
	
	public static float precisionAtN(List<Boolean> scores, int n)
	{		
		return countFirstN(scores, n)/n;
	}
	
	public static List<Boolean> getCorrection(Scoring scoring)
	{
		FSList nextScore = scoring.getScores();
		List<Boolean> labels = new ArrayList<Boolean>();
		List<Double> scores = new ArrayList<Double>();
		while(nextScore instanceof NonEmptyFSList)
		{
			ScoredSpan passage = (ScoredSpan) ((NonEmptyFSList) nextScore).getHead();
			boolean label = ((Passage) passage.getOrig()).getLabel();
			double score = passage.getScore();
			labels.add(label);
			scores.add(score);
			nextScore = ((NonEmptyFSList) nextScore).getTail();
		}
		concurrentSort(scores,labels);
		return labels;
	}
	
	/**
	 * Auxiliary method to sum the values in a list of doubles which are coindexed to a boolean value True in a list of booleans
	 * 
	 * @param values - The values to sum
	 * @param labels - The boolean filter condition
	 * @param n - The maximum number of values to consider
	 * @return The sum of the first n values, filtered by True labels
	 */
	public static float countFirstN(Iterable<Boolean> values, int n)
	{
		float sum = 0;
		Iterator<Boolean> it = values.iterator(); 
		for(int i = 0; i < n; i++)
		{
			if(it.hasNext() && it.next())
				sum++;
		}
		return sum;
	}
	
	/**
	 * Concurrent sort code from https://ideone.com/u2OICl
	 * Published to stackoverflow.com by user bcorso
	 * 
	 * Performs an in-place (destructive) sort on a list of array lists.  
	 * The keylist is unchanged (unless it is passed as one of the lists)
	 * 
	 * @param key: the list of comparable keys on which to sort
	 * @param lists: the coindexed set of lists to sort
	 */
	public static <T extends Comparable<T>> void concurrentSort( final List<T> key, List<?>... lists)
	{
        // Do validation
        if(key == null || lists == null)
        	throw new NullPointerException("key cannot be null.");
 
        for(List<?> list : lists)
        	if(list.size() != key.size())
        		throw new IllegalArgumentException("all lists must be the same size");
 
        // Lists are size 0 or 1, nothing to sort
        if(key.size() < 2)
        	return;
 
        // Create a List of indices
		List<Integer> indices = new ArrayList<Integer>();
		for(int i = 0; i < key.size(); i++)
			indices.add(i);
 
        // Sort the indices list based on the key
		Collections.sort(indices, new Comparator<Integer>()
		{
			public int compare(Integer i, Integer j) 
			{
				return key.get(i).compareTo(key.get(j));
			}
		});
 
		Map<Integer, Integer> swapMap = new HashMap<Integer, Integer>(indices.size());
 
        // create a mapping that allows sorting of the List by N swaps.
		for(int i = 0; i < indices.size(); i++)
		{
			int k = indices.get(i);
			while(swapMap.containsKey(k))
				k = swapMap.get(k);
 
			swapMap.put(i, k);
		}
 
        // for each list, swap elements to sort according to key list
        for(Map.Entry<Integer, Integer> e : swapMap.entrySet())
			for(List<?> list : lists)
				Collections.swap(list, e.getKey(), e.getValue());
	}

	public static ConfMatrix getConfMat(Scoring score, JCas jcas) {
		List<Boolean> correct = getCorrection(score);

		ConfMatrix mat = new ConfMatrix(jcas);
		
		//True positives
		Metric tp = new Metric(jcas);
		float truePos  = countFirstN(correct,CUTOFF);
		tp.setValue(truePos);
		tp.setMetricName("True positives");
		tp.setComponentId(AnalysisUtils.class.getName());
		tp.addToIndexes();
		mat.setTp(tp);
		
		//False positives
		Metric fp = new Metric(jcas);
		float falsePos = CUTOFF - truePos;
		if(falsePos < 0)
			falsePos = 0;
		fp.setValue(falsePos);
		fp.setMetricName("False positives");
		fp.setComponentId(AnalysisUtils.class.getName());
		fp.addToIndexes();
		mat.setFp(fp);
		
		
		//False negatives
		Metric fn = new Metric(jcas);
		float falseNeg = 0;
		if(correct.size()-CUTOFF > 0)
		{
			falseNeg = countFirstN(new ListReverser<Boolean>(correct),correct.size()-CUTOFF);
		}
		fn.setValue(falseNeg);
		fn.setMetricName("False negatives");
		fn.setComponentId(AnalysisUtils.class.getName());
		fn.addToIndexes();
		mat.setFn(fn);
		
		//True negatives
		Metric tn = new Metric(jcas);
		float trueNeg  = correct.size() - CUTOFF - falseNeg;
		if(trueNeg < 0)
			trueNeg = 0;
		tn.setValue(trueNeg);
		tn.setMetricName("True negatives");
		tn.setComponentId(AnalysisUtils.class.getName());
		tn.addToIndexes();
		mat.setTn(tn);
		
		mat.setComponentId(AnalysisUtils.class.getName());
		mat.setValue(CUTOFF);
		mat.setMetricName("Confusion matrix");
		mat.addToIndexes();
		return mat;
	}
	
	public static FSList addPrecisionToFSList(FSList metrics, Scoring score, JCas jcas) {
		List<Boolean> correct = getCorrection(score);
		float tp = countFirstN(correct,CUTOFF);
		float fp = CUTOFF - tp;
		
		Metric precision = (Metric) TypeUtils.getFromFSList(metrics,Metric.class,isPrecision);
		if(precision == null)
		{
			precision = new Metric(jcas);
			precision.addToIndexes();					
			metrics = TypeUtils.addToFSList(metrics, precision, jcas);
		}
		precision.setComponentId(AnalysisUtils.class.getName());
		precision.setMetricName("Precision");
		if(tp == 0)
			precision.setValue(0);
		else
			precision.setValue(tp/(tp+fp));
		//System.out.printf("%d:+%f-%f=%.3f",correct.size(),tp,fp,precision.getValue());
		return metrics;
	}

	public static FSList addRecallToFSList(FSList metrics, Scoring score, JCas jcas) {
		List<Boolean> correct = getCorrection(score);
		float tp = countFirstN(correct,CUTOFF);
		float fn = 0; 
		if(correct.size()-CUTOFF > 0)
		{
			fn = countFirstN(new ListReverser<Boolean>(correct),correct.size()-CUTOFF);
		}
				
		Metric recall = (Metric) TypeUtils.getFromFSList(metrics, Metric.class, isRecall);
		if(recall == null)
		{
			recall = new Metric(jcas);
			recall.addToIndexes();
			metrics = TypeUtils.addToFSList(metrics, recall, jcas);
		}
		recall.setComponentId(AnalysisUtils.class.getName());
		recall.setMetricName("Recall");
		if(tp == 0)
			recall.setValue(0);
		else
			recall.setValue(tp/(tp+fn));
		//System.out.printf("%d:+%f-%f=%.3f",correct.size(),tp,fn,recall.getValue());
		return metrics;
	}

	public static FSList addF1MeasToFSList(FSList metrics, Scoring score, JCas jcas) {
		metrics = addPrecisionToFSList(metrics, score, jcas);
		metrics = addRecallToFSList(metrics, score, jcas);
		float prec = ((Metric) TypeUtils.getFromFSList(metrics,Metric.class,isPrecision)).getValue();
		float rec = ((Metric) TypeUtils.getFromFSList(metrics,Metric.class,isRecall)).getValue();
		Metric f1meas = (Metric) TypeUtils.getFromFSList(metrics, Metric.class, isF1Meas);
		if(f1meas == null)
		{
			f1meas = new Metric(jcas);
			f1meas.addToIndexes();
			metrics = TypeUtils.addToFSList(metrics, f1meas, jcas);
		}
		f1meas.setComponentId(AnalysisUtils.class.getName());
		f1meas.setMetricName("F1-Score");
		f1meas.setValue(2*prec*rec/(prec+rec));
		return metrics;
	}
}
