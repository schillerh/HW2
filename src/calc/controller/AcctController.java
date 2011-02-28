package calc.controller;

import calc.model.AcctListModel;
import calc.model.AcctModel;
import calc.view.AcctListView;
import calc.view.AcctView;
import calc.view.JFrameView;

public class AcctController extends AbstractController {

	public AcctController(AcctModel acct, String currency) {
		setModel(new AcctModel(acct.aname(),acct.aid(), acct.abalance()));
		setView(new AcctView((AcctModel)getModel(), this, currency));
		((JFrameView)getView()).setVisible(true);
	}

	public void operation(String aOption) {
		throw new UnsupportedOperationException();
	}
}