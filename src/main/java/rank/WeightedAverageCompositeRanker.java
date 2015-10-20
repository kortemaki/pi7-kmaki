package rank;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.apache.uima.jcas.JCas;

import type.Score;

/**
 * Refined abstraction of the Composite ranker type
 * Concretely specifies the composition scheme
 *   as a weighted average of the rankers added
 * 
 * @author maki
 */
public class WeightedAverageCompositeRanker extends CompositeRanker 
{
  /** Individual rankers */
  public List<Float> weights;
  
  /******** Builder pattern implementation ********/
  public static class WeightedAverageCompositeRankerBuilder 
  	extends CompositeRankerBuilder
  {
	  public List<Float> weights;
	  public WeightedAverageCompositeRankerBuilder()
	  {
		  super();
		  weights = new ArrayList<Float>();
	  }

	@Override
	public IRanker instantiateRanker() {
	  return new WeightedAverageCompositeRanker(this);
	}

    public void addWeight(Float weight)
	{
	  weights.add(weight);
	}
  }
  /******* End Builder pattern implementation ******/
  
  public WeightedAverageCompositeRanker(WeightedAverageCompositeRankerBuilder builder)
  {
	super(builder);
	this.weights = new ArrayList<Float>();
	for(float weight : builder.weights)
	{
		this.weights.add(weight);
	}
	this.compositionAPI = new WeightedAverageCompositionAPIImpl();
  }
}

/**
 * Weighted Average CompositionAPI implementation for pi7-kmaki
 * Describes a method to produce a composite Score from a List<Score>.
 * In this case, uses a weighted average of the scores, as defined by theRanker's List of weights. 
 *  
 * @author maki
 */
class WeightedAverageCompositionAPIImpl implements CompositionAPI
{

  private final String TOO_FEW_WEIGHTS_MSG
   		= "WeightedAverageCompositeRanker instantiated with too few weights";
  /**
   * Compute the aggregated score by taking a weighted average of scores.
   * IndexedScores make use of the weight assigned to this ranker's list of Weighted Rankers
   */
  public Score compose(JCas jcas, IRanker theRanker, List<Score> scores) 
  {
	if(theRanker instanceof WeightedAverageCompositeRanker)
	{
	  WeightedAverageCompositeRanker ranker = (WeightedAverageCompositeRanker) theRanker;
	
	  Double weightedSum = 0.0;
	  int i = 0;
	  for(Score score : scores) {
		Float weight = (float) 1;
	    try 
	    {	
	      weight = ranker.weights.get(i);
	    }	
	    catch(Throwable e)
	    {
	      throw new NotImplementedException(TOO_FEW_WEIGHTS_MSG);
	    }
		weightedSum += score.getScore()*weight;    	
	    i++;
	  }
	  Score score = new Score(jcas);
	  if(scores.size()==0)
	  {
		System.out.println("NO SCORES!?");
		score.setScore(0);
	  }
	  else
	  {
		System.out.println(weightedSum);
		score.setScore(weightedSum/scores.size());
	  }
	  score.setComponentId(theRanker.toString());
	  return score;
	}
	else 
	{
	  throw new NotImplementedException();
	}
  }
}

