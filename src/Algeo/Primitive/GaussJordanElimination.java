package Algeo.Primitive;

public class GaussJordanElimination {
    public static String readInput() throws Exception {
        StringBuilder inputBuilder = new StringBuilder();
        int character;
        while ((character = System.in.read()) != '\n') {
            inputBuilder.append((char) character);
        }
        return inputBuilder.toString();
    }

    public static String gaussJordanElimination(double[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            if (matrix[i][i] == 0) {
                if (!switchRow(matrix, i)) {
                    continue;
                }
            }
            double diagonalValue = matrix[i][i];
            for (int j = 0; j < n + 1; j++) {
                matrix[i][j] /= diagonalValue;
            }
            for (int j = 0; j < n; j++) {
                if (j != i && matrix[j][i] != 0) {
                    double factor = matrix[j][i];
                    for (int k = 0; k < n + 1; k++) {
                        matrix[j][k] -= factor * matrix[i][k];
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (isRowZero(matrix[i]) && matrix[i][n] != 0) {
                return "No solutions found."; 
            }
        }

        for (int i = 0; i < n; i++) {
            if (isRowZero(matrix[i]) && matrix[i][n] == 0) {
                return "Infinite solutions found."; 
            }
        }

        return "Unique solution found."; 
    }

    // Memeriksa apakah sebuah baris adalah baris nol
    public static boolean isRowZero(double[] row) {
        for (int i = 0; i < row.length - 1; i++) { 
            if (row[i] != 0) {
                return false;
            }
        }
        return true;
    }

    // Mengubah baris jika elemen diagonal adalah nol
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
        return false; // Tidak ada baris yang bisa ditukar
    }
    public static void printArrayJordan(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.printf("x%d = %.4f\n", i + 1, matrix[i][matrix[i].length - 1]);
        }
    }
}
