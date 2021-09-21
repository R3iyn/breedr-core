package eu.breedr.breedrcore.repositories;

import eu.breedr.breedrcore.domain.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    UserRole findUserRoleByRole(String role);
}
