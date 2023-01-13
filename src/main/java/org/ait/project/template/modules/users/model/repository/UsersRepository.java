package org.ait.project.template.modules.users.model.repository;

import org.ait.project.template.modules.users.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByName(String name);

    Optional<Users> findByEmail(String email);

    Boolean existsByName(String name);

    Boolean existsByEmail(String email);

    Users getByEmail(String email);
}
