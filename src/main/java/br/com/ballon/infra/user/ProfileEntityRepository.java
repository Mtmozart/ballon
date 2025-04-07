package br.com.ballon.infra.user;

import br.com.ballon.domain.user.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileEntityRepository extends JpaRepository<ProfileEntity, Long> {

    @Query("SELECT p FROM ProfileEntity p WHERE p.type = :type")
    Optional<ProfileEntity> findByType(Profile type);
}
