package Algeo.Primitive;

public class GaussElimination {
    public static String readInput() throws Exception {
        StringBuilder inputBuilder = new StringBuilder();
        int character;
        while ((character = System.in.read()) != '\n') {
            inputBuilder.append((char) character);
        }
        return inputBuilder.toString();
    }

    public static String gaussElimination(double[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            if (matrix[i][i] == 0) {
                if (!switchRow(matrix, i)) {
                    continue;
                }
            }

            // Eliminasi Gauss
            for (int j = i + 1; j < n; j++) {
                if (matrix[j][i] != 0) {
                    double factor = matrix[j][i] / matrix[i][i];
                    for (int k = i; k < n + 1; k++) {
                        matrix[j][k] -= factor * matrix[i][k];
                    }
                }
                if (isRowZero(matrix[j]) && matrix[j][n] != 0) {
                    return "No solutions found.";
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (isRowZero(matrix[i]) && matrix[i][n] == 0) {
                return "Infinite solutions found.";
            }
        }
        return "Unique solution found.";
    }
    private static boolean isRowZero(double[] row) {
        for (int i = 0; i < row.length - 1; i++) {
            if (row[i] != 0) {
                return false;
            }
        }
        return true;
    }

    // Mengubah baris jika elemen diagonalnya nol
    public static boolean switchRow(double[][] matrix, int i) {
        for (int row = i + 1; row < matrix.length; row++) {
            if (matrix[row][i] != 0) {
                for (int j = 0; j < matrix[i].length; j++) {
                    double temp = matrix[i][j];
                    matrix[i][j] = matrix[row][j];
                    matrix[row][j] = temp;
                }
                return true; 
            }
        }
        return false;
    }

    // Substitusi balik untuk mendapatkan solusi
    public static double[] backSubstitution(double[][] matrix) {
        double[] x = new double[matrix.length];
        for (int i = matrix.length - 1; i >= 0; i--) {
            x[i] = matrix[i][matrix.length];
            for (int j = i + 1; j < matrix.length; j++) {
                x[i] -= matrix[i][j] * x[j];
            }
            x[i] /= matrix[i][i];
        }
        return x;
    }

}
