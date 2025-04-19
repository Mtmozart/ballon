package br.com.ballon.application.expenses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.Month;
import java.util.UUID;

public record GetDataExpense(
        @NotNull(message = "{expense.month.notnull}")
        Month month,

        @NotNull(message = "{expense.year.notnull}")
        int year,

        @NotBlank(message = "{expense.title.notblank}")
        String title,

        @NotNull(message = "{expense.value.notnull}")
        @Positive(message = "{expense.value.positive}")
        BigDecimal value,

        @NotNull(message = "{expense.consumer.notnull}")
        UUID consumerId,

        @NotNull(message = "{expense.category.notnull}")
        Long categoriaId
) {
}