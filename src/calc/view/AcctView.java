package calc.view;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;

//import reference.Account;
//import reference.AccountViewDelegate;
//import reference.Currency;
//import reference.NumericTextField;

import calc.controller.AcctController;
import calc.controller.AcctListController;
import calc.model.AcctModel;
import calc.model.AcctListModel;
import calc.model.ModelEvent;

public class AcctView extends JFrameView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4193418810079590234L;
	public static final String DEPOSIT = "Deposit";
	public static final String WITHDRAW = "Withdraw";
	public static final String DISMISS = "Dismiss";
	
	private String srcCurrency;
	private JTextField textField = new JTextField(); 
	private JTextField balanceField = new JTextField();
	private JTextField entryField = new JTextField();
	//private String operation = PLUS; 
	
	public AcctView(final AcctModel model, AcctController controller, final String currency) { 
		super(model, controller); 
		textField.setText(model.abalance().toString());
		this.srcCurrency=currency;
		//private AccountViewDelegate delegate;
	   // private NumericTextField entryField;
	   
	    
	    try{
	    GridBagConstraints constraints = new GridBagConstraints();
	            JPanel rootPanel = new JPanel();
	            rootPanel.setLayout(new GridBagLayout());

	            

	            JPanel transactionPanel = new JPanel();
	            transactionPanel.setLayout(new GridBagLayout());
	            transactionPanel.setBorder(BorderFactory.createTitledBorder("Transaction"));

	            // Listen to changes to the account balance document
	            
	            //account.getDocument().addDocumentListener(this);

	            // Show available funds
	            JLabel fundsLabel = new JLabel("Available funds in " +currency, null, JLabel.RIGHT);
	            constraints.anchor = GridBagConstraints.EAST;
	            transactionPanel.add(fundsLabel, constraints);
	            balanceField = new JTextField(10);
	            //updateBalance();
	            System.out.println("model.abbalance: "+model.abalance());
	            System.out.println("model.abbalance to string: "+model.abalance().toString());
	            balanceField.setText(String.valueOf(model.fromUSD(model.abalance(),currency)));
	            balanceField.setEditable(false);
	            constraints.gridx = 1;
	            transactionPanel.add(balanceField, constraints);

	            // Make a pretty separator
	            JSeparator separator = new JSeparator();
	            //separator.setBorder(BorderFactory.createEtchedBorder());
	            constraints.gridx = 0;
	            constraints.gridy = 1;
	            constraints.gridwidth = 2;
	            constraints.fill = GridBagConstraints.HORIZONTAL;
	            transactionPanel.add(separator, constraints);

	            // Add an entry field with a label
	            JLabel entryLabel = new JLabel("Enter amount in " + currency+ "", null, JLabel.RIGHT);
	            constraints.anchor = GridBagConstraints.EAST;
	            constraints.gridwidth = 1;
	            constraints.gridy = 2;
	            transactionPanel.add(entryLabel, constraints);

	            entryField = new JTextField("0.00");
	            clearEntry();
	            constraints.fill = GridBagConstraints.HORIZONTAL;
	            constraints.gridx = 1;
	            constraints.gridy = 2;
	            transactionPanel.add(entryField, constraints);

	            // Add a button to make deposits
	            JButton button = new JButton("Deposit");
	            button.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent event) {
	//                    doDeposit();
	                	//balanceField.setText(String.valueOf(((AcctModel)getModel()).deposit(entryField.getText(), srcCurrency)));
	                   // balanceField.setText(String.valueOf(model.deposit(entryField.getText(), srcCurrency)));
	                    ((AcctController)getController()).operation(entryField.getText(),srcCurrency, "Deposit");
	                   // updateBalance();
	                   
	                }
	            });
	            constraints = new GridBagConstraints();
	            constraints.gridy = 3;
	            transactionPanel.add(button, constraints);

	            // Add a button to make withdrawals
	            button = new JButton("Withdraw");
	            button.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent event) {
	                	//balanceField.setText(String.valueOf(model.withdraw(entryField.getText(), srcCurrency)));
	                	((AcctController)getController()).operation(entryField.getText(),srcCurrency, "Withdraw");
	                    //updateBalance();	
	                }
	            });
	            constraints.gridx = 1;
	            transactionPanel.add(button, constraints);

	            // Add the transaction panel
	            constraints = new GridBagConstraints();
	            constraints.insets = new Insets(10, 10, 10, 10);
	            constraints.ipadx = constraints.ipady = 10;
	            rootPanel.add(transactionPanel, constraints);

	            // Add a button to close the window
	            button = new JButton("Dismiss");
	            button.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent event) {
	                    dispose();
	                }
	            });
	            constraints = new GridBagConstraints();
	            constraints.insets = new Insets(0, 10, 10, 10);
	            constraints.gridx = 0;
	            constraints.gridy = 1;
	            constraints.anchor = GridBagConstraints.LAST_LINE_END;
	            rootPanel.add(button, constraints);


	            this.setContentPane(rootPanel);
	            this.pack();
	            this.setTitle(model.aname() + " " + model.aid() + "; Operations in " + currency);
	            this.setVisible(true);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

	    public void clearEntry() {
	        //entryField.(0.00);
	    }

	    public void updateBalance() {
	        this.balanceField.setText(String.valueOf(((AcctModel)getModel()).getBalance(this.srcCurrency)));
	    }
	    
	    /*
	    private void doWithdraw() {
	        try {
	           this.model.this.withdraw(new Double(entryField.getText()));
	        } catch (NumberFormatException ex) {
	            ex.printStackTrace();
	        }
	    }

	    private void doDeposit() {
	        try {
	            model.deposit(new Double(textField.getText()));
	        } catch (NumberFormatException ex) {
	            ex.printStackTrace();
	        }
	    }
*/
	    //public AccountViewDelegate getDelegate() {
	      //  return delegate;
	    //}

	   // public void setDelegate(AccountViewDelegate delegate) {
	    //    this.delegate = delegate;
	    //}

	    /*public void insertUpdate(DocumentEvent e) {
	        updateBalance();

	    }

	    public void removeUpdate(DocumentEvent e) {
	        updateBalance();

	    }

	    public void changedUpdate(DocumentEvent e) {
	        updateBalance();
	    }*/
		 // Now implement the necessary event handling code 
		public void modelChanged(ModelEvent event) {
			updateBalance();	
		//balanceField.setText(String.valueOf(event.getAmount()));

		 }
		 // Inner classes for Event Handling 
		class Handler implements ActionListener { 
			// Event handling is handled locally
			public void actionPerformed(ActionEvent e) {
				((AcctController)getController()).operation(e.getActionCommand(),"",""); 
		    } }
	}
/*
		this.getContentPane().add(textField, BorderLayout.NORTH); 
		JPanel buttonPanel = new JPanel();         
		Handler handler = new Handler(); 
		JButton jButtonUSD = new JButton(USD); 
		jButtonUSD.addActionListener(handler); 
		JButton jButtonEuro = new JButton(EURO); 
		jButtonEuro.addActionListener(handler); 
		JButton jButtonYen = new JButton(YEN); 
		jButtonYen.addActionListener(handler); 
		JButton jButtonSave = new JButton(SAVE); 
		jButtonSave.addActionListener(handler); 
		JButton jButtonExit = new JButton(EXIT); 
		jButtonExit.addActionListener(handler); 
		JButton jButtonDeposit = new JButton(DEPOSIT); 
		jButtonDeposit.addActionListener(handler); 
		JButton jButtonWithdraw = new JButton(WITHDRAW); 
		jButtonWithdraw.addActionListener(handler); 
		JButton jButtonDismiss = new JButton(DISMISS); 
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
			((AcctController)getController()).operation(e.getActionCommand()); 
	    } }
	
}*/
