package Hangman;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args){
        System.out.println("HANGMAN");
        hangman("java");
    }

    private static void hangman(String answer){
        System.out.print("Guess the world: ");
        String player_input = input();
        if (player_input.equals(answer)){
            System.out.println("You survived!");
        }
        else{
            System.out.println("You lost!");
        }
    }

    private static String input(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

}
