package rank;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.apache.uima.jcas.JCas;

import type.Passage;
import type.Question;
import type.Score;

/*
 * CompositeRanker abstract class for pi7-kmaki
 * Describes a general ranker class which aggregates scores
 *   from one or more rankers using the CompositionAPI.compose method.
 *   
 * The Bridge pattern helps to decouple the abstract CompositeRanker class and extending subclasses
 *   from the CompositionAPI interface class describing the method of composition.
 *   
 * The Builder pattern is used to ensure that changes to the CompositeRanker instantiation
 *   do not directly impact User class code, and changes to User class code likewise do not
 *   necessitate changes to the CompositeRanker instantiation.
 *   
 *   This pattern has the added benefit that rankers may be instantiated cleanly
 *     within different JCas environments by the same Builder instance.
 */
public abstract class CompositeRanker extends AbstractRanker 
{

  List<IRanker> rankers;
  protected CompositionAPI compositionAPI;
  
  /**
   * Abstract Builder class for the CompositeRanker
   * 
   * Handles the list of IRankerBuilders 
   *   with which to build the rankers for the composite ranker
   *
   * @author maki
   */
  public abstract static class CompositeRankerBuilder extends AbstractRankerBuilder
  {
    List<IRankerBuilder> rankerBuilders;
	 
	public CompositeRankerBuilder()
	{
	  rankerBuilders = new ArrayList<IRankerBuilder>();
	}
	  
	public void addRankerBuilder( IRankerBuilder builder )
	{
	  rankerBuilders.add( builder );
	};
	  
	/**
	 * Method to change the JCas for future CompositeRanker builds
	 * For convenience, also changes the jcas for any added rankers.
	 */
	@Override
	public void setJCas( JCas jcas )
	{
	  super.setJCas( jcas );
	  for( IRankerBuilder builder : rankerBuilders )
	  {
	    builder.setJCas( jcas );
	  }
	}
  }
  
  public CompositeRanker( CompositeRankerBuilder builder ) 
  {
	super(builder);
    rankers = new ArrayList<IRanker>();
    for( IRankerBuilder r : builder.rankerBuilders )
    {
    	rankers.add( r.instantiateRanker() );
    }
    this.scoringAPI = new AggregateScoringAPIImpl();
  }
  
  public String toString()
  {
	  List<String> rankerStrings = new ArrayList<String>( rankers.size() );
	  for( IRanker ranker : rankers )
	  {
		  rankerStrings.add( ranker.toString() );
	  }
	  return this.getClass().getName()+":{ " + String.join(", ",rankerStrings) + " }";
  }
}

/**
 * Aggregate ScoringAPI implementation for pi7-kmaki
 * 
 * Aggregates the scores given to a question-passage pair 
 *   under all component rankers included in CompositeRanker theRanker 
 *   by using theRanker's CompositionAPI.compose function
 *   
 * @requires theRanker instanceof CompositeRanker
 * @author maki
 */
class AggregateScoringAPIImpl implements ScoringAPI 
{
  public Score score( JCas jcas, IRanker theRanker, Question question, Passage passage ) 
  {
	Score score = null;
	if( theRanker instanceof CompositeRanker ) 
	{
      CompositeRanker ranker = (CompositeRanker) theRanker;
      List<Score> scores = new ArrayList<Score>( ranker.rankers.size() ); 
      for( IRanker r : ranker.rankers )
    	scores.add( r.score(question,passage) );
      score = ranker.compositionAPI.compose( jcas, theRanker, scores );
	}
	else
	{
	  throw new NotImplementedException();
	}
	return score;
  }	
}

