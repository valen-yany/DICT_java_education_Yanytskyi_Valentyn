package CreditCalculator;

import java.util.Scanner;

public class CreditCalculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Credit C = new Credit();
        System.out.println("Enter the loan principal:");
        int principal = in.nextInt();
        C.setPrincipal(principal);
        while (true) {
            System.out.println("""
                    What do you want to calculate?
                    type "m" – for number of monthly payments,
                    type "p" – for the monthly payment:""");
            String type = in.next();
            if (type.equals("m")) {
                System.out.println("Enter the monthly payment:");
                int monthly_payment = in.nextInt();
                C.setMonthlyPayment(monthly_payment);
                String months = (C.getMonths() > 1) ? "%d months".formatted(C.getMonths()) : "%d month".formatted(C.getMonths());
                System.out.printf("It will take %d months to repay the loan", months);
                break;
            }
            else if(type.equals("p")){
                System.out.println("Enter the number of months:");
                int months = in.nextInt();
                C.setMonths(months);
                String last_payment = (C.getLastPayment() > 0)? "and the last payment = %d".formatted(C.getLastPayment()) : "";
                System.out.printf("Your monthly payment = %d %s", C.getMonthlyPayment(), last_payment);
                break;
            }
        }
    }
}

class Credit{
    private int principal;
    private int months;
    private int monthlyPayment;
    private int lastPayment;

    public void setPrincipal(int principal){
        this.principal = principal;
    }

    public void setMonths(int months){
        this.months = months;
        this.monthlyPayment = (int)Math.ceil((double) principal / months);
        this.lastPayment = monthlyPayment*months != principal?  principal - (months - 1) * monthlyPayment : 0;
    }

    public void setMonthlyPayment(int monthlyPayment){
        this.monthlyPayment = monthlyPayment;
        this.months = (int)Math.ceil((double) principal / monthlyPayment);
    }

    public int getMonthlyPayment(){
        return monthlyPayment;
    }

    public int getMonths(){
        return months;
    }

    public int getLastPayment(){
        return lastPayment;
    }
}
