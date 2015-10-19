package rank;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.apache.uima.jcas.JCas;

import type.Passage;
import type.Question;
import type.Score;

/*
 * TODO: documentation
 */
public abstract class CompositeRanker extends AbstractRanker {

  List<IRanker> rankers;
  protected CompositionAPI compositionAPI;
  
  public CompositeRanker(JCas jcas) {
    rankers = new ArrayList<IRanker>();
    this.scoringAPI = new AggregateScoringAPIImpl();
    this.jcas = jcas;
  }

  public void addRanker(IRanker ranker)
  {
	  rankers.add(ranker);
  };
  
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

class AggregateScoringAPIImpl implements ScoringAPI {
	public Score score(JCas jcas, IRanker theRanker, Question question, Passage passage) {
		Score score = null;
		if( theRanker instanceof CompositeRanker ) {
			CompositeRanker ranker = (CompositeRanker) theRanker;
			List<Score> scores = new ArrayList<Score>(ranker.rankers.size()); 
			for(IRanker r : ranker.rankers)
				scores.add(r.score(question,passage));
			score = ranker.compositionAPI.compose(jcas, theRanker, scores);
		}
		else
		{
			throw new NotImplementedException();
		}
		
		return score;
	}	
}

