package br.com.ballon.infra.expense;

import br.com.ballon.domain.expenses.Months;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseEntityRepository extends JpaRepository<ExpenseEntity, UUID> {

    @Modifying
    @Query("UPDATE Expense e SET e.month = :month, e.year = :year, e.title = :title, e.value = :value, e.category.id = :categoryId, e.updatedAt = :updatedAt WHERE e.id = :id")
    Optional<Integer> updateExpense(Month month, Year year, String title, BigDecimal value, Long categoryId, Instant updatedAt, UUID id);

    @Query("SELECT e FROM Expense e WHERE e.consumer.id = :userId")
    Optional<List<ExpenseEntity>> getAllByUser(UUID userId);

    @Query("SELECT e FROM Expense e WHERE e.category.id = :categoryId AND e.consumer.id = :consumerId")
    Optional<List<ExpenseEntity>> getExpensesByCategoryId(Long categoryId, UUID consumerId);

    @Query("SELECT e FROM Expense e WHERE e.month = :month AND e.consumer.id = :consumerId AND e.year = 2025 ")
    Optional<List<ExpenseEntity>> getExpensesByMonth(Month month, UUID consumerId);

    @Query("SELECT e FROM Expense e WHERE e.year = :year AND e.consumer.id = :consumerId")
    Optional<List<ExpenseEntity>> getExpensesByYear(Year year, UUID consumerId);

    @Query("SELECT e FROM Expense e WHERE e.month = :month AND e.consumer.id = :consumerId AND e.year = :year ")
    Optional<List<ExpenseEntity>> getExpensesByMonthAndCategoryAndYear(Month month, UUID consumerId, Year year);

}
