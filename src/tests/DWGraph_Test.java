package tests;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.fail;
import org.junit.Assert;
import api.*;
import api.DWGraph_DS;
import api.Node;
import api.geo_location;
import api.geolocation;
import gameClient.util.Point3D;

import api.directed_weighted_graph;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DWGraph_Test {
    public static directed_weighted_graph createrGraph(int vert, int e, double weight) {
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
    void addNode() {
        directed_weighted_graph g5 = new DWGraph_DS();
        assertEquals(0, g5.nodeSize());
        g5.addNode(new Node());
        assertEquals(1, g5.nodeSize());

    }

    @Test
    void getEdge() {
        directed_weighted_graph g2 = createrGraph(14, 9, 1);
        assertEquals(1, g2.getEdge(0, 1).getWeight());
        assertEquals(null, g2.getEdge(1, 0));
        g2.connect(0, 9, 500);
        assertEquals(500, g2.getEdge(0, 9).getWeight());
        assertEquals(null, g2.getEdge(9, 0));

    }

    @Test
    void getNode() {
        directed_weighted_graph gg = createrGraph(1, 1, 1);
        assertEquals(1, gg.getNode(1).getKey());
        gg.addNode(new Node(150));
        int k = gg.getNode(150).getKey();
        assertEquals(150, k);
        assertEquals(2, gg.nodeSize());
    }

        @Test
        void connect () {
            directed_weighted_graph g4 = createrGraph(10, 7, 1);
            assertEquals(7, g4.edgeSize());
            g4.connect(7, 0, 1);
            assertEquals(5, g4.edgeSize());
            g4.connect(7, 0, 5);
            assertEquals(5, g4.edgeSize());
        }

        @Test
        void getV () {
            directed_weighted_graph g5 = new DWGraph_DS();
            assertEquals(0, g5.getV().size());
            g5 = createrGraph(2000, 2000, 1);
            assertEquals(2000, g5.getV().size());
            assertEquals(2000, g5.nodeSize());
            g5.removeNode(300);
            assertEquals(700, g5.getV().size());
            assertEquals(700, g5.nodeSize());

        }

        @Test
        void getE () {
            directed_weighted_graph g6 = createrGraph(501, 0, 1);
           int i=0;
            int j=1;
           while(i < 500){
               g6.connect(0, i, 1);
               i++;
           }
            assertEquals(998, g6.getE(0).size());
            while(j < 500) {
                assertEquals(0, g6.getE(j).size());
                j++;
            }


        }

        @Test
        void removeNode () {
            directed_weighted_graph g7 = new DWGraph_DS();
            g7.removeNode(100);
            int i =0;
            g7 = createrGraph(1000, 999, 400);
            g7.removeNode(10000);
            int godel = g7.nodeSize();
            while(i < godel) {
                g7.removeNode(i);
                i++;
            }
            assertEquals(0, g7.nodeSize());
            assertEquals(0, g7.edgeSize());
            assertEquals(0, g7.getV().size());
        }

        @Test
        void removeEdge () {
            directed_weighted_graph g8 = createrGraph(100, 1000, 1);
            assertEquals(99, g8.edgeSize());
            g8.removeEdge(71, 99);
            g8.removeEdge(1, 1);
            g8.removeEdge(0, 100);
            assertEquals(71, g8.edgeSize());
            assertEquals(0, g8.getE(71).size());
        }

        @Test
        void nodeSize () {
            directed_weighted_graph g9 = new DWGraph_DS();
            assertEquals(0, g9.nodeSize());
            g9 = createrGraph(900, 899, 1);
            assertEquals(900, g9.nodeSize());

        }

        @Test
        void edgeSize () {
            directed_weighted_graph g10 = new DWGraph_DS();
            assertEquals(0, g10.edgeSize());
            g10 = createrGraph(1000, 1000, 1);
            assertEquals(999, g10.edgeSize());
        }

        @Test
        void getMC () {
            directed_weighted_graph g11 = new DWGraph_DS();
            int i=0;
            assertEquals(0, g11.getMC());
            while(i < 900){
                g11.addNode(new Node(i));
                i++;
            }
            assertEquals(900, g11.getMC());
            g11.removeNode(500);
            assertEquals(1000, g11.getMC());
            g11.removeNode(500);
            assertEquals(1000, g11.getMC());
        }
    }


