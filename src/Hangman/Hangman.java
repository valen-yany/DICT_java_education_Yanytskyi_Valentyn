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
        String answer = choice(words_list);
        String word_output = answer.substring(0,2) + "-".repeat(answer.length() - 2);
        System.out.printf("Guess the world %s: ", word_output);
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
        return in.nextLine().toLowerCase();
    }

    private static String choice(String[] words){
        Random rnd = new Random();
        return words[rnd.nextInt(words.length)];
    }
}
