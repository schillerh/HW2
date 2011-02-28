package accountManager.test;

import accountManager.model.Account;
import accountManager.model.Accounting;
import org.junit.Before;
import org.junit.Test;

public class AccountingTest {
    private Accounting accounting;

    @Before
    public void setUp() {
        accounting = new Accounting();
    }
    
    @Test
    public void addAccountTest() {
        Account newAccount = new Account("name", 123, 50.0);

        Account[] expected = {newAccount};
        accounting.addAccount(newAccount);
        Account[] result = accounting.getAccounts();

        org.junit.Assert.assertArrayEquals(expected, result);
    }
}
