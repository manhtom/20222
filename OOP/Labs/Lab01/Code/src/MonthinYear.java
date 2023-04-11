import javax.swing.*;

public class MonthinYear {

    public static void main(String[] args){
        int m=0;
        int y=0;
        while (true){ // variables defined in while cannot be used outside the loop
            String input1=JOptionPane.showInputDialog(null, "Please enter the month");
            String input2=JOptionPane.showInputDialog(null, "Please enter the year");
            if (Check.Month(input1)!=0 && Check.Year(input2)){ 
                y=Integer.parseInt(input1);
                m=Check.Month(input2);
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
