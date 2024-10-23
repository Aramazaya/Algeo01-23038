package main;
import primitive.*;
import regression.*;
import interpolation.*;
import bicubicspline.*;
//import Algeo.BicubicSpline;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("MENU");
            System.out.println("1. Sistem Persamaan Linier");
            System.out.println("2. Determinan");
            System.out.println("3. Matriks Balikan");
            System.out.println("4. Interpolasi Polinom");
            System.out.println("5. Regresi Linear dan Kuadratik Berganda");
            System.out.println("6. Interpolasi Bicubic Spline");
            System.out.println("7. Keluar");
            System.out.print("Pilih Menu: ");
            int menu = scanner.nextInt();
            switch(menu){
                case 1:
                    System.out.println("1. Metode eliminasi Gauss");
                    System.out.println("2. Metode eliminasi Gauss-Jordan");
                    System.out.println("3. Kaidah Cramer");
                    System.out.print("Pilih Metode: ");
                    int metode = scanner.nextInt();
                    switch(metode){
                        case 1:
                            double matrix1[][] = BasicFunction.inputMatrix();
                            String resultStr = GaussElimination.gaussElimination(matrix1);
                            System.out.println(resultStr);
                            break;
                        case 2:
                            double matrix2[][] = BasicFunction.inputMatrix();
                            String resultStr2 = GaussJordanElimination.gaussJordanElimination(matrix2);
                            System.out.println(resultStr2);
                            break;
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
                                    try{InputOutput.writeArrayFile(CramerSolution, filename);}
                                    catch (Exception e){System.out.println("an Error occured, file cannot be saved.");}
                                    break;
                                }
                            }
                            break;
                        default:
                            System.out.println("Input tidak valid");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("1. Metode reduksi baris");
                    System.out.println("2. Metode ekspansi kofaktor");
                    System.out.println("3. Kembali");
                    System.out.print("Pilih Metode: ");
                    int metodeDeterminan = scanner.nextInt();
                    switch(metodeDeterminan){
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
                    break;
                case 3:
                    System.out.println("1. Metode OBE");
                    System.out.println("2. Metode Adjoin");
                    System.out.println("3. Kembali");
                    System.out.print("Pilih Metode: ");
                    int metodeInverse = scanner.nextInt();
                    switch(metodeInverse){
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
                    //Tolong tambahin inputan
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
                        String resultStr = MultipleLinearRegression.multipleLinearRegression();
                        if (resultStr == "Success"){
                            System.out.println("Save Hasil ke file?(Y/n)");
                            while (true){
                                char save = scanner.next().charAt(0);
                                if (save == 'Y'){
                                    System.err.println("Masukkan nama file: ");
                                    String filename = scanner.next();
                                    try{InputOutput.writeArrayFile(MultipleLinearRegression.Coeff.value, filename);}
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
}