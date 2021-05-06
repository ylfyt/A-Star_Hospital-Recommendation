package classes;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class NodeButton extends Button
{
    private String assetPath = "img/";
    private Node node;
    private Graph graph;
    private Pane mapPanel;

    private ImageView icon;

    public NodeButton(Node node, Graph graph, Pane mapPanel)
    {
        super();
        this.setMaxSize(18, 18);
        this.node = node;
        this.graph = graph;
        this.mapPanel = mapPanel;

        if (node.getType().equals("rs"))
        {
            icon = new ImageView(new Image(assetPath + "plusIcon.png"));
        }
        else
        {
            icon = new ImageView(new Image(assetPath + "targetIcon.png"));
        }

        icon.setFitWidth(18);
        icon.setFitHeight(18);
        this.setGraphic(icon);

        float xPos = getPosXRelative(node.getPosition().getX());
        float yPos = getPosYRelative(node.getPosition().getY());
        this.setLayoutX(xPos - 9);
        this.setLayoutY(yPos - 9);

        this.setOnAction(event -> System.out.println(node.getName()));
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
