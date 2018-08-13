package cloud.jetstream.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "orders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "orderId", scope = Long.class)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long orderId;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account orderAccount;

  private int orderNumber;

  @Temporal(value = TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'kk:mm:ss")
  private Date orderDate;

  @ManyToOne
  @JoinColumn(name = "address_id")
  private Address shippingAddress;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "order_id")
  private List<OrderLineItem> orderLineItems;

  @Transient
  @JsonIgnore
  private int totalPrice;


  public Order(Account orderAccount, int orderNumber, Date orderDate,
      Address shippingAddress, List<OrderLineItem> orderLineItems, int totalPrice) {
    this.orderAccount = orderAccount;
    this.orderAccount.addOrder(this);
    this.orderNumber = orderNumber;
    this.orderDate = orderDate;
    this.shippingAddress = shippingAddress;
    this.shippingAddress.addIncomingOrder(this);
    this.orderLineItems = orderLineItems;
    this.totalPrice = totalPrice;
  }

  public Order() {
  }

  public long getOrderId() {
    return orderId;
  }

  public void setOrderId(long orderId) {
    this.orderId = orderId;
  }

  public Account getOrderAccount() {
    return orderAccount;
  }

  public void setOrderAccount(Account orderAccount) {
    this.orderAccount = orderAccount;
    if(this.orderAccount != null) {
      if(this.orderAccount.getAccountOrders() != null &&
          !this.orderAccount.getAccountOrders().contains(this)) {
        this.orderAccount.addOrder(this);
      }
    }
  }

  public int getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(int orderNumber) {
    this.orderNumber = orderNumber;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
    if(this.shippingAddress != null && !this.shippingAddress.getIncomingOrders().contains(this)) {
      this.shippingAddress.addIncomingOrder(this);
    }
  }

  public List<OrderLineItem> getOrderLineItems() {
    return orderLineItems;
  }

  public void setOrderLineItems(List<OrderLineItem> orderLineItems) {
    this.orderLineItems = orderLineItems;
    if(this.orderLineItems != null) {
      for(OrderLineItem oli : this.getOrderLineItems()) {
        oli.setLineItemOrder(this);
      }
    }
  }

  public void addOrderLineItem(OrderLineItem orderLineItem) {
    this.orderLineItems.add(orderLineItem);
    orderLineItem.setLineItemOrder(this);
  }

  public void removeOrderLineItem(OrderLineItem orderLineItem) {
    this.orderLineItems.remove(orderLineItem);
    orderLineItem.setLineItemOrder(null);
  }

  public int getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
  }

  private void addShippingAddressToAccount(Account account, Address shippingAddress) {
    if(account == null || shippingAddress == null) {
      return;
    }
      account.addAddress(shippingAddress);
  }
}
