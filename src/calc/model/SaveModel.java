package calc.model;




import java.io.*;
import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: elixir
 * Date: 2/11/11
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class SaveModel {

    public static boolean write(AcctListModel accounting) {
        return write(accounting, null);
    }
    
    public static boolean write(AcctListModel accounting, String filename) {
        BufferedWriter buffer = null;
        try {
            if (filename == null)
            	
                filename = accounting.getFilename();
            
            buffer = new BufferedWriter(new FileWriter(filename));

            // Write each account to the file
            for (AcctModel account : accounting.getAccounts()) {
                synchronized (account) {
                    buffer.write(account.getName() + " " + String.valueOf(account.getAcctNumber()) + " " + String.valueOf(account.getBalance()));
                }
                buffer.newLine();
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (buffer != null) {
                    buffer.flush();
                    buffer.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
