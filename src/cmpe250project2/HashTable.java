/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cmpe250project2;
import java.io.*;
import javax.swing.JOptionPane;
/**
 * Table components of the Robin Hood hashing
 * @author Seckin Savasci - 2008400078
 * @author Bogazici University CmpE - Cmpe250
 */
public class HashTable
{   /**
     * Array that stores the nodes
     */
    private  Node[] table;
    /**
     * Size of the hash table
     */
    private int size;
    /**
     * Number of inserted elements
     */
    private int inserted=0;
    /**
     * Constructor that creates the container array with given size
     * @param given size of the array to be created
     */
    public HashTable(int given)
    {
        table = new Node[given];
        size = given;
    }
    /**
     * Constructor that reads from given file. The file format is : First line
     * indicates the hash table size, and each other line contains the node
     * values. So every line must have only 1 integer value, or any value that
     * can parsable using Integer.parseInt(String) method.
     * @param fileName name of the file for constructing hash table
     */
    public HashTable(String fileName)
    {
        FileOutputStream Output;
        PrintStream OrgOut = System.out;
        PrintStream file;

        try
        {
            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader input = new BufferedReader(new InputStreamReader(fstream));
            Output = new FileOutputStream("output.txt");
            file = new PrintStream(Output);
            System.setOut(file); 
            String line = input.readLine();
            int counter = 0;
            if(line!=null)
            {   int given=Integer.parseInt(line);
                table=new Node[given];
                size=given;
            }
            line = input.readLine();
            while(line !=null)
            {
                Node read = new Node();
                read.setValue(Integer.parseInt(line));
                insert(read);
                counter++;
                line = input.readLine();
            }
            System.setOut(OrgOut);
            file.close();
            String Message = counter+" node(s) inserted into Hash table : "+this.toString()+"\n"
                    +"Check output.txt for detailed log\n";
            JOptionPane.showMessageDialog(null,Message,"Robin Hood Hashing by Seckin Savasci",3);
            System.out.printf("%d node(s) inserted into Hash table : %s\n",counter,this.toString());
        }
        catch (FileNotFoundException ioexc)
        {
            System.out.println(ioexc.getMessage());
        }
        catch (IOException ioexc)
        {
            System.out.println(ioexc.getMessage());
        }

    }

    /**
     * Method for inserting nodes into hash table, this method checks the
     * insertion status, and can call another insert(x) instance if needed.
     * So stack overflow can occur by misuse of this method.
     * @param x node to be inserted in hash table
     * @return true if insertion is successful, otherwise false
     */
    private final boolean insert(Node x)
    {
        int index;
        if(inserted==size)
        {
            System.out.println("Table is full!");
            return false;
        }
        index = x.getTargetLocation(size);
        if(table[index]==null)
        {
            table[index]=x;
            inserted++;
            System.out.printf("Node %d Inserted @ room %d - collision count for this node is %d\r\n",x.getValue(),index,x.getCollisionCount());
            return true;
        }
        else if(table[index].getCollisionCount()<x.getCollisionCount())
        {
            table[index].collisionIncrement();
            x.collisionIncrement();
            System.out.printf("Node %d collided with node %d while inserting - " +
                    " collision count for the former node is %d\r\nReplacing node %d " +
                    "- collision count for this node is %d\r\n",
                    x.getValue(),table[index].getValue(),
                    x.getCollisionCount(),table[index].getValue(),
                    table[index].getCollisionCount());
            Node previous;
            previous=table[index];
            table[index]=x;
            System.out.printf("Node %d Inserted @ room %d - collision count for this node is %d\r\n",x.getValue(),index,x.getCollisionCount());

            return insert(previous);
        }
        else if(table[index].getCollisionCount()>=x.getCollisionCount())
        {
            x.collisionIncrement();
            System.out.printf("Node %d collided with node %d while inserting - " +
                    " collision count for the former node is %d\r\n",
                    x.getValue(),table[index].getValue(),
                    x.getCollisionCount());
            return insert(x);
        }
        return false;
        
    }
    /**
     * Printing method to standard output,prints each node's value in every room
     * to standard output, prints "empty" if the room is filled with any node.
     * Not in final mode, testing purposes only
     */
    public void printTable()
    {
        for(int i=0;i<table.length;i++)
        {
            if(table[i]==null)
                System.out.println("empty");
            else System.out.println(table[i].getValue());
        }
    }
}
