package camt.se234.project;

import camt.se234.project.dao.ProductDao;
import camt.se234.project.entity.Product;
import camt.se234.project.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    ProductDao productDao;
    ProductServiceImpl productService;

    @Before
    public void setup() {
        productDao = mock(ProductDao.class);
        productService = new ProductServiceImpl();
        productService.setProductDao(productDao);
    }

    @Test
    public void getAllProductsTest() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product("coca-cola"));
        mockProducts.add(new Product("milk"));
        mockProducts.add(new Product("snack"));
        when(productDao.getProducts()).thenReturn(mockProducts);
        assertThat(productService.getAllProducts(), hasItem(new Product("milk")));
        assertThat(productService.getAllProducts(), hasItems(new Product("coca-cola"),
                new Product("milk")));
    }

    @Test
    public void getAvailableProductsTest() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product("milk", 20));
        mockProducts.add(new Product("coke", 15));
        mockProducts.add(new Product("water", 7));
        mockProducts.add(new Product("sperm", -5));
        when(productDao.getProducts()).thenReturn(mockProducts);
        assertThat(productService.getAvailableProducts(), hasItem(new Product("milk", 20)));
        assertThat(productService.getAvailableProducts(), is(not(new Product("sperm", -5))));
    }

    @Test
    public void getUnavailableProductSizeTest() {
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product("milk", 20));
        mockProducts.add(new Product("coke", 15));
        mockProducts.add(new Product("water", 7));
        mockProducts.add(new Product("fanta", 18));
        mockProducts.add(new Product("sperm", -5));
        when(productDao.getProducts()).thenReturn(mockProducts);
        assertThat(productService.getUnavailableProductSize(), is(1));
    }
}
