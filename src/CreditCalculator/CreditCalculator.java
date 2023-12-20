package CreditCalculator;

public class CreditCalculator {
    public static void main(String[] args){
        Credit C = new Credit();
    }
}

class Credit{

    Credit(){
        System.out.println("""
Loan principal: 1000
Month 1: repaid 250
Month 2: repaid 250
Month 3: repaid 500
The loan has been repaid!""");
    }
}
