package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.exception.NotFoundException;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Book book1 = new Book(1, "book1", 100, "author1", 400, 2021);
    private Book book2 = new Book(2, "book2", 90, "author2", 300, 2022);
    private TShirt tShirt1 = new TShirt(3, "tshirt1", 1000, "red","M");
    private TShirt tShirt2 = new TShirt(4, "tshirt2", 2000, "blue", "S");

    @BeforeEach
    void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(tShirt1);
        manager.add(tShirt2);
    }

    @Test
    public void shouldRemoveByCorrectId() {
        repository.removeById(3);
        Product[] expected = new Product[]{tShirt2, book2, book1};
        Product[] actual = manager.getAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoveByIncorrectId() {
        assertThrows(NotFoundException.class, () -> repository.removeById(7));
    }
}