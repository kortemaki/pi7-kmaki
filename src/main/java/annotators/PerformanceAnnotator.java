package annotators;
import java.util.List;
import org.apache.uima.analysis_component.CasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.EmptyFSList;
import type.Metric;
import type.PAtN;
import type.Performance;
import type.Question;
import type.Scoring;
import type.TestElementAnnotation;
import util.TypeUtils;
import util.AnalysisUtils;

public class PerformanceAnnotator extends CasAnnotator_ImplBase  
{
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public void process(CAS aCas) throws AnalysisEngineProcessException 
	{
		JCas jcas;
		try {
			jcas = aCas.getJCas();
		} catch (CASException e) {
			throw new AnalysisEngineProcessException(e);
		}
		
		//Produce one performance for each test element
		FSIndex<Scoring> teIndex = (FSIndex) jcas.getAnnotationIndex(Scoring.type);

		for(Scoring score : teIndex)
		{
			Question question = ((TestElementAnnotation) score.getOrig()).getQuestion();
			Performance performance = new Performance(jcas);
			performance.setTestElement((TestElementAnnotation) score.getOrig());
			performance.setBegin(question.getBegin());
			performance.setEnd(question.getEnd());
			
			//////////////////////
			// Performance Metrics
			performance.setMetrics(new EmptyFSList(jcas));
			
			List<Boolean> correct = AnalysisUtils.getCorrection(score);			
			
			//Precision at 1
			PAtN pAt1 = new PAtN(jcas);
			pAt1.setN(1);
			pAt1.setValue(AnalysisUtils.precisionAtN(correct, 1));
			pAt1.setMetricName("Precision at 1.");
			pAt1.setComponentId(this.getClass().getName());
			pAt1.addToIndexes();
			performance.setMetrics(TypeUtils.addToFSList(performance.getMetrics(),pAt1,jcas));
			
			//Precision at 5
			PAtN pAt5 = new PAtN(jcas);
			pAt5.setN(5);
			pAt5.setValue(AnalysisUtils.precisionAtN(correct, 5));
			pAt5.setMetricName("Precision at 5.");
			pAt5.setComponentId(this.getClass().getName());
			pAt5.addToIndexes();
			performance.setMetrics(TypeUtils.addToFSList(performance.getMetrics(),pAt5,jcas));
			
			//Reciprocal rank
			Metric rr = new Metric(jcas);
			rr.setValue(AnalysisUtils.reciprocalRank(correct));
			rr.setMetricName("Reciprocal rank.");
			rr.setComponentId(this.getClass().getName());
			rr.addToIndexes();
			performance.setMetrics(TypeUtils.addToFSList(performance.getMetrics(),rr,jcas));
			
			//Average Precision
			Metric ap = new Metric(jcas);
			ap.setValue(AnalysisUtils.averagePrecision(correct));
			ap.setMetricName("Average precision.");
			ap.setComponentId(this.getClass().getName());
			ap.addToIndexes();
			performance.setMetrics(TypeUtils.addToFSList(performance.getMetrics(),ap,jcas));
			
			performance.setComponentId(this.getClass().getName());
			performance.addToIndexes();

			System.out.println("    Computed metrics for document " + ((TestElementAnnotation) score.getOrig()).getQuestion().getId() + ".");
		}		
	}
}
