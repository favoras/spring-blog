package com.springblog.webblog.repository;

import com.springblog.webblog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(@Param("role") String roleName);

}
