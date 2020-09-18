package bestcommerce.merchant.db;

import bestcommerce.merchant.model.Merchant;
import bestcommerce.merchant.usecase.MerchantRepo;
import org.springframework.stereotype.Component;

@Component
public class MerchantJPAAdapter implements MerchantRepo {
	private final MerchantJPARepository repository;

	public MerchantJPAAdapter(MerchantJPARepository repository) {
		this.repository = repository;
	}

	@Override
	public Merchant save(MerchantEntity merchantEntity) {
		return repository.save(merchantEntity).toMerchant();
	}
}
