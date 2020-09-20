package bestcommerce.merchant.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantJPARepository extends JpaRepository<MerchantEntity, Long> {
	Optional<MerchantEntity> findByEmail(String email);
}
