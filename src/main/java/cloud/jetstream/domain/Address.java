package cloud.jetstream.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "addressId", scope = Long.class)
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long addressId;

  private String streetName;
  private int buildingNum;
  private String city;
  private String state;
  private int zipCode;
  private String country;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  @OneToMany
  @JoinColumn(name = "address_id")
  private List<Order> incomingOrders;

  @OneToMany
  @JoinColumn(name = "address_id")
  private List<Shipment> incomingShipments;

  public Address(String streetName, int buildingNum, String city, String state, int zipCode,
      String country, Account account, List<Order> incomingOrders, List<Shipment> incomingShipments) {
    this.streetName = streetName;
    this.buildingNum = buildingNum;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.country = country;
    this.account = account;
    this.incomingOrders = incomingOrders;
    this.incomingShipments = incomingShipments;
  }


//  public Address(String streetName, int buildingNum, String city, String state, int zipCode,
//      String country, Account account) {
//    this.streetName = streetName;
//    this.buildingNum = buildingNum;
//    this.city = city;
//    this.state = state;
//    this.zipCode = zipCode;
//    this.country = country;
//    this.account = account;
//  }

  public Address() {

  }

  public long getAddressId() {
    return addressId;
  }

  public void setAddressId(long addressId) {
    this.addressId = addressId;
  }

  public String getStreetName() {
    return streetName;
  }

  public void setStreetName(String streetName) {
    this.streetName = streetName;
  }

  public int getBuildingNum() {
    return buildingNum;
  }

  public void setBuildingNum(int buildingNum) {
    this.buildingNum = buildingNum;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public int getZipCode() {
    return zipCode;
  }

  public void setZipCode(int zipCode) {
    this.zipCode = zipCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
    if(this.account != null && !this.account.getAccountAddress().contains(this)) {
      this.account.addAddress(this);
    }
  }

  public List<Order> getIncomingOrders() {
    return incomingOrders;
  }

  public void setIncomingOrders(List<Order> incomingOrders) {
    this.incomingOrders = incomingOrders;
    if(this.incomingOrders != null) {
      for(Order o: this.incomingOrders) {
        o.setShippingAddress(this);
      }
    }
  }

  public void addIncomingOrder(Order order) {
    this.incomingOrders.add(order);
    order.setShippingAddress(this);
  }

  public void removeIncomingOrder(Order order) {
    this.incomingOrders.remove(order);
    order.setShippingAddress(null);
  }

  public List<Shipment> getIncomingShipments() {
    return incomingShipments;
  }

  public void setIncomingShipments(List<Shipment> incomingShipments) {
    this.incomingShipments = incomingShipments;
    if(this.incomingShipments != null) {
      for(Shipment s : this.incomingShipments) {
        s.setShipmentAddress(this);
      }
    }
  }

  public void addIncomingShipment(Shipment shipment) {
    this.incomingShipments.add(shipment);
    shipment.setShipmentAddress(this);
  }

  public void removeIncomingShipment(Shipment shipment) {
    this.incomingShipments.remove(shipment);
    shipment.setShipmentAddress(null);
  }
}
