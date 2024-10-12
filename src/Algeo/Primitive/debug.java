package Algeo.Primitive;

public class debug {
    public static void main(String[] abrarbeban){
        double[][] matrix = BasicFunction.inputMatrix();
        double[][] matrix_n = new double[matrix.length][matrix[0].length];
        System.out.println("Row Deduction Determinant : " + Determinant.rowReductionDeterminant(matrix_n));
        BasicFunction.copyMatrix(matrix, matrix_n);
        System.out.println("Cofactor Expansion Determinant : " + CofactorExpansion.determinant(matrix_n));
        BasicFunction.copyMatrix(matrix, matrix_n);
        try{System.out.println("Inverse : ");BasicFunction.printMatrix(Inverse.InverseCofactor(matrix_n));}
        catch(IllegalArgumentException e){System.out.println(e.getMessage());}
        BasicFunction.copyMatrix(matrix, matrix_n);
        try{System.out.println("Inverse : ");BasicFunction.printMatrix(Inverse.InverseERO(matrix_n));}
        catch(IllegalArgumentException e){System.out.println(e.getMessage());}
        System.out.println("Input Matrix for Cramer and Gauss : ");
        matrix = BasicFunction.inputMatrix();
        BasicFunction.copyMatrix(matrix, matrix_n);
        System.out.println("Cramer Solution : ");BasicFunction.printArray(Cramer.CramerSolver(matrix));
        System.out.println("Gauss Solution : ");
        BasicFunction.copyMatrix(matrix, matrix_n);
        System.out.println("Matriks Awal: ");
        BasicFunction.printMatrix(matrix_n);
        String result = GaussElimination.gaussElimination(matrix_n);
        System.out.println("Matrix Akhir:");
        BasicFunction.printMatrix(matrix_n);
        System.out.println(result);
        if (result.equals("Unique solution found.")) {
            double[] x = GaussElimination.backSubstitution(matrix_n);
            BasicFunction.printArray(x);
        }
        System.out.println("Gauss-Jordan Solution: ");
        BasicFunction.copyMatrix(matrix, matrix_n);
        System.out.println("Matriks Awal: ");
        BasicFunction.printMatrix(matrix);
        result = GaussJordanElimination.gaussJordanElimination(matrix_n);
        System.out.println("Matrix Akhir:");
        BasicFunction.printMatrix(matrix_n);
        System.out.println(result);
        if (result.equals("Unique solution found.")) {
            GaussJordanElimination.printArrayJordan(matrix_n);
        }
    }
}
