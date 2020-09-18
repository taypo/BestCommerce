package bestcommerce.merchant.usecase;

import bestcommerce.merchant.api.CreateMerchantDTO;
import bestcommerce.merchant.model.Merchant;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CreateMerchantTest {

	@Test
	void testPasswordIsEncrypted() {
		CreateMerchant createMerchant = new CreateMerchant(Mockito.mock(MerchantRepo.class));
		CreateMerchantDTO merchantDTO = new CreateMerchantDTO();
		merchantDTO.password = "password";

		Merchant merchant = createMerchant.create(merchantDTO);
		assertNotEquals(merchant.getEncryptedPassword(), merchantDTO.password);
	}

	@Test
	void testPasswordLengthIsVerified() {
		CreateMerchant createMerchant = new CreateMerchant(Mockito.mock(MerchantRepo.class));
		CreateMerchantDTO merchantDTO = new CreateMerchantDTO();
		merchantDTO.password = "pwd";

		assertThrows(PasswordTooShortException.class, () -> createMerchant.create(merchantDTO));

	}

	@Test
	void testPasswordContainsAlphaNumericChars() {
		CreateMerchant createMerchant = new CreateMerchant(Mockito.mock(MerchantRepo.class));
		CreateMerchantDTO merchantDTO = new CreateMerchantDTO();
		merchantDTO.password = "password!";

		assertThrows(PasswordContainsInvalidCharsException.class, () -> createMerchant.create(merchantDTO));
	}
}