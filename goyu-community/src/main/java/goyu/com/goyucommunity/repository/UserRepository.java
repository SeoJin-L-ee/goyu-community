package goyu.com.goyucommunity.repository;

import goyu.com.goyucommunity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
