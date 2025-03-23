package br.com.ballon.infra.expense;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "subcategories")
public class SubCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private Long categoryId;

    @Column(nullable = false)
    private UUID consumerId;

    public SubCategoryEntity() {
    }

    public SubCategoryEntity(Long id, String title, Long categoryId, UUID consumerId) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.consumerId = consumerId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public UUID getConsumerId() {
        return consumerId;
    }
}
