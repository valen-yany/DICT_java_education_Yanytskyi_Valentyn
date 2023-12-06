package MatrixProcessing;
import java.util.Scanner;

public class MatrixProcessing {
    public static void main(String[] args){
        matricesAdd();
    }

    private static void matricesAdd(){
        int[] firstSizes = getSizes();
        Matrix firstMatrix = new Matrix(firstSizes[0], firstSizes[1]);
        int[] secondSizes = getSizes();
        Matrix seconndMatrix = new Matrix(secondSizes[0], secondSizes[1]);
        Matrix thirdMatrix = Matrix.add(firstMatrix, seconndMatrix);
        thirdMatrix.print();
    }

    private static int[] getSizes(){
        int[] sizes = new int[2];
        Scanner in = new Scanner(System.in);
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

