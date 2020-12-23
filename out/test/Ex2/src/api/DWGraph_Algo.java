package api;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.io.*;
import java.util.*;


public class DWGraph_Algo implements dw_graph_algorithms {
    private static final long serialVersionUID = 1L;
    private directed_weighted_graph weightG;
    private nodeDistComp compare;
    public DWGraph_Algo() { weightG = new DWGraph_DS(); }
    public DWGraph_Algo(directed_weighted_graph g){ weightG = g;
    }

    @Override
    public void init(directed_weighted_graph g) { weightG = g; }

    @Override
    public directed_weighted_graph getGraph() { return this.weightG; }

    @Override
    public directed_weighted_graph copy() {
        if (this.weightG != null) {
            directed_weighted_graph graph = new DWGraph_DS();
            for (node_data node : this.weightG.getV()) {
                //copyNode(cGraph, toBeCopied);
                node_data newNode=new Node((Node) node);
                graph.addNode(newNode);

                // maybe should delete, idk yet
                node_data nodeKey = graph.getNode(node.getKey());
                nodeKey.setInfo(node.getInfo());
                nodeKey.setTag(node.getTag());
            }

            for (node_data cNode : this.weightG.getV()) {
                for (edge_data neighbor : this.weightG.getE(cNode.getKey())) {
                    graph.connect(neighbor.getSrc(), neighbor.getDest(), neighbor.getWeight());

                }
            }
            return graph;
        }
        return null;
    }

    @Override
    public boolean isConnected() {
        for (node_data node : this.weightG.getV())
            node.setTag(Double.MAX_EXPONENT);

        int x = 1;

        if (weightG.getV().size() == 1)
            return true;

        node_data tempNode = getArbitraryNode();
        if (tempNode == null)
            return true;

        Queue<Node> list = new LinkedList<>();
        if(list == null)
            return false;

        list.add((Node) tempNode);
        tempNode.setTag(x);

        if (weightG.getE(tempNode.getKey()).size() == 0)
            return false;

        while (list.isEmpty() == false) {
            node_data inf = list.poll();

            for (edge_data i : weightG.getE(inf.getKey())) {
                if (x != i.getTag()) {
                    i.setTag(x);
                    list.add((Node) i);
                }
            }

        }
        for (node_data node : weightG.getV()) {
            if (node.getTag() != x)
                return false;
        }
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if (weightG.getNode(src) == null || weightG.getNode(dest) == null)
            return -1;
        if (src == dest)
            return 0;
        String info = "";
        Node first =(Node) this.weightG.getNode(src);
        Node lastone =(Node) this.weightG.getNode(dest);
            for(node_data n : weightG.getV() ){
                n.setWeight(Integer.MAX_VALUE);
                n.setTag(0);
                n.setInfo("");
                }
            weightG.getNode(src).setWeight(0);
            if(first.getTag() == 1 && first == lastone)
                return -1;
            Cheacker(src, dest, info);
            return weightG.getNode(dest).getWeight();

        }

            // maybe 'or' || between them and first.getkey() = weightG.getnode(dest).getkey()
            //while( first!=weightG.getNode(dest) && !lastone.getInfo().isEmpty() ){}




    private void Cheacker(int src, int dest, String s) {
        node_data first = weightG.getNode(src);
        node_data last = weightG.getNode(dest);
        if(first == last && first.getTag() == 1) return;
        for (edge_data e : weightG.getE(src)) {
            double WeightNew = e.getWeight() + weightG.getNode(e.getSrc()).getWeight();
            if (WeightNew < weightG.getNode(e.getDest()).getWeight()) {
                weightG.getNode(e.getDest()).setWeight(WeightNew);
                weightG.getNode(e.getDest()).setInfo(s + "->" + src);
                weightG.getNode(e.getSrc()).setTag(1);
                Cheacker(e.getDest(), dest, s + "->" + src);
            }

        }
    }

    @Override
    public List<node_data> shortestPath(int src, int dest) {
            Double val = shortestPathDist(src,dest);
            if (val == Integer.MAX_VALUE) return null;
            LinkedList<node_data> toReturn = new LinkedList<node_data>();
            node_data currV = weightG.getNode(dest);
            toReturn.add(currV);
            while (currV!=weightG.getNode(src)){
                node_data toAdd = weightG.getNode(Integer.parseInt(currV.getInfo()));
                toReturn.addFirst(toAdd);
                currV=toAdd;
            }
            return toReturn;
        }


    @Override
    public boolean save(String file) {
        ObjectOutputStream oOutput;
        try {
            FileOutputStream fOutput = new FileOutputStream(file);
            if(fOutput == null)
                return false;

            oOutput = new ObjectOutputStream(fOutput);
            if(oOutput == null)
                return false;

            oOutput.writeObject(this.getGraph());
            return true;
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean load(String file) {
        try {
            FileInputStream fInput = new FileInputStream(file);
            if(fInput == null)
                return false;

            ObjectInputStream oInput = new ObjectInputStream(fInput);
            if(oInput == null)
                return false;

            directed_weighted_graph graph = (DWGraph_DS) oInput.readObject();
            this.init(graph);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    class nodeDistComp implements Comparator<node_data> {

        @Override
        public int compare(node_data obj1, node_data obj2) {
            if(obj1.getTag() > obj2.getTag())
                return (int) (obj1.getTag() - obj2.getTag());
            else
                return (int) (obj2.getTag() - obj1.getTag());
        }
    }
    private node_data getArbitraryNode() {
        for (node_data n : this.weightG.getV()) {
            return n;
        }
        return null;
    }

    private List<node_data> path(HashMap<Integer, node_data> prev, int st, int end) {
        List<node_data> path = new ArrayList<>();
        if(path == null)
            return null;

        node_data node = weightG.getNode(end);
        path.add(node);

        while ((path.contains(prev.get(st))) == false) {

            node = prev.get(node.getKey());
            path.add(node);

            if (node.getKey() == st)
                break;
        }

        Collections.reverse(path);
        return path;
    }
}

