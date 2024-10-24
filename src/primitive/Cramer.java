package primitive;

import java.util.Scanner;

public class Cramer {
    public static double[] driverCramerSolver(){
        Scanner scanner = new Scanner(System.in);
        double[][] matrix = new double[0][0];
        while (true){
            System.out.print("Ambil variabel dari file?(Y/n/C) : ");
            try{char choice = BasicFunction.readInput().charAt(0);
            if (choice == 'Y' || choice == 'y'){
                System.out.print("Masukan path ke file (D:/Documents/var.txt): ");
                String filename = scanner.nextLine();
                InputOutput.readMatrixFile(filename);
                break;
            } else if (choice == 'N' || choice == 'n'){
                matrix = BasicFunction.inputMatrix();
                break;
            } else if (choice == 'C' || choice == 'c'){
                return new double[] {0.267};
            } else {
                System.out.println("Masukan tidak valid.");
            }
            } catch (Exception e){
                System.out.println("Error, silahkan coba lagi.");
            }
        }
        double[] result = CramerSolver(matrix);
        System.out.println("Solusi dari persamaan adalah : ");
        BasicFunction.printArray(result);
        return result;
    }
    public static double[] CramerSolver (double[][] matrix) throws IllegalArgumentException{
        // Prekondisi matriks harus n x (n+1)

        if (matrix == null || matrix.length == 0 || matrix.length + 1 != matrix[0].length){
            throw new IllegalArgumentException("Matriks harus berukuran n x (n+1)");
        }

        double[] tempDet = new double[matrix.length]; // Menampung determinan tiap variasi cramer
        int row = matrix.length; // Karena sudah pasti matriks n x n maka cukup memakai panjang dari row
        double[] resultArray = stripVectorB(matrix); // Menampung array hasil (B)
        double[][] variableMatrix = stripVectorA(matrix); // Menampung matrix variabel (A)
        double[][] determinantMatrix = new double[variableMatrix.length][variableMatrix[0].length]; // Menyalin variabel matrix untuk dicari determinant nya
        for (int r=0 ; r < row ; r++){
            System.arraycopy(variableMatrix[r], 0, determinantMatrix[r], 0, row);
        } 
        double det = Determinant.rowReductionDeterminant(determinantMatrix);
        
        
        if (det == 0){
            throw new IllegalStateException("Determinan bernilai nol sehingga tidak ada solusi"); // Tidak memiliki solusi
        }
        else{
            // Mencari determinan dari tiap variasi matriks
            for (int i = 0 ; i < row ; i++){
                double tempMatrix[][] = new double[row][row];
                for (int r=0 ; r < row ; r++){
                    System.arraycopy(variableMatrix[r], 0, tempMatrix[r], 0, row);
                }                
                BasicFunction.setColumnElement(tempMatrix, i, resultArray); // Mengubah 1 kolom dari tempMatrix menjadi array hasil 
                tempDet[i] = Determinant.rowReductionDeterminant(tempMatrix); // Menampung determinan variasi tersebut
            }

            // Mencari solusi dari tiap variasi matriks
            for (int i = 0; i < row; i++){
                resultArray[i] = tempDet[i] / det;
            }
        }
        // MatrixFunction.printArray(resultArray);
        return resultArray;
    }

    // Mengekstrak matriks hasil atau matriks B pada persamaan Ax = B
    public static double[] stripVectorB(double[][] matrix){
        if (matrix == null || matrix.length==0){
            throw new IllegalArgumentException("Matrix tidak boleh null atau kosong");
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        
        double[] resultArray = new double[row];
        for (int i = 0; i < row; i++){
            resultArray[i] = matrix[i][col-1];
        }
        
        return resultArray;
    }
    
    // Mengekstrak matriks variabel atau matriks A pada persamaan Ax = B
    private static double[][] stripVectorA(double[][] matrix){
        if (matrix == null || matrix.length==0){
            throw new IllegalArgumentException("Matrix tidak boleh null atau kosong");
        }
        int row = matrix.length;
        int col = matrix[0].length-1;

        double[][] resultMatrix = new double[row][col];
        
        for (int i = 0; i < row; i++){
            for (int j = 0 ; j < col; j++){
                resultMatrix[i][j] = matrix[i][j];
            }
        }

        return resultMatrix;
    }
}