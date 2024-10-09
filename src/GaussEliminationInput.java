public class GaussEliminationInput {

    public static void main(String[] args) throws Exception {
        int n = 0;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Masukkan jumlah baris: ");
            String input = readInput().trim();
            try {
                n = Integer.parseInt(input);
                if (n > 0) {
                    validInput = true;
                } else {
                    System.out.println("Jumlah baris harus lebih dari 0. Silakan coba lagi.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka yang benar.");
            }
        }

        // Buat Matrix n x (n+1)
        double[][] matrix = new double[n][n + 1];
        while (true) {
            System.out.println("Masukkan Matrix baris demi baris dengan spasi setiap bilangan di 1 baris:");
            boolean correctInput = true;
            for (int i = 0; i < n; i++) {
                String[] elements = readInput().trim().split("\\s+");
                if (elements.length != n + 1) {
                    System.out.println("Jumlah elemen pada baris tidak sesuai. Silakan masukkan ulang seluruh matriks.");
                    correctInput = false;
                    break;
                }
                for (int j = 0; j < n + 1; j++) {
                    try {
                        matrix[i][j] = Double.parseDouble(elements[j]);
                    } catch (NumberFormatException e) {
                        System.out.println("Input tidak valid. Masukkan ulang seluruh matriks.");
                        correctInput = false;
                        break;
                    }
                }
                if (!correctInput) {
                    break;
                }
            }
            if (correctInput) {
                break;
            }
        }

        System.out.println("Matriks Awal: ");
        printMatrix(matrix);
        String result = gaussElimination(matrix);
        System.out.println("Matrix Akhir:");
        printMatrix(matrix);
        
        System.out.println(result);
        if (result.equals("Unique solution found.")) {
            double[] x = backSubstitution(matrix);
            printArray(x);
        }
    }

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

    // Fungsi untuk mencetak matriks
    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%8.4f ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // Fungsi untuk mencetak solusi
    public static void printArray(double[] solution) {
        for (int i = 0; i < solution.length; i++) {
            System.out.printf("x%d = %.4f\n", i + 1, solution[i]);
        }
    }
}
