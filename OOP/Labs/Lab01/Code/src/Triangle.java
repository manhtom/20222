import java.util.*;

public class Triangle {
    public static void main(String[]  args){
        Scanner s=new Scanner(System.in);
        int n = s.nextInt();
        s.close();
        for (int k=0; k < n; k++) { // loop k lan
            String d="%"+(int)((2*n-1)/2-k+1)+"s";
            System.out.printf(d,"");
            for (int j=1; j <= 2*(k+1)-1; j++){ // loop 2k-1 lan
                System.out.printf("%s","*");
            }
            System.out.println(""); // break line
        }
    }
}

/// check lai ngay tai sao k=0 lai dc ma k=1 k dc
// min laf %1s