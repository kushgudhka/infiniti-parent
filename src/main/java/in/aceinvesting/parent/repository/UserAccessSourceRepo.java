package in.aceinvesting.parent.repository;

import in.aceinvesting.parent.entity.UserAccessSourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Kush Gudhka
 */
public interface UserAccessSourceRepo extends JpaRepository<UserAccessSourceEntity, Long> {
    UserAccessSourceEntity findByPanNumber(String pan);
}