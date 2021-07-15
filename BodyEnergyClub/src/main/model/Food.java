package model;


import exceptions.ZeroLengthName;
import persistence.Writable;

// Represents any food item having name,sugar content,protein content(both in grams) and calories
public abstract class Food implements Writable {

    protected String name;
    protected int sugarContent;
    protected int proteinContent;
    protected int calories;

    /*
     * REQUIRES: sugarContent, proteinContent, calories >=0
     *           name to be non-zero length
     * EFFECTS:  name on Food is set to this.name;
     * protein content (in grams) is assigned to proteinContent
     * sugar content (in grams) is assigned to sugarContent
     * calories is assigned to calories
     */

    //Constructor for food class
    public Food(String name, int sugarContent, int proteinContent, int calories) throws ZeroLengthName {
        if (name.length() == 0) {
            throw new ZeroLengthName();
        }
        this.name = name;
        this.sugarContent = sugarContent;
        this.proteinContent = proteinContent;
        this.calories = calories;
    }

    //getters
    public String getName() {
        return name;
    }

    public int getSugarContent() {
        return sugarContent;
    }

    public int getProteinContent() {
        return proteinContent;
    }

    public int getCalories() {
        return calories;
    }


}
