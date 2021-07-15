package model;

import exceptions.ZeroLengthName;
import org.junit.jupiter.api.Test;

import static model.Fruit.*;
import static model.Milk.*;
import static model.Protein.VEGAN_PROTEIN;
import static model.Protein.WHEY_PROTEIN;
import static model.Smoothie.bananaSmoothieVegan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SmoothieTest {

    @Test
    public void testSmoothieConstructor(){
       assertEquals("Banana Special",bananaSmoothieVegan.getName());
       assertEquals(BANANA_LIST,bananaSmoothieVegan.getFruits());
       assertEquals(ALMOND_MILK_UNSWEETENED,bananaSmoothieVegan.getMilkType());
       assertEquals(VEGAN_PROTEIN, bananaSmoothieVegan.getProteinType());
       assertEquals(89+30+170, bananaSmoothieVegan.getCalories());
       assertEquals(12+2,bananaSmoothieVegan.getSugarContent());
       assertEquals(1+1+30,bananaSmoothieVegan.getProteinContent());

    }

    @Test
    public void testSmoothieCalorieContent(){
        Smoothie testSmoothie = null;
        try {
            testSmoothie = new Smoothie("random",BANANA_MANGO_LIST,COW_MILK,WHEY_PROTEIN);
        } catch (ZeroLengthName zeroLengthName) {
            zeroLengthName.printStackTrace();
        }
        assertEquals(BANANA.getCalories()+MANGO.getCalories()+COW_MILK.getCalories()+WHEY_PROTEIN.getCalories()
        ,testSmoothie.getCalories());
    }

    @Test
    public void testSmoothieProteinContent(){
        Smoothie testSmoothie = null;
        try {
            testSmoothie = new Smoothie("tester",CHERRY_LIST,OAT_MILK_SWEETENED,VEGAN_PROTEIN);
        } catch (ZeroLengthName zeroLengthName) {
            zeroLengthName.printStackTrace();
        }
        assertEquals(CHERRY.getProteinContent()+OAT_MILK_SWEETENED.getProteinContent()+
                VEGAN_PROTEIN.getProteinContent(),testSmoothie.getProteinContent());
    }

    @Test
    public void testSmoothieSugarContent(){
        Smoothie testSmoothie = null;
        try {
            testSmoothie = new Smoothie("testerSmoothie",BANANA_LIST,ALMOND_MILK_SWEETENED,WHEY_PROTEIN);
        } catch (ZeroLengthName zeroLengthName) {
            zeroLengthName.printStackTrace();
        }
        assertEquals(BANANA.getSugarContent()+ALMOND_MILK_SWEETENED.getSugarContent()+
                WHEY_PROTEIN.getSugarContent(),testSmoothie.getSugarContent());
    }

    @Test
    public void testSmoothieConstructorErrorNotExpected(){
        Smoothie testSmoothie = null;

        try {
            testSmoothie = new Smoothie("random",BANANA_LIST,OAT_MILK_SWEETENED,VEGAN_PROTEIN);
        } catch (ZeroLengthName e) {
            fail();
        }

        assertEquals("random",testSmoothie.getName());
        assertEquals(BANANA_LIST,testSmoothie.getFruits());
        assertEquals(OAT_MILK_SWEETENED,testSmoothie.getMilkType());
        assertEquals(VEGAN_PROTEIN, testSmoothie.getProteinType());
        assertEquals(BANANA.getCalories() + OAT_MILK_SWEETENED.getCalories() + VEGAN_PROTEIN.getCalories(),
                testSmoothie.getCalories());
        assertEquals(BANANA.getSugarContent() + OAT_MILK_SWEETENED.getSugarContent() +
                VEGAN_PROTEIN.getSugarContent(),testSmoothie.getSugarContent());
        assertEquals(BANANA.getProteinContent() + OAT_MILK_SWEETENED.getProteinContent() +
                VEGAN_PROTEIN.getProteinContent(),testSmoothie.getProteinContent());

    }

    @Test
    public void testSmoothieConstructorErrorExpected(){
        Smoothie testSmoothie = null;

        try {
            testSmoothie = new Smoothie("",BANANA_LIST,OAT_MILK_SWEETENED,VEGAN_PROTEIN);
        } catch (ZeroLengthName e) {
            //Expected
        }



    }


}
