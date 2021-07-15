package model;

import exceptions.ZeroLengthName;
import org.json.JSONObject;
import persistence.Writable;


import java.util.ArrayList;

// Represents any food item having name,sugar content,protein content(both in grams) and calories
public class Fruit extends Food implements Writable {

    public static  Fruit APPLE;
    public static Fruit BANANA;
    public static Fruit CHERRY;
    public static Fruit MANGO;

    static {
        try {
            APPLE = new Fruit("apple",10,1,52);
            BANANA = new Fruit("banana",12,1,89);
            CHERRY = new Fruit("cherry",8,2,97);
            MANGO = new Fruit("mango",14,1,60);

        } catch (ZeroLengthName zeroLengthName) {
            zeroLengthName.printStackTrace();
        }
    }

    public static ArrayList<Fruit> BANANA_LIST = singleFruitListMaker(BANANA);
    public static ArrayList<Fruit> CHERRY_LIST = singleFruitListMaker(CHERRY);
    public static ArrayList<Fruit> BANANA_MANGO_LIST = twoFruitListMaker(BANANA,MANGO);

    /*
     * REQUIRES: sugarContent, proteinContent, calories >=0
     *           name to be non-zero length
     * EFFECTS:  name on Fruit is set to this.name;
     * protein content (in grams) is assigned to proteinContent
     * sugar content (in grams) is assigned to sugarContent
     * calories is assigned to calories
     */
    public Fruit(String name, int sugarContent, int proteinContent, int calories) throws ZeroLengthName {

        super(name,sugarContent,proteinContent,calories);

    }

    //EFFECTS: puts given fruit in a list of type Fruit and returns the list
    public static ArrayList<Fruit> singleFruitListMaker(Fruit f) {
        ArrayList<Fruit> fruitList = new ArrayList<>();
        fruitList.add(f);
        return fruitList;
    }

    //EFFECTS: puts given fruit in a list of type Fruit and returns the list
    public static ArrayList<Fruit> twoFruitListMaker(Fruit f,Fruit j) {
        ArrayList<Fruit> fruitList = new ArrayList<>();
        fruitList.add(f);
        fruitList.add(j);
        return fruitList;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("protein content",getProteinContent());
        json.put("sugar content",getSugarContent());
        json.put("calories",getCalories());
        return json;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Fruit fruit = (Fruit) obj;

        return fruit.getName().equals(this.getName());
    }
}
