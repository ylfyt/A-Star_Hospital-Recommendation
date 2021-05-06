package classes;

public class Point
{
    private float x;
    private float y;

    public Point()
    {
        this.x = 0;
        this.y = 0;
    }

    public Point(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    public void printPoint()
    {
        System.out.print("(" + x + "," + y + ")");
    }

    public static float distance(Point p1, Point p2, float scale)
    {
        return (float) Math.sqrt((p2.x-p1.x)*(p2.x-p1.x) + (p2.y-p1.y)*(p2.y- p1.y) * scale);
    }
}
