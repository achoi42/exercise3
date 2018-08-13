package cloud.jetstream.config;

import cloud.jetstream.domain.Account;
import cloud.jetstream.domain.Address;
import cloud.jetstream.domain.Order;
import cloud.jetstream.domain.OrderLineItem;
import cloud.jetstream.domain.Product;
import cloud.jetstream.domain.Shipment;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(Account.class);
    config.exposeIdsFor(Address.class);
    config.exposeIdsFor(Order.class);
    config.exposeIdsFor(OrderLineItem.class);
    config.exposeIdsFor(Product.class);
    config.exposeIdsFor(Shipment.class);
  }
}
