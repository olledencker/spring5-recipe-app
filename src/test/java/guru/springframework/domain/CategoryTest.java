package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


public class CategoryTest {

    Category category;

    @Before
    public void setUpTest() {
        category = new Category();
    }

    @Test
    public void getId() {
        Long id = 1L;
        category.setId(id);
        assertEquals(id,category.getId());

    }
}