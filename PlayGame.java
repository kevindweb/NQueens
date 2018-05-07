import java.util.*;

public class PlayGame{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int boardSize = getM(sc);
        int numQueens = getN(sc, boardSize);
        GameBoard board = new GameBoard(boardSize);
        queens(numQueens, board, 1);
        // Queen newQueen = new Queen(2, 1);
        // board.setQueen(newQueen);
        // board.printSpacesHit();
        // System.out.println("BOARD: ");
        board.printBoard();
        // board.printSpacesHit();
    }
    public static void queens(int num, GameBoard board, int row){
        if(num < 1){
            return;
        }
        int width = board.width;
        for(int i=1; i <= width; i++){
            if(board.validSpace(row, i) == 0){
                Queen curr = new Queen(row, i);
                board.setQueen(curr);
                break;
            }
        }
        queens(num - 1, board, row + 1);
        // this is recursive call to next row to set queen
    }
    public static int getN(Scanner sc, int board){
        int num;
        System.out.println("How many queens would you like?");
        num = sc.nextInt();
        while(num < 1 || num > board){
            // invalid input
            if(num < 1){
                System.out.println("You cannot have less than 1 queens!");
            } else{
                System.out.println("You cannot have more queens than the number of rows!");
            }
            System.out.println("How many queens would you like?");
            num = sc.nextInt();
        }
        return num;
    }
    public static int getM(Scanner sc){
        int num;
        System.out.println("How big do you want the board?(ex: 9) ");
        num = sc.nextInt();
        while(num < 1){
            // invalid input
            System.out.println("You cannot have less than 1 row!");
            System.out.println("How big do you want the board?(ex: 9) ");
            num = sc.nextInt();
        }
        return num;
    }
}
