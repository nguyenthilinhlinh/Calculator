package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Font;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

public class Calculator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField display;
	private JPanel panel;
	private double result,num1,num2;
	private String operator = "" ;
	private boolean isNewInput; // overwrite the previous result or append to it

	private Map<String,Runnable> actions ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param HasMap 
	 */
	public Calculator() {
		setTitle("Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 707, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		display = new JTextField();
		display.setFont(new Font("Tahoma", Font.BOLD, 11));
		display.setEditable(false);
		contentPane.add(display, BorderLayout.NORTH);
		display.setColumns(10);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(6, 4, 5, 5));
		
		String[] buttons = {
		        "%", "CE", "C", "backspace",
		        "1/x", "x²", "²√x", "/",
		        "7", "8", "9", "×",
		        "4", "5", "6", "-",
		        "1", "2", "3", "+",
		        "+/-", "0", ".", "="
		    };
		
		for(String text : buttons) {
			JButton button = new JButton(text);
			button.setFont(new Font("Tahoma", Font.BOLD, 11));
			panel.add(button);
			if ("0123456789.".contains(text)) {
                button.addActionListener(new NumberActionListener());
            } else if ("+-×/".contains(text)) {
                button.addActionListener(new OperatorActionListener());
            } else {
                button.addActionListener(new FunctionActionListener());
            }
		}
		actions = new HashMap<>();
		actions.put("C",() -> clear());
		actions.put("CE",() -> clearEntry());
        actions.put("backspace", () -> backspace());
        actions.put("%", () -> handlePercentage());
        actions.put("1/x", () -> handleReciprocal());
        actions.put("x²", () -> handleSquare());
        actions.put("√x", () -> handleSquareRoot());
        actions.put("+/-", () -> toggleSign());
        actions.put("=", () -> calculateResult());
//		add(panel);
//		setVisible(true);

			
		}	

		private void calculateResult() {
		
			 num2 = Double.parseDouble(display.getText());
			 double result = 0;
			 switch(operator) {
				 case "+" -> result = num1 + num2;
				 case "-" -> result = num1 - num2;
				 case "×" -> result = num1 * num2;
				 case "/" -> {
				 if(num2 != 0) {
					 result = num1 / num2;
				 } else {
					 display.setText("Không thể chia cho 0");
					 return;
				 }
				 }
			 }
			 display.setText(String.valueOf(result));
			 isNewInput = true;
		           
			 }
		
		private class NumberActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = e.getActionCommand();
				if (isNewInput) {
					display.setText("");
					isNewInput = false;
				}
				display.setText(display.getText() + input);
			}
		}
		private class OperatorActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				operator = e.getActionCommand();
				num1 = Double.parseDouble(display.getText());
				 display.setText(display.getText() + " " + operator + " ");
				isNewInput = true;
			}
		}
		
		private class FunctionActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				if(actions.containsKey(command)) {
					actions.get(command).run();
				}
			}
			
	
	}
		private void clear() {
			display.setText("");
			operator = "";
			num1 = 0;
			num2 = 0;
			isNewInput = true;
			
			}

		private void clearEntry() {
			display.setText("");
			isNewInput = true;
			}

		private void backspace() {
			String text = display.getText();
			if (text.length() > 0) {
				text = text.substring(0, text.length() - 1);
			}
			display.setText(text);
			isNewInput = true;
		}	
		
		private void handlePercentage() {
			num1 = Double.parseDouble(display.getText()) / 100;
//			num1 = num1 /100 ;
			display.setText(String.valueOf(num1));
		}
		
		private void handleReciprocal() {
			double num = Double.parseDouble(display.getText());
			if(num != 0) {
				display.setText(String.valueOf(1 / num));
			}
			else {
				display.setText("Không thể chia cho 0");
				return;
			}
		}
		private void handleSquare() {
			double num = Double.parseDouble(display.getText());
				display.setText(String.valueOf(num * num));
			
		}
		private void handleSquareRoot() {
			double num = Double.parseDouble(display.getText());
			display.setText(String.valueOf(Math.sqrt(num)));
		}
		private void toggleSign() {
			double num = Double.parseDouble(display.getText());
			display.setText(String.valueOf(-num));
		}
		
		}

		


















