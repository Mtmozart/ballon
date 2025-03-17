package br.com.ballon.domain.expenses;

public enum Months {
    JANUARY("january"),
    FEBRUARY("february"),
    MARCH("march"),
    APRIL("april"),
    MAY("may"),
    JUNE("june"),
    JULY("july"),
    AUGUST("august"),
    SEPTEMBER("september"),
    OCTOBER("october"),
    NOVEMBER("november"),
    DECEMBER("december");

    private final String monthName;

    Months(String monthName) {
        this.monthName = monthName;
    }

    public String getMonthName() {
        return monthName;
    }

    public static Months fromString(String month) {
        for (Months m : Months.values()) {
            if (m.getMonthName().equalsIgnoreCase(month)) {
                return m;
            }
        }
        throw new IllegalArgumentException("Mês inválido: " + month);
    }
}
