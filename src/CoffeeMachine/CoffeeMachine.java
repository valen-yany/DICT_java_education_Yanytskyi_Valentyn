package CoffeeMachine;
import java.util.Arrays;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int water = 400;
        int milk = 540;
        int beans = 120;
        int cups = 9;
        int money = 550;
        int[] ingredients = new int[] {water, milk, beans, cups, money};
        printIngredients(ingredients);
        String input = "";
        while(!input.equals("buy") && !input.equals("fill") && !input.equals("take")) {
            System.out.println("\nWrite action(buy, fill, take):");
            input = in.nextLine();
        }
        if(input.equals("buy")){
            int coffee_type = 0;
            while (coffee_type < 1 || coffee_type > 3 ){
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                coffee_type = in.nextInt();
            }
            makeCoffee(ingredients, coffee_type);
        }
        else if(input.equals("fill")){
            fillMachine(ingredients);
        }
        else {
            takeMoney(ingredients);
        }
        printIngredients(ingredients);

    }

    public static void printIngredients(int[] ingredients){
        System.out.printf("""
                
                The coffee machine has:
                %d of water
                %d of milk
                %d of coffee beans
                %d of disposable cups
                %d of money\n""", ingredients[0], ingredients[1], ingredients[2], ingredients[3], ingredients[4]);
    }

    public static void makeCoffee(int[] ingredients, int type){
        if(type == 1){
            ingredients[0] -= 250;
            ingredients[2] -= 16;
            ingredients[4] += 4;
        }
        else if(type == 2){
            ingredients[0] -= 350;
            ingredients[1] -= 75;
            ingredients[2] -= 20;
            ingredients[4] += 7;
        }
        else if(type == 3){
            ingredients[0] -= 200;
            ingredients[1] -= 100;
            ingredients[2] -= 12;
            ingredients[4] += 6;
        }
        ingredients[3] -= 1;
    }

    public static void fillMachine(int[] ingredients){
        Scanner i = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        ingredients[0] += i.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        ingredients[1] += i.nextInt();
        System.out.println("Write how many grams of coffee you want to add:");
        ingredients[2] += i.nextInt();
        System.out.println("Write how many disposable coffee cups you want to add:");
        ingredients[3] += i.nextInt();
    }

    public static void takeMoney(int[] ingredients){
        System.out.printf("I gave you %d", ingredients[4]);
        ingredients[4] = 0;
    }

}
