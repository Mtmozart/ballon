package br.com.ballon.infra.user;

import br.com.ballon.domain.user.Profile;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity(name = "Admin")
@Table(name = "admins")
public class AdminEntity {

    @Id
    private UUID id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Profile typeUser = Profile.ADMIN;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP WITH TIME ZONE DEFAULT now()")
    private Instant createdAt = Instant.now();
    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = Instant.now();
        }
    }

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant updatedAt;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant deletedAt;

    public AdminEntity() {
    }

    public AdminEntity(UUID id, String fullName, String email, String password, Profile typeUser, boolean isDeleted, Instant createdAt, Instant updatedAt, Instant deletedAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.typeUser = typeUser;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Profile getTypeUser() {
        return typeUser;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
