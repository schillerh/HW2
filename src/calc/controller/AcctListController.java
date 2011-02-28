package calc.controller;
import calc.model.AcctListModel;
import calc.model.AcctModel;
import calc.model.CalculatorModel;
import calc.model.Digit5;
import calc.view.CalculatorView;
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

	public void operation(String option ){
		if(option.equals(AcctListView.USD)){
			//new AcctController();
			((AcctModel)getModel()).deposit("2930","USD");
		}else if(option.equals(CalculatorView.PLUS)){
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
	
}}