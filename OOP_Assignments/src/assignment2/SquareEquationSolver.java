package assignment2;

import java.util.Scanner;

import assignment2.SquareEquation.SquareEquationException;

/**
 * scans an input of equation (from user) and solves them
 * @author Ali Masarweh
 *
 */
public class SquareEquationSolver {
	
	private final static int Equation_Leading_Power = 2;
	private final static String Equation_Form = "aX^2+bX+c=0: ";
	private final static String Entering_Message = Equation_Form + "Enter a,b,c: ";
	private final static String Exit_Or_Continue_Output = "Enter 0 or any number to Exit or 1"
			+ " to solve " + Equation_Form;
	private final static String Enter_Param = "Enter %s: ";
	
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		String input = "1";
		
		double[] parameters = new double[Equation_Leading_Power+1];
		/* *** Program Loop *** */
		while(!input.equals("0")) {
			System.out.println(Entering_Message);
			/* *** Scanning The Parameters of The Equation *** */
			char starting_param = 'a';
			for (int i = 0; i <= Equation_Leading_Power; i++) {
				System.out.print(String.format(Enter_Param, starting_param++));
				parameters[i] = Double.parseDouble(sc.nextLine());
			}
			System.out.println(/* Equation of the given parameters */
					String.format("%sX^2+%sX+%s=0",parameters[0],parameters[1],parameters[2])
					);
			
			/* *** Solving and Handling Errors *** */
			PrintSolutionAndHandleErrors(parameters);
			System.out.println(Exit_Or_Continue_Output);
			input = sc.nextLine();
		}
		sc.close();
		System.out.println("Bye-bye! ");
	}
	
	/**
	 * print the solution of the equation, if any exist, handles the errors in case any occurs
	 * @param parameters the parameters of the equation (a,b,c)
	 */
	private static void PrintSolutionAndHandleErrors(double[] parameters) {
		SquareEquation SE = new SquareEquation(parameters[0], parameters[1], parameters[2]);
		try {
			SE.solveForX();
			int numberOfSultions = SE.get_x1() != SE.get_x2()?2:1;
			if(numberOfSultions == 2) {
				System.out.println(String.format("x1:%s    x2:%s ", SE.get_x1(),SE.get_x2()));
			}
			else if(parameters[0] != 0)/* Linear Equation Case */
				System.out.println(String.format("x1=x2:%s ", SE.get_x1(),SE.get_x2()));
			else /* One Solution Case */
				System.out.println("x1=:"+SE.get_x1()+" ");
			
		} catch (SquareEquationException e) {
			e.printStackTrace();
		}
	}
	

}
