public  class MatrixFunction {
    // Mencari baris yang memiliki semua nilainya nol
    public static boolean rowZero (double[][] matrix, int rowIndex){
        int col;
        for(col = 0; col < matrix[rowIndex].length ; col++ ){
            if(matrix[rowIndex][col] != 0){
                return false;
            }
        }
        return true;
    }
    
    public static boolean colZero (double[][] matrix, int colIndex){
        int row;
        for(row = 0; row < matrix[colIndex].length ; row++ ){
            if(matrix[row][colIndex] != 0){
                return false;
            }
        }
        return true;
    }
    // Mengalikan vektor dengan skala
    public static void scaleVector (double[][] matrix, int rowIndex ,int scale){
        for(int col = 0; col < matrix[rowIndex].length ; col++){
            matrix[rowIndex][col] *= scale;
        }
    }

    // Menukar 2 baris matrix
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
    
}
