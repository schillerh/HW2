package calc.controller;
import calc.model.AcctListModel;
import calc.model.AcctModel;
import calc.view.AcctListView;
import calc.view.JFrameView;

import java.io.*;
import java.text.ParseException;

public class AcctListController extends AbstractController {

	public AcctListController(String fileName) throws FileNotFoundException, ParseException {
		setModel(new AcctListModel(fileName));
		setView(new AcctListView((AcctListModel)getModel(), this));
		((JFrameView)getView()).setVisible(true);
	}

	public void operation(String option, int index ){
		//if(option.equals("Dollars")){
			//new AcctController();
			//((AcctListModel)getModel()).acctList.get(index);
			//new AcctController(((AcctListModel)getModel()).acctList.get(index),option);
			//((AcctModel)getModel()).deposit("2930","USD");
			new AcctController(((AcctListModel)getModel()).getAccounts().get(index),option);
		/*}else if(option.equals("Euro")){
			try{
				((CalculatorModel)getModel()).add();
			}catch(Digit5 ex)
			{System.out.println(ex.getMessage());}
		}else if(option.equals(CalculatorView.CLEAR)){
			((CalculatorModel)getModel()).clear();
		}else if(option.equals(CalculatorView.EQUALS)){
			((CalculatorModel)getModel()).equals();
		}else {
			((CalculatorModel)getModel()).store(Integer.parseInt(option));
		}
		*/
	
}}