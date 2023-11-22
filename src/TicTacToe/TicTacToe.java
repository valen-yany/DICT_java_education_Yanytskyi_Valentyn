package TicTacToe;
import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args){
        Board b = new Board();
        Player p = new Player('X');
        b.PrintGrid();
        p.MakeMove(b);
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

    public void FillCell(int[] coords, char sym){
        grid[coords[0]][coords[1]] = sym;
    }

    public boolean isCellOccupied(int[] coords){
        return!(grid[coords[0]][coords[1]] == '_');
    }
}

class Player{
    char player_sym;
    public Player(char player_sym){
        this.player_sym = player_sym;
    }

    public char GetSymbol(){
        return player_sym;
    }

    public void MakeMove(Board board){
        Scanner in = new Scanner(System.in);
        outer:
        do {
            String[] coords;
            System.out.print("Enter cells: ");
            coords = in.nextLine().trim().split("\\s+");
            if (coords.length != 2){
                continue;
            }
            int[] num_coords = new int[2];
            for (int i = 0; i < 2; i++) {
                if(!coords[i].matches("[0-9]")){
                    System.out.println("You should enter numbers!");
                    continue outer;
                }
                num_coords[i] = Integer.parseInt(coords[i]) - 1;
                if (num_coords[i] < 0 || num_coords[i] > 2){
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue outer;
                }
            }
            if(board.isCellOccupied(num_coords)){
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            board.FillCell(num_coords, player_sym);
            break;
        }while(true);
    }
}
