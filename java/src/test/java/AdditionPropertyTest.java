import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.generator.java.lang.IntegerGenerator;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static utils.StupidUtils.sum;

@RunWith(JUnitQuickcheck.class)
public class AdditionPropertyTest {

    @Target({PARAMETER})
    @Retention(RUNTIME)
    @From(IntegerGenerator.class)
    @InRange(min = "0", max = "100")
    public @interface SmallInt {}

    @Property
    public void commutativityProperty(@SmallInt int x,
                                      @SmallInt int y) {
        assertThat(sum(x, y), equalTo(sum(y, x)));
    }

    @Property
    public void additiveProperty(@SmallInt int x) {
        int result1 = sum(sum(x , 1), 1);
        int result2 = sum(x, 2);
        assertEquals(result1, result2);    }

    @Property
    public void identityProperty(@SmallInt int x) {
        assertEquals(x, sum(x, 0));
    }
}
