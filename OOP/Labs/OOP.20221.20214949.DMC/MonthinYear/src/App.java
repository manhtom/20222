import javax.swing.*;

public class App {

    public static void main(String[] args){
        int m=0;
        int y=0;
        int[] input=Input();
        m=input[0];
        y=input[1];
        if (m == 4 || m == 6 || m == 9 || m == 11){
            JOptionPane.showMessageDialog(null, "There are 30 days in this month", "Days in a month",0);
        }
        else if (m==2){
            if (MYCheck.LeapYear(y)){
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

    private static int[] Input(){
        // variables defined in while cannot be used outside the loop
            int[] input=new int[2];
            String tempinput1=JOptionPane.showInputDialog(null, "Please enter the month\nNote: You must either enter the month in its full name/its abbreviation/3 letters/number.\nFor example, the valid inputs of February are February, Feb., Feb, and 2.");
            if (MYCheck.Month(tempinput1) == 0){
                while (true){
                String temp=JOptionPane.showInputDialog(null, "The month you've entered is invalid. Please try again. \nNote: You must either enter the month in its full name/its abbreviation/3 letters/number.\nFor example, the valid inputs of February are February, Feb., Feb, and 2.");
                if (MYCheck.Month(temp) != 0){
                break; 
                }
                }
            }
            String tempinput2=JOptionPane.showInputDialog(null, "Please enter the year.\nNote: The year must be a non-negative number with all the digits.");

            if (MYCheck.Year(tempinput2) == false){
                while (true){
                    String temp=JOptionPane.showInputDialog(null, "The year you've entered is invalid. Please try again.\nNote: The year must be a non-negative number with all the digits.");
                    if (MYCheck.Year(temp)){
                    break; 
                }
                }
            }
            input[0]=MYCheck.Month(tempinput1); //conversion to month in numeral form 
            input[1]=Integer.parseInt(tempinput2); //year inputstring to int
            return input;
    
        }
}
