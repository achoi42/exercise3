package cloud.jetstream.repository;

import cloud.jetstream.domain.Shipment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

  @Query("SELECT s FROM Shipment s WHERE s.shipmentAccount.accountId = :accountId ORDER BY s.deliveryDate ASC")
  List<Shipment> findAccountShipments(@Param("accountId") long id);
}
