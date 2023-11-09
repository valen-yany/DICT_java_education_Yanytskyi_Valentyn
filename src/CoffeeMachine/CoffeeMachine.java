package CoffeeMachine;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args){
        CoffeeMachineClass c = new CoffeeMachineClass(400,540,120,9,550);
    }
}
class CoffeeMachineClass{
    final static Scanner in = new Scanner(System.in);
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    public CoffeeMachineClass(int water, int milk, int beans, int cups, int money){
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
        mainMenu();
    }

    private void mainMenu(){
        while(true){
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
            String action = in.next();
            switch (action) {
                case "buy" -> {
                    String coffee_type = "";
                    while (!coffee_type.equals("1") && !coffee_type.equals("2") && !coffee_type.equals("3") && !coffee_type.equals("back")) {
                        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                        coffee_type = in.next();
                    }
                    if (coffee_type.equals("back")) {
                        continue;
                    }
                    makeCoffee(coffee_type);
                }
                case "fill" -> fillMachine();
                case "take" -> takeMoney();
                case "remaining" -> printIngredients();
                case "exit" -> {
                    return;
                }
            }
        }
    }
    private void printIngredients(){
        System.out.printf("""
                
                The coffee machine has:
                %d of water
                %d of milk
                %d of coffee beans
                %d of disposable cups
                %d of money
                """, water, milk, beans, cups, money);
    }
    private void makeCoffee(String type) {
        int t = Integer.parseInt(type);
        int[][] coffeeIngredients = new int[][]{{250, 0, 16, 1, 4}, {350, 75, 20, 1, 7}, {200, 100, 12, 1, 6}};
        if (checkIngredients(coffeeIngredients[t - 1])) {
            System.out.println("I have enough resources, making you a coffee!");
            for (int i = 0; i < 5; i++) {
                switch (i) {
                    case 0 -> water -= coffeeIngredients[t - 1][i];
                    case 1 -> milk -= coffeeIngredients[t - 1][i];
                    case 2 -> beans -= coffeeIngredients[t - 1][i];
                    case 3 -> cups -= coffeeIngredients[t - 1][i];
                    case 4 -> money += coffeeIngredients[t - 1][i];
                }
            }
        }
    }

    private boolean checkIngredients(int[] recipe){
        if(water - recipe[0] < 0){
            System.out.println("Sorry, no enough water!");
            return false;
        }
        else if(milk - recipe[1] < 0){
            System.out.println("Sorry, no enough milk!");
            return false;
        }
        else if(beans - recipe[2] < 0){
            System.out.println("Sorry, no enough coffee beans!");
            return false;
        }
        else if(cups - recipe[3] < 0){
            System.out.println("Sorry, no enough disposable cups!");
            return false;
        }
        return true;
    }
    private void fillMachine(){
        Scanner i = new Scanner(System.in);
        System.out.println("\nWrite how many ml of water you want to add:");
        water += i.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milk += i.nextInt();
        System.out.println("Write how many grams of coffee you want to add:");
        beans += i.nextInt();
        System.out.println("Write how many disposable coffee cups you want to add:");
        cups += i.nextInt();
    }

    private void takeMoney(){
        System.out.printf("I gave you %d", money);
        money = 0;
    }
}
