package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField display;
	private String operator ;
	private double num1;
	private double num2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test() {
		setTitle("Caculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		display = new JTextField();
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(display, BorderLayout.NORTH);
		display.setFont(new Font("Tahoma", Font.BOLD, 20));
		display.setEditable(false);
		display.setColumns(10);
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(6, 4, 5, 5));
		
		//------------------row1 col1 ----------------------------------
		JButton btnPercentage = new JButton("%");
		panel.add(btnPercentage);
		
		JButton btnCE = new JButton("CE	");
		panel.add(btnCE);
		
		JButton btnClear = new JButton("C");
		btnClear.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                display.setText("");
	            }
	        });
		panel.add(btnClear);
		JButton delete = new JButton("delete");
		panel.add(delete);
		
		// row2
		JButton reciprocal = new JButton("1/x");
		panel.add(reciprocal);
		JButton square = new JButton("x²");
		panel.add(square);
		JButton squareRoot = new JButton("²√x");
		panel.add(squareRoot);
		JButton divide = new JButton("÷");
//		divide.addActionListener(new OperatorActionListener());
		panel.add(divide);
		
		//row3
		JButton seven = new JButton("7");
		seven.addActionListener(new NumBerActionListener());
		panel.add(seven);
		
		JButton eight = new JButton("8");
		eight.addActionListener(new NumBerActionListener());
		panel.add(eight);
		
		JButton nine = new JButton("9");
		panel.add(nine);
		nine.addActionListener(new NumBerActionListener());
		
		JButton multiply = new JButton("×");
//		divide.addActionListener(new OperatorActionListener());
		panel.add(multiply);
		
		//row4
		JButton four = new JButton("4");
		panel.add(four);
		four.addActionListener(new NumBerActionListener());
		
		JButton five = new JButton("5");
		panel.add(five);
		five.addActionListener(new NumBerActionListener());
		
		JButton six = new JButton("6");
		panel.add(six);
		six.addActionListener(new NumBerActionListener());
		
		JButton minus = new JButton("-");
//		divide.addActionListener(new OperatorActionListener());
		panel.add(minus);
		
		//row5
		JButton one = new JButton("1");
		panel.add(one);
		one.addActionListener(new NumBerActionListener());
		
		JButton two = new JButton("2");
		panel.add(two);
		two.addActionListener(new NumBerActionListener());
		
		JButton three = new JButton("3");
		panel.add(three);
		three.addActionListener(new NumBerActionListener());
		
		JButton plus = new JButton("+");
//		divide.addActionListener(new OperatorActionListener());
		panel.add(plus);
		
		//row6
		JButton plusMinus = new JButton("+/-");
		panel.add(plusMinus);
		JButton zero = new JButton("0");
		zero.addActionListener(new NumBerActionListener());
		panel.add(zero);
		JButton dot = new JButton(".");
		panel.add(dot);
		JButton equal = new JButton("=");
		equal.addActionListener(new 
				OperatorActionListener());
		panel.add(equal);
		

		
	}
	
	private class NumBerActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String input = e.getActionCommand();
			display.setText(display.getText() + input);
		}
	}
	private class OperatorActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			double num1 = Double.parseDouble(display.getText());
			double num2 = Double.parseDouble(display.getText());
			double result = 0;
			switch(operator) {
			case " + "  ->
						result = num1 + num2; 
			case " - "  ->
						result = num1 - num2; 
			case " * "  ->
						result = num1 * num2; 
			case " /  "  ->
			{
				if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    display.setText("Error");
                    return;
                }
			} 
			}
//			display.setText(Double.toString(result));
			display.setText(String.valueOf(result));
//			display.setText("");
		}
	}
	

}



















