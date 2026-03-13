import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderMapRepo();
        IdService idService = new UUIDIdService();

        ShopService shopService = new ShopService(productRepo, orderRepo, idService);

        // add Produkte
        productRepo.addProduct(new Product("1", "Laptop", 999.99));
        productRepo.addProduct(new Product("2", "Maus", 19.99));
        productRepo.addProduct(new Product("3", "Tastatur", 49.99));

        System.out.println("=== PRODUKTE IM SHOP ===");
        productRepo.getProducts().forEach(System.out::println);
        System.out.println();

        // Bestellungen anlegen
        System.out.println("=== BESTELLUNGEN WERDEN ANGELEGT ===");
        Order order1 = shopService.addOrder(List.of("1", "2"));
        Order order2 = shopService.addOrder(List.of("3"));
        Order order3 = shopService.addOrder(List.of("1", "3", "2"));

        // Ausgabe der Bestellungen
        System.out.println("\n=== BESTELLUNGEN ERFOLGREICH ERSTELLT ===");
        printOrder(order1);
        printOrder(order2);
        printOrder(order3);

        System.out.println("\n=== ÄLTESTE BESTELLUNG PRO STATUS ===");

        Map<OrderStatus, Order> oldestOrders = shopService.getOldestOrderPerStatus();

        oldestOrders.forEach((status, order) -> {
            System.out.println("--------------------------------------------------");
            System.out.println("Status:        " + status);
            System.out.println("Order-ID:      " + order.id());
            System.out.println("Zeitstempel:   " + order.timestamp());
            System.out.println("Produkte:");
            order.products().forEach(p ->
                    System.out.println("  - " + p.name() + " (" + p.id() + "), Preis: " + p.price() + "€")
            );
        });

        System.out.println("\n=== PROGRAMM BEENDET ===");
    }

    private static void printOrder(Order order) {
        System.out.println("--------------------------------------------------");
        System.out.println("Order-ID:      " + order.id());
        System.out.println("Status:        " + order.status());
        System.out.println("Zeitstempel:   " + order.timestamp());
        System.out.println("Produkte:");
        order.products().forEach(p ->
                System.out.println("  - " + p.name() + " (" + p.id() + "), Preis: " + p.price() + "€")
        );
    }
}
