import javax.swing.*;

public class App {
    public static void main(String[] args) throws Exception {
        String input1=JOptionPane.showInputDialog(null, "You are about to add two matrices. Two matrices must be of the same size.\nPlease enter the number of rows in these matrices:");
        String input2=JOptionPane.showInputDialog(null, "You are about to add two matrices. Two matrices must be of the same size.\nPlease enter the number of columns in these matrices:");
        int row=Integer.parseInt(input1);
        int col=Integer.parseInt(input2);
        float[][] m1=new float[row][col];
        float[][] m2=new float[row][col];
        JOptionPane.showMessageDialog(null, String.format("You will now enter the first matrix (matrix A). The matrix will be of size %d x %d. On being asked, you will enter each element in this matrix.",row,col));
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                input1=JOptionPane.showInputDialog(null, String.format("Please enter the element a(%d,%d)",i+1,j+1));
                m1[i][j]=Float.parseFloat(input1);
            }
        }
        JOptionPane.showMessageDialog(null, String.format("You will now enter the second matrix (matrix B). The matrix will be of size %d x %d. On being asked, you will enter each element in this matrix.",row,col));
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                input1=JOptionPane.showInputDialog(null, String.format("Please enter the element b(%d,%d)",i+1,j+1));
                m2[i][j]=Float.parseFloat(input1);
            }
        }
        String result="";
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                m1[i][j]=m1[i][j]+m2[i][j];
                result=result+m1[i][j];
                result=result+" ";
            }
            result=result+"\n";
        }
        JOptionPane.showMessageDialog(null,String.format("The resulting matrix of adding matrix A and matrix B is%n%s",result));
    }
}
