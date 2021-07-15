package ui;

import exceptions.ZeroLengthName;
import model.Fruit;
import model.Milk;
import model.Protein;
import model.Smoothie;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static model.Fruit.*;
import static model.Milk.*;
import static model.Protein.VEGAN_PROTEIN;
import static model.Protein.WHEY_PROTEIN;
import static ui.SmoothieStoreApp.*;

// Class that shows custom smoothie menu window
public class CustomSmoothieMenu extends JFrame  implements ActionListener {



    ArrayList<String> ingredients = new ArrayList<>();
    ArrayList<String> fruits = new ArrayList<>();
    String milk;
    String protein;
    String enteredName;

    //Smoothie constructor fields
    Milk chosenMilk;
    Protein chosenProtein;
    ArrayList<Fruit> chosenFruits = new ArrayList<>();
    Smoothie customMadeSmoothie;

    //Panel Fields
    JPanel westPanel;
    JPanel eastPanel;
    JPanel eastTop;
    JPanel eastMiddle;
    JPanel eastBottom;

    //Button Fields
    JButton milkButton1;
    JButton milkButton2;
    JButton milkButton3;
    JButton fruitButton1;
    JButton fruitButton2;
    JButton fruitButton3;
    JButton fruitButton4;
    JButton stopFruitSelection;
    JButton proteinButton1;
    JButton proteinButton2;
    JButton submitNameButton;
    JButton buildSmoothieButton;
    JButton saveSmoothieButton;

    //Radio Button Fields
    JRadioButton milkRadioButton;
    JRadioButton fruitRadioButton;
    JRadioButton proteinRadioButton;

    ButtonGroup radioButtons;

    //Text Field
    JTextField smoothieName;
    JTextArea ingredientsTextArea;
    JTextArea subsetIngredientsTextArea;

    //Label Field
    JLabel showOnlyLabel;

    //Collection of Buttons Segments
    ArrayList<JButton> fruitButtons;
    ArrayList<JButton> milkButtons;
    ArrayList<JButton> proteinButtons;

    //JSON Fields
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private static final String JSON_STORE_UI = "./data/smoothiestoreui.json";

    //MODIFIES: this
    //EFFECTS:  initializes custom smoothie menu and displays it on screen
    public CustomSmoothieMenu() {

        jsonWriter = new JsonWriter(JSON_STORE_UI);
        jsonReader = new JsonReader(JSON_STORE_UI);

        initializeGraphics();
        initializePanels();
        initializeSubmitNameTextField();
        initializeButtons();
        initializeButtonCollections();
        //initializeSplitPane();



    }

    //MODIFIES: smoothieName
    //EFFECTS: initializes smoothie name text field
    private void initializeSubmitNameTextField() {
        smoothieName = new JTextField(20);


    }

    //MODIFIES: fruitButtons, milkButtons and proteinButtons
    //EFFECTS:  initializes button groups and adds members to it
    private void initializeButtonCollections() {
        fruitButtons = new ArrayList<>();
        milkButtons = new ArrayList<>();
        proteinButtons = new ArrayList<>();

        fruitButtons.add(fruitButton1);
        fruitButtons.add(fruitButton2);
        fruitButtons.add(fruitButton3);
        fruitButtons.add(fruitButton4);


        milkButtons.add(milkButton1);
        milkButtons.add(milkButton2);
        milkButtons.add(milkButton3);

        proteinButtons.add(proteinButton1);
        proteinButtons.add(proteinButton2);

    }


    //EFFECTS: makes method calls to functions which initializes all buttons in window
    private void initializeButtons() {

        initializeMilkButtons();
        initializeFruitButtons();
        initializeProteinButtons();
        initializeSubmitNameButton();
        initializeBuildSmoothieButton();
        initializeSaveSmoothieButton();
    }

    //MODIFIES: saveSmoothieButton and westPanel
    //EFFECTS: initializes saveSmoothieButton and adds it to westPanel
    private void initializeSaveSmoothieButton() {
        saveSmoothieButton = new JButton();
        saveSmoothieButton.addActionListener(this);
        saveSmoothieButton.setText("Save Smoothie");
        saveSmoothieButton.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 4));
        saveSmoothieButton.setFont(new Font("Arial",  Font.BOLD, 40));
        saveSmoothieButton.setEnabled(false);
        westPanel.add(saveSmoothieButton);
        westPanel.repaint();
        westPanel.revalidate();
    }

    //MODIFIES: buildSmoothieButton and westPanel
    //EFFECTS: initializes buildSmoothieButton and adds it to westPanel
    private void initializeBuildSmoothieButton() {
        buildSmoothieButton = new JButton();
        buildSmoothieButton.addActionListener(this);
        buildSmoothieButton.setText("Build(Blend) Smoothie");
        buildSmoothieButton.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 4));
        buildSmoothieButton.setFont(new Font("Arial",  Font.BOLD, 40));
        buildSmoothieButton.setEnabled(false);
        westPanel.add(buildSmoothieButton);
        westPanel.repaint();
        westPanel.revalidate();
    }

    //MODIFIES: submitNameButton and westPanel
    //EFFECTS: initializes submitNameButton and adds it to westPanel
    private void initializeSubmitNameButton() {
        submitNameButton = new JButton();
        submitNameButton.addActionListener(this);
        submitNameButton.setText("Submit Name");
        submitNameButton.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 4));
        submitNameButton.setFont(new Font("Arial",  Font.BOLD, 40));
        submitNameButton.setEnabled(false);
        westPanel.add(submitNameButton);
        smoothieName.setFont(new Font("Arial", Font.BOLD,35));
        westPanel.add(smoothieName);
        westPanel.repaint();
        westPanel.revalidate();
    }

    //MODIFIES: proteinButton1 and proteinButton2
    //EFFECTS: sets up all protein buttons
    private void initializeProteinButtons() {
        proteinButton1 = new JButton();
        proteinButton2 = new JButton();

        setupProteinButtons();

    }


    //MODIFIES:  fruitButton1, fruitButton2,fruitButton3 and fruitButton4
    //EFFECTS: sets up all fruit buttons
    private void initializeFruitButtons() {

        fruitButton1 = new JButton();
        fruitButton2 = new JButton();
        fruitButton3 = new JButton();
        fruitButton4 = new JButton();
        stopFruitSelection = new JButton();

        setupTwoFruitButtons();
        setupTwoMoreFruitButtons();
        setupStopFruitSelectionButton();
    }



    //MODIFIES: milkButton1, milkButton2, milkButton3
    //EFFECTS: sets up all milk buttons
    private void initializeMilkButtons() {
        milkButton1 = new JButton();
        milkButton2 = new JButton();
        milkButton3 = new JButton();


        setupMilkButtons();

    }

    //MODIFIES: fruitButton1, fruitButton 2 and westPanel
    //EFFECTS: fruitButton1 and fruitButton2 setup and added to panel
    private void setupTwoFruitButtons() {
        fruitButton1.addActionListener(this);
        fruitButton1.setText("Banana");
        fruitButton1.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 4));
        fruitButton1.setFont(new Font("Arial",  Font.BOLD, 40));
        fruitButton1.setEnabled(false);
        westPanel.add(fruitButton1);
        westPanel.repaint();
        westPanel.revalidate();

        fruitButton2.addActionListener(this);
        fruitButton2.setText("Mango");
        fruitButton2.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 4));
        fruitButton2.setFont(new Font("Arial",  Font.BOLD, 40));
        fruitButton2.setEnabled(false);
        westPanel.add(fruitButton2);
        westPanel.repaint();
        westPanel.revalidate();

    }

    //MODIFIES:fruitButton3, fruitButton4 and westPanel
    //EFFECTS: fruitButton3 and fruitButton4 setup and added to panel
    private void setupTwoMoreFruitButtons() {
        fruitButton3.addActionListener(this);
        fruitButton3.setText("Cherry");
        fruitButton3.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 4));
        fruitButton3.setFont(new Font("Arial",  Font.BOLD, 40));
        fruitButton3.setEnabled(false);
        westPanel.add(fruitButton3);
        westPanel.repaint();
        westPanel.revalidate();

        fruitButton4.addActionListener(this);
        fruitButton4.setText("Apple");
        fruitButton4.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 4));
        fruitButton4.setFont(new Font("Arial",  Font.BOLD, 40));
        fruitButton4.setEnabled(false);
        westPanel.add(fruitButton4);
        westPanel.repaint();
        westPanel.revalidate();
    }

    //MODIFIES: milkButtons and westPanel
    //EFFECTS: sets up milk buttons and adds them to westPanel
    private void setupMilkButtons() {
        milkButton1.addActionListener(this);
        milkButton1.setText("Cow Milk");
        milkButton1.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 4));
        milkButton1.setFont(new Font("Arial",  Font.BOLD, 40));
        westPanel.add(milkButton1);
        westPanel.repaint();
        westPanel.revalidate();

        milkButton2.addActionListener(this);
        milkButton2.setText("Oat Milk Sweetened");
        milkButton2.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 4));
        milkButton2.setFont(new Font("Arial",  Font.BOLD, 40));
        westPanel.add(milkButton2);
        westPanel.repaint();
        westPanel.revalidate();

        milkButton3.addActionListener(this);
        milkButton3.setText("Almond Milk Unsweetened");
        milkButton3.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 4));
        milkButton3.setFont(new Font("Arial",  Font.BOLD, 40));
        westPanel.add(milkButton3);
        westPanel.repaint();
        westPanel.revalidate();

    }

    //MODIFIES: stopFruitSelection and westPanel
    //EFFECTS: sets up stop fruit selection button and adds them to westPanel
    private void setupStopFruitSelectionButton() {
        stopFruitSelection.addActionListener(this);
        stopFruitSelection.setText("Stop Fruit Selection");
        stopFruitSelection.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 4));
        stopFruitSelection.setFont(new Font("Arial",  Font.BOLD, 40));
        stopFruitSelection.setEnabled(false);
        westPanel.add(stopFruitSelection);
        westPanel.repaint();
        westPanel.revalidate();
    }

    //MODIFIES: proteinButtons and westPanel
    //EFFECTS: sets up protein buttons and adds them to westPanel
    private void setupProteinButtons() {
        proteinButton1.addActionListener(this);
        proteinButton1.setText("Vegan Protein");
        proteinButton1.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 4));
        proteinButton1.setFont(new Font("Arial",  Font.BOLD, 40));
        proteinButton1.setEnabled(false);
        westPanel.add(proteinButton1);
        westPanel.repaint();
        westPanel.revalidate();

        proteinButton2.addActionListener(this);
        proteinButton2.setText("Whey Protein");
        proteinButton2.setPreferredSize(new Dimension(FRAME_WIDTH / 2, FRAME_HEIGHT / 4));
        proteinButton2.setFont(new Font("Arial",  Font.BOLD, 40));
        proteinButton2.setEnabled(false);
        westPanel.add(proteinButton2);
        westPanel.repaint();
        westPanel.revalidate();

    }

    //MODIFIES: this, westPanel and eastPanel
    //EFFECTS: initializes panels and adds them to screen
    private void initializePanels() {
        westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(14,1,40,30));
        westPanel.setBackground(Color.PINK);
        add(westPanel,BorderLayout.WEST);
        westPanel.setPreferredSize(new Dimension(FRAME_WIDTH / 2,FRAME_HEIGHT));


        eastPanel = new JPanel();
        eastPanel.setBackground(Color.ORANGE);
        add(eastPanel,BorderLayout.EAST);
        eastPanel.setPreferredSize(new Dimension(FRAME_WIDTH / 2,FRAME_HEIGHT));
        eastPanelSubPanelsSetup();
    }

    //MODIFIES:eastPanel, eastTop, eastBottom, eastMiddle
    //EFFECTS: initializes panels and adds them to screen
    private void eastPanelSubPanelsSetup() {
        eastTop = new JPanel();
        eastTop.setBackground(Color.GRAY);
        eastPanel.add(eastTop,BorderLayout.NORTH);
        eastTop.setPreferredSize(new Dimension(FRAME_WIDTH / 2,FRAME_HEIGHT / 3));

        eastMiddle = new JPanel();
        eastMiddle.setBackground(Color.black);
        eastPanel.add(eastMiddle,BorderLayout.CENTER);
        eastMiddle.setPreferredSize(new Dimension(FRAME_WIDTH / 2,FRAME_HEIGHT / 3));

        eastBottom = new JPanel();
        eastBottom.setBackground(Color.gray);
        eastBottom.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
        eastBottom.setLayout(new FlowLayout(FlowLayout.CENTER,45,10));
        eastPanel.add(eastBottom,BorderLayout.CENTER);
        eastBottom.setPreferredSize(new Dimension(FRAME_WIDTH / 2,FRAME_HEIGHT / 3));

        eastSubPanelComponentsSetup();
        eastBottomButtonSetup();
    }

    // Partially taken from :
    // https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing
    // /examples/layout/BoxLayoutDemoProject/src/layout/BoxLayoutDemo.java
    // MODIFIES: radioButtons and eastBottom
    // EFFECTS: sets up radio buttons and adds them to east bottom panel
    private void eastBottomButtonSetup() {
        radioButtons = new ButtonGroup();
        milkRadioButton = new JRadioButton("milk");
        proteinRadioButton = new JRadioButton("protein");
        fruitRadioButton = new JRadioButton("fruit");
        showOnlyLabel = new JLabel("SHOW ONLY SELECTED INGREDIENT:   ");

        setupMilkRadioButtonActionListener();
        setupProteinRadioButtonActionListener();
        setupFruitRadioButtonActionListener();


        radioButtons.add(milkRadioButton);
        radioButtons.add(proteinRadioButton);
        radioButtons.add(fruitRadioButton);

        eastBottom.add(showOnlyLabel);
        eastBottom.add(milkRadioButton);
        eastBottom.add(proteinRadioButton);
        eastBottom.add(fruitRadioButton);


        radioButtonLayout();
    }

    //EFFECTS: sets up action listener
    private void setupFruitRadioButtonActionListener() {

        fruitRadioButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                updateSubsetIngredientsTextArea(fruits);
                eastMiddle.revalidate();
                eastMiddle.revalidate();
                subsetIngredientsTextArea.revalidate();
                subsetIngredientsTextArea.repaint();
                playSound();
            }
        });
    }

    //EFFECTS: sets up action listener
    private void setupProteinRadioButtonActionListener() {


        proteinRadioButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                updateSubsetIngredientsTextArea(protein);
                eastMiddle.revalidate();
                eastMiddle.revalidate();
                subsetIngredientsTextArea.revalidate();
                subsetIngredientsTextArea.repaint();
                playSound();
            }
        });

    }

    //EFFECTS: sets up action listener
    private void setupMilkRadioButtonActionListener() {

        milkRadioButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                updateSubsetIngredientsTextArea(milk);
                eastMiddle.revalidate();
                eastMiddle.revalidate();
                subsetIngredientsTextArea.revalidate();
                subsetIngredientsTextArea.repaint();
                playSound();
            }
        });
    }

    //EFFECTS: sets up action listener
    private void radioButtonLayout() {
        showOnlyLabel.setFont(new Font("Arial",Font.BOLD,42));

        milkRadioButton.setFont(new Font("Arial",Font.BOLD,40));
        milkRadioButton.setVerticalAlignment(SwingConstants.TOP);

        proteinRadioButton.setFont(new Font("Arial",Font.BOLD,40));
        proteinRadioButton.setVerticalAlignment(SwingConstants.CENTER);


        fruitRadioButton.setFont(new Font("Arial",Font.BOLD,40));
        fruitRadioButton.setVerticalAlignment(SwingConstants.BOTTOM);
    }

    private void eastSubPanelComponentsSetup() {
        ingredientsTextArea = new JTextArea(13,30);
        eastTop.add(ingredientsTextArea);
        ingredientsTextArea.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        ingredientsTextArea.setLineWrap(true);
        ingredientsTextArea.setWrapStyleWord(true);
        ingredientsTextArea.setEditable(false);
        ingredientsTextArea.setFont(new Font("Arial",Font.ITALIC,30));

        subsetIngredientsTextArea = new JTextArea(13,30);
        eastMiddle.add(subsetIngredientsTextArea);
        subsetIngredientsTextArea.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
        subsetIngredientsTextArea.setLineWrap(true);
        subsetIngredientsTextArea.setWrapStyleWord(true);
        subsetIngredientsTextArea.setEditable(false);
        subsetIngredientsTextArea.setFont(new Font("Arial",Font.ITALIC,30));


    }

    //MODIFIES: this
    //EFFECTS: sets up custom smoothie menu window
    private void initializeGraphics() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(true);
        setVisible(true);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.GREEN);
        this.getContentPane().setBackground(Color.MAGENTA);

    }

    //EFFECTS: detects mouse click for all buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == milkButton1) {
            chosenMilk = COW_MILK;
            milk = chosenMilk.getName();
            ingredients.add(chosenMilk.getName());
            disableAllMilkButtons();
            enableAllFruitButtons();
            updateIngredientsTextArea();
            playSound();

        }
        if (e.getSource() == milkButton2) {
            chosenMilk = OAT_MILK_SWEETENED;
            milk = chosenMilk.getName();
            ingredients.add(chosenMilk.getName());
            disableAllMilkButtons();
            enableAllFruitButtons();
            updateIngredientsTextArea();
            playSound();
        }
        actionPerformedPartTwo(e);

    }

    //EFFECTS: detects mouse click for all buttons
    private void actionPerformedPartTwo(ActionEvent e) {
        if (e.getSource() == milkButton3) {
            chosenMilk = ALMOND_MILK_UNSWEETENED;
            milk = chosenMilk.getName();
            ingredients.add(chosenMilk.getName());
            disableAllMilkButtons();
            enableAllFruitButtons();
            updateIngredientsTextArea();
            playSound();
        }
        if (e.getSource() == fruitButton1) {
            Fruit f = BANANA;
            fruits.add(f.getName());
            chosenFruits.add(f);
            ingredients.add(f.getName());
            updateIngredientsTextArea();
            playSound();
        }

        actionPerformedPartThree(e);

    }

    //EFFECTS: detects mouse click for all buttons
    private void actionPerformedPartThree(ActionEvent e) {

        if (e.getSource() == fruitButton2) {
            Fruit f = MANGO;
            fruits.add(f.getName());
            chosenFruits.add(f);
            ingredients.add(f.getName());
            updateIngredientsTextArea();
            playSound();
        }
        if (e.getSource() == fruitButton3) {
            Fruit f = CHERRY;
            fruits.add(f.getName());
            chosenFruits.add(f);
            ingredients.add(f.getName());
            updateIngredientsTextArea();
            playSound();
        }


        actionPerformedPartFour(e);
    }

    //EFFECTS: detects mouse click for all buttons
    private void actionPerformedPartFour(ActionEvent e) {

        if (e.getSource() == fruitButton4) {
            Fruit f = APPLE;
            fruits.add(f.getName());
            chosenFruits.add(f);
            ingredients.add(f.getName());
            updateIngredientsTextArea();
            playSound();
        }

        if (e.getSource() == stopFruitSelection) {
            disableAllFruitButtons();
            enableAllProteinButtons();
            updateIngredientsTextArea();
            playSound();
        }

        actionPerformedPartFive(e);
    }

    //EFFECTS: detects mouse click for all buttons
    private void actionPerformedPartFive(ActionEvent e) {

        if (e.getSource() == proteinButton1) {
            chosenProtein = VEGAN_PROTEIN;
            protein = chosenProtein.getName();
            submitNameButton.setEnabled(true);
            ingredients.add(chosenProtein.getName());
            disableAllProteinButtons();
            updateIngredientsTextArea();
            playSound();
        }

        if (e.getSource() == proteinButton2) {
            chosenProtein = WHEY_PROTEIN;
            protein = chosenProtein.getName();
            submitNameButton.setEnabled(true);
            ingredients.add(chosenProtein.getName());
            disableAllProteinButtons();
            updateIngredientsTextArea();
            playSound();
        }


        actionPerformedPartSix(e);
    }

    //EFFECTS: detects mouse click for all buttons
    private void actionPerformedPartSix(ActionEvent e) {
        if (e.getSource() == submitNameButton) {
            enteredName = smoothieName.getText();
            submitNameButton.setEnabled(false);
            buildSmoothieButton.setEnabled(true);
            playSound();
        }

        if (e.getSource() == buildSmoothieButton) {
            try {
                customMadeSmoothie = new Smoothie(enteredName,chosenFruits,chosenMilk,chosenProtein);
                playSound();
                saveSmoothieButton.setEnabled(true);
                buildSmoothieButton.setEnabled(false);
            } catch (ZeroLengthName zeroLengthName) {
                new ErrorWindow();
                submitNameButton.setEnabled(true);
            }

        }

        if (e.getSource() == saveSmoothieButton) {
            new SmoothieDetailSelector(customMadeSmoothie);
            saveSmoothieButton.setEnabled(false);
            saveSmoothieUi(customMadeSmoothie);
        }

    }

    //EFFECTS: disables all protein buttons
    private void disableAllProteinButtons() {
        for (JButton b: proteinButtons) {
            b.setEnabled(false);
        }
    }

    //EFFECTS: enables all protein buttons
    private void enableAllProteinButtons() {
        for (JButton b: proteinButtons) {
            b.setEnabled(true);
        }
    }

    //EFFECTS: disables all fruit buttons
    private void disableAllFruitButtons() {
        for (JButton b: fruitButtons) {
            b.setEnabled(false);
        }

        stopFruitSelection.setEnabled(false);

    }

    //EFFECTS: enables all fruit buttons
    private void enableAllFruitButtons() {
        for (JButton b: fruitButtons) {
            b.setEnabled(true);
        }

        stopFruitSelection.setEnabled(true);
    }

    //EFFECTS: disables all milk buttons
    private void disableAllMilkButtons() {
        for (JButton b: milkButtons) {
            b.setEnabled(false);
        }
    }

    // source: http://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html
    // EFFECTS: plays sounds on button click
    private void playSound() {
        try {
            AudioInputStream audioInputStream;
            audioInputStream = AudioSystem.getAudioInputStream(new File("./data/buzzer.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    // EFFECTS: saves the Smoothie to file
    private void saveSmoothieUi(Smoothie customSmoothie) {
        try {
            jsonWriter.open();
            jsonWriter.writeSmoothie(customSmoothie);
            jsonWriter.close();
            System.out.println("Saved " + customSmoothie.getName() + " to " + JSON_STORE_UI);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_UI);
        }

    }

    //MODIFIES: ingredientsTextArea and eastPanel
    //EFFECTS: updates text area on radio button click
    private void updateIngredientsTextArea() {
        ingredientsTextArea.setText("The Smoothie Contains: ");
        for (String s: ingredients) {
            ingredientsTextArea.append(s);
            ingredientsTextArea.append(" ");
        }
    }

    //MODIFIES:subsetIngredientsTextArea and eastPanel
    //EFFECTS: updates text area on west panel button clicks
    private void updateSubsetIngredientsTextArea(String ingredient) {
        subsetIngredientsTextArea.setText("Ingredient from Selected Category: ");
        subsetIngredientsTextArea.append(ingredient);


    }

    //MODIFIES:subsetIngredientsTextArea and eastPanel
    //EFFECTS: updates text area on west panel button clicks
    private void updateSubsetIngredientsTextArea(ArrayList<String> ingredients) {
        subsetIngredientsTextArea.setText("Ingredients from Selected Category: ");
        for (String s: ingredients) {
            subsetIngredientsTextArea.append(s);
            subsetIngredientsTextArea.append(" ");
        }


    }
}
