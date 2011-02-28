package calc.view;
import calc.controller.Controller;
import calc.model.Model;

public interface View {
	Controller getController();
	void setController(Controller controller);
	Model getModel();
	void setModel(Model model);
}
