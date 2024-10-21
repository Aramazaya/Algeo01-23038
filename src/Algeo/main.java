import Primitive.BasicFunction;
import Primitive.GaussElimination;
import Primitive.GaussJordanElimination;
import Primitive.Cramer;
import Primitive.Determinant;
import Primitive.CofactorExpansion;
import Primitive.Inverse;
import Interpolance.PolinomialInterpolation;
import Regression.MultipleLinearRegression;
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
                            double matrix1[][] = Primitive.BasicFunction.inputMatrix();
                            String resultStr = Primitive.GaussElimination.gaussElimination(matrix1);
                            System.err.println(resultStr);
                            break;
                        case 2:
                            double matrix2[][] = Primitive.BasicFunction.inputMatrix();
                            String resultStr2 = Primitive.GaussJordanElimination.gaussJordanElimination(matrix2);
                            System.err.println(resultStr2);
                            break;
                        case 3:
                            double matrix[][] = Primitive.BasicFunction.inputMatrix();
                            double[] CramerSolution = Primitive.Cramer.CramerSolver(matrix);
                            if (CramerSolution != null){
                                Primitive.BasicFunction.printArray(CramerSolution);
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
                            double matrix[][] = Primitive.BasicFunction.inputMatrix();
                            double resultFloat = Primitive.Determinant.rowReductionDeterminant(matrix);
                            System.out.println("Determinan: " + resultFloat);
                            break;
                        case 2:
                            double matrix2[][] = Primitive.BasicFunction.inputMatrix();
                            double resultFloat2 = Primitive.CofactorExpansion.determinant(matrix2);
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
                            double matrix[][] = Primitive.BasicFunction.inputMatrix();
                            double resultMatrix[][] = Primitive.Inverse.InverseERO(matrix);
                            Primitive.BasicFunction.printMatrix(resultMatrix);
                            break;
                        case 2:
                            double matrix2[][] = Primitive.BasicFunction.inputMatrix();
                            double resultMatrix2[][] = Primitive.Inverse.InverseCofactor(matrix2);
                            Primitive.BasicFunction.printMatrix(resultMatrix2);
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
                    double matrix[][] = Primitive.BasicFunction.inputMatrix();                    
                    double resultFloat = Interpolance.PolinomialInterpolation.polinomialInterpolation(matrix);
                    System.out.println("Hasil interpolasi: " + resultFloat);
                    break;
                case 5:
                    String resultStr = Regression.MultipleLinearRegression.multipleLinearRegression();
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