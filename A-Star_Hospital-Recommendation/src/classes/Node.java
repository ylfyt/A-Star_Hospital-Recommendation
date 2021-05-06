package classes;

public class Node
{
    private Point pos;
    private int id;
    private String name;

    public Node(float x, float y, int id, String name)
    {
        this.pos = new Point(x, y);
        this.id = id;
        this.name = name;
    }

    public Point getPosition(){
        return this.pos;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
}
