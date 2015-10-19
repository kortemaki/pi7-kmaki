package rank;

import org.apache.uima.jcas.JCas;

import type.Passage;
import type.Question;
import type.Score;

public interface ScoringAPI {
  public Score score(JCas jcas, IRanker ranker, Question question, Passage passage);
}
