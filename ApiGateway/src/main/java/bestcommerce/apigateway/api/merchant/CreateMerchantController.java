package bestcommerce.apigateway.api.merchant;

import bestcommerce.apigateway.config.MessagingConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateMerchantController {

	private final RabbitTemplate template;

	public CreateMerchantController(RabbitTemplate template) {
		this.template = template;
	}

	@PostMapping("/merchant")
	public CreateMerchantResponse createMerchant(@RequestBody CreateMerchantRequest request) {
		CreateMerchantResponse response = template.convertSendAndReceiveAsType(
				MessagingConfig.EXCHANGE_NAME,
				MessagingConfig.MERCHANT_KEY,
				request,
				new ParameterizedTypeReference<CreateMerchantResponse>() {
				});
		return response;
	}
}
