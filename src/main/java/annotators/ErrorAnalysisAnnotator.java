package annotators;
import org.apache.uima.analysis_component.CasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.EmptyFSList;
import type.ErrorAnalysisAnnotation;
import type.Question;
import type.Scoring;
import type.TestElementAnnotation;
import util.AnalysisUtils;
import util.TypeUtils;

public class ErrorAnalysisAnnotator extends CasAnnotator_ImplBase  
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
		
		//Produce one annot for each test element
		FSIndex<Scoring> teIndex = (FSIndex) jcas.getAnnotationIndex(Scoring.type);

		for(Scoring score : teIndex)
		{
			Question question = ((TestElementAnnotation) score.getOrig()).getQuestion();
			ErrorAnalysisAnnotation annot = new ErrorAnalysisAnnotation(jcas);
			annot.setTestElement((TestElementAnnotation) score.getOrig());
			annot.setBegin(question.getBegin());
			annot.setEnd(question.getEnd());
			
			// annot Metrics
			annot.setMetrics(new EmptyFSList(jcas));
			
			///////////////////
			//Confusion matrix
			annot.setMetrics(TypeUtils.addToFSList(annot.getMetrics(),AnalysisUtils.getConfMat(score,jcas),jcas));
			
			//////
			//PRF
			annot.setMetrics(AnalysisUtils.addPrecisionToFSList(annot.getMetrics(),score,jcas));
			annot.setMetrics(AnalysisUtils.addRecallToFSList(annot.getMetrics(),score,jcas));
			annot.setMetrics(AnalysisUtils.addF1MeasToFSList(annot.getMetrics(),score,jcas));
			
			annot.setComponentId(this.getClass().getName());
			annot.addToIndexes();

			System.out.println("    Computed metrics for document " + ((TestElementAnnotation) score.getOrig()).getQuestion().getId() + ".");
		}		
	}
}
