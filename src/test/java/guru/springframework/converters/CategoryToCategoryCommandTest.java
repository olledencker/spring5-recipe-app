package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {
    public static final Long CAT_ID=1L;
    public static final  String DESCR="a descr";
    CategoryToCategoryCommand converter;
    @Before
    public void setUp() throws Exception {
        converter= new CategoryToCategoryCommand();
    }
    @Test
    public void isNullable(){
        assertNull(converter.convert(null));
    }
    @Test
    public void EmptyObjectTest(){
        assertNotNull(converter.convert(new Category()));
    }


    @Test
    public void convert() {
        //given
        Category cat = Category.builder().id(CAT_ID).description(DESCR).build();
        //when
        CategoryCommand catCom = converter.convert(cat);
        //then
        assertNotNull(catCom);
        assertEquals(catCom.getDescription(),DESCR);
        assertEquals(catCom.getId(),CAT_ID);

    }
}