package com.utcb.javaBackendStart.auth.repositories;

import com.utcb.javaBackendStart.auth.dtos.RoleDto;
import com.utcb.javaBackendStart.auth.entities.RoleEntity;
import com.utcb.javaBackendStart.constants.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleDto> findRoleEntityByName(RoleEnum name);
}
