package MatrixProcessing;
import java.util.Scanner;

public class MatrixProcessing {
    private static final Scanner in = new Scanner(System.in);
    public static void main(String[] args){
        //matricesAdd();
        matrixConstMultiply();
    }

    private static void matricesAdd(){
        int[] firstSizes = getSizes();
        Matrix firstMatrix = new Matrix(firstSizes[0], firstSizes[1]);
        int[] secondSizes = getSizes();
        Matrix secondMatrix = new Matrix(secondSizes[0], secondSizes[1]);
        Matrix thirdMatrix = Matrix.add(firstMatrix, secondMatrix);
        thirdMatrix.print();
    }

    private static void matrixConstMultiply(){
        int[] Sizes = getSizes();
        Matrix matrix = new Matrix(Sizes[0], Sizes[1]);
        double constant = in.nextDouble();
        Matrix result = Matrix.MatrixMultiplyByConstant(matrix, constant);
        result.print();
    }

    private static int[] getSizes(){
        int[] sizes = new int[2];
        try{
            String[] size = in.nextLine().trim().split("\\s+");
            if (size.length != 2){
                throw new Exception("Enter only rows and columns");
            }
            for (int i = 0; i < 2; i++) {
                if (!size[i].matches("[0-9]")) {
                    throw new Exception("You should enter numbers!");
                }
                sizes[i] = Integer.parseInt(size[i]);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return sizes;
    }
}

