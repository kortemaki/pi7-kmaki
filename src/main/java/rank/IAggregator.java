package rank;

import java.util.List;

public interface IAggregator {

  /**
   * Aggregates the given scores, and returns a resulting score.
   * 
   * @param scores
   * @return an aggregated score
   */
  public Score aggregateScores(List<Score> scores);

}
