package cmpe250project2;

/**
 * Implementation of nodes which is used in Robin Hood Hashing algorithm
 * @author Seckin Savasci - 2008400078
 * @author Bogazici University CmpE - Cmpe250
 */
public class Node
{   /**
     * intial value of the newly created node
     */
    private static int INITIAL_VALUE = -1;
    /**
     * Value stored in a node
     */
    private int value;
    /**
     * The number of probes done for placement of an element
     */
    private int probeCount;
    /**
     * The status that shows either the node is still being used or deleted
     */
    private boolean isActive;
    /**
     * Number of collisions occured for this node
     */
    private int collisionCount;
    /**
     * Default Constructor that initializes the fields to default values
     * @see #value
     * @see #isActive
     * @see #probeCount
     */
    public Node()
    {
        value = INITIAL_VALUE;
        isActive = false;
        probeCount = 0;
        collisionCount=0;
    }
    /**
     * Returns the number of collision
     * @return number of collision for this node
     * @see #collisionCount
     * @see #collisionIncrement()
     */
    public int getCollisionCount()
    {
        return collisionCount;
    }
    /**
     * Increments the collisionCount by 1 , that method must be called after
     * each collision
     * @see #collisionCount
     * @see #getCollisionCount()
     */
    public void collisionIncrement()
    {
        collisionCount++;
    }
    /**
     * Sets the value of the node 
     * @param x value that will be stored in the node
     * @see #getValue() 
     */
    public final void setValue(int x)
    {
        value=x;
    }
    /**
     * Gets the value of the node
     * @return value stored in the node
     * @see #setValue(int)
     */
    public final int getValue()
    {
        return value;
    }
    /**
     * Activates the node - States that node is reachable in the hash table
     * @see #isActive
     * @see #deactivate()
     */
    public final void activate()
    {
        isActive=true;
    }
    /**
     * Deactivates the node - States that node is not reachable in the hash table
     * @see #isActive
     * @see #activate()
     */
    public final void deactivate()
    {
        isActive=false;
    }
    /**
     * Method for getting number of probes that already done
     * @return the number of probes done
     */
    public final int getProbeCount()
    {
        return probeCount;
    }
    /**
     * Method that increments the probe count of the instance by 1. This method
     * is the signature for each probing done.
     */
    private final void probed()
    {
        probeCount++;
    }
    /**
     * The method that returns the position to be probed. For Robin Hood hashing,
     * we use double hashing method, where "hash1(x)=x mod tableSize" and
     * "hash2(x)= R - (x mod R)". R is the closest prime less than tableSize.
     * For each call return value changes due to change in collisionCount value.
     * @param tableSize size of the hash table
     * @return place to be probed in table
     * @throws InitialNodeHashingException
     *
     */
    public final int getTargetLocation(int tableSize)
    {  if(value==INITIAL_VALUE)
       {
           throw new InitialNodeHashingException();
       }
       int hashCode = ToolBox.hash1(value, tableSize)+collisionCount*ToolBox.hash2(value,tableSize);
       probed();
       return hashCode%tableSize;
    }

}
