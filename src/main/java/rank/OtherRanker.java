package rank;

import org.apache.uima.jcas.JCas;

import type.Passage;
import type.Question;
import type.Score;

public class OtherRanker extends AbstractRanker {

  public OtherRanker(JCas jcas)
  {
	  this.jcas = jcas;
	  this.scoringAPI = new OtherScoringAPIImpl();
  }

}

/**
 * Bridge implementation of other scoring method
 * @author maki
 *
 */
class OtherScoringAPIImpl implements ScoringAPI
{
	/**
	 * Returns a score of the given passage associated with the given question.
	 * 
	 * @param question
	 * @param passage
	 * @return a score of the passage
	 */
	public Score score(JCas jcas, IRanker ranker, Question question, Passage passage) 
	{
		// 	TODO Complete the implementation of this method.

		Score dummy = new Score(jcas);
		dummy.setComponentId("Dummy score");
		return dummy;
	}
}