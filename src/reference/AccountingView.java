package accountManager.view;

import accountManager.model.Account;
import accountManager.model.Accounting;
import accountManager.util.Currency;
import sun.awt.VerticalBagLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountingView extends JFrame {
    private AccountingViewDelegate delegate;
    private Accounting accounting;
    private JComboBox comboBox;
    
    public AccountingView(Accounting accounting) {
        this.accounting = accounting;
    }

    public void create() {
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
            comboBox = new JComboBox(accounting.getAccounts());
            panel.add(comboBox, constraints);

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = 1;
            constraints.gridx = 0;
            constraints.gridy = 1;

            // Add the editing buttons
            for (final Currency currency : Currency.ALL) {
                // Add edit buttons
                JButton button = new JButton("Edit using " + currency.getSymbol());
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        doEditAccount(currency);
                    }
                });

                panel.add(button, constraints);
                constraints.gridx++;
            }

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
            JButton button = new JButton("Save");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    delegate.doSave();
                }
            });
            constraints.weightx = 0.0;
            constraints.gridx = 1;
            constraints.gridy = 1;
            constraints.anchor = GridBagConstraints.LAST_LINE_END;
            constraints.insets = new Insets(0, 0, 10, 0);
            rootPanel.add(button, constraints);

            // And an Exit button
            button = new JButton("Exit");
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    delegate.doExit();
                }
            });
            constraints.gridx = 2;
            constraints.insets = new Insets(0, 0, 10, 10);
            rootPanel.add(button, constraints);


            this.setContentPane(rootPanel);
            this.pack();
            this.setTitle("Accounting - " + accounting.getFilename());
            this.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void doEditAccount(Currency currency) {
        delegate.doEditAccount((Account)comboBox.getSelectedItem(), currency);
    }

    public AccountingViewDelegate getDelegate() {
        return delegate;
    }

    public void setDelegate(AccountingViewDelegate delegate) {
        this.delegate = delegate;
    }
}
