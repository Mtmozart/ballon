package br.com.ballon.domain.expenses;

import br.com.ballon.domain.user.Profile;

public enum CategoryEnum {
    ESSENTIALS("essentials"),
    LEISURES("leisures"),
    DISCRETIONARY("discretionary"),
    INVESTMENTS("investments");

    private String category;

    CategoryEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public static CategoryEnum fromString(String category) {
        for (CategoryEnum c : CategoryEnum.values()) {
            if (c.getCategory().equalsIgnoreCase(category)) {
                return c;
            }
        }
        throw new IllegalArgumentException("Categoria inválida.");
    }

}
