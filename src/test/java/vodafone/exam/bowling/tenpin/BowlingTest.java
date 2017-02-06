package vodafone.exam.bowling.tenpin;


import org.junit.Test;
import org.junit.Assert;

/**
 * Created by karthik on 6/02/17.
 */
public class BowlingTest {

    @Test
    public void testInvalidScoresBowling() {

        String[] scores = {"1", "12", "3", "4"};

        try {
            Bowling bowling = new Bowling(scores);
        }
        catch (Exception e) {
            Assert.assertEquals(Bowling.RANGE_ERROR, e.getMessage());
        }

    }

    @Test
    public void testNonNumericScoresBowling() {

        String[] scores = {"1", "g", "3", "4"};

        try {
            Bowling bowling = new Bowling(scores);
        }
        catch (Exception e) {
            Assert.assertEquals(NumberFormatException.class, e.getClass());
        }

    }

    @Test
    public void testPartialScoresBowling() {

        String[] scores = {"9", "1", "9", "1"};

        Bowling bowling = new Bowling(scores);
        Assert.assertEquals(29, bowling.tallyFrames());

    }

    @Test
    public void testAllStrikesBowling() {

        String[] scores = {"10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10", "10"};

        Bowling bowling = new Bowling(scores);
        Assert.assertEquals(300, bowling.tallyFrames());

    }


}
