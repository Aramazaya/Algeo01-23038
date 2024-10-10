package Algeo.Primitive;

public class Cramer {
    public static double[] CramerSolver (double[][] matrix){
        // Prekondisi matriks harus n x n

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
            return null; // Tidak memiliki solusi
        }
        else{
            // Mencari determinan dari tiap variasi matriks
            for (int i = 0 ; i < row ; i++){
                double tempMatrix[][] = new double[row][row];
                for (int r=0 ; r < row ; r++){
                    System.arraycopy(variableMatrix[r], 0, tempMatrix[r], 0, row);
                }                
                MatrixFunction.setColumnElement(tempMatrix, i, resultArray); // Mengubah 1 kolom dari tempMatrix menjadi array hasil 
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
    private static double[] stripVectorB(double[][] matrix){
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