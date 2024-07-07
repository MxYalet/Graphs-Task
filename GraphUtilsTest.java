/*
 ***** Important!  Please Read! *****
 *
 *  - Do NOT remove any of the existing import statements
 *  - Do NOT import additional junit packages
 *  - You MAY add in other non-junit packages as needed
 *
 *  - Do NOT remove any of the existing test methods or change their name
 *  - You MAY add additional test methods.  If you do, they should all pass
 *
 *  - ALL of your assert test cases within each test method MUST pass, otherwise the
 *        autograder will fail that test method
 *  - You MUST write the require number of assert test cases in each test method,
 *        otherwise the autograder will fail that test method
 *  - You MAY write more than the required number of assert test cases as long as they all pass
 *
 *  - All of your assert test cases within a method must be related to the method they are meant to test
 *  - All of your assert test cases within a method must be distinct and non-trivial
 *  - Your test cases should reflect the method requirements in the homework instruction specification
 *
 *  - Your assert test cases will be reviewed by the course instructors and they may take off
 *        points if your assert test cases to do not meet the requirements
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Set;

class GraphUtilsTest {

	@Test
	void testMinDistance() {
		/*
		 * TODO Write at least 5 assert test cases that test your 'minDistance' method
		 * Review the homework instructions and write assert test realated the this methods specification
		 * All 5 assert statements MUST pass.
		 */
		DirectedGraph graph = new DirectedGraph();
		graph.addEdge("A", "B");
		graph.addEdge("B", "C");
		graph.addEdge("A", "D");
		graph.addEdge("D", "E");
		graph.addEdge("E", "C");

		// Test case 1: Valid path with direct connection
		assertEquals(1, GraphUtils.minDistance(graph, "A", "B"), "Test case 1 failed");
		// Invalid input (null source)
		assertEquals(-1, GraphUtils.minDistance(graph, null, "B"), "Test case 6 failed");

		// Invalid input (null destination)
		assertEquals(-1, GraphUtils.minDistance(graph, "A", null), "Test case 7 failed");

		// Source node does not exist in graph
		assertEquals(-1, GraphUtils.minDistance(graph, "Z", "B"), "Test case 8 failed");

		// Destination node does not exist in graph
		assertEquals(-1, GraphUtils.minDistance(graph, "A", "Z"), "Test case 9 failed");

		// Valid path with multiple intermediate nodes
		assertEquals(2, GraphUtils.minDistance(graph, "A", "C"), "Test case 10 failed");


	}

	@Test
	void testNodesWithinDistance() {
		/*
		 * TODO Write at least 5 assert test cases that test your 'nodesWithinDistance' method
		 * Review the homework instructions and write assert test realated the this methods specification
		 * All 5 assert statements MUST pass.
		 */
		// Create a directed graph for testing
		DirectedGraph graph = new DirectedGraph();
		graph.addEdge("A", "B");
		graph.addEdge("B", "C");
		graph.addEdge("A", "D");
		graph.addEdge("D", "E");
		graph.addEdge("E", "C");

		Set<String> expected1 = Set.of("B", "D");
		assertEquals(expected1, GraphUtils.nodesWithinDistance(graph, "A", 1), "Test case 1 failed");

		// Test Valid input with nodes within greater distance
		Set<String> expected2 = Set.of("B", "C", "D", "E");
		assertEquals(expected2, GraphUtils.nodesWithinDistance(graph, "A", 3), "Test case 2 failed");
		// Test Invalid input (null source)
		assertNull(GraphUtils.nodesWithinDistance(graph, null, 1), "Test case 5 failed");

		// Test Invalid input (source node not in graph)
		assertNull(GraphUtils.nodesWithinDistance(graph, "Z", 1), "Test case 6 failed");

		// Test Invalid input (distance less than 1)
		assertNull(GraphUtils.nodesWithinDistance(graph, "A", 0), "Test case 7 failed");

		// Test valid input with nodes within distance 2 from "B"
		Set<String> expected8 = Set.of("C");
		assertEquals(expected8, GraphUtils.nodesWithinDistance(graph, "B", 1), "Test case 8 failed");

		// Test Valid input with nodes within distance 2 from "A"
		Set<String> expected9 = Set.of("B", "C", "D", "E");
		assertEquals(expected9, GraphUtils.nodesWithinDistance(graph, "A", 2), "Test case 9 failed");

	}

	@Test
	void testIsHamiltonianCycle() {
		/*
		 * TODO Write at least 5 assert test cases that test your 'isHamiltonianCycle' method
		 * Review the homework instructions and write assert test realated the this methods specification
		 * All 5 assert statements MUST pass.
		 */
		DirectedGraph graph = new DirectedGraph();
		graph.addEdge("A", "B");
		graph.addEdge("B", "C");
		graph.addEdge("C", "A");
		graph.addEdge("A", "D");
		graph.addEdge("D", "E");
		graph.addEdge("E", "A");

		// Test case 1: Valid Hamiltonian cycle
		List<String> cycle1 = List.of("A", "B", "C", "A");
		HamiltonianReport expected1 = new HamiltonianReport(HamiltonianReport.Status.INVALID_CYCLE, null);
		assertEquals(expected1, GraphUtils.isHamiltonianCycle(graph, cycle1), "Test case 1 failed");

		// Test case 2: Null input graph
		List<String> cycle2 = List.of("A", "B", "C", "A");
		HamiltonianReport expected2 = new HamiltonianReport(HamiltonianReport.Status.NULL_INPUT, null);
		assertEquals(expected2, GraphUtils.isHamiltonianCycle(null, cycle2), "Test case 2 failed");

		// Test case 3: Null input cycle
		HamiltonianReport expected3 = new HamiltonianReport(HamiltonianReport.Status.NULL_INPUT, null);
		assertEquals(expected3, GraphUtils.isHamiltonianCycle(graph, null), "Test case 3 failed");

		// Test case 4: Invalid cycle length
		List<String> cycle4 = List.of("A", "B");
		HamiltonianReport expected4 = new HamiltonianReport(HamiltonianReport.Status.INVALID_LENGTH, null);
		assertEquals(expected4, GraphUtils.isHamiltonianCycle(graph, cycle4), "Test case 4 failed");

		// Test case 5: Acyclic input list
		List<String> cycle5 = List.of("A", "B", "D", "C");
		HamiltonianReport expected5 = new HamiltonianReport(HamiltonianReport.Status.INVALID_CYCLE, null);
		assertEquals(expected5, GraphUtils.isHamiltonianCycle(graph, cycle5), "Test case 5 failed");

	}
}
