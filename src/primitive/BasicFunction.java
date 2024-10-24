package primitive;

public class BasicFunction {
    public static double[][] inputMatrixn(){
        int n, m;
        n = m = 0;
        boolean validInput = false;
        validInput = false;
        while (!validInput){
            System.out.print("Masukan Jumlah Kolom : ");
            try{
                String input = readInput().trim();
            try {
                m=Integer.parseInt(input);
                n = Integer.parseInt(input);
                if (m>0) {
                    validInput = true;
                } else {System.out.println("Kolom harus lebih dari nol");}
            } catch (NumberFormatException e){
                System.out.println("Masukkan hanya menerima angka");
            }} catch (Exception e){
                System.out.println("Error, silahkan coba lagi.");
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
                        System.out.println("Jumlah kolom tidak sesuai.");
                        validInput = false;
                    }
                    for (int j = 0; j < m; j++) {
                        try {
                            matrix[i][j] = Double.parseDouble(elements[j]);
                        } catch (NumberFormatException e) {
                            System.out.println("Masukan hanya menerima angka.");
                            validInput = false;
                        }
                    }
                } catch (Exception e){
                    System.out.println("Error, silahkan coba lagi.");
                    validInput = false;
                }
                if (validInput) {break;}
            }   
        }
        return matrix;
    }
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
                } else {System.out.println("Baris harus lebih dari nol.");}
            } catch (NumberFormatException e){
                System.out.println("Masukan hanya menerima angka.");
            }} catch (Exception e){
                System.out.println("Error, silahkan coba lagi.");
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
                } else {System.out.println("Kolom harus lebih dari nol");}
            } catch (NumberFormatException e){
                System.out.println("Masukkan hanya menerima angka");
            }} catch (Exception e){
                System.out.println("Error, silahkan coba lagi.");
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
                        System.out.println("Jumlah kolom tidak sesuai.");
                        validInput = false;
                    }
                    for (int j = 0; j < m; j++) {
                        try {
                            matrix[i][j] = Double.parseDouble(elements[j]);
                        } catch (NumberFormatException e) {
                            System.out.println("Masukan hanya menerima angka.");
                            validInput = false;
                        }
                    }
                } catch (Exception e){
                    System.out.println("Error, silahkan coba lagi.");
                    validInput = false;
                }
                if (validInput) {break;}
            }   
        }
        return matrix;
    }
    public static double[][] inputPolinomial(){
        int n,m;
        n = m = 0;
        boolean validInput = false;
        while (!validInput){
            System.out.print("Masukan Jumlah Titik : ");
            try{String input = readInput().trim();
            try {
                n=Integer.parseInt(input);
                if (n>0) {
                    validInput = true;
                } else {System.out.println("Jumlah titik harus lebih dari nol.");}
            } catch (NumberFormatException e){
                System.out.println("Masukan hanya menerima angka.");
            }} catch (Exception e){
                System.out.println("Error, silahkan coba lagi.");
        }}
        
        m = 2; //2 Kolom
        
        double[][] matrix = new double[n+1][m];
        System.out.println("Masukan Matrix sesuai format");
        for (int i = 0; i < n+1; i++){
            while (true) {
                validInput = true;
                try{
                    String[] elements = readInput().trim().split("\\s+");
                    if (elements.length != m){
                        System.out.println("Jumlah kolom tidak sesuai.");
                        validInput = false;
                    }
                    for (int j = 0; j < m; j++) {
                        try {
                            matrix[i][j] = Double.parseDouble(elements[j]);
                        } catch (NumberFormatException e) {
                            System.out.println("Masukan hanya menerima angka.");
                            validInput = false;
                        }
                    }
                } catch (Exception e){
                    System.out.println("Error, silahkan coba lagi.");
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

    public static double[][] multiplication(double[][] m1, double[][] m2){
        double[][] result = new double[m1.length][m2[0].length];
        for (int i = 0; i < m1.length;i++){
            for (int j = 0; j < m2[0].length; j++){
                for (int k = 0; k < m1[0].length; k++){
                    result[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return result;
    }
      public static void swapRow (double[][] matrix, int row1, int row2){
        double[] tempArray;

        tempArray = matrix[row1];
        matrix[row1] = matrix[row2];
        matrix[row2] = tempArray;
    }

    // Mencari nilai terbesar dari diagonal utama matrix dan melakukan penukaran baris
    public static void partialPivot(double[][] matrix, int currRow){
        int totalRow = matrix.length;

        int maxRow = currRow;
        double maxValue = matrix[currRow][currRow];
        // Asumsikan row dengan elemen terbesar 
        for(int i = currRow + 1; i < totalRow; i++){
            if(matrix[i][currRow] > maxValue){
                maxValue = matrix[i][currRow];
                maxRow = i;
            }
        }
        if(maxRow != currRow){
            swapRow(matrix, maxRow, currRow);
        }
    }

    public static void setColumnElement(double[][] matrix, int colIndex, double[] values) {
        int row = matrix.length;
        for (int i = 0; i < row; i++) {
            matrix[i][colIndex] = values[i]; 
        }
    }
    public static void setColumnOneElement(double[][] matrix, int colIndex, double values) {
        int row = matrix.length;
        for (int i = 0; i < row; i++) {
            matrix[i][colIndex] = values; 
        }
    }

    public static double[] stripMatrix(double[][] matrix, int colIndex){
        int row = matrix.length;

        double[] resultArray = new double[row];
        for (int i = 0; i < row; i++){
            resultArray[i] = matrix[i][colIndex];
        }

        return resultArray;
    }

    public static double[][] stripMatrixPolinomial(double[][] matrix){
        int row = matrix.length - 1;
        
        double[][] resultMatrix = new double[row][2];
        for (int i = 0; i < row; i++){
            for(int j = 0; j < 2; j++){
                resultMatrix[i][j] = matrix[i][j];

            }
        }

        return resultMatrix;
    }
    
    public static double[][] stripMatrixEquation(double[][] matrix){
        int row = matrix.length-1;
        int col = matrix[0].length;
        
        double[][] resultMatrix = new double[row][2];
        for (int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                resultMatrix[i][j] = matrix[i][j];
            }
        }

        return resultMatrix;
    }
    
    public static int factorial(int n){
        if (n == 0){
            return 1;
        }
        else {
            return n * factorial(n-1);
        }
    }

}
