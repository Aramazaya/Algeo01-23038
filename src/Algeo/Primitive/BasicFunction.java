package Algeo.Primitive;

public class BasicFunction {

    public static double[][] inputMatrix(){
        int n,m;
        n = m = 0;
        boolean validInput = false;
        while (!validInput){
            System.out.print("Masukan Jumlah Baris : ");
            try{String input = readInput().trim();
            try {
                n=Integer.parseInt(input);
                if (n>0) {
                    validInput = true;
                } else {System.out.println("Yang Bener Kontol");}
            } catch (NumberFormatException e){
                System.out.println("Angka Goblog");
            }} catch (Exception e){
                System.out.println("An Error Occured. Please Try Again.");
        }}
        validInput = false;
        while (!validInput){
            System.out.print("Masukan Jumlah Kolom : ");
            try{
                String input = readInput().trim();
            try {
                m=Integer.parseInt(input);
                if (m>0) {
                    validInput = true;
                } else {System.out.println("Yang Bener Kontol");}
            } catch (NumberFormatException e){
                System.out.println("Angka Goblog");
            }} catch (Exception e){
                System.out.println("An Error Occured. Please Try Again.");
            }
        }
        double[][] matrix = new double[n][m];
        System.out.println("Masukan Matrix sesuai format");
        for (int i = 0; i < n; i++){
            while (true) {
                validInput = true;
                try{
                    String[] elements = readInput().trim().split("\\s+");
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
                } catch (Exception e){
                    System.out.println("Yang bener aja la kontol, Row nya kurang.");
                    validInput = false;
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

    public static double[][] getIdentity(int n){
        double[][] matrix = new double[n][n];
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if (i==j){
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }
    public static boolean rowZero (double[][] matrix, int rowIndex){
        int col;
        for(col = 0; col < matrix[rowIndex].length ; col++ ){
            if(matrix[rowIndex][col] != 0){
                return false;
            }
        }
        return true;
    }
    public static boolean colZero(double[][] matrix, int colIndex) {
        if (colIndex < 0 || colIndex >= matrix[0].length) {
            throw new IllegalArgumentException("Column index is out of bounds.");
        }
        
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][colIndex] != 0) {
                return false;
            }
        }
        return true;
    }
    public static void scaleVector (double[][] matrix, int rowIndex ,int scale){
        for(int col = 0; col < matrix[rowIndex].length ; col++){
            matrix[rowIndex][col] *= scale;
        }
    }
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
