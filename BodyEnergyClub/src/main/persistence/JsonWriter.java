package persistence;

import model.Fruit;
import model.Milk;
import model.Protein;
import model.Smoothie;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private final String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of fruit to file
    public void writeFruit(Fruit f) {
        JSONObject json = f.toJson();
        saveToFile(json.toString(TAB));
    }

    public void writeMilk(Milk m) {
        JSONObject json = m.toJson();
        saveToFile(json.toString(TAB));
    }

    public void writeProtein(Protein p) {
        JSONObject json = p.toJson();
        saveToFile(json.toString(TAB));
    }

    public void writeSmoothie(Smoothie s) {
        JSONObject json = s.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
