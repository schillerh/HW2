package accountManager.test;

import accountManager.model.Account;
import accountManager.util.InsufficientFundsException;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
    private final double initialBalance = 50.0;
    private Account account50;
    
    @Before
    public void setUp() {
        account50 = new Account("test", 123, initialBalance);
    }

    @Test
    public void depositTest() {
        double deposit = initialBalance;
        Account expected = new Account("name", 123, initialBalance + deposit);
        Account result = account50.deposit(deposit);
        org.junit.Assert.assertEquals(expected.getBalance(), result.getBalance(), 0.00000001);
    }

    @Test
    public void withdrawSuccessTest() throws InsufficientFundsException {
        double withdraw = initialBalance / 2;
        Account expected = new Account("name", 123, initialBalance - withdraw);
        Account result = account50.withdraw(withdraw);
        org.junit.Assert.assertEquals(expected.getBalance(), result.getBalance(), 0.00000001);
    }

    @Test(expected = InsufficientFundsException.class) public void withdrawFailTest() throws InsufficientFundsException {
        double withdraw = 2 * initialBalance;
        account50.withdraw(withdraw);
    }


}
