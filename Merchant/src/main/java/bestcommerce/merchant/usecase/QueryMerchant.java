package bestcommerce.merchant.usecase;

import bestcommerce.merchant.api.QueryMerchantDTO;
import bestcommerce.merchant.model.Merchant;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class QueryMerchant {
	private final MerchantRepo merchantRepo;
	private final PasswordEncoder passwordEncoder;

	public QueryMerchant(MerchantRepo merchantRepo, PasswordEncoder passwordEncoder) {
		this.merchantRepo = merchantRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public Merchant query(QueryMerchantDTO merchantDTO) {
		Merchant merchant = merchantRepo.findByEmail(merchantDTO.email).orElseThrow(() -> new MerchantNotFoundException()).toMerchant();
		if (passwordEncoder.matches(merchantDTO.password, merchant.getEncryptedPassword()) == false)
			throw new InvalidPasswordException();
		return merchant;
	}
}
