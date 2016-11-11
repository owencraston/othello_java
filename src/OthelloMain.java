// Imports
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import javax.swing.*;
import java.awt.Font;

public class OthelloMain extends JFrame{

    // Creating event listener
    OthelloEvent Othello = new OthelloEvent(this);
    // Creating a 2d button array containing 64 buttons
    JButton [][] boxes = new JButton[8][8];
    // Creating a label to store what turn it is 
    JLabel turn = new JLabel(" The turn is:      ");
    // Creating two labels to store the number of pieces each player has
    JLabel whiteP = new JLabel("Black Score:  ");
    JLabel blackP = new JLabel("White Score:  ");
    // Creating button called play
    JButton play = new JButton ("Play");
    // Creating buttons for different colors, difficulties, instructions, exit, going back, highscores and players
    JButton instructions = new JButton ("Instructions");
    JButton player1 = new JButton ("1 Player");
    JButton player2 = new JButton ("2 Players");
    JButton blackB = new JButton ("Black");
    JButton whiteB = new JButton ("White");
    JButton easy = new JButton ("Easy");
    JButton hard = new JButton ("Hard");
    JButton back1 = new JButton ("Back");
    JButton back2 = new JButton ("Back");
    JButton exit = new JButton ("Exit");
    JButton exit2 = new JButton ("Exit");
    // Creating frames for the menus
    JFrame frame1 = new JFrame ("Othello Menu");
    JFrame frame2 = new JFrame ("Othello GameBoard");
    JFrame frame3 = new JFrame ("Othello Instructions");
    // Creating JLabels for background
    JLabel background = new JLabel();
    JLabel background2 = new JLabel();
    JLabel background3 = new JLabel();
    // Creating JLabels to tell user to enter the name
    JLabel name1 = new JLabel(" Enter player 1 name:");
    JLabel name2 = new JLabel(" Enter player 2 name:");
    // Creating JLabel and JPanel for title
    JLabel title = new JLabel("Play Othello");
    JPanel titlePanel = new JPanel();
    // Creating JPanel for buttons and for play button
    JPanel buttonsPanel = new JPanel();
    JPanel playPanel = new JPanel();
    // Creating JPanels for each frame
    JPanel boardBorder = new JPanel();
    JPanel scores = new JPanel();
    JPanel gameboard = new JPanel();
    JPanel instructionsPanel = new JPanel();
    JPanel instructionsBack = new JPanel();
    // Creating JButton to go back from the gameboard to the main menu and restart the game
    JButton restart = new JButton ("Restart");
    // Creating JTextAreas for user to input names
    JTextField user1 = new JTextField ();
    JTextField user2 = new JTextField ();
    // Creating button for the user to report that he has no more available moves
    JButton noMoves = new JButton ("No Moves");
    // Creating JLabel for the numbers of moves
    JLabel movesNumber = new JLabel("Number of moves: ");
    // Creating JLabel to show the name of the opponents
    JLabel names1 = new JLabel();
    JLabel names2 = new JLabel();
    JLabel names3 = new JLabel();
    // Creating a label to store the instructions image
    JLabel instructionsImage = new JLabel();
    
    // Method to set playing board
    public OthelloMain () {
        
        // Setting size and title of the frames
        frame1.setSize (480,600);
        frame1.setResizable(false);
        frame2.setSize (1100,1000);
        frame2.setResizable(false);
        frame3.setSize (700,1300);
        frame3.setResizable(false);
        // Makes the program stop running when the window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Initializing variables for new name for buttons
        String newName, newX, newY;
        
        // Setting font to zero to make it non-visible
        Font font1 = new Font("Verdana", Font.BOLD, 0);
        
        // Creating and adding the buttons to the gameboard 
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                
                // Getting single position of coordinate
                newX = String.valueOf(i);
                
                newY = String.valueOf(j);
                // Setting newName variable to the combined coordinate
                newName = newX + newY;
                
                // Assigning names to each button
                boxes [i][j] = new JButton(newName);
                // Assigning 0 font to each button to make the numbers invisible
                boxes [i][j].setFont(font1);
                
                // Alternating colours by modding the sum of i and j by 2
                    if ((i+j)%2 == 0 ){
                        boxes [i][j] .setBackground(new Color(0, 110, 57) );
                    } else {
                        boxes [i][j] .setBackground(new Color(24, 135, 45) );
                    }
                    
                // Setting size of buttons
                boxes [i][j].setPreferredSize(new Dimension(100, 100) );
                // Adding boxes to frame
                gameboard.add(boxes[i][j]);
                // Adding action listener for each box
                boxes[i][j].addActionListener(Othello); 
            }
        }
        
        // Creating icon for black and white tile and instructions
        ImageIcon black = new ImageIcon ("BlackTile.png");
        ImageIcon white = new ImageIcon ("WhiteTile.png");
        ImageIcon instructionsPic = new ImageIcon ("Instructions.png");
        // Creating icon for background
        ImageIcon woodBackground = new ImageIcon ("woodBackground.jpg");
        
        // Assigning values to first four boxes in the center
        boxes[3][3].setIcon(black);
        boxes[3][4].setIcon(white);        
        boxes[4][3].setIcon(white);
        boxes[4][4].setIcon(black);
        
        // Setting background2 (gameboard) to flowlayout
        FlowLayout layout = new FlowLayout();
        background.setLayout(layout);
        background2.setLayout(layout);
        scores.setLayout(layout);
        background3.setLayout(layout);
        
        // Arranging the components in a rectangular grid, where all cells are of equal size
        GridLayout layout1 = new GridLayout (6,3,130,30);
        buttonsPanel.setLayout(layout1);
        
        // Arranging the components in a rectangular grid, where all cells are of equal size
        GridLayout layout2 = new GridLayout (9,9,1,1);
        gameboard.setLayout(layout2);
        
        // Setting background for the frames
        background.setIcon(woodBackground);
        background2.setIcon(woodBackground);
        background3.setIcon(woodBackground);
        
        // Setting font for title
        Font titleFont = new Font ("Sans Serif", Font.CENTER_BASELINE, 50);
        title.setFont(titleFont);
        title.setForeground(Color.WHITE);
        titlePanel.setBackground(new Color(24, 135, 45));
        
        // Setting color for background
        buttonsPanel.setBackground(new Color(24, 135, 45));
        
        // Setting color font for name labels
        name1.setForeground(Color.WHITE);
        name2.setForeground(Color.WHITE);
        
        // Setting font for user names
        Font userFont = new Font ("Sans Serif", Font.CENTER_BASELINE, 15);
        user1.setFont(userFont);
        user2.setFont(userFont);
        name1.setFont(userFont);
        name2.setFont(userFont);
        
        // Setting font for names label
        Font namesFont = new Font ("Sans Serif", Font.CENTER_BASELINE, 15);
        names1.setFont(namesFont);
        names2.setFont(namesFont);
        names3.setFont(namesFont);
        
        // Setting image for instructions panel
        instructionsImage.setIcon(instructionsPic);
        instructionsPanel.add(instructionsImage);
        
        // Setting font for buttons
        Font buttonFont = new Font ("Sans Serif", Font.CENTER_BASELINE, 20);
        play.setFont(buttonFont);
        player1.setFont(buttonFont);
        player2.setFont(buttonFont);
        whiteB.setFont(buttonFont);
        blackB.setFont(buttonFont);
        easy.setFont(buttonFont);
        hard.setFont(buttonFont);
        instructions.setFont(buttonFont);
        restart.setFont(buttonFont);
        back1.setFont(buttonFont);
        back2.setFont(buttonFont);
        exit.setFont(buttonFont);
        exit2.setFont(buttonFont);
        noMoves.setFont(buttonFont);
        
        // Adding all buttons and labels to the label in the first frame
        background.add(titlePanel);
        background.add(buttonsPanel);
        background.add(playPanel);
        buttonsPanel.add(player1);
        buttonsPanel.add(player2);
        buttonsPanel.add(name1);
        buttonsPanel.add(name2);
        buttonsPanel.add(user1);
        buttonsPanel.add(user2);
        buttonsPanel.add(blackB);
        buttonsPanel.add(whiteB);
        buttonsPanel.add(easy);
        buttonsPanel.add(hard);
        buttonsPanel.add(instructions);
        buttonsPanel.add(exit);
        titlePanel.add(title);
        playPanel.add(play);
        
        // Adding restart button, no Mmoves button, turn, white player and black player labels to the gameboard
        scores.add(turn);
        scores.add(whiteP);
        scores.add(blackP);
        scores.add(movesNumber);
        scores.add(restart);
        scores.add(noMoves);
        scores.add(exit2);
        gameboard.add(names1);
        gameboard.add(names2);
        gameboard.add(names3);
        background2.add(gameboard);
        background2.add(scores);
        
        // Adding back button and instructions panel to background3 label
        instructionsBack.add(back1);
        background3.add(instructionsPanel);
        background3.add(instructionsBack);
        
        // Enabling the buttons to respond to mouse clicks
        play.addActionListener(Othello);
        player1.addActionListener(Othello);
        player2.addActionListener(Othello);
        blackB.addActionListener(Othello);
        whiteB.addActionListener(Othello);
        easy.addActionListener(Othello);
        hard.addActionListener(Othello);
        restart.addActionListener(Othello);
        instructions.addActionListener(Othello);
        back1.addActionListener(Othello);
        noMoves.addActionListener(Othello);
        exit.addActionListener(Othello);
        exit2.addActionListener(Othello);
        
        // Disabling text area2 unless user chooses a 2 player game
        user2.setEnabled(false);
        
        // Adding the panels to their respective frames
        frame1.add(background);
        frame2.add(background2);
        frame3.add(background3);
        
        // Setting menu frame to visible
        frame1.setVisible(true);
    }

    public static void main(String[] args){
        // Running the screen layout class
        OthelloMain frame = new OthelloMain();
    }
}