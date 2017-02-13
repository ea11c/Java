import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 Eric Adams
 TicTacToe - Player
 */
public class Player {
    boolean human;
    int order = 0; //-1 = X and 1 = O
    Player(int XorO, boolean HorC){
        order = XorO;
        human = HorC;
    }
    int MakeMove(Board game, Player other){
        int index = -1;
        boolean loopControl;
        game.PrintBoard();
        Scanner input = new Scanner(System.in);
        if(this.human){
            if(this.order == -1) {
                System.out.print("Player 1 please enter a move (1-9): ");
            }
            else{
                System.out.print("Player 2 please enter a move (1-9): ");
            }
            loopControl = true;
            while(loopControl) {
                try {
                    index = input.nextInt();
                    if(index <= 9 && index >= 1){
                        if(!game.SpaceAvailable(index, this.order)) {
                            throw new InputMismatchException("must be a valid space");
                        }
                    }
                    else{
                        throw new InputMismatchException("must be a valid space");
                    }
                    loopControl = false;
                } catch (InputMismatchException e) {
                    //didn't enter an acceptable integer
                    loopControl = true;
                    System.out.println("Please input an integer between 1 & 9 that is an empty space.");
                    input.nextLine();
                }
            }
        }
        else{ //Player is a computer
            //handle checking for win, checking to prevent win, random spot selection
            Random rand = new Random();
            int indexCounter = 0;  //used to track which index to enter into unused Spaces array
            int[] unusedSpaces = {0,0,0,0,0,0,0,0,0};
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    if(game.gameBoard[i][j] == 0){
                        unusedSpaces[indexCounter] = (i * 3) + j + 1;
                        indexCounter++;
                    }
                }
            }
            for(int i = 0; i < indexCounter; i++){
               if(game.CheckWinPC(this, unusedSpaces[i])){ //check for win
                    index = unusedSpaces[i];
                    game.SpaceAvailable(index, this.order);
                    return index;
                }
                if(game.CheckWinPC(other, unusedSpaces[i])){  //check to prevent win
                    index = unusedSpaces[i];
                    game.SpaceAvailable(index, this.order);
                    return index;
                }
                if(unusedSpaces[i] == 5){  //check for middle space
                    index = 5;
                    game.SpaceAvailable(index, this.order);
                    return index;
                }
            }
            //above for loop failed to set index, randomly pick from available spaces
            int randomIndex = rand.nextInt(indexCounter);
            index = unusedSpaces[randomIndex];
            game.SpaceAvailable(index, this.order);
            return index;
        }
        return index;
    }
}
