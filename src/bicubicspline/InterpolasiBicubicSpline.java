package bicubicspline;

import java.util.Scanner;
import primitive.BasicFunction;
import primitive.Inverse;
public class InterpolasiBicubicSpline {
	 public static void main(String[] args) {
            double[][] input = BasicFunction.inputMatrix();
	        matrixSingular(input);
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Masukkan nilai x: ");
	        double x = scanner.nextDouble();
	        System.out.print("Masukkan nilai y: ");
	        double y = scanner.nextDouble();
	        String hasilInterpolasi = bicubiInterpolation(x, y);
	        System.out.println(hasilInterpolasi);   
	        scanner.close(); 
	    }
	
    static double[][] matriksInput = new double[16][1];
    static double[][] matriksInverse = new double[16][16];
    static double[][] matriksCoefficient = new double[16][1];

    public static String bicubiInterpolation(double x, double y) {
        StringBuilder result = new StringBuilder();
        double total = 0;
        int index = 0; 
    
        for (int i = 0; i < 16; i++) {
            int row = i / 4; 
            int col = i % 4; 
            double xFactor = Math.pow(x, col);
            double yFactor = Math.pow(y, row);
            total += matriksCoefficient[index][0] * xFactor * yFactor;
            index++;
        }
    System.out.println("Hasil Interpolasi:");
    result.append(String.format("(%.2f, %.2f) is %f", x, y, total));
    return result.toString();
    }

    public static void matrixCoefficient(){
        matriksInverse = Inverse.get_Inverse_Matriks_fromIdentity(matriksInverse);
        matriksCoefficient = BasicFunction.multiplication(matriksInverse, matriksInput);
    }
    
    public static void konstanta(int i) {
        int loop = i % 4;
        if (loop == 0) derivative(0, 0, i);
        else if (loop == 1) derivative(1, 0, i);
        else if (loop == 2) derivative( 0, 1, i);
        else derivative(1, 1, i);
    }

    public static void derivative(int x, int y, int derivativeType){
        double computedValue;
        int matrixIndex = 0;
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (derivativeType < 4) {
                    computedValue = Math.pow(x, col) * Math.pow(y, row);
                } else if (derivativeType < 8) {
                    computedValue = col * Math.pow(x, col - 1) * Math.pow(y, row);
                } else if (derivativeType < 12) {
                    computedValue = row * Math.pow(x, col) * Math.pow(y, row - 1);
                } else {
                    computedValue = col * row * Math.pow(x, col - 1) * Math.pow(y, row - 1);
                }
                matriksInverse[derivativeType][matrixIndex] = (int) computedValue;
                matrixIndex++;
            }
        }
    }
    
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
}
