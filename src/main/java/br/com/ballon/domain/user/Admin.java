package br.com.ballon.domain.user;


import java.util.UUID;

public class Admin extends User {


    protected Admin(Builder<?> builder) {
        super(builder);
    }

    public static class AdminUserBuilder extends User.Builder<AdminUserBuilder> {
        @Override
        public Admin build() {
            validate();
            return new Admin(this);
        }
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "id=" + getId() +
                ", profile=" + getProfile() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }

}
