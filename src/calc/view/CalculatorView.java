package calc.view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calc.controller.CalculatorController;
import calc.model.CalculatorModel;
import calc.model.ModelEvent;

public class CalculatorView extends JFrameView {
	public static final String PLUS = "+"; 
	public static final String MINUS = "-"; 
	public static final String CLEAR = "Clr"; 
	public static final String EQUALS = "="; 
	private JTextField textField = new JTextField(); 
	private String operation = PLUS; 
	public CalculatorView(CalculatorModel model, CalculatorController controller) { 
		super(model, controller); 
		textField.setText("0"); 
		this.getContentPane().add(textField, BorderLayout.NORTH); 
		JPanel buttonPanel = new JPanel();         
		Handler handler = new Handler(); 
		JButton jButtonUSD = new JButton("Edit Account in $"); 
		jButtonUSD.addActionListener(handler); 
		JButton jButtonEuro = new JButton("Edit Account in Euro"); 
		jButtonEuro.addActionListener(handler); 
		JButton jButtonYen = new JButton("Edit Account in Yen"); 
		jButtonYen.addActionListener(handler); 
		JButton jButtonSave = new JButton("Save"); 
		jButtonSave.addActionListener(handler); 
		JButton jButtonExit = new JButton("Exit"); 
		jButtonExit.addActionListener(handler); 
		JButton jButtonDeposit = new JButton("Deposit"); 
		jButtonDeposit.addActionListener(handler); 
		JButton jButtonWithdraw = new JButton("Withdraw"); 
		jButtonWithdraw.addActionListener(handler); 
		JButton jButtonDismiss = new JButton("Dismiss"); 
		jButtonDismiss.addActionListener(handler); 
		JButton jButton9 = new JButton("9"); 
		jButton9.addActionListener(handler); 
		JButton jButton0 = new JButton("0"); 
		jButton0.addActionListener(handler); 
		JButton minusButton = new JButton(MINUS); 
		minusButton.addActionListener(handler); 
		JButton plusButton = new JButton(PLUS); 
		plusButton.addActionListener(handler); 
		JButton clearButton = new JButton(CLEAR); 
		clearButton.addActionListener(handler); 
		JButton equalsButton = new JButton(EQUALS); 
		equalsButton.addActionListener(handler); 
	buttonPanel.setLayout(new GridLayout(5, 3, 1, 1));
	this.getContentPane().add(buttonPanel, BorderLayout.CENTER);
	buttonPanel.add(jButtonUSD, null);
	buttonPanel.add(jButtonEuro, null);
	buttonPanel.add(jButtonYen, null);
	buttonPanel.add(jButtonSave, null);
	buttonPanel.add(jButtonExit, null);
	buttonPanel.add(jButtonDeposit, null);
	buttonPanel.add(jButtonWithdraw, null);
	buttonPanel.add(jButtonDismiss, null);
	buttonPanel.add(jButton9, null);
	buttonPanel.add(jButton0, null);
	buttonPanel.add(minusButton, null);
	buttonPanel.add(plusButton, null);
	buttonPanel.add(clearButton, null);
	buttonPanel.add(equalsButton, null);
	pack();

	 }
	 // Now implement the necessary event handling code 
	public void modelChanged(ModelEvent event) {
	String msg = event.getAmount() + "";
	textField.setText(msg);

	 }
	 // Inner classes for Event Handling 
	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			((CalculatorController)getController()).operation(e.getActionCommand()); 
	    } }
	
	public static void main(String [] args) { new CalculatorController(); }
}
