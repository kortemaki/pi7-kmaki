package rank;

import org.apache.uima.jcas.JCas;
import type.Passage;
import type.Question;
import type.Score;

public class CharacterRanker extends AbstractRanker {

  public static class CharacterRankerBuilder extends AbstractRankerBuilder {

	public IRanker instantiateRanker() {
		return new CharacterRanker(this);
	}
	
  }
	
  public CharacterRanker(CharacterRankerBuilder builder)
  {
	  super(builder);
	  this.scoringAPI = new CharacterScoringAPIImpl();
  }
}

/**
 * Bridge implementation of other scoring method
 * 
 * Performs scoring on a question-passage pair by character ngram overlap
 * 
 * @author maki
 */
class CharacterScoringAPIImpl implements ScoringAPI
{
	/**
	 * Returns a score of the given passage associated with the given question.
	 * 
	 * Uses a case-insensitive character overlap metric to compute the score.
	 * 
	 * @param question
	 * @param passage
	 * @return a score of the passage
	 */
	public Score score(JCas jcas, IRanker ranker, Question question, Passage passage) 
	{
		// Get the text for this Test Element's question
		String qtext = question.getText();
		String ptext = passage.getText();
		
		Double overlap = (double) longestSubstr(qtext.toUpperCase(), ptext.toUpperCase());
		
		// Make the score
		Score score = new Score(jcas);
		score.setScore(overlap/qtext.length());
		score.setComponentId(this.getClass().getName());
		score.addToIndexes();

		return score;
	}
	/**
	 * Method to compute the length of the longest common substring between two strings
	 * 
	 * Code adapted from https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Longest_common_substring#Java
	 */
	public static int longestSubstr(String first, String second) 
	{
		if (first == null || second == null || first.length() == 0 || second.length() == 0) 
		{
			return 0;
		}

		int maxLen = 0;
		int fl = first.length();
		int sl = second.length();
		int[][] table = new int[fl+1][sl+1];

		for(int s=0; s <= sl; s++)
			table[0][s] = 0;
		for(int f=0; f <= fl; f++)
			table[f][0] = 0;

		for (int i = 1; i <= fl; i++) {
			for (int j = 1; j <= sl; j++) {
				if (first.charAt(i-1) == second.charAt(j-1)) {
					if (i == 1 || j == 1) {
						table[i][j] = 1;
					}
					else {
						table[i][j] = table[i - 1][j - 1] + 1;
					}
					if (table[i][j] > maxLen) {
						maxLen = table[i][j];
					}
				}
			}
		}
		return maxLen;
	}
}