package vodafone.exam.bowling.tenpin;

/**
 * Created by karthik on 6/02/17.
 */
public class Frame {

    public final static int MIN_SCORE = 0;
    public final static int MAX_SCORE = 10;
    public final static int MAX_SCORE_INDEX = 2;

    private int[] scores = {0, 0, 0};
    private Boolean spare = Boolean.FALSE;
    private Boolean strike = Boolean.FALSE;

    public int getScore(int index) {
        return scores[index];
    }

    public void setScore(int index, int score) {
        scores[index] = score;
        if (index == 0 && score == MAX_SCORE) {
            strike = Boolean.TRUE;
        }
        if (index == 1 && ((scores[0] + scores[1]) >= MAX_SCORE)) {
            spare = Boolean.TRUE;
        }
    }

    public Boolean isSpare() {
        return spare;
    }

    public void setSpare() {
        this.spare = Boolean.TRUE;
    }

    public Boolean isStrike() {
        return strike;
    }

    public void setStrike() {
        scores[0] = MAX_SCORE;
        this.strike = Boolean.TRUE;
    }
}
