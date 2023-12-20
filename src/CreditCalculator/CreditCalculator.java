package CreditCalculator;

import java.util.Arrays;
import java.util.Scanner;

public class CreditCalculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CreditLogic C = new CreditLogic();
        while (true) {
            System.out.println("""
                    What do you want to calculate?
                    type "n" for number of monthly payments,
                    type "a" for annuity monthly payment amount,
                    type "p" for loan principal:""");
            String type = in.next();
            if(!Arrays.asList(new String[]{"n", "a", "p"}).contains(type)){
                continue;
            }
            if(!type.equals("p")){
                System.out.println("Enter the loan principal:");
                C.setPrincipal(in.nextInt());
            }
            if(!type.equals("a")){
                System.out.println("Enter the monthly payment:");
                C.setMonthlyPayment(in.nextDouble());
            }
            if (!type.equals("n")) {
                System.out.println("Enter the number of periods:");
                C.setMonths(in.nextInt());
            }
            System.out.println("Enter the loan interest:");
            C.setInterest(in.nextDouble());
            C.Calculate();
            if(type.equals("p")){
                System.out.printf("Your loan principal = %d", C.getPrincipal());
            }
            else if(type.equals("a")){
                System.out.println(C.getMonthlyPayment() == Math.ceil(C.getMonthlyPayment()) ? "Your monthly payment = %d".formatted((int) C.getMonthlyPayment()): "Your monthly payment = %.2f".formatted(C.getMonthlyPayment()));
            }
            else if(type.equals("n")){
                int years = C.getMonths() / 12;
                int months = C.getMonths() % 12;
                String months_text = months > 0? months > 1? "%d months".formatted(months):"1 month" : "";
                String date = years == 0? months_text: years > 1? "%d years %s".formatted(years, months > 0? "and " + months_text: months_text) : "%d year %s".formatted(years, months > 0? "and " + months_text: months_text);
                System.out.println("It will take " + date + " to repay this loan");
            }
            break;
        }
    }
}

class CreditLogic {

    private Integer principal;
    private Integer months;
    private Double monthlyPayment ;

    private double interest;


    public void setPrincipal(int principal){
        this.principal = principal;
    }

    public void setMonths(int months){
        this.months = months;
    }

    public void setInterest(double interest){
        this.interest = interest/1200;
    }

    public void setMonthlyPayment(double monthlyPayment){
        this.monthlyPayment = monthlyPayment;
    }

    public void Calculate() {
        if (principal == null) {
            principal = (int) Math.ceil(monthlyPayment / ((interest * Math.pow(1 + interest, months)) / (Math.pow(1 + interest, months) - 1)));
        } else if (monthlyPayment == null) {
            monthlyPayment = principal * ((interest * Math.pow(1 + interest, months)) / (Math.pow(1 + interest, months) - 1));
        } else if (months == null) {
            months = (int) Math.ceil(Math.log(monthlyPayment / (monthlyPayment - interest * principal)) / Math.log(1 + interest));
        }
    }

    public double getMonthlyPayment(){
        return monthlyPayment;
    }

    public int getMonths(){
        return months;
    }

    public int getPrincipal(){
        return principal;
    }

    public double getInterest() {
        return interest;
    }
}
