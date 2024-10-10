package Algeo.Primitive;
public class inverse {
    public static double[][] Inverse(double[][] Matrix){
        double det = CofactorExpansion.determinant(Matrix);
        if(det == 0){
            System.out.println("Inverse does not exist");
            return null;
        }
        double[][] adjugate = new double[Matrix.length][Matrix.length];
        double[][] inverse = new double[Matrix.length][Matrix.length];
        for(int i = 0; i < Matrix.length; i++){
            for(int j = 0; j < Matrix.length; j++){
                double[][] cofactor = CofactorExpansion.takeCofactor(Matrix, i, j);
                adjugate[i][j] = Math.pow(-1, i+j) * CofactorExpansion.determinant(cofactor);
            }
        }
        inverse = BasicFunction.transpose(adjugate);
        for(int i = 0; i < Matrix.length; i++){
            for(int j = 0; j < Matrix.length; j++){
                inverse[i][j] /= det;
            }
        }
        return inverse;
    }
}
