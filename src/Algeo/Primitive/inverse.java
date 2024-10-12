package Algeo.Primitive;
public class Inverse {
    public static double[][] InverseCofactor(double[][] Matrix) throws IllegalArgumentException{
        double det = CofactorExpansion.determinant(Matrix);
        if(det == 0){
            throw new IllegalArgumentException("Matrix is singular.");
        }
        double[][] adjugate = new double[Matrix.length][Matrix.length];
        double[][] inverse = new double[Matrix.length][Matrix.length];
        for(int i = 0; i < Matrix.length; i++){
            for(int j = 0; j < Matrix.length; j++){
                double[][] cofactor = CofactorExpansion.takeCofactor(Matrix, i, j);
                adjugate[i][j] = Math.pow(-1, i+j) * CofactorExpansion.determinant(cofactor);
            }
        }
        inverse = BasicFunction.transpose(adjugate);
        for(int i = 0; i < Matrix.length; i++){
            for(int j = 0; j < Matrix.length; j++){
                inverse[i][j] /= det;
            }
        }
        return inverse;
    }
    public static boolean switchRowIden(double[][] matrix, double[][] identity, int i) {
        for (int row = i + 1; row < matrix.length; row++) {
            if (matrix[row][i] != 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    double temp = matrix[i][j];
                    matrix[i][j] = matrix[row][j];
                    matrix[row][j] = temp;
                    temp = identity[i][j];
                    identity[i][j] = identity[row][j];
                    identity[row][j] = temp;
                }
                return true; 
            }
        }
        return false; // Tidak ada baris yang bisa ditukar
    }
    public static boolean isRowZeroIden(double[] row) {
        for (int i = 0; i < row.length; i++) { 
            if (row[i] != 0) {
                return false;
            }
        }
        return true;
    }
    public static void toIdentity(double[][] matrix, double[][] identity) throws IllegalArgumentException {
        int n = matrix.length;
        // Loop over the diagonal of the matrix
        for (int i = 0; i < n; i++) {
            // Make the diagonal element 1 by dividing the entire row by the diagonal element
            double diagonalElement = matrix[i][i];
            if (diagonalElement == 0) {
                throw new IllegalArgumentException("Matrix is singular and cannot be reduced to identity.");
            }
            for (int j = 0; j < n; j++) {
                matrix[i][j] /= diagonalElement;
                identity[i][j] /= diagonalElement;
            }

            // Make the other elements in the column 0 by subtracting multiples of the current row
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = matrix[k][i];
                    for (int j = 0; j < n; j++) {
                        matrix[k][j] -= factor * matrix[i][j];
                        identity[k][j] -= factor * identity[i][j];
                    }
                }
            }
        }
    }
    public static double[][] InverseERO(double[][] Matrix) throws IllegalArgumentException{
        double[][] augmentedMatrix = new double[Matrix.length][Matrix.length];
        augmentedMatrix = BasicFunction.getIdentity(Matrix.length);
        toIdentity(Matrix, augmentedMatrix);
        return augmentedMatrix;
    }
}
