package br.com.ballon.domain.user;

import br.com.ballon.domain.expenses.Months;

public enum Profile {
    ADMIN("admin"),
    CONSUMER("consumer");

    private String profile;

    Profile(String profile) {
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    public static Profile fromString(String profile) {
        for (Profile p : Profile.values()) {
            if (p.getProfile().equalsIgnoreCase(profile)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Perfil inválido");
    }
}
