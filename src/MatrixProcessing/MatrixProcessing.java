package MatrixProcessing;
import java.util.Scanner;

public class MatrixProcessing {
    private static final Scanner in = new Scanner(System.in);
    public static void main(String[] args){
        String option = "";
        while(!option.equals("0")){
            System.out.println("""
                    
                    1.Add matrices
                    2.Multiply matrix by a constant
                    3.Multiply matrices
                    0.Exit""");
            System.out.print("Your choice: ");
            option = in.nextLine();
            switch (option){
                case "1":
                    matricesAdd();
                    break;
                case "2":
                    matrixConstMultiply();
                    break;
                case "3":
                    matricesMultiply();
                    break;
            }
        }
    }

    private static void matricesAdd(){
        System.out.print("Enter size of first matrix:");
        int[] firstSizes = getSizes();
        System.out.println("Enter first matrix:");
        Matrix firstMatrix = new Matrix(firstSizes[0], firstSizes[1]);
        System.out.print("Enter size of second matrix:");
        int[] secondSizes = getSizes();
        System.out.println("Enter second matrix:");
        Matrix secondMatrix = new Matrix(secondSizes[0], secondSizes[1]);
        Matrix thirdMatrix = Matrix.add(firstMatrix, secondMatrix);
        System.out.println("The result is:");
        thirdMatrix.print();
    }

    private static void matrixConstMultiply(){
        System.out.print("Enter size of matrix:");
        int[] Sizes = getSizes();
        System.out.println("Enter matrix:");
        Matrix matrix = new Matrix(Sizes[0], Sizes[1]);
        System.out.print("Enter constant: ");
        String constantInput = in.next();
        double constant = Double.parseDouble(constantInput);
        Matrix result = Matrix.multiplyByConstant(matrix, constant);
        System.out.println("The result is:");
        result.print();
    }

    private static void matricesMultiply(){
        System.out.print("Enter size of first matrix:");
        int[] firstSizes = getSizes();
        System.out.println("Enter first matrix:");
        Matrix firstMatrix = new Matrix(firstSizes[0], firstSizes[1]);
        System.out.print("Enter size of second matrix:");
        int[] secondSizes = getSizes();
        System.out.println("Enter second matrix:");
        Matrix secondMatrix = new Matrix(secondSizes[0], secondSizes[1]);
        Matrix thirdMatrix = Matrix.multiply(firstMatrix, secondMatrix);
        System.out.println("The result is:");
        thirdMatrix.print();
    }


    private static int[] getSizes() {
        int[] sizes = new int[2];
        try {
            String[] size = in.nextLine().trim().split("\\s+");
            if (size.length != 2) {
                throw new Exception("Enter only rows and columns");
            }
            for (int i = 0; i < 2; i++) {
                if (!size[i].matches("\\d+")) {
                    throw new Exception("You should enter positive integers!");
                }
                sizes[i] = Integer.parseInt(size[i]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return sizes;
    }
}

