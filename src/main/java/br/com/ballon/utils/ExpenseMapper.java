package br.com.ballon.utils;

import br.com.ballon.application.expenses.DataExpense;
import br.com.ballon.application.expenses.GetDataExpense;
import br.com.ballon.application.expenses.GetDataExpenseUpdate;
import br.com.ballon.application.expenses.GetDataRecurringExpenses;
import br.com.ballon.domain.expenses.Expense;
import br.com.ballon.infra.expense.CategoryEntity;
import br.com.ballon.infra.expense.ExpenseEntity;
import br.com.ballon.infra.user.ConsumerEntity;

import java.time.Instant;
import java.time.Year;

public class ExpenseMapper {

    public static Expense toDomainByRegisterDto(GetDataExpense data) {
        return new Expense.Builder()
                .withMonth(data.month())
                .withTitle(data.title())
                .withYear(Year.of(data.year()))
                .withValue(data.value())
                .build();
    }

    public static Expense toDomainByRegisterRecurringDto(GetDataRecurringExpenses data) {
        return new Expense.Builder()
                .withMonth(data.month())
                .withTitle(data.title())
                .withYear(Year.of(data.year()))
                .withValue(data.value())
                .build();
    }

    public static Expense toDomainByUpdateDto(GetDataExpenseUpdate data) {
        return new Expense.Builder()
                .withMonth(data.month())
                .withTitle(data.title())
                .withYear(Year.of(data.year()))
                .withValue(data.value())
                .build();
    }

    public static ExpenseEntity toExpenseEntityByExepenseConsumerCategory(Expense expense, ConsumerEntity consumer, CategoryEntity category) {
        return new ExpenseEntity(
                expense.getId(),
                expense.getMonth(),
                expense.getYear(),
                expense.getTitle(),
                expense.getValue(),
                consumer,
                category,
                Instant.now(),
                null
        );
    }

    public static DataExpense toDataResponse(ExpenseEntity expenseEntity) {
        return new DataExpense(
                expenseEntity.getId(),
                expenseEntity.getTitle(),
                expenseEntity.getMonth(),
                expenseEntity.getYear().getValue(),
                expenseEntity.getValue(),
                expenseEntity.getCategories().getTitle().toString()
        );
    }
}
