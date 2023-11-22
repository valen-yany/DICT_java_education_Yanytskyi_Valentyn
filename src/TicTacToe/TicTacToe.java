package TicTacToe;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args){
        Board b = new Board();
        b.PrintGrid();
    }


}

class Board{
    private final char[][] grid = new char[3][3];
    public Board(){
        String field = "";
        while (field.length() != 9 || !field.matches(".*[XO_]")){
            System.out.print("Enter cells: ");
            Scanner in = new Scanner(System.in);
            field = in.next();
        }
        SetGrid(field.toCharArray());
    }

    private void SetGrid(char[] field){
        for(int i=0;i<9;i++){
            grid[i/3][i%3] = field[i];
        }
    }
    
    public void PrintGrid(){
        System.out.println("-----");
        for (char[] line:grid) {
            System.out.print("|");
            for(char cell:line){
                System.out.print(cell);
            }
            System.out.println("|");
        }
        System.out.println("-----");
    }
}
