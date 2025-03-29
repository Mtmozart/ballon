package br.com.ballon.application.expenses;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.Month;
import java.util.UUID;

public record GetDataRecurringExpenses(
        @NotNull(message = "O mês não pode ser nulo")
        Month month,

        @NotNull(message = "O ano não pode ser nulo")
        int year,

        @NotBlank(message = "O título não pode estar em branco")
        String title,

        @NotNull(message = "O valor não pode ser nulo")
        @Positive(message = "O valor deve ser positivo")
        BigDecimal value,

        @NotNull(message = "A categoria não pode ser nula")
        @Positive(message = "O valor deve ser positivo")
        Long categoriaId,

        @NotNull(message = "O consumidor não pode ser nulo")
        UUID consumerId,

        @NotNull(message = "Deve informar a quantidade de recorrência")
        @Positive(message = "O valor deve ser positivo")
        Long recurring
) {
}
