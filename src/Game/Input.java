package Game;
import java.util.Scanner;

/**
 * Take an input from a user.
 *
 * @author Andrei Cruceru
 * @version 21112020
 */
public class Input
{
    private Scanner reader;

    /**
     * Create a new InputReader that reads text from the text terminal.
     */
    public Input()
    {
        reader = new Scanner(System.in);
    }

    /**
     * Read a line of text from standard input (the text terminal),
     * and return it as a String.
     *
     * @return  A String typed by the user.
     */
    public String getString()
    {
        String string ="";
        while(string.length() == 0)
        {
            
            System.out.print("> ");         // print prompt
            string = reader.nextLine();

            if(string.length() == 0)
                System.out.println("\n        Cannot leave blank!\n");
        }

        return string;
    }
    
    /**
     * @return any input.
     */
    public String getAny()
    { 
        String string = reader.nextLine();
        
        return string;
    }
      
    /**
     * Read a line from standard input (the text terminal),
     * and return it as a Integer.
     *
     * @return  A Integer typed by the user. 
     */
    public int getInteger()
    {
        int number = 0;
        boolean valid = false;

        while(!valid)
        {
            String string = getString();
            try
            {
                number = Integer.parseInt(string);
                valid = true;
            }
            catch(Exception e)
            {
                System.out.println("\n        Not a valid number!\n");
            }
        }
        
        return number;
    }
    
}
