import javax.swing.*;

public class MonthinYear {

    public static void main(String[] args){
        int m=0;
        int y=0;
        while (true){ // variables defined in while cannot be used outside the loop
            String montht=JOptionPane.showInputDialog(null, "Please enter the month");
            String yeart=JOptionPane.showInputDialog(null, "Please enter the year");
            int m1=0;
            m1=Check.Month(montht);
            if (m1!=0 && Check.Year(yeart)){ 
                y=Integer.parseInt(yeart);
                m=m1;
                break;
            }
        }
        if (m == 4 || m == 6 || m == 9 || m == 11){
            JOptionPane.showMessageDialog(null, "There are 30 days in this month", "Days in a month",0);
        }
        else if (m==2){
            if (Check.LeapYear(y)){
                JOptionPane.showMessageDialog(null, "There are 29 days in this month", "Days in a month",0);
            }
            else{
                JOptionPane.showMessageDialog(null, "There are 28 days in this month", "Days in a month",0);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "There are 31 days in this month", "Days in a month",0);
        }
    }
}
