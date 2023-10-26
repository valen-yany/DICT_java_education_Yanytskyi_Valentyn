package Hangman;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Hangman {
    public static void main(String[] args){
        String[] words_list = {"java", "kotlin", "python", "javascript"};
        hangman(words_list);
    }

    private static void hangman(String[] words_list){
        System.out.println("HANGMAN");
        while(true){
            String status = null;
            while (!"play".equals(status)){
                System.out.print("Type \"play\" to play the game, \"exit\" to quit:");
                status = input();
                if("exit".equals(status)){
                    return;
                }
            }
            int lives = 8;
            ArrayList<String> guessed_letters = new ArrayList<>();
            String answer = choice(words_list);
            String word = answer;
            String word_output = "-".repeat(answer.length());
            while(lives > 0){
                System.out.printf("\n%s\nInput a letter: ", word_output);
                String player_input = input();
                if (player_input.length() > 1){
                    System.out.println("You should input a single letter");
                    continue;
                }
                if (!player_input.matches("[a-z]")){
                    System.out.println("Please enter a lowercase English letter.");
                    continue;
                }
                if (guessed_letters.contains(player_input)){
                    System.out.println("You`ve already guessed this letter");
                }
                else if(word.contains(player_input)){
                    while(word.contains(player_input)){
                        int index = word.indexOf(player_input);
                        word = word.substring(0, index) + "-" + word.substring(index + 1);
                        word_output = word_output.substring(0, index) + player_input + word_output.substring(index + 1);
                    }
                    guessed_letters.add(player_input);
                }
                else{
                    System.out.println("That letter doesn`t appear in the word");
                    lives--;
                    guessed_letters.add(player_input);
                }

                if(answer.equals(word_output)){
                    System.out.printf("\n%s\nYou guessed the word!\nYou survived!\n", answer);
                    break;
                }
            }
            if (lives == 0) {
                System.out.println("You lost!");
            }
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
