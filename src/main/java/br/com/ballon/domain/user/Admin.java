package br.com.ballon.domain.user;


public class Admin extends User {

    private boolean isDeleted = false;

    protected Admin(AdminUserBuilder builder) {
        super(builder);
        this.isDeleted = builder.isDeleted;
    }


    public boolean isDeleted() {
        return isDeleted;
    }

    public static class AdminUserBuilder extends User.Builder<AdminUserBuilder> {
        private boolean isDeleted = false;

        public AdminUserBuilder withIsDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

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
