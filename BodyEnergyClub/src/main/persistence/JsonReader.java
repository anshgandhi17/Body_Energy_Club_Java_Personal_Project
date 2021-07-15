package persistence;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import exceptions.ZeroLengthName;
import model.Fruit;
import model.Milk;
import model.Protein;
import model.Smoothie;
import org.json.*;
// Taken from JsonSerializationDemo
// URL: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
// Represents a reader that reads workroom from JSON data stored in file

public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads smoothie from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Smoothie read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);

        return parseSmoothie(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: Smoothie fruit from JSON object and adds them to a list of fruits and returns it
    private Smoothie parseSmoothie(JSONObject jsonObject) {
        String name = jsonObject.getString("name");

        //Extracting Smoothie Components as JSONObjects
        JSONObject proteinObject = jsonObject.getJSONObject("protein");
        JSONArray fruitsArray = jsonObject.getJSONArray("fruits");
        JSONObject milkObject = jsonObject.getJSONObject("milk");

        //Rebuilding components as self-defined classes
        Milk milk = parseMilk(milkObject);
        List<Fruit> fruits = parseFruits(fruitsArray);
        Protein protein = parseProtein(proteinObject);

        Smoothie smoothie = null;
        try {
            smoothie = new Smoothie(name,fruits,milk,protein);
        } catch (ZeroLengthName zeroLengthName) {
            zeroLengthName.printStackTrace();
        }
        return smoothie;
    }

    private Protein parseProtein(JSONObject proteinObject) {
        String name = proteinObject.getString("name");
        int sugar = proteinObject.getInt("sugar content");
        int protein = proteinObject.getInt("protein content");
        int calories = proteinObject.getInt("calories");

        try {
            return new Protein(name,sugar,protein,calories,true);
        } catch (ZeroLengthName zeroLengthName) {
            zeroLengthName.printStackTrace();
        }

        return null;
    }


    // EFFECTS: parses milk from JSON object and returns it to calling function
    private Milk parseMilk(JSONObject milkObject) {
        String name = milkObject.getString("name");
        int sugar = milkObject.getInt("sugar content");
        int protein = milkObject.getInt("protein content");
        int calories = milkObject.getInt("calories");

        try {
            return new Milk(name,sugar, protein,calories,true,true);
        } catch (ZeroLengthName zeroLengthName) {
            zeroLengthName.printStackTrace();
        }
        return null;
    }

    // EFFECTS: parses list of Fruits from JSONArray and returns it to calling function
    private List<Fruit> parseFruits(JSONArray fruitsArray) {
        List<Fruit> fruits = new ArrayList<>();
        for (Object fruit: fruitsArray) {
            JSONObject nextFruit = (JSONObject) fruit;
            fruits.add(parseFruit(nextFruit));
        }
        return fruits;
    }

    // EFFECTS: parses fruit from JSON object and returns it to calling function
    private Fruit parseFruit(JSONObject fruitObject) {
        String name = fruitObject.getString("name");
        int calories = fruitObject.getInt("calories");
        int protein  = fruitObject.getInt("protein content");
        int sugar = fruitObject.getInt("sugar content");

        Fruit fruit = null;
        try {
            fruit = new Fruit(name, sugar, protein, calories);
        } catch (ZeroLengthName zeroLengthName) {
            zeroLengthName.printStackTrace();
        }

        return fruit;

    }
}
