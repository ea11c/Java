//Eric Adams

import java.util.Scanner;

public class Pi
{
	public static void main(String[] args)
	{
		int terms;  //holds number of terms
		double result = 4; //holds result of Pi calculation

		Scanner input = new Scanner(System.in);

		System.out.printf("Compute to how many terms of the series? ");
		terms = input.nextInt();
		System.out.printf("\nterms\tPI approximation\n");
		for(double i=1.0; i <= terms; i++)
		{
			if(i==1){
				result = 4;
			}
			else if(i%2 == 1){ //is an odd iteration, should add
				result = result + 4.0/(2*i - 1);
			}
			else{ //an even iteration, should subtract
				result = result - 4.0/(2*i - 1);
			}
			System.out.printf("%d\t%f\n", (int)i, result);
		}
	}
}
