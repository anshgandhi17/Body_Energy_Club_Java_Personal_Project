package model;


import exceptions.ZeroLengthName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.Fruit.*;
import static org.junit.jupiter.api.Assertions.*;

class FruitTest {

    @Test
    void testFruitConstructor() {
        assertEquals("mango", MANGO.getName());
        assertEquals(14,MANGO.getSugarContent());
        assertEquals(1,MANGO.getProteinContent());
        assertEquals(60,MANGO.getCalories());
    }

    @Test
    void testSingleFruitListMaker() {
        ArrayList<Fruit> singleFruitList = new ArrayList<>();
        singleFruitList = singleFruitListMaker(APPLE);
        assertEquals(1,singleFruitList.size());
    }

    @Test
    void testFruitListMaker() {
        ArrayList<Fruit> twoFruitList = new ArrayList<>();
        twoFruitList = twoFruitListMaker(MANGO,CHERRY);
        assertEquals(2,twoFruitList.size());
        assertEquals("mango",twoFruitList.get(0).getName());
        assertEquals("cherry",twoFruitList.get(1).getName());
    }

    @Test
    public void testProteinConstructorErrorExpected() {
        Fruit failFruit = null;

        try {
            failFruit= new Fruit("",0,0,0);
        } catch (ZeroLengthName e) {
            //Expected
        }
    }

    @Test
    public void testProteinConstructorErrorNotExpected() {
        Fruit passFruit = null;

        try {
            passFruit= new Fruit("pass",0,0,0);
        } catch (ZeroLengthName e) {
            fail();
        }

        assertEquals("pass",passFruit.getName());
        assertEquals(0,passFruit.getSugarContent());
        assertEquals(0,passFruit.getProteinContent());
        assertEquals(0,passFruit.getCalories());
    }
}