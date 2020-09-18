package bestcommerce.merchant.usecase;

import bestcommerce.merchant.api.CreateMerchantDTO;
import bestcommerce.merchant.config.BeanConfig;
import bestcommerce.merchant.db.MerchantEntity;
import bestcommerce.merchant.model.Merchant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CreateMerchantTest {

	private PasswordEncoder passwordEncoder;

	@BeforeEach
	public void setUp() {
		passwordEncoder = new BeanConfig().passwordEncoder();
	}

	@Test
	void testPasswordIsEncrypted() {
		MerchantRepo mockRepo = Mockito.mock(MerchantRepo.class);
		when(mockRepo.save(any(MerchantEntity.class))).thenAnswer((Answer<Merchant>) invocationOnMock -> {
			MerchantEntity e = invocationOnMock.getArgument(0);
			e.setId(1L);
			return e.toMerchant();
		});
		CreateMerchant createMerchant = new CreateMerchant(mockRepo, passwordEncoder);
		CreateMerchantDTO merchantDTO = new CreateMerchantDTO();
		merchantDTO.password = "password";

		Merchant merchant = createMerchant.create(merchantDTO);

		assertTrue(passwordEncoder.matches(merchantDTO.password, merchant.getEncryptedPassword()));
	}

	@Test
	void testPasswordLengthIsVerified() {
		CreateMerchant createMerchant = new CreateMerchant(Mockito.mock(MerchantRepo.class), passwordEncoder);
		CreateMerchantDTO merchantDTO = new CreateMerchantDTO();
		merchantDTO.password = "pwd";

		assertThrows(PasswordTooShortException.class, () -> createMerchant.create(merchantDTO));

	}

	@Test
	void testPasswordContainsAlphaNumericChars() {
		CreateMerchant createMerchant = new CreateMerchant(Mockito.mock(MerchantRepo.class), passwordEncoder);
		CreateMerchantDTO merchantDTO = new CreateMerchantDTO();
		merchantDTO.password = "password!";

		assertThrows(PasswordContainsInvalidCharsException.class, () -> createMerchant.create(merchantDTO));
	}
}