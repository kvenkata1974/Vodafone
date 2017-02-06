package vodafone.exam.bowling.tenpin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by karthik on 5/02/17.
 */
public class Bowling {

    public final static int MAX_FRAMES = 10;

    public final static String RANGE_ERROR = "Score outside of range";

    private List<Integer> scores;

    private List<Frame> frames;

    private Boolean tallyDone = Boolean.FALSE;

    private int totalScore = 0;


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
                    // Roll over to new frame in next iteration as boolean "newFrame" is still true
                    addFrame(currentFrame);
                } else {
                    newFrame = Boolean.FALSE;
                }
            } else {
                scoreIndex++;
                currentFrame.setScore(scoreIndex, i);

                // Frame roll over check, last frame can have 3 score entries in case of strike or spare
                if (frames.size() < MAX_FRAMES-1 ||
                        (scoreIndex == Frame.MAX_SCORE_INDEX || !currentFrame.isStrike() && !currentFrame.isSpare())) {
                    addFrame(currentFrame);
                    newFrame = Boolean.TRUE;
                }

            }

        }

        if (!newFrame) {
            // Incomplete frame due to lack of entry(s)
            addFrame(currentFrame);
        }
    }

    public Boolean addFrame(Frame f) {

        if (frames.size()  == MAX_FRAMES) {
            return Boolean.FALSE;
        }

        tallyDone = Boolean.FALSE;

        return frames.add(f);

    }

    public int tallyFrames() {

        if (!tallyDone) {

            int tally = 0;

            for (int i = 0; i < frames.size(); i++) {
                tally += tallyFrame(i);
            }

            totalScore = tally;
            tallyDone = Boolean.TRUE;
        }


        return totalScore;
    }

    // The tally score for a single frame after evaluating strike and spare
    private int tallyFrame(int frameNumber) {

        int tally = 0;

        if (frameNumber < frames.size()) {

            Frame currentFrame = frames.get(frameNumber);

            // Last frame is a special case
            if (frameNumber == MAX_FRAMES -1) {
                if (currentFrame.isStrike()) {
                    tally = Frame.MAX_SCORE + currentFrame.getScore(1) + currentFrame.getScore(2);
                } else if (currentFrame.isSpare()) {
                    tally = Frame.MAX_SCORE + currentFrame.getScore(2);
                } else
                    tally = currentFrame.getScore(0) + currentFrame.getScore(1);
            } else { // Not last frame
                Frame zeroFrame = new Frame();

                Frame nextFrame = ((frameNumber + 1) < frames.size()) ? frames.get(frameNumber + 1) : zeroFrame;
                Frame nextNextFrame = ((frameNumber + 2) < frames.size()) ? frames.get(frameNumber + 2) : zeroFrame;

                if (currentFrame.isStrike()) {
                    if (nextFrame.isStrike()) {
                        // Special case for last but one frame, use the second slot of last frame
                        if (frameNumber == MAX_FRAMES -2) {
                            tally = (Frame.MAX_SCORE * 2) + nextFrame.getScore(1);
                        } else {
                            tally = (Frame.MAX_SCORE * 2) + nextNextFrame.getScore(0);
                        }

                    } else if (nextFrame.isSpare()) {
                        tally = Frame.MAX_SCORE * 2;
                    } else {
                        tally = Frame.MAX_SCORE + nextFrame.getScore(0) + nextFrame.getScore(1);
                    }

                } else if (currentFrame.isSpare()) {
                    tally = Frame.MAX_SCORE + nextFrame.getScore(0);
                } else
                    tally = currentFrame.getScore(0) + currentFrame.getScore(1);
            }


        }

        return tally;
    }

    public static void main (String[] args) {

        Bowling bowling = new Bowling(args);
        int tally = bowling.tallyFrames();

        System.out.println(Arrays.toString(args) + " --> " + tally);

    }
}
