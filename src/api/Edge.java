package api;
import java.io.Serializable;
public class Edge implements edge_data, Serializable{
    private int src;
    private int dest;
    private int tag;
    private double weight;
    private String info;

    // ******* Constructors ******* \\
    public Edge(){
        src = 0;
        dest = 0;
        tag = 0;
        weight = 0.0;
        info = "";
    }

    public Edge(int src,int dest, int tag, double weight, String info){
        if(weight<0)
            throw new RuntimeException("negative weight");
        this.src = src;
        this.dest = dest;
        this.tag = tag;
        this.weight = weight;
        this.info = info;
    }
    // Copy Constructor
    public Edge(Edge other){
        this.src = other.src;
        this.dest = other.dest;
        this.tag = other.tag;
        this.weight = other.weight;
        this.info = other.info;

    }


    @Override
    public int getSrc() {
        return src;
    }

    @Override
    public int getDest() {
        return dest;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String s) {
            info = s;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
        tag = t;
    }

    /**
     *Print all the criterions of the edge
     */
    public String toString() {
        return "src: "+src+",dest: "+dest+",weight: "+weight+",info: "+info+",tag: "+tag; }
}
