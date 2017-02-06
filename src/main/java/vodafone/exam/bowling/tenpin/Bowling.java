package vodafone.exam.bowling.tenpin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karthik on 5/02/17.
 */
public class Bowling {

    public final static int MAX_FRAMES = 10;

    public final static String RANGE_ERROR = "Score outside of range";

    private List<Integer> scores;
    private int frameCount = 0;
    private List<Frame> frames;


    public Bowling(String unverifiedScores[]) {

        scores = new ArrayList<Integer>();
        frames = new ArrayList<Frame>();

        for (String s : unverifiedScores) {
            int i = Integer.parseInt(s);
            if (i < Frame.MIN_SCORE || i > Frame.MAX_SCORE) {
                throw new RuntimeException(RANGE_ERROR);
            }
            scores.add(i);
        }

        Boolean newFrame = Boolean.TRUE;
        Frame currentFrame = null;
        int scoreIndex = 0;

        for (int i : scores) {
            if (newFrame) {
                currentFrame = new Frame();
                scoreIndex = 0;

                currentFrame.setScore(scoreIndex, i);

                // Check if strike and current frame is not the last frame
                if (currentFrame.isStrike() && frames.size() < MAX_FRAMES-1) {
                    // Roll over to new frame in next iteration as boolean is still true
                    addFrame(currentFrame);
                } else {
                    newFrame = Boolean.FALSE;
                }
            } else {
                scoreIndex++;
                currentFrame.setScore(scoreIndex, i);

                // Frame roll over check
                if (frames.size() < MAX_FRAMES-1 ||
                        (scoreIndex == Frame.MAX_SCORE_INDEX || !currentFrame.isStrike() && !currentFrame.isSpare())) {
                    addFrame(currentFrame);
                    newFrame = Boolean.TRUE;
                }

            }

        }
    }

    public Boolean addFrame(Frame f) {

        if (frames.size()  == MAX_FRAMES) {
            return Boolean.FALSE;
        }

        return frames.add(f);

    }

    public static void main (String[] args) {

        Bowling bowling = new Bowling(args);

    }
}
