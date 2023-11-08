package CoffeeMachine;
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
        String action = "";
        while(true){
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
            action = in.next();
            if(action.equals("buy")){
                String coffee_type = "";
                while (!coffee_type.equals("1") && !coffee_type.equals("2") && !coffee_type.equals("3") && !coffee_type.equals("back")){
                    System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                    coffee_type = in.next();
                }
                if (coffee_type.equals("back")){
                    continue;
                }
                makeCoffee(ingredients, coffee_type);
            }
            else if(action.equals("fill")){
                fillMachine(ingredients);
            }
            else if(action.equals("take")){
                takeMoney(ingredients);
            }
            else if(action.equals("remaining")){
                printIngredients(ingredients);
            }
            else if(action.equals("exit")){
                break;
            }
        }
    }

    public static void printIngredients(int[] ingredients){
        System.out.printf("""
                
                The coffee machine has:
                %d of water
                %d of milk
                %d of coffee beans
                %d of disposable cups
                %d of money
                """, ingredients[0], ingredients[1], ingredients[2], ingredients[3], ingredients[4]);
    }

    public static void makeCoffee(int[] ingredients, String type){
        int t = Integer.parseInt(type);
        int[][] coffeeIngredients = new int[][] {{250, 0, 16, 1, -4}, {350, 75, 20, 1, -7}, {200, 100, 12, 1, -6}};
        if (checkIngredients(ingredients, coffeeIngredients[t - 1])) {
            System.out.println("I have enough resources, making you a coffee!");
            for(int i=0; i < 5; i++){
                ingredients[i] -= coffeeIngredients[t-1][i];
            }
        }
    }

    public static boolean checkIngredients(int[] ingredients, int[] recipe){
        if(ingredients[0] - recipe[0] < 0){
            System.out.println("Sorry, no enough water!");
            return false;
        }
        else if(ingredients[1] - recipe[1] < 0){
            System.out.println("Sorry, no enough milk!");
            return false;
        }
        else if(ingredients[2] - recipe[2] < 0){
            System.out.println("Sorry, no enough coffee beans!");
            return false;
        }
        else if(ingredients[3] - recipe[3] < 0){
            System.out.println("Sorry, no enough disposable cups!");
            return false;
        }
        return true;
    }

    public static void fillMachine(int[] ingredients){
        Scanner i = new Scanner(System.in);
        System.out.println("\nWrite how many ml of water you want to add:");
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
