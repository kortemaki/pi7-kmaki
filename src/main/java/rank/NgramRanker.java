package rank;

import java.util.List;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.FSArray;
import org.apache.uima.jcas.cas.TOP;

import type.Ngram;
import type.NgramSet;
import type.Passage;
import type.Question;
import type.Score;
import type.Span;
import util.CheckMethod;
import util.TypeUtils;

/**
 * NgramRanker class to assign scores to question-passage pairs
 * according to their Ngram overlap.
 * 
 * By default, examines all NgramSet in the respective AnalysisAnnotations
 *   for each document, and averages the overlap across NgramSets.
 * 
 * Builder pattern allows for more flexible configuration of parameters
 *   such as restricting the comparison to specific n-gram widths.
 * 
 * @author maki
 *
 */
public class NgramRanker extends AbstractRanker 
{
    public NgramRanker(NgramRankerBuilder builder)
    {
    	super(builder);
    	this.scoringAPI = new NgramScoringAPIImpl(builder.checkMethods);
    }

    /**
     * Builder implementation for the ngram ranker
     * 
     *   Instantiate the builder and initialize jcas
     *   with NgramRankerBuilder.setJCas(JCas jcas)
     * 
     *   Then call NgramRanker(NgramRankerBuilder)
     *   
     *   Optionally, set checkMethods equal to an array of checkMethods
     *     which specify the ngramsets to compare.
     *     
     *      //e.g. To compare 5-grams:
     *      NgramRankerBuilder builder = new NgramRankerBuilder();
     *      builder.setCheckMethods(AnalysisUtils.hasN5);
     *      builder.setJCas(someCas);
     *      Score score = (new NgramRanker(builder)).score(someQuestion, somePassage);
     *      
     *   Inherits the setJCas(JCas jcas) method from AbstractRankerBuilder
     */
	@SuppressWarnings("rawtypes")
    public static class NgramRankerBuilder extends AbstractRankerBuilder{
    	CheckMethod[] checkMethods;
    	
    	public NgramRankerBuilder(){
    		this.jcas = null;
    		this.checkMethods = null;
    	}
    	
    	public void setCheckMethods(CheckMethod... checkMethods)
    	{
    		this.checkMethods = checkMethods;
    	}

		public IRanker instantiateRanker() {
			return new NgramRanker(this);
		}
    }
}


/**
 * Bridge implementation of the ngram scoring method
 * 
 * Implements an Ngram overlap scoring method
 * which scores a passage with respect to its ngram similarity with the question
 * 
 * The checkMethods instance variable allows for configurable restrictions
 *   on the NgramSets which are compared.
 * 
 * @author maki
 *
 */
@SuppressWarnings("rawtypes")
class NgramScoringAPIImpl implements ScoringAPI
{
	CheckMethod[] checkMethods;
	
	NgramScoringAPIImpl(CheckMethod[] checkMethods)
	{
		this.checkMethods = checkMethods;
	}
	
	/**
	 * Returns a Score of the given passage associated with the given question.
	 * 
	 * The Score is computed using the percentage token overlap 
	 *   averaged over all NgramSets which fulfill this.checkMethods 
	 *   in the question and passage's respective AnalysisAnnotations FSLists.
	 * 
	 * @param question the Question to compare
	 * @param passage the Passage to compare
	 * @return a Score of the passage
	 */
	public Score score(JCas jcas, IRanker ranker, Question question, Passage passage) 
	{
		// Get the ngrams for this Test Element's question
		List<TOP> questionNgramSets = (List<TOP>) TypeUtils.getAllFromFSList(
				question.getAnalysisAnnotations(), NgramSet.class, this.checkMethods);

		// Get the ngrams for this Test Element's question
		List<TOP> passageNgramSets = (List<TOP>) TypeUtils.getAllFromFSList(
				passage.getAnalysisAnnotations(), NgramSet.class, this.checkMethods);

		float total = (float) 0;
		int count = 0;
		for(TOP questionNgrams : questionNgramSets)
		{
			FSArray qtokens = ((NgramSet) questionNgrams).getNgrams();
			for(TOP passageNgrams : passageNgramSets)
			{
				FSArray ptokens = ((NgramSet) passageNgrams).getNgrams();
				total += tokenOverlap(qtokens, ptokens);
				count++;
			}
		}
		
		// Make the score
		Score score = new Score(jcas);
		score.setScore(total/count);
		score.setComponentId(this.getClass().getName());
		score.addToIndexes();

		return score;
	}

	/**
	 * Scores the agreement between the two FSArray<TokenizedSpan> params based on ngram
	 * overlap
	 * 
	 * @return
	 */
	private static float tokenOverlap(FSArray tokens1, FSArray tokens2) 
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
	
	/**
	 * Auxiliary method to determine equality of a pair of ngrams
	 * The ngrams must be the same length N
	 * 
	 * Uses string.equals to compare tokens for equality, 
	 *   and returns true iff all tokens are equal.
	 * 
	 * @param ngram1 The first ngram to compare
	 * @param ngram2 The second ngram to compare
	 * @return
	 */
	private static boolean sameNgram(Ngram ngram1, Ngram ngram2) 
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