import java.util.*;

public class GameBoard{
    private char[][] state;
    private int[][] spacesHit;
    public int height;
    public int width;
    public GameBoard(int num){
        state = new char[num][num];
        spacesHit = new int[num][num];
        width = num;
        height = num;
    }
    public boolean setQueen(Queen q){
        // return false if there is already someone there
        // or the row/col combination is outside bounds
        int row = q.getRow();
        int col = q.getCol();
        if(row > height || row < 1 || col > width || col < 1){
            System.out.println("Row or Column is out of bounds!");
            return false;
        } else if(state[row - 1][col - 1] != 0){
            System.out.println("Queen already present!");
            return false;
        } else if(spacesHit[row - 1][col - 1] == 1){
            System.out.println("Space is hit!");
            return false;
        }
        state[row - 1][col - 1] = 'Q';
        for(int i=0; i < height; i++){
            spacesHit[row - 1][i] = 1;
            spacesHit[i][col - 1] = 1;
        }
        // direction in the format of [row, column]
        int[] northeast = {row - 1, col + 1};
        // these are the row,col values of the diagonals adjacent to this queen...
        int[] northwest = {row - 1, col - 1};
        int[] southeast = {row + 1, col + 1};
        int[] southwest = {row + 1, col - 1};
        ArrayList<int[]> directions = new ArrayList<int[]>();
        directions.add(northeast);
        directions.add(northwest);
        directions.add(southeast);
        directions.add(southwest);
        int numDirections = 4;
        while(!directionsNull(directions)){
            for(int i=0; i < numDirections; i++){
                int[] curr = directions.get(i);
                if(curr == null){
                    continue;
                } else if(curr[0] < 1 || curr[0] > height || curr[1] < 1 || curr[1] > height){
                    // check if values are within bounds
                    directions.set(i, null);
                    // remove this diagonal
                    continue;
                } else{
                    spacesHit[curr[0] - 1][curr[1] - 1] = 1;
                    if(i == 0){
                        curr[0]--;
                        curr[1]++;
                    } else if(i == 1){
                        curr[0]--;
                        curr[1]--;
                    } else if(i == 2){
                        curr[0]++;
                        curr[1]++;
                    } else{
                        curr[0]++;
                        curr[1]--;
                    }
                    directions.set(i, curr);
                }
            }
        }
        return true;
    }
    public boolean directionsNull(ArrayList<int[]> directions){
        int lengthy = directions.size();
        for(int i = 0; i < lengthy; i++){
            if(directions.get(i) != null){
                return false;
            }
        }
        return true;
    }
    public void printList(ArrayList<int[]> list){
        for(int i=0; i < 4; i++){
            System.out.print("i: " + i + " arr: " + Arrays.toString(list.get(i)) + " ");
        }
        System.out.println();
    }
    public int validSpace(int row, int col){
        return spacesHit[row - 1][col - 1];
    }
    public void printBoard(){
        for(int i=0; i < height; i++){
            System.out.println(Arrays.toString(state[i]));
        }
    }
    public void printSpacesHit(){
        for(int i=0; i < height; i++){
            System.out.println(Arrays.toString(spacesHit[i]));
        }
    }
}
