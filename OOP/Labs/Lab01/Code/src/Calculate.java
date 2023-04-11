import java.util.*;
import javax.swing.*;

public class Calculate {
    public static void main(String[] args) {
        double double1=Double.parseDouble(JOptionPane.showInputDialog(null,"Please enter the first number:"));
        double double2=Double.parseDouble(JOptionPane.showInputDialog(null,"Please enter the first number:"));
        double tong=double1+double2;
        double hieu=double1-double2;
        double tich=double1*double2;
        double thuong=double1/double2;
        JOptionPane.showMessageDialog(null, "The sum of 2 double number is "+tong);
        JOptionPane.showMessageDialog(null, "The difference of 2 double number is "+hieu);
        JOptionPane.showMessageDialog(null, "The product of 2 double number is "+tich);
        JOptionPane.showMessageDialog(null, "The quotient of 2 double number is "+thuong);
    }
}
