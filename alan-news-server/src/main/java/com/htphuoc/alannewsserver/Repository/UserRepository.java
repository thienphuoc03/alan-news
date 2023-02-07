package com.htphuoc.alannewsserver.Repository;

import com.htphuoc.alannewsserver.Exception.NotFoundException;
import com.htphuoc.alannewsserver.Model.User;
import com.htphuoc.alannewsserver.Security.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    default User getUser(UserDetailsImpl currentUser) {
        return getUserByUserName(currentUser.getUsername());
    }

    default User getUserByUserName(String username) {
        return findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Not found user with username:" + username));
    }
}
