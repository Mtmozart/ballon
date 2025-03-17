package br.com.ballon.infra.user;

import br.com.ballon.domain.expenses.Months;
import br.com.ballon.domain.user.Profile;
import br.com.ballon.infra.expense.ExpenseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "Consumer")
@Table(name = "consumers")
public class ConsumerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Profile typeUser = Profile.CONSUMER;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private Instant createdAt = Instant.now();

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant updatedAt;

    @OneToMany(mappedBy = "consumer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ExpenseEntity> expenses = new HashSet<>();  // Relacionamento com Expense

    // Construtores
    public ConsumerEntity() {
    }

    public ConsumerEntity(UUID id, String fullName, String email, String password, Profile typeUser, Instant createdAt, Instant updatedAt, Set<ExpenseEntity> expenses) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.typeUser = typeUser;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expenses = expenses;
    }

    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Profile getTypeUser() {
        return typeUser;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Set<ExpenseEntity> getExpenses() {
        return expenses;
    }
}
