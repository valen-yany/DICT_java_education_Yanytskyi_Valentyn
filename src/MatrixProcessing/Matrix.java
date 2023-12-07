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

                for (int k = 0; k < columns; k++) {
                    if (!row[k].matches("^-?\\d+(\\.\\d+)?$")) {
                        throw new Exception("You must enter only numbers");
                    }
                    data[i][k] = Double.parseDouble(row[k]);
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
                throw new Exception("The operation cannot be performed. Numbers of rows and columns in matrices must be equal");
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

    public static Matrix multiplyByConstant(Matrix matrix, double constant){
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

    public static Matrix multiply(Matrix m1, Matrix m2){
        int resultRows = m1.getRows();
        int resultColumns = m2.getColumns();
        try{
            if(m2.getRows() != m1.getColumns()){
                throw new Exception("The operation cannot be performed. Number of  columns in first matrix and number of rows in second matrix must be equal");
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
                resultData[i][j] = 0;
                for(int k=0;k<m2.getRows();k++){
                    resultData[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return new Matrix(resultRows,resultColumns,resultData);
    }

    public static Matrix transpose(Matrix matrix, int type){
        int resultRow = 1;
        int resultColumn = 1;
        double[][] result = {{0}};
        try{

            switch (type) {
                case 1 -> {
                    resultRow = matrix.getColumns();
                    resultColumn = matrix.getRows();
                    result = new double[resultRow][resultColumn];
                    for (int i = 0; i < resultRow; i++) {
                        for (int j = 0; j < resultColumn; j++) {
                            result[i][j] = matrix.getData()[j][i];
                        }
                    }
                }
                case 2 -> {
                    resultRow = matrix.getColumns();
                    resultColumn = matrix.getRows();
                    result = new double[resultRow][resultColumn];
                    for (int i = 0; i < resultRow; i++) {
                        for (int j = 0; j < resultColumn; j++) {
                            result[i][j] = matrix.getData()[matrix.getRows() - 1 - j][matrix.getColumns() - 1 - i];
                        }
                    }
                }
                case 3 -> {
                    resultRow = matrix.getRows();
                    resultColumn = matrix.getColumns();
                    result = new double[resultRow][resultColumn];
                    for(int i = 0; i < resultRow; i++){
                        for(int j = 0; j < resultColumn; j++){
                            result[i][j] = matrix.getData()[i][matrix.getColumns() - 1 - j];
                        }
                    }
                }
                case 4 -> {
                    resultRow = matrix.getRows();
                    resultColumn = matrix.getColumns();
                    result = new double[resultRow][resultColumn];
                    for(int i = 0; i < resultRow; i++){
                        for(int j = 0; j < resultColumn; j++){
                            result[i][j] = matrix.getData()[matrix.getRows() - 1 - i][j];
                        }
                    }
                }
                default -> throw new Exception("Incorrect type");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return new Matrix(resultRow, resultColumn, result);
    }

    public static double determinant(Matrix matrix){
        try{
            if(matrix.getColumns() != matrix.getRows()){
                throw new Exception("The determinant is existing only for square matrix");
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        int matrixLength = matrix.getRows();
        if (matrixLength == 1){
            return matrix.getData()[0][0];
        }
        else if(matrixLength == 2){
            return matrix.getData()[0][0] * matrix.getData()[1][1] - matrix.getData()[0][1] *matrix.getData()[1][0];
        }
        double det = 0;
        for(int i = 0; i < matrixLength; i++){
            double [][] minorData = new double[matrixLength][matrixLength];
            for(int j = 1;j<matrixLength; j++){
                int shift = 0;
                for(int k = 0; k < matrixLength; k++){
                    if(k==i){
                        shift = 1;
                        continue;
                    }
                    minorData[j - 1][k - shift] = matrix.getData()[j][k];
                }
            }
            Matrix minor = new Matrix(matrixLength - 1, matrixLength - 1, minorData);
            det += Math.pow(-1, i) * Matrix.determinant(minor) * matrix.getData()[0][i];
        }
        return det;
    }
}