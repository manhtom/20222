public class ArraySorting {
   	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		System.out.printf("%s","Enter the array size: ");
		int n = s.nextInt();
		s.nextLine();
		int[] array = new int[n];
		System.out.printf("%s","Enter the array: ");
		int k = 0;
		while (s.hasNextInt() && k<n) {
			int input = s.nextInt();
			array[k] = input;
			k++;
		}
		s.close();
		// done inputting. now still needs to sort
		//System.out.println("The sum of array elements is "+)
		//System.out.println("The average of array elements is "+)

	} 
}
