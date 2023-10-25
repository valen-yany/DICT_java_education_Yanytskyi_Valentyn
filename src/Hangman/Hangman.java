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
        int lives = 8;
        String answer = choice(words_list);
        String word = answer;
        String word_output = "-".repeat(answer.length());
        while(lives > 0){
            System.out.printf("\n%s\nInput a letter: ", word_output);
            String player_input = input();
            if (word_output.contains(player_input)){
                System.out.println("No improvements");
                lives--;
            }
            else if(word.contains(player_input)){
                while(word.contains(player_input)){
                    int index = word.indexOf(player_input);
                    word = word.substring(0, index) + "-" + word.substring(index + 1);
                    word_output = word_output.substring(0, index) + player_input + word_output.substring(index + 1);
                }
            }
            else{
                System.out.println("That letter doesn`t appear in the word");
                lives--;
            }
            if(answer.equals(word_output)){
                System.out.printf("\n%s\nYou guessed the word!\nYou survived!", answer);
                return;
            }

        }
        System.out.println("You lost!");
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
