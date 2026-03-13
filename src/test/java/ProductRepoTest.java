import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @Test
    void getProducts() {
        // GIVEN
        ProductRepo repo = new ProductRepo();

        // WHEN
        List<Product> actual = repo.getProducts();

        // THEN
        List<Product> expected = new ArrayList<>();
        expected.add(new Product("1", "Apfel", 1.0));

        assertEquals(expected, actual);
    }

    @Test
    void getProductById() {
        // GIVEN
        ProductRepo repo = new ProductRepo();

        // WHEN
        Optional<Product> actual = repo.getProductById("1");

        // THEN
        Product expected = new Product("1", "Apfel", 1.0);

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void addProduct() {
        // GIVEN
        ProductRepo repo = new ProductRepo();
        Product newProduct = new Product("2", "Banane", 2.2);

        // WHEN
        Product actual = repo.addProduct(newProduct);

        // THEN
        Product expected = new Product("2", "Banane", 2.2);

        assertEquals(expected, actual);

        Optional<Product> fromRepo = repo.getProductById("2");
        assertTrue(fromRepo.isPresent());
        assertEquals(expected, fromRepo.get());
    }

    @Test
    void removeProduct() {
        // GIVEN
        ProductRepo repo = new ProductRepo();

        // WHEN
        repo.removeProduct("1");

        // THEN
        Optional<Product> result = repo.getProductById("1");
        assertTrue(result.isEmpty());
    }
}