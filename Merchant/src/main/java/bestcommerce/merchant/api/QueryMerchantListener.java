package bestcommerce.merchant.api;

import bestcommerce.merchant.model.Merchant;
import bestcommerce.merchant.usecase.QueryMerchant;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class QueryMerchantListener {
	private final QueryMerchant queryMerchant;

	public QueryMerchantListener(QueryMerchant queryMerchant) {
		this.queryMerchant = queryMerchant;
	}

	@RabbitListener(queues = "#{queryMerchantQueue.name}")
	public Merchant queryMerchant(QueryMerchantDTO queryMerchantDTO) {
		try {
			return queryMerchant.query(queryMerchantDTO);
		} catch (Exception e) {
			// TODO return an error type
			e.printStackTrace();
		}
		return null;
	}
}
