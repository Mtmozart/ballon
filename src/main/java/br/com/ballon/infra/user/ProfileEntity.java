package br.com.ballon.infra.user;

import br.com.ballon.domain.user.Profile;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "profiles")
public class ProfileEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Profile type;

    public ProfileEntity() {
    }

    public ProfileEntity(Long id, Profile type) {
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public boolean isAdmin() {
        return this.type.equals(Profile.ADMIN);
    }


    public boolean isConsumer() {
        return this.type.equals(Profile.CONSUMER);
    }


    @Override
    public String getAuthority() {
        return type.name();
    }
}
