package bestcommerce.merchant.db;

import bestcommerce.merchant.model.Merchant;
import bestcommerce.merchant.usecase.support.MerchantRepo;
import org.springframework.stereotype.Component;

import java.util.Optional;

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

	@Override
	public Optional<MerchantEntity> findByEmail(String email) {
		return repository.findByEmail(email);
	}
}
