package CreditCalculator;

import java.text.DecimalFormat;
import java.util.*;

public class CreditCalculator {
    public static void main(String[] args) {

        CreditLogic C = new CreditLogic();
        String type = System.getProperty("type");
        if(type == null || !C.setType(type)) {

            System.out.println("Incorrect parameters");
            return;
        }
        String principal = System.getProperty("principal", "-1");
        C.setPrincipal(Double.parseDouble(principal));
        String periods = System.getProperty("periods", "-1");
        C.setMonths(Integer.parseInt(periods));

        String interest = System.getProperty("interest", "-1");
        C.setInterest(Double.parseDouble(interest));
        String payment = System.getProperty("payment", "-1");
        C.setMonthlyPayment(Double.parseDouble(payment));
        if((type.equals("diff") && C.getMonthlyPayment() > 0)){
            System.out.println("Incorrect parameters");
            return;
        }
        String[] arg = new String[] {principal, periods, payment};
        if (Collections.frequency(Arrays.asList(arg), "-1") > 1){
            System.out.println("Incorrect parameters");
            return;
        }
        C.Calculate();

    }
}

class CreditLogic {

    private Double principal;
    private Integer months;
    private Double monthlyPayment ;

    private String type;


    private double interest;

    public void setPrincipal(double principal){
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

    public boolean setType(String type) {
        if(type.equals("diff") || type.equals("annuity")){
            this.type = type;
            return true;
        }
        return false;
    }

    public void Calculate() {
        if(type.equals("diff")){
            if(principal < 1 || months < 1 || interest < 0){
                System.out.println(principal);
                System.out.println("Incorrect parameters");
                return;
            }
            calcDiff();
        }
        else if (principal == -1) {
            if(monthlyPayment < 1 || months < 1 || interest < 0){
                System.out.println("Incorrect parameters");
                return;
            }
            principal = Math.floor(monthlyPayment / ((interest * Math.pow(1 + interest, months)) / (Math.pow(1 + interest, months) - 1)));
            System.out.println("Your loan principal = " + new DecimalFormat( "#.###" ).format(principal));
        } else if (monthlyPayment == -1) {
            if(principal < 1 || months < 1 || interest < 0){
                System.out.println("Incorrect parameters");
                return;
            }
            monthlyPayment = Math.ceil(principal * ((interest * Math.pow(1 + interest, months)) / (Math.pow(1 + interest, months) - 1)));
            System.out.println("Your annuity payment = " + new DecimalFormat( "#.##" ).format(monthlyPayment));
        } else if (months == -1) {
            if(principal < 1 || monthlyPayment < 1 || interest < 0){
                System.out.println("Incorrect parameters");
                return;
            }
            months = (int) Math.ceil(Math.log(monthlyPayment / (monthlyPayment - interest * principal)) / Math.log(1 + interest));
            int years = months / 12;
            int months_year = months % 12;
            String months_text = months_year > 0? months_year > 1? "%d months ".formatted(months_year):"1 month " : "";
            String date = years == 0? months_text: years > 1? "%d years %s".formatted(years, months_year > 0? "and " + months_text: months_text) : "%d year %s".formatted(years, months_year > 0? "and " + months_text: months_text);
            System.out.println("It will take " + date + "to repay this loan");
        }
    }

    private void calcDiff(){
        double summa = 0;
        for (int j = 1; j <= months; j++) {
            double month = (Math.ceil(principal / months + (interest * (principal - (principal * (j - 1)) / months))));
            System.out.printf("Month %d: payment is "  + new DecimalFormat( "#.###" ).format(month) + "\n", j );
            summa += month;
        }
        System.out.printf("Overpayment = " + new DecimalFormat( "#.##" ).format(summa - principal));
    }

    public double getMonthlyPayment(){
        return monthlyPayment;
    }

    public int getMonths(){
        return months;
    }

    public double getPrincipal(){
        return principal;
    }

    public double getInterest() {
        return interest;
    }

    public String getType() {
        return type;
    }


}
