import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Exercise05 {

    // THE GODMOTHER
    // ========================
    // Complete the numbered tasks below.
    // Open and execute the accompanying tests to confirm your solution is correct.

    // 1. Your Godmother gives you $10 every other Friday throughout the year.
    // Payments start on the first Friday of the year.
    // Given a date, calculate payments expected from that date until the end of the year.
    BigDecimal calculateGiftsTilEndOfYear(LocalDate date) {
        LocalDate first = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        LocalDate end = date.with(TemporalAdjusters.lastDayOfYear());
        long difference = first.until(end, ChronoUnit.WEEKS);

        BigDecimal balance = new BigDecimal(0);

        if (first.until(end, ChronoUnit.DAYS) < 7 && end.getDayOfWeek() == DayOfWeek.FRIDAY) {
            balance = balance.add(new BigDecimal(10));
        }

        for (int i = 0; i < difference; i += 2) {
            balance = balance.add(new BigDecimal(10));
        }


        return balance;
    }

    // 2. Your Godmother is getting quirky. She adjusted her payment schedule.
    // She still pays every other Friday throughout the year, starting on the first Friday of the year.
    // But now, she pays a number of dollars equal to the day of the month.
    // Examples
    // Jan 31 == $31
    // Mar 1 == $1
    // July 12 == $12
    // Given a date, calculate payments expected from that date until the end of the year.
    BigDecimal calculateQuirkyGiftsTilEndOfYear(LocalDate date) {
        int thisYear = date.getYear();
        LocalDate firstFridayOfYear = LocalDate.of(thisYear - 1, 12, 31).with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        BigDecimal gifts = BigDecimal.ZERO;

        LocalDate dt = firstFridayOfYear;
        while (dt.getYear() == thisYear) {
            if (!dt.isBefore(date)) {
                gifts = gifts.add(new BigDecimal(dt.getDayOfMonth()));
            }
            dt = dt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
            dt = dt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        }

        return gifts;
    }

}
