package bestcommerce.merchant.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantJPARepository extends JpaRepository<MerchantEntity, Long> {
}
