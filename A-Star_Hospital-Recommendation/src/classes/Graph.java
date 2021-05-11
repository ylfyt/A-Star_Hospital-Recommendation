package classes;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Node> nodes;
    private List<List<Integer>> connectedNode;
    private float scale;

    private int numOfRS;
    private int numOfUnknonw;


    public Graph(float scale)
    {
        this.nodes = new ArrayList<>();
        this.connectedNode = new ArrayList<>();
        this.scale = scale;
        numOfRS = 0;
        numOfRS = 0;
    }

    public List<Node> getListNodes(){
        return this.nodes;
    }

    public Node getNode(int id) {
        return nodes.get(id);
    }

    public List<List<Integer>> getListListConnectedNode(){
        return this.connectedNode;
    }

    public List<Integer> getListConnectedNode(int id) {
        return this.connectedNode.get(id);
    }

    public int getConnectedNode(int id1, int id2){
        return (int)this.connectedNode.get(id1).get(id2);
    }

    public float getScale(){
        return this.scale;
    }

    public void addNode(float x, float y, String type, String name)
    {
        int id = nodes.size();
        if (type.equals("rs"))
        {
            numOfRS++;
        }
        else
        {
            numOfUnknonw++;
        }

        this.nodes.add(new Node(x, y, id, type, name));
        this.connectedNode.add(new ArrayList<>());
    }

    public void addEdge(int id1, int id2)
    {
        try{
            connectedNode.get(id1).add(id2);
            connectedNode.get(id2).add(id1);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public float getMaxX()
    {
        if (this.nodes.size() == 0)
        {
            return -999;
        }

        float maxX = nodes.get(0).getPosition().getX();
        for (int i = 1; i < nodes.size(); i++) {
            if (maxX < nodes.get(i).getPosition().getX())
                maxX = nodes.get(i).getPosition().getX();
        }

        return maxX;
    }
    public float getMaxY()
    {
        if (this.nodes.size() == 0)
        {
            return -999;
        }

        float maxY = nodes.get(0).getPosition().getY();
        for (int i = 1; i < nodes.size(); i++) {
            if (maxY < nodes.get(i).getPosition().getY())
                maxY = nodes.get(i).getPosition().getY();
        }

        return maxY;
    }

    public float getMinX()
    {
        if (this.nodes.size() == 0)
        {
            return -999;
        }

        float minX = nodes.get(0).getPosition().getX();
        for (int i = 1; i < nodes.size(); i++) {
            if (minX > nodes.get(i).getPosition().getX())
                minX = nodes.get(i).getPosition().getX();
        }

        return minX;
    }
    public float getMinY()
    {
        if (this.nodes.size() == 0)
        {
            return -999;
        }

        float minY = nodes.get(0).getPosition().getY();
        for (int i = 1; i < nodes.size(); i++) {
            if (minY > nodes.get(i).getPosition().getY())
                minY = nodes.get(i).getPosition().getY();
        }

        return minY;
    }

    public float getMapDistance(int id1, int id2)
    {
        return Point.distance(nodes.get(id1).getPosition(), nodes.get(id2).getPosition(), scale);
    }

    public float getMapRouteDistance(List<Integer> route)
    {
        float dis = 0;
        for (int i=0; i < route.size()-1; i++)
        {
            int id1 = route.get(i);
            int id2 = route.get(i+1);
            dis += getMapDistance(id1, id2);
        }

        return dis;
    }

    public int getNumOfNode()
    {
        return nodes.size();
    }

    public void printGraph()
    {
        System.out.println("Scale: " + scale);
        for (int i = 0; i < nodes.size(); i++) {
            System.out.print(nodes.get(i).getId());
            nodes.get(i).getPosition().printPoint();
            System.out.print(nodes.get(i).getType());
            System.out.print(":");
            System.out.print(nodes.get(i).getName());
            if (i != nodes.size()-1)
                System.out.print(", ");
            else
                System.out.println();
        }

        for (int i = 0; i < connectedNode.size(); i++) {
            System.out.print(nodes.get(i).getId() + ": ");
            for (int j = 0; j < connectedNode.get(i).size(); j++) {
                int id = connectedNode.get(i).get(j);
                System.out.print(id);
                if (j != connectedNode.get(i).size()-1)
                    System.out.print(", ");
            }
            System.out.println();
        }
    }
}
