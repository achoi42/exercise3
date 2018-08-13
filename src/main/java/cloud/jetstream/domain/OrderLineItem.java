package cloud.jetstream.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "oliId", scope = Long.class)
public class OrderLineItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long oliId;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product oliProduct;

  private int oliQuantity;
  private double oliPrice;
  private double oliTotalPrice;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order lineItemOrder;

  @ManyToOne
  @JoinColumn(name = "shipment_id")
  private Shipment oliShipment;

  public OrderLineItem(Product oliProduct, int oliQuantity, double oliPrice, double oliTotalPrice,
      Order lineItemOrder, Shipment oliShipment) {
    this.oliProduct = oliProduct;
    this.oliQuantity = oliQuantity;
    this.oliPrice = oliPrice;
    this.oliTotalPrice = oliTotalPrice;
    this.lineItemOrder = lineItemOrder;
    this.oliShipment = oliShipment;
  }

  public OrderLineItem() {
  }

  public long getOliId() {
    return oliId;
  }

  public void setOliId(long oliId) {
    this.oliId = oliId;
  }

  public Product getOliProduct() {
    return oliProduct;
  }

  public void setOliProduct(Product oliProduct) {
    this.oliProduct = oliProduct;
    if(this.oliProduct!= null) {
      oliProduct.addOrderLineItem(this);
    }
  }

  public int getOliQuantity() {
    return oliQuantity;
  }

  public void setOliQuantity(int oliQuantity) {
    this.oliQuantity = oliQuantity;
  }

  public double getOliPrice() {
    return oliPrice;
  }

  public void setOliPrice(double oliPrice) {
    this.oliPrice = oliPrice;
  }

  public double getOliTotalPrice() {
    return oliTotalPrice;
  }

  public void setOliTotalPrice(double oliTotalPrice) {
    this.oliTotalPrice = oliTotalPrice;
  }

  public Shipment getOliShipment() {
    return oliShipment;
  }

  public void setOliShipment(Shipment oliShipment) {
    this.oliShipment = oliShipment;
    if(this.oliShipment != null) {
      if(this.oliShipment.getShipmentOli() != null &&
          !this.oliShipment.getShipmentOli().contains(this)) {
        this.oliShipment.addOrderLineItem(this);
      }
    }
  }

  public Order getLineItemOrder() {
    return lineItemOrder;
  }

  public void setLineItemOrder(Order lineItemOrder) {
    this.lineItemOrder = lineItemOrder;
    if(this.lineItemOrder.getOrderLineItems() != null) {
      if(this.lineItemOrder.getOrderLineItems() != null &&
          !this.lineItemOrder.getOrderLineItems().contains(this)) {
        this.lineItemOrder.addOrderLineItem(this);
      }
    }
  }
}