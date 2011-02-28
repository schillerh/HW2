package calc.controller;
import calc.model.Model;
import calc.view.View;

public interface Controller {
	void setModel(Model model);
	Model getModel();
	View getView();
	void setView(View view);
}
