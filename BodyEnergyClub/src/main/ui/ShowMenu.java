package ui;

import model.Smoothie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static model.Smoothie.*;
import static ui.SmoothieStoreApp.*;

//Class that represents the window with the hard-coded smoothie menu
public class ShowMenu extends JFrame  {


    //Panel Fields
    JPanel centerPanel;

    //Button Fields
    JButton firstSmoothieBtn;
    JButton secondSmoothieBtn;
    JButton thirdSmoothieBtn;

    //MODIFIES: this
    //EFFECTS: makes calls to functions that initialize the menu
    public ShowMenu() {
        initializeGraphics();
        initializePanels();
        initializeButtons();
    }

    //MODIFIES: this and centerPanel
    //EFFECTS:  initializes center panel and adds it to this
    private void initializePanels() {
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3,1,40,40));
        centerPanel.setBackground(Color.PINK);
        add(centerPanel,BorderLayout.CENTER);
        centerPanel.setPreferredSize(new Dimension(FRAME_WIDTH,CENTER_PANEL_HEIGHT));

    }

    //MODIFIES: this and buttonPanel
    //EFFECTS:  initializes buttons and adds them to this
    private void initializeButtons() {
        firstSmoothieBtn = new JButton();
        secondSmoothieBtn = new JButton();
        thirdSmoothieBtn = new JButton();

        firstSmoothieSetup();
        secondSmoothieSetup();
        thirdSmoothieSetup();
    }

    //MODIFIES: firstSmoothieBtn and centerPanel
    //EFFECTS: sets up first Smoothie button and adds it ot center panel
    private void firstSmoothieSetup() {

        //firstSmoothie.setBounds(FRAME_WIDTH / 15,BUTTON_PANEL_TOP_MARGIN,BUTTON_WIDTH,BUTTON_HEIGHT);
        //firstSmoothie.addActionListener(this);
        firstSmoothieBtn.setText("Banana Special");
        // showMenuButton.setFocusable(true);
        firstSmoothieBtn.setFont(new Font("Arial",  Font.BOLD, 40));
        centerPanel.add(firstSmoothieBtn);

        firstSmoothieBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                new SmoothieDetailSelector(bananaSmoothieVegan);

            }
        });


    }

    //MODIFIES: secondSmoothieBtn and centerPanel
    //EFFECTS: sets up second Smoothie button and adds it ot center panel
    private void secondSmoothieSetup() {
        //secondSmoothie.setBounds(FRAME_WIDTH / 15,BUTTON_PANEL_TOP_MARGIN,BUTTON_WIDTH,BUTTON_HEIGHT);
        //firstSmoothie.addActionListener(this);
        secondSmoothieBtn.setText("Cherry Blast");
        // showMenuButton.setFocusable(true);
        secondSmoothieBtn.setFont(new Font("Arial",  Font.BOLD, 40));
        centerPanel.add(secondSmoothieBtn);

        secondSmoothieBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                new SmoothieDetailSelector(cherrySmoothieNotVegan);

            }
        });

    }

    //MODIFIES: thirdSmoothieBtn and centerPanel
    //EFFECTS: sets up third Smoothie button and adds it ot center panel
    private void thirdSmoothieSetup() {
        //firstSmoothie.setBounds(FRAME_WIDTH / 15,BUTTON_PANEL_TOP_MARGIN,BUTTON_WIDTH,BUTTON_HEIGHT);
        //firstSmoothie.addActionListener(this);
        thirdSmoothieBtn.setText("Banana-Mango Duo");
        // showMenuButton.setFocusable(true);
        thirdSmoothieBtn.setFont(new Font("Arial",  Font.BOLD, 40));
        centerPanel.add(thirdSmoothieBtn);

        thirdSmoothieBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {

                new SmoothieDetailSelector(bananaMangoSmoothieNotVegan);

            }
        });


    }


    //MODIFIES: this
    //EFFECTS: initializes JFrame (ShowMenu Window)
    private void initializeGraphics() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(true);
        setVisible(true);
        //setDefaultCloseOperation(JFrame.);
        setBackground(Color.GREEN);
        this.getContentPane().setBackground(Color.MAGENTA);


    }



}
