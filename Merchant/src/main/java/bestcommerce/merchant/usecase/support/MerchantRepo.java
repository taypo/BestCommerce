package bestcommerce.merchant.usecase.support;

import bestcommerce.merchant.db.MerchantEntity;
import bestcommerce.merchant.model.Merchant;

import java.util.Optional;

public interface MerchantRepo {
	Merchant save(MerchantEntity merchantEntity);
	Optional<MerchantEntity> findByEmail(String email);
}
