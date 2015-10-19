package rank;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;

import type.Ngram;
import type.NgramSet;
import type.Passage;
import type.Question;
import type.Score;
import type.Span;
import util.CheckMethod;
import util.TypeUtils;

public class NgramRanker extends AbstractRanker 
{
    public NgramRanker(JCas jcas)
    {
    	this.jcas = jcas;
    	this.scoringAPI = new NgramScoringAPIImpl();
    }
}

/**
 * Bridge implementation of the ngram scoring method
 * 
 * @author maki
 *
 */
class NgramScoringAPIImpl implements ScoringAPI
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
		// TODO Complete the implementation of this method.
		// Get the ngrams for this Test Element's question
		NgramSet questionNgrams = (NgramSet) TypeUtils.getFromFSList(
				question.getAnalysisAnnotations(), NgramSet.class,
				(CheckMethod[]) null);

		// Get the ngrams for this Test Element's question
		NgramSet passageNgrams = (NgramSet) TypeUtils.getFromFSList(
				passage.getAnalysisAnnotations(), NgramSet.class,
				(CheckMethod[]) null);

		// Make the score
		Score score = new Score(jcas);
		score.setScore(this.score(questionNgrams, passageNgrams));
		score.setComponentId(this.getClass().getName());
		score.addToIndexes();

		return score;
	}

	/**
	 * Scores the agreement between the two NgramSet params based on ngram
	 * overlap
	 * 
	 * @return
	 */
	private Double score(NgramSet tokens1, NgramSet tokens2) 
	{
		return (double) tokenOverlap(tokens1.getNgrams(), tokens2.getNgrams());
	}

	private float tokenOverlap(FSArray tokens1, FSArray tokens2) 
	{
		if (tokens1 == null || tokens2 == null)
			return 0;

		float count = 0;
		for (int i = 0; i < tokens1.size(); i++) 
		{
			for (int j = 0; j < tokens2.size(); j++) 
			{
				if (tokens1.get(i) != null
						&& tokens2.get(j) != null
						&& sameNgram((Ngram) tokens1.get(i),
								(Ngram) tokens2.get(j)))
					count++;
			}
		}
		return count / (tokens1.size() * tokens2.size());
	}

	private boolean sameNgram(Ngram ngram1, Ngram ngram2) 
	{
		if (ngram1.getN() != ngram2.getN())
			return false;
		for (int i = 0; i < ngram1.getN(); i++) 
		{
			if (!((Span) ngram1.getTokens().get(i)).getText().equals(
					((Span) ngram2.getTokens().get(i)).getText()))
				return false;
		}
		return true;
	}

}