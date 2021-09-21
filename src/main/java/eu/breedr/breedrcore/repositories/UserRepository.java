package eu.breedr.breedrcore.repositories;

import eu.breedr.breedrcore.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find a user by email
     *
     * @return the {@link User}
     */
    User findByEmail(String email);
}
