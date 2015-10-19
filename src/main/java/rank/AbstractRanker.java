package rank;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.jcas.JCas;

import type.Score;
import type.Passage;
import type.Question;

/**
 * This class provides a skeletal implementation of interface IRanker.
 */
public abstract class AbstractRanker implements IRanker {
  JCas jcas;
  protected ScoringAPI scoringAPI;
  
  /**
   * Sorts the given list of passages associated with the given question, and returns a ranked list
   * of passages. The scoringAPI of the given AbstractRanker instance must provide the appropriate scoring method.
   * 
   * This method is currently defunct, but is not used in any code. 
   * 
   * @param question
   * @param passages
   */
  public List<Passage> rank(Question question, List<Passage> passages) {
    // TODO Complete the implementation of this method.

    // Score all the given passages and sort them in List object 'rankedPassages' below.
    List<Passage> rankedPassages = new ArrayList<Passage>();

    return rankedPassages;
  }

  /**
   * Returns a score of the given passage associated with the given question.
   * The scoringAPI of the given AbstractRanker instance must provide the appropriate scoring method.
   * 
   * @param question
   * @param passage
   * @return
   */
  public Score score(Question question, Passage passage) {
	return this.scoringAPI.score(this.jcas, this, question,passage);
  }

  /**
   * Returns a String describing this ranker
   * Defaults to the name of the ranker class
   * 
   * @return
   */
  public String toString(){
	return this.getClass().getName();
  }
}
