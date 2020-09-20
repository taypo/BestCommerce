package bestcommerce.merchant.api;

import bestcommerce.merchant.model.Merchant;
import bestcommerce.merchant.usecase.CreateMerchant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CreateMerchantListener {

	private final CreateMerchant createMerchant;

	public CreateMerchantListener(CreateMerchant createMerchant) {
		this.createMerchant = createMerchant;
	}

	@RabbitListener(queues = "#{queue.name}")
	public Merchant createMerchant(CreateMerchantDTO createMerchantDTO) {
		try {
			return createMerchant.create(createMerchantDTO);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}