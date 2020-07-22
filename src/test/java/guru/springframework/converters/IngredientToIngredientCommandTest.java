package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {
    IngredientToIngredientCommand converter;
    public static final Long INGRCOM_ID = 1L;
    public static final Long UOMCOM_ID=2L;
    public static final String DESCRCOM ="a descr";
    public static final BigDecimal INGrCOM_AMOUNT= new BigDecimal(0.23);
    @Before
    public void setUp() throws Exception {
        converter= new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }
    @Test
    public void isNullable(){
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObject(){
        assertNotNull(converter.convert(new Ingredient()));

    }
    @Test
    public void convert() {
        Ingredient ingredient = Ingredient.builder()
                                    .id(INGRCOM_ID)
                                    .amount(INGrCOM_AMOUNT)
                                    .description(DESCRCOM)
                                    .uom(UnitOfMeasure.builder().id(UOMCOM_ID).build()).build();

        IngredientCommand inCom = converter.convert(ingredient);

        assertNotNull(inCom);
        assertEquals(inCom.getId(),INGRCOM_ID);
        assertEquals(inCom.getAmount(),INGrCOM_AMOUNT);
        assertEquals(inCom.getDescription(),DESCRCOM);
        assertEquals(inCom.getUom().getId(),UOMCOM_ID);
    }
}