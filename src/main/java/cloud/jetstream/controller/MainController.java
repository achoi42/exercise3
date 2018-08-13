//package cloud.jetstream.controller;
//
//import cloud.jetstream.domain.Order;
//import cloud.jetstream.present.AccountOrder;
//import cloud.jetstream.repository.AccountRepository;
//import cloud.jetstream.repository.AddressRepository;
//import cloud.jetstream.repository.OrderLineItemRepository;
//import cloud.jetstream.repository.OrderRepository;
//import cloud.jetstream.repository.ProductRepository;
//import cloud.jetstream.repository.ShipmentRepository;
//import cloud.jetstream.service.OrderService;
//import java.util.List;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class MainController {
//
//  private AccountRepository accountRepository;
//  private AddressRepository addressRepository;
//  private OrderRepository orderRepository;
//  private OrderLineItemRepository oliRepository;
//  private ProductRepository productRepository;
//  private ShipmentRepository shipmentRepository;
//
//  private OrderService orderService;
//
//  public MainController(AccountRepository accountRepository,
//      AddressRepository addressRepository, OrderRepository orderRepository,
//      OrderLineItemRepository oliRepository,
//      ProductRepository productRepository,
//      ShipmentRepository shipmentRepository,
//      OrderService orderService) {
//    this.accountRepository = accountRepository;
//    this.addressRepository = addressRepository;
//    this.orderRepository = orderRepository;
//    this.oliRepository = oliRepository;
//    this.productRepository = productRepository;
//    this.shipmentRepository = shipmentRepository;
//    this.orderService = orderService;
//  }
//
//  public MainController() {
//  }
//
//  @GetMapping("/orders/present/findAccountOrders?accountId={id}")
//  public List<AccountOrder> presentAccountOrders(@PathVariable(name = "id") long id) {
//    List<Order> orders = orderRepository.findAccountOrders(id);
//    List<AccountOrder> presentOrders = orderService.present(orders);
//    return presentOrders;
//  }
//}
