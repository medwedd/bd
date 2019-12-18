package ru.nikolaev.photogallery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikolaev.photogallery.model.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {}
