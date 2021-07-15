package model;

import exceptions.ZeroLengthName;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.List;
//Subclass of food,represents a smoothie and  contains two additional fields (other than the ones in Food)
// to provide information about the fruits in the smoothie and the type of milk that has been used

public class Smoothie extends Food implements Writable {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    public static Smoothie bananaSmoothieVegan;
    public static Smoothie cherrySmoothieNotVegan;
    public static Smoothie bananaMangoSmoothieNotVegan;


    static {
        try {
            bananaSmoothieVegan = new Smoothie("Banana Special", Fruit.BANANA_LIST,
                    Milk.ALMOND_MILK_UNSWEETENED, Protein.VEGAN_PROTEIN);
            cherrySmoothieNotVegan = new Smoothie("Cherry Blast", Fruit.CHERRY_LIST, Milk.COW_MILK,
                    Protein.WHEY_PROTEIN);
            bananaMangoSmoothieNotVegan = new Smoothie("Banana-Mango Duo", Fruit.BANANA_MANGO_LIST,
                    Milk.OAT_MILK_SWEETENED, Protein.WHEY_PROTEIN);
        } catch (ZeroLengthName zeroLengthName) {
            zeroLengthName.printStackTrace();
        }
    }



    private final List<Fruit> fruits;
    private final Milk milkType;
    private final Protein proteinType;

    //EFFECTS: constructs object of type smoothie
    public Smoothie(String name, List<Fruit> fruits,Milk milkType, Protein proteinType) throws ZeroLengthName {

        super(name,smoothieSugarContent(fruits,milkType,proteinType),
                smoothieProteinContent(fruits,milkType,proteinType),
                smoothieCalorieContent(fruits,milkType,proteinType));
        this.fruits = fruits;
        this.milkType = milkType;
        this.proteinType = proteinType;

    }

    //EFFECTS: returns the sugar content of smoothie by adding sugar content of all ingredients
    private static int smoothieSugarContent(List<Fruit> fruitInSmthie,Milk milkInSmthie,Protein proteinInSmthie) {
        int sugarAmount = 0;

        for (Fruit f: fruitInSmthie)  {
            sugarAmount += f.getSugarContent();
        }

        sugarAmount += milkInSmthie.getSugarContent();
        sugarAmount += proteinInSmthie.getSugarContent();

        return sugarAmount;

    }

    //EFFECTS: returns the protein content of smoothie by adding protein content of all ingredients
    private static int smoothieProteinContent(List<Fruit> fruitsInSmthie, Milk milkInSmthie, Protein proteinInSmthie) {
        int proteinAmount = 0;

        for (Fruit f: fruitsInSmthie) {
            proteinAmount += f.getProteinContent();
        }

        proteinAmount += milkInSmthie.getProteinContent();
        proteinAmount += proteinInSmthie.getProteinContent();

        return proteinAmount;

    }

    //EFFECTS: returns the calories content of smoothie by adding calories content of all ingredients
    private static int smoothieCalorieContent(List<Fruit> fruitsInSmthie,Milk milkInSmthie,Protein proteinInSmthie) {
        int calorieAmount = 0;

        for (Fruit f: fruitsInSmthie) {
            calorieAmount += f.getCalories();
        }

        calorieAmount += milkInSmthie.getCalories();
        calorieAmount += proteinInSmthie.getCalories();

        return calorieAmount;

    }


    //getters
    public List<Fruit> getFruits() {
        return fruits;
    }

    public Milk getMilkType() {
        return milkType;
    }

    public Protein getProteinType() {
        return proteinType;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("protein",getProteinType().toJson());
        json.put("fruits",fruitsToJson());
        json.put("milk",getMilkType().toJson());
        json.put("protein content",getProteinContent());
        json.put("sugar content",getSugarContent());
        json.put("calories",getCalories());
        return json;
    }

    private JSONArray fruitsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Fruit f: fruits) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }

}
