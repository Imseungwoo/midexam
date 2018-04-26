package kr.ac.jejunu;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductDaoTest {

    private ProductDao productDao;
    private ProductDao hallaDao;

    @Before
    public void setup() {
        productDao = new JejuDao();
        hallaDao = new HallaDao();

    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = productDao.get(id);
        assertThat(id, is(product.getId()));
        assertThat(title, is(product.getTitle()));
        assertThat(price, is(product.getPrice()));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Product product = new Product();

        product.setTitle("한라봉");
        product.setPrice(20000);

        Long id = productDao.insert(product);

        Product insertedProduct = productDao.get(id);
        assertThat(insertedProduct.getId(), is(id));
        assertThat(insertedProduct.getTitle(), is(product.getTitle()));
        assertThat(insertedProduct.getPrice(), is(product.getPrice()));
    }

    @Test
    public void hallaGet() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = hallaDao.get(id);
        assertThat(id, is(product.getId()));
        assertThat(title, is(product.getTitle()));
        assertThat(price, is(product.getPrice()));
    }

    @Test
    public void hallaAdd() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        product.setTitle("한라봉");
        product.setPrice(20000);

        Long id = hallaDao.insert(product);

        Product insertedProduct = hallaDao.get(id);
        assertThat(insertedProduct.getId(), is(id));
        assertThat(insertedProduct.getTitle(), is(product.getTitle()));
        assertThat(insertedProduct.getPrice(), is(product.getPrice()));
    }
}
