package calc.model;
import calc.model.AcctModel;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;
import java.io.*;
import java.lang.String;
import java.nio.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import calc.view.AcctListView;

//import reference.Account;
//import reference.Accounting;

public class AcctListModel extends AbstractModel {
	public ArrayList<AcctModel> acctList;
	//private Vector<AcctModel> acctList;
	public String DOLLAR = "Dollar";
	public String YEN = "Yen";
	public String EURO = "Euro";
    private Vector<String> aname;
    private Vector<Integer> aid;
    private Vector<Double> abalance;
    
	public  AcctListModel(String fileName) throws FileNotFoundException, ParseException{
		this.acctList = new ArrayList<AcctModel>();
		//this.parseFile(fileName);
		this.aname= new Vector<String>();
		this.aid= new Vector<Integer>();
		this.abalance= new Vector<Double>();
		BufferedReader buffer;
		try {
            //Accounting accounting = new Accounting(filename);
            buffer = new BufferedReader(new FileReader(fileName));

            // Parse each line of the file
            int lineNumber = 0;
            String lineString = buffer.readLine();
            while(lineString != null) {
                // Temporary read holders
                String name;
                int id;
                double balance;
                
                String[] tokens = lineString.split(" ");
                if (tokens.length != 3) {
                    // Each lineString should contain only a name, id, and balance
                    throw new ParseException("A single line can only consist of a name, numeric ID, and balance.\nLine with error:\n" + lineString, lineNumber);
                }

                // Validate name
                name = tokens[0].trim();
                if (!name.matches("^[a-zA-Z]+$"))
                    throw new ParseException("Name can only contain letters.\nName with error: " + tokens[0], lineNumber);
                // Valid id
                if (!tokens[1].trim().matches("^[0-9]+$"))
                    throw new ParseException("ID can only contain digits.\nID with error: " + tokens[1], lineNumber);
                id = Integer.parseInt(tokens[1]);

                if (!tokens[2].matches("^[0-9.]+$"))
                    throw new ParseException("Balance can only be a positive real number.\nBalance with error: " + tokens[2], lineNumber);
                balance = Double.parseDouble(tokens[2]);

                //accounting.addAccount(new Account(name, id, balance));
                this.acctList.add(new AcctModel(name, id, balance));
                this.aname.addElement(name);
                aid.addElement(id);
                abalance.addElement(balance);
                // Get the next lineString for pocessing
                lineString = buffer.readLine();
                lineNumber++;
            }
            // Clean up
            buffer.close();
            
           // return acctList;
        } catch (NumberFormatException ex) {
            // Handle invalid ids and balances. This should never occur since we matched our numbers via regex
            ex.printStackTrace();
            //return null;
        } catch (IOException ex) {
            // Handle generic I/O errors
            System.out.println("I/O Error: " + ex.getMessage());
            //return null;
        } catch (ParseException ex){
        	//Handle ParseException
        	System.out.println("Parse Error: " + ex.getMessage());
        } 
	}

	public AcctModel addAcct(String aName, String aAcctID, int aAmount, String aCurrencty) {
		//return new AcctModel()
		throw new UnsupportedOperationException();
	}
	public ArrayList<AcctModel> getAccounts(){
		return acctList;
	}
	public int acctListSize(){
		return acctList.size();
	}
	public Vector getAcc(){
		Vector str = new Vector(acctList);
		System.out.println(acctList);
		System.out.println(acctList.toString());
		System.out.println(str.get(1));
		return str;
			
	}
	public Vector<String> getName(){
		return aname;
	}
	public Vector<Integer> getID(){
		return aid;
	}
	public Vector<Double> getBalance(){
		return abalance;
	}
	//public ArrayList<AcctModel> parseFile (String fileName) throws FileNotFoundException, ParseException{
	//File file =new File(fileName);
	
		
	/*	
    FileInputStream fstream = null;
    try
      {
    	fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine = "";
            String[] tokens = strLine.split(", ");
            //Read file line by line
            while ((strLine = br.readLine()) != null)   {
              // Copy the content into the array
              tokens = strLine.split(",");
              acctList.add(new AcctModel(tokens[0], tokens[1], tokens[2]));
            }
	            }
	            catch (IOException e) {
	              e.printStackTrace();
	            }
	            finally {
	                try { fstream.close(); } catch ( Exception ignore ) {}
	            }
	       
	return acctList;
	*/
		
    }
	
