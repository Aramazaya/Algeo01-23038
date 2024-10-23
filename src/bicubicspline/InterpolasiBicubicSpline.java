package bicubicspline;

import java.util.Scanner;
import primitive.BasicFunction;
import primitive.Inverse;
public class InterpolasiBicubicSpline {
	 public static void main(String[] args) {
	        double[][] input = {
	                { 21, 98, 125, 153 },
	                { 51, 101, 161, 59 },
	                { 0, 42, 72, 210 },
	                { 16, 12, 81, 96 }
	        };
	        
	        matrixSingular(input);
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Masukkan nilai x: ");
	        double x = scanner.nextDouble();
	        System.out.print("Masukkan nilai y: ");
	        double y = scanner.nextDouble();
	        String hasilInterpolasi = bicubiInterpolation(x, y);
	        System.out.println("Hasil Interpolasi:");
	        System.out.println(hasilInterpolasi);   
	        scanner.close(); 
	    }
    
    static double[][] matriksInput = new double[16][1];
    static double[][] matriksInverse = new double[16][16];
    static double[][] matriksCoefficient = new double[16][1];
    
    public static void matrixSingular(double[][] input) {
        int index = 0;
        int i = 0, j = 0;
        while (i < 4) {
            while (j < 4) {
                matriksInput[index][0] = input[i][j];
                index++;
                j++;
            }
            j = 0;
            i++;
        }
        int count = 0;
        while (count < 16) {
            konstanta(count);
            count++;
        }
        matrixCoefficient();
    }

    public static void matrixCoefficient(){
        matriksInverse = Inverse.getInverseMatriksIdentity(matriksInverse);
        matriksCoefficient = BasicFunction.multiplication(matriksInverse, matriksInput);
    }
    
    public static void konstanta(int i) {
        int loop = i % 4;
        if (loop == 0) derivative(0, 0, i);
        else if (loop == 1) derivative(1, 0, i);
        else if (loop == 2) derivative( 0, 1, i);
        else derivative(1, 1, i);
    }

    public static void derivative(int x, int y, int i){
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
                matriksInverse[i][idx] = (int) value;
                idx++;
            }
        }
    }
    
    public static String bicubiInterpolation(double x, double y) {
        StringBuilder sout = new StringBuilder();
        double sum = 0;
        int index = 0; 
            for (int l = 0; l < 16; l++) {
            int k = l / 4; 
            int j = l % 4; 
            double xTerm = Math.pow(x, j);
            double yTerm = Math.pow(y, k);
            sum += matriksCoefficient[index][0] * xTerm * yTerm;
            index++;
        }
        sout.append(String.format("f(%.2f,%.2f) = %f", x, y, sum));
        return sout.toString();
    }
}
