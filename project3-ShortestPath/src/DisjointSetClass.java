package Project3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*<pre>
* Class        DisjointSetClass.java
* Description  a class used in Kruskal's algorithm for creating a MST
* Platform     jdk 1.8.0_241; NetBeans IDE 11.3; PC Windows 10
* Course       CS 143
* Date         5/28/2021
* @author      <i>Nguyen Vi Cao</i>
* @version     %1% %2%
* @see         javax.swing.JFrame
* @see         java.awt.Toolkit 
*</pre>
*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class DisjointSetClass 
{
    //class instance variables
    private static double totalWeight = 0;
    private Map<Integer, Integer> parent = new HashMap<>();
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getTotalWeight
     * Description  getting method to return the total weight of the MST
     * @author      <i>Nguyen Vi Cao</i>
     * Date         5/28/2021
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static double getTotalWeight()
    {
        return totalWeight;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       makeSet
     * Description  creates a new set consisting of the vertices.
     * @author      <i>Nguyen Vi Cao</i>
     * Date         5/28/2021
     * @param       numberOfVertices int
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void makeSet(int numberOfVertices)
    {
        //create numberOFVertices disjoint sets (one for each vertex)
        for (int i = 0; i < numberOfVertices; i++)
        {
            parent.put(i, i);
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       find
     * Description  recursive method to find the root of the set in which 
     *              element k belongs
     * @author      <i>Nguyen Vi Cao</i>
     * Date         5/28/2021
     * @param       numberOfVertices int
     * @return      root k int
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private int find(int k)
    {
        //if k is root
        if (parent.get(k) == k)
        {
            return k;
        }
        //recurse for the parent until we find the root
        return find(parent.get(k));
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       union
     * Description  perform union of two subsets
     * @author      <i>Nguyen Vi Cao</i>
     * Date         5/28/2021
     * @param       numberOfVertices int
     * @return      root k int
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void union(int a, int b)
    {
        //find the root of the sets in which elements x and y belongs
        int u = find(a);
        int v = find(b);
        parent.put(u, v);
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       kruskalAlgorithm
     * Description  method to construct MST using Kruskal's algorithm.
     * @author      <i>Nguyen Vi Cao</i>
     * Date         5/28/2021
     * @param       edges int
     * @param       numberOfVertices int
     * @return      mst ArrayList
    *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static List<WeightedEdge> kruskalAlgorithm(List<WeightedEdge> edges,
            int numberOfVertices)
    {
        //stores the edges present in MST
        List<WeightedEdge> mst = new ArrayList();
        
        //Initialize DisjointSetClass
        //create a singleton set for each element of the universe
        DisjointSetClass ds = new DisjointSetClass();
        ds.makeSet(numberOfVertices);
        
        int index = 0;
        
        //sort edges by increasing weight
        Collections.sort(edges, Comparator.comparingDouble(e -> e.weight));
        
        //MST contains exactly V - 1 edges
        while (mst.size() != numberOfVertices - 1)
        {
            //consider the next edges with minimum weight from the graph
            WeightedEdge next_edge = edges.get(index++);
            
            //find the root of the sets to which two endpoints
            //vertices of the next edge belongs
            int u = ds.find(next_edge.u);
            int v = ds.find(next_edge.v);
            
            //if both endpoints have different parents, they belong to 
            //different connected components and can be included in MST
            if (u != v)
            {
                mst.add(next_edge);
                ds.union(u, v);
                totalWeight += next_edge.weight;
            }
        }
        return mst;
    }
}
