package rank;

import java.util.List;

import org.apache.uima.jcas.JCas;

import type.Score;
import type.Passage;
import type.Question;

public interface IRanker 
{
  /**
   * Sorts the given list of passages associated with the given question, and returns a ranked list
   * of passages.
   * 
   * @param question
   * @param passages
   */
  public List<Passage> rank(Question question, List<Passage> passages);

  /**
   * Returns a score of the given passage associated with the given question.
   * 
   * @param question
   * @param passage
   * @return a score of the passage
   */
  public Score score(Question question, Passage passage);
 
  /**
   * Interface for a builder pattern class for the IRanker interface
   * 
   * @author maki
   */
  public static interface IRankerBuilder
  {
	public IRanker instantiateRanker();
	public void setJCas(JCas jcas);
  }
  
}
