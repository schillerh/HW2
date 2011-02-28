package calc.view;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Vector;


//import reference.Account;
import reference.AccountingViewDelegate;

import calc.controller.AcctListController;

import calc.controller.AcctController;
import calc.model.AcctListModel;
import calc.model.AcctModel;
import calc.model.ModelEvent;

public class AcctListView extends JFrameView {

    private AccountingViewDelegate delegate;
    private AcctListModel accounting;

    private JComboBox comboBox;
    public static final String USD = "Edit Account in $";
	public static final String YEN = "Edit Account in Yen";
	public static final String EURO = "Edit Account in Euro";
	public static final String SAVE = "Save";
	public static final String EXIT = "Exit";
    private static final long serialVersionUID = 4193418810079590485L;

	public AcctListView(AcctListModel model, AcctListController controller) { 
		super(model, controller); 
 
    	this.accounting = model;
    

       try {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            GridBagConstraints constraints = new GridBagConstraints();

            // Main container
            JPanel rootPanel = new JPanel();
            rootPanel.setLayout(new GridBagLayout());

            // Accounting container
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createTitledBorder("Accounting"));
            panel.setLayout(new GridBagLayout());

            // Add a selection for accounts
            constraints.insets = new Insets(4,4,4,4);
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = 3;
            constraints.gridx = 0;
            constraints.gridy = 0;
           // String test[accounting.acctListSize()]= accounting.getAcc();
            //Vector<AcctModel> test = new Vector<AcctModel>(acct);
            //System.out.println(acct.toString());
            //System.out.println(test);
            System.out.println(accounting.acctList.toString());
            //System.out.println(accounting.getAccounts().toArray());
            Vector test= accounting.getAcc();
            System.out.println(test.elements());
            System.out.println("Account testing stuff: "+this.accounting.getAccounts().toString());
            comboBox = new JComboBox(this.accounting.getName());
            panel.add(comboBox, constraints);

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = 1;
            constraints.gridx = 0;
            constraints.gridy = 1;

            // Add the editing buttons
            //for (final Currency currency : Currency.ALL) {
            
            
            // Add edit buttons

            Handler handler = new Handler(); 
    		JButton jButtonUSD = new JButton(USD); 
    		jButtonUSD.addActionListener(new ActionListener() {
    			this.accounting.getAccounts().get(comboBox.getSelectedIndex());
    			public void actionPerformed(ActionEvent event) {
                    //doEditAccount(currency);
                	new AcctController(accounting.getAccounts().get(comboBox.getSelectedIndex()),"Dollars");
                }
    		});
    		panel.add(jButtonUSD, constraints);
            constraints.gridx++;
    		JButton jButtonEuro = new JButton(EURO); 
    		jButtonEuro.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    //doEditAccount(currency);
                	new AcctController(accounting.getAccounts().get(comboBox.getSelectedIndex()),"Euro");
                }
    		});
    		panel.add(jButtonEuro, constraints);
            constraints.gridx++;
    		JButton jButtonYen = new JButton(YEN); 
    		jButtonYen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    //doEditAccount(currency);
                	new AcctController(accounting.getAccounts().get(comboBox.getSelectedIndex()),"Yen");
                }
    		}); 
    		panel.add(jButtonYen, constraints);
            constraints.gridx++;
            
            /* JButton button = new JButton(USD);
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        doEditAccount(currency);
                    }
                });
                */


       
                //}

            // Add all account panel stuff
            constraints = new GridBagConstraints();
            constraints.gridx = constraints.gridy = 0;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = 3;
            constraints.insets = new Insets(10, 10, 10, 10);
            rootPanel.add(panel, constraints);

            // Make some blank space
            constraints = new GridBagConstraints();
            constraints.weightx = 1.0;
            constraints.gridx = 0;
            constraints.gridy = 1;
            rootPanel.add(new JLabel(), constraints);

            // Add a Save button
            JButton jButtonSave = new JButton(SAVE); 
    		jButtonSave.addActionListener(handler); 
    		
            
            /*JButton button = new JButton("Save");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    delegate.doSave();
                }
            });*/
            constraints.weightx = 0.0;
            constraints.gridx = 1;
            constraints.gridy = 1;
            constraints.anchor = GridBagConstraints.LAST_LINE_END;
            constraints.insets = new Insets(0, 0, 10, 0);
            rootPanel.add(jButtonSave, constraints);

            // And an Exit button
            JButton jButtonExit = new JButton(EXIT); 
    		jButtonExit.addActionListener(handler); 
            /*button = new JButton("Exit");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    delegate.doExit();
                }
            });
            */
            constraints.gridx = 2;
            constraints.insets = new Insets(0, 0, 10, 10);
            rootPanel.add(jButtonExit, constraints);


            this.setContentPane(rootPanel);
            this.pack();
            this.setTitle("Accounting ");
            this.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /*private void doEditAccount(Currency currency) {
        delegate.doEditAccount((Account)comboBox.getSelectedItem(), currency);
    }
*/
    public AccountingViewDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(AccountingViewDelegate delegate) {
        this.delegate = delegate;
    }


	
	 // Now implement the necessary event handling code 
	public void modelChanged(ModelEvent event) {
	//String msg = event.getAmount() + "";
	//textField.setText(msg);
	comboBox.getSelectedItem();
	 }
 // Inner classes for Event Handling 
	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			((AcctListController)getController()).operation(e.getActionCommand()); 
	    } }
	

	
}
	/*
	private static final long serialVersionUID = 4193418810079590485L;
	public static final String PLUS = "+"; 
	public static final String MINUS = "-"; 
	public static final String CLEAR = "Clr"; 
	public static final String EQUALS = "=";
	public static final String USD = "Edit Account in $";
	public static final String YEN = "Edit Account in Yen";
	public static final String EURO = "Edit Account in Euro";
	public static final String SAVE = "Save";
	public static final String EXIT = "Exit";
	public static final String DEPOSIT = "Deposit";
	public static final String WITHDRAW = "Withdraw";
	public static final String DISMISS = "Dismiss";
	
	private JTextField textField = new JTextField(); 
	private String operation = PLUS; 
	
	public AcctListView(AcctListModel model, AcctListController controller) { 
		super(model, controller); 
		textField.setText("0"); 
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
			((AcctListController)getController()).operation(e.getActionCommand(),"18273"); 
	    } }
	
	public static void main(String [] args) throws FileNotFoundException, ParseException { 
		String temp;
		temp= args[0];
		System.out.println(temp);
		new AcctListController(args[0]); }
}
*/