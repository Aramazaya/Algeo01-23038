package primitive;

import java.util.Scanner;

public class Determinant {
    public static double driverRowReductionDet(){
        double[][] matrix = new double[0][0];
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("Ambil variabel dari file?(Y/n/C) : ");
            try{char choice = BasicFunction.readInput().charAt(0);
            if (choice == 'Y' || choice == 'y'){
                System.out.print("Masukan path ke file (D:/Documents/var.txt): ");
                String filename = scanner.nextLine();
                InputOutput.readMatrixFile(filename);
                break;
            } else if (choice == 'N' || choice == 'n'){
                matrix = BasicFunction.inputMatrixn();
                break;
            } else if (choice == 'C' || choice == 'c'){
                return 0.267;
            } else {
                System.out.println("Masukan tidak valid.");
            }
            } catch (Exception e){
                System.out.println("Error, silahkan coba lagi.");
            }
        }
        double det = rowReductionDeterminant(matrix);
        System.out.println("Determinan Matriks : " + det);
        return det;
    }
    public static double rowReductionDeterminant(double[][] matrix)throws IllegalArgumentException{
        int i, j, k, row;
        double det = 1;
        boolean swapped;
        row = matrix.length;
        if (row == 0 || matrix[0].length != row) {
            throw new IllegalArgumentException("Matrix harus kotak (n x n).");
        }

        // Mengecek kasus determinan nol
        for (i = 0; i<row; i++){
            if (BasicFunction.rowZero(matrix, i)){
                return 0; // Ada baris nol maka determinan akan menjadi nol
            }
            if (BasicFunction.colZero(matrix, i)){
                return 0; // Ada kolom nol maka determinan akan menjadi nol
            }
        }


        // Jika baris pivot nol maka akan ditukar dengan yang tidak nol
        for (i = 0 ; i < row ; i++){
            if (matrix[i][i] == 0){
                swapped = false;
                for(j = i+1 ; j < row ; j++){
                    if (matrix[j][i] != 0){
                        BasicFunction.swapRow(matrix, j, i);
                        swapped = true;
                        det *= -1;
                        break;
                    }
                } 
                if (!swapped) return 0; // Kalau tidak ada yang tertukar maka tidak ada leading one dan determinan menhasilkan nol
            }

            for(j= i+1 ; j < row ; j++){
                double factor = matrix[j][i] / matrix[i][i];
                for(k= i; k<row ; k++){
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
        det *= matrix[i][i]; // Mengalikan diagonal utama matriks
        }
        return det;
    }   
    // Mencari nilai terbesar dari diagonal utama matrix dan melakukan penukaran baris
     
}
