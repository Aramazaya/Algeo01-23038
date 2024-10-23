package primitive;
public class CofactorExpansion{
    public static double[][] takeCofactor(double[][] matrix, int row, int col){
        double[][] cofactor = new double[matrix.length-1][matrix.length-1];
        int i = 0, j = 0;
        for(int x = 0; x < matrix.length; x++){
            for(int y = 0; y < matrix.length; y++){
                if(x != row && y != col){
                    cofactor[i][j++] = matrix[x][y];
                    if(j == matrix.length-1){
                        j = 0;
                        i++;
                    }
                }
            }
        }
        return cofactor;
    }

    public static double determinant(double[][] matrix){
        double det = 0;
        if(matrix.length == 1){
            return matrix[0][0];
        }
        if(matrix.length == 2){
            return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
        }
        for(int i = 0; i < matrix.length; i++){
            double[][] cofactor = takeCofactor(matrix, 0, i);
            det += Math.pow(-1, i) * matrix[0][i] * determinant(cofactor);
        }
        return det;
    }
}