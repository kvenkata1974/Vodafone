package vodafone.exam.bowling.tenpin;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by karthik on 6/02/17.
 */
public class FrameTest {

    @Test
    public void testNoStrikeAndNoSpare() {

        Frame frame = new Frame();
        frame.setScore(0, 4);
        frame.setScore(1, 5);

        Assert.assertEquals(Boolean.FALSE, frame.isStrike());
        Assert.assertEquals(Boolean.FALSE, frame.isSpare());

    }

    @Test
    public void testStrike() {

        Frame frame = new Frame();
        frame.setScore(0, 10);

        Assert.assertEquals(Boolean.TRUE, frame.isStrike());

    }

    @Test
    public void testSpare() {

        Frame frame = new Frame();
        frame.setScore(0, 5);
        frame.setScore(1, 6);

        Assert.assertEquals(Boolean.TRUE, frame.isSpare());

    }
}
