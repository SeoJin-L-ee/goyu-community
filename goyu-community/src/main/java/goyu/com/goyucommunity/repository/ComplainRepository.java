package goyu.com.goyucommunity.repository;

import goyu.com.goyucommunity.domain.Complain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplainRepository extends JpaRepository<Complain, Long> {
}
