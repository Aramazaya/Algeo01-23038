package bicubicspline;

import java.util.Scanner;
import primitive.BasicFunction;
import primitive.InputOutput;
import primitive.Inverse;
public class InterpolasiBicubicSpline {
	 public static String driverBicubicSpline() {
            Scanner scanner = new Scanner(System.in);
            double[][] input = new double[0][0];
            double[] predictor = new double[2];
            while (true){
            System.out.print("Ambil variabel dari file?(Y/n/C) : ");
            try{char choice = BasicFunction.readInput().charAt(0);
            if (choice == 'Y' || choice == 'y'){
                System.out.print("Masukan path ke file (D:/Documents/var.txt): ");
                String filename = scanner.nextLine();
                InputOutput.readInputBicubic(filename, input, predictor, 4, 4);
                break;
            } else if (choice == 'N' || choice == 'n'){
                input = BasicFunction.inputMatrix();
                while (true){
                    try{
                    String[] elements = BasicFunction.readInput().split(" ");
                    if(elements.length != 2){
                        System.out.println("Jumlah Kolom tidak sesuai");
                    }else {
                        try {
                            for (int i = 0; i < 2; i ++ ){
                                predictor[i] = Double.parseDouble(elements[i]);}
                        } catch (NumberFormatException e) {
                            System.out.println("Masukan hanya menerima angka");
                        }
                    }

                } catch (Exception e){System.out.println("Error Occured");}
                break;}
                break;
            } else if (choice == 'C' || choice == 'c'){
                return "0.267";
            } else {
                System.out.println("Masukan tidak valid.");
            }
            } catch (Exception e){
                System.out.println("Error, silahkan coba lagi.");
            }
        }
	        matrixSingular(input);
	        double x = predictor[0];
	        double y = predictor[1];
	        String hasilInterpolasi = bicubiInterpolation(x, y);
	        System.out.println(hasilInterpolasi);   
            return hasilInterpolasi;
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
        matriksInverse = Inverse.getInverseMatriks(matriksInverse);
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
