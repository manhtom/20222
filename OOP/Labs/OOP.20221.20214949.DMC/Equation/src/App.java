import javax.swing.*;
//import java.util.*;

public class App{
    public static void main(String[] args){
        String option="";
        while (option.equals("1") == false && option.equals("2") == false && option.equals("3") == false){
            option=JOptionPane.showInputDialog(null, "Select 1 if you want to solve single variable 1st-deg equation.\nSelect 2 if you want to solve a system of 1st-deg equations with 2 variables.\nSelect 3 if you want to solve single variable 2nd-deg equation.");
        }

        if (option.equals("1")){
            FirstDegree.Eqn();
        }
        else if (option.equals("2")){
            FirstDegree.EqnSys();
        }
        else{
            SecondDegree.Eqn();
        }
    }

    }
