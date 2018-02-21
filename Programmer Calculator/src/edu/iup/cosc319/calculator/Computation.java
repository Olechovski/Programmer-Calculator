package edu.iup.cosc319.calculator;

import java.math.BigInteger;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * This class is designed to handle all of the calculations
 * from the expression submitted by the user and return a result.
 * @author Eric Olechovski
 *
 */
public class Computation extends ButtonHandler{

	public static final BigInteger MAX_VALUE = new BigInteger("1208925819614629174706175");
	public static final BigInteger MIN_VALUE = new BigInteger("-604462900800115466829816");
	/** Constructor used to access user's input
	 * @param inputTextField
	 */
	public Computation(JTextField inputTextField){
		super(inputTextField);
	}


	/**
	 * This method is designed to parse all of the formatted characters
	 * i.e. (commas, spaces, negation) to prepare the expression to be evaluated.
	 * 
	 * @param notation
	 * @return returns a conversion list of the calculated result
	 */
	public ArrayList<String> computeInput (String notation){


		String input = inputTextField.getText();
		String num = "";
		int substringIndex = 0;

		ArrayList<String> expression = new ArrayList<String>();
		ArrayList<String> operatorSet = new ArrayList<String>();

		operatorSet.add("~");
		operatorSet.add("(-)");
		operatorSet.add("/");
		operatorSet.add("*");
		operatorSet.add("+");
		operatorSet.add("-");
		operatorSet.add("(");
		operatorSet.add("(AND)");
		operatorSet.add("(OR)");


		for (int i = 0; i < input.length(); i++ ){

			String character = Character.toString(input.charAt(i));

			if ( operatorSet.contains(character) ){

				if (!character.equals("~") && substringIndex != i){
					num = input.substring(substringIndex, i-1);
					num = num.replace(",", "");
					num = num.replace(" ", "");
					expression.add(num);
				}

				// determines the logical operator
				if (character.equals("(")){

					int y = i;
					boolean loop = true;

					while (loop){
						String closedP = Character.toString(input.charAt(y));
						if (closedP.equals(")")){
							loop = false;}
						y++;
					}
					character = input.substring(i, y);
					i = y;
					substringIndex = i+1;
				}

				else if( character.equals("~")){
					substringIndex = i+1;
				}

				else if( character.equals("-") && !Character.toString(input.charAt(i+1)).equals(" ")){
					substringIndex = i+1;
					character = "(-)";
				}

				else{
					substringIndex = i+2;
				}

				expression.add(character);

			}


			else if ( i == input.length()-1){
				num = input.substring(substringIndex);
				num = num.replace(",", "");
				num = num.replace(" ", "");
				expression.add(num);
			}

		}

		ArrayList<String> x = evaluate(expression, operatorSet, notation, 0);

		return x;

	}


	/** This method is responsible for performing the operations according to the
	 * 	user input.
	 * 
	 * @param expression
	 * @param operatorSet
	 * @param notation
	 * @param indexedOperator
	 * @return the conversion list of the calculated result
	 */
	private static ArrayList<String> evaluate (ArrayList<String> expression, ArrayList<String> operatorSet, String notation, int indexedOperator){

		String operator = operatorSet.get(indexedOperator);
		String operand1 = "";
		String operand2 = "";
		BigInteger product = null;

		// takes care of all negations
		int index;
		while (expression.contains("~") || expression.contains("(-)")){


			if (expression.contains("~")){
				index = expression.indexOf("~");
			}

			else {
				index = expression.indexOf("(-)");
			}




			operand1 = expression.get(index+1);

			BigInteger x = new BigInteger(toDecimal(operand1, notation));
			product = x.negate();

			String resultString = convert(product, notation);
			expression.remove(index);
			expression.remove(index);
			expression.add(index, resultString);

		}



		for (int i = 0; i < expression.size(); i ++){


			if ( operator.equals(expression.get(i)) ){


				if (expression.size() > 1 && !operator.equals("~")){

					operand1 = expression.get(i-1);
					operand2 = expression.get(i+1);

					BigInteger x = new BigInteger(toDecimal(operand1, notation));
					BigInteger y = new BigInteger(toDecimal(operand2, notation));



					try{

						if ( operator.equals("/")){
							product = x.divide(y);
						}

						else if ( operator.equals("*")){
							product = x.multiply(y);
						}

						else if ( operator.equals("+")){
							product = x.add(y);
						}

						else if ( operator.equals("-")){
							product = x.subtract(y);
						}

						else if ( operator.equals("(AND)")){
							product = x.and(y);
						}

						else if ( operator.equals("(OR)")){
							product = x.or(y);
						}

						String resultString = convert(product, notation);
						expression.add(i-1, resultString);

						expression.remove(i);
						expression.remove(i);
						expression.remove(i);

						i--;

					}

					catch (java.lang.ArithmeticException e){

						JOptionPane.showMessageDialog(null, "Divide by zero error");
						return null;
					}

				}
			}
		}



		// if there is only one number, hence it is the result
		if ( expression.size() == 1 ){


			BigInteger result = new BigInteger(toDecimal(expression.get(0), notation));
			int val = result.compareTo(MAX_VALUE);
			int val2 = result.compareTo(MIN_VALUE); 
			expression.remove(0);

			if (val >= 0){
				result = MAX_VALUE;
				expression.add("( MAX VALUE )");
			}
			else if (val2 <= 0 ){
				result = MIN_VALUE;
				expression.add("( MIN VALUE )");
			}

			else{
				expression.add(convert(result, notation));
			}

			expression.add(convert(result, "Hex"));
			expression.add(convert(result, "Dec"));
			expression.add(convert(result, "Oct"));
			expression.add(convert(result, "Bin"));


			return expression;

		}
		else{
			return evaluate (expression, operatorSet, notation, indexedOperator + 1);
		}
	}


	/** This method converts the result into the other numerical notations
	 * @param product
	 * @param notation
	 * @return conversion of result
	 */
	private static String convert(BigInteger product, String notation) {

		if (notation.equals("Hex")){
			return product.toString(16).toUpperCase();
		}
		else if (notation.equals("Dec")){
			return product.toString(10);
		}
		else if (notation.equals("Oct")){
			return product.toString(8);
		}
		else if (notation.equals("Bin")){
			return product.toString(2);
		}
		else
			return null;


	}

	/** This method is designed to change the expression so it 
	 * 	can be calculated in decimal and then converted into other 
	 *  notations after a result has been reached.
	 *  
	 * @param num
	 * @param notation
	 * @return changes a number to a base 10 number (Decimal)
	 */
	private static String toDecimal (String num, String notation) {
		if (notation.equals("Hex")){
			return new BigInteger(num, 16).toString(10);
		}
		else if (notation.equals("Oct")){
			return new BigInteger(num, 8).toString(10);
		}
		else if (notation.equals("Bin")){
			return new BigInteger(num, 2).toString(10);
		}
		else {
			return num;
		}

	}



}
