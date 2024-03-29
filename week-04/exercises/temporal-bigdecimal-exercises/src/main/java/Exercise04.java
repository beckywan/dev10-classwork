import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Exercise04 {

    // BigDecimal
    // ========================
    // Complete the numbered tasks below.
    // Open and execute the accompanying tests to confirm your solution is correct.

    // 1. add a, b, and c together and return the result
    BigDecimal add(BigDecimal a, BigDecimal b, BigDecimal c) {
        return a.add(b).add(c);
    }

    // 2. divide a by b and return the result with only two decimal points
    BigDecimal divideWithTwoDecimalPlaces(BigDecimal a, BigDecimal b) {
        return a.divide(b,2, RoundingMode.HALF_UP);
    }

    // 3. calculate the sum of elements in values and return it
    // with a scale of 4.
    BigDecimal sum(BigDecimal[] values) {
        BigDecimal total = new BigDecimal(0);
        for(BigDecimal v : values) {
            total = total.add(v);
        }
        return total;
    }

    // 4. calculate the average of elements in values
    BigDecimal average(BigDecimal[] values) {
        BigDecimal result = sum(values).divide(BigDecimal.valueOf(values.length));
        return result.setScale(4, RoundingMode.HALF_UP);
    }

    /**
     * 5. complete the calculateInterest method using the spec below.
     * <p>
     * Calculates the total interest earned on an investment.
     * Does *not* calculate the final balance, just the interest over and above the initial investment.
     *
     * @param investment   the starting balance
     * @param interestRate the interest rate expressed
     * @param periods      number of periods in which to apply the interest
     * @return total interest earned (final balance - initial investment)
     */
    BigDecimal calculateInterest(BigDecimal investment, BigDecimal interestRate, int periods) {

        BigDecimal balance = investment;

        for (int i = 0; i < periods; i++) {
            balance = balance.add(balance.multiply(interestRate));
        }

        BigDecimal totalInterest = balance.subtract(investment);
        return totalInterest;
    }
}
