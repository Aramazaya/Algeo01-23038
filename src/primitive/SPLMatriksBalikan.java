package primitive;

import java.util.Scanner;

public class SPLMatriksBalikan {
    public static String driverSPLInverse(){
        double[][] matrix = new double[0][0];
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("Ambil variabel dari file?(Y/n/C) : ");
            try{char choice = BasicFunction.readInput().charAt(0);
            if (choice == 'Y' || choice == 'y'){
                System.out.print("Masukan path ke file (D:/Documents/var.txt): ");
                String filename = scanner.nextLine();
                System.out.println("filename: " + filename);
                matrix = InputOutput.readMatrixFile(filename);
                break;
            } else if (choice == 'N' || choice == 'n'){
                matrix = BasicFunction.inputMatrix();
                break;
            } else if (choice == 'C' || choice == 'c'){
                return "0.267";
            } else {
                System.out.println("Masukan tidak valid.");
            }
            } catch (Exception e){
                System.out.println("Error, silahkan coba lagi.");
                System.out.println(e);
            }
        }
        String hasil = SPLInverse(matrix);
        if (hasil == null){return "0.267";}
        else {return hasil;}
    }
    public static String SPLInverse(double[][] matrix){
        int m = matrix[0].length-1;
        int n = matrix.length;
        double[] B = new double[n];
        for (int i = 0; i < n; i++){
            B[i] = matrix[i][m];
        }
        double[][] A = new double[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                A[i][j] = matrix[i][j];
            }
        }
        if (m != n){
            System.out.println("Matriks tidak memiliki invers");
            return null;
        }
        try{
            double[][] AInverse = Inverse.InverseCofactor(A);
        if (AInverse == null){
            System.out.println("Matriks tidak memiliki invers");
            return null;
        }
        double[] X = new double[m];
        for (int i = 0; i < m; i++){
            X[i] = 0;
            for (int j = 0; j < n; j++){
                X[i] += AInverse[i][j] * B[j];
            }
        }
        String hasil = "";
        for (int i = 0; i < m; i++){
            hasil += ("x" + i + " = " + X[i] + "\n");
        }
        System.out.println(hasil);
        return hasil;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
