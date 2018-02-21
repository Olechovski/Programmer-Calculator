package edu.iup.cosc319.calculator;

/**
 * This class is designed to launch the calculator program
 * 
 * @ Eric Olechovski
 */
import java.awt.EventQueue;


public class Calculator {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorGUI window = new CalculatorGUI();
					window.frame.setVisible(true);
					window.frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
