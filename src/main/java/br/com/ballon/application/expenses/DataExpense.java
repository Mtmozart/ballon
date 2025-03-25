package br.com.ballon.application.expenses;

import java.math.BigDecimal;
import java.time.Month;
import java.util.UUID;

public record DataExpense(
        UUID id,
        String title,
        Month month,
        int year,
        BigDecimal value,
        String category
) {
}
