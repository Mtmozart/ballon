package br.com.ballon.infra.expense;

import br.com.ballon.domain.expenses.CategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true, length = 50)
    private CategoryEnum title;

    public CategoryEntity() {
    }

    public CategoryEntity(Long id, CategoryEnum title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public CategoryEnum getTitle() {
        return title;
    }


}
