package calc.model;
import java.lang.Double;
import java.text.DecimalFormat;

public class AcctModel extends AbstractModel {
	private String acctName;
	private int acctNumber;
	private double acctBalance;
	DecimalFormat df= new DecimalFormat("#0.00");
	
	public AcctModel(String aName, int aNumber, double aBalance) {
		this.acctName=aName;
		this.acctNumber=aNumber;
		this.acctBalance= aBalance;
		printAcct();
		//throw new UnsupportedOperationException();
	}

	public double getBalance(){
		return acctBalance;
	}
	public double getBalance(String currency){
		return this.fromUSD(acctBalance, currency);
	}
	public String getName(){
		return this.acctName;
	}
	public Integer getAcctNumber(){
		return this.acctNumber;
	}
	
	public void deposit(String amount, String srcCurrency) {
		System.out.println("Deposit amount: "+amount);
		
		double aAmount = Double.valueOf(amount.trim()).doubleValue();
		System.out.println("After double: "+aAmount);
		this.acctBalance+=this.toUSD(aAmount, srcCurrency);
		ModelEvent me = new ModelEvent(this, 3, srcCurrency, Double.valueOf(df.format(this.fromUSD(this.acctBalance, srcCurrency))));
		notifyChanged(me);
		//return Double.valueOf(df.format(this.fromUSD(this.acctBalance, srcCurrency)));
	}

	public void withdraw(String amount, String srcCurrency) {
		double aAmount = Double.valueOf(amount.trim()).doubleValue();
		if (this.toUSD(aAmount, srcCurrency)>this.acctBalance){
			throw new UnsupportedOperationException();
		}
		else this.acctBalance= this.acctBalance-this.toUSD(aAmount, srcCurrency);
		
		ModelEvent me = new ModelEvent(this, 2, srcCurrency, Double.valueOf(df.format(this.fromUSD(acctBalance, srcCurrency))));
		notifyChanged(me);
		//return Double.valueOf(df.format(this.fromUSD(this.acctBalance, srcCurrency)));
	}

	public double fromUSD(double aAmount, String toCurrency) {
		if (toCurrency=="Euro"){
			return  Double.valueOf(df.format(aAmount*.79));
		}
		else if (toCurrency=="Yen"){
			return Double.valueOf(df.format(aAmount*94.1));
		}
		else return Double.valueOf(df.format(aAmount));
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