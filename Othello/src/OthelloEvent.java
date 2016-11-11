// Importing classes that will be used in the program
import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

// Creating a class that will respond to the mouse and keyboard by "listening"
public class OthelloEvent implements ItemListener, ActionListener, Runnable {
    
    // Giving name to board to call the Main in the Event package
    OthelloMain board;
    
    // Creating position array
    int position [][] = null;
    
    // Creating arrayList to store value of all possible sums for the computer
    int sumAI [][] = new int [8][8];
    
    // Creating variables to store X and Y position of button played
    int positionX;
    int positionY;
    
    // Creating variables to store new position of where the buttons will stop changing
    int newX;
    int newY;
    int tempX;
    int check;
    
    // Creating counters to store the value of how many pieces each player has
    int whitePiece;
    int blackPiece;
    
    // Creating 2D array to store the values of each buttons
    int value [][] = new int [8][8];
    
    // Creating empty, opposite and current int variables to store value of piece
    int opposite = 2;
    int current = 1;
    int absent = 0;
    
    // Creating boolean variables to check whether the computer finds a valid placement for the piece
    boolean play = false;
    boolean empty = false;
    boolean go = false;
    boolean invalid = false;
    
    // Creating boolean to decide whether the AI will check for the highest sum or lowest sum
    boolean easy = false;
    
    // Creating int variable to store the sum of the pieces
    int sumPieces;
    
    // Creating boolean variable to decide whether the AI will play or a person will
    boolean onePlayer = false;
    
    // Create a int variable to store the amount of seconds passed
    int secondsPassed = 0;
    
    // Creating variable to enable clicking only if the AI has already run
    boolean click = true;
    
    // Creating variable to make invalid move run only if the game hasn't reached the end yet
    boolean end = false;
    
    // Creating icon for black and white tile
    ImageIcon black = new ImageIcon ("BlackTile.png");
    ImageIcon white = new ImageIcon ("WhiteTile.png");

    // Creating turn string variables to determine which color will be next
    String turn = "";
    
    // Creating sum int to store sum of all boxes
    int sum = 0;
    
    // Creating sum, minSum and maxSum for the AI to calculate the best move for each position
    int positionSum = 0;
    int maxSum [][] = new int [8][8];
    int minSum = 100;
    
    // Creating counters to check whether a move is false
    int falseMove = 0;
    int run = 0;

    // Creating counters to decide what turn it is
    int turnCounter = 0;
    int valueCounter = 0;
    
    // Creating string variables to store the name of the players
    String user1, user2;
    
    // Creating counter to count the moves in the game
    int moves;
    
    // Creating action listener for timer
    ActionListener timerListener = new ActionListener() {
        
    // Method that calls timer
    public void actionPerformed(ActionEvent evt) {
             
        // Calling the AI with a one second delay
        AI(); 
        // Stopping the timer after it ran
        timer.stop();
        }
    };
    
    // Creating timer
    int timerDelay = 1 * 1000;
    javax.swing.Timer timer = new javax.swing.Timer(timerDelay, timerListener);
    
    // Making the event respond to the main
    public OthelloEvent (OthelloMain in){
        board = in;
    }
    
    // Method to create the starting layout
    public void startPosition(){
        // Assigning values to first four boxes in the center
        board.boxes[3][3].setIcon(black);
        board.boxes[3][4].setIcon(white);        
        board.boxes[4][3].setIcon(white);
        board.boxes[4][4].setIcon(black);
    }
    
    // Method to change the turn of the players
    public void place (){
        // Placing piece only if the move is valid
        if(run!=0){
            // Run if the box selected has no icon
            if (board.boxes[positionY][positionX].getIcon()!=black && board.boxes[positionY][positionX].getIcon()!=white){

                // Add one to the counter to change the turn of the player
                turnCounter ++;

                if (turnCounter%2==0){
                    // if the counter is even, the following turn will be white's
                    turn = "white";
                    // Setting label to following turn
                    board.turn.setText(" The turn is: " + turn);
                    // Setting icon to black
                    board.boxes[positionY][positionX].setIcon(black);
                } 
                else {
                    // if the counter is odd the following turn will be black's
                    turn = "black";
                    // Setting label to following turn
                    board.turn.setText(" The turn is: " + turn);
                    // Setting icon to white
                    board.boxes[positionY][positionX].setIcon(white); 
                }
            }
        }
    }
    
    // Method to assign values to each square depending on its color
    public void assign (){
        // Running through the whole button array
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                
                // Run if the icon is black
                if (board.boxes[i][j].getIcon()==black){
                    
                    if (valueCounter%2==0){
                        // if the counter is even assign value of 2 to black
                        value [i][j] = opposite;
                    } else {
                        // if the counter is odd assign value of 1 to black
                        value [i][j] = current;
                    }
                }
                // Run if the icon is white
                else if (board.boxes[i][j].getIcon()==white){
                    
                    if (valueCounter%2==0){
                        // if the counter is even assign value of 2 to white
                        value [i][j] = current;
                    } else {
                        // if the counter is odd assign value of 1 to white
                        value [i][j] = opposite;
                    }
                } 
                else {
                    // Assigning temporary value of 0 to the empty boxes
                    value [i][j] = absent;
                }
            }
        }
        
        // Add one to counter to switch the value of the pieces for the opponent
        valueCounter ++;
        // Add one to the moves counter to count the moves in the game
        moves ++;
    }
    
    /* Method to check if the move is valid by 
    calculating the sum and if there is an empty spot*/
    public void checkValid (int i, int j){
        // Run while the boolean play is false
        if (play == false){
            // Adding value of button to sum array
            sum += (value[i][j]);

            /* if the sum becomes odd make boolean play true and assign 
             value to newX as to where it stops */
            if (sum%2 == 1){
                play = true;
                newX = j;
                newY = i;
            }
        }

        // If the sum is even check whether there is an empty spot in the row
        if (sum%2 == 0){
            if (empty == false){
                if (value[i][j] == 0){
                    empty = true;
                }
            }
        }
    }
    
    // Method to change the boxes of a valid move
    public void changePiece (int i, int j){
        // Assigning colour to box depending on the value of the counter
        if (valueCounter%2==0){
            board.boxes[i][j].setIcon(black);
        } else {
            board.boxes[i][j].setIcon(white);
        }
    }
    
    // Method to reset values of play, empty and sum
    public void reset (){
        // Reset play, empty and sum
        play = false;
        empty = false;
        sum = 0;
    }
    
    // Method to check button one to the right of where the user clicks
    public void checkRight (){
        // Making sure that the button clicked is empty
        if (value [positionY] [positionX] == 0){
            
            // Checking right as long as the position is not on the right border
            if (positionX<7){
                
                // If the value of the button to the right is opposite as the current one, run the code
                if (value [positionY] [positionX+1] == opposite ){
                    
                    // for loop that goes through the entire row
                    for (int i=positionY; i<positionY+1; i++){
                    // for loop that checks from the X position of the button clicked up to the end of the row
                        for (int j=positionX+1; j<8; j++){
                            // Calling on method to check if move is valid
                            checkValid(i, j);
                        }
                    }
                    
                    // Changing the colours only if it's possible to play and there is no empty spot
                    if (play == true && empty == false){
                    
                    // Adding value of sum to the positionSum
                    positionSum += sum;
                        
                        // Running only if variable go is false
                        if (go==false){
                            for (int i=positionY; i<positionY+1; i++){
                                for (int j=positionX+1; j<newX; j++){
                                    // Calling on method to change the pieces of the valid move
                                    changePiece(i, j);
                                }
                            }
                        }
                        
                        // Adding one to run counter if the move is valid
                        run ++;
                    }
                }
            }
        }
        
        // Calling on method to reset play, empty and sum
        reset();
    }
    
    // Method to check button one to the left of where the user clicks
    public void checkLeft (){
        // Making sure the button clicked is empty
        if (value [positionY] [positionX] == 0){
            
            // Checking left as long as the position is not on the left border
            if (positionX>0){

                // If the value of the button to the left is opposite as the current one, run the code
                if (value [positionY] [positionX-1] == opposite){
                
                    // For loop to check all buttons to the left after the one clicked
                    for (int i=positionY; i<positionY+1; i++){
                        for (int j=positionX-1; j>=0; j--){
                            // Calling on method to check if move is valid
                            checkValid(i, j);
                        }
                    }

                    // Changing the colours only if it's possible to play and there is no empty spot
                    if (play == true && empty == false){
                    
                    // Adding value of sum to the positionSum
                    positionSum += sum;
                        
                        // Running only if variable go is false
                        if (go==false){
                            for (int i=positionY; i<positionY+1; i++){
                                for (int j=positionX-1; j>newX; j--){
                                    // Calling on method to change the pieces of the valid move
                                    changePiece(i, j);
                                }
                            }
                        }
                        
                        // Adding one to run counter if the move is valid
                        run ++;
                    }
                }
            }
        }
        
        // Calling on method to reset play, empty and sum
        reset();
    }
    
    // Method to check button one above of where the user clicks    
    public void checkUp (){
        // Making sure the button clicked is empty
        if (value [positionY] [positionX] == 0){
            // Checking yp as long as the position is not on the top border
            if (positionY>0){

                // If the value of the button above is opposite as the current one, run the code
                if (value [positionY-1] [positionX] == opposite){
                
                    // For loop to check all buttons to the right after the one clicked
                    for (int i=positionY-1; i>=0; i--){
                        for (int j=positionX; j<positionX+1; j++){
                            // Calling on method to check if move is valid
                            checkValid(i, j);
                        }
                    }
                    
                    // Changing the colours only if it's possible to play and there is no empty spot
                    if (play == true && empty == false){
                    
                    // Adding value of sum to the positionSum
                    positionSum += sum;
                        
                        // Running only if variable go is false
                        if (go==false){
                            for (int i=positionY-1; i>=newY; i--){
                                for (int j=positionX; j<positionX+1; j++){
                                // Calling on method to change the pieces of the valid move
                                changePiece(i, j);
                                }
                            }
                        }
                        
                        // Adding one to run counter if the move is valid
                        run ++;
                    }
                }
            }
        }
        
        // Calling on method to reset play, empty and sum
        reset();
    }
    
    // Method to check button one below of where the user clicks
    public void checkDown (){
        // Making sure the button clicked is empty
        if (value [positionY] [positionX] == 0){
            
            // Checking down as long as the position is not on the bottom border
            if (positionY<7){

                // If the value of the button below is opposite as the current one, run the code
                if (value [positionY+1] [positionX] == opposite){

                    // For loop to check all buttons to the right after the one clicked
                    for (int i=positionY+1; i<8; i++){
                        for (int j=positionX; j<positionX+1; j++){
                            // Calling on method to check if move is valid
                            checkValid(i, j);
                        }
                    }
                    
                    // Changing the colours only if it's possible to play and there is no empty spot
                    if (play == true && empty == false){
                    
                    // Adding value of sum to the positionSum
                    positionSum += sum;
                        
                        // Running only if variable go is false
                        if (go==false){
                            for (int i=positionY+1; i<newY; i++){
                                for (int j=positionX; j<positionX+1; j++){
                                    // Calling on method to change the pieces of the valid move
                                    changePiece(i, j);
                                }
                            }
                        }
                        
                        // Adding one to run counter if the move is valid
                        run ++;
                    }
                }
            }
        }
        
        // Calling on method to reset play, empty and sum
        reset();
    }
    
    // Method to check button one to the right and one down of where the user clicks
    // Diagonal Right Down (DRD)
    public void checkDRD (){
        // Making sure that the button clicked is empty
        if (value [positionY] [positionX] == 0){
            
            // Checking right and down as long as the position is not on the right or bottm border
            if (positionX<7 && positionY<7){
                
                // If the value of the button to the right and down is opposite as the current one, run the code
                if (value [positionY+1] [positionX+1] == opposite){
                    
                    // Saving value of positionX in tempX
                    tempX = positionX;
                    
                    // nested for loop that goes through the entire diagonal right up row
                    for (int i=positionY+1; i<8; i++){
                        
                        // Check variable to make sure the for loop doesn't go past its boundries
                        check = 0;
                        
                        // If positionX + 2 is past the for loop boundries set it to check
                        if (positionX+2 < 8) {
                            check = positionX+2;
                        }
                        
                        if (positionX<7){
                            int j = positionX+1;
                            // Calling on method to check if move is valid
                            checkValid(i, j);
                        }
                        
                        positionX++;
                    }
                    
                    // Resetting value of positionX for next checks
                    positionX = tempX;
                    
                    // Changing the colours only if it's possible to play and there is no empty spot
                    if (play == true && empty == false){
                    
                    // Adding value of sum to the positionSum
                    positionSum += sum;
                        
                        // Running only if variable go is false
                        if (go==false){
                            for (int i=positionY+1; i<newY; i++){
                                for (int j=positionX+1; j<positionX+2; j++){
                                    // Calling on method to change the pieces of the valid move
                                    changePiece(i, j);
                                }
                                
                                // Adding one to positionX to make the foor loop go diagonally
                                positionX++;
                            }
                        
                            // Resetting value of positionX for next checks
                            positionX = tempX;
                        }
                        
                        // Adding one to run counter if the move is valid
                        run ++;
                    }
                }
            }
        }
        
        // Calling on method to reset play, empty and sum
        reset();
    }
    
    // Method to check button one to the right and one up of where the user clicks
    // Diagonal Right up (DRU)
    public void checkDRU (){
        // Making sure that the button clicked is empty
        if (value [positionY] [positionX] == 0){
            
            // Checking down and right only if the position is not on the right border
            if (positionX<7 && positionY>0){
                
                // If the value of the button to the right and up is opposite as the current one, run the code
                if (value [positionY-1] [positionX+1] == opposite){
                    
                    // Saving value of positionX in tempX
                    tempX = positionX;
                    
                    for (int i=positionY-1; i>=0; i--){
                        
                        // Check variable to make sure the for loop doesn't go past its boundries
                        check = 0;
                        
                        if(positionX+2 < 8) {
                            check = positionX+2;
                        }
                        
                         if (positionX<7){
                            int j = positionX+1;
                            // Calling on method to check if move is valid
                            checkValid(i, j);
                        }
                        
                        // Add one to position X
                        positionX++;
                    }
                    
                    // Set the temp variable to positionX
                    positionX = tempX;
                    
                    // Changing the colours only if it's possible to play and there is no empty spot
                    if (play == true && empty == false){
                    
                    // Adding value of sum to the positionSum
                    positionSum += sum;
                        
                        // Running only if variable go is false
                        if (go==false){
                        
                            for (int i=positionY-1; i>newY; i--){
                                for (int j=positionX+1; j<positionX+2; j++){
                                    // Calling on method to change the pieces of the valid move
                                    changePiece(i, j);
                                }
                                
                                // Adding one to positionX to make the foor loop go diagonally
                                positionX++;
                            }
                        
                            // Resetting value of positionX for next checks
                            positionX = tempX;
                        }
                        
                        // Adding one to run counter if the move is valid
                        run ++;
                    }
                }
            }
        }
        
        // Calling on method to reset play, empty and sum
        reset();
    }
    
    // Method to check button one to the left and one up of where the user clicks
    // Diagonal Left Up (DLU)
    public void checkDLU (){
        // Making sure that the button clicked is empty
        if (value [positionY] [positionX] == 0){
            
            if (positionX>0 && positionY>0){
                
                // If the value of the button to the left and up is opposite as the current one, run the code
                if (value [positionY-1] [positionX-1] == opposite){
                    
                    tempX = positionX;
                    
                    // nested for loop that goes through the entire diagonal right up row
                    for (int i=positionY-1; i>=0; i--){
                        
                        check = 0;
                        
                        if(positionX-2 > 0) {
                            check = positionX-2;
                        }
                        
                         if (positionX>0){
                            int j = positionX-1;
                            // Calling on method to check if move is valid
                            checkValid(i, j);
                        }
                        
                        positionX--;
                    }
                    
                    positionX = tempX;
                    
                    // Changing the colours only if it's possible to play and there is no empty spot
                    if (play == true && empty == false){
                    
                    // Adding value of sum to the positionSum
                    positionSum += sum;
                        
                        if (go==false){
                        
                            for (int i=positionY-1; i>newY; i--){
                                for (int j=positionX-1; j>positionX-2; j--){
                                    // Calling on method to change the pieces of the valid move
                                    changePiece(i, j);
                                }
                                
                                // Subtracting one to positionX to make the foor loop go diagonally
                                positionX--;
                            }
                        
                            // Resetting value of positionX for next checks
                            positionX = tempX;
                        }
                        
                        // Adding one to run counter if the move is valid
                        run ++;
                    }
                }
            }
        }
        
        // Calling on method to reset play, empty and sum
        reset();
    }
    
    // Method to check button one to the left and one down of where the user clicks
    // Diagonal Left Down (DLD)
    public void checkDLD (){
        // Making sure that the button clicked is empty
        if (value [positionY] [positionX] == 0){
            
            if (positionX>0 && positionY<7){
                
                if (value [positionY+1] [positionX-1] == opposite){
                    
                    tempX = positionX;
                    
                    // nested for loop that goes through the entire diagonal right up row
                    for (int i=positionY+1; i<8; i++){
                        
                        check = 0;
                        
                        if(positionX-2 > 0) {
                            check = positionX-2;
                        }
                            
                        if (positionX>0){
                            int j = positionX-1;
                            // Calling on method to check if move is valid
                            checkValid(i, j);
                        }
                        
                        positionX--;
                    }
                    
                    positionX = tempX;
                    
                    // Changing the colours only if it's possible to play and there is no empty spot
                    if (play == true && empty == false){
                    
                    // Adding value of sum to the positionSum
                    positionSum += sum;
                        
                        if (go==false){
                            
                            for (int i=positionY+1; i<newY; i++){
                                for (int j=positionX-1; j>positionX-2; j--){
                                    // Calling on method to change the pieces of the valid move
                                    changePiece(i, j);
                                }
                                
                                // Subtracting one to positionX to make the foor loop go diagonally
                                positionX--;
                            }
                        
                            // Resetting value of positionX for next checks
                            positionX = tempX;
                        }
                        
                        // Adding one to run counter if the move is valid
                        run ++;
                    }
                }
            }
        }
        
        // Calling on method to reset play, empty and sum
        reset();
    }
    
    // Method to allow the user declare no more available moves
    public void noMoves(){
        // Set go variable to true to disable the changePiece method in the checks
        go = true;
        
        // Resetting run to check if there are any other possible moves
        run = 0;
        
        // Running throughout the whole board to see if there are available moves
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                // Giving new values to positionX and positionY
                positionX = i;
                positionY = j;
                
                // Running through all the checks
                checkRight();
                checkLeft();
                checkUp();
                checkDown();
                checkDRD();
                checkDLU();
                checkDRU();
                checkDLD();
            }
        }
                
        // If there are no moves available for the next player, the game is ended
        if (run==0){
            JOptionPane.showMessageDialog(null, "There are no more available moves for you, GAME OVER");
            
            // Making noMoves button disabled
            board.noMoves.setEnabled(false);
            
            // Checking which winner it is based on the turn
            if (turnCounter%2==0){
                JOptionPane.showMessageDialog(null, "The winner is black");
            } else {
                JOptionPane.showMessageDialog(null, "The winner is white");
            }
            
            // Disabling game
            for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    board.boxes[i][j].setEnabled(false);
                }
            }
            // Disabling noMoves button
            board.noMoves.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "You still have at least an available move, keep looking!");
        }
        
        // Resetting variable
        go = false;
    }
    
    // Method to check for final win
    public void finalWin (){
        // Checking for win if all the tiles have been selected
        if (sumPieces==64){
            // Setting end to true so the invalidMove method won't run
            end = true;
            // Prompt the user that he won
            JOptionPane.showMessageDialog(null, "You have reached the end of the game");
            // Disabling noMoves button
            board.noMoves.setEnabled(false);
            // If there are more black tiles than white tiles, black has won
            if (blackPiece>whitePiece){
                JOptionPane.showMessageDialog(null, "The winner is black");
            } 
            // If the number of black and white tiles is the same, it will be a tie
            else if (blackPiece<whitePiece){
                JOptionPane.showMessageDialog(null, "The winner is white");          
            }
            // If there are more white tiles than black tiles, black has won
            else if (blackPiece==whitePiece){
                JOptionPane.showMessageDialog(null, "It's a tie");                 
            }
            
            // Disabling game
            for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    board.boxes[i][j].setEnabled(false);
                }
            }
            // Disabling noMoves button
            board.noMoves.setEnabled(false);
        }
    }
    
    // Method to prompt JPane when move is invalid
    public void invalidMove () {
        // Run only if the game isn't at the end
        if (end==false){
            
            // Set go variable to true to disable the changePiece method in the checks
            go = true;
            
            // If the counter is still at 0 for all the moves being impossible it means all the checks don't work
            if (run==0){
                JOptionPane.showMessageDialog(null, "This move is invalid");
                // Making click boolean true again to enable the user to click
                click = true;
                invalid = true;
            }

            // If the move is valid call the method to assign values
            else if (run!=0){
                assign();
                invalid = false;
            }

            // Resetting run to check if there are any other possible moves
            run = 0;

            // Running throughout the whole board to see which moves are valid
            for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    // Giving new values to positionX and positionY
                    positionX = i;
                    positionY = j;

                    // Running through all the checks
                    checkRight();
                    checkLeft();
                    checkUp();
                    checkDown();
                    checkDRD();
                    checkDLU();
                    checkDRU();
                    checkDLD();
                }
            }

            // If there are no moves available for the next player, the game is ended
            if (run==0){
                JOptionPane.showMessageDialog(null, "There are no more available moves");

                // Checking which winner it is based on the turn

                if (turnCounter%2==0){
                    JOptionPane.showMessageDialog(null, "The winner is black");
                } else {
                    JOptionPane.showMessageDialog(null, "The winner is white");
                }

                // Disabling game
                for (int i=0; i<8; i++){
                    for (int j=0; j<8; j++){
                        board.boxes[i][j].setEnabled(false);
                    }
                }
                // Disabling noMoves button
                board.noMoves.setEnabled(false);
            }

            // Setting go to false to enable the pieces to change
            go = false;
        }
    }
    
    // Method to count how many pieces white and black have and to check for a winner
    public void tally (){
        
        // Resetting counters
        blackPiece = 0;
        whitePiece = 0;
        sumPieces = 0;
        
        // Running through the board to check how many buttons are white and how many are black
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                
                // Adding one to whitePiece if button is white
                if (board.boxes[i][j].getIcon()==white){
                    whitePiece++;
                }
                // Adding one to blackPiecePiece if button is white
                else if (board.boxes[i][j].getIcon()==black){
                    blackPiece++;
                }
            }
        }
        // Showing scores on the board
        board.whiteP.setText("White Score: " + whitePiece);
        board.blackP.setText("Black Score: " + blackPiece);
        // Showing number of moves
        board.movesNumber.setText("Number of moves: " + moves);
        // Adding total of pieces on the board
        sumPieces = whitePiece+blackPiece;
    }
    
    // Method to run the AI
    public void AI (){
        
        // Setting go to true to disable the checks from changing the pieces
        go = true;
        
        // Running only if the previous move was a valid one and it's a 1 player game
        if (invalid==false && onePlayer==true){
            
            // Resetting run to check if there are any other possible moves
            run = 0;

            // Running throughout the whole board to see which moves are valid
            for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    // Giving new values to positionX and positionY
                    positionX = i;
                    positionY = j;

                    // Running through all the checks
                    checkRight();
                    checkLeft();
                    checkUp();
                    checkDown();
                    checkDRD();
                    checkDLU();
                    checkDRU();
                    checkDLD();
                    
                    // Run only if the move is possible
                    if (run!=0){
                        // Adding to sumAI array the total sum for that position
                        sumAI[i][j] = positionSum;
                        // Resetting positionSum to calculate sum at next positions
                        positionSum = 0;
                        // Resetting run to check if there are any other possible moves
                        run = 0;
                    }
                }
            }
            
            // Running through sumAI to check which move has the best possible outcome
            for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    
                    // If the player chooses to play the hard mode check for highest sum
                    if (easy==false){
                        // Check if the positionSum is greater than the previously set maxSum
                        if (sumAI[i][j]>maxSum[i][j]) {
                            // Set the maxSum to the higher value in the array
                            maxSum[i][j] = sumAI[i][j];
                            // Set the positions to the i and j position of maxSum
                            positionX = i;
                            positionY = j;
                        }
                    } 
                    // If the player chooses to play the easy mode check for lowest sum
                    else if (easy==true){
                        // Check if the positionSum is smaller than the previously set minSum and the move is a valid one
                        if (sumAI[i][j]<minSum && sumAI[i][j]!=0) {
                            // Set the minSum to the lower value in the array
                            minSum = sumAI[i][j];
                            // Set the positions to the i and j position of minSum
                            positionX = i;
                            positionY = j;
                        }
                    }
                }
            }

            // Resetting go boolean to false to enable the checks to change the colours of the pieces
            go = false;
            checkRight();
            checkLeft();
            checkUp();
            checkDown();
            checkDRD();
            checkDLU();
            checkDRU();
            checkDLD();
            
            // Calling method to place black piece and assign values
            place();
            assign();
            
            // Resetting run counter for further clicks
            run = 0;
        }
        // Calling method to count the score of the two players and check for final win
        tally();
        finalWin();
        
        // Resetting variables for next moves
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                sumAI[i][j] = 0;
                maxSum[i][j] = 0;
            }
        }
        
        // Resetting value of minSum for further clicks
        minSum = 100;
        // Making click boolean true again to enable the user to click
        click = true;
    }
    
    // Making the program respond to clicks
    public void actionPerformed (ActionEvent event){
        String command = event.getActionCommand();
        
        // Deciding which player the user wants to be
        if (command.equals("Black")){
            // Setting color to black to show the user the button selected
            board.blackB.setBackground(Color.BLACK);
            board.turn.setText(" The turn is: black");
            // Adding 1 to counters to make the game start with a black turn
            turnCounter++;
            valueCounter++;
            board.blackB.setEnabled(false);
            board.whiteB.setEnabled(false);
        } else if (command.equals("White")){
            board.whiteB.setBackground(Color.BLACK);
            board.turn.setText(" The turn is: white");
            board.blackB.setEnabled(false);
            board.whiteB.setEnabled(false);
        }
        
        // Deciding the level of difficulty for the player
        if (command.equals("Hard")){
            board.hard.setBackground(Color.BLACK);
            board.hard.setEnabled(false);
            board.easy.setEnabled(false);
        } else if (command.equals("Easy")){
            board.easy.setBackground(Color.BLACK);
            board.hard.setEnabled(false);
            board.easy.setEnabled(false);
            // Setting easy variable to true to act on the AI code
            easy = true;
        }
        
        // Deciding whether the user wants to 1 player or 2 player game
        if (command.equals("1 Player")){
            // Retreiving names from the user
            user1 = (board.user1.getText());
            user2 = "The Computer";
            board.player1.setBackground(Color.BLACK);
            // Setting onePlayer variable to true to make the AI run
            onePlayer = true;
            board.player1.setEnabled(false);
            board.player2.setEnabled(false);
        } else if (command.equals("2 Players")){
            // Retreiving names from the users
            user1 = (board.user1.getText());
            user2 = (board.user2.getText());
            board.player2.setBackground(Color.BLACK);
            board.user2.setEnabled(true);
            board.player1.setEnabled(false);
            board.player2.setEnabled(false);
            board.hard.setEnabled(false);
            board.easy.setEnabled(false);
            board.user2.setEnabled(true);
        }
        
        // Button to restart game
        if (command.equals("Restart")){
            // Going back to first frame
            board.frame1.setVisible(true);
            board.frame2.setVisible(false);
            
            // Resetting buttons
            board.hard.setEnabled(true);
            board.hard.setBackground(Color.WHITE);
            board.easy.setEnabled(true);
            board.easy.setBackground(Color.WHITE);
            board.player1.setEnabled(true);
            board.player1.setBackground(Color.WHITE);
            board.player2.setEnabled(true);
            board.player2.setBackground(Color.WHITE);
            board.blackB.setEnabled(true);
            board.blackB.setBackground(Color.WHITE);
            board.whiteB.setEnabled(true);
            board.whiteB.setBackground(Color.WHITE);
            board.play.setEnabled(true);
            board.play.setBackground(Color.WHITE);
            board.user2.setEnabled(false);
            board.noMoves.setEnabled(true);
            // Resetting booleans
            onePlayer = false;
            end = false;
            // Resetting text
            board.whiteP.setText("White Score: ");
            board.blackP.setText("Black Score: ");
            board.movesNumber.setText("Number of moves: ");
            // Clearing inputs
            board.user1.setText("");
            board.user2.setText("");
            // Resetting game counters
            valueCounter = 0;
            turnCounter = 0;
            moves = 0;
            
            // Resetting board
            for (int i=0; i<8; i++){
                for (int j=0; j<8; j++){
                    board.boxes[i][j].setIcon(null);
                    board.boxes[i][j].setEnabled(true);
                }
            }       
        }
        
        // Showing instructions to user
        if (command.equals("Instructions")){
            board.frame1.setVisible(false);
            board.frame3.setVisible(true);
        }
        
        // Going back from instructions panel to main menu
        if (command.equals("Back")){
            board.frame1.setVisible(true);
            board.frame3.setVisible(false);
        }
        
        // Exiting program on click
        if (command.equals("Exit")){
            System.exit(0);
        }
        
        // Calling method for the user to see if he has anymore moves left
        if (command.equals("No Moves")){
            noMoves();
        }
        
        // Call procedures when play button is pressed
        if (command.equals("Play")){
            
            // Running only if all methods are chosen
            if ((board.player1.isEnabled()==false) && (board.easy.isEnabled()==false) && (board.whiteB.isEnabled()==false)){
                
                // Assigning a value to the variables to display on the board based on what the user entered
                if (board.player1.getBackground()==Color.BLACK){
                   user1 = (board.user1.getText());
                   user2 = "The Computer";
                } else if (board.player2.getBackground()==Color.BLACK){
                   user1 = (board.user1.getText());
                   user2 = (board.user2.getText());
                }
                
                /* Replacing the menu frame with the game frame by setting
                   the first to invisible and the second to visible
                */ 
                board.frame2.setVisible(true);
                board.frame1.setVisible(false);

                // Setting the text to display on the board
                board.names1.setText("  " + user1);
                board.names2.setText("           VS");
                board.names3.setText(user2);

                // Disable play button
                board.play.setEnabled(false);
                // Placing first pieces
                startPosition();
                // Giving values to starting pieces
                assign();
            }
            // Prompt user to select all game methods
            else {
                JOptionPane.showMessageDialog(null, "Please select all game modes");
            }
        }
        
        
        // Running through the whole board to verify where the user clicks and run the proper checks
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                
                // Running if the AI is selected
                if (onePlayer==true){
                    // If the AI is selected boolean click has to be true
                    if (click==true){
                        
                         if (command.equals(String.valueOf(j)+String.valueOf(i))){
                            positionX = i;
                            positionY = j;

                            // Running checks
                            checkRight();
                            checkLeft();
                            checkUp();
                            checkDown();
                            checkDRD();
                            checkDLU();
                            checkDRU();
                            checkDLD();
                            place();

                            // Setting click boolean to false to disable the user to click again
                            click = false;

                            // Methods to check for invalid move and add up score
                            tally();
                            invalidMove();

                            // Running timer only if the game mode is 1 player and the user's move is valid
                            if (invalid==false && onePlayer==true){
                                // Starting timer to run AI with a 1 second delay
                                timer.restart();
                            }

                            // Resetting variables
                            go = false;
                            run = 0;
                            invalid = false;
                        }
                    }
                }
                
                // Running if the AI is not selected
                else {
                    if (command.equals(String.valueOf(j)+String.valueOf(i))){
                        positionX = i;
                        positionY = j;

                        // Running checks
                        checkRight();
                        checkLeft();
                        checkUp();
                        checkDown();
                        checkDRD();
                        checkDLU();
                        checkDRU();
                        checkDLD();
                        place();
                        
                        // Methods to check for invalid move and add up score
                        tally();
                        finalWin();
                        invalidMove();

                        // Resetting variables
                        go = false;
                        run = 0;
                        invalid = false;
                    }
                }
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}