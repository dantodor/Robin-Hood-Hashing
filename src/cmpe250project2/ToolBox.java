/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cmpe250project2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;

/**
 * General Toolbox class that contains several useful operations.
 * @author Seckin Savasci - 2008400078
 * @author Bogazici University CmpE - Cmpe250
 */
public class ToolBox
{
    /**
     * ToolBox contains all static methods, so no need to create an instance.
     * To avoid object creation constructor is set private.
     */
    private ToolBox()
    {
        // I don't want you to create an instance
    }
    /**
     * Method that checks if the given number is a prime
     * @param n integer to be tested
     * @return true if number is a prime number, otherwise false
     */
    public static boolean isPrime(int n)
    {
        boolean prime = true;
	for (int i = 3; i <= Math.sqrt(n); i += 2)
            if (n % i == 0)
            {
		prime = false;
		break;
            }
        if (( n%2 !=0 && prime && n > 2) || n == 2)
            return true;
	else return false;
    }
    /**
     * Method that finds and returns the previous prime number
     * @param n integer value that closest previous prime will be found
     * @return the value of the closest prime less than n
     */
    public static int previousPrime(int n)
    {
        n--;
        while(!isPrime(n))
            n--;
        return n;
    }
    public static int hash1(int value,int tableSize)
    {
        return value%tableSize;
    }
    public static int hash2(int value,int tableSize)
    {
        return previousPrime(tableSize) - (value%previousPrime(tableSize));
    }
    /**
     * Method that checks if the input file is suitable for hashing, as checking
     * the given table size and given number of nodes. You have to call this
     * method before creating hash table. It informs user via GUI.
     * @param fileName name of the file for valiadating
     * @return true if file is good for hashing, otherwise false
     */
    public static boolean validateForHashing(String fileName)
    {
        try
        {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader input = new BufferedReader(new InputStreamReader(fstream));


            String line = input.readLine();
            int counter = 0;
            int definedSize = Integer.parseInt(line);
            line = input.readLine();
            while(line!=null)
            {
                counter++;
                line = input.readLine();
            }
            String message;
            boolean status;
            if(definedSize>=counter)
            {
                message = "File Validation Completed Succesfully!\n"+
                    "Table size : "+definedSize+"\n"+
                    "Nodes to be inserted : "+counter+"\n";
                status = true;
            }
            else
            {
                message = "File Validation Fails!\n"+
                    "Table size is not enough! Please increase the table size"+
                    "as editing the first line of the input file.\n"+
                    "Minumum needed table size : "+counter+"\n";
                status = false;
            }
            input.close();
            JOptionPane.showMessageDialog(null,message,"Robin Hood Hashing by Seckin Savasci",3);
            return status;

        }
        catch (FileNotFoundException ioexc)
        {
            System.out.println(ioexc.getMessage());
        }
        catch (IOException ioexc)
        {
            System.out.println(ioexc.getMessage());
        }
        return false;
    }



}
