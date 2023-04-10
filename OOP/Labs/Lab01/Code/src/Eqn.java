import javax.swing.*;
import java.util.*;

public class Eqn{
    public static void main(String[] args){
        String option="";
        while (option.equals("1") == false && option.equals("2") == false){
            option=JOptionPane.showInputDialog(null, "Select 1 if you want to solve single variable equation, select 2 if you want to solve a system of equations");
        }

        if (option.equals("1")){
            System.out.println(FirstDegree());
        }
        else if (option.equals("2")){
            System.out.println(FirstDegreeSys());
        }
    }
    public static String FirstDegree(){
        Scanner s=new Scanner(System.in);
        float a = s.nextFloat();
        float b = s.nextFloat();
        s.close();

        if (a==0){
            if (b==0){
                return "Infinite solutions";
            }
            else{
                return "No solution";
            }
        }
        else{
            return "Unique solution: "+String.format("%.2f",(-b/a));
        }
    }
    public static String FirstDegreeSys(){
        Scanner s=new Scanner(System.in);
        float a1 = s.nextFloat();
        float b1 = s.nextFloat();
        float c1 = s.nextFloat();
        float a2 = s.nextFloat();
        float b2 = s.nextFloat();
        float c2 = s.nextFloat();
        s.close();
        float D=a1*b2-a2*b1;
        float Dx=c1*b2-c2*b1;
        float Dy=a1*c2-a2*c1;
        if (D==0){
            if (Dx !=0 || Dy !=0){
                return "No solution";
            }
            else{
                return "Infinite solutions";
            }
        }
        else{
            return "Single solution: "+String.format("(%.2f, %.2f)",Dx/D,Dy/D);
        }
    }
}