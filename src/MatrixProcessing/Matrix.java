package MatrixProcessing;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Matrix {
    private final int rows;
    private final int columns;
    private final double[][] data;

    public Matrix(int rows, int columns){
        this.rows = rows;
        this.columns = columns;
        this.data = new double[rows][columns];
        for(int i = 0; i<rows;i++){
            try {
                Scanner in = new Scanner(System.in);
                String[] row = in.nextLine().trim().split("\\s+");
                if (row.length != columns) {
                    throw new Exception("You must enter correct row with right number of columns");
                }
                ;
                for (int k = 0; k < columns; k++) {
                    if (!row[i].matches("[0-9]")) {
                        throw new Exception("You must enter only numbers");
                    }
                    data[i][k] = Integer.parseInt(row[k]);
                }
            }catch(Exception e){
                System.out.println("Error: " + e.getMessage());
                System.exit(1);
            }
        }
    }

    private Matrix(int rows, int columns, double[][] data){
        this.data = data;
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double[][] getData() {
        return data;
    }

    public void print(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                System.out.print(new DecimalFormat( "#.###" ).format(data[i][j]) + " ");
            }
            System.out.println();
        }
    }

    public static Matrix add(Matrix m1, Matrix m2){
        int resultRows = m1.getRows();
        int resultColumns = m1.getColumns();
        try{
            if(resultRows != m2.getRows() || resultColumns != m2.getColumns()){
                throw new Exception("ERROR");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        double[][] firstMatrix = m1.getData();
        double[][] secondMatrix = m2.getData();
        double[][] resultData = new double[resultRows][resultColumns];
        for(int i = 0; i < resultRows;i++){
            for(int j=0;j<resultColumns;j++){
                resultData[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
            }
        }
        return new Matrix(resultRows, resultColumns, resultData);
    }

    public static Matrix MatrixMultiplyByConstant(Matrix matrix, double constant){
        int matrixRows = matrix.getRows();
        int matrixColumns = matrix.getColumns();
        double[][] matrixData = new double [matrixRows][matrixColumns];
        for(int i=0;i<matrixRows;i++){
            for(int j=0;j<matrixColumns;j++){
                matrixData[i][j] = matrix.getData()[i][j] * constant;
            }
        }
        return new Matrix(matrixRows,matrixColumns,matrixData);
    }
}
