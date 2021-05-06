package classes;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Node> nodes;
    private List<List<Integer>> connectedNode;
    private float scale;

    public Graph(float scale)
    {
        this.nodes = new ArrayList<>();
        this.connectedNode = new ArrayList<>();
        this.scale = scale;
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

    public void addNode(float x, float y, String name)
    {
        int id = nodes.size();
        this.nodes.add(new Node(x, y, id, name));
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

    public void printGraph()
    {
        System.out.println("Scale: " + scale);
        for (int i = 0; i < nodes.size(); i++) {
            System.out.print(nodes.get(i).getId());
            nodes.get(i).getPosition().printPoint();
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
