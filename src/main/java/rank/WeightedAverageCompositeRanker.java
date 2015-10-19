package rank;

import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.apache.uima.jcas.JCas;

import type.Score;

/**
 * Refined abstraction of the Composite ranker type
 * More concretely specifies the composition scheme
 *   as a weighted average of the rankers added
 * 
 * @author maki
 *
 */
public class WeightedAverageCompositeRanker extends CompositeRanker {
  /** Individual rankers */
  public List<Float> weights;
  
  public WeightedAverageCompositeRanker(JCas jcas)
  {
	super(jcas);
  }
  public void addWeightedRanker(IRanker ranker, Float weight)
  {
	  this.addRanker(ranker);
	  this.addWeight(weight);
  };
  public void addWeight(Float weight)
  {
	  weights.add(weight);
  };
}

class IndexedScore extends Score{
	Double score;
	int index;
	
	IndexedScore(Double s, int i)
	{
		this.score = s;
		this.index = i;
	}
}

class WeightedAverageCompositionAPIImpl implements CompositionAPI{
  /**
   * Compute the aggregated score by taking a weighted average of scores.
   * IndexedScores make use of the weight assigned to this ranker's list of Weighted Rankers
   */
  public Score compose(JCas jcas, IRanker theRanker, List<Score> scores) {
	if(theRanker instanceof WeightedAverageCompositeRanker){
		WeightedAverageCompositeRanker ranker = (WeightedAverageCompositeRanker) theRanker;
	
		Double weightedSum = 0.0;
		for(Score score : scores) {
			Float weight = (float) 1;
			
			if(score instanceof IndexedScore) {	
				weight = ranker.weights.get(((IndexedScore) score).index);
			}
			weightedSum += score.getScore()*weight;    	
		}
		
		Score score = new Score(jcas);
		score.setScore(weightedSum/scores.size());
		score.setComponentId(theRanker.toString());
		return score;
		}
		else 
		{
			throw new NotImplementedException();
		}
  	}
}
