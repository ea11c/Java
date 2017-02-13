//Eric Adams

import java.util.Scanner;

public class Reverse
{
	public static void main(String[] args)
	{
		long num;  //holds number read in
		long num_rev;  //holds reversed number
		Scanner input = new Scanner(System.in);
		while(true)  //infinite loop, exit if num == 0
		{
			System.out.printf("Please enter a long integer (0 to quit): ");
			num = input.nextLong();
			if(num == 0)
			{
				System.out.printf("\nGoodbye!\n");
				return;
			}
			num_rev = reverseDigits(num);
			System.out.printf("\nThe number reversed is: %d\n\n", num_rev);
		}
	}
	public static long reverseDigits(long num)
	{
	//Convert the passed long to a string, read through the string and add the
	//numbers to a new string in reversed order, convert new string to a long.
		String num_rev = new String();
		String num_act = Long.toString(num);
		
		for(int i=num_act.length() - 1; i >= 0; i--)
		{
			num_rev += num_act.charAt(i);
		}
		return Long.valueOf(num_rev);
	}
}
