package Algeo.BicubicSpline;
public class InterpolasiBicubicSpline {
    public static String matrixOutput (double[][] matriks){
        String output = "";
        for(int i=0; i<matriks.length;i++){
            for (int j=0; j<matriks[0].length; j++){
                output += String.format("%d ",(int)matriks[i][j]);
            }
            output += String.format("\n");
        }
        return output;
    }
    
    static double[][] matriksInput = new double[16][1];
    static double[][] matriksX = new double[16][16];
    static double[][] matriksXInversed = new double[16][16];
    static double[][] matriksCoefficient = new double[16][1];
    
    public static void setMatrix (double[][] input){
        int index = 0;
 
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                matriksInput[index][0] = input[i][j];
                index++;
            }
        }
        for (int i = 0; i < 16; i++) {
            konstanta(i);
        }
        matriksXInversed = Inverse.get_Inverse_Matriks_fromIdentity(matriksX);
        matriksCoefficient = perkalian_Matriks(matriksXInversed, matriksInput);
    }
    
    
    public static void konstanta(int i){
        int x,y;
        int loop = i%4;
        switch (loop) {
            case 0:
                x = 0;
                y = 0;
                valueTurunanBerarah(x,y,i);
                break;
            case 1:
                x = 1;
                y = 0;
                valueTurunanBerarah(x,y,i);
                break;
            case 2:
                x = 0;
                y = 1;
                valueTurunanBerarah(x,y,i);
                break;
            default:
                x = 1;
                y = 1;
                valueTurunanBerarah(x,y,i);
                break;
        }
    }

    public static void valueTurunanBerarah(int x, int y, int i){
        double value;
        int idx = 0;
        for(int k=0; k<4; k++){
            for(int l=0; l<4; l++){
                if (i < 4) {
                    value = (Math.pow(x,l) * Math.pow(y,k));
                } else if (i < 8) {
                    value = l * (Math.pow(x,l-1) * Math.pow(y,k));
                } else if (i < 12) {
                    value = k * (Math.pow(x,l) * Math.pow(y,k-1));
                } else {
                    value = k*l*(Math.pow(x,l-1) * Math.pow(y,k-1));
                }
                matriksX[i][idx] = (int) value;
                idx++;
            }
        }
    }
    
    public static double GetAValue(int i, int j){
        return matriksCoefficient[i+j*4][0];
    }
    
    public static String getBicubicOutput(double x, double y){
        String sout = "";
        double sum = 0;
        for(int k=0; k<4; k++){
            for(int l=0; l<4; l++){
                sum += GetAValue(l,k)*Math.pow(x,l)*Math.pow(y,k);
            }
        }
        sout += String.format("f(%f,%f) = %f", x,y, sum);
        return sout;
    }

    public static String OutputMX(){
        String output;
        output = matrixOutput(matriksCoefficient);
        return output;
    }
    
    public static String OutputNilai(){
        String output;
        matriksXInversed = Inverse.get_Inverse_Matriks_fromIdentity(matriksX);
        output = matrixOutput(matriksXInversed);
        return output;
    }
    
    public   static double[][] perkalian_Matriks(double[][] matriks1, double[][] matriks2){ // Do matriks1 * matriks2 for SPL Purposes
        double[][] matriks_copy = new double[matriks1.length][matriks2[0].length]; 
        for(int i = 0 ; i < matriks1.length; i++){
            for(int j = 0; j < matriks2.length;j++){
                for(int k = 0; k < matriks2[0].length;k++){
                    matriks_copy[i][k] += matriks1[i][j] * matriks2[j][k];
                }
            }
        }
        return matriks_copy;
    }
    
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //

    public class Inverse {
        public static String getInverseOutput(double[][] matriks, String Function){
            double det = determinan_kofaktor(matriks);
            if (det == 0){
                return "Matrix determinant 0";
            }
            double[][] matrix = new double[matriks.length][matriks[0].length];
            if (Function == "Adjoin"){
                matrix = getInverseFromAdjoin(matriks);
        
            } else if (Function == "Identity"){
                matrix = get_Inverse_Matriks_fromIdentity(matriks); 
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

        public static double[][] get_Inverse_Matriks_fromIdentity(double[][] matrix){ 
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
    }
    
    
    ///
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
    public static double determinan_kofaktor(double[][] m){
        if(m.length == 0){
            return 0;
        }
        if(m.length == 1){
            return m[0][0];
        }
        if(m[0].length == 2){
            return (m[0][0] * m[1][1]) - (m[0][1] *m[1][0]);
        }

        double result = 0;
        int l;
        for(int i = 0; i < m.length; i++){
            double[][] temp = new double[m.length-1][m[i].length-1];
            for(int j = 0; j < temp.length ; j++){
                l = 0;
                for(int k = 0; k < m[i].length;k++){
                    if(k != i){
                        temp[j][l] = m[j+1][k];
                        l++;
                    }
                }

            }
            result += m[0][i] * Math.pow(-1, 2+i)* determinan_kofaktor(temp);
        }

        return result;
    }
    
}