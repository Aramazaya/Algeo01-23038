package main;
import primitive.BasicFunction;
import primitive.GaussElimination;
import primitive.GaussJordanElimination;
import primitive.Cramer;
import primitive.Determinant;
import primitive.CofactorExpansion;
import primitive.Inverse;
import primitive.InputOutput;
import interpolation.PolinomialInterpolation;
import regression.MultipleLinearRegression;
import bicubicspline.*;

import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
        while (true) {
            System.out.println("\nMENU");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks Balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Regresi Linear dan Kuadratik Berganda");
            System.out.println("6. Interpolasi Bicubic Spline");
            System.out.println("7. Interpolasi Gambar");
            System.out.println("8. Keluar");
            
            int menu = InputOutput.getValidIntegerInput(scanner, "Pilih Menu: ");
            boolean isSolutionValid = true;

            switch (menu) {
                // *** Sistem Persamaan Linier ***
                case 1:
                    System.out.println("1. Metode eliminasi Gauss");
                    System.out.println("2. Metode eliminasi Gauss-Jordan");
                    System.out.println("3. Kaidah Cramer");
                    
                    int metode = InputOutput.getValidIntegerInput(scanner, "Pilih Metode: ");
                    switch (metode) {
                        // *** GAUSS ***
                        case 1:
                        double matrix1[][] = BasicFunction.inputMatrix();
                        try {
                            String resultStr = GaussElimination.gaussElimination(matrix1);
                            System.err.println(resultStr);
                            if(isSolutionValid){
                                InputOutput.writeStringFile(resultStr);
                            }
                            break;
                        } catch (Exception e) {
                            System.err.println("Error: " + e);
                        }
                        
                        
                        // *** GAUSS JORDAN ***
                            case 2:
                            double matrix2[][] = BasicFunction.inputMatrix();
                            try {
                                String resultStr2 = GaussJordanElimination.gaussJordanElimination(matrix2);
                                System.out.println(resultStr2);
                                if(isSolutionValid){
                                    InputOutput.writeStringFile(resultStr2);
                                }
                            } catch (Exception e) {
                                System.err.println("Error: " + e);
                            }
                            break;
                        
                        // *** CRAMER ***
                        case 3:
                            double matrix[][] = BasicFunction.inputMatrix();
                            double[] CramerSolution = Cramer.CramerSolver(matrix);
                            
                            if (CramerSolution != null){
                                System.out.println();
                                BasicFunction.printArray(CramerSolution);
                            }                            
                            while (true){
                                try {
                                    if(isSolutionValid){
                                        InputOutput.writeArrayFile(CramerSolution);
                                        break;
                                    }
                                } catch (IllegalArgumentException e) {
                                    isSolutionValid = false;
                                    System.err.println("Error : " + e);
                                } catch( IllegalStateException e){
                                    isSolutionValid = false;
                                    System.err.println("Determinan bernilai nol sehingga tidak ada solusi");
                                }
                            }
                            break;
                        default:
                            System.out.println("Input tidak valid");
                            break;
                    }
                    break;

                    
                // *** DETERMINAN *** 
                case 2:
                System.out.println("1. Metode reduksi baris");
                System.out.println("2. Metode ekspansi kofaktor");
                System.out.println("3. Kembali");
                
                try{
                        int metodeDeterminan = InputOutput.getValidIntegerInput(scanner, "Pilih Metode: ");
                        switch (metodeDeterminan) {
                            case 1:
                                double matrix[][] = BasicFunction.inputMatrix();
                                double resultFloat = Determinant.rowReductionDeterminant(matrix);
                                System.out.println("Determinan: " + resultFloat);
                                if(isSolutionValid){
                                    InputOutput.writeDoubleFile(resultFloat);
                                }
                                
                                break;
                                case 2:
                                double matrix2[][] = BasicFunction.inputMatrix();
                                double resultFloat2 = CofactorExpansion.determinant(matrix2);
                                System.out.println("Determinan: " + resultFloat2);
                                if(isSolutionValid){
                                    InputOutput.writeDoubleFile(resultFloat2);
                                }
                                break;
                                case 3:
                                break;
                                default:
                                System.out.println("Input tidak valid");
                                break;
                            }
                        }catch(IllegalArgumentException e){
                            System.err.println("Error: " + e);
                        }catch(Exception e){
                            System.err.println("Error: " + e);
                        }
                        break;

                        
                        // *** Invers ***
                        case 3:
                        System.out.println("1. Metode OBE");
                        System.out.println("2. Metode Adjoin");
                        System.out.println("3. Kembali");
                        
                        try{
                            int metodeInverse = InputOutput.getValidIntegerInput(scanner, "Pilih Metode: ");
                            switch (metodeInverse) {
                                case 1:
                                double matrix[][] = BasicFunction.inputMatrix();
                                double resultMatrix[][] = Inverse.InverseERO(matrix);
                                System.out.println("Hasil Matrix:");
                                BasicFunction.printMatrix(resultMatrix);
                                if(isSolutionValid){
                                    InputOutput.writeMatrixFile(resultMatrix);
                                }
                                
                                break;
                                case 2:
                                double matrix2[][] = BasicFunction.inputMatrix();
                                double resultMatrix2[][] = Inverse.InverseCofactor(matrix2);
                                System.out.println("Hasil Matrix:");
                                BasicFunction.printMatrix(resultMatrix2);
                                if(isSolutionValid){
                                    InputOutput.writeMatrixFile(resultMatrix2);
                                }
                                break;
                                case 3:
                                break;
                                default:
                                System.out.println("Input tidak valid");
                                break;
                            }
                    }catch(Exception e3 ){
                        System.err.println("Error : " + e3);
                    }
                    break;
                    case 4:
                    double result = PolinomialInterpolation.polinomialInterpolationSolver();
                    InputOutput.writeDoubleFile(result);
                    break;
                case 5:
                    System.out.println("1. Kuadratik Berganda");
                    System.out.println("2. Linear Berganda");
                    System.out.println("3. Kembali");
                    System.out.print("Pilih Metode: ");
                    int metodeRegresi = scanner.nextInt();
                    switch(metodeRegresi){
                        case 1:
                        double resultStr = MultipleLinearRegression.multipleLinearRegression();

                        if (resultStr != 0.2567){
                            System.out.println("Save Hasil ke file?(Y/n)");
                            while (true){
                                char save = scanner.next().charAt(0);
                                if (save == 'Y'){
                                    System.err.println("Masukkan nama file: ");
                                    String filename = scanner.next();
                                    try{InputOutput.writeDoubleToFile(resultStr, filename);}
                                    catch (Exception e){System.out.println("an Error occured, file cannot be saved.");}
                                    break;
                                }
                            }
                        }
                        case 2:
                    }
                    break;
                case 6:
                    InterpolasiBicubicSpline.bicubicSpline();
                    break;
                case 7:
                    try{
                        ImageResizer.resizeImage();
                    }
                    catch(Exception e){
                        System.err.println("Error: " + e);
                    }
                    break;
                    
                case 8:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Input tidak valid");
                    break;
            }
        }
        }
    }
    
}