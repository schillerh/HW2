package calc;

import java.io.FileNotFoundException;
import java.text.ParseException;

import calc.controller.AcctListController;

public class AccountManager {

	/**
	 * @param args
	 */
	public static void main(String [] args) throws FileNotFoundException, ParseException { 
		String temp;
		temp= args[0];
		System.out.println(temp);
		new AcctListController(temp); }

	}

