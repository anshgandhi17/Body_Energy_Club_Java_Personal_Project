package model;

import exceptions.ZeroLengthName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static model.Protein.VEGAN_PROTEIN;

public class ProteinTest {

    @Test
    public void testProteinConstructor(){
        assertEquals("Vegan Protein",VEGAN_PROTEIN.getName());
        assertEquals(2,VEGAN_PROTEIN.getSugarContent());
        assertEquals(30,VEGAN_PROTEIN.getProteinContent());
        assertEquals(170,VEGAN_PROTEIN.getCalories());
        assertTrue(VEGAN_PROTEIN.isVegan());

    }

    @Test
    public void testProteinConstructorErrorExpected() {
        Protein failProtein = null;

        try {
            failProtein= new Protein("",0,0,0,false);
        } catch (ZeroLengthName e) {
            //Expected
        }
    }

    @Test
    public void testProteinConstructorErrorNotExpected() {
        Protein passProtein = null;

        try {
            passProtein= new Protein("pass",0,0,0,false);
        } catch (ZeroLengthName e) {
            fail();
        }

        assertEquals("pass",passProtein.getName());
        assertEquals(0,passProtein.getSugarContent());
        assertEquals(0,passProtein.getProteinContent());
        assertEquals(0,passProtein.getCalories());
        assertFalse(passProtein.isVegan());
    }
}
