/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here. Note external code may
 * reduce your score but appropriate citation is required to avoid academic
 * integrity violations. Please see the Course Syllabus as well as the
 * university code of academic integrity:
 *  https://catalog.upenn.edu/pennbook/code-of-academic-integrity/ >
 * Signed,
 * Author: YOUR NAME HERE
 * Penn email: <YOUR-EMAIL-HERE@seas.upenn.edu>
 * Date: YYYY-MM-DD
 */

import java.util.*;

public class GraphUtils {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 78327812893L;

    /**
     * Given a graph, this method returns the smallest number of edges from the src
     * node to the dest node, or 0 when src = dest, or −1 for any invalid input.
     * Invalid inputs are defined as: any of graph, src, or dest is null; no path
     * exists from src to dest; any of src or dest do not exist in graph.
     *
     * @param graph directed or undirected graph
     * @param src   source node
     * @param dest  destination node
     * @return the smallest number of edges from the src to dest, or -1 for any
     *         invalid input
     */
    /**
     * Returns the smallest number of edges from the source node to the destination node in the graph.
     *
     * @param graph The graph to search within.
     * @param src   The source node.
     * @param dest  The destination node.
     * @return The smallest number of edges from src to dest, or -1 if any input is invalid or no path exists.
     */
    public static int minDistance(Graph graph, String src, String dest) {
        // Check for invalid inputs
        if (graph == null || src == null || dest == null || !graph.containsNode(src) || !graph.containsNode(dest)) {
            return -1;
        }

        // If source and destination are the same
        if (src.equals(dest)) {
            return 0;
        }

        // BFS to find the shortest path
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(src);
        visited.add(src);

        int distance = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            distance++;

            for (int i = 0; i < levelSize; i++) {
                String currentNode = queue.poll();
                for (String neighbor : graph.getNodeNeighbors(currentNode)) {
                    if (neighbor.equals(dest)) {
                        return distance;
                    }
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }

        // If no path is found
        return -1;
    }

    /**
     * Given a graph, a src node contained in graph, and a distance of at least 1,
     * this method returns the set of all nodes, excluding src, for which the
     * smallest number of edges from src to each node is less than or equal to
     * distance; null is returned if there is any invalid input. Invalid inputs are
     * defined as: any of graph or src is null; src is not in graph; distance is
     * less than 1.
     *
     * @param graph    directed or undirected graph
     * @param src      source node
     * @param distance maximum distance from source to the nodes to include in
     *                 output set
     * @return set of all nodes, excluding src, for which the smallest number of
     *         edges from src to each node is less than or equal to distance, or
     *         null on invalid input
     */
    public static Set<String> nodesWithinDistance(Graph graph, String src, int distance) {
        // Check for invalid inputs
        if (graph == null || src == null || distance < 1 || !graph.containsNode(src)) {
            return null;
        }

        Set<String> result = new HashSet<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(src);
        visited.add(src);

        int currentDistance = 0;
        while (!queue.isEmpty() && currentDistance < distance) {
            int levelSize = queue.size();
            currentDistance++;

            for (int i = 0; i < levelSize; i++) {
                String currentNode = queue.poll();
                for (String neighbor : graph.getNodeNeighbors(currentNode)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                        // Only add to result if within the specified distance
                        if (currentDistance <= distance) {
                            result.add(neighbor);
                        }
                    }
                }
            }
        }

        return result;
    }

    /**
     * Given a Graph, this method indicates whether the List of node values
     * represents a Hamiltonian Cycle.
     *
     * A Hamiltonian Cycle is a valid path through the graph in which every node
     * in the graph is visited exactly once except for the start and end nodes.
     * The method returns a HamiltonianReport object describing the validity of the
     * Hamiltonian Cycle represented by the input List. For this exercise, a cycle must
     * contain at least 3 nodes.
     *
     * @param g      	The directed or undirected graph to operate on
     * @param values 	The proposed path to test on the graph
     * @return Non-null HamiltonianReport describing if values represent a valid
     * 				 	Hamiltonian cycle of g
     */
    public static HamiltonianReport isHamiltonianCycle(Graph g, List<String> values) {
        // Check for NULL_INPUT
        if (g == null || values == null) {
            return new HamiltonianReport(HamiltonianReport.Status.NULL_INPUT, null);
        }

        // Check for INVALID_LENGTH
        if (values.size() < 3 || g.getNumNodes() < 3) {
            return new HamiltonianReport(HamiltonianReport.Status.INVALID_LENGTH, null);
        }

        // Check for INVALID_CYCLE
        if (!values.get(0).equals(values.get(values.size() - 1)) || values.size() != g.getNumNodes() + 1) {
            return new HamiltonianReport(HamiltonianReport.Status.INVALID_CYCLE, null);
        }

        // Check for each element if it exists in the graph
        Set<String> visited = new HashSet<>();
        for (int i = 0; i < values.size() - 1; i++) {
            String current = values.get(i);
            String next = values.get(i + 1);

            // Check if the node exists in the graph
            if (!g.containsNode(current)) {
                return new HamiltonianReport(HamiltonianReport.Status.INVALID_NODE, current);
            }

            // Check if the edge exists in the graph
            if (!g.getNodeNeighbors(current).contains(next)) {
                return new HamiltonianReport(HamiltonianReport.Status.INVALID_NODE, next);
            }

            // Check if the node is visited more than once (except the first/last node)
            if (visited.contains(current) && i != 0) {
                return new HamiltonianReport(HamiltonianReport.Status.INVALID_NODE, current);
            }

            visited.add(current);
        }

        // If all checks pass, return VALID
        return new HamiltonianReport(HamiltonianReport.Status.VALID, null);
    }
}
