package ui;

import model.Smoothie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ui.SmoothieStoreApp.*;

// Class that shows the smoothie detail window
public class SmoothieDetailSelector extends JFrame  {

    Smoothie smoothie;

    //Panel Fields
    JPanel centerPanel;
    JPanel bottomPanel;

    //Button Fields
    JButton caloriesBtn;
    JButton sugarBtn;
    JButton proteinBtn;
    JButton checkOutBtn;


    public static final int BOTTOM_PANEL_HEIGHT = (FRAME_HEIGHT / 5);

    // Constructs an object of SmoothieDetailSelector
    public SmoothieDetailSelector(Smoothie smoothie) {
        this.smoothie = smoothie;
        initializeGraphics();
        initializePanels();
        initializeButtons();
    }

    // MODIFIES: this
    // EFFECTS: initializes graphics for smoothie detail selector window
    private void initializeGraphics() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
        setBackground(Color.GREEN);



    }

    // MODIFIES: this, centerPanel, bottomPanel
    // EFFECTS: initializes panels and adds them to SmoothieDetailSelector window
    private void initializePanels() {
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4,1,40,40));
        centerPanel.setBackground(Color.PINK);
        add(centerPanel,BorderLayout.NORTH);
        centerPanel.setPreferredSize(new Dimension(FRAME_WIDTH,CENTER_PANEL_HEIGHT));

        bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.DARK_GRAY);
        add(bottomPanel,BorderLayout.SOUTH);
        revalidate();
        repaint();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER,45,10));
        pack();
        bottomPanel.setPreferredSize(new Dimension(FRAME_WIDTH, BOTTOM_PANEL_HEIGHT));
    }

    //EFFECTS: initalizes buttons
    private void initializeButtons() {
        caloriesBtn = new JButton();
        sugarBtn = new JButton();
        proteinBtn = new JButton();
        checkOutBtn = new JButton();

        

        calorieButtonSetup();
        sugarButtonSetup();
        proteinButtonSetup();

    }

//    private void checkOutButtonSetup() {
//        checkOutBtn.setText("Check Out");
//        // showMenuButton.setFocusable(true);
//        checkOutBtn.setFont(new Font("Arial",  Font.BOLD, 40));
//        checkOutBtn.setPreferredSize(new Dimension(100,100));
//        checkOutBtn.setSize(100,100);
//        centerPanel.add(checkOutBtn);
//
//        checkOutBtn.addActionListener(new ActionListener() {
//    // TODO: Implement checkoutbutton functionality, omitted for now;
//            @Override
//            public void actionPerformed(ActionEvent arg0) {
//                quitLabel.setVisible(true);
//                centerPanel.revalidate();
//                centerPanel.repaint();
//                revalidate();
//                repaint();
//
//                checkOut();
//
//            }
//        });
//
//    }

    //MODIFIES: calorie button
    //EFFECTS: sets up calorie button
    private void calorieButtonSetup() {

        //firstSmoothie.setBounds(FRAME_WIDTH / 15,BUTTON_PANEL_TOP_MARGIN,BUTTON_WIDTH,BUTTON_HEIGHT);
        //firstSmoothie.addActionListener(this);
        caloriesBtn.setText("Show Calories");
        // showMenuButton.setFocusable(true);
        caloriesBtn.setFont(new Font("Arial",  Font.BOLD, 40));
        caloriesBtn.setPreferredSize(new Dimension(100,100));
        caloriesBtn.setSize(100,100);
        centerPanel.add(caloriesBtn);

        String  caloriesLabelText = nutritionalLabelGenerator("calories");
        JLabel caloriesLabel = new JLabel(caloriesLabelText);

        caloriesBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                bottomPanelLabelAddition(caloriesLabel);

            }
        });


    }

    //MODIFIES: sugar button
    //EFFECTS: sets up sugar button
    private void sugarButtonSetup() {
        //secondSmoothie.setBounds(FRAME_WIDTH / 15,BUTTON_PANEL_TOP_MARGIN,BUTTON_WIDTH,BUTTON_HEIGHT);
        //firstSmoothie.addActionListener(this);
        sugarBtn.setText("Show sugar content");
        // showMenuButton.setFocusable(true);
        sugarBtn.setFont(new Font("Arial",  Font.BOLD, 40));
        centerPanel.add(sugarBtn);

        String  sugarLabelText = nutritionalLabelGenerator("sugar");
        JLabel sugarLabel = new JLabel(sugarLabelText);

        sugarBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                bottomPanelLabelAddition(sugarLabel);

            }
        });






    }

    //EFFECTS: sets up bottom panel labels
    private void bottomPanelLabelAddition(JLabel label) {
        label.setVerticalTextPosition(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setFont(new Font("Arial",Font.BOLD,60));
        label.setForeground(Color.white);
        bottomPanel.removeAll();
        bottomPanel.revalidate();
        bottomPanel.repaint();
        bottomPanel.add(label);
        pack();
    }

    //EFFECTS: sets up protein button
    //MODIFIES: proteinBtn
    private void proteinButtonSetup() {
        //firstSmoothie.setBounds(FRAME_WIDTH / 15,BUTTON_PANEL_TOP_MARGIN,BUTTON_WIDTH,BUTTON_HEIGHT);
        //firstSmoothie.addActionListener(this);
        proteinBtn.setText("Show protein content");
        // showMenuButton.setFocusable(true);
        proteinBtn.setFont(new Font("Arial",  Font.BOLD, 40));
        centerPanel.add(proteinBtn);
        centerPanel.revalidate();
        centerPanel.repaint();
        revalidate();
        repaint();

        String  proteinLabelText = nutritionalLabelGenerator("protein");
        JLabel proteinLabel = new JLabel(proteinLabelText);

        proteinBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                bottomPanelLabelAddition(proteinLabel);

            }
        });
    }

    //EFFECTS: generates label for appropriate button click
    private String nutritionalLabelGenerator(String type) {
        if (type.equals("sugar")) {
            return smoothie.getName() + " has " + smoothie.getSugarContent() + " grams" + " of sugar.";
        } else if (type.equals("protein")) {
            return smoothie.getName() + " has " + smoothie.getProteinContent() + " grams" + " of protein.";
        } else if (type.equals("calories")) {
            return smoothie.getName() + " has " + smoothie.getCalories() + " " + " calories.";

        }

        return "shouldn't have reached this point";
    }



}
