package bestcommerce.merchant.usecase;

import bestcommerce.merchant.db.MerchantEntity;
import bestcommerce.merchant.model.Merchant;

public interface MerchantRepo {
	Merchant save(MerchantEntity merchantEntity);
}
