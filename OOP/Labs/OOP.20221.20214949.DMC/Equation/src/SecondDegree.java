//import java.util.*;
import javax.swing.*;

public class SecondDegree {
    public static void Eqn(){ //this method is implemented with javax.swing
        double a=Double.parseDouble(JOptionPane.showInputDialog(null,"Please enter the coefficient a:\nNote: a must be non-zero"));
        if (a==0){
            double temp=0;
            while (temp==0){
            temp=Double.parseDouble(JOptionPane.showInputDialog(null,"We are asking for non-zero coefficient. Please enter a again."));
            }
            a=temp;
        }
        double b=Double.parseDouble(JOptionPane.showInputDialog(null,"Please enter the coefficient b: "));
        double c=Double.parseDouble(JOptionPane.showInputDialog(null,"Please enter the coefficient c: "));
        double delta=Math.pow(b,2)-4*a*c; //math.pow chu deo phai math.exp nhe (exp la e roi :)). math.power operates in double mode
        if (delta<0){
            JOptionPane.showMessageDialog(null, "This equation has no real roots.");
        }
        else if (delta==0){
            JOptionPane.showMessageDialog(null,  "This equation has double roots: "+String.format("%.2f",-b/(2*a)));
        }
        else{
            JOptionPane.showMessageDialog(null,  "This equation has two unique roots: "+String.format("(%.2f, %.2f)",(-b+Math.sqrt(delta))/(2*a), (-b-Math.sqrt(delta))/(2*a)));
        }
    }
}

