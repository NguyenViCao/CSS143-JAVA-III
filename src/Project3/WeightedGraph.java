package Project3;
import java.util.*;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*<pre>
 * Class        WeightedGraph.java
 * Description  Concrete class in the triad of defining data structures in
 *              Java with interfaces, abstract classes and concrete classes.
 *              It extends the abstract AbstractGraph class. It contains
 *              definition of inner class MST, minimum spanning tree.
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
public class WeightedGraph<V> extends AbstractGraph<V> 
{
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  UnweightedGraph()-default constructor
     * Description  Construct an empty weighted graph
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public WeightedGraph() 
    {
    }

    /** Construct a WeightedGraph from vertices and edged in arrays */
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  WeightedGraph()-overloaded constructor
     * Description  Construct a WeightedGraph from vertices and edges in arrays.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       vertices V[]
     * @param       edges int[][]
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/     
    public WeightedGraph(V[] vertices, int[][] edges) {
      createWeightedGraph(java.util.Arrays.asList(vertices), edges);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  WeightedGraph()-overloaded constructor
     * Description  Construct a WeightedGraph from vertices and edges in list.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       edges int[][]
     * @param       numberOfVertices int
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/     
    public WeightedGraph(int[][] edges, int numberOfVertices) {
        List<V> vertices = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++)
            vertices.add((V)(new Integer(i)));

        createWeightedGraph(vertices, edges);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  WeightedGraph()-overloaded constructor
     * Description  Construct a WeightedGraph for vertices 0, 1, 2 and edge list.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       vertices List
     * @param       edges List
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    public WeightedGraph(List<V> vertices, List<WeightedEdge> edges) {
        createWeightedGraph(vertices, edges);
    }

    /**  */
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor  WeightedGraph()-overloaded constructor
     * Description  Construct a WeightedGraph from vertices 0, 1, and edge array.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       edges List
     * @param       numberOfVertices int
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/     
    public WeightedGraph(List<WeightedEdge> edges, int numberOfVertices) {
        List<V> vertices = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++)
            vertices.add((V)(new Integer(i)));

        createWeightedGraph(vertices, edges);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       createWeightedGraph
     * Description  Create adjacency lists from edge arrays.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       vertices List<V>
     * @param       edges int[][]
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    private void createWeightedGraph(List<V> vertices, int[][] edges) {
        this.vertices = vertices;     

        for (int i = 0; i < vertices.size(); i++) {
            neighbors.add(new ArrayList<Edge>()); // Create a list for vertices
        }

        for (int i = 0; i < edges.length; i++) {
            neighbors.get(edges[i][0]).add(
                new WeightedEdge(edges[i][0], edges[i][1], edges[i][2]));
        }
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       createWeightedGraph
     * Description  Create adjacency lists from edge lists.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       vertices List<V>
     * @param       edges List<WeightedEdge>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    private void createWeightedGraph(List<V> vertices, List<WeightedEdge> edges) 
    {
        this.vertices = vertices;     

        for (int i = 0; i < vertices.size(); i++) {
            neighbors.add(new ArrayList<Edge>()); // Create a list for vertices
        }

        for (WeightedEdge edge: edges) {      
            neighbors.get(edge.u).add(edge); // Add an edge into the list
        }
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getWeight
     * Description  Return the weight on the edge (u, v).
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @throws      Exception
     * @param       u int
     * @param       v int
     * @return      weight double
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    public double getWeight(int u, int v) throws Exception {
        for (Edge edge : neighbors.get(u)) {
            if (edge.v == v) {
              return ((WeightedEdge)edge).weight;
            }
        }

      throw new Exception("Edge does not exit");
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       printWeightedEdges
     * Description  Display edges with weights.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void printWeightedEdges() 
    {
        for (int i = 0; i < getSize(); i++) 
        {
            System.out.print(getVertex(i) + " (" + i + "): ");
            for (Edge edge : neighbors.get(i)) 
            {
                System.out.print("(" + edge.u + ", " + edge.v + ", " + 
                    ((WeightedEdge)edge).weight + ") ");
            }
            System.out.println();
        }
    }
 /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       displayWeightedEdges
     * Description  Display edges with weights and return a StringBuffer.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @return      
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public StringBuffer displayWeightedEdges() 
    {
        StringBuffer out = new StringBuffer();
        for (int i = 0; i < getSize(); i++) {
            out.append(getVertex(i) + " (" + i + "): ");
            for (Edge edge : neighbors.get(i)) {
                out.append("(" + edge.u + ", " + edge.v + ", " + 
                    ((WeightedEdge)edge).weight + ") ");
            }
            out.append("\n");
        }
        return out;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       addEdge
     * Description  Add edges to the weighted graph.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       u int
     * @param       v int
     * @param       weight double
     * @return      true/false boolean
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    public boolean addEdge(int u, int v, double weight) {
        return addEdge(new WeightedEdge(u, v, weight));
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getMinimumSpanningTree
     * Description  Get a minimum spanning tree rooted at vertex 0.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @return      minimum spanning tree MST
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    public MST getMinimumSpanningTree() {
        return getMinimumSpanningTree(0);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getMinimumSpanningTree
     * Description  Get a minimum spanning tree rooted at a specified vertex.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     * @param       startingVertex int
     * @return      minimum spanning tree MST
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    public MST getMinimumSpanningTree(int startingVertex) {
        // cost[v] stores the cost by adding v to the tree
        double[] cost = new double[getSize()];
        for (int i = 0; i < cost.length; i++) {
            cost[i] = Double.POSITIVE_INFINITY; // Initial cost 
        }
        cost[startingVertex] = 0; // Cost of source is 0

        int[] parent = new int[getSize()]; // Parent of a vertex
        parent[startingVertex] = -1; // startingVertex is the root
        double totalWeight = 0; // Total weight of the tree thus far

        List<Integer> T = new ArrayList<Integer>();

        // Expand T
        while (T.size() < getSize()) {
            // Find smallest cost v in V - T 
            int u = -1; // Vertex to be determined
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i = 0; i < getSize(); i++) {
                if (!T.contains(i) && cost[i] < currentMinCost) {
                    currentMinCost = cost[i];
                    u = i;
                }
            }

            T.add(u); // Add a new vertex to T
            totalWeight += cost[u]; // Add cost[u] to the tree

            // Adjust cost[v] for v that is adjacent to u and v in V - T
            for (Edge e : neighbors.get(u)) {
                if (!T.contains(e.v) && cost[e.v] > ((WeightedEdge)e).weight) {
                    cost[e.v] = ((WeightedEdge)e).weight;
                    parent[e.v] = u; 
                }
            }
        } // End of while

        return new MST(startingVertex, parent, T, totalWeight);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Class        MST
     * Description  MST is an inner class in WeightedGraph.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/     
    public class MST extends Tree {
        private double totalWeight; // Total weight of all edges in the tree

        public MST(int root, int[] parent, List<Integer> searchOrder,
            double totalWeight) {
            super(root, parent, searchOrder);
            this.totalWeight = totalWeight;
        }

        public double getTotalWeight() {
            return totalWeight;
        }
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       ShortestPathTree
     * Description  Find single source shortest paths.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @param       sourceVertex int
     * @return      shortest path ShortestPathTree
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public ShortestPathTree getShortestPath(int sourceVertex) {
        // cost[v] stores the cost of the path from v to the source
        double[] cost = new double[getSize()];
        for (int i = 0; i < cost.length; i++) {
          cost[i] = Double.POSITIVE_INFINITY; // Initial cost set to infinity
        }
        cost[sourceVertex] = 0; // Cost of source is 0

        // parent[v] stores the previous vertex of v in the path
        int[] parent = new int[getSize()];
        parent[sourceVertex] = -1; // The parent of source is set to -1

        // T stores the vertices whose path found so far
        List<Integer> T = new ArrayList<>();

        // Expand T
        while (T.size() < getSize()) {
            // Find smallest cost v in V - T 
            int u = -1; // Vertex to be determined
            double currentMinCost = Double.POSITIVE_INFINITY;
            for (int i = 0; i < getSize(); i++) {
                if (!T.contains(i) && cost[i] < currentMinCost) {
                    currentMinCost = cost[i];
                    u = i;
                }
            }

            T.add(u); // Add a new vertex to T

            // Adjust cost[v] for v that is adjacent to u and v in V - T
            for (Edge e : neighbors.get(u)) {
                if (!T.contains(e.v) 
                    && cost[e.v] > cost[u] + ((WeightedEdge)e).weight) {
                    cost[e.v] = cost[u] + ((WeightedEdge)e).weight;
                    parent[e.v] = u; 
                }
            }
      } // End of while

      // Create a ShortestPathTree
      return new ShortestPathTree(sourceVertex, parent, T, cost);
    }

    /**  */
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Class        ShortestPathTreeShortestPathTree
     * Description  ShortestPathTree is an inner class in WeightedGraph.
     * Date         4/5/2021
     * History Log  7/18/2018, 5/7/2020
     * @author      <i>Nguyen Vi Cao</i>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/  
    public class ShortestPathTree extends Tree {
        private double[] cost; // cost[v] is the cost from v to source

        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         *<pre>
         * Constructor  ShortestPathTree()-constructor
         * Description  Construct a path
         * Date         4/5/2021
         * History Log  7/18/2018, 5/7/2020
         * @author      <i>Nguyen Vi Cao</i>
         * @param       source int
         * @param       parent int[]
         * @param       searchOrder List
         * @param       cost double[]
         *</pre>
        *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
        public ShortestPathTree(int source, int[] parent, 
            List<Integer> searchOrder, double[] cost) {
            super(source, parent, searchOrder);
            this.cost = cost;
        }

        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *<pre>
        * Method       getCost
        * Description  Return the cost for a path from the root to vertex v.
        * Date         4/5/2021
        * History Log  7/18/2018, 5/7/2020
        * @author      <i>Nguyen Vi Cao</i>
        * @param       v int
        * @return      cost double
        *</pre>
       *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
        public double getCost(int v) {
          return cost[v];
        }

        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        *<pre>
        * Method       printAllPaths
        * Description  Print paths from all vertices to the source.
        * Date         4/5/2021
        * History Log  7/18/2018, 5/7/2020
        * @author      <i>Nguyen Vi Cao</i>
        *</pre>
       *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public void printAllPaths() {
            System.out.println("All shortest paths from " +
                vertices.get(getRoot()) + " are:");
            for (int i = 0; i < cost.length; i++) {
                printPath(i); // Print a path from i to the source
                System.out.println("(cost: " + cost[i] + ")"); // Path cost
            }
        }
    }
}