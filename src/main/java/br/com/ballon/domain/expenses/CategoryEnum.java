package br.com.ballon.domain.expenses;

public enum CategoryEnum {
    FIXED_COSTS("fixed_costs"),
    COMFORT("comfort"),
    GOALS("goals"),
    KNOWLEDGE("knowledge"),
    PLEASURES("pleasures"),
    FINANCIAL_FREEDOM("financial_freedom");

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
