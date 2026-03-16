import lombok.RequiredArgsConstructor;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

//@RequiredArgsConstructor // nicht mehr gebraucht, da ich das Konstructor händisch geschrieben.
public class ShopService {

    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final IdService idService;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo, IdService idService){
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.idService = idService;
    }

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();

        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);

            if (productToOrder.isEmpty()) {
                throw new IllegalArgumentException("Produkt mit der Id " + productId + " existiert nicht!");
            }

            products.add(productToOrder.get());
        }

        Order newOrder = new Order(
                idService.generateId(),   // ← jetzt über IdService
                products,
                OrderStatus.PROCESSING,
                Instant.now()
        );

        return orderRepo.addOrder(newOrder);
    }
    //Programmierung: Bestellstatus mit stream
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepo.getOrders()
                .stream()
                .filter(order -> order.status() == status)
                .toList();
    }

    public Order updateOrder(String orderId, OrderStatus newStatus) {
        Order existingOrder = orderRepo.getOrderById(orderId);

        if (existingOrder == null) {
            throw new IllegalArgumentException("Order mit der Id " + orderId + " existiert nicht!");
        }

        Order updatedOrder = existingOrder.withStatus(newStatus);

        orderRepo.removeOrder(orderId);
        orderRepo.addOrder(updatedOrder);

        return updatedOrder;
    }

    public Map<OrderStatus, Order> getOldestOrderPerStatus() {
        Map<OrderStatus, Order> result = new HashMap<>();

        for (Order order : orderRepo.getOrders()) {
            OrderStatus status = order.status();

            if (!result.containsKey(status) ||
                    order.timestamp().isBefore(result.get(status).timestamp())) {

                result.put(status, order);
            }
        }

        return result;
    }


}





    /*
    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Product productToOrder = productRepo.getProductById(productId);
            if (productToOrder == null) {
                System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                return null;
            }
            products.add(productToOrder);
        }

        // Programmierung: Bestellstatus: Status jetzt mitgegeben werden
        Order newOrder = new Order(
                UUID.randomUUID().toString(),
                products,
                OrderStatus.PROCESSING
        );

        return orderRepo.addOrder(newOrder);
    }

    // Programmierung: Bestellstatus
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepo.getOrders()
                .stream()
                .filter(order -> order.status() == status)
                .toList();
    }
}

*/
