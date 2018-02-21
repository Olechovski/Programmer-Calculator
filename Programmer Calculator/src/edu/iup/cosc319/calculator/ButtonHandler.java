package edu.iup.cosc319.calculator;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import javax.swing.*;

/** This Class will serve as the button action class, which will be
 *  responsible for carrying out the actions for each button on the calculator.
 *  
 *  
 * @author Eric Olechovski
 *
 */

public class ButtonHandler extends CalculatorGUI{
	String expression;
	String result;
	String notation;
	Boolean processedExpression = false;
	Boolean valueMinMax = false;

	@SuppressWarnings("serial")
	public static HashSet<String> operatorSet = new HashSet<String>(){{

		add("~");
		add("-");
		add("+");
		add("/");
		add("*");
		add(")");
		add("(AND)");
		add("(OR)");
		add("(NOR)");
	}};


	/**
	 * General purpose constructor
	 *  
	 * @param inputTextField
	 * @param outputTextFieldHex
	 * @param outputTextFieldBin 
	 * @param outputTextFieldOctal 
	 * @param outputTextFieldDec 
	 * @param numericalNotation
	 * @param buttons_A_to_F
	 * @param buttons_2_to_9
	 * @param memoryList
	 */

	public ButtonHandler(
			JTextField inputTextField, 
			JTextField outputTextFieldHex,
			JTextField outputTextFieldDec,
			JTextField outputTextFieldOctal,
			JTextArea outputTextAreaBin,
			ButtonGroup numericalNotation, 
			ButtonGroup buttons_A_to_F,
			ButtonGroup buttons_2_to_9,
			List memoryList) {

		super(inputTextField,
				outputTextFieldHex,
				outputTextFieldDec,
				outputTextFieldOctal,
				outputTextAreaBin,
				numericalNotation,
				buttons_A_to_F,
				buttons_2_to_9,
				memoryList);


	}


	/**
	 * Constructor used for "Computation.java" class
	 * @param inputTextField
	 */
	public ButtonHandler(JTextField inputTextField) {
		super(inputTextField);
	}



	/************** Numeric Notation Actions ***********/	
	public class OctalSelection implements ActionListener {

		private JButton b;
		private JButton b2;

		public OctalSelection(JButton b, JButton b2) {
			super();
			this.b = b;
			this.b2 = b2;
		}


		public void actionPerformed(ActionEvent e) {

			// disables button group A-F
			Enumeration<AbstractButton> x = buttons_A_to_F.getElements();
			for (int i = 0; i < buttons_A_to_F.getButtonCount(); i++){
				x.nextElement().setEnabled(false);
			}

			// enables button group 2-9
			Enumeration<AbstractButton> y = buttons_2_to_9.getElements();
			for (int i = 0; i < buttons_2_to_9.getButtonCount(); i++){
				y.nextElement().setEnabled(true);
			}

			b.setEnabled(false); // button_8
			b2.setEnabled(false); // button_9


			clearTextFields();
		}

	}

	public class BinSelection implements ActionListener {


		public void actionPerformed(ActionEvent e) {

			// disables button group A-F
			Enumeration<AbstractButton> x = buttons_A_to_F.getElements();
			for (int i = 0; i < buttons_A_to_F.getButtonCount(); i++){
				x.nextElement().setEnabled(false);
			}

			// disables button group 2-9
			Enumeration<AbstractButton> y = buttons_2_to_9.getElements();
			for (int i = 0; i < buttons_2_to_9.getButtonCount(); i++){
				y.nextElement().setEnabled(false);
			}


			clearTextFields();

		}

	}

	public class HexSelection implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// enables button group A-F
			Enumeration<AbstractButton> x = buttons_A_to_F.getElements();
			for (int i = 0; i < buttons_A_to_F.getButtonCount(); i++){
				x.nextElement().setEnabled(true);
			}

			// enables button group 2-9
			Enumeration<AbstractButton> y = buttons_2_to_9.getElements();
			for (int i = 0; i < buttons_2_to_9.getButtonCount(); i++){
				y.nextElement().setEnabled(true);
			}


			clearTextFields();

		}

	}

	public class DecSelection implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			// disables button group A-F
			Enumeration<AbstractButton> x = buttons_A_to_F.getElements();
			for (int i = 0; i < buttons_A_to_F.getButtonCount(); i++){
				x.nextElement().setEnabled(false);
			}

			// enables button group 2-9
			Enumeration<AbstractButton> y = buttons_2_to_9.getElements();
			for (int i = 0; i < buttons_2_to_9.getButtonCount(); i++){
				y.nextElement().setEnabled(true);
			}

			clearTextFields();

		}

	}

	public String getSelectedNotation(){

		// determine numerical notation
		String selectedNotation = "";
		Enumeration<AbstractButton> notations  = numericalNotation.getElements();

		while (notations.hasMoreElements()){

			AbstractButton element = notations.nextElement();

			if (element.isSelected()){
				selectedNotation = element.getText();
				break;
			}

		}
		return selectedNotation;

	}
	
	public void setNotationSelection(String desiredNotation){
		
		String notation = "";
		Enumeration<AbstractButton> notations  = numericalNotation.getElements();

		while (notations.hasMoreElements()){

			AbstractButton element = notations.nextElement();
			notation = element.getText();
			
			if (desiredNotation.equals(notation)){
			
				element.setSelected(true);
				
			}
			else{
				element.setSelected(false);
				
			}

		}
		
		
	}
	/***********************************************************/



	/************** Validation & Clearing Actions ***********/

	public class Validation implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if ( valueMinMax ){
				valueMaximumMinimum();
			}

			String originalInput = inputTextField.getText();
			int inputLength = originalInput.length();

			boolean negation = validateNegation(originalInput);
			if ( inputLength == 0 ){
				System.out.println("Blank Input");
			}

			else if(originalInput.charAt(inputLength-1) == ' '){
				JOptionPane.showMessageDialog(inputTextField, "Cannot end expression with an operator.");

			}

			else if(negation == false){
				JOptionPane.showMessageDialog(inputTextField, "Invalid Expression.");
			}


			else{

				String notationSelected = getSelectedNotation();

				Computation compute = new Computation(inputTextField);
				ArrayList<String> conversionList = compute.computeInput(notationSelected);
				if (conversionList != null){

					result = formatExpression(conversionList.get(0));
					expression = inputTextField.getText();
					notation = getSelectedNotation();
					processedExpression = true;

					inputTextField.setText(formatExpression(conversionList.get(0)));
					if (inputTextField.getText().contains( "VALUE")){
						valueMinMax = true;
					}
					outputTextFieldHex.setText(formatHEX_BIN_OCTAL(conversionList.get(1), 4));
					outputTextFieldDec.setText(formatDEC(conversionList.get(2)));
					outputTextFieldOctal.setText(formatHEX_BIN_OCTAL(conversionList.get(3), 3));
					outputTextAreaBin.setText(formatHEX_BIN_OCTAL(conversionList.get(4), 4));
				}

			}

		}

	}

	public class BackSpace implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String original = inputTextField.getText();
			int endIndex = original.length()-1;
			if ( valueMinMax ){
				valueMaximumMinimum();
				return;
			}
			if (endIndex > 0 && original.substring(endIndex).equalsIgnoreCase(" ")){
				endIndex -= 2;
			}
			if (endIndex > 0){
				String backSpace = original.substring(0, endIndex);
				backSpace = formatExpression(backSpace);
				inputTextField.setText(backSpace);
			}
			else{
				inputTextField.setText("");
			}
		}



	}

	public class ClearInput implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if ( valueMinMax ){
				valueMaximumMinimum();
			}
			inputTextField.setText("");
		}
	}

	public void clearTextFields(){


		inputTextField.setText("");
		outputTextFieldHex.setText("");
		outputTextFieldDec.setText("");
		outputTextFieldOctal.setText("");
		outputTextAreaBin.setText("");

	}

	public boolean validateNegation (String inputExpression){

		int inputLength = inputExpression.length();

		for(int i = 0; i < inputLength; i++){
			char character = inputExpression.charAt(i);

			if (character == '~'){

				if(i == inputLength-1){
					return false;
				}
				else if(inputExpression.charAt(i+1) == ' '){
					return false;
				}

				else if(i > 0 && inputExpression.charAt(i-1) != ' '){
					return false;
				}


			}

		}


		return true;
	}

	private void valueMaximumMinimum() {
		clearTextFields();
		valueMinMax = false;
	}
	/***********************************************************/



	/************** Input & Formating Actions **********/
	public class Input implements ActionListener {



		private JButton b;


		public Input(JButton b) {
			super();
			this.b = b;

		}


		public void actionPerformed(ActionEvent e) {

			if ( valueMinMax ){
				valueMaximumMinimum();
			}

			String inputExpression = inputTextField.getText();
			String inputChar = b.getText();
			if (inputChar.equals(" (NOT) ")){
				inputChar = "~";
			}
			else if (inputChar.equals("(-)")){
				inputChar = "-";
			}

			int inputLength = inputExpression.length();
			String newInput;

			// ensures that an expression cannot start with an operator
			if (inputLength == 0 && inputChar.length() > 1){
				newInput = "";
			}

			// avoids invalid inputs for "~"
			else if (inputChar.equals("~") && inputLength > 0 || inputChar.equals("-")  && inputLength > 0){

				if (inputExpression.charAt(inputLength-1) != ' '){
					newInput = inputExpression;
				}
				else {
					newInput = inputExpression + inputChar;
					newInput = formatExpression(newInput);
				}
			}

			// avoids adjacent arithmetic operators and expressions that start with an arithmetic operator
			else if( inputLength > 1 && inputExpression.charAt(inputLength-1) == ' ' && inputChar.length()>1){
				newInput = inputExpression;
			}

			else {
				newInput = inputExpression + inputChar;
				newInput = formatExpression(newInput);
			}

			inputTextField.setText(newInput);
		}


	}

	public String formatExpression(String newInput) {

		char lastCharacter = newInput.charAt(newInput.length()-1);

		if (newInput.length() < 4 || lastCharacter == ' '){
			return newInput;
		}


		String notationSelected = getSelectedNotation();
		String formattedInput = "";


		if (notationSelected.equals("Hex") || notationSelected.equals("Bin")){

			formattedInput = formatHEX_BIN_OCTAL(newInput, 4);
		}

		else if(notationSelected.equals("Dec")){

			formattedInput = formatDEC(newInput);
		}


		else{

			formattedInput = formatHEX_BIN_OCTAL(newInput, 3);
		}


		return formattedInput;
	}

	public String formatDEC(String newInput) {

		//loop to find a string of characters to format
		int charCount = 0;
		int commaCount = 0;
		String original = "";


		for (int i = 1; i < newInput.length()+1; i++){

			char character = newInput.charAt(newInput.length()-i);
			if ( character == ' '){
				original = newInput.substring(0,newInput.length()-(charCount+commaCount));
				break;
			}
			if ( character == ','){
				commaCount++;

			}
			else if (!operatorSet.contains(Character.toString(character))){
				charCount++;
			}
			else {
				original = newInput.substring(0,1);
			}

		}

		String unformat = newInput.substring(newInput.length()-(charCount+commaCount));
		unformat = unformat.replace(",", "");
		String formattedInput = "";
		int count = 0;
		for (int i = charCount-1; i >= 0; i --){

			formattedInput = unformat.charAt(i) + formattedInput; 
			count ++;
			if (count == 3 && i > 0 && unformat.charAt(i-1) != '~'){
				formattedInput = "," + formattedInput;
				count = 0;
			}


		}
		formattedInput = original + formattedInput;
		return formattedInput;

	}

	public String formatHEX_BIN_OCTAL(String newInput, int spaceIncrement) {

		//loop to find a string of characters to format
		int charCount = 0;
		int spaceCount = 0;
		String original = "";
		for (int i = 1; i < newInput.length()+1; i++){

			char character = newInput.charAt(newInput.length()-i);

			if ( operatorSet.contains(Character.toString(character))){
				// if "-" is for negation and not an operator

				if ( charCount + spaceCount == 0){
					return newInput;
				}
				if(newInput.charAt(newInput.length()-i+1) == ' '){
					spaceCount--;
				}

				original = newInput.substring(0,newInput.length()-(charCount+spaceCount));
				break;

			}
			if ( character == ' '){
				spaceCount++;

			}
			else{
				charCount++;
			}

		}

		String unformat = newInput.substring(newInput.length()-(charCount+spaceCount));
		unformat = unformat.replace(" ", "");
		String formattedInput = "";
		int count = 0;
		for (int i = charCount-1; i >= 0; i --){

			formattedInput = unformat.charAt(i) + formattedInput; 
			count ++;
			if (count == spaceIncrement && i > 0){
				formattedInput = " " + formattedInput;
				count = 0;
			}


		}
		formattedInput = original + formattedInput;
		return formattedInput;


	}
	/***********************************************************/



	/****************** Memory Actions *************/
	public class SaveExpression implements ActionListener {

		// number of expressions kept in the memory list
		// " * 3 " is for displaying results of expression
		int memorySize = 10 * 3;

		public void actionPerformed(ActionEvent e) {


			if ( processedExpression ){
				boolean itemAlreadySaved = false;

				for ( int i = 0; i < memoryList.getItemCount(); i++){

					if (memoryList.getItem(i).equals(expression)){
						itemAlreadySaved = true;
						break;
					}

				}

				if ( !itemAlreadySaved ){
					if (memorySize == memoryList.getItemCount()){
						memoryList.remove(memoryList.getItemCount()-1);
						memoryList.remove(memoryList.getItemCount()-1);
						memoryList.remove(memoryList.getItemCount()-1);
					}


					String expressionResult = "Result: " + result + "  Notation: " + notation;
					memoryList.add("",0);
					memoryList.add(expressionResult,0);
					memoryList.add(expression,0);
					memoryList.repaint();
					memoryList.select(0);


				}

			}
		}
	}

	public class ApplySavedExpression implements ActionListener {
		
		
		private JButton b;
		private JButton b2;

		public ApplySavedExpression(JButton b, JButton b2) {
			super();
			this.b = b;
			this.b2 = b2;
		}
		

		public void actionPerformed(ActionEvent e) {
			String selectedItem = memoryList.getSelectedItem();

			try{
				if ( !selectedItem.equals("")){
					
					String notation = memoryList.getItem(memoryList.getSelectedIndex()+1);
					
					if ( selectedItem.startsWith("Result")){
						notation = selectedItem;
						selectedItem = memoryList.getItem(memoryList.getSelectedIndex()-1);
					}
					
					notation = notation.substring(notation.length()-3, notation.length());
					
					setNotationSelection(notation);
					
					if (notation.equals("Hex")){
						
						new HexSelection().actionPerformed(e);
					}
					
					else if (notation.equals("Dec")){
						
						new DecSelection().actionPerformed(e);
					}
					
					else if (notation.equals("Oct")){
						
						new OctalSelection(b,b2).actionPerformed(e);
					}
					
					else {
						
						new BinSelection().actionPerformed(e);
					}
					
					inputTextField.setText(selectedItem);

				}
			}

			catch (NullPointerException e1){
				JOptionPane.showMessageDialog(inputTextField, "No Saved Expressions in Memory");
			}

		}
	}

	public class DeleteSavedExpression implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			try{
				int selectedItemIndex = memoryList.getSelectedIndex();
				if ( !memoryList.getSelectedItem().equals("")){
					if( selectedItemIndex != -1 ){
						if (memoryList.getSelectedItem().startsWith("Result")){
							selectedItemIndex -= 1;
						}

						memoryList.remove(selectedItemIndex);
						memoryList.remove(selectedItemIndex);
						memoryList.remove(selectedItemIndex);
					}

				}
			}
			catch (NullPointerException e1){
				JOptionPane.showMessageDialog(inputTextField, "No Saved Expressions in Memory");
			}
		}
	}

	public class ClearAllSavedExpressions implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			while (memoryList.getItemCount() != 0){
				memoryList.remove(0);
			}
		}
	}
	/***********************************************************/

}
