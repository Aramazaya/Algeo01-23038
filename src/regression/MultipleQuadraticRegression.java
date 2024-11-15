package regression;

import java.util.Scanner;

import primitive.BasicFunction;
import primitive.Cramer;
import primitive.GaussElimination;
import primitive.InputOutput;

public class MultipleQuadraticRegression {
    private static int numOfVar(double[][] IndependentVar){
        int ds = 0;
        for (int i = 0; i<IndependentVar[0].length-1; i++){
            ds += i+1;
        }
        return IndependentVar[0].length*2+ds;
    }
    private static boolean getCoefficient(double[][] IndependentVar, double[][] DependentVar, DoubleWrapper Coeff){
        double[][] EROMatrix = new double[numOfVar(IndependentVar)+1][numOfVar(IndependentVar)+2];
        int n = IndependentVar.length; //Jumlah Sampel
        int k = IndependentVar[0].length; //Jumlah Variabel
        //Row 1                                                       
        EROMatrix[0][0] = n;
        //Sum of Independent Var
        int colIndex = 1;
        double[] sumIndependentVar = new double[k];
        for (int i = 0; i < k; i++){
            for (int j = 0; j<n; j++){
                sumIndependentVar[i] += IndependentVar[j][i];
            }
            EROMatrix[0][colIndex] = sumIndependentVar[i];
            colIndex++;
        }
        //Sum of Pairwise
        for (int i=0; i<k;i++){
            for (int j=i+1;j<k;j++){
                double sumPairwise = 0;
                for (int l=0; l<n; l++){
                    sumPairwise += IndependentVar[l][i]*IndependentVar[l][j];
                }
                EROMatrix[0][colIndex] = sumPairwise;
                colIndex++;
            }
        }
        //Sum of Quads
        for (int i=0; i<k; i++){
            double sumQuads = 0;
            for (int j=0; j<n; j++){
                sumQuads += IndependentVar[j][i]*IndependentVar[j][i];
            }
            EROMatrix[0][colIndex] = sumQuads;
            colIndex++;
        }
        //Sum of Dependent Var
        double sumDependentVar = 0;
        for (int i = 0; i<n; i++){
            sumDependentVar += DependentVar[i][0];
        }
        EROMatrix[0][colIndex] = sumDependentVar;
        System.out.println(EROMatrix.length);
        //Remaining Rows
        //Independent Rows
        for (int row = 1; row <= k; row++){
            //Column 1
            EROMatrix[row][0] = EROMatrix[0][row];
            //Sum of Independent Var*Independent
            colIndex = 1;
            for (int i = 0;i < k;i++){
                double sumProduct = 0;
                for (int j = 0; j < n;j ++){
                    sumProduct += IndependentVar[j][i]*IndependentVar[j][row-1];
                }
                EROMatrix[row][colIndex] = sumProduct;
                colIndex++;
            }
            //Sum of Pairwise*Independent
            for (int i=0; i<k;i++){
                for (int j=i+1;j<k;j++){
                    double sumPairwise = 0;
                    for (int l=0; l<n; l++){
                        sumPairwise += IndependentVar[l][i]*IndependentVar[l][j]*IndependentVar[l][row-1];
                    }
                    EROMatrix[row][colIndex] = sumPairwise;
                    colIndex++;
                }
            }
            //Sum of Quads*Independent
            for (int i = 0; i<k;i++){
                double sumQuads = 0;
                for (int j=0; j<n; j++){
                    sumQuads += IndependentVar[j][i]*IndependentVar[j][i]*IndependentVar[j][row-1];
                }
                EROMatrix[row][colIndex] = sumQuads;
                colIndex++;
            }
            //Sum of Dependent Var*Independent
            sumDependentVar = 0;
            for (int i = 0; i<n; i++){
                sumDependentVar += DependentVar[i][0]*IndependentVar[i][row-1];
            }
            EROMatrix[row][colIndex] = sumDependentVar;
        }
        //Pairwise Rows
        int row = k+1;
        for (int pair1 = 0; pair1 < k; pair1++){
            for (int pair2 = pair1+1; pair2 < k; pair2++){
                //Column 1
                EROMatrix[row][0] = EROMatrix[0][row];
                //Sum of Pair*Independent
                colIndex = 1;
                for (int i = 0;i < k;i++){
                    double sumProduct = 0;
                    for (int j = 0; j < n;j ++){
                        sumProduct += IndependentVar[j][pair1]*IndependentVar[j][pair2]*IndependentVar[j][i];
                    }
                    EROMatrix[row][colIndex] = sumProduct;
                    colIndex++;
                }
                //Sum of Pair*Pair
                for (int i=0; i<k;i++){
                    for (int j=i+1;j<k;j++){
                        double sumPairwise = 0;
                        for (int l=0; l<n; l++){
                            sumPairwise += IndependentVar[l][i]*IndependentVar[l][j]*IndependentVar[l][pair1]*IndependentVar[l][pair2];
                        }
                        EROMatrix[row][colIndex] = sumPairwise;
                        colIndex++;
                    }
                }
                //Sum of Pair*Quads
                for (int i = 0; i<k;i++){
                    double sumQuads = 0;
                    for (int j=0; j<n; j++){
                        sumQuads += IndependentVar[j][i]*IndependentVar[j][i]*IndependentVar[j][pair1]*IndependentVar[j][pair2];
                    }
                    EROMatrix[row][colIndex] = sumQuads;
                    colIndex++;
                }
                //Sum of Dependent Var*Pair
                sumDependentVar = 0;
                for (int i = 0; i<n; i++){
                    sumDependentVar += DependentVar[i][0]*IndependentVar[i][pair1]*IndependentVar[i][pair2];
                }
                EROMatrix[row][colIndex] = sumDependentVar;
                row++;
                
            }
        }
        //Quads Rows
        for (int quad = 0; quad < k; quad++){
            System.out.println("ROW: " + row);
            //Column 1
            EROMatrix[row][0] = EROMatrix[0][row];
            //Sum of Quads*Independent
            colIndex = 1;
            for (int i = 0;i < k;i++){
                double sumProduct = 0;
                for (int j = 0; j < n;j ++){
                    sumProduct += IndependentVar[j][quad]*IndependentVar[j][quad]*IndependentVar[j][i];
                }
                EROMatrix[row][colIndex] = sumProduct;
                colIndex++;
            }
            //Sum of Quads*Pair
            for (int i=0; i<k;i++){
                for (int j=i+1;j<k;j++){
                    double sumPairwise = 0;
                    for (int l=0; l<n; l++){
                        sumPairwise += IndependentVar[l][i]*IndependentVar[l][j]*IndependentVar[l][quad]*IndependentVar[l][quad];
                    }
                    EROMatrix[row][colIndex] = sumPairwise;
                    colIndex++;
                }
            }
            //Sum of Quads*Quads
            for (int i = 0; i<k;i++){
                double sumQuads = 0;
                for (int j=0; j<n; j++){
                    sumQuads += IndependentVar[j][i]*IndependentVar[j][i]*IndependentVar[j][quad]*IndependentVar[j][quad];
                }
                EROMatrix[row][colIndex] = sumQuads;
                colIndex++;
            }
            //Sum of Dependent Var*Quads
            sumDependentVar = 0;
            for (int i = 0; i<n; i++){
                sumDependentVar += DependentVar[i][0]*IndependentVar[i][quad]*IndependentVar[i][quad];
            }
            EROMatrix[row][colIndex] = sumDependentVar;
            row++;
        }
        double[] result = Cramer.CramerSolver(EROMatrix);
        BasicFunction.printArray(result);
        Coeff.value = result;
        return true;
    }
    public static double multipleQuadRegression() {
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
            System.out.print("Ambil variabel dari file?(Y/n) : ");
            try{char choice = BasicFunction.readInput().charAt(0);
            if (choice == 'Y' || choice == 'y'){
                System.out.print("Masukan path ke file (D:/Documents/regresi.txt): ");
                String filename = scanner.nextLine();
                InputOutput.readInputRegression(filename, Variables, Predictors, n, m);
                break;
            } else if (choice == 'N' || choice == 'n'){
                Variables = MultipleLinearRegression.inputMatrixReg(n, m);
                Predictors = MultipleLinearRegression.inputArrayReg(m);
                break;
            } else if (choice == 'C' || choice == 'c'){
                return 0.267;
            } else {
                System.out.println("Masukan tidak valid.");
            }
            } catch (Exception e){
                System.out.println("Error, silahkan coba lagi.");
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
        if (result == false) {
            return 0;
        }
        double results = 0;
        double[] Predictor = new double[numOfVar(IndependentVariable)];
        int colIndex = 0;
        for (int i = 0; i<IndependentVariable[0].length; i++){
            Predictor[colIndex] = Predictors[i];
            colIndex++;
        }
        for (int i = 0; i<IndependentVariable[0].length; i++){
            for (int j = i+1; j<IndependentVariable[0].length; j++){
                Predictor[colIndex] = Predictors[i]*Predictors[j];
                colIndex++;
            }
        }
        for (int i = 0; i<IndependentVariable[0].length; i++){
            Predictor[colIndex] = Predictors[i]*Predictors[i];
            colIndex++;
        }
        for (int i = 1; i<IndependentVariable[0].length; i++){
            results += coef.value[i]*Predictor[i-1];
        }
        results += coef.value[0];
        System.out.println("Hasil Prediksi Model : " + results);
        return results;
    }
}
