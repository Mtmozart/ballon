package br.com.ballon.domain.expenses;

import br.com.ballon.domain.exception.BallonException;

import java.util.Objects;
import java.util.UUID;

public class SubCategory {

    private long id;
    private String name;
    private long categoryId;
    private UUID userId;

    public SubCategory() {
    }

    public SubCategory(long id, String name, long categoryId, UUID userId) {
        if (name == null || name.trim().isEmpty()) {
            throw new BallonException("O nome da subcategoria não pode ser nulo ou vazio.");
        }
        if (categoryId <= 0) {
            throw new BallonException("O ID da categoria não pode ser zero ou negativo.");
        }

        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.userId = userId;
    }

    public static class Builder {
        private SubCategory subCategory;

        public Builder() {
            subCategory = new SubCategory();
        }

        public Builder id(long id) {
            subCategory.id = id;
            return this;
        }

        public Builder name(String name) {
            subCategory.name = name;
            return this;
        }

        public Builder categoryId(long categoryId) {
            subCategory.categoryId = categoryId;
            return this;
        }

        public Builder userId(UUID userId) {
            subCategory.userId = userId;
            return this;
        }

        public SubCategory build() {
            if (subCategory.name == null || subCategory.name.trim().isEmpty()) {
                throw new BallonException("O nome da subcategoria não pode ser nulo ou vazio.");
            }
            if (subCategory.categoryId <= 0) {
                throw new BallonException("O ID da categoria não pode ser zero ou negativo.");
            }
            return subCategory;
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void updateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new BallonException("O nome da subcategoria não pode ser nulo ou vazio.");
        }
        this.name = name;
    }

    public void updateCategoryId(long categoryId) {
        if (categoryId <= 0) {
            throw new BallonException("O ID da categoria não pode ser zero ou negativo.");
        }
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SubCategory that = (SubCategory) o;
        return id == that.id && categoryId == that.categoryId && Objects.equals(name, that.name) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoryId, userId);
    }
}
