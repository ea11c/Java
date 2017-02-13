//Eric Adams

import java.util.Scanner;
import java.util.Random;

public class DiceStats
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		int num_dice;
		int num_rolls;
		System.out.printf("How many dice will constitute one roll? ");
		num_dice = input.nextInt();
		System.out.print("How many rolls? ");
		num_rolls = input.nextInt();
		System.out.printf("Sum\t# of times\tPercentage\n");

		//min and max sum used in array creation;
		int min_sum = 1 * num_dice;
		int max_sum = 6 * num_dice;

		//formula for number of possible results is max-min+1
		int[] dice_results = new int[max_sum - min_sum + 1];

		//initialize all array indices to 0
		for(int i=0; i<dice_results.length; i++)
			dice_results[i] = 0;

		Random rand = new Random();
		int sum = 0;
		//to calculate required array index: result - num_dice
		for(int i=0; i<num_rolls; i++) //loop to roll dice and count sums
		{
			sum = 0;
			for(int j=0; j<num_dice; j++)
			{
				sum += rand.nextInt(6) + 1;
			}
			dice_results[sum - num_dice]++;
		}
		double percent = 0;
		//display results and calculate  percentages
		for(int i=0; i<dice_results.length; i++)
		{
			percent = (double)dice_results[i]/(double)num_rolls * 100;
			System.out.printf("%d\t%d\t\t%.2f %%\n", i+num_dice, dice_results[i], percent);
		}
	}
}
