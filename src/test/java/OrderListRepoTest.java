import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderListRepoTest {

    @Test
    void getOrders() {
        // GIVEN
        OrderListRepo repo = new OrderListRepo();

        Instant timestamp = Instant.now();
        Product product = new Product("1", "Apfel", 1.0);
        Order newOrder = new Order("1", List.of(product), OrderStatus.PROCESSING, timestamp);
        repo.addOrder(newOrder);

        // WHEN
        List<Order> actual = repo.getOrders();

        // THEN
        List<Order> expected = new ArrayList<>();
        Product product1 = new Product("1", "Apfel", 1.0);
        expected.add(new Order("1", List.of(product1), OrderStatus.PROCESSING, timestamp));

        assertEquals(expected, actual);
    }

    @Test
    void getOrderById() {
        // GIVEN
        OrderListRepo repo = new OrderListRepo();

        Instant timestamp = Instant.now();
        Product product = new Product("1", "Apfel", 1.0);
        Order newOrder = new Order("1", List.of(product), OrderStatus.PROCESSING, timestamp);
        repo.addOrder(newOrder);

        // WHEN
        Order actual = repo.getOrderById("1");

        // THEN
        Product product1 = new Product("1", "Apfel", 1.0);
        Order expected = new Order("1", List.of(product1), OrderStatus.PROCESSING, timestamp);

        assertEquals(expected, actual);
    }

    @Test
    void addOrder() {
        // GIVEN
        OrderListRepo repo = new OrderListRepo();

        Instant timestamp = Instant.now();
        Product product = new Product("1", "Apfel", 1.0);
        Order newOrder = new Order("1", List.of(product), OrderStatus.PROCESSING, timestamp);

        // WHEN
        Order actual = repo.addOrder(newOrder);

        // THEN
        Product product1 = new Product("1", "Apfel", 1.0);
        Order expected = new Order("1", List.of(product1), OrderStatus.PROCESSING, timestamp);

        assertEquals(expected, actual);
        assertEquals(expected, repo.getOrderById("1"));
    }
}
