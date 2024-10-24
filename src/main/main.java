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
                    System.out.println("4. Kembali");
                    
                    int metode = InputOutput.getValidIntegerInput(scanner, "Pilih Metode: ");
                    switch (metode) {
                        // *** GAUSS ***
                        case 1:
                        String resultString = GaussElimination.driverGaussElimination();
                        System.out.println(resultString);
                        if (resultString != "0.267"){
                            while (true){
                                System.out.println("Masukan Hasil ke file?(Y/n)");
                                char save = scanner.next().charAt(0);
                                if (save == 'Y' || save == 'y'){
                                    System.out.println("Masukkan path ke file e.g (D:/Documents/out.txt): ");
                                    String filename = scanner.next();
                                    try{InputOutput.writeStringToFile(resultString, filename);}
                                    catch (Exception e){System.out.println("an Error occured, file cannot be saved.");}
                                    break;
                                }
                                else if (save == 'N' || save == 'n'){
                                    break;
                                } else{
                                    System.out.println("Masukan tidak valid.");
                                }
                            }
                        }
                        break;
                        // *** GAUSS JORDAN ***
                        case 2:
                        
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
                        case 4:break;
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
                                double resultFloat = Determinant.driverRowReductionDet();
                                if (resultFloat != 0.267){
                                    while (true){
                                        System.out.println("Masukan Hasil ke file?(Y/n)");
                                        char save = scanner.next().charAt(0);
                                        if (save == 'Y' || save == 'y'){
                                            System.out.println("Masukkan path ke file e.g (D:/Documents/out.txt): ");
                                            String filename = scanner.next();
                                            try{InputOutput.writeDoubleToFile(resultFloat, filename);}
                                            catch (Exception e){System.out.println("an Error occured, file cannot be saved.");}
                                            break;
                                        }
                                        else if (save == 'N' || save == 'n'){
                                            break;
                                        } else{
                                            System.out.println("Masukan tidak valid.");
                                        }
                                    }
                                }
                                break;
                                case 2:
                                double resultFloat2 = CofactorExpansion.driverCofactorDet();
                                if (resultFloat2 != 0.267){System.out.println("Masukan hasil ke file?(Y/n)");
                                while (true){
                                    char save = scanner.next().charAt(0);
                                    if (save == 'Y' || save == 'y'){
                                        System.out.println("Masukkan path ke file e.g (D:/Documents/out.txt): ");
                                        String filename = scanner.next();
                                        try{InputOutput.writeDoubleToFile(resultFloat2, filename);}
                                        catch (Exception e){System.out.println("an Error occured, file cannot be saved.");}
                                        break;
                                    }
                                    else if (save == 'N' || save == 'n'){
                                        break;
                                    } else {
                                        System.out.println("Masukan tidak valid.");
                                    }
                                }
                                break;}
                                else{break;}
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
                    System.out.println("Masukkan titik (x,y) dengan format \"x<spasi>y\" ");
                    System.out.println("Masukkan titik yang ingin ditaksir pada baris terakhir diikuti dengan nol \"x 0\"");
                    double matrix[][] = BasicFunction.inputPolinomial();
                    double resultFloat = PolinomialInterpolation.polinomialInterpolation(matrix);
                    System.out.println("Hasil interpolasi: " + resultFloat);
                    if(isSolutionValid){
                        InputOutput.writeDoubleFile(resultFloat);
                    }
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