package regression;

import primitive.BasicFunction;
import primitive.GaussElimination;
import primitive.InputOutput;

public class MultipleQuadraticRegression {
    private static int numOfVar(double[][] IndependentVar){
        int fact = BasicFunction.factorial(IndependentVar[0].length)/BasicFunction.factorial(2)*BasicFunction.factorial(IndependentVar[0].length-2);
        return IndependentVar[0].length*2+fact;
    }
    private static boolean getCoefficient(double[][] IndependentVar, double[][] DependentVar, DoubleWrapper Coeff){
        double[][] EROMatrix = new double[IndependentVar[0].length+1][numOfVar(IndependentVar)+2];
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
        //Remaining Rows
        for (int row = 1; row < EROMatrix.length; row++){
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
        }
        String result = GaussElimination.gaussElimination(EROMatrix);
        BasicFunction.printMatrix(EROMatrix);
        if (result=="No solutions found."){
            System.out.println("The Equation has no solution. The model cannot be loaded.");
            return false;
        }
        else if (result=="Infinite solutions found."){
            System.out.println("The Equation has infinite solutions. The model cannot be loaded.");
            return false;
        }
        else {
            Coeff.value = GaussElimination.normalBackSubstitution(EROMatrix);
            return true;
        }
    }
    public static double multipleQuadRegression() {
        System.out.println("Masukan jumlah sampel dan variabel dalam satu baris dengan spasi: ");
        int n = 0;
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
                String filename = BasicFunction.readInput();
                InputOutput.readInputRegression(filename, Variables, Predictors, m, n);
                break;
            } else if (choice == 'N' || choice == 'n'){
                Variables = MultipleLinearRegression.inputMatrixReg(n, m);
                Predictors = MultipleLinearRegression.inputArrayReg(m);
                break;
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
            for (int j = i; j<IndependentVariable[0].length; j++){
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
