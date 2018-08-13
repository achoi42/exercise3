package cloud.jetstream.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "productId", scope = Long.class)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long productId;

  private String productName;
  private String productDescription;

  // Assuming image provided as filepath/uri
  private String image;
  private double productPrice;

  @OneToMany
  @JoinColumn(name = "product_id")
  public List<OrderLineItem> orderLineItems;

  public Product(String productName, String productDescription, String image, double productPrice, List<OrderLineItem> orderLineItems) {
    this.productName = productName;
    this.productDescription = productDescription;
    this.image = image;
    this.productPrice = productPrice;
    this.orderLineItems = orderLineItems;
  }

  public Product() {
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getProductDescription() {
    return productDescription;
  }

  public void setProductDescription(String productDescription) {
    this.productDescription = productDescription;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public double getProductPrice() {
    return productPrice;
  }

  public void setProductPrice(double productPrice) {
    this.productPrice = productPrice;
  }

  public List<OrderLineItem> getOrderLineItems() {
    return orderLineItems;
  }

  public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
    this.orderLineItems = orderLineItems;
    if(this.orderLineItems == null) {
      this.orderLineItems = Collections.emptyList();
    }
  }

  public void addOrderLineItem(OrderLineItem orderLineItem) {
    if(this.orderLineItems == null) {
      this.orderLineItems = Collections.emptyList();
    }
    if(!this.orderLineItems.contains(orderLineItem)) {
      this.orderLineItems.add(orderLineItem);
      orderLineItem.setOliProduct(this);
    }
  }
  public void removeOrderLineItem(OrderLineItem orderLineItem) {
    if(this.orderLineItems != null) {
      this.orderLineItems.remove(orderLineItem);
    }
    orderLineItem.setOliProduct(null);
  }
}
