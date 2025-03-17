package br.com.ballon.domain.user;

import br.com.ballon.domain.expenses.Expense;
import br.com.ballon.domain.expenses.Months;

import java.math.BigDecimal;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class Consumer extends User {
    private final Set<Expense> expenses = new HashSet<>();

    protected Consumer(ConsumerBuilder builder) {
        super(builder);
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
        expense.addConsumer(this);
    }

    public void removeExpense(Expense expense) {
        expenses.remove(expense);
    }

    public BigDecimal monthlyAndYearExpenses(Months month, Year year) {
        return expenses.stream()
                .filter(e -> e.getMonth().equals(month) && e.getYear().equals(year))
                .map(Expense::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + getId() +
                ", profile=" + getProfile() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", expenses=" + expenses +
                '}';
    }

    public static class ConsumerBuilder extends User.Builder<ConsumerBuilder> {

        public ConsumerBuilder withExpenses(Set<Expense> expenses) {
            return this;
        }

        @Override
        protected ConsumerBuilder self() {
            return this;
        }

        @Override
        public Consumer build() {
            validate();
            validateConsumer();
            return new Consumer(this);
        }


        private void validateConsumer() {
//            if (expenses == null) {
//                throw new IllegalArgumentException("Invalid parameters: expenses must not be null");
//            }
        }
    }
}