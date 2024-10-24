package interpolation;

import primitive.BasicFunction;
import primitive.Cramer;

public class PolinomialInterpolation{
        public static double polinomialInterpolation(double[][] matrix) throws IllegalArgumentException{
        int nInitial = matrix.length;
        int mInitial = matrix[0].length;
        
        // Mengekstrak titik persamaan yang diketahui
        double[][] tempMatrix = new double[nInitial][mInitial];
        BasicFunction.copyMatrix(matrix, tempMatrix);
        double[][] matrixPoint = BasicFunction.stripMatrixPolinomial(tempMatrix); 
        int n = matrixPoint.length;
        // Mengesktrak titik yang akan diestimasi
        double x = matrix[nInitial-1][0];

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                 if (matrixPoint[i][0] == matrixPoint[j][0]) {
                    throw new IllegalArgumentException("Terdapat nilai duplikat, interpolasi tidak dapat dilakukan.");
                }
            }
        }
        

        if (n < 1) {
            throw new IllegalArgumentException("Jumlah titik harus lebih besar dari 0.");
        }
        else{
            // Matriks akan berukuran n x 2 karena akan mengandung titik (x,y)
            // Harus ditambahkan nilai 1 pada kolom pertama agar a0 terpenuhi persamaan a0 + a1x0 + ... = y0 
            double[][] matrixEquation = new double[n][n+1]; // n + 1 karena akan menampung vektor hasil di akhir kolom
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
                result += arrayResult[i] * Math.pow(x,i) ;
               
                String formattedCoefficient = String.format("%.4f", arrayResult[i]);
                if (i==0){
                    output.append(formattedCoefficient);
                }
                else{
                    if(arrayResult[i] > 0 ){
                        output.append(" + ").append(formattedCoefficient).append("x^").append(i);
                    }
                    else{
                        output.append(" ").append(formattedCoefficient).append("x^").append(i);

                    }
                }
            }
            System.out.println(output.toString());
            System.out.printf("f(%.4f) = ", x);
            ;
            return result;

        }
    }
}