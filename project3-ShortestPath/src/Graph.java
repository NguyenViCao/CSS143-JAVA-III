package Project3;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*<pre>
 * Interface    Graph.java
 * Description  Crucial abstract class in the triad of defining data structures
 *              in Java with interfaces, abstract classes and concrete classes.
 *              The Graph interface provides a number of method to be defined
 *              used by the UnweightedGraph and WeightedGraph class via the 
 *              AbstractGraph class which implements this interface.
 *              concrete classes.
 * Platform     jdk 1.8.0_241; NetBeans IDE 11.3; PC Windows 10
 * Course       CS 143
 * Hourse       1 hours and 9 minutes
 * Date         4/5/2021
 * History Log  7/18/2018, 5/7/2020
 * @author	<i>Nguyen Vi Cao</i>
 * @version 	%1% %2%
 * @param       <V> generic type
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public interface Graph<V> 
{
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getSize
     * Description  Return the number of vertices in the graph.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @return      size int
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getSize();

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getVertices
     * Description  Return the vertices in the graph as a list
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @return      list List
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    public java.util.List<V> getVertices();
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getVertices
     * Description  Return the object for the specified vertex index
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       index int
     * @return      vertex V
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/     
    public V getVertex(int index);

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getIndex
     * Description  Return the index for the specified vertex object
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       v V
     * @return      index int
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public int getIndex(V v);

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getNeighbors
     * Description  Return the neighbors of vertex with the specified index
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       index int
     * @return      list List
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public java.util.List<Integer> getNeighbors(int index);

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getDegree
     * Description  Return the degree for a specified vertex.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       v int
     * @return      degree int
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int getDegree(int v);

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       printEdges
     * Description  Print the edges.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void printEdges();

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       displayEdges
     * Description  Display the edges as String.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @return      output String
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String displayEdges();
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       clear
     * Description  Clear graph.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void clear();
 
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       addVertex
     * Description  Add a vertex to the graph.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       vertex V
     * @return      true/false boolean
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean addVertex(V vertex);

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       addEdge
     * Description  Add an edge to the graph.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       u int
     * @param       v int
     * @return      true/false boolean
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean addEdge(int u, int v);

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       dfs
     * Description  Obtain a depth-first search tree.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       v int
     * @return      tree Tree
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public AbstractGraph<V>.Tree dfs(int v);

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       bfs
     * Description  Obtain a breadth-first search tree.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       v int
     * @return      tree Tree
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public AbstractGraph<V>.Tree bfs(int v);
}
