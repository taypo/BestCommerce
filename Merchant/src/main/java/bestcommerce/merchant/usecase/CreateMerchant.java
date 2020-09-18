package bestcommerce.merchant.usecase;

import bestcommerce.merchant.api.CreateMerchantDTO;
import bestcommerce.merchant.db.MerchantEntity;
import bestcommerce.merchant.model.Merchant;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CreateMerchant {
	private final MerchantRepo merchantRepo;
	private final PasswordEncoder passwordEncoder;

	public CreateMerchant(MerchantRepo merchantRepo, PasswordEncoder passwordEncoder) {
		this.merchantRepo = merchantRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public Merchant create(CreateMerchantDTO merchantDTO) {
		if(merchantDTO.password.length() < 6)
			throw new PasswordTooShortException();
		if(merchantDTO.password.matches("^.*[^a-zA-Z0-9 ].*$"))
			throw new PasswordContainsInvalidCharsException();

		MerchantEntity merchantEntity = new MerchantEntity(
				merchantDTO.type,
				merchantDTO.name,
				merchantDTO.ownerName,
				merchantDTO.address,
				merchantDTO.phoneNumber,
				merchantDTO.email,
				passwordEncoder.encode(merchantDTO.password)
		);

		return merchantRepo.save(merchantEntity);
	}
}
