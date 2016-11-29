import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.generator.InRange;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.hamcrest.Matchers;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

@RunWith(JUnitQuickcheck.class)
public class MoneyChangerTest {

    @Property
    public void sumOfCoinsEqualsAmount(
            @InRange(min = "0", max = "500") int amountToChange,
            Set<@InRange(min = "1", max = "100") Integer> denominations) {

        assumeThat(denominations.size(), greaterThan(0));
        denominations.add(1);

        MoneyChanger moneyChanger = new MoneyChanger();
        List<Integer> coins = moneyChanger.change(amountToChange, denominations);

        int sum = coins.stream().mapToInt(coin -> coin).sum();
        assertThat(sum, equalTo(amountToChange));
    }

    @Property
    // Smaller coins summing up to a larger coin should be replaced by that larger coin.
    public void numberOfCoinsIsMinimal(@InRange(min = "0", max = "500") int amountToChange) {
        Set<Integer> denominations = new HashSet<>(Arrays.asList(200, 100, 50, 20, 10, 5, 1));

        MoneyChanger moneyChanger = new MoneyChanger();
        List<Integer> coins = moneyChanger.change(amountToChange, denominations);

        for (int d : denominations) {
            int sumOfSmallerCoins = coins.stream()
                    .filter(coin -> coin < d)
                    .mapToInt(coin -> coin)
                    .sum();
            assertThat(sumOfSmallerCoins, Matchers.lessThan(d));
        }
    }

}
