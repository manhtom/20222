//import java.util.*;
import javax.swing.*;

public class FirstDegree {
    public static void Eqn(){
        float a = Float.parseFloat(JOptionPane.showInputDialog(null, "The equation to solve will have the form: ax+b=0.\nPlease enter a:"));
        float b = Float.parseFloat(JOptionPane.showInputDialog(null, "Please enter b:"));

        if (a==0){
            if (b==0){
                JOptionPane.showMessageDialog(null,"This equation has infinite solutions.");
            }
            else{
                JOptionPane.showMessageDialog(null,"This equation has no solution.");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"This equation has a unique solution: "+String.format("%.2f",(-b/a)));
        }
    }
    public static void EqnSys(){
        int j=0;
        float[] input=new float[6];
        for (int k=1; k<=2; k++){
            float temp=Float.parseFloat(JOptionPane.showInputDialog(null,String.format("The equation to solve will have the form:\na1*x+b1*y=c1\na2*x+b2*y=c2\nPlease enter a%d:",k)));
            input[j]=temp;
            j++;
            temp=Float.parseFloat(JOptionPane.showInputDialog(null,String.format("The equation to solve will have the form:\na1*x+b1*y=c1\na2*x+b2*y=c2\nPlease enter b%d:",k)));
            input[j]=temp;
            j++;
            temp=Float.parseFloat(JOptionPane.showInputDialog(null,String.format("The equation to solve will have the form:\na1*x+b1*y=c1\na2*x+b2*y=c2\nPlease enter c%d:",k)));
            input[j]=temp;
            j++;

        }
        float D=input[0]*input[4]-input[3]*input[1];
        float Dx=input[2]*input[4]-input[5]*input[1];
        float Dy=input[0]*input[5]-input[3]*input[2];
        if (D==0){
            if (Dx !=0 || Dy !=0){
               JOptionPane.showMessageDialog(null,"This system of equations has no solution.");
            }
            else{
                JOptionPane.showMessageDialog(null,"This system of equations has infinite solutions.");
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"This system of equations has a unique solution: "+String.format("(%.2f, %.2f)",Dx/D,Dy/D));
        }
    }
}
