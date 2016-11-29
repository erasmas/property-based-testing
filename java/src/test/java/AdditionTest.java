import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static utils.StupidUtils.sum;

public class AdditionTest {

    private static final Random RANDOM = new Random();

    @Test
    public void testSumUsingCommutativityProperty() {
        // Changing the order of arguments shouldn't change their sum
        for (int i = 0; i < 100; i++) {
            int x = randomInt();
            int y = randomInt();
            assertThat(sum(x, y), equalTo(sum(y, x)));
        }
    }

    @Test
    // `x + 2` is the same as `x + 1 + 1`
    public void testSumWithAdditiveProperty() {
        for (int i = 0; i < 100; i++) {
            int x = randomInt();
            int result1 = sum(sum(x , 1), 1);
            int result2 = sum(x, 2);
            assertEquals(result1, result2);
        }
    }

    @Test
    // `x + 1` = `x`
    public void testSumWithIdentityProperty() {
        for (int i = 0; i < 100; i++) {
            int x = randomInt();
            assertEquals(x, sum(x, 0));
        }
    }

    private int randomInt() {
        return RANDOM.nextInt(100);
    }

}
