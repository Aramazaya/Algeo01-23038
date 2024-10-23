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

import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMENU");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks Balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Regresi Linear dan Kuadratik Berganda");
            System.out.println("6. Interpolasi Bicubic Spline");
            System.out.println("7. Keluar");
            
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
                                    writeStringFile(resultStr2);
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
                                BasicFunction.printArray(CramerSolution);
                            }
                            System.err.println("Save Hasil ke file?(Y/n)");
                            while (true){
                                char save = scanner.next().charAt(0);
                                if (save == 'Y'){
                                    System.err.println("Masukkan nama file: ");
                                    String filename = scanner.next();
                                    try{writeArrayFile(CramerSolution);}
                                    catch (Exception e){System.out.println("an Error occured, file cannot be saved.");}
                            try {
                                if (CramerSolution != null) {
                                    BasicFunction.printArray(CramerSolution);
                                }
                                if(isSolutionValid){
                                    writeArrayFile(CramerSolution);
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
                    }
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
                                break;
                            case 2:
                                double matrix2[][] = BasicFunction.inputMatrix();
                                double resultFloat2 = CofactorExpansion.determinant(matrix2);
                                System.out.println("Determinan: " + resultFloat2);
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

                case 3:
                    System.out.println("1. Metode OBE");
                    System.out.println("2. Metode Adjoin");
                    System.out.println("3. Kembali");
                    
                    int metodeInverse = InputOutput.getValidIntegerInput(scanner, "Pilih Metode: ");
                    switch (metodeInverse) {
                        case 1:
                            double matrix[][] = BasicFunction.inputMatrix();
                            double resultMatrix[][] = Inverse.InverseERO(matrix);
                            BasicFunction.printMatrix(resultMatrix);
                            break;
                        case 2:
                            double matrix2[][] = BasicFunction.inputMatrix();
                            double resultMatrix2[][] = Inverse.InverseCofactor(matrix2);
                            BasicFunction.printMatrix(resultMatrix2);
                            break;
                        case 3:
                            break;
                        default:
                            System.out.println("Input tidak valid");
                            break;
                    }
                    break;
                case 4:
                    double matrix[][] = BasicFunction.inputMatrix();
                    double resultFloat = PolinomialInterpolation.polinomialInterpolation(matrix);
                    System.out.println("Hasil interpolasi: " + resultFloat);
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
                    System.out.println("test");
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Input tidak valid");
                    break;
            }
        }
    }
    

    public static void writeMatrixFile(double[][] matrix) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String outputPath;
        while (true) {
            System.out.println("Save Hasil ke file?(Y/n)");
            char save = scanner.next().charAt(0);
            if (save == 'Y' || save == 'y') {
                System.out.println("Masukkan nama file: ");
                String filename = scanner.next();
                outputPath = String.format("data/%s", filename + ".txt");
                
                boolean isFileExists = InputOutput.checkFilePath(outputPath);
                if (isFileExists){
                    System.out.println("Terdapat file dengan nama yang sama apakah anda ingin overwrite? (y/N)");
                    char overwrite = scanner.next().charAt(0);
                    if (overwrite == 'Y' || overwrite == 'y'){
                        InputOutput.writeMatrixToFile(matrix, outputPath);
                        System.out.println("File berhasil di simpan pada" + outputPath);
                    }
                    else if (save == 'n' || save == 'N') {
                        System.out.println("File tidak disimpan\n");
                    } else {
                        System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
                    }
                }
                else{
                    InputOutput.writeMatrixToFile(matrix, outputPath);
                    System.out.println("File berhasil di simpan pada " + outputPath);
                }

                break;
            }

            else if (save == 'n' || save == 'N') {
                System.out.println("File tidak disimpan.\n");
                break; // Exit the loop without saving
            } else {
                System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
            }
        }
    }

    public static void writeArrayFile(double[] array) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String outputPath;
        
        while (true) {
            System.out.println("Save Hasil ke file?(Y/n)");
            char save = scanner.next().charAt(0);
            if (save == 'Y' || save == 'y') {
                System.out.println("Masukkan nama file: ");
                String filename = scanner.next();
                outputPath = String.format("data/%s", filename + ".txt");
                
                boolean isFileExists = InputOutput.checkFilePath(outputPath);
                if (isFileExists){
                    System.out.println("Terdapat file dengan nama yang sama apakah anda ingin overwrite? (y/N)");
                    char overwrite = scanner.next().charAt(0);
                    if (overwrite == 'Y' || overwrite == 'y'){
                        InputOutput.writeArrayToFile(array, outputPath);
                        System.out.println("File berhasil di simpan pada " + outputPath);
                    }
                    else if (save == 'n' || save == 'N') {
                        System.out.println("File tidak disimpan\n");
                    } else {
                        System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
                    }
                }
                else{
                    InputOutput.writeArrayToFile(array, outputPath);
                    System.out.println("File berhasil di simpan pada" + outputPath);
                }
                
                break;
            }
            
            else if (save == 'n' || save == 'N') {
                System.out.println("File tidak disimpan.\n");
                break; // Exit the loop without saving
            } else {
                System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
            }
        }
    }

    public static void writeStringFile(String string) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String outputPath;
        
        while (true) {
            System.out.println("Save Hasil ke file?(Y/n)");
            char save = scanner.next().charAt(0);
            if (save == 'Y' || save == 'y') {
                System.out.println("Masukkan nama file: ");
                String filename = scanner.next();
                outputPath = String.format("data/%s", filename + ".txt");
                
                boolean isFileExists = InputOutput.checkFilePath(outputPath);
                if (isFileExists){
                    System.out.println("Terdapat file dengan nama yang sama apakah anda ingin overwrite? (y/N)");
                    char overwrite = scanner.next().charAt(0);
                    if (overwrite == 'Y' || overwrite == 'y'){
                        InputOutput.writeStringToFile(string, outputPath);
                        System.out.println("File berhasil di simpan pada " + outputPath);
                    }
                    else if (save == 'n' || save == 'N') {
                        System.out.println("File tidak disimpan\n");
                    } else {
                        System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
                    }
                }
                else{
                    InputOutput.writeStringToFile(string, outputPath);
                    System.out.println("File berhasil di simpan pada" + outputPath);
                }
                
                break;
            }
            
            else if (save == 'n' || save == 'N') {
                System.out.println("File tidak disimpan.\n");
                break; // Exit the loop without saving
            } else {
                System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
            }
        }
    }
}