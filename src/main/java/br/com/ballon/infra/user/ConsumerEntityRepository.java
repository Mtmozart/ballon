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
public interface ConsumerEntityRepository extends JpaRepository<ConsumerEntity, UUID> {

    @Modifying
    @Query("UPDATE Consumer c SET c.fullName = :fullName, c.email = :email, c.updatedAt = :updatedAt WHERE c.id = :id")
    Optional<Integer> updatesEntity(String fullName, String email, UUID id, Instant updatedAt);

    @Query("SELECT c FROM Consumer c WHERE c.email = :email")
    Optional<ConsumerEntity> findByEmailToValidate(String email);

    Optional<UserDetails> findByEmail(String email);



}
