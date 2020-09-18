package bestcommerce.merchant.usecase;

import bestcommerce.merchant.api.CreateMerchantDTO;
import bestcommerce.merchant.model.Merchant;

public class CreateMerchant {
	private final MerchantRepo merchantRepo;

	public CreateMerchant(MerchantRepo merchantRepo) {
		this.merchantRepo = merchantRepo;
	}

	public Merchant create(CreateMerchantDTO merchantDTO) {

		return null;
	}
}
