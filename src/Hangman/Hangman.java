package Hangman;
import java.util.Scanner;
import java.util.Random;

public class Hangman {
    public static void main(String[] args){
        String[] words_list = {"java", "kotlin", "python", "javascript"};
        System.out.println("HANGMAN");
        hangman(words_list);
    }

    private static void hangman(String[] words_list){
        System.out.print("Guess the world: ");
        String answer = choice(words_list);
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

    private static String choice(String[] words){
        Random rnd = new Random();
        return words[rnd.nextInt(words.length)];
    }
}
