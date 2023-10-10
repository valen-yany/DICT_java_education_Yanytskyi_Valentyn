package ChatBot;
import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String year = "2023";
        String botName = "Artem";
        System.out.printf("Hello, my name is %s.\nI was created in %s.\nPlease, remind me your name.\n", botName, year);
        String userName = in.nextLine();
        System.out.printf("What a great name you have, %s!\nLet me guess your age.\nEnter remainders of dividing your age by 3, 5 and 7.\n", userName);
        int remainder3 = in.nextInt();
        int remainder5 = in.nextInt();
        int remainder7 = in.nextInt();
        int age = (remainder3 * 70 + remainder5 * 21 + remainder7 * 15) % 105;
        System.out.printf("Your age is %d; that's a good time to start programming!\nNow I will prove to you that I can count to any number you want!\n", age);
        int border = in.nextInt();
        for(int i = 0; i <= border;i++){
            System.out.printf("%d !\n", i);
        }
    }
}
