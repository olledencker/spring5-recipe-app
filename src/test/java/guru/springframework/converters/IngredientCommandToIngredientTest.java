package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {
    public static final Long INGR_ID = 1L;
    public static final Long UOM_ID=2L;
    public static final String DESCR ="a descr";
    public static final BigDecimal INGr_AMOUNT= new BigDecimal(0.23);
    IngredientCommandToIngredient converter;
    @Before
    public void setUp() throws Exception {
        converter= new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());


    }

    @Test
    public void isNullable(){
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObject(){
        assertNotNull(converter.convert(new IngredientCommand()));

    }
    @Test
    public void convert() {
        IngredientCommand inCom = IngredientCommand.builder()
                .id(INGR_ID)
                .amount(INGr_AMOUNT)
                .description(DESCR)
                .uom(UnitOfMeasureCommand.builder().id(UOM_ID).build()).build();

        Ingredient ingr= converter.convert(inCom);

        assertNotNull(ingr);
        assertEquals(ingr.getId(),INGR_ID);
        assertEquals(ingr.getDescription(), DESCR);
        assertEquals(ingr.getUom().getId(), UOM_ID);

    }
}