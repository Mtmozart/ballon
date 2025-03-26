package br.com.ballon.infra.expense;

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

    @Modifying(clearAutomatically = true)
    @Query("SELECT e FROM Expense e WHERE e.consumer.id = :userId")
    Optional<List<ExpenseEntity>> getAllByUser(UUID userId);

}
