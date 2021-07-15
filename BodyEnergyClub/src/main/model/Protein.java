package model;
//Subclass of Food, adds one field to show if Protein is vegan or not

import exceptions.ZeroLengthName;
import org.json.JSONObject;
import persistence.Writable;

// Class that represents a protein
public class Protein extends Food implements Writable {

    public static Protein VEGAN_PROTEIN;
    public static Protein WHEY_PROTEIN;

    static {
        try {
            VEGAN_PROTEIN = new Protein("Vegan Protein",2, 30,170,
                    true);
            WHEY_PROTEIN = new Protein("Whey Protein",1, 24,120,
                    false);
        } catch (ZeroLengthName zeroLengthName) {
            zeroLengthName.printStackTrace();
        }
    }



    private final boolean isVegan;

    //EFFECTS: instantiates an object of protein class
    public Protein(String name, int sugarContent, int proteinContent, int calories,boolean isVegan)
            throws ZeroLengthName {
        super(name,sugarContent,proteinContent,calories);
        this.isVegan = isVegan;
    }

    //getters
    public boolean isVegan() {
        return isVegan;
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
        Protein protein = (Protein) obj;

        return protein.getName().equals(this.getName());
    }
}
