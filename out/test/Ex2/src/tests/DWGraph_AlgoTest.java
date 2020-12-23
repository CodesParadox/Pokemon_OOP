package tests;
import api.*;
import api.DWGraph_Algo;
import api.DWGraph_DS;
import api.Node;
import org.junit.jupiter.api.Test;
import org.json.JSONException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DWGraph_AlgoTest {
    public directed_weighted_graph createdGraph(int vert, int e, double weight){
        directed_weighted_graph g = new DWGraph_DS();
        for (int i = 0; i < vert; i++) {
            g.addNode(new Node(i));
        }
        int j = 0;
        while (j < e) {
            g.connect(j, j + 1, weight);
            j++;
        }
        return g;
    }
    @Test
    void init() {
        dw_graph_algorithms ga = new DWGraph_Algo(createdGraph(100000, 1, 200));
        directed_weighted_graph g1 = createdGraph(0,0,0);
        ga.init(g1);
        assertTrue(ga.isConnected());
        assertEquals(0, ga.getGraph().nodeSize());
        assertEquals(ga.getGraph(), g1);
        ga.init(createdGraph(10,9,1));
        assertEquals(10, ga.getGraph().nodeSize());

    }

    @Test
    void copy() {
        dw_graph_algorithms g2_1 = new DWGraph_Algo(createdGraph(100000,1,200));
        dw_graph_algorithms g2_2 = new DWGraph_Algo(g2_1.copy());
        assertEquals(g2_1,g2_2);
        node_data n = g2_1.getGraph().getV().iterator().next();
        g2_2.getGraph().removeNode(n.getKey());
        assertNotEquals(g2_1,g2_2);
        g2_2.getGraph().addNode(new Node(1000000));
        assertNotEquals(g2_1,g2_2);
        g2_2.getGraph().removeNode(1000000);
        g2_2.getGraph().addNode(new Node(0));
        g2_2.getGraph().connect(n.getKey(),n.getKey()+1,200);
        assertTrue(g2_1.equals(g2_2));

    }

    @Test
    void isConnected() {
        dw_graph_algorithms g3 = new DWGraph_Algo(createdGraph(100000, 1, 200));
        assertTrue(g3.isConnected());
        g3.getGraph().addNode(new Node(0));
        assertTrue(g3.isConnected());
        g3 = new DWGraph_Algo(createdGraph(1000,999,1));
        assertFalse(g3.isConnected());
        g3.getGraph().connect(998,0,1);
        assertFalse(g3.isConnected());
        g3.getGraph().connect(999,0,1);
        assertTrue(g3.isConnected());
    }

    @Test
    void shortestPathDist() {
        dw_graph_algorithms g4 = new DWGraph_Algo(createdGraph(100000, 1, 200));
        g4.init(createdGraph(5,4,1));
        assertEquals(4,g4.shortestPathDist(0,4));
        assertEquals(-1, g4.shortestPathDist(4,0));
        assertEquals(0,g4.shortestPathDist(0,0));
        g4.getGraph().connect(4,0,1);
        assertEquals(4, g4.shortestPathDist(4,3));
        assertEquals(4, g4.shortestPathDist(3,2));
        assertEquals(4, g4.shortestPathDist(2,1));
        assertEquals(4, g4.shortestPathDist(1,0));

    }

    @Test
    void shortestPath() {
        dw_graph_algorithms g5 = new DWGraph_Algo(createdGraph(100000, 1, 200));
        System.out.println(g5.shortestPath(0,0));
        g5.init(createdGraph(10,9,1));
        List<node_data> path = g5.shortestPath(0,9);
        for(node_data n : path){
            System.out.print(n.getKey());
            if(n != path.get(path.size()-1)) System.out.print(" --> ");
        }
    }

    @Test
    void save() throws JSONException {
        dw_graph_algorithms g6 = new DWGraph_Algo(createdGraph(100000, 1, 200));
        g6.init(createdGraph(100,99,1));
        g6.save("C:/Users/Galb/Desktop/Tests/tests/graph.json");
    }

    @Test
    void load() throws JSONException {
        dw_graph_algorithms g7_1 = new DWGraph_Algo(createdGraph(100000, 1, 200));
        g7_1.init(createdGraph(100,99,1));
        g7_1.save("C:/Users/Galb/Desktop/Tests/graph.json");
        dw_graph_algorithms g7_2 = new DWGraph_Algo(createdGraph(100000, 1, 200));
        g7_2.load("C:/Users/Galb/Desktop/Tests/graph.json");
        assertTrue(g7_1.equals(g7_2));
        System.out.println(g7_1.getGraph());
        System.out.println(g7_2.getGraph());
    }
}
