/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cmpe250project2;


/**
 * main method for testing purposes
 * @author Seckin Savasci - 2008400078
 * @author Bogazici University CmpE - Cmpe250
 */
public class Main {

  
    public static void main(String[] args)
    {
        HashTable hashTable;
       if(ToolBox.validateForHashing("input.txt"))
               hashTable = new HashTable("input.txt");
    }

}
