/**
 * 
 */
package com.ss.lms.services;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author flips
 *
 */
public abstract class BaseService 
{

	public int validIntInput(int i)
	{
		Scanner scan = new Scanner(System.in);
		int input = 0;
		while(input<= 0 || input > i)
		{
			try
			{
				input = scan.nextInt();
				if(input > i || input <=0)
					System.out.println("Input proper integer from 1 to " + i);
			}
			catch(InputMismatchException e)
			{
				System.out.println("Input proper integer from 1 to " + i);
				input = 0;
			}
		}
		return input;
	}
	
	public String validStringInput()
	{
		Scanner scan = new Scanner(System.in);
		String input = "";
		while(input.length()<= 0)
		{
			try
			{
				input = scan.nextLine();
				if(input.length() <= 0)
					System.out.println("Improper Input! Must contain atleast 1 character");
			}
			catch(InputMismatchException e)
			{
				System.out.println("Improper Input! Must contain atleast 1 character");
			}
		}
		return input;
	}
}
