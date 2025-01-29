package practice_projects.languages.java.beginner;



public class PascalTriangle {
    
    static int factorialTernary(int num) {
        return (num == 1 || num == 0) ? 1 : num * factorialTernary(num - 1);
    }

    static void pascalTriangle(int row) {
        int outerLoopIndex, InnerLoopIndex;     
        for (outerLoopIndex = 0; outerLoopIndex <= row; outerLoopIndex++) {            
            for (InnerLoopIndex = 0; InnerLoopIndex <= row - outerLoopIndex; InnerLoopIndex++) {
                System.out.print(" ");                
            }
            for (InnerLoopIndex = 0; InnerLoopIndex <= outerLoopIndex; InnerLoopIndex++) {
                System.out.print(" " +
                                      factorialTernary(outerLoopIndex) 
                                      /*--------- */  /  /*--------- */
                (factorialTernary(outerLoopIndex - InnerLoopIndex) * factorialTernary(InnerLoopIndex)));
            }
            System.out.println();
        }
    }
            
    public static void main(String[] args) {
        pascalTriangle(5);
    }    
}


