package rank;

import java.util.List;

import org.apache.uima.jcas.JCas;

import type.Score;

public interface CompositionAPI {
	public Score compose(JCas jcas, IRanker theRanker, List<Score> scores);
}
