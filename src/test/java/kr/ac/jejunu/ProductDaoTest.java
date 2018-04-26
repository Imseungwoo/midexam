package kr.ac.jejunu;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;

public class ProductDaoTest {

    private ProductDao productDao;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
//        ApplicationContext applicationContext = new GenericXmlApplicationContext("classpath:daoFactory.xml");
        productDao = applicationContext.getBean("productDao", ProductDao.class);
    }

    @Test
    public void update() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Long id = insertProductTest(product);

        product.setId(id);
        product.setTitle("한라봉");
        product.setPrice(30000);
        productDao.update(product);

        Product updatedProduct = productDao.get(id);

        assertThat(updatedProduct.getId(), is(product.getId()));
        assertThat(updatedProduct.getTitle(), is(product.getTitle()));
        assertThat(updatedProduct.getPrice(), is(product.getPrice()));

    }

    private Long insertProductTest(Product product) throws SQLException, ClassNotFoundException {
        product.setTitle("한라봉");
        product.setPrice(20000);
        return productDao.insert(product);
    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        Long id = insertProductTest(product);

        productDao.delete(id);

        Product deletedProduct = productDao.get(id);
        assertThat(deletedProduct, nullValue());

    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        long id = 1;
        Product product = productDao.get(id);
        assertThat(product.getId(), is(id));
        assertThat(product.getTitle(), is("제주감귤"));
        assertThat(product.getPrice(), is(15000));
    }

    @Test
    public void add() throws SQLException, ClassNotFoundException {
        Product product = new Product();

        Long id = insertProductTest(product);

        Product insertedProduct = productDao.get(id);
        assertThat(insertedProduct.getId(), is(id));
    }
}
