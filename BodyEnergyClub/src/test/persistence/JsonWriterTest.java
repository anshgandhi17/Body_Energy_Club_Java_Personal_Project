package persistence;


import exceptions.ZeroLengthName;
import model.Fruit;
import model.Smoothie;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static model.Fruit.*;
import static model.Milk.ALMOND_MILK_UNSWEETENED;
import static model.Milk.OAT_MILK_SWEETENED;
import static model.Protein.VEGAN_PROTEIN;
import static model.Protein.WHEY_PROTEIN;
import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Smoothie s = new Smoothie("Test smoothie",BANANA_MANGO_LIST,ALMOND_MILK_UNSWEETENED,VEGAN_PROTEIN);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        } catch (ZeroLengthName zeroLengthName) {
            zeroLengthName.printStackTrace();
        }
    }

    @Test
    void testWriterEmptySmoothieStore() {
        try {
            Smoothie s = new Smoothie("Test smoothie",BANANA_MANGO_LIST,ALMOND_MILK_UNSWEETENED,VEGAN_PROTEIN);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySmoothieStore.json");
            writer.open();
            writer.writeSmoothie(s);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySmoothieStore.json");
            Smoothie alt = reader.read();
            assertEquals(s.getName(), alt.getName());
            assertEquals(s.getCalories(),alt.getCalories());
            assertEquals(s.getSugarContent(),alt.getSugarContent());
            assertEquals(s.getProteinContent(),alt.getProteinContent());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (ZeroLengthName zeroLengthName) {
            zeroLengthName.printStackTrace();
        }
    }

    @Test
    void testWriterGeneralSmoothieStore() {
        try {
            List<Fruit> sampleFruits = new LinkedList<>();
            sampleFruits.add(BANANA);
            sampleFruits.add(CHERRY);

            Smoothie sampleSmoothie = new Smoothie("My Testing Smoothie",sampleFruits,OAT_MILK_SWEETENED,
                    WHEY_PROTEIN);


            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSmoothieStore.json");
            writer.open();
            writer.writeSmoothie(sampleSmoothie);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSmoothieStore.json");
            Smoothie altSmoothie = reader.read();

            checkSmoothie("My Testing Smoothie",sampleFruits,WHEY_PROTEIN,OAT_MILK_SWEETENED,altSmoothie);


        } catch (IOException e) {
            System.out.println(e);
            fail("Exception should not have been thrown");
        } catch (ZeroLengthName zeroLengthName) {
            zeroLengthName.printStackTrace();
        }
    }
}