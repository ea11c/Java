/**
 Eric Adams
 TicTacToe
 */
public class TicTacToe {
    public static void main(String[] args){ //initialize characters and main game loop
        Player playerX = new Player(-1, false);
        Player playerO = new Player(1, false);
        int usedSpaces = 0; //counts number of spaces used, game is a tie if usedSpaces == 9
        //check command line args and initialize players accordingly
        if(args.length >= 3){
            //command line syntax error, too many arguments
            System.out.println("Usage: java TicTacToe [-c [1|2]]");
            System.exit(0);
        }
        if(args.length == 0){
            // 2 human players
            playerX.human = true;
            playerO.human = true;
        }
        else if(args[0].equals("-c")){ //there is at least 1 computer
            if(args.length == 1){
                //2 computer players
                playerO.human = false;
                playerX.human = false;
            }
            else if(args[1].equals("1")){
                // 1st player is computer 2nd is human
                playerX.human = false;
                playerO.human = true;
            }
            else if(args[1].equals("2")){
                // 1st player human 2nd computer
                playerX.human = true;
                playerO.human = false;
            }
            else{
                //command line syntax error
                System.out.println("Usage: java TicTacToe [-c [1|2]]");
                System.exit(0);
            }
        }
        else{
            //command line error, print java TicTacToe [-c [1|2]]
            System.out.println("Usage: java TicTacToe [-c [1|2]]");
            return;
        }

        //initialize board
        Board game = new Board();
        int move;
        boolean loopControl;  //used to control entry/exit from try/catch block loops
        //main game loop
        while(true){
            move = playerX.MakeMove(game, playerO);
            usedSpaces++;
            System.out.println("Player 1 chooses space " + move);
            if(game.CheckWin(playerX, move)){
                game.PrintBoard();
                System.out.print("Player 1 wins!\n");
                return;
            }
            else if(usedSpaces == 9){
                game.PrintBoard();
                System.out.print("Tie game!\n");
                return;
            }
            move = playerO.MakeMove(game, playerX);
            usedSpaces++;
            System.out.println("Player 2 chooses space " + move);
            if(game.CheckWin(playerO, move)){
                game.PrintBoard();
                System.out.print("Player 2 wins!\n");
                return;
            }
            else if(usedSpaces == 9){
                game.PrintBoard();
                System.out.print("Tie game!\n");
                return;
            }
        }
    }
}
