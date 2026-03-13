import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.With;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();

        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            //Programmierung: Ausnahmen (Exceptions)
            if (productToOrder.isEmpty()) {
                throw new IllegalArgumentException("Produkt mit der Id " + productId + " existiert nicht!");
            }
            products.add(productToOrder.get());
        }
        // Programmierung: Bestellstatus: Status jetzt mitgegeben werden
        Order newOrder = new Order(
                UUID.randomUUID().toString(),
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
    //Order anhand der ID holt
    public Order updateOrder(String orderId, OrderStatus newStatus) {
        Order existingOrder = orderRepo.getOrderById(orderId);
        //Exception wirft, wenn sie nicht existiert
        if (existingOrder == null) {
            throw new IllegalArgumentException("Order mit der Id " + orderId + " existiert nicht!");
        }

        // Neue Order mit geändertem Status erzeugen (Record = immutable)
        Order updatedOrder = existingOrder.withStatus(newStatus);

        // Alte Order entfernen und neue ersetzen.
        orderRepo.removeOrder(orderId);
        orderRepo.addOrder(updatedOrder);

        return updatedOrder;
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
