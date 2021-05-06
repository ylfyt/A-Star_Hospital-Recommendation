package classes;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class EdgeLine extends Line {
    private int id1;
    private int id2;
    private Graph graph;
    private Pane mapPanel;

    public EdgeLine(int id1, int id2, Graph graph, Pane mapPanel)
    {
        super();
        this.id1 = id1;
        this.id2 = id2;
        this.graph = graph;
        this.mapPanel = mapPanel;

        float xStart = getPosXRelative(graph.getNode(id1).getPosition().getX());
        float yStart = getPosYRelative(graph.getNode(id1).getPosition().getY());

        float xEnd = getPosXRelative(graph.getNode(id2).getPosition().getX());
        float yEnd = getPosYRelative(graph.getNode(id2).getPosition().getY());

        this.setStartX(xStart);
        this.setStartY(yStart);

        this.setEndX(xEnd);
        this.setEndY(yEnd);
    }

    public float getPosXRelative(float x)
    {
        float pad = 70;

        float xPanelCenter = ((float) mapPanel.getWidth()-pad)/2;
        float xCenterPos = (graph.getMaxX() + graph.getMinX())/2;
        float xMaxDisCenter =(graph.getMaxX() - graph.getMinX())/2;

        float dis = x - xCenterPos;

        if (xMaxDisCenter == 0) {
            return  xPanelCenter + pad/2;
        }
        else{
            return dis/xMaxDisCenter * xPanelCenter + xPanelCenter + pad/2;
        }
    }

    public float getPosYRelative(float y)
    {
        float pad = 70;

        float yPanelCenter = ((float) mapPanel.getHeight()-pad)/2;
        float yCenterPos = (graph.getMaxY() + graph.getMinY())/2;
        float yMaxDisCenter =(graph.getMaxY() - graph.getMinY())/2;

        float dis = y - yCenterPos;

        if (yMaxDisCenter == 0) {
            return  yPanelCenter + pad/2;
        }
        else{
            return dis/yMaxDisCenter * yPanelCenter + yPanelCenter + pad/2;
        }
    }
}
