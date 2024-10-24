package main;
import primitive.GaussElimination;
import primitive.GaussJordanElimination;
import primitive.Cramer;
import primitive.Determinant;
import primitive.CofactorExpansion;
import primitive.Inverse;
import primitive.SPLMatriksBalikan;
import primitive.InputOutput;
import interpolation.PolinomialInterpolation;
import regression.MultipleLinearRegression;
import regression.MultipleQuadraticRegression;
import bicubicspline.*;

import java.util.Scanner;

public class main {
    public static String resultString;
    public static double resultFloat;
    public static void main(String[] args) throws Exception{
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

            switch (menu) {
                // *** Sistem Persamaan Linier ***
                case 1:
                    System.out.println("1. Metode eliminasi Gauss");
                    System.out.println("2. Metode eliminasi Gauss-Jordan");
                    System.out.println("3. Kaidah Cramer");
                    System.out.println("4. Metode matriks balikan");
                    System.out.println("5. Kembali");
                    
                    int metode = InputOutput.getValidIntegerInput(scanner, "Pilih Metode: ");
                    switch (metode) {
                        // *** GAUSS ***
                        case 1:
                        resultString = GaussElimination.driverGaussElimination();
                        if (resultString != "0.267"){
                            while(true){
                                try {
                                    InputOutput.writeStringFile(resultString);
                                    break;   
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                            }
                        }
                        break;
                        // *** GAUSS JORDAN ***
                        case 2:
                        resultString = GaussJordanElimination.driverGaussJordanElimination();
                        System.out.println(resultString);
                        if (resultString != "0.267"){
                            while(true){
                                try {
                                    InputOutput.writeStringFile(resultString);
                                    break;
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                            }
                        }
                        break;
                    
                        // *** CRAMER ***
                        case 3:
                            try {
                                double[] resultCramer = Cramer.driverCramerSolver();
                                if (resultCramer[0] != 0.267){
                                    while(true){
                                        try {
                                            InputOutput.writeArrayFile(resultCramer);
                                            break;
                                        } catch (Exception e) {
                                            System.err.println(e);
                                        }

                                    }
                                }
                                
                            } catch (Exception e) {
                                System.err.println(e);
                            }
                            break;
                        case 4:
                        resultString = SPLMatriksBalikan.driverSPLInverse();
                        if (resultString != "0.267"){
                            while(true){
                                try {
                                    InputOutput.writeStringFile(resultString);
                                    break;
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                            }
                        }
                        break;
                        case 5 : break;
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
                                resultFloat = Determinant.driverRowReductionDet();
                                if (resultFloat != 0.267){
                                    while(true){
                                        try {
                                            InputOutput.writeDoubleFile(resultFloat);                                            
                                            break;
                                        } catch (Exception e) {
                                            System.err.println(e);
                                        }

                                    }
                                }
                                break;
                                case 2:
                                resultFloat = CofactorExpansion.driverCofactorDet();
                                if (resultFloat != 0.267){
                                    while(true){
                                        try {
                                            InputOutput.writeDoubleFile(resultFloat);
                                            break;
                                        } catch (Exception e) {
                                            System.err.println(e);
                                        }

                                    }
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
                                double[][] inverseERO = Inverse.driverInverseERO();
                                if (inverseERO!=null){
                                    while(true){
                                        try {
                                            InputOutput.writeMatrixFile(inverseERO);
                                            break;
                                        } catch (Exception e) {
                                            System.err.println(e);
                                        }
                                        
                                    }
                                }
                                break;
                                case 2:
                                double[][] inverseCofac = Inverse.driverInverseCofactor();
                                if (inverseCofac!=null){
                                    while (true) {
                                        try {
                                            InputOutput.writeMatrixFile(inverseCofac);
                                            break;
                                        } catch (Exception e) {
                                            System.err.println(e);
                                        }
                                    }
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
                    try {
                        double result = PolinomialInterpolation.polinomialInterpolationSolver();
                        while (true) {
                            try {
                                InputOutput.writeDoubleFile(result);
                                break;
                            } catch (Exception e) {
                                System.err.println(e);
                            }
                            
                        }
                    } catch (Exception e) {
                        System.err.println(e);
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
                        resultFloat = MultipleLinearRegression.multipleLinearRegression();
                        if (resultFloat != 0.267){
                            while (true) {
                                try {
                                    InputOutput.writeDoubleFile(resultFloat);
                                    break;
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                            }
                        }
                        break;
                        case 2:
                        resultFloat = MultipleQuadraticRegression.multipleQuadRegression();
                        if (resultFloat != 0.267){
                            while (true) {
                                try {
                                    InputOutput.writeDoubleFile(resultFloat);
                                    break;
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                            }
                        }
                        break;
                        default:
                        System.out.println("Input tidak valid");
                        break;
                    }
                    break;
                case 6:
                    resultString = InterpolasiBicubicSpline.driverBicubicSpline();
                    while (true) {
                        try {
                            InputOutput.writeStringFile(resultString);
                            break;
                        } catch (Exception e) {
                            System.err.println(e);
                        }
                        
                    }
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