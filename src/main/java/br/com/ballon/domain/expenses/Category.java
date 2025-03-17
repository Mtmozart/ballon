package br.com.ballon.domain.expenses;

import br.com.ballon.domain.exception.BallonException;

import java.util.List;

public class Category {
    private long id;
    private CategoryEnum title;

    public long getId() {
        return id;
    }

    public CategoryEnum getTitle() {
        return title;
    }

    public Category(long id, CategoryEnum title) {
        if (title == null) {
            throw new BallonException("Categoria não pode ser nula.");
        }
        this.id = id;
        this.title = title;
    }

    public void update(CategoryEnum title) {
        if (title == null) {
            throw new BallonException("Categoria não pode ser nula.");
        }
        this.title = title;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", title=" + title +
                '}';
    }
}
