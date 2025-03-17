package br.com.ballon.infra.expense;

import br.com.ballon.domain.expenses.Category;
import br.com.ballon.domain.expenses.CategoryEnum;
import br.com.ballon.infra.user.ConsumerEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.Month;
import java.time.Year;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Expense")
@Table(name = "expenses")
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Month month;

    @Column(nullable = false)
    private Year year;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id", nullable = false)
    private ConsumerEntity consumer;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private Instant createdAt = Instant.now();

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant updatedAt;

    public ExpenseEntity() {
    }

    public ExpenseEntity(UUID id, Month month, Year year, String title, BigDecimal value, ConsumerEntity consumer, CategoryEntity category, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.month = month;
        this.year = year;
        this.title = title;
        this.value = value;
        this.consumer = consumer;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public Month getMonth() {
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

    public ConsumerEntity getConsumer() {
        return consumer;
    }

    public CategoryEntity getCategories() {
        return category;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
