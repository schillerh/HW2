package accountManager.view;

import accountManager.model.Account;
import accountManager.util.Currency;

public interface AccountingViewDelegate {

    public void doEditAccount(Account event, Currency currency);

    public void doSave();

    public void doExit();
}
