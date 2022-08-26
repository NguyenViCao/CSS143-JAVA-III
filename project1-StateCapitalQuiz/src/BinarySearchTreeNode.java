
package StateCapitalsQuiz;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*<pre>
 Class         BinarySearchTreeNode
 File          BinarySearchTreeNode.java
 Description   A self-referential class representing a binary tree node.
* @author       <i>Nguyen Vi Cao</i>
* Environment   PC, Windows 10, jdk1.8.0_241, NetBeans 11.3
* Date          2/26/2021
* @version      1.0.0
*</pre>
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
class BinarySearchTreeNode 
{
    //package access members
    BinarySearchTreeNode left;      //left node
    Player data;           //data item
    BinarySearchTreeNode right;     //right node
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Constructor      TreeNode()-- constructor
    * Description      Initialize data to d and make this a leaf node.
    * @param           d int
    * @author          <i>Nguyen Vi Cao</i>
    * Date             2/26/2021
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode (Player artist)
    {
        data = artist;
        left = right = null; //this node has no children
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Method          insert()
    * Description     Recursive method to insert a BinarySearchTreeNode into a 
                      Tree that contains nodes.
    * Date            4/26/2021
    * @author         <i>Nguyen Vi Cao</i>	
    * @param          artist Artist
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public synchronized void insert (Player artist)
    {
        if (artist.compareTo(data) < 0)
        {
            if (left == null)
                left = new BinarySearchTreeNode(artist);
            else
                left.insert(artist);
        }
        else
            if (artist.compareTo(data) >= 0)
            {
                if (right == null)
                    right = new BinarySearchTreeNode(artist);
                else 
                    right.insert(artist);
            }
    }
}

