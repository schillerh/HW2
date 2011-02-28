package calc.view;
import javax.swing.JFrame;

import calc.controller.Controller;
import calc.model.AbstractModel;
import calc.model.Model;
import calc.model.ModelListener;

abstract public class JFrameView extends JFrame implements View, ModelListener {
	private Model model;
	private Controller controller;
	public JFrameView (Model model, Controller controller){
		setModel(model);
		setController(controller);
	}
	public void registerWithModel(){
		((AbstractModel)model).addModelListener(this);
	}
	public Controller getController(){return controller;}
	
	public void setController(Controller controller){this.controller = controller;}
	
	public Model getModel(){return model;}
	
	public void setModel(Model model) {
		this.model = model;
		registerWithModel();
	}
	
}
