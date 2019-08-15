package ml.denoah.jwt.core.role.repository;

import ml.denoah.jwt.core.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
