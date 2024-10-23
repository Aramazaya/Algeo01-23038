package primitive;
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

    public static String getInverseOutput(double[][] matriks, String Function){
        double det = determinan_kofaktor(matriks);
        if (det == 0){
            return "Matrix determinant 0";
        }
        double[][] matrix = new double[matriks.length][matriks[0].length];
        if (Function == "Adjoin"){
            matrix = getInverseFromAdjoin(matriks);
    
        } else if (Function == "Identity"){
            matrix = getInverseMatriks(matriks); 
        } 
        String sOut = "";
        for (int i=0; i<matrix.length; i++){
            for (int j=0; j<matrix[i].length; j++){
                sOut += String.format("%.3f ",matrix[i][j]);
            }
            sOut += "\n";
        }
        return sOut;
    }
    
    private static double[][] MatriksIdentity_Maker(double[][] matrix){ 
        int n = matrix.length;
        double[][] matrix_identity = new double[n][n];
        for(int i = 0; i < n ; i++){
            for(int j = 0; j < n; j++){
                if(i == j){
                    matrix_identity[i][j] = 1;
                }
                else{
                    matrix_identity[i][j] = 0;
                }  
            }
        }
        return matrix_identity;
    }

    private static double[][] Add_MatrixIdentity(double[][] matrix){ 
        int n = matrix.length;
        double[][] matriks_identity = MatriksIdentity_Maker(matrix);
        double[][] temp = new double[n][n*2];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n;j++){
                temp[i][j] = matrix[i][j];
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n;j++){
                temp[i][j+n] = matriks_identity[i][j];
            }
        }
        return temp;
    }

    private static int zeroCounter(double[][] matrix, int row){ 
        int sum = 0;
        for(int i = 0; i < matrix[row].length; i++){
            if(matrix[row][i] == 0){
                sum++;
            }
            else{
                return sum;
            }
        }
        return sum;
    }

    private static void swapping_Operation(double[][] matrix){ 
        for(int i = 0; i < matrix.length; i++){
            for(int j = matrix.length-1; j >= i; j--){
                if(zeroCounter(matrix, i) > zeroCounter(matrix, j)){
                    matrix_Swapping(matrix, i, j);
                }
            }
        }
    }

    private static void matrix_Swapping(double[][] matrix, int row_will_be_changed, int row_who_changed){
        double[][] temp = new double[1][matrix[0].length];
        for(int i = 0; i < matrix[0].length;i++){
            temp[0][i] = matrix[row_who_changed][i];
            matrix[row_who_changed][i] = matrix[row_will_be_changed][i];
            matrix[row_will_be_changed][i] = temp[0][i];
        }
    }

    private static void Multipy_Operation(double[][] matrix, int row_will_be_changed, int column_will_be_changed){ 
        double x = 1/matrix[row_will_be_changed][column_will_be_changed];
        for(int i = 0; i < matrix[0].length;i++){
            matrix[row_will_be_changed][i] *= x;
        } 
    }

    private static void Reduce_Operation(double[][] matrix, int row_will_be_changed,int column_will_be_changed, int row_who_changed){ 
        double x =  matrix[row_will_be_changed][column_will_be_changed]/matrix[row_who_changed][column_will_be_changed];
        for(int i = 0; i < matrix[0].length; i++){
            matrix[row_will_be_changed][i] -= (x*matrix[row_who_changed][i]);
        }
    }

    private static double[][] Gauss_Operation(double[][] matrix){ 
        double[][] matrix_inverted = Add_MatrixIdentity(matrix);

        for(int i = 0; i < matrix_inverted.length; i++){
            swapping_Operation(matrix_inverted);
            Multipy_Operation(matrix_inverted, i, i);
            for(int j = 0; j < matrix_inverted.length;j++){
                if(j != i){
                    Reduce_Operation(matrix_inverted, j, i, i);
                    if(matrix_inverted[j][i] == 0){
                        matrix[j][i] = Math.abs(matrix[i][j]);
                    }
                }
            }
        }  
        return matrix_inverted;
    }

    public static double[][] getInverseMatriks(double[][] matrix){ 
        double[][] matriks_inverted = Gauss_Operation(matrix);
        int end_row = matriks_inverted.length;
        int start_col = (matriks_inverted[0].length)/2;
        int end_col = matriks_inverted[0].length;
        int k = 0;
        for(int i = 0; i < end_row ;i++){
            for(int j = start_col; j < end_col; j++){
                matrix[i][k] = matriks_inverted[i][j];
                k++;
            }
            k= 0;
        }
        return matrix;
    }

    public static double[][] getInverseFromAdjoin(double[][] matriks){
        double det = determinan_kofaktor(matriks);
        matriks = getKofaktor(matriks);
        matriks = getTranspose(matriks);
        for(int i = 0; i<matriks.length; i++){
            for(int j=0; j<matriks[i].length; j++){
                matriks[i][j] *= (1/det);
            }
        }
        return matriks;
    }

    private static double[][] getKofaktor(double[][]matriks){
        double[][] m = new double[matriks.length][matriks[0].length];
        for (int i = 0; i<matriks.length; i++){
            for (int j = 0; j<matriks[i].length; j++){
                m[i][j] = getKofaktorValue(matriks, i, j) * (double)Math.pow(-1,(i+j));
            }
        }
        return m;
    }

    private static double getKofaktorValue(double[][] matriks, int x, int y){
        double[][] temp_matriks = new double[matriks.length-1][matriks[0].length-1];
        int tempi = 0, tempj = 0;
        for (int i=0; i<matriks.length; i++){
            for (int j=0; j<matriks[i].length; j++){
                if (i != x && j != y){
                    temp_matriks[tempi][tempj] = matriks[i][j];
                    tempj++;
                    if (tempj>=temp_matriks.length){
                        tempj = 0;
                        tempi++;
                    }
                        
                }
            }
        }
        return determinan_kofaktor(temp_matriks);
    }

    private static double[][] getTranspose(double[][] matriks){
        double[][] matriks_transpose= new double[matriks.length][matriks[0].length];
        for (int i = 0; i<matriks.length; i++){
            for (int j = 0; j<matriks[i].length; j++){
                matriks_transpose[i][j] = matriks[j][i];
            }
        }
        return matriks_transpose;
    }

    public static double determinan_kofaktor(double[][] m) {
        int n = m.length;
        if (n == 1) {
            return m[0][0];
        }
        if (n == 2) {
            return m[0][0] * m[1][1] - m[0][1] * m[1][0];
        }
        
        double result = 0.0;
        for (int j = 0; j < n; j++) {
            result += Math.pow(-1, j) * m[0][j] * determinan_kofaktor(submatriks(m, 0, j));
        }
        
        return result;
    }

    private static double[][] submatriks(double[][] m, int row, int col) {
        int n = m.length;
        double[][] subm = new double[n - 1][n - 1];
        int r = -1;
        for (int i = 0; i < n; i++) {
            if (i == row) continue;
            r++;
            int c = -1;
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                subm[r][++c] = m[i][j];
            }
        } 
        return subm;
    }

}
