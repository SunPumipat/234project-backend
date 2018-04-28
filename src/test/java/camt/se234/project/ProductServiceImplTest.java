package camt.se234.project;

import camt.se234.project.dao.ProductDao;
import camt.se234.project.entity.Product;
import camt.se234.project.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    ProductDao productDao;
    ProductServiceImpl productService;

    @Before
    public void setup(){
        productDao = mock(ProductDao.class);
        productService = new ProductServiceImpl();
        productService.setProductDao(productDao);
    }

    @Test
    public void getAllProductsTest(){
        List<Product> mockProducts = new ArrayList<>();
        mockProducts.add(new Product("coca-cola"));
        mockProducts.add(new Product("milk"));
        mockProducts.add(new Product("snack"));
        when(productService.getAllProducts()).thenReturn(mockProducts);
        assertThat(productService.getAllProducts(), hasItem(new Product("milk")));
        assertThat(productService.getAllProducts() ,hasItems(new Product("coca-cola"),
                new Product("milk")));
    }
}
