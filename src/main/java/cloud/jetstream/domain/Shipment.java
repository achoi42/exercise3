package cloud.jetstream.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "shipments")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "shipmentId", scope = Long.class)
public class Shipment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long shipmentId;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account shipmentAccount;

  @ManyToOne
  @JoinColumn(name = "address_id")
  private Address shipmentAddress;

  @OneToMany
  @JoinColumn(name = "shipment_id")
  private List<OrderLineItem> shipmentOli;

  @Temporal(value = TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'kk:mm:ss")
  private Date shippedDate;

  @Temporal(value = TemporalType.TIMESTAMP)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'kk:mm:ss")
  private Date deliveryDate;

  public Shipment(Account shipmentAccount, Address shipmentAddress,
      List<OrderLineItem> shipmentOli, Date shippedDate, Date deliveryDate) {
    this.shipmentAccount = shipmentAccount;
    this.shipmentAddress = shipmentAddress;
    this.shipmentOli = shipmentOli;
    this.shippedDate = shippedDate;
    this.deliveryDate = deliveryDate;
  }

  public Shipment() {

  }

  public long getShipmentId() {
    return shipmentId;
  }

  public void setShipmentId(long shipmentId) {
    this.shipmentId = shipmentId;
  }

  public Account getShipmentAccount() {
    return shipmentAccount;
  }

  public void setShipmentAccount(Account shipmentAccount) {
    this.shipmentAccount = shipmentAccount;
  }

  public Address getShipmentAddress() {
    return shipmentAddress;
  }

  public void setShipmentAddress(Address shipmentAddress) {
    this.shipmentAddress = shipmentAddress;
  }

  public List<OrderLineItem> getShipmentOli() {
    return shipmentOli;
  }

  public void setShipmentOli(List<OrderLineItem> shipmentOli) {
    this.shipmentOli = shipmentOli;
    if(this.shipmentOli != null) {
      for(OrderLineItem oli : this.shipmentOli) {
        oli.setOliShipment(this);
      }
    }
  }

  public void addOrderLineItem(OrderLineItem oli) {
    this.shipmentOli.add(oli);
    oli.setOliShipment(this);
  }

  public void removeOrderLineItem(OrderLineItem oli) {
    this.shipmentOli.remove(oli);
    oli.setOliShipment(null);
  }
  public Date getShippedDate() {
    return shippedDate;
  }

  public void setShippedDate(Date shippedDate) {
    this.shippedDate = shippedDate;
  }

  public Date getDeliveryDate() {
    return deliveryDate;
  }

  public void setDeliveryDate(Date deliveryDate) {
    this.deliveryDate = deliveryDate;
  }
}
