package accountManager.view;

import accountManager.model.Account;
import accountManager.util.Currency;
//import accountManager.util.NumericTextField;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountView extends JFrame implements DocumentListener {
    private AccountViewDelegate delegate;
    private NumericTextField entryField;
    private JTextField balanceField;

    private Account account;
    private Currency currency;


	public AccountView(Account account, Currency currency) {
		this.account = account;
        this.currency = currency;
	}

    public void create() {
        try {
            JPanel rootPanel = new JPanel();
            rootPanel.setLayout(new GridBagLayout());

            GridBagConstraints constraints = new GridBagConstraints();

            JPanel transactionPanel = new JPanel();
            transactionPanel.setLayout(new GridBagLayout());
            transactionPanel.setBorder(BorderFactory.createTitledBorder("Transaction"));

            // Listen to changes to the account balance document
            account.getDocument().addDocumentListener(this);

            // Show available funds
            JLabel fundsLabel = new JLabel("Available funds in " + currency.getSymbol(), null, JLabel.RIGHT);
            constraints.anchor = GridBagConstraints.EAST;
            transactionPanel.add(fundsLabel, constraints);
            balanceField = new JTextField(10);
            updateBalance();
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
            JLabel entryLabel = new JLabel("Enter amount in " + currency.getSymbol()+ "", null, JLabel.RIGHT);
            constraints.anchor = GridBagConstraints.EAST;
            constraints.gridwidth = 1;
            constraints.gridy = 2;
            transactionPanel.add(entryLabel, constraints);

            entryField = new NumericTextField(10, account.getDocument().getFormat());
            clearEntry();
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 1;
            constraints.gridy = 2;
            transactionPanel.add(entryField, constraints);

            // Add a button to make deposits
            JButton button = new JButton("Deposit");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    doDeposit();
                }
            });
            constraints = new GridBagConstraints();
            constraints.gridy = 3;
            transactionPanel.add(button, constraints);

            // Add a button to make withdrawals
            button = new JButton("Withdraw");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    doWithdraw();
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
            this.setTitle(account.getName() + " " + account.getId() + "; Operations in " + currency.getName());
            this.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void clearEntry() {
        entryField.setValue(0.00);
    }

    public void updateBalance() {
        balanceField.setText(account.getDocument().getFormat().format(currency.fromUSD(account.getBalance())));
    }

    private void doWithdraw() {
        try {
            delegate.withdraw(new Double(entryField.getText()));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    private void doDeposit() {
        try {
            delegate.deposit(new Double(entryField.getText()));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }

    public AccountViewDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(AccountViewDelegate delegate) {
        this.delegate = delegate;
    }

    public void insertUpdate(DocumentEvent e) {
        updateBalance();

    }

    public void removeUpdate(DocumentEvent e) {
        updateBalance();

    }

    public void changedUpdate(DocumentEvent e) {
        updateBalance();
    }
}
