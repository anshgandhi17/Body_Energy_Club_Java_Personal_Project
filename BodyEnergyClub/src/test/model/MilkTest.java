package model;

import exceptions.ZeroLengthName;
import org.junit.jupiter.api.Test;

import static model.Milk.COW_MILK;
import static org.junit.jupiter.api.Assertions.*;

public class MilkTest {

    @Test
    public void testMilkConstructor(){
        assertEquals("Cow's Milk",COW_MILK.getName());
        assertEquals(12,COW_MILK.getSugarContent());
        assertEquals(8,COW_MILK.getProteinContent());
        assertEquals(149,COW_MILK.getCalories());
        assertFalse(COW_MILK.isVegan());
        assertFalse(COW_MILK.isUnsweetened());
    }

    @Test
    public void testMilkConstructorErrorExpected() {
        Milk failMilk = null;

        try {
            failMilk= new Milk("",0,0,0,false,false);
        } catch (ZeroLengthName e) {
            //Expected
        }
    }

    @Test
    public void testProteinConstructorErrorNotExpected() {
        Milk passMilk = null;

        try {
            passMilk= new Milk("pass",0,0,0,false,false);
        } catch (ZeroLengthName e) {
            fail();
        }

        assertEquals("pass",passMilk.getName());
        assertEquals(0,passMilk.getSugarContent());
        assertEquals(0,passMilk.getProteinContent());
        assertEquals(0,passMilk.getCalories());
        assertFalse(passMilk.isVegan());
        assertFalse(passMilk.isUnsweetened());
    }
}
