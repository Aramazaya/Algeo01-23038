package Algeo.Interpolance;
import Algeo.Primitive.Cramer;
import Algeo.Primitive.MatrixFunction;

public class PolinomialInterpolation{
        public static double polinomialInterpolation(double[][] matrix, int n, double x){
        if (n < 1) {
            throw new IllegalArgumentException("Jumlah titik harus lebih besar dari 0.");
        }
        else{
            // Matriks akan berukuran n x 2 karena akan mengandung titik (x,y)
            // Harus ditambahkan nilai 1 pada kolom pertama agar a0 terpenuhi persamaan a0 + a1x0 + ... = y0 
            double[][] matrixEquation = new double[n][n+1]; // n + 1 karena akan menampung vektor hasil di akhir kolom
            MatrixFunction.setColumnOneElement(matrixEquation, 0, 1); // Memasukkan nilai 1 pada awal kolom
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
            
            for(int i=0 ; i<n ; i++){
                result += arrayResult[i] * Math.pow(x,i) ;
            }
            return result;

        }
    }
}