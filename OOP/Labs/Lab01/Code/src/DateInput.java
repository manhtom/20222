import javax.swing.*;
public class DateInput {
    public static String[] Input(){
    // variables defined in while cannot be used outside the loop
        String[] input=new String[2];
        input[0]=JOptionPane.showInputDialog(null, "Please enter the month\nNote: You must either enter the month in its full name/its abbreviation/3 letters/number.\nFor example, the valid inputs of February are February, Feb., Feb, and 2.");
        if (MYCheck.Month(input[0]) == 0){
            String temp=JOptionPane.showInputDialog(null, "The month you've entered is invalid. Please try again. \nNote: You must either enter the month in its full name/its abbreviation/3 letters/number.\nFor example, the valid inputs of February are February, Feb., Feb, and 2.");
            input[0]=MYCheck.Month(temp);
        }
        input[1]=JOptionPane.showInputDialog(null, "Please enter the year.\nNote: The year must be a non-negative number with all the digits.");
        while (MYCheck.Year(input[1]) == false){
            String temp=JOptionPane.showInputDialog(null, "The year you've entered is invalid. Please try again.\nNote: The year must be a non-negative number with all the digits.");
            input[1]=temp;
        }

        return input;

    }
}
