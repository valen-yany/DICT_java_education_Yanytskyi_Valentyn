package TicTacToe;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args){
        Board b = new Board();
        b.PrintGrid();
        if(b.CheckImpossible()){
            System.out.println("Impossible");
        }
        else if(b.CheckWin('X')){
            System.out.println("X wins");
        }
        else if(b.CheckWin('O')){
            System.out.println("O wins");
        }
        else if(b.CheckEmpty()){
            System.out.println("Game not finished");
        }
        else{
            System.out.println("Draw");
        }
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

    public boolean CheckImpossible(){
        int x_grid = 0;
        int o_grid = 0;
        for(char[] line: grid){
            for(char cell: line){
                if(cell == 'X'){
                    x_grid++;
                }
                else if(cell == 'O'){
                    o_grid++;
                }
            }
        }
        return (Math.abs(x_grid - o_grid) >=2 || (CheckWin('X') && CheckWin('O')));
    }

    public boolean CheckWin(char player){
        char[] winLine = new char[] {player, player, player};
        return Arrays.equals(grid[0], winLine) || Arrays.equals(grid[1], winLine) || Arrays.equals(grid[2], winLine) || Arrays.equals(new char[]{grid[0][0], grid[1][0], grid[2][0]}, winLine) || Arrays.equals(new char[]{grid[0][1], grid[1][1], grid[2][1]}, winLine) || Arrays.equals(new char[]{grid[0][2], grid[1][2], grid[2][2]}, winLine) || Arrays.equals(new char[]{grid[0][0], grid[1][1], grid[2][2]}, winLine) || Arrays.equals(new char[]{grid[0][2], grid[1][1], grid[2][0]}, winLine);
    }

    public boolean CheckEmpty(){
        for(char[] line: grid){
            for(char cell: line){
                if(cell == '_'){
                    return true;
                }
            }
        }
        return false;
    }
}
