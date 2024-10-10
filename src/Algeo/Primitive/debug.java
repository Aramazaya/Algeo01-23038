package Algeo.Primitive;

public class debug {
    public static void main(String[] abrarbeban) throws Exception{
        double[][] matrix = BasicFunction.inputMatrix();
        double[][] matrix_n = new double[matrix.length][matrix[0].length];
        System.out.println("B4 rowDeduction");
        BasicFunction.printMatrix(matrix_n);
        System.out.println("Row Deduction Determinant : " + Determinant.rowReductionDeterminant(matrix_n));
        System.out.println("After Row Deduction");
        BasicFunction.printMatrix(matrix_n);
        BasicFunction.copyMatrix(matrix, matrix_n);
        System.out.println("B4 Cofactor");
        BasicFunction.printMatrix(matrix);
        System.out.println("Cofactor Expansion Determinant : " + CofactorExpansion.determinant(matrix_n));
        System.out.println("After Cofactor");
        BasicFunction.printMatrix(matrix_n);
        BasicFunction.copyMatrix(matrix, matrix_n);
        System.out.println("Inverse : ");BasicFunction.printMatrix(inverse.Inverse(matrix_n));
        System.out.println("Input Matrix for Cramer and Gauss : ");
        matrix = BasicFunction.inputMatrix();
        BasicFunction.copyMatrix(matrix, matrix_n);
        System.out.println("Cramer Solution : ");BasicFunction.printArray(Cramer.CramerSolver(matrix));
        System.out.println("Gauss Solution : ");
        BasicFunction.copyMatrix(matrix, matrix_n);
        System.out.println("Matriks Awal: ");
        BasicFunction.printMatrix(matrix_n);
        String result = GaussEliminationInput.gaussElimination(matrix_n);
        System.out.println("Matrix Akhir:");
        BasicFunction.printMatrix(matrix_n);
        System.out.println(result);
        if (result.equals("Unique solution found.")) {
            double[] x = GaussEliminationInput.backSubstitution(matrix_n);
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
