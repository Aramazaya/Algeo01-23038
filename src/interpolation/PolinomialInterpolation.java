package interpolation;

import primitive.BasicFunction;
import primitive.Cramer;
import primitive.InputOutput;

import java.util.Scanner;

public class PolinomialInterpolation{
        public static double polinomialInterpolation(double[][] matrix, int jumlahTitik, double xEstimate) throws IllegalArgumentException{
        // Mengekstrak titik persamaan yang diketahui
        int n = jumlahTitik;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                 if (matrix[i][0] == matrix[j][0]) {
                    throw new IllegalArgumentException("Terdapat nilai duplikat, interpolasi tidak dapat dilakukan.");
                }
            }
        }
        
        // Matriks akan berukuran n x 2 karena akan mengandung titik (x,y)
        // Harus ditambahkan nilai 1 pada kolom pertama agar a0 terpenuhi persamaan a0 + a1x0 + ... = y0 
        double[][] matrixEquation = new double[n][n+1]; // n + 1 karena akan menampung vektor hasil di akhir kolom
        for(int i = 0; i<n; i++){
            for(int j = 0 ; j< n+1 ; j ++){
                matrixEquation[i][j] = 0;
            }
        }
        BasicFunction.setColumnOneElement(matrixEquation, 0, 1); // Memasukkan nilai 1 pada awal kolom
        int col = matrixEquation[0].length;

        for (int i = 0; i < n; i++){             
            for(int j = 1; j < col-1 ; j++){ // Iterasi dimulai dari kolom setelah angka 1
                matrixEquation[i][j] =  matrix[i][0]; // Matrix Variabel
                matrixEquation[i][j] = Math.pow(matrixEquation[i][j], j);
            }
            matrixEquation[i][col-1] = matrix[i][1]; // Matrix hasil
        }

        double[] arrayResult = new double[n];
        arrayResult = Cramer.CramerSolver(matrixEquation);
        double result = 0;

        StringBuilder output = new StringBuilder();
        output.append("f(x) = ");
        for(int i=0 ; i<n ; i++){
            result += arrayResult[i] * Math.pow(xEstimate,i) ;
            
            String formattedCoefficient = String.format("%.4f", arrayResult[i]);
            if (i==0){
                if(arrayResult[0] != 0){
                    output.append(formattedCoefficient);
                }
            }
            else{
                if(arrayResult[i] > 0 ){
                    output.append(" + ").append(formattedCoefficient).append("x^").append(i);
                }
                else if(arrayResult[i] == 0){

                }
                else{
                    output.append(" ").append(formattedCoefficient).append("x^").append(i);

                }
            }
        }
        System.out.println(output.toString());
        System.out.printf("f(%.4f) = ", xEstimate);
        ;
        return result;
    }
    
    public static double polinomialInterpolationSolver() throws Exception {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        boolean isInputValid = false;
    
        while (!isInputValid) {
            System.out.println("Apakah anda ingin membaca dari file (y/N/c): ");
            try{char readFile = BasicFunction.readInput().charAt(0);
    
            if (readFile == 'y' || readFile == 'Y') {
                isInputValid = true; // Valid input, exit loop
                double[] x = new double[1];
                System.out.println("Masukkan jumlah titik: ");
                int n = scanner.nextInt();
                scanner.nextLine();
                double[][] matrix = new double[n][2];

                System.out.print("Masukan path ke file (D:/Documents/regresi.txt): ");
                String filename = scanner.nextLine();
                
                InputOutput.readInputPolinomialInterpolation(filename, matrix, n, x);
                double xEstimate = x[0];
                double result = PolinomialInterpolation.polinomialInterpolation(matrix, n, xEstimate);
                System.out.println(result);
                
                return result;
    
            } else if (readFile == 'n' || readFile == 'N') {
                boolean isNValid = false; // Valid input, exit loop
                int n = 0;
                while (!isNValid) {
                    System.out.println("Masukkan jumlah titik:");
                    n = scanner.nextInt();
                    if (n > 0) {
                        isNValid = true;
                    } else {
                        System.out.println("Jumlah titik harus lebih dari nol.");
                    }
                }
    
                double[][] matrix = new double[n][2];
                System.out.println("Masukkan nilai titik-titik tanpa kurung:");
                for (int i = 0; i < n; i++) {
                    System.out.print("Titik " + (i + 1) + " (x, y): ");
                    matrix[i][0] = scanner.nextDouble(); // Input untuk x
                    matrix[i][1] = scanner.nextDouble(); // Input untuk y
                }
    
                System.out.println("Masukkan nilai x yang akan diestimasi:");
                double x = scanner.nextDouble();
                System.out.println();
                double result = PolinomialInterpolation.polinomialInterpolation(matrix, n, x);
                System.out.println(result);
                return result;
    
            } else if (readFile == 'c' || readFile == 'C') {
                isInputValid = true;
                return 0.267; 
    
            } }catch(Exception e){
                System.out.println("Error, silahkan coba lagi.");
                System.out.println(e);
            }
        }
    
        return 0; // Default return in case nothing else works
    } 
}