package calc.model;
import java.awt.event.ActionEvent;

public class ModelEvent extends ActionEvent {
	private double amount;
	private String currency;
	public ModelEvent(Object obj, int id, String currency, double amount){
		super(obj, id, currency);
		this.amount = amount;
		this.currency = currency;
	}
	public double getAmount(){return amount;}
	public String  getCurrency(){return currency;}
}
