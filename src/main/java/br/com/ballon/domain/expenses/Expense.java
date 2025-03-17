package br.com.ballon.domain.expenses;

import br.com.ballon.domain.exception.BallonException;
import br.com.ballon.domain.user.Consumer;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Objects;
import java.util.UUID;

public class Expense {
    private UUID id;
    private Months month;
    private Year year;
    private String title;
    private BigDecimal value;
    private Category category;
    private SubCategory subCategory;
    private Consumer consumer;

    public Expense() {
    }

    public Expense(UUID id, Months month, Year year, String title, BigDecimal value, Category category, SubCategory subCategory) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.title = title.toLowerCase();
        this.value = value;
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new BallonException("O valor não pode ser menor que zero.");
        }
        this.category = category;
        this.subCategory = subCategory;
    }

    public UUID getId() {
        return id;
    }

    public Months getMonth() {
        return month;
    }

    public Year getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Category getCategory() {
        return category;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void addConsumer(Consumer consumer) {
        this.consumer = consumer;
    }


    public static class Builder {

        private Expense expense;

        public Builder() {
            expense = new Expense();
        }

        public Builder withMonth(Months month) {
            expense.month = month;
            return this;
        }

        public Builder withYear(Year year) {
            expense.year = year;
            return this;
        }

        public Builder withTitle(String title) {
            if (title == null || title.trim().isEmpty()) {
                throw new BallonException("O título não pode ser nulo ou vazio.");
            }
            expense.title = title.toLowerCase();
            return this;
        }

        public Builder withValue(BigDecimal value) {
            if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
                throw new BallonException("O valor não pode ser nulo ou menor que zero.");
            }
            expense.value = value;
            return this;
        }

        public Builder withCategory(Category category) {
            if (category == null) {
                throw new BallonException("A categoria não pode ser nula.");
            }
            expense.category = category;
            return this;
        }

        public Builder withSubCategory(SubCategory subCategory) {
            if (subCategory == null) {
                throw new BallonException("A categoria não pode ser nula.");
            }
            expense.subCategory = subCategory;
            return this;
        }

        public Builder withConsumer(Consumer consumer) {
            if (consumer == null) {
                throw new BallonException("O consumidor não pode ser nulo.");
            }
            expense.consumer = consumer;
            return this;
        }

        public Expense build() {
            expense.geraIdentificadorUnico();
            return expense;
        }
    }

    private void geraIdentificadorUnico() {
        this.id = UUID.randomUUID();
    }

    public void update(Months month, Year year, String title, BigDecimal value, Category category) {
        if (month == null || year == null || title == null || value == null || category == null || consumer == null) {
            throw new BallonException("Parâmetros inválidos.");
        }
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new BallonException("O valor não pode ser menor que zero.");
        }
        this.month = month;
        this.year = year;
        this.title = title;
        this.value = value;
        this.category.update(category.getTitle());
    }


    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", month=" + month +
                ", year=" + year +
                ", title='" + title + '\'' +
                ", value=" + value +
                ", category=" + category +
                ", subCategory=" + subCategory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return Objects.equals(id, expense.id) && month == expense.month && Objects.equals(year, expense.year) && Objects.equals(title, expense.title) && Objects.equals(value, expense.value) && Objects.equals(category, expense.category) && Objects.equals(consumer, expense.consumer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, month, year, title, value, category, consumer);
    }
}
