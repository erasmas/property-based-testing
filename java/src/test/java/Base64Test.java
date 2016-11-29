import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitQuickcheck.class)
public class Base64Test {

    @Property
    public void encodedStringShouldDecodeToTheSameString(String s) {
        String encoded = Base64.encode(s.getBytes());
        String decoded = new String(Base64.decode(encoded));
        assertEquals(s, decoded);
    }
}
