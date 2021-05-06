package classes;

import app.MainWindowController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.List;

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

        this.setOnAction(event -> onClickEvent());
    }

    public void onClickEvent()
    {
        try{
            List<javafx.scene.Node> childs = mapPanel.getChildren();

            for (javafx.scene.Node node : childs)
            {
                if (node instanceof EdgeLine)
                {
                    EdgeLine el = (EdgeLine) node;
                    if (this.node.getId() == el.getId1() || this.node.getId() == el.getId2())
                    {
                        el.setStroke(Color.RED);
                        refreshPanel();
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void refreshPanel()
    {
        try {
            List<javafx.scene.Node> childs = mapPanel.getChildren();

            for (javafx.scene.Node node : childs)
            {
                if (node instanceof EdgeLine)
                {
                    EdgeLine el = (EdgeLine) node;
                    el.setStroke(Color.BLACK);
                }
                else
                {
                    NodeButton nb = (NodeButton) node;
//                    nb.setStyle("-fx-background-color: " + "#FFFFFF" + ";");
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
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
