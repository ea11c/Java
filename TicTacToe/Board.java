/**
 * Created by eric on 2/6/2017.
 */

public class Board {
    int gameBoard [][];
    Board(){
        gameBoard = new int[][] {{0,0,0},{0,0,0},{0,0,0}};
        /*
        Creates a board with 9 spaces initalized with integers of 0, -1 will represent X's and 1 will represent O's

        Board indexes:
        1|2|3
        4|5|6
        7|8|9
        */
    }
    void PrintBoard(){
        System.out.print("Game Board:\t\tPositions:\n");
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" ");
                if(this.gameBoard[i][j] == 0){
                    System.out.print(" ");
                }
                else if(this.gameBoard[i][j] == -1){
                    System.out.print("X");
                }
                else{
                    System.out.print("O");
                }
                System.out.print(" ");
                if(j != 2){
                    System.out.print("|");
                }
                else{
                    System.out.print("\t\t");
                    if(i == 0){
                        System.out.print(" 1 | 2 | 3");
                    }
                    else if(i == 1){
                        System.out.print(" 4 | 5 | 6");
                    }
                    else{
                        System.out.print(" 7 | 8 | 9\n");
                    }
                }
            }
            if(i != 2){
                System.out.print("\n-----------\t\t-----------\n");
            }
        }
    }
    boolean CheckWin(Player check, int space){ //check has player info, and space is the most recently played space
        int index;
        if(space < 4){
            index = space - 1;
            if(gameBoard[0][0] == check.order && gameBoard[0][1] == check.order && gameBoard[0][2] == check.order){
                //horizontal win
                return true;
            }
            if(gameBoard[0][index] == check.order && gameBoard[1][index] == check.order && gameBoard[2][index] == check.order){
                //vertical win
                return true;
            }
            if(space == 1 || space == 3){
                //check for diagonal win
                if(gameBoard[1][1] != check.order){
                    //can't have diagonal win without middle square
                    return false;
                }
                else if((space == 1 && gameBoard[2][2] == check.order) || (space == 3 && gameBoard[2][0] == check.order)){
                    //diagonal win
                    return true;
                }
            }
        }
        else if(space < 7){
            index = space - 4;
            if(gameBoard[1][0] == check.order && gameBoard[1][1] == check.order && gameBoard[1][2] == check.order){
                //horizontal win
                return true;
            }
            if(gameBoard[0][index] == check.order && gameBoard[1][index] == check.order && gameBoard[2][index] == check.order){
                //vertical win
                return true;
            }
            if(space == 5){
                //check for diagonal win
                if(gameBoard[0][0] == check.order && gameBoard[2][2] == check.order){
                    return true;
                }
                else if(gameBoard[2][0] == check.order && gameBoard[0][2] == check.order){
                    return true;
                }
            }
        }
        else{
            index = space - 7;
            if(gameBoard[2][0] == check.order && gameBoard[2][1] == check.order && gameBoard[2][2] == check.order){
                //horizontal win
                return true;
            }
            if(gameBoard[0][index] == check.order && gameBoard[1][index] == check.order && gameBoard[2][index] == check.order){
                //vertical win
                return true;
            }
            if(space == 7 || space == 9){
                //check for diagonal win
                if(gameBoard[1][1] != check.order){
                    //can't have diagonal win without middle square
                    return false;
                }
                else if((space == 7 && gameBoard[0][2] == check.order) || (space == 9 && gameBoard[0][0] == check.order)){
                    //diagonal win
                    return true;
                }
            }
        }
        return false;
    }
    boolean SpaceAvailable(int space, int XorY){ //checks to see if the available space is a possible space to play
        if(space < 4){
            if(this.gameBoard[0][space - 1] == 0){
                this.gameBoard[0][space - 1] = XorY;
                return true;
            }
            else
                return false;
        }
        else if(space < 7){
            if(this.gameBoard[1][space - 4] == 0){
                this.gameBoard[1][space - 4] = XorY;
                return true;
            }
            else
                return false;
        }
        else{
            if(this.gameBoard[2][space - 7] == 0){
                this.gameBoard[2][space - 7] = XorY;
                return true;
            }
            else
                return false;
        }
    }
    boolean CheckWinPC(Player check, int space){ //check has player info, and space is the space to check against
        int row, column;
        int count = 0;
        if(space < 4) {
            column = space - 1;
            row = 0;
        }
        else if(space < 7) {
            column = space - 4;
            row = 1;
        }
        else {
            column = space - 7;
            row = 2;
        }
        for(int i = 0; i < 3; i++){
            if(gameBoard[row][i] == check.order){
                count++;
            }
        }
        if(count == 2){
            //horizontal win possible
            return true;
        }
        count = 0;
        for(int i = 0; i < 3; i++){
            if(gameBoard[i][column] == check.order){
                count++;
            }
        }
        if(count == 2){
            //vertical win possible
            return true;
        }
        if(space == 1 || space == 3 || space == 5 || space == 7 || space == 9){
            if(gameBoard[1][1] != check.order){
                return false;
            }
            else if(space == 1 || space == 9){
                if(gameBoard[0][0] == check.order || gameBoard[2][2] == check.order){
                    if(gameBoard[0][0] == 0 || gameBoard[2][2] == 0){
                        //diagonal win possible
                        return true;
                    }
                    return false;
                }
            }
            else if(space == 3 || space == 7){
                if(gameBoard[0][2] == check.order || gameBoard[2][0] == check.order){
                    if(gameBoard[0][2] == 0 || gameBoard[2][0] == 0){
                        //diagonal win possible
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }
}
