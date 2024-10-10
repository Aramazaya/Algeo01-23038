package Algeo.Primitive;

public class BasicFunction {

    public static double[][] inputMatrix() throws Exception{
        int n,m;
        n = m = 0;
        boolean validInput = false;
        while (!validInput){
            System.out.print("Masukan Jumlah Baris : ");
            String input = readInput().trim();
            try {
                n=Integer.parseInt(input);
                if (n>0) {
                    validInput = true;
                } else {System.out.println("Yang Bener Kontol");}
            } catch (NumberFormatException e){
                System.out.println("Angka Goblog");
            }
        }
        validInput = false;
        while (!validInput){
            System.out.print("Masukan Jumlah Kolom : ");
            String input = readInput().trim();
            try {
                m=Integer.parseInt(input);
                if (m>0) {
                    validInput = true;
                } else {System.out.println("Yang Bener Kontol");}
            } catch (NumberFormatException e){
                System.out.println("Angka Goblog");
            }
        }
        double[][] matrix = new double[n][m];
        System.out.println("Masukan Matrix sesuai format");
        for (int i = 0; i < n; i++){
            while (true) {
                validInput = true;
                String[] elements = BasicFunction.readInput().trim().split("\\s+");
                if (elements.length != m){
                    System.out.println("Yang bener aja la kontol, Row nya kurang.");
                    validInput = false;
                }
                for (int j = 0; j < m; j++) {
                    try {
                        matrix[i][j] = Double.parseDouble(elements[j]);
                    } catch (NumberFormatException e) {
                        System.out.println("Woi Goblok. Yang lu masukin bukan angka tolol.");
                        validInput = false;
                    }
                }
                if (validInput) {break;}
            }   
        }
        return matrix;
    }
    public static void copyMatrix(double[][] matrix, double[][] matrix_out){
        for (int i = 0; i < matrix.length; i++){
            matrix_out[i] = matrix[i].clone();
        }
    }

    /*public static double[][] inputMatrix() throws Exception{
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
        return matrix;
    }
    /* */
    public static double[][] transpose(double[][] Matrix){
        double[][] transpose = new double[Matrix.length][Matrix.length];
        for (int i=0;i < Matrix.length; i++){
            for (int j=0; j < Matrix.length; j++){
                transpose[j][i] = Matrix[i][j];
            }
        }
        return transpose;
    }

    public static String readInput() throws Exception{
        StringBuilder inputBuilder = new StringBuilder();
        int character;
        while ((character = System.in.read()) != '\n') {
            inputBuilder.append((char) character);
        }
        return inputBuilder.toString();
    }

    public static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%8.4f ", matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void printArray(double[] solution) {
        for (int i = 0; i < solution.length; i++) {
            System.out.printf("x%d = %.4f\n", i + 1, solution[i]);
        }
    }
}
