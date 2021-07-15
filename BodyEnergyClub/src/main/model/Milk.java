package model;
//Subclass of Food, adds two fields to represent if the milk is Vegan or not and if it is unsweetened or not

import exceptions.ZeroLengthName;
import org.json.JSONObject;
import persistence.Writable;

public class Milk extends Food implements Writable {

    public static Milk COW_MILK;
    public static Milk OAT_MILK_SWEETENED;
    public static Milk ALMOND_MILK_UNSWEETENED;
    public static Milk ALMOND_MILK_SWEETENED;
    public static Milk OAT_MILK_UNSWEETENED;

    static {
        try {
            COW_MILK = new Milk("Cow's Milk",12, 8,149,false,
                    false);
            ALMOND_MILK_SWEETENED = new Milk("Almond Milk Sweetened",5,1,60,
                    true,false);
            OAT_MILK_UNSWEETENED = new Milk("Oat Milk Unsweetened",0,2,
                    100,
                    true,true);
            OAT_MILK_SWEETENED = new Milk("Oat Milk Sweetened",4,3,120,
                    true,false);
            ALMOND_MILK_UNSWEETENED = new Milk("Almond Milk Unsweetened",0,1,30,
                    true,true);
        } catch (ZeroLengthName zeroLengthName) {
            zeroLengthName.printStackTrace();
        }
    }


    private final boolean isVegan;
    private final boolean isUnsweetened;

    //EFFECTS: construct object of type milk
    public Milk(String name,int sugarContent,int proteinContent,int calories,boolean isVegan,boolean isUnsweetened)
            throws ZeroLengthName {
        super(name,sugarContent,proteinContent,calories);
        this.isVegan = isVegan;
        this.isUnsweetened = isUnsweetened;

    }

    //getters
    public boolean isVegan() {
        return isVegan;
    }

    public boolean isUnsweetened() {
        return isUnsweetened;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("protein content",getProteinContent());
        json.put("sugar content",getSugarContent());
        json.put("calories",getCalories());
        json.put("vegan",isVegan());
        return json;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Milk milk = (Milk) obj;

        return milk.getName().equals(this.getName());
    }
}
