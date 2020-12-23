package api;

import java.util.Collection;
import java.util.HashMap;
import java.io.Serializable;
public class DWGraph_DS implements directed_weighted_graph, Serializable{

    public HashMap<Integer, node_data> nodes = new HashMap<>();
    public HashMap<Integer, HashMap<Integer, edge_data>> edgeW;

    private int nodeCapacity = 0;
    private int edgeCount = 0;
    private int mc =0;
    public DWGraph_DS(){
        nodes = new HashMap<>();
        edgeW = new HashMap<>();
    }

    /**    private HashMap<Integer, node_info> weightGraph;
     private HashMap<Integer, HashMap<node_info, Double>> wightList;
     private int vers, edgs, mc;

     public WGraph_DS() {
     this.weightGraph = new HashMap<>();
     this.wightList = new HashMap<>();
     this.edgs = 0;
     this.mc = 0;
     }
     */
    public DWGraph_DS(HashMap<Integer, node_data> nm){
        nodes = nm;
        edgeW = new HashMap<>();
        for(node_data n : nm.values()){
            edgeW.put(n.getKey(), new HashMap<>());
        }
    }


    @Override
    public node_data getNode(int key) {
       if(nodes.containsKey(key))
           return nodes.get(key);
        return null;
    }

    @Override
    public edge_data getEdge(int src, int dest) {
        if(edgeW.containsKey(src) && edgeW.containsKey(dest))
            return edgeW.get(src).get(dest);
        return null;
    }

    @Override
    public void addNode(node_data n) {
        if (nodes.containsKey(n.getKey())) {
            throw new RuntimeException("There is already node with this correct key");
        } else {
            nodes.put(n.getKey(), n);
            nodeCapacity++;
            mc++;
        }
    }
    @Override
    public void connect(int src, int dest, double w) {
        if (nodes.containsKey(src) && nodes.containsKey(dest)) {
                Edge co = new Edge(src, dest, 0, w , null);
                if(src != dest && w>0){
                    if(edgeW.get(src) !=null && edgeW.get(src).get(dest) == null){
                        edgeW.get(src).put(dest, co);
                        edgeCount++;
                        mc++;
                    }
                }

        }
    }

    @Override
    public Collection<node_data> getV() {
        return nodes.values();
    }

    @Override
    public Collection<edge_data> getE(int node_id) {
        if(edgeW.isEmpty() || edgeW.get(node_id) == null)
                return null;
        return edgeW.get(node_id).values();

    }

    @Override
    public node_data removeNode(int key) {
        if (this.nodes.containsKey(key)) {
            for (node_data n : nodes.values()) {
                removeEdge(n.getKey(), key);
                nodeCapacity--;
                mc++;
            }
            return nodes.remove(key);
        }
        return null;
    }

    @Override
    public edge_data removeEdge(int src, int dest) {
        if(src == dest){
            throw new RuntimeException("Same node - cant be edge from him to himself");
        }
        if(edgeW.get(src).get(dest) != null){
            edge_data Deletedge = new Edge((Edge) edgeW.get(src).get(dest));
            edgeW.get(src).remove(getNode(dest));
            edgeW.get(dest).remove(getNode(src));
            edgeCount--;
            mc++;
            return Deletedge;
        }
        return null;
    }

    @Override
    public int nodeSize() {
        return nodeCapacity;
        //return nodes.size();
    }

    @Override
    public int edgeSize() {
        return edgeCount;
    }

    @Override
    public int getMC() {
        return mc;
    }



}
