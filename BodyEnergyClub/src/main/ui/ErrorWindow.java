package ui;

import javax.swing.*;
import java.awt.*;

import static ui.SmoothieStoreApp.*;

//Error Message Window Class
public class ErrorWindow extends JFrame {

    //Panel Fields
    JPanel centerPanel;

    //Label Field
    JLabel errorLabel;

    // MODIFIES: this
    // EFFECTS: shows error window
    public ErrorWindow() {
        initializeGraphics();
        initializeLabel();
        addLabel();
    }

    // MODIFIES: errorLabel
    // EFFECTS: initializes errorLabel
    private void initializeLabel() {
        errorLabel = new JLabel("Please enter a name longer than zero characters");
        errorLabel.setFont(new Font("Arial",Font.BOLD,30));
    }

    //EFFECTS: adds errorLabel to ErrorWindow
    private void addLabel() {
        add(errorLabel);
    }

    //MODIFIES:this
    //EFFECTS: sets up graphics for error window
    private void initializeGraphics() {
        setSize(FRAME_WIDTH / 2, FRAME_HEIGHT / 5);
        setVisible(true);
        setBackground(Color.GREEN);

    }


}
