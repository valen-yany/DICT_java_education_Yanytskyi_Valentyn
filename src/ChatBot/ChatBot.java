package ChatBot;
import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String year = "2023";
        String botName = "Artem";
        System.out.printf("Hello, my name is %s.\nI was created in %s.\nPlease, remind me your name.\n", botName, year);
        String userName = in.nextLine();
        System.out.printf("What a great name you have, %s!", userName);
    }
}
