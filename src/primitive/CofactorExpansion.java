package primitive;

import java.util.Scanner;

public class CofactorExpansion{
    public static double[][] takeCofactor(double[][] matrix, int row, int col){
        double[][] cofactor = new double[matrix.length-1][matrix.length-1];
        int i = 0, j = 0;
        for(int x = 0; x < matrix.length; x++){
            for(int y = 0; y < matrix.length; y++){
                if(x != row && y != col){
                    cofactor[i][j++] = matrix[x][y];
                    if(j == matrix.length-1){
                        j = 0;
                        i++;
                    }
                }
            }
        }
        return cofactor;
    }

    public static double determinant(double[][] matrix){
        double det = 0;
        if(matrix.length == 1){
            return matrix[0][0];
        }
        if(matrix.length == 2){
            return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
        }
        for(int i = 0; i < matrix.length; i++){
            double[][] cofactor = takeCofactor(matrix, 0, i);
            det += Math.pow(-1, i) * matrix[0][i] * determinant(cofactor);
        }
        return det;
    }
    public static double driverCofactorDet(){
        @SuppressWarnings("resource")
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
        double det = determinant(matrix);
        System.out.println("Determinan Matriks : " + det);
        return det;
    }
}