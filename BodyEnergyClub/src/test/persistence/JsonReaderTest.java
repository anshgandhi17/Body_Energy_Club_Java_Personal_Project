package persistence;

import model.Fruit;
import model.Milk;
import model.Protein;
import model.Smoothie;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static model.Fruit.BANANA;
import static model.Fruit.CHERRY;
import static model.Milk.OAT_MILK_SWEETENED;
import static model.Protein.WHEY_PROTEIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Smoothie smoothie = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySmoothieStore() {
        JsonReader reader = new JsonReader("./data/testFile.json");
        try {
            Smoothie smoothie = reader.read();
            assertEquals("Test smoothie", smoothie.getName());
            assertEquals(0, smoothie.getFruits().size());
            assertEquals(0,smoothie.getCalories());
            assertEquals(0,smoothie.getProteinContent());
            assertEquals(0,smoothie.getSugarContent());

        } catch (IOException e) {
            System.out.println(e);
            fail ("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSmoothieStore() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSmoothieStore.json");
        try {
            Smoothie smoothie = reader.read();
            assertEquals("My Testing Smoothie", smoothie.getName());

            List<Fruit> fruits = smoothie.getFruits();
            assertEquals(2, fruits.size());
            assertEquals(BANANA, fruits.get(0));
            assertEquals(CHERRY,fruits.get(1));

            Protein protein = smoothie.getProteinType();
            assertEquals(WHEY_PROTEIN, protein);

            Milk milk = smoothie.getMilkType();
            assertEquals(OAT_MILK_SWEETENED,milk);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
