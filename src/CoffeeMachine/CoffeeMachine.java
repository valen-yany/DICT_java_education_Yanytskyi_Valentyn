package CoffeeMachine;
import java.util.Arrays;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Write how many ml of water the coffee machine has:");
        int water = in.nextInt();
        System.out.println("Write how many ml of milk the coffee machine has:");
        int milk = in.nextInt();
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int beans = in.nextInt();

        int machine_cups = getCups(water, milk, beans);

        System.out.println("Write how many cups of coffee you will need");
        int needed_cups = in.nextInt();

        checkCups(machine_cups, needed_cups);
    }

    public static int getCups(int water, int milk, int beans){
        int[] ingredients = new int[] {water / 200, milk / 50, beans / 15};
        return Arrays.stream(ingredients).min().getAsInt();
    }

    public static void checkCups(int machine_cups, int needed_cups){
        if(machine_cups == needed_cups){
            System.out.println("Yes, I can make that amount of coffee");
        }
        else if (machine_cups > needed_cups){
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)", machine_cups - needed_cups);
        }
        else{
            System.out.printf("No, I can make only %d cups of coffee", machine_cups);
        }
    }
}
