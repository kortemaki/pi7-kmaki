package rank;

public class Score implements Comparable<Score> {
	Double score;
	
	public int compareTo(Score other)
	{
		return this.score.compareTo(other.score);
	}
}
