import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ShopServiceTest {

    @Test
    void addOrderTest() {
        // GIVEN
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderListRepo(); // oder OrderMapRepo, je nach deinem Projekt


        IdService idService = () -> "test-id"; // einfache Fake-ID für Tests
        ShopService shopService = new ShopService(productRepo, orderRepo, idService);

        // Produkt muss existieren, sonst wirft addOrder eine Exception
        productRepo.addProduct(new Product("1", "Apfel", 1.0));

        List<String> productIds = List.of("1");

        // WHEN
        Order actual = shopService.addOrder(productIds);

        // THEN
        // Wir prüfen NICHT die ganze Order, weil:
        // - id ist zufällig (UUID)
        // - timestamp ist unterschiedlich
        // → wir prüfen nur die Produkte und den Status

        assertEquals(1, actual.products().size());
        assertEquals(new Product("1", "Apfel", 1.0), actual.products().get(0));

        assertEquals(OrderStatus.PROCESSING, actual.status());
        assertNotNull(actual.id());
        assertNotNull(actual.timestamp());
    }
}
