import java.util.*;
import javax.swing.*;

public class App {
    public static void main(String[] args) {
        double double1=Double.parseDouble(JOptionPane.showInputDialog(null,"Please enter the first number:"));
        double double2=Double.parseDouble(JOptionPane.showInputDialog(null,"Please enter the first number:"));
        double tong=double1+double2;
        JOptionPane.showMessageDialog(null, "The sum of these double numbers is "+tong);
        double hieu=double1-double2;
        JOptionPane.showMessageDialog(null, "The difference of these double numbers is "+hieu);
        double tich=double1*double2;
        JOptionPane.showMessageDialog(null, "The product of these double numbers is "+tich);
        if (double2!=0){
            double thuong=double1/double2;
            JOptionPane.showMessageDialog(null, "The quotient of these double numbers is "+thuong);
        }
        else{
            JOptionPane.showMessageDialog(null,"The quotient of these double numbers is unavailable because the divisor is equal to 0");
        }

    }
}
