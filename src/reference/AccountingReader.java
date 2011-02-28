package accountManager.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class AccountingReader {
    public static Accounting read(String filename) throws FileNotFoundException, ParseException {
        BufferedReader buffer;
        try {
            Accounting accounting = new Accounting(filename);
            buffer = new BufferedReader(new FileReader(filename));

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

                accounting.addAccount(new Account(name, id, balance));

                // Get the next lineString for processing
                lineString = buffer.readLine();
                lineNumber++;
            }
            // Clean up
            buffer.close();

            return accounting;
        } catch (NumberFormatException ex) {
            // Handle invalid ids and balances. This should never occur since we matched our numbers via regex
            ex.printStackTrace();
            return null;
        } catch (IOException ex) {
            // Handle generic I/O errors
            System.out.println("I/O Error: " + ex.getMessage());
            return null;
        }
    }
}
