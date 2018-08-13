package cloud.jetstream.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Arrays;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "accountId", scope = Long.class)
public class Account {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long accountId;

  private String firstName;
  private String lastName;
  private String email;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id")
  private List<Address> accountAddress;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id")
  private List<Order> accountOrders;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "account_id")
  private List<Shipment> accountShipments;

  public Account(String firstName, String lastName, String email,
      List<Address> accountAddress, List<Order> accountOrders, List<Shipment> accountShipments) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.accountAddress = accountAddress;
    this.accountOrders = accountOrders;
    this.accountShipments = accountShipments;
  }

  public Account(){

  }

  public long getAccountId() {
    return accountId;
  }

  public void setAccountId(long accountId) {
    this.accountId = accountId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Address> getAccountAddress() {
    return accountAddress;
  }

  public void setAccountAddress(List<Address> accountAddress) {
    this.accountAddress = accountAddress;
    if(this.accountAddress != null) {
      for (Address a : this.accountAddress) {
        a.setAccount(this);
      }
    }
  }

  public void addAddress(Address address) {
    if(accountAddress == null) {
      accountAddress = Arrays.asList(address);
    }
    if(!accountAddress.contains(address)) {
      this.accountAddress.add(address);
    }
    address.setAccount(this);
  }

  public void removeAddress(Address address) {
    this.accountAddress.remove(address);
    address.setAccount(null);
  }

  //@OneToMany(cascade = CascadeType.ALL, mappedBy = "orderAccount")
  public List<Order> getAccountOrders() {
    return accountOrders;
  }

  public void setAccountOrders(List<Order> accountOrders) {
    this.accountOrders = accountOrders;
    if(this.accountOrders != null) {
      for (Order o: this.accountOrders) {
        o.setOrderAccount(this);
      }
    }
  }

  public void addOrder(Order order) {
    this.accountOrders.add(order);
    order.setOrderAccount(this);
  }

  public void removeOrder(Order order) {
    this.accountOrders.remove(order);
    order.setOrderAccount(null);
  }

  public List<Shipment> getAccountShipments() {
    return accountShipments;
  }

  public void setAccountShipments(List<Shipment> accountShipments) {
    this.accountShipments = accountShipments;
    if(this.accountShipments != null) {
      for(Shipment s: this.accountShipments) {
        s.setShipmentAccount(this);
      }
    }
  }

  public void addShipment(Shipment shipment) {
    this.accountShipments.add(shipment);
    shipment.setShipmentAccount(this);
  }

  public void removeShipment(Shipment shipment) {
    this.accountShipments.remove(shipment);
    shipment.setShipmentAccount(null);
  }
}