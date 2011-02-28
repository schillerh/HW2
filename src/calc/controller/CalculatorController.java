package calc.controller;
import calc.model.CalculatorModel;
import calc.model.Digit5;
import calc.view.CalculatorView;
import calc.view.JFrameView;

public class CalculatorController extends AbstractController {
	public CalculatorController(){
		setModel(new CalculatorModel());
		setView(new CalculatorView((CalculatorModel)getModel(), this));
		((JFrameView)getView()).setVisible(true);
	}
	public void operation(String option){
		if(option.equals(CalculatorView.MINUS)){
			((CalculatorModel)getModel()).subtract();
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
	}
}
