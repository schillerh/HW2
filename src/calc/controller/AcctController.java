package calc.controller;

import calc.model.AcctListModel;
import calc.model.AcctModel;
import calc.view.AcctListView;
import calc.view.AcctView;
import calc.view.JFrameView;

public class AcctController extends AbstractController {

	public AcctController(AcctModel acct, String currency) {
		setModel(acct);
		setView(new AcctView((AcctModel)getModel(), this, currency));
		((JFrameView)getView()).setVisible(true);
	}

	public void operation(String amount, String srcCurrency, String option ) {
		if (option=="Deposit"){
			((AcctModel)getModel()).deposit(amount, srcCurrency);
		}
		else if (option == "Withdraw"){
			((AcctModel)getModel()).withdraw(amount, srcCurrency);
		}
		else throw new UnsupportedOperationException();
	}
}