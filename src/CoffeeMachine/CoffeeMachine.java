package CoffeeMachine;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Write how many cups of coffee you will need:");
        int cups = in.nextInt();
        getIngredients(cups);
    }

    public static void getIngredients(int cups){
        System.out.printf("For %d cups of coffee you will need:\n%d ml of water\n%d ml of milk\n%d g of coffee beans", cups, cups * 200, cups * 50, cups*15);
    }
}
