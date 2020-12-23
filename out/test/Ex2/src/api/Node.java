package api;

import gameClient.util.Point3D;
import api.geo_location;
import api.geolocation;
public class Node implements  node_data{

    private int key;
    private int kcount =0;
    private int tag;
    private geo_location point;
    private double weight;
    private String info;

    // ******* Constructors ******* \\

    public Node(){ // Default Constructor
        key = kcount++;
        tag = 0;
        point = null;
        weight = 0.0;
        info = "";
    }
    // create node by giving key
    public Node(int k){
        key = k;
        tag = 0;
        point = null;
        weight = 0.0;
        info = "";
    }
    public Node(int key,int tag, geo_location point, double weight, String info) {
        this.key = key;
        this.tag = tag;
        this.point = point;
        this.weight = weight;
        this.info = info;
    }

    public Node(int k, geo_location locat) {
        key = k;
        tag = 0;
        point = locat;
        weight = Double.POSITIVE_INFINITY;
        info = "";
    }


    /**
     * Copy Constructor
     */
    public Node(Node other) {
        this.key = other.key;
        this.tag = other.tag;
        this.point = other.point;
        this.weight = other.weight;
        this.info = other.info;
    }


    @Override
    public int getKey() {
        return key;
    }

    @Override
    public geo_location getLocation() {
        return point;
    }

    @Override
    public void setLocation(geo_location p) {
            point = p;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double w) {
            weight = w;
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
     *Print all the criterions of the node
     */
    public String toString() {
        return "key: "+key+",tag: "+tag+",point: "+point+",weight: "+weight+",info: "+info; }
}
