package br.com.ballon.application.expenses;

import br.com.ballon.infra.expense.CategoryEntity;

import java.math.BigDecimal;

public record StaticsAllCategoryResults(String category, BigDecimal expense) {

    public StaticsAllCategoryResults {
        if (category == null || expense == null) {
            throw new IllegalArgumentException("Categoria e valor não podem ser nulos");
        }
    }
    public StaticsAllCategoryResults(CategoryEntity categoryEntity, BigDecimal expense) {
        this(categoryEntity.getTitle().name(), expense);
    }
}
