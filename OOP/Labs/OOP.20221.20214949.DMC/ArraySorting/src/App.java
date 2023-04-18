import java.util.Arrays;

import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
    int[] input;
    input=input();
    ArrayManipulation array=new ArrayManipulation(input);
    JOptionPane.showMessageDialog(null,String.format("This is the array you've entered: %s%nThe sorted array is: %s%nSum of all elements in the array is %d%nAverage value of all elements in the array is %.2f%n",Arrays.toString(input),Arrays.toString(array.BubbleSort()),array.Sum(),array.Average()));

}

public static int[] input(){
    JOptionPane.showMessageDialog(null,"You are about to enter an array.\nAfter entering the array, the program will display the sum and average values of the entered array.");
    int k=Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter the number of elements in the array"));
    int[] temp=new int[k];
    for (int i=1;i<=k;i++){
        int inp=Integer.parseInt(JOptionPane.showInputDialog(null, String.format("Please enter the element number %d",i)));
        temp[i-1]=inp;

    }
    return temp;
}
}
