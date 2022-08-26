package StateCapitalsQuiz;

import java.util.Objects;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*<pre>
* Class         BinarySearchTree
* File          BinarySearchTree.java
* Description   A definition for BinarySearchTree class with multitude of methods on 
                operations on trees.
* @author       <i>Nguyen Vi Cao</i>
* Environment   PC, Windows 10, jdk1.8.0_241, NetBeans 11.3
* Date          4/26/2021
* @version      1.0.0
*</pre>
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class BinarySearchTree 
{
    private BinarySearchTreeNode root;
    StringBuilder printBuffer = new StringBuilder("");
    StringBuilder saveBuffer = new StringBuilder("");
    StringBuilder buffer = new StringBuilder("");
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    * Constructor      Tree()-- default constructor
    * Description      Construct an empty Tree of integers
    * @author          <i>Nguyen Vi Cao</i>
    * Date             4/26/2021
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTree()
    {
        root = null;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          insertNode()
    *	Description     Insert a new node in the binary search tree. If the root
    *                   node is full, create the root node here. Otherwise, call
    *                   the insert method of class TreeNode.
    *   @param          player Player
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void insertNode (Player player)
    {
        if (root == null)
            root = new BinarySearchTreeNode(player);
        else
            root.insert(player);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          remove()
    *	Description     Remove from the tree. Nothing is done if x is not found.
    *   @param          player Player to be removed
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void remove (Player player)
    {
        root = remove (player, root);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          remove()
    *	Description     Recursive internal method to remove a node from a subtree.
    *   @param          player Player the item to remove
    *   @param          node BinarySearchTreeNode the mode that roots the tree.
    *   @return         node BinarySearchTreeNode -- the new root
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode remove (Player player, BinarySearchTreeNode node)
    {
        if (node == null)
            return node;                            //Item not found: do nothing
        if (player.compareTo(node.data) < 0)
            node.left = remove(player, node.left);
        else if (player.compareTo(node.data) > 0)
            node.right = remove(player, node.right);
        else if (node.left != null && node.right != null)   //two children
        {
            node.data = findMin(node.right).data;
            node.right = remove(node.data, node.right);
        }
        else
            node = (node.left != null) ? node.left : node.right;
        return node;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          getRoot()
    *	Description     Getter method to return the root of the tree
    *   @return         root BinarySearchTreeNode
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode getRoot()
    {
        return root;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          removeAll()
    *	Description     a method to remove all nodes of the BinarySearchTree
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void removeAll()
    {
        root = null;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          findMin()
    *	Description     calls overloaded findMin() with root's data passed to it.
    *   @return         player Player -- with minimum name
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player findMin()
    {
        return findMin(root).data;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          findMin()
    *	Description     Recursive method to find the smallest item in a subtree
    *   @param          node TreeNode
    *   @return         node containing the smallest item
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode findMin(BinarySearchTreeNode node)
    {
        if (node == null)
            return null;
        else if (node.left == null)
            return node;
        return findMin (node.left);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          findMax()
    *	Description     Calls overloaded findMax() with root's data passed to it
    *   @return         player Player -- with minimum name
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player findMax()
    {
        return findMax(root).data;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          findMax()
    *	Description     Recursive method to find the largest item in a subtree
    *   @param          node TreeNode
    *   @return         node containing the largest item
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode findMax (BinarySearchTreeNode node)
    {
        if ( node != null)
        {
            while ( node.right != null)
                node = node.right;
        }
        return node;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          elementAt()
    *	Description     Internal method to get element field.
    *   @param          node BinarySearchTreeNode
    *   @return         node BinarySearchTreeNode
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Player elementAt (BinarySearchTreeNode node)
    {
        return (node == null ? null : node.data);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          nodeWith()
    *	Description     a method to return the player (data) given the node.
    *                   overloaded compareTo method in the Player's class for 
    *                   partial substring match
    *   @param          data Player
    *   @param          node BinarySearchTreeNode
    *   @return         node BinarySearchTreeNode
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode nodeWith(Player data, BinarySearchTreeNode node)
    {
        if(node == null)
            return null;
        else
        {
            if(data.compareTo(node.data, true) == 0)
                return node;
            else
                if(data.compareTo(node.data, true) < 0)
                    return nodeWith(data, node.left);
                else
                    return nodeWith(data, node.right);
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          nodeWith()
    *	Description     an overloaded method to return the player (data) given the 
    *                   player's name as a String, must be an exact match
    *   @param          playerName String
    *   @param          node BinarySearchTreeNode
    *   @return         node BinarySearchTreeNode
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode nodeWith(String playerName, BinarySearchTreeNode node)
    {
        if(node == null)
            return null;
        else
            if(playerName.compareToIgnoreCase(node.data.getName()) == 0)
                return node;
            else
                if(playerName.compareToIgnoreCase(node.data.getName()) < 0)
                    return nodeWith(playerName, node.left);
                else
                    return nodeWith(playerName, node.right);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          contains()
    *	Description     a boolean method to determine if a player is in the BST
    *   @param          player Player
    *   @return         true if player is in tree; false otherwise
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean contains(Player player)
    {
        if(nodeWith(player, root) == null)
            return false;
        else
        {
            BinarySearchTreeNode foundPlayer = nodeWith(player, root);
            return (foundPlayer.data).equals(player);
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          next()
    *	Description     an unfinished method to return the next player according
    *                   to the sorting of the BST given an player
    *   @param          player Player
    *   @return         player Player -- the next player.
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player next(Player player)
    {
        BinarySearchTreeNode nextNode = nodeWith(player, root);
        if(nextNode.left != null && nextNode.right != null) //two children
            return findMin(nextNode.right).data;
        else 
            return null;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          previous()
    *	Description     an unfinished method to return the next player according
    *                   to the sorting of the BST given an player
    *   @param          player Player
    *   @return         player Player -- the next player.
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player previous(Player player)
    {
        BinarySearchTreeNode nextNode = nodeWith(player, root);
        if(nextNode.left != null && nextNode.right != null) //two children
            return findMax(nextNode.left).data;
        else
            return null;        //unfinished
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          preorderTraversal()
    *	Description     Display data of nodes in preorder: Node, Left, Right.
    *                   Calls recursive preorderHelper method to do the real 
    *                   display
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void preorderTraversal()
    {
        preorderHelper(root);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          preorderHelper()
    *	Description     Display data of nodes in preorder: Node, Left, Right.
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void preorderHelper(BinarySearchTreeNode node)
    {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preorderHelper(node.left);
        preorderHelper(node.right);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          inorderTraversal()
    *	Description     Display data of nodes in preorder: Node, Left, Right.
    *                   Calls recursive inorderHelper method to do the real display
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void inorderTraversal()
    {
        inorderHelper(root);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          inorderHelper()
    *	Description     Display data of nodes in preorder: Node, Left, Right.
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void inorderHelper(BinarySearchTreeNode node)
    {
        if (node == null)
            return;
        inorderHelper(node.left);
        System.out.print(node.data + " ");
        inorderHelper(node.right);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          postorderTraversal()
    *	Description     Display data of nodes in preorder: Node, Left, Right.
    *                   Calls recursive postorderHelper method to do the real display
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void postorderTraversal()
    {
        postorderHelper(root);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          postorderTraversal()
    *	Description     Display data of nodes in preorder: Node, Left, Right.
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void postorderHelper(BinarySearchTreeNode node)
    {
        if (node == null)
            return;
        postorderHelper(node.left);
        postorderHelper(node.right);
        System.out.print(node.data + " ");
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          toString()
    *	Description     outputs the tree contents in sorted order as a string
    *                   (for output). Calls printTree for inorder traversal.
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public String toString()
    {
        if (isEmpty() )
            return("");
        else 
            return(printTree(root));
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          printTree()
    *	Description     outputs the tree contents in preorder
    *   @param          node BinarySearchTreeNode -- the node that roots the tree
    *   @return         tree String
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String printTree (BinarySearchTreeNode node)
    {
        if (node != null)
        {
            printTree(node.left);
            printBuffer.append(node.data.toString() + '\n');
            printTree(node.right);
        }
        return printBuffer.toString();
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          buildBuffer()
    *	Description     build a StringBuilder that contains all data in preorder
    *                   traversal.
    *   @param          node BinarySearchTreeNode -- the node that roots the tree
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void buildBuffer(BinarySearchTreeNode node)
    {
        if(node != null)
        {
            buffer.append(node.data.toString() + '\n');
            buildBuffer(node.left);
            buildBuffer(node.right);
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          getBuffer()
    *	Description     return buffer.
    *   @return         buffer String
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String getBuffer()
    {
        return  buffer.toString();      //printBuffer.toString();
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       setBuffer()
     * Description  Set buffer.
     * @param       buffer StringBuilder
     * @author      <i>Nguyen VI Cao</i>
     * Date         4/26/2021
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setBuffer(StringBuilder buffer)
    {
        this.buffer = buffer;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          isEmpty()
    *	Description     Test if the tree is logically empty.
    *   @return         true or false boolean
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean isEmpty()
    {
        return root == null;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          size()
    *	Description     Find number of nodes in a tree. Calls sizeHelper for the
    *                   real work.
    *   @return         size int
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int size()
    {
        return sizeHelper(root);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          sizeHelper()
    *	Description     find number of nodes in a tree recursively
    *   @return         size int
    *   @param          node TreeNode
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int sizeHelper(BinarySearchTreeNode node)
    {
        if (node == null)
            return 0;
        else
            return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          countLeaves()
    *	Description     Find number of leaves in a tree. Calls recursive method 
    *                   countLeavesHelper for the real work
    *   @return         count int
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int countLeaves()
    {
        return countLeavesHelper(root);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          countLeavesHelper()
    *	Description     Return the number of leaves in the tree to which node 
    *                   points.
    *   @param          node TreeNode
    *   @return         count int
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int countLeavesHelper(BinarySearchTreeNode node)
    {
        if (node == null)
            return 0;
        else if (node.left == null && node.right == null)
            return 1;
        else
            return countLeavesHelper(node.left) + countLeavesHelper(node.right);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          height()
    *	Description     The height of a node is the number of edges on the longest
    *                   path from the node to a leaf. A leaf node will have a
    *                   height of 0. Calls recursive heighthHelper for a real work
    *   @return         height int
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int height()
    {
        return heighthHelper(root);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *<pre>
    *	Method          heighthHelper()
    *	Description     The height of a node is the number of edges on the longest
    *                   path from the node to a leaf. A leaf node will have a
    *                   height of 0.
    *   @param          node TreeNode
    *   @return         count int
    *	Date            4/26/2021
    *	@author         <i>Nguyen Vi Cao</i>	
    *</pre>
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int heighthHelper(BinarySearchTreeNode node)
    {
        if (node == null)
            return 0;
        else
        {
            return 1 + Math.max(heighthHelper(node.left), 
                    heighthHelper(node.right));
        }
//        int left = heighthHelper(node.left);
//        int right = heighthHelper(node.right);
//        int x = (left>right) ? left + 1: right + 1;
//        return x;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.root);
        hash = 67 * hash + Objects.hashCode(this.printBuffer);
        hash = 67 * hash + Objects.hashCode(this.saveBuffer);
        hash = 67 * hash + Objects.hashCode(this.buffer);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BinarySearchTree other = (BinarySearchTree) obj;
        return true;
    }
}
