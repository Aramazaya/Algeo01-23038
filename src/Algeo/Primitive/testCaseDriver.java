package Algeo.Primitive;
import Algeo.Interpolance.PolinomialInterpolation;
import Algeo.Primitive.InputOutput;
import java.io.FileNotFoundException;


public class testCaseDriver {
    public static void main(String[] args) throws FileNotFoundException{
        // SPL Augmented
        // double[][] matrix = InputOutput.readMatrixFile("test/1a.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/1b.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/1c.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/1d1.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/1d2.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/2a.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/2b.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/3a.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/3b.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/4.txt");

        // Interpolasi Polinomial
        // double[][] matrix = InputOutput.readMatrixFile("test/5a1.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/5a2.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/5a3.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/5a4.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/5b1.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/5b2.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/5b3.txt");
        // double[][] matrix = InputOutput.readMatrixFile("test/5c.txt");

        // *** DETERMINANT ***
        // System.out.println("Row Deduction Determinant : " + Determinant.rowReductionDeterminant(matrix_n));
        // BasicFunction.copyMatrix(matrix, matrix_n);
        // System.out.println("Cofactor Expansion Determinant : " + CofactorExpansion.determinant(matrix_n));
        // BasicFunction.copyMatrix(matrix, matrix_n);
        
        // *** INVERSE ***
        // try{System.out.println("Inverse : ");BasicFunction.printMatrix(Inverse.InverseCofactor(matrix_n));}
        // catch(IllegalArgumentException e){System.out.println(e.getMessage());}
        // BasicFunction.copyMatrix(matrix, matrix_n);
        // try{System.out.println("Inverse : ");BasicFunction.printMatrix(Inverse.InverseERO(matrix_n));}
        // catch(IllegalArgumentException e){System.out.println(e.getMessage());}
        
        
        // *** CRAMER ***
        // BasicFunction.copyMatrix(matrix, matrix_n);
        // System.out.println("Cramer Solution : ");BasicFunction.printArray(Cramer.CramerSolver(matrix));
        
        // *** GAUSS ***
        // System.out.println("Gauss Solution : ");
        // BasicFunction.copyMatrix(matrix, matrix_n);
        // System.out.println("Matriks Awal: ");
        // BasicFunction.printMatrix(matrix_n);
        
        // System.out.println("Matrix Awal:");
        // BasicFunction.copyMatrix(matrix, matrix_n);
        // BasicFunction.printMatrix(matrix_n);
        // String result = GaussElimination.gaussElimination(matrix_n);
        // System.out.println("Matrix Akhir:");
        // BasicFunction.printMatrix(matrix_n);
        // System.out.println(result);
        
        // System.out.println("Gauss-Jordan Solution: ");
        // BasicFunction.copyMatrix(matrix, matrix_n);
        // System.out.println("Matriks Awal: ");
        // BasicFunction.printMatrix(matrix);
        // result = GaussJordanElimination.gaussJordanElimination(matrix_n);
        // System.out.println("Matrix Akhir:");
        // BasicFunction.printMatrix(matrix_n);
        // System.out.println(result);
        
        // *** INTERPOLATION *** 
        // BasicFunction.copyMatrix(matrix, matrix_n);
        // double result = PolinomialInterpolation.polinomialInterpolation(matrix);
        // System.err.println("Polinomial Interpolation: " + result);
        // 5C Error handling??
        int start = 0;
        int end = 2;
        int number = 5;
        double h = (end - start)/number;
        double[][] points = new double[number+2][2]; //Menampung (x, f(x))

        for(int i = 0; i <= number; i++){
            double x = start + i * h;
            double y = f(x);
            points[i][0] = x;
            points[i][1] = y;
        }

        double xEstimate = 0.2;
        points[number+1][0] = xEstimate;
        points[number+1][1] = 0;
        double interpolatedValue = PolinomialInterpolation.polinomialInterpolation(points);
        System.out.println("Interpolated value: " + interpolatedValue);
    }
    
    public static double f(double x){
        return ((Math.pow(x, 2) + Math.sqrt(x)) / (Math.exp(x) + x));
    }
}
