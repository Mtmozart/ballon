package br.com.ballon.infra.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdminEntityRepository extends JpaRepository<AdminEntity, UUID> {

    @Modifying
    @Query("UPDATE Admin a SET a.fullName = :fullName, a.email = :email, a.updatedAt = :updatedAt WHERE a.id = :id")
    Optional<Integer> updatesEntity(String fullName, String email, UUID id, Instant updatedAt);


    @Modifying
    @Query("UPDATE Admin a SET a.isDeleted = :isDeleted, a.deletedAt = :deletedAt WHERE a.id = :id")
    Optional<Integer> deleteAdmin(UUID id, boolean isDeleted, Instant deletedAt);

    Optional<UserDetails> findByEmail(String email);

}
