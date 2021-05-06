package classes;

public class Node
{
    private Point pos;
    private int id;
    private String name;
    private String type;

    public Node(float x, float y, int id, String type, String name)
    {
        this.pos = new Point(x, y);
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Point getPosition(){
        return this.pos;
    }

    public int getId(){
        return this.id;
    }

    public String getType(){
        return this.type;
    }

    public String getName(){
        return this.name;
    }
}
