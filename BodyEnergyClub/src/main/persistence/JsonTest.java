package persistence;

import model.Fruit;
import model.Milk;
import model.Protein;
import model.Smoothie;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Class used for Json Testing
public class JsonTest {
    protected void checkSmoothie(String name, List<Fruit> fruits, Protein protein, Milk milk,Smoothie s) {

        assertEquals(name, s.getName());
        System.out.println(fruits.size());
        System.out.println(s.getFruits().size());
        assertEquals(fruits.size(),s.getFruits().size());

        int i = 0;
        for (Fruit f: fruits) {
            assertTrue(f.equals(s.getFruits().get(i)));
            i++;
        }

        assertEquals(protein,s.getProteinType());
        assertEquals(milk,s.getMilkType());

    }

}
