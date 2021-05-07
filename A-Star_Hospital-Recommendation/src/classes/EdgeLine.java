package classes;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class EdgeLine extends Line {
    private int id1;
    private int id2;

    public EdgeLine(int id1, int id2)
    {
        super();
        this.id1 = id1;
        this.id2 = id2;

        float xStart = AppManagement.getPosXRelative(AppManagement.graph.getNode(id1).getPosition().getX());
        float yStart = AppManagement.getPosYRelative(AppManagement.graph.getNode(id1).getPosition().getY());

        float xEnd = AppManagement.getPosXRelative(AppManagement.graph.getNode(id2).getPosition().getX());
        float yEnd = AppManagement.getPosYRelative(AppManagement.graph.getNode(id2).getPosition().getY());

        this.setStartX(xStart);
        this.setStartY(yStart);

        this.setEndX(xEnd);
        this.setEndY(yEnd);

        this.setStrokeWidth(2.5);
    }

    public int getId1(){
        return id1;
    }

    public int getId2(){
        return id2;
    }
}
