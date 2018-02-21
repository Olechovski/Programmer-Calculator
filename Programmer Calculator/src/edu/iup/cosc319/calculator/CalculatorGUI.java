package edu.iup.cosc319.calculator;

/**
 * 
 * CalculatorGUI creates the User Interface 
 * for the calculator. 
 * 
 * @author Eric Olechovski
 */

import javax.swing.*;
import java.awt.*;


public class CalculatorGUI {

	JFrame frame;
	JTextField inputTextField;
	JTextField outputTextFieldHex;
	JTextField outputTextFieldDec;
	JTextField outputTextFieldOctal;
	JTextArea outputTextAreaBin;
	ButtonGroup numericalNotation;
	ButtonGroup buttons_A_to_F;
	ButtonGroup buttons_2_to_9;
	List memoryList;

	/**
	 * Creates the application 
	 * 
	 * @wbp.parser.constructor
	 */
	public CalculatorGUI() {
		initialize();
	}


	/**
	 * Constructor's purpose is for creating actions for "ButtonHandler.java"
	 * 
	 * @param inputTextField
	 * @param outputTextFieldHex
	 * @param numericalNotation
	 * @param buttons_A_to_F
	 * @param buttons_2_to_9
	 * @param memoryList
	 */
	public CalculatorGUI(JTextField inputTextField, JTextField outputTextFieldHex, JTextField outputTextFieldDec, JTextField outputTextFieldOctal, JTextArea outputTextAreaBin,
			ButtonGroup numericalNotation, ButtonGroup buttons_A_to_F, ButtonGroup buttons_2_to_9, List memoryList) {

		this.inputTextField = inputTextField;
		this.outputTextFieldHex = outputTextFieldHex;
		this.outputTextFieldDec = outputTextFieldDec;
		this.outputTextFieldOctal = outputTextFieldOctal;
		this.outputTextAreaBin = outputTextAreaBin;
		this.numericalNotation = numericalNotation;
		this.buttons_A_to_F = buttons_A_to_F;
		this.buttons_2_to_9 = buttons_2_to_9;
		this.memoryList = memoryList;
	}



	/**
	 * Constructor's purpose is for evaluation for "Computation.java"
	 * 
	 * @param inputTextField
	 */
	public CalculatorGUI(JTextField inputTextField) {
		this.inputTextField = inputTextField;

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		numericalNotation = new ButtonGroup();
		buttons_A_to_F = new ButtonGroup();
		buttons_2_to_9 = new ButtonGroup();

		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.getContentPane().setBackground(new Color(51, 51, 51));
		frame.setBounds(100, 100, 1368, 819);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Programmer Calculator");


		/***** Input/Output TextFields & Labels *****/
		JTextArea txtrHexDecOctBin = new JTextArea();
		txtrHexDecOctBin.setForeground(new Color(255, 255, 255));
		txtrHexDecOctBin.setEditable(false);
		txtrHexDecOctBin.setBackground(new Color(51, 51, 51));
		txtrHexDecOctBin.setFont(new Font("Calibri", Font.PLAIN, 25));
		txtrHexDecOctBin.setText("Hex\r\n\r\nDec\r\n\r\nOct\r\n\r\nBin");
		txtrHexDecOctBin.setBounds(12, 123, 68, 213);
		frame.getContentPane().add(txtrHexDecOctBin);

		outputTextFieldHex = new JTextField();
		outputTextFieldHex.setBorder(null);
		outputTextFieldHex.setForeground(new Color(255, 255, 255));
		outputTextFieldHex.setBackground(new Color(102, 102, 102));
		outputTextFieldHex.setFont(new Font("Tahoma", Font.PLAIN, 20));
		outputTextFieldHex.setEditable(false);
		outputTextFieldHex.setBounds(82, 122, 498, 36);
		frame.getContentPane().add(outputTextFieldHex);
		outputTextFieldHex.setColumns(10);

		outputTextFieldDec = new JTextField();
		outputTextFieldDec.setBorder(null);
		outputTextFieldDec.setForeground(new Color(255, 255, 255));
		outputTextFieldDec.setBackground(new Color(102, 102, 102));
		outputTextFieldDec.setFont(new Font("Tahoma", Font.PLAIN, 20));
		outputTextFieldDec.setEditable(false);
		outputTextFieldDec.setColumns(10);
		outputTextFieldDec.setBounds(82, 183, 498, 36);
		frame.getContentPane().add(outputTextFieldDec);

		outputTextFieldOctal = new JTextField();
		outputTextFieldOctal.setBorder(null);
		outputTextFieldOctal.setForeground(new Color(255, 255, 255));
		outputTextFieldOctal.setBackground(new Color(102, 102, 102));
		outputTextFieldOctal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		outputTextFieldOctal.setEditable(false);
		outputTextFieldOctal.setColumns(10);
		outputTextFieldOctal.setBounds(82, 245, 498, 36);
		frame.getContentPane().add(outputTextFieldOctal);


		outputTextAreaBin = new JTextArea();
		outputTextAreaBin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		outputTextAreaBin.setForeground(new Color(255, 255, 255));
		outputTextAreaBin.setBackground(new Color(102, 102, 102));
		outputTextAreaBin.setBounds(82, 307, 498, 62);
		outputTextAreaBin.setWrapStyleWord(true);
		outputTextAreaBin.setLineWrap(true);
		outputTextAreaBin.setEditable(false);
		frame.getContentPane().add(outputTextAreaBin);


		inputTextField = new JTextField();
		inputTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		inputTextField.setBackground(new Color(255, 255, 225));
		inputTextField.setFont(new Font("Dialog", Font.PLAIN, 30));
		inputTextField.setEditable(false);
		inputTextField.setBounds(23, 23, 808, 71);
		frame.getContentPane().add(inputTextField);
		inputTextField.setColumns(10);


		memoryList = new List();
		memoryList.setBackground(new Color(128, 128, 128));
		memoryList.setForeground(new Color(255, 255, 255));
		memoryList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		memoryList.setBounds(888, 226, 404, 412);
		frame.getContentPane().add(memoryList);
		/*************************************************************/

		ButtonHandler buttonHandler = new ButtonHandler(inputTextField, 
				outputTextFieldHex, outputTextFieldDec, outputTextFieldOctal, outputTextAreaBin, numericalNotation, buttons_A_to_F, buttons_2_to_9, memoryList);

		/***** ARITHMETIC OPERATORS *****/
		JButton divisonButton = new JButton(" / "); 
		divisonButton.setBackground(new Color(102, 102, 102));
		divisonButton.setForeground(new Color(255, 255, 255));
		divisonButton.addActionListener(buttonHandler.new Input(divisonButton));
		divisonButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		divisonButton.setBounds(701, 403, 97, 76);
		frame.getContentPane().add(divisonButton);

		JButton multiplicationButton = new JButton(" * ");
		multiplicationButton.setBackground(new Color(102, 102, 102));
		multiplicationButton.setForeground(new Color(255, 255, 255));
		multiplicationButton.addActionListener(buttonHandler.new Input(multiplicationButton));
		multiplicationButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		multiplicationButton.setBounds(701, 493, 97, 76);
		frame.getContentPane().add(multiplicationButton);

		JButton subtractionButton = new JButton(" - ");
		subtractionButton.setBackground(new Color(102, 102, 102));
		subtractionButton.setForeground(new Color(255, 255, 255));
		subtractionButton.addActionListener(buttonHandler.new Input(subtractionButton));
		subtractionButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		subtractionButton.setBounds(701, 583, 97, 76);
		frame.getContentPane().add(subtractionButton);

		JButton additionButton = new JButton(" + ");
		additionButton.setBackground(new Color(102, 102, 102));
		additionButton.setForeground(new Color(255, 255, 255));
		additionButton.addActionListener(buttonHandler.new Input(additionButton));
		additionButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		additionButton.setBounds(701, 673, 97, 76);
		frame.getContentPane().add(additionButton);

		JButton equalButton = new JButton(" = ");
		equalButton.setBackground(new Color(255, 153, 0));
		equalButton.setForeground(new Color(255, 255, 255));
		equalButton.addActionListener(buttonHandler.new Validation());
		equalButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		equalButton.setBounds(592, 673, 97, 76);
		frame.getContentPane().add(equalButton);

		/***** LOGICAL OPERATORS *****/
		JButton andButton = new JButton(" (AND) ");
		andButton.setBackground(new Color(102, 102, 102));
		andButton.setForeground(new Color(255, 255, 255));
		andButton.addActionListener(buttonHandler.new Input(andButton));
		andButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		andButton.setBounds(592, 403, 97, 76);
		frame.getContentPane().add(andButton);

		JButton orButton = new JButton(" (OR) ");
		orButton.setBackground(new Color(102, 102, 102));
		orButton.setForeground(new Color(255, 255, 255));
		orButton.addActionListener(buttonHandler.new Input(orButton));
		orButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		orButton.setBounds(592, 493, 97, 76);
		frame.getContentPane().add(orButton);

		JButton notButton = new JButton(" (NOT) ");
		notButton.setBackground(new Color(102, 102, 102));
		notButton.setForeground(new Color(255, 255, 255));
		notButton.addActionListener(buttonHandler.new Input(notButton));
		notButton.setFont(new Font("Tahoma", Font.PLAIN, 19));
		notButton.setBounds(592, 583, 97, 76);
		frame.getContentPane().add(notButton);


		/****** LETTER BUTTONS ******/
		JButton btnA = new JButton("A");
		btnA.setForeground(new Color(0, 0, 0));
		btnA.setBackground(new Color(204, 204, 204));
		btnA.addActionListener(buttonHandler.new Input(btnA));
		btnA.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnA.setBounds(47, 403, 97, 76);
		frame.getContentPane().add(btnA);
		buttons_A_to_F.add(btnA);

		JButton btnB = new JButton("B");
		btnB.setForeground(new Color(0, 0, 0));
		btnB.setBackground(new Color(204, 204, 204));
		btnB.addActionListener(buttonHandler.new Input(btnB));
		btnB.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnB.setBounds(156, 403, 97, 76);
		frame.getContentPane().add(btnB);
		buttons_A_to_F.add(btnB);

		JButton btnC = new JButton("C");
		btnC.setBackground(new Color(204, 204, 204));
		btnC.setForeground(new Color(0, 0, 0));
		btnC.addActionListener(buttonHandler.new Input(btnC));
		btnC.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnC.setBounds(47, 493, 97, 76);
		frame.getContentPane().add(btnC);

		JButton btnD = new JButton("D");
		btnD.setBackground(new Color(204, 204, 204));
		btnD.setForeground(new Color(0, 0, 0));
		btnD.addActionListener(buttonHandler.new Input(btnD));
		btnD.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnD.setBounds(156, 493, 97, 76);
		frame.getContentPane().add(btnD);
		buttons_A_to_F.add(btnD);

		JButton btnE = new JButton("E");
		btnE.setBackground(new Color(204, 204, 204));
		btnE.setForeground(new Color(0, 0, 0));
		btnE.addActionListener(buttonHandler.new Input(btnE));
		btnE.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnE.setBounds(47, 583, 97, 76);
		frame.getContentPane().add(btnE);
		buttons_A_to_F.add(btnE);

		JButton btnF = new JButton("F");
		btnF.setBackground(new Color(204, 204, 204));
		btnF.setForeground(new Color(0, 0, 0));
		btnF.addActionListener(buttonHandler.new Input(btnF));
		btnF.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnF.setBounds(156, 583, 97, 76);
		frame.getContentPane().add(btnF);
		buttons_A_to_F.add(btnF);


		/***** NUMBER BUTTONS *****/
		JButton button_0 = new JButton("0");
		button_0.setBackground(new Color(204, 204, 204));
		button_0.setForeground(new Color(0, 0, 0));
		button_0.addActionListener(buttonHandler.new Input(button_0));
		button_0.setFont(new Font("Tahoma", Font.PLAIN, 30));
		button_0.setBounds(374, 673, 97, 76);
		frame.getContentPane().add(button_0);

		JButton button_1 = new JButton("1");
		button_1.setBackground(new Color(204, 204, 204));
		button_1.setForeground(new Color(0, 0, 0));
		button_1.addActionListener(buttonHandler.new Input(button_1));
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		button_1.setBounds(265, 583, 97, 76);
		frame.getContentPane().add(button_1);

		JButton button_2 = new JButton("2");
		button_2.setBackground(new Color(204, 204, 204));
		button_2.setForeground(new Color(0, 0, 0));
		button_2.addActionListener(buttonHandler.new Input(button_2));
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		button_2.setBounds(374, 583, 97, 76);
		frame.getContentPane().add(button_2);
		buttons_2_to_9.add(button_2);

		JButton button_3 = new JButton("3");
		button_3.setBackground(new Color(204, 204, 204));
		button_3.setForeground(new Color(0, 0, 0));
		button_3.addActionListener(buttonHandler.new Input(button_3));
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		button_3.setBounds(483, 583, 97, 76);
		frame.getContentPane().add(button_3);
		buttons_2_to_9.add(button_3);

		JButton button_4 = new JButton("4");
		button_4.setBackground(new Color(204, 204, 204));
		button_4.setForeground(new Color(0, 0, 0));
		button_4.addActionListener(buttonHandler.new Input(button_4));
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		button_4.setBounds(265, 493, 97, 76);
		frame.getContentPane().add(button_4);
		buttons_2_to_9.add(button_4);

		JButton button_5 = new JButton("5");
		button_5.setBackground(new Color(204, 204, 204));
		button_5.setForeground(new Color(0, 0, 0));
		button_5.addActionListener(buttonHandler.new Input(button_5));
		button_5.setFont(new Font("Tahoma", Font.PLAIN, 30));
		button_5.setBounds(374, 493, 97, 76);
		frame.getContentPane().add(button_5);
		buttons_2_to_9.add(button_5);

		JButton button_6 = new JButton("6");
		button_6.setBackground(new Color(204, 204, 204));
		button_6.setForeground(new Color(0, 0, 0));
		button_6.addActionListener(buttonHandler.new Input(button_6));
		button_6.setFont(new Font("Tahoma", Font.PLAIN, 30));
		button_6.setBounds(483, 493, 97, 76);
		frame.getContentPane().add(button_6);
		buttons_2_to_9.add(button_6);

		JButton button_7 = new JButton("7");
		button_7.setBackground(new Color(204, 204, 204));
		button_7.setForeground(new Color(0, 0, 0));
		button_7.addActionListener(buttonHandler.new Input(button_7));
		button_7.setFont(new Font("Tahoma", Font.PLAIN, 30));
		button_7.setBounds(265, 403, 97, 76);
		frame.getContentPane().add(button_7);
		buttons_2_to_9.add(button_7);

		JButton button_8 = new JButton("8");
		button_8.setBackground(new Color(204, 204, 204));
		button_8.setForeground(new Color(0, 0, 0));
		button_8.addActionListener(buttonHandler.new Input(button_8));
		button_8.setFont(new Font("Tahoma", Font.PLAIN, 30));
		button_8.setBounds(374, 403, 97, 76);
		frame.getContentPane().add(button_8);
		buttons_2_to_9.add(button_8);

		JButton button_9 = new JButton("9");
		button_9.setBackground(new Color(204, 204, 204));
		button_9.setForeground(new Color(0, 0, 0));
		button_9.addActionListener(buttonHandler.new Input(button_9));
		button_9.setFont(new Font("Tahoma", Font.PLAIN, 30));
		button_9.setBounds(483, 403, 97, 76);
		frame.getContentPane().add(button_9);
		buttons_2_to_9.add(button_9);


		/***** NUMERICAL NOTATION BUTTONS *****/
		JRadioButton radioHex = new JRadioButton("Hex");
		radioHex.setForeground(new Color(255, 255, 255));
		radioHex.setBackground(new Color(51, 51, 51));
		radioHex.setSelected(true);
		radioHex.addActionListener(buttonHandler.new HexSelection());
		radioHex.setFont(new Font("Tahoma", Font.PLAIN, 20));
		radioHex.setHorizontalAlignment(SwingConstants.CENTER);
		radioHex.setBounds(615, 154, 120, 77);
		frame.getContentPane().add(radioHex);
		numericalNotation.add(radioHex);

		JRadioButton radioDec = new JRadioButton("Dec");
		radioDec.setForeground(new Color(255, 255, 255));
		radioDec.setBackground(new Color(51, 51, 51));
		radioDec.addActionListener(buttonHandler.new DecSelection());
		radioDec.setHorizontalAlignment(SwingConstants.CENTER);
		radioDec.setFont(new Font("Tahoma", Font.PLAIN, 20));
		radioDec.setBounds(615, 243, 120, 77);
		frame.getContentPane().add(radioDec);
		numericalNotation.add(radioDec);

		JRadioButton radioOct = new JRadioButton("Oct");
		radioOct.setForeground(new Color(255, 255, 255));
		radioOct.setBackground(new Color(51, 51, 51));
		radioOct.addActionListener(buttonHandler.new OctalSelection(button_8, button_9));
		radioOct.setHorizontalAlignment(SwingConstants.CENTER);
		radioOct.setFont(new Font("Tahoma", Font.PLAIN, 20));
		radioOct.setBounds(739, 154, 120, 77);
		frame.getContentPane().add(radioOct);
		numericalNotation.add(radioOct);

		JRadioButton radioBin = new JRadioButton("Bin");
		radioBin.setForeground(new Color(255, 255, 255));
		radioBin.setBackground(new Color(51, 51, 51));
		radioBin.addActionListener(buttonHandler.new BinSelection());
		radioBin.setHorizontalAlignment(SwingConstants.CENTER);
		radioBin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		radioBin.setBounds(739, 243, 120, 77);
		frame.getContentPane().add(radioBin);
		numericalNotation.add(radioBin);


		/***** CLEAR CONTEXT BUTTONS *****/
		JButton btnBckSpc = new JButton("Backspace");
		btnBckSpc.setBackground(new Color(204, 204, 204));
		btnBckSpc.addActionListener(buttonHandler.new BackSpace());
		btnBckSpc.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnBckSpc.setBounds(156, 673, 206, 76);
		frame.getContentPane().add(btnBckSpc);


		JButton btnCe = new JButton("CE");
		btnCe.setBackground(new Color(204, 204, 204));
		btnCe.addActionListener(buttonHandler.new ClearInput());
		btnCe.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCe.setBounds(483, 673, 97, 76);
		frame.getContentPane().add(btnCe);
		buttons_A_to_F.add(btnC);


		/***** NEGATION BUTTON *****/
		JButton negationButton = new JButton("(-)");
		negationButton.setBackground(new Color(204, 204, 204));
		negationButton.addActionListener(buttonHandler.new Input(negationButton));
		negationButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		negationButton.setBounds(47, 673, 97, 76);
		frame.getContentPane().add(negationButton);



		/***** MEMORY BUTTONS ****/
		JLabel lblMemory = new JLabel("Memory");
		lblMemory.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemory.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMemory.setForeground(Color.WHITE);
		lblMemory.setBounds(995, 177, 193, 43);
		frame.getContentPane().add(lblMemory);

		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		saveButton.addActionListener(buttonHandler.new SaveExpression());
		saveButton.setBackground(new Color(204, 204, 204));
		saveButton.setBounds(888, 673, 89, 39);
		frame.getContentPane().add(saveButton);

		JButton applyButton = new JButton("Apply");
		applyButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		applyButton.addActionListener(buttonHandler.new ApplySavedExpression(button_8, button_9));
		applyButton.setBackground(new Color(204, 204, 204));
		applyButton.setBounds(995, 673, 89, 39);
		frame.getContentPane().add(applyButton);

		JButton deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteButton.addActionListener(buttonHandler.new DeleteSavedExpression());
		deleteButton.setBackground(new Color(204, 204, 204));
		deleteButton.setBounds(1102, 673, 89, 39);
		frame.getContentPane().add(deleteButton);

		JButton clearAllButton = new JButton("Clear All");
		clearAllButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		clearAllButton.addActionListener(buttonHandler.new ClearAllSavedExpressions());
		clearAllButton.setBackground(new Color(204, 204, 204));
		clearAllButton.setBounds(1203, 673, 89, 39);
		frame.getContentPane().add(clearAllButton);


	}
}
