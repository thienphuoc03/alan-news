package com.htphuoc.alannewsserver.Repository;

import com.htphuoc.alannewsserver.Model.ERole;
import com.htphuoc.alannewsserver.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole roleName);
}
