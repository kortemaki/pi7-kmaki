package rank;

import java.util.ArrayList;
import java.util.List;

import org.apache.uima.jcas.JCas;

import type.Score;
import type.Passage;
import type.Question;

/**
 * AbstractRanker abstract class for pi7-kmaki
 * Describes a general ranker class which assigns similarity scores
 *   to question-passage pairs using a ScoringAPI.score method.
 *   
 * The Bridge pattern helps to decouple the AbstractRanker class and extending subclasses
 *   from the ScoringAPI interface class describing the method of scoring.
 *   This helps to keep the ScoringAPI class focused, organized, and reusable,
 *   but also enables more flexibility in the Ranker class implementations.
 *   
 * The Builder pattern is used to ensure that changes to the AbstractRanker instantiation
 *   do not directly impact User class code, and changes to User class code likewise do not
 *   necessitate changes to the AbstractRanker instantiation.
 *   
 *   This pattern has the added benefit that rankers may be instantiated cleanly
 *     within different JCas environments by the same Builder instance.
 */
public abstract class AbstractRanker implements IRanker {
  JCas jcas;
  protected ScoringAPI scoringAPI;
 
  /**
   * Abstract builder class for Rankers
   * 
   * @author maki
   *
   */
  abstract static class AbstractRankerBuilder implements IRankerBuilder
  {
  	JCas jcas;
  	public void setJCas(JCas jcas)
  	{
  		this.jcas = jcas;
  	}	
  	public abstract IRanker instantiateRanker();
  }
  
  public AbstractRanker(AbstractRankerBuilder builder)
  {
	  this.jcas = builder.jcas;
  }
  
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
	return this.scoringAPI.score(this.jcas, this, question, passage);
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