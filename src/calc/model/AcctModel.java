package calc.model;
import java.lang.Double;

public class AcctModel extends AbstractModel {
	private String acctName;
	private int acctNumber;
	private double acctBalance;

	public AcctModel(String aName, int aNumber, double aBalance) {
		this.acctName=aName;
		this.acctNumber=aNumber;
		this.acctBalance= aBalance;
		printAcct();
		//throw new UnsupportedOperationException();
	}

	public double getBalance(int accNum, String aCurrency){
		return this.fromUSD(this.acctBalance, aCurrency);
	}
	public double getBalance(){
		return acctBalance;
	}
	public String getName(int accNum){
		return this.acctName;
	}
	
	public double deposit(String amount, String srcCurrency) {
		System.out.println("Deposit amount: "+amount);
		
		double aAmount = Double.valueOf(amount.trim()).doubleValue();
		System.out.println("After double: "+aAmount);
		this.acctBalance+=this.toUSD(aAmount, srcCurrency);
	
		ModelEvent me = new ModelEvent(this, 1, "", this.acctBalance);
		notifyChanged(me);
		return acctBalance;
	}

	public void withdraw(double aAmount, String srcCurrency) {
		if (this.toUSD(aAmount, srcCurrency)>this.acctBalance){
			throw new UnsupportedOperationException();
		}
		else this.acctBalance= this.acctBalance-this.toUSD(aAmount, srcCurrency);
		
		ModelEvent me = new ModelEvent(this, 1, "", this.acctBalance);
		notifyChanged(me);
	}

	public double fromUSD(double aAmount, String toCurrency) {
		if (toCurrency=="Euro"){
			return aAmount*.79;
		}
		else if (toCurrency=="Yen"){
			return aAmount*94.1;
		}
		else return aAmount;
	}
	public String aname(){
		return this.acctName;
	}
	public Integer aid(){
		return this.acctNumber;
	}
	public Double abalance(){
		return this.acctBalance;
	}
	public void printAcct(){
		System.out.println(this.acctName+" : "+ this.acctNumber +" : "+ this.acctBalance);
	}
	public double toUSD(double aAmount, String fromCurrency){
		if (fromCurrency=="Yen"){
			return aAmount/94.1;
		}
		else if (fromCurrency=="Euro"){
			return aAmount/.79;
		}
		else return aAmount;
	}
}