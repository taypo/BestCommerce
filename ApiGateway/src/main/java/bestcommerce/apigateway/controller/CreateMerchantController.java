package bestcommerce.apigateway.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateMerchantController {

	@PostMapping
	public void createMerchant(@RequestBody CreateMerchantRequest request) {

	}
}
