package regression;
import java.util.Scanner;

import primitive.BasicFunction;
import primitive.GaussElimination;
import primitive.InputOutput;
class DoubleWrapper {
    public double[] value;

    public DoubleWrapper(double[] value) {
        this.value = value;
    }
}


public class MultipleLinearRegression {
    public static final String Coeff = null;
    private static boolean getCoefficient(double[][] IndependentVar, double[][] DependentVar, DoubleWrapper Coeff){
        double[][] EROMatrix = new double[IndependentVar[0].length+1][IndependentVar[0].length+2];
        int n = IndependentVar.length; //Jumlah Sampel
        int k = IndependentVar[0].length; //Jumlah Variabel
        for (int i=0; i<EROMatrix.length; i++){
            for (int j=0; j<k; j++){
                for (int l=0; l<n; l++){
                    if (i==0){EROMatrix[i][j+1] += IndependentVar[l][j];}
                    else {EROMatrix[i][j+1] += IndependentVar[l][i-1]*IndependentVar[l][j];}
                }
            }
        }
        for (int i=0; i<EROMatrix.length; i++){
            for (int j=0;j<EROMatrix.length;j++){
                if (i==0){EROMatrix[i][k+1] += DependentVar[j][0];}
                else {EROMatrix[i][k+1] += IndependentVar[j][i-1]*DependentVar[j][0];}
            }
        }
        EROMatrix[0][0] = n;
        for (int i=1; i<EROMatrix.length; i++){EROMatrix[i][0] = EROMatrix[0][i];}
        String[] type = {""};
        String result = GaussElimination.gaussElimination(EROMatrix, type);
        if (result==null){
            System.out.println("SPL Tidak memiliki solusi.");
            return false;
        }
        else if (type[0]=="parametric"){
            System.out.println("SPL memiliki hasil tak hingga.");
            return false;
        }
        else {
            Coeff.value = GaussElimination.normalBackSubstitution(EROMatrix);
            return true;
        }
    }
    public static double[] inputArrayReg(int m){
        boolean validInput = false;
        double[] matrix = new double[m-1];
        System.out.println("Masukan Array prediktor sesuai format");
        System.out.println("x1i x2i x3i...xni");
            while (true) {
                try{
                    String[] elements = BasicFunction.readInput().trim().split("\\s+");
                    if (elements.length != m-1){
                        System.out.println("Jumlah kolom tidak sesuai.");
                    }
                        try {
                            for (int i = 0; i < matrix.length; i++){matrix[i] = Double.parseDouble(elements[i]);}
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Masukan hanya menerima angka.");
                        }
                    }
                catch (Exception e){
                    System.out.println("Error, silahkan coba lagi.");
                }
            }   
        return matrix;
    }
    public static double[][] inputMatrixReg(int n, int m){
        boolean validInput = false;
        double[][] matrix = new double[n][m];
        System.out.println("Masukan Matrix sesuai format");
        System.out.println("x1i x2i x3i...xni yi");
        System.out.println("x1j x2j x3j...xnj yj");
        for (int i = 0; i < n; i++){
            while (true) {
                validInput = true;
                try{
                    String[] elements = BasicFunction.readInput().trim().split("\\s+");
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
    public static double multipleLinearRegression() {
        System.out.println("Masukan jumlah sampel dan variabel dalam satu baris dengan spasi: ");
        int n = 0;
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int m = 0;
        while (true) {
            try {
                String[] elements = BasicFunction.readInput().trim().split("\\s+");
                n = Integer.parseInt(elements[0]);
                m = Integer.parseInt(elements[1]);
                break;
            } catch (Exception e) {
                System.out.println("Error, silahkan coba lagi.");
            }
        }
        double[][] Variables = new double[n][m];
        double[] Predictors = new double[m-1];
        while (true){
            System.out.print("Ambil variabel dari file?(Y/n/C) : ");
            try{char choice = BasicFunction.readInput().charAt(0);
            if (choice == 'Y' || choice == 'y'){
                System.out.print("Masukan path ke file (D:/Documents/regresi.txt): ");
                String filename = scanner.nextLine();
                InputOutput.readInputRegression(filename, Variables, Predictors, n, m);
                break;
            } else if (choice == 'N' || choice == 'n'){
                Variables = inputMatrixReg(n, m);
                Predictors = inputArrayReg(m);
                break;
            } else if (choice == 'C' || choice == 'c'){
                return 0.267;
            }
            else {
                System.out.println("Masukan tidak valid.");
            }
            } catch (Exception e){
                System.out.println("Error, silahkan coba lagi.");
                System.out.println(e);
            }
        }
        double[][] IndependentVariable = new double[Variables.length][Variables[0].length-1];
        for (int i=0; i<Variables.length; i++) {
            for (int j=0; j<Variables[0].length-1; j++) {
                IndependentVariable[i][j] = Variables[i][j];
            }
        }
        double[][] DependentVariable = new double[Variables.length][1];
        for (int i=0; i<Variables.length; i++) {
            DependentVariable[i][0] = Variables[i][Variables[0].length - 1];
        }
        DoubleWrapper coef = new DoubleWrapper(new double[IndependentVariable[0].length+1]);
        boolean result = getCoefficient(IndependentVariable, DependentVariable, coef);
        BasicFunction.printArray(coef.value);
        if (result == false) {
            System.out.println("Sistem Persamaan tidak memiliki solusi.");
            return 0.2567;
        }
        double results = 0;
        for (int i = 1; i<IndependentVariable[0].length; i++){
            results += coef.value[IndependentVariable[0].length-i-1]*Predictors[i];
        }
        results += coef.value[IndependentVariable[0].length-1];
        System.out.println("Hasil Prediksi Model : " + results);
        return results;
    }
}