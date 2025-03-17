package br.com.ballon.domain.user;

import br.com.ballon.domain.exception.BallonException;

import java.util.UUID;

public abstract class User {
    private final UUID id;
    private final Profile profile;
    private final String name;
    private final String email;
    private final String password;

   protected User(Builder<?> builder) {
        this.id = builder.id;
        this.profile = builder.profile;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Builder base
    public static class Builder<T extends Builder<T>> {
        private UUID id = generateId();
        private Profile profile;
        private String name;
        private String email;
        private String password;


        public T withProfile(Profile profile) {
            this.profile = profile;
            return self();
        }

        public T withName(String name) {
            this.name = name.toLowerCase();
            return self();
        }

        public T withEmail(String email) {
            this.email = email.toLowerCase();
            return self();
        }

        public T withPassword(String password) {
            this.password = password;
            return self();
        }


        protected T self() {
            return (T) this;
        }

        public User build() {
            validate();
            return new User(this) {};
        }

        protected void validate() {
            if (profile == null || name == null || email == null || password == null) {
                throw new BallonException("Invalid parameters: profile, name, email, and password must not be null");
            }
        }

        private UUID generateId(){
            return UUID.randomUUID();
        }
    }
}