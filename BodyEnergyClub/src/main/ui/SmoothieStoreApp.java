package ui;

import exceptions.InvalidInput;
import exceptions.ZeroLengthName;
import model.Fruit;
import model.Milk;
import model.Protein;
import model.Smoothie;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;

import static model.Fruit.*;
import static model.Milk.*;
import static model.Protein.VEGAN_PROTEIN;
import static model.Protein.WHEY_PROTEIN;
import static model.Smoothie.*;
// Code modelled after teller application, simple drawing player
// link: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
//

// Smoothie Store Application
public class SmoothieStoreApp extends JFrame implements ActionListener {
    private ArrayList<Smoothie> smoothiesInStore;
    private ArrayList<Milk> milksInStore;
    private ArrayList<Fruit> fruitsInStore;
    private ArrayList<Protein> proteinsInStore;
    private Scanner input;
    private Scanner scan;

    public static final int FRAME_WIDTH = 2000;
    public static final int FRAME_HEIGHT = 1500;

    public static final int BUTTON_PANEL_HEIGHT = (FRAME_HEIGHT / 5);
    public static final int RED_PANEL_HEIGHT = (FRAME_HEIGHT / 5);
    public static final int CENTER_PANEL_HEIGHT = FRAME_HEIGHT - BUTTON_PANEL_HEIGHT - RED_PANEL_HEIGHT;

    public static final int ICON_TEXT_GAP = -20;
    public static final Color BORDER_COLOUR = Color.blue;
    public static final int BUTTON_WIDTH = FRAME_WIDTH / 5;
    public static final int BUTTON_HEIGHT = 125;
    public static final int BUTTON_PANEL_TOP_MARGIN = 30;

    //JSON Fields
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    private final JsonReader jsonReader2;
    private static final String JSON_STORE = "./data/smoothiestore.json";
    private static final String JSON_STORE_UI = "./data/smoothiestoreui.json";


    //Panel Fields
    JPanel redPanel;
    JPanel buttonPanel;
    JPanel centerPanel;

    //Label Fields
    JLabel bodyWellnessLabel;
    JLabel quitLabel;

    //Button Fields
    JButton showMenuButton;
    JButton customMenuButton;
    JButton quitButton;
    JButton loadSmoothieButton;



    // EFFECTS: runs the smoothie store application
    public SmoothieStoreApp() {
        super("Body Wellness Club");
       // initializeFields();

        initializeGraphics();
        initializePanels();
        initializeLabels();
        initializeButtons();

        //init();
        //initializeSound();
        //initializeInteraction();

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        jsonReader2 = new JsonReader(JSON_STORE_UI);

//        runSmoothieStore();
        // for console uncomment line above
    }

    private void initializePanels() {

        redPanel = new JPanel();
        redPanel.setBackground(Color.red);
        add(redPanel,BorderLayout.NORTH);
        pack();
        redPanel.setPreferredSize(new Dimension(FRAME_WIDTH,RED_PANEL_HEIGHT));


        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.DARK_GRAY);
        add(buttonPanel,BorderLayout.SOUTH);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,45,10));
        pack();
        buttonPanel.setPreferredSize(new Dimension(FRAME_WIDTH, BUTTON_PANEL_HEIGHT));

        centerPanel = new JPanel();
        centerPanel.setBackground(Color.pink);
        add(centerPanel,BorderLayout.CENTER);
        pack();
        centerPanel.setPreferredSize(new Dimension(FRAME_WIDTH, CENTER_PANEL_HEIGHT));

    }


    // EFFECTS: initializes and adds labels to Frame that are used in the Smoothie Store App
    private void initializeLabels() {

        bodyWellnessLabel = new JLabel("BODY WELLNESS CLUB");
        bodyWellnessLabelSetUp();

        quitLabel = new JLabel("Thank you for coming. See you soon!");
        quitLabelSetUp();


    }

    private void quitLabelSetUp() {

        quitLabel.setVerticalTextPosition(SwingConstants.CENTER);
        quitLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        quitLabel.setFont(new Font("Arial",Font.BOLD,60));
        centerPanel.add(quitLabel);
        pack();
        quitLabel.setVisible(false);

    }

    private void bodyWellnessLabelSetUp() {

        Border border = BorderFactory.createLineBorder(BORDER_COLOUR,5,true);
        ImageIcon menuImage = new ImageIcon("MenuIcon.png");
        bodyWellnessLabel.setIcon(menuImage);
        bodyWellnessLabel.setHorizontalTextPosition(JLabel.CENTER);
        bodyWellnessLabel.setVerticalTextPosition(JLabel.BOTTOM);
        bodyWellnessLabel.setForeground(Color.BLUE);
        bodyWellnessLabel.setFont(new Font("Arial",Font.BOLD,50));
        bodyWellnessLabel.setIconTextGap(ICON_TEXT_GAP);
        // bodyWellnessLabel.setBackground(Color.black);
        // bodyWellnessLabel.setOpaque(true);
        // bodyWellnessLabel.setBorder(border);
        bodyWellnessLabel.setHorizontalAlignment(SwingConstants.LEFT);
        bodyWellnessLabel.setBounds(FRAME_WIDTH / 3, FRAME_HEIGHT / 10, FRAME_WIDTH,100);
        redPanel.add(bodyWellnessLabel);
    }


    // MODIFIES: this
    // EFFECTS: draws the JFrame window where the Smoothie Store Application will run
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.GREEN);
        this.getContentPane().setBackground(Color.GREEN);
        pack();

    }

    private void initializeButtons() {

        showMenuButton = new JButton();
        customMenuButton = new JButton();
        quitButton = new JButton();
        loadSmoothieButton = new JButton();

        showMenuButtonSetUp();
        customMenuButtonSetUp();
        quitButtonSetUp();
        loadSmoothieButtonSetUp();
        pack();

    }

    private void loadSmoothieButtonSetUp() {

        loadSmoothieButton.setBounds((FRAME_WIDTH / 20) + 2 * (FRAME_WIDTH / 3),BUTTON_PANEL_TOP_MARGIN,
                BUTTON_WIDTH,BUTTON_HEIGHT);
        loadSmoothieButton.setText("Load last custom made smoothie");
        // showMenuButton.setFocusable(true);
        loadSmoothieButton.setEnabled(true);
        loadSmoothieButton.setFont(new Font("Arial",  Font.BOLD, 30));
        buttonPanel.add(loadSmoothieButton);


        loadSmoothieButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                loadSmoothieUi();
                centerPanel.revalidate();
                centerPanel.repaint();
                revalidate();
                repaint();

            }
        });
    }


    private void quitButtonSetUp() {

        quitButton.setBounds((FRAME_WIDTH / 20) + 2 * (FRAME_WIDTH / 3),BUTTON_PANEL_TOP_MARGIN,
                BUTTON_WIDTH,BUTTON_HEIGHT);
        quitButton.addActionListener(this);
        quitButton.setText("Quit App");
        // showMenuButton.setFocusable(true);
        quitButton.setFont(new Font("Arial",  Font.BOLD, 30));
        buttonPanel.add(quitButton);


        quitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                quitLabel.setVisible(true);
                centerPanel.revalidate();
                centerPanel.repaint();
                revalidate();
                repaint();

                checkOut();

            }
        });
    }



    private void customMenuButtonSetUp() {

        customMenuButton.setBounds((FRAME_WIDTH / 15) + (FRAME_WIDTH / 3),
                BUTTON_PANEL_TOP_MARGIN,BUTTON_WIDTH,BUTTON_HEIGHT);
        customMenuButton.addActionListener(this);
        customMenuButton.setText("Custom Order");
        // showMenuButton.setFocusable(true);
        customMenuButton.setFont(new Font("Arial",  Font.BOLD, 30));
        buttonPanel.add(customMenuButton);
    }

    private void showMenuButtonSetUp() {


        showMenuButton.setBounds(FRAME_WIDTH / 15,BUTTON_PANEL_TOP_MARGIN,BUTTON_WIDTH,BUTTON_HEIGHT);
        showMenuButton.addActionListener(this);
        showMenuButton.setText("Show Menu");
        // showMenuButton.setFocusable(true);
        showMenuButton.setFont(new Font("Arial",  Font.BOLD, 30));
        buttonPanel.add(showMenuButton);

    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runSmoothieStore() {
        boolean keepGoing = true;
        String command = null;


        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThank you for coming to the store. See you soon");


    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("s")) {
            showSmoothieMenu();
        } else if (command.equals("c")) {
            showCustomSmoothieMenu();
        } else if (command.equals("l")) {
            loadSmoothie();
        } else {
            System.out.println("Selection not valid....");
        }
    }

    // MODIFIES:
    // EFFECTS : builds custom smoothie based on details entered by users
    private void showCustomSmoothieMenu() {
        Smoothie customSmoothie;
        System.out.println("\nWelcome to custom smoothie builder ");
        Milk customMilk = null;


        try {
            customMilk = chooseMilk();
        } catch (InvalidInput invalidInput) {
            System.out.println("Please enter a valid number");
            showCustomSmoothieMenu();
        }
        List<Fruit> customFruits = chooseFruits();
        Protein customProtein = chooseProtein();
        String customName = chooseName();

        try {
            customSmoothie = new Smoothie(customName,customFruits,customMilk,customProtein);
            printDetailsCustomSmoothie(customSmoothie);
        } catch (ZeroLengthName zeroLengthName) {
            new ErrorWindow();
            zeroLengthName.printStackTrace();
        }


    }

    //EFFECTS: prints out details of custom smoothie and terminates program
    private void printDetailsCustomSmoothie(Smoothie customSmoothie) {

        System.out.println("\nHere are the details of your order:");
        System.out.println("\tName:" + customSmoothie.getName());
        System.out.println("\tProtein :" + customSmoothie.getProteinContent() + " grams");
        System.out.println("\tCalories:" + customSmoothie.getCalories() + " calories");
        System.out.println("\tSugar:" + customSmoothie.getSugarContent() + " grams");
        
        wouldLikeToSaveSmoothie(customSmoothie);
    
//        checkOut();
    }

    private void wouldLikeToSaveSmoothie(Smoothie customSmoothie) {
        System.out.println("\nWould you like to save your smoothie?");
        System.out.println("\ty -> yes");
        System.out.println("\tn -> no");

        Scanner input2 = new Scanner(System.in);
        String command = input2.next();
        command = command.toLowerCase();

        if (command.equals("y")) {
            saveSmoothie(customSmoothie);
        }
        
    }

    // EFFECTS: saves the Smoothie to file
    private void saveSmoothie(Smoothie customSmoothie) {
        try {
            System.out.println("----Writer opening----");
            jsonWriter.open();
            jsonWriter.writeSmoothie(customSmoothie);
            jsonWriter.close();
            System.out.println("Saved " + customSmoothie.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }

    }

    // MODIFIES: this
    // EFFECTS: loads smoothie from file
    private void loadSmoothie() {
        try {
            Smoothie smoothie = jsonReader.read();
            System.out.println("Loaded " + smoothie.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads smoothie from file
    private void loadSmoothieUi() {
        try {
            Smoothie smoothie = jsonReader2.read();
            new SmoothieDetailSelector(smoothie);

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_UI);
        }
    }

    //REQUIRES: input string of non-zero length
    //EFFECTS:  input string is returned to calling function to assign custom name to smoothie
    private String chooseName() {
        System.out.println("\tPlease provide a name for your custom designed smoothie:");
        String customName = input.next();
        return customName;

    }


    //EFFECTS : returns selected milk from given menu to calling method
    private Milk chooseMilk() throws InvalidInput {
        int i = 0;

        System.out.println("\tChoose from the following milks:");

        for (Milk m: milksInStore) {
            System.out.println("\tPress " + ++i + " for:" + m.getName());
        }
        int intCommand = scan.nextInt();
        if (intCommand > milksInStore.size()) {
            throw new InvalidInput();
        }
        return milksInStore.get(intCommand - 1);
        
    }

    //REQUIRES: input>=1 && input<= proteinsInStore.size()
    //EFFECTS : returns selected protein from given menu to calling method
    private Protein chooseProtein() {
        int i = 0;

        System.out.println("\tChoose from the following proteins:");
        
        for (Protein p: proteinsInStore) {
            System.out.println("\tPress " + ++i + " for:" + p.getName());
        }
        int intCommand = scan.nextInt();
        return proteinsInStore.get(intCommand - 1);
    }

    //EFFECTS: let's user choose fruits from menu and has option to create new custom fruit
    private List<Fruit> chooseFruits() {

        ArrayList<Fruit> selectedFruits = new ArrayList<>();
        Fruit userDefinedFruit;
        
        boolean keepGoing = true;

        while (keepGoing) {
            fruitCommandPrint();
            int intCommand = scan.nextInt();
            if (intCommand >= 1 && intCommand <= fruitsInStore.size()) {
                selectedFruits.add(fruitsInStore.get(intCommand - 1));
            } else if (intCommand == (fruitsInStore.size() + 1)) {
                try {
                    userDefinedFruit = new Fruit(inputFruitName(),inputSugarContent(),inputProteinContent(),
                            inputCalories());
                    selectedFruits.add(userDefinedFruit);
                } catch (ZeroLengthName zeroLengthName) {
                    zeroLengthName.printStackTrace();
                }

            } else if (intCommand == (fruitsInStore.size() + 2)) {
                keepGoing = false;
            }

        }
        
        return selectedFruits;

    }

    private void fruitCommandPrint() {
        int i = 0;
        System.out.println("\tChoose from the following fruits(You can choose same fruit multiple times):");
        for (Fruit f: fruitsInStore) {
            System.out.println("\tPress " + ++i + " for:" + f.getName());
        }

        System.out.println("\tPress " + (fruitsInStore.size() + 1) + " to add a fruit not on the menu");
        System.out.println("\tPress " + (fruitsInStore.size() + 2) + " to finish fruit selection");
    }

    //REQUIRES: input >= 0
    //EFFECTS: returns entered int to calling method to assign new fruit's calories content
    private int inputCalories() {
        System.out.println("\tEnter calories (>= 0) of the fruit:");
        int fruitCalories = scan.nextInt();
        return fruitCalories;
    }

    //REQUIRES: input >= 0
    //EFFECTS: returns entered int to calling method to assign new fruit's protein content
    private int inputProteinContent() {
        System.out.println("\tEnter protein content (>= 0 grams) of the fruit:");
        int fruitProtein = scan.nextInt();
        return fruitProtein;
    }

    //REQUIRES: input >= 0
    //EFFECTS: returns entered int to calling method to assign new fruit's sugar content
    private int inputSugarContent() {
        System.out.println("\tEnter sugar content (>= 0 grams) of the fruit:");
        int fruitSugar = scan.nextInt();
        return fruitSugar;
    }

    //REQUIRES: input of non-zero length
    //EFFECTS: returns entered string to calling method to assign  new fruit's name
    private String inputFruitName() {
        String fruitName = null;
        System.out.println("\tEnter name of fruit:");
        fruitName = input.next();
        return fruitName;
    }

    // MODIFIES: this
    // EFFECTS: initializes smoothie list
    private void init() {
        smoothiesInStore = new ArrayList<>();
        smoothiesInStore.add(bananaSmoothieVegan);
        smoothiesInStore.add(cherrySmoothieNotVegan);
        smoothiesInStore.add(bananaMangoSmoothieNotVegan);

        milksInStore = new ArrayList<>();
        milksInStore.add(OAT_MILK_UNSWEETENED);
        milksInStore.add(OAT_MILK_SWEETENED);
        milksInStore.add(COW_MILK);
        milksInStore.add(ALMOND_MILK_UNSWEETENED);
        milksInStore.add(ALMOND_MILK_SWEETENED);

        proteinsInStore = new ArrayList<>();
        proteinsInStore.add(VEGAN_PROTEIN);
        proteinsInStore.add(WHEY_PROTEIN);

        fruitsInStore = new ArrayList<>();
        fruitsInStore.add(BANANA);
        fruitsInStore.add(MANGO);
        fruitsInStore.add(APPLE);
        fruitsInStore.add(CHERRY);


        input = new Scanner(System.in);
        scan =  new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> Show Smoothie Menu");
        System.out.println("\tc -> Make Custom Smoothie");
        System.out.println("\tl -> Load Previously Saved Custom Smoothie(s)");
        System.out.println("\tq -> Quit");

    }

    // MODIFIES: this
    // EFFECTS:  shows the smoothies in store
    private void showSmoothieMenu() {
        String command = null;
        System.out.println("Choose from the following smoothies:");
        for (int i = 0; i < smoothiesInStore.size(); ) {
            Smoothie s = smoothiesInStore.get(i);
            System.out.println(++i + " -> " + s.getName());
        }

        System.out.println("If you didn't like anything, press 'q' to go back to the previous menu");

        command = input.next();
        command = command.toLowerCase();

        if (command.equals("q")) {
            return;
        } else if (Integer.parseInt(command) >= 1 || Integer.parseInt(command) <= smoothiesInStore.size()) {
            processSmoothieCommand(command);
        } else {
            System.out.println("Invalid input. Choose again!");
            showSmoothieMenu();
        }
    }

    //REQUIRES: command >=1 && command <=5
    //EFFECTS: depending upon user input, calls relevant helper methods to produce output
    private void processSmoothieCommand(String command) {
        int num = Integer.parseInt(command) - 1;
        Smoothie selectedSmoothie = smoothiesInStore.get(num);
        System.out.println("You have selected our nutritious " + selectedSmoothie.getName());
        int option = showSelectedSmoothieMenu();
        switch (option) {
            case 1:
                calorieCase(selectedSmoothie,command);
                break;
            case 2:
                sugarCase(selectedSmoothie,command);
                break;
            case 3:
                System.out.println("Your smoothie has " + selectedSmoothie.getProteinContent() + " grams of protein");
                processSmoothieCommand(command);
                break;
            case 4:
                System.out.println("Here's your smoothie. Enjoy!!");
                checkOut();
            case 5:
                showSmoothieMenu();
            default:
                System.out.println("Invalid input.Select again");
        }
    }

    //EFFECTS: prints out sugar details of selected smoothie and goes back to same menu
    private void sugarCase(Smoothie selectedSmoothie, String command) {
        System.out.println("Your smoothie has " + selectedSmoothie.getSugarContent() + " grams of sugar");
        processSmoothieCommand(command);
    }

    //EFFECTS: prints out calorie details of selected smoothie and goes back to same menu
    private void calorieCase(Smoothie selectedSmoothie,String command) {
        System.out.println("Your smoothie has " + selectedSmoothie.getCalories() + " calories");
        processSmoothieCommand(command);
    }

    //REQUIRES: command >=1 && command <=5
    //EFFECTS: displays smoothie specific menu to user and takes input
    private int showSelectedSmoothieMenu() {

        String command = null;


        System.out.println("1 -> View Calories in Smoothie");
        System.out.println("2 -> View Sugar Content in Smoothie");
        System.out.println("3 -> View Protein Content in Smoothie");
        System.out.println("4 -> I'm hungry, I need food not facts.(Check out option)");
        System.out.println("5 -> Take me back to previous menu");

        command = input.next();

        int option = Integer.parseInt(command);
        return option;


    }


    //EFFECTS: prints out thank you statement and terminates the program
    private void checkOut() {
        //System.out.println("\nThank you for coming to the store. See you soon");
        //exit(0);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showMenuButton) {

            new ShowMenu();
            playSound();
        }

        if (e.getSource() == customMenuButton) {
            new CustomSmoothieMenu();
            playSound();
//            newContentPane.setOpaque(true);
//            setContentPane(newContentPane);

            pack();
            setVisible(true);
        }

//        if (e.getSource() == quitButton) {
//            out.println("---TESTING--");
//            centerPanel.remove(quitLabel);
//            centerPanel.removeAll();
//            centerPanel.revalidate();
//            centerPanel.repaint();
//            for (double i = 1.0; i <= 1000000000.0; i++) {
//
//            }
//            checkOut();
      //  }
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
}

//ADDING SPACING BETWEEN BUTTONS -> used flow layout
// quit label appearing only on pressing quit button?!
// show menu -> smoothie options : buttons needs to be smaller?!



