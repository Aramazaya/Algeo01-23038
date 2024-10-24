import java.io.IOException;

import interpolation.PolinomialInterpolation;
import primitive.BasicFunction;
import primitive.GaussElimination;
import primitive.GaussJordanElimination;
import primitive.InputOutput;

public class driver_testcase {

    public static void main(String[] args) throws IOException {
        StringBuilder output = new StringBuilder();

        // SPL Augmented
        double[][] matrix1a = InputOutput.readMatrixFile("test/Input/1a.txt");
        double[][] matrix1b = InputOutput.readMatrixFile("test/Input/1b.txt");
        double[][] matrix1c = InputOutput.readMatrixFile("test/Input/1c.txt");
        double[][] matrix1d1 = InputOutput.readMatrixFile("test/Input/1d1.txt");
        double[][] matrix1d2 = InputOutput.readMatrixFile("test/Input/1d2.txt");
        double[][] matrix2a = InputOutput.readMatrixFile("test/Input/2a.txt");
        double[][] matrix2b = InputOutput.readMatrixFile("test/Input/2b.txt");
        double[][] matrix3a = InputOutput.readMatrixFile("test/Input/3a.txt");
        double[][] matrix3b = InputOutput.readMatrixFile("test/Input/3b.txt");
        double[][] matrix4 = InputOutput.readMatrixFile("test/Input/4.txt");

        output.append("1a\n").append(GaussJordanElimination.gaussJordanElimination(matrix1a)).append("\n");
        output.append("1b\n").append(GaussJordanElimination.gaussJordanElimination(matrix1b)).append("\n");
        output.append("1c\n").append(GaussJordanElimination.gaussJordanElimination(matrix1c)).append("\n");
        output.append("1d1\n").append(GaussJordanElimination.gaussJordanElimination(matrix1d1)).append("\n");
        output.append("1d2\n").append(GaussJordanElimination.gaussJordanElimination(matrix1d2)).append("\n");
        output.append("2a\n").append(GaussJordanElimination.gaussJordanElimination(matrix2a)).append("\n");
        output.append("2b\n").append(GaussJordanElimination.gaussJordanElimination(matrix2b)).append("\n");
        output.append("3a\n").append(GaussJordanElimination.gaussJordanElimination(matrix3a)).append("\n");
        output.append("3b\n").append(GaussJordanElimination.gaussJordanElimination(matrix3b)).append("\n");
        output.append("4\n").append(GaussJordanElimination.gaussJordanElimination(matrix4)).append("\n");
        
        // output.append("1a\n").append(GaussElimination.gaussElimination(matrix1a)).append("\n");
        // output.append("1b\n").append(GaussElimination.gaussElimination(matrix1b)).append("\n");
        // output.append("1c\n").append(GaussElimination.gaussElimination(matrix1c)).append("\n");
        // output.append("1d1\n").append(GaussElimination.gaussElimination(matrix1d1)).append("\n");
        // output.append("1d2\n").append(GaussElimination.gaussElimination(matrix1d2)).append("\n");
        // output.append("2a\n").append(GaussElimination.gaussElimination(matrix2a)).append("\n");
        // output.append("2b\n").append(GaussElimination.gaussElimination(matrix2b)).append("\n");
        // output.append("3a\n").append(GaussElimination.gaussElimination(matrix3a)).append("\n");
        // output.append("3b\n").append(GaussElimination.gaussElimination(matrix3b)).append("\n");
        // output.append("4\n").append(GaussElimination.gaussElimination(matrix4)).append("\n");
        
        // try {
        //     output.append("1a\n").append(primitive.Cramer.CramerSolver(matrix1a)).append("\n");
   
        // } catch (Exception e) {
        //     output.append("1a " + e + "\n");
        // }
        // try {
        //     output.append("1b\n").append(primitive.Cramer.CramerSolver(matrix1b)).append("\n");
        // } catch (Exception e) {
        //     output.append("1b " + e + "\n");
        // }
        // try {
        //     output.append("1c\n").append(primitive.Cramer.CramerSolver(matrix1c)).append("\n");
        // } catch (Exception e) {
        //     output.append("1c " + e + "\n");
        // }
        // try {
        //     output.append("1d1\n").append(primitive.Cramer.CramerSolver(matrix1d1)).append("\n");
        // } catch (Exception e) {
        //     output.append("1d1 " + e + "\n");
        // }
        // try {
        //     output.append("1d2\n").append(primitive.Cramer.CramerSolver(matrix1d2)).append("\n");
        // } catch (Exception e) {
        //     output.append("1d2 " + e + "\n");
        // }
        // try {
        //     output.append("2a\n").append(primitive.Cramer.CramerSolver(matrix2a)).append("\n");
        // } catch (Exception e) {
        //     output.append("2a " + e + "\n");
        // }
        // try {
        //     output.append("2b\n").append(primitive.Cramer.CramerSolver(matrix2b)).append("\n");
        // } catch (Exception e) {
        //     output.append("2b " + e + "\n");
        // }
        // try {
        //     output.append("3a\n").append(primitive.Cramer.CramerSolver(matrix3a)).append("\n");
        // } catch (Exception e) {
        //     output.append("3a " + e + "\n");
        // }
        // try {
        //     output.append("3b\n").append(primitive.Cramer.CramerSolver(matrix3b)).append("\n");
        // } catch (Exception e) {
        //     output.append("3b " + e + "\n");
        // }
        // try {
        //     output.append("4\n").append(primitive.Cramer.CramerSolver(matrix4)).append("\n");
        // } catch (Exception e) {
        //     output.append("4 " + e + "\n");
        // }

        // Interpolasi Polinomial
        
        double[][] matrix5a1 = InputOutput.readMatrixFile("test/Input/5a1.txt");
        double[][] matrix5a2 = InputOutput.readMatrixFile("test/Input/5a2.txt");
        double[][] matrix5a3 = InputOutput.readMatrixFile("test/Input/5a3.txt");
        double[][] matrix5a4 = InputOutput.readMatrixFile("test/Input/5a4.txt");
        double[][] matrix5b1 = InputOutput.readMatrixFile("test/Input/5b1.txt");
        double[][] matrix5b2 = InputOutput.readMatrixFile("test/Input/5b2.txt");
        double[][] matrix5b3 = InputOutput.readMatrixFile("test/Input/5b3.txt");
        double[][] matrix5b4 = InputOutput.readMatrixFile("test/Input/5b4.txt");

        
        double result5a1 = PolinomialInterpolation.polinomialInterpolation(matrix5a1);
        System.out.println(result5a1);
        double result5a2 = PolinomialInterpolation.polinomialInterpolation(matrix5a2);
        System.out.println(result5a2);
        double result5a3 = PolinomialInterpolation.polinomialInterpolation(matrix5a3);
        System.out.println(result5a3);
        double result5a4 = PolinomialInterpolation.polinomialInterpolation(matrix5a4);
        System.out.println(result5a4);
        double result5b1 = PolinomialInterpolation.polinomialInterpolation(matrix5b1);
        System.out.println(result5b1);
        double result5b2 = PolinomialInterpolation.polinomialInterpolation(matrix5b2);
        System.out.println(result5b2);
        double result5b3 = PolinomialInterpolation.polinomialInterpolation(matrix5b3);
        System.out.println(result5b3);
        double result5b4 = PolinomialInterpolation.polinomialInterpolation(matrix5b4);
        System.out.println(result5b4);

        double start = 0;
        double end = 2;
        int number = 5;
        double h = (end - start) / number;
        double[][] points = new double[number + 2][2];

        for (int i = 0; i <= number; i++) {
            double x = start + i * h;
            double y = f(x);
            points[i][0] = x;
            points[i][1] = y;
        }

        double xEstimate = 8;
        points[number + 1][0] = xEstimate;
        points[number + 1][1] = 0;

        System.out.println("INTERPOLATED EQUATION");
        double result = PolinomialInterpolation.polinomialInterpolation(points);
        System.out.println(result);
        try {
            // double[] interpolatedEquation = PolinomialInterpolation.polinomialInterpolationEquation(points);
            // output.append("5c = ").append(interpolatedEquation).append("\n");
            // output.append("X = " + xEstimate + "; Real Value = " + f(xEstimate));
        } catch (IllegalStateException e) {
            output.append("5c Determinant is zero\n");
        }

        System.out.println(output.toString());
        String outputString = output.toString();
        String outputPath = "test/output/testCaseOutput.txt";
        InputOutput.writeStringToFile(outputString, outputPath);
    }


    public static double f(double x) {
        return ((Math.pow(x, 2) + Math.sqrt(x)) / (Math.exp(x) + x));
    }
}
