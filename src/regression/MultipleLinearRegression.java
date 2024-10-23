package regression;
import primitive.BasicFunction;
import primitive.GaussElimination;
class DoubleWrapper {
    public double[] value;

    public DoubleWrapper(double[] value) {
        this.value = value;
    }
}
public class MultipleLinearRegression {
    private static boolean getCoefficient(double[][] IndependentVar, double[][] DependentVar, DoubleWrapper Coeff){
        double[][] EROMatrix = new double[IndependentVar[0].length+1][IndependentVar[0].length+2];
        int n = IndependentVar.length; //Jumlah Sampel
        int k = IndependentVar[0].length; //Jumlah Variabel
        for (int i=0; i<EROMatrix.length; i++){
            for (int j=0; j<k; j++){
                for (int l=0; l<n; l++){
                    if (i==0){EROMatrix[i][j+1] += IndependentVar[l][j];}
                    else {EROMatrix[i][j+1] += IndependentVar[l][i-1]*IndependentVar[l][j];}
                    System.out.println(EROMatrix[i][j]);
                    System.out.println("(" + i + ", " + j + ")");
                }
            }
        }
                BasicFunction.printMatrix(EROMatrix);
        for (int i=0; i<EROMatrix.length; i++){
            for (int j=0;j<EROMatrix.length;j++){
                System.out.println("(" + i + ", " + j + ")");
                if (i==0){EROMatrix[i][k+1] += DependentVar[j][0];}
                else {EROMatrix[i][k+1] += IndependentVar[j][i-1]*DependentVar[j][0];}
            }
        }
        EROMatrix[0][0] = n;
        for (int i=1; i<EROMatrix.length; i++){EROMatrix[i][0] = EROMatrix[0][i];}
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
    public static double multipleLinearRegression() {
        System.out.println("Insert Independent Variables as such:");
        System.out.println("x1i x2i x3i...xni yi");
        System.out.println("x1j x2j x3j...xnj yj");
        double[][] Variables = BasicFunction.inputMatrix();
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
            return 0;
        }
        System.out.println("The Model is loaded!!\nPlease insert the value of the independent variables to predict the dependent variable:");
        double[] Predictor = new double[IndependentVariable[0].length];
        while (true){try{String[] elements = BasicFunction.readInput().trim().split("\\s+");
        for (int i=0; i<IndependentVariable[0].length; i++) {
            Predictor[i] = Double.parseDouble(elements[i]);
        }break;}
        catch (Exception e){
            System.out.println("An Error Occured. Please Try Again.");
        }}
        double results = 0;
        for (int i = 1; i<IndependentVariable[0].length; i++){
            results += coef.value[i]*Predictor[i];
        }
        results += coef.value[0];
        System.out.println("The predicted value of the dependent variable is: " + results);
        return results;
    }
}