package rank;

import java.util.ArrayList;
import java.util.List;

import type.Passage;
import type.Question;

class IndexedScore extends Score{
	Double score;
	int index;
	
	IndexedScore(Double s, int i)
	{
		this.score = s;
		this.index = i;
	}
}

/**
 * Wrapper class for the IRanker interface
 * Adds Float weight which is associated with the given ranker
 * @author maki
 *
 */
class WeightedRanker implements IRanker{
	IRanker ranker;
	Float weight;
	
	WeightedRanker(IRanker r, Float w)
	{
		this.ranker = r;
		this.weight = w;
	}

	public List<Passage> rank(Question question, List<Passage> passages) {
		return this.ranker.rank(question,passages);
	}

	public Double score(Question question, Passage passage) {
		return this.ranker.score(question,passage);
	}
}

public class CompositeRanker extends AbstractRanker implements IAggregator {

  /** Individual rankers */
  private List<WeightedRanker> rankers;
  
  public CompositeRanker() {
    rankers = new ArrayList<WeightedRanker>();
  }

  public void addRanker(IRanker ranker) {
    rankers.add(new WeightedRanker(ranker,(float) 1));
  }

  /**
   * Returns a score of the given passage associated with the given question.
   * 
   * @param question
   * @param passage
   * @return a score of the passage
   */
  @Override
  public Double score(Question question, Passage passage) {
    List<Score> scores = new ArrayList<Score>();
    for (int index = 0; index < rankers.size(); index++) {
      IRanker ranker = rankers.get(index).ranker;
      scores.add(new IndexedScore(ranker.score(question, passage),index));
    }
    return aggregateScores(scores).score;
  }

  //Compute the aggregated score by taking a weighted average of scores.
  //IndexedScores make use of the weight assigned to this ranker's list of Weighted Rankers
  public Score aggregateScores(List<Score> scores) {

	Double weightedSum = 0.0;
    for(Score score : scores) {
    	Float weight = (float) 1;
    	if(score instanceof IndexedScore)
    	{
    		weight = rankers.get(((IndexedScore) score).index).weight;
    	}
    	weightedSum += score.score*weight;    	
    }
	  
    Score score = new Score();
    score.score = weightedSum/rankers.size();
    return score;
  }

  public String toString()
  {
	  List<String> rankerStrings = new ArrayList<String>(rankers.size());
	  for(IRanker ranker : rankers)
	  {
		  rankerStrings.add(ranker.toString());
	  }
	  return this.getClass().getName()+":{ " + String.join(", ",rankerStrings) + " }";
  }
  
}
