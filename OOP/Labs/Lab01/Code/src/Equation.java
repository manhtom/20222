import javax.swing.*;
import java.util.*;

public class Eqn{
    public static void main(String[] args){
        String option="";
        while (option.equals("1") == false && option.equals("2") == false && option.equals("3") == false){
            option=JOptionPane.showInputDialog(null, "Select 1 if you want to solve single variable 1st-deg equation.\nSelect 2 if you want to solve a system of 1st-deg equations with 2 variables.\nSelect 3 if you want to solve single variable 2nd-deg equation.");
        }

        if (option.equals("1")){
            System.out.println(FirstDegree());
        }
        else if (option.equals("2")){
            System.out.println(FirstDegreeSys());
        }
        else{
            JOptionPane.showMessageDialog(null,SecondDegree());
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
    public static String SecondDegree(){ //this method is implemented with javax.swing
        double a=Double.parseDouble(JOptionPane.showInputDialog(null,"Please enter the first coefficient: "));
        double b=Double.parseDouble(JOptionPane.showInputDialog(null,"Please enter the second coefficient: "));
        double c=Double.parseDouble(JOptionPane.showInputDialog(null,"Please enter the third coefficient: "));
        double delta=Math.pow(b,2)-4*a*c; //math.pow chu deo phai math.exp nhe (exp la e roi :)). math.power operates in double mode
        if (delta<0){
            return "No solution";
        }
        else if (delta==0){
            return "Double solution: "+String.format("%.2f",-b/(2*a));
        }
        else{
            return "Two unique solutions: "+String.format("(%.2f, %.2f)",(-b+Math.sqrt(delta))/(2*a), (-b-Math.sqrt(delta))/(2*a));
        }

    }
}
