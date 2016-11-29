import java.util.*;

class MoneyChanger {

    private static int maxDenominator(int m, List<Integer> denominations) {
        // assumes that denominations is a sorted array
        for (int denomination : denominations) {
            if (denomination <= m) {
                return denomination;
            }
        }
        throw new IllegalArgumentException(String.format("Couldn't find denominator for %d", m));
    }

    List<Integer> change(int m, Set<Integer> denominations) {
        int changedMoney = 0;
        ArrayList<Integer> sortedDenominations = new ArrayList<>(denominations);
        sortedDenominations.sort(Collections.reverseOrder());
        ArrayList<Integer> coinsUsed = new ArrayList<>();
        while (changedMoney != m) {
            final int change = m - changedMoney;
            int denominator = maxDenominator(change, sortedDenominations);
            if (denominator <= change) {
                final int numberOfCoins = change / denominator;
                changedMoney += numberOfCoins * denominator;
                for (int i = 0; i < numberOfCoins; i++) {
                    coinsUsed.add(denominator);
                }
            }
        }
        return coinsUsed;
    }

}
