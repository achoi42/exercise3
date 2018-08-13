package cloud.jetstream.repository;

import cloud.jetstream.domain.Order;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  @Query("SELECT o FROM Order o WHERE o.orderAccount.accountId = :accountId ORDER BY o.orderDate ASC")
  List<Order> findAccountOrders(@Param("accountId") long id);
}
