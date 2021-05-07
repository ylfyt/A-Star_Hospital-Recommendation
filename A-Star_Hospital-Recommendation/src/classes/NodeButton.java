package classes;

import app.MainWindowController;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class NodeButton extends Button
{
    private String assetPath = "img/";
    private Node node;

    private ImageView icon;

    public NodeButton(Node node)
    {
        super();
        this.setMaxSize(15, 15);
        this.node = node;

        if (node.getType().equals("rs"))
        {
            icon = new ImageView(new Image(assetPath + "plusIcon.png"));
            icon.setFitWidth(15);
            icon.setFitHeight(15);
            this.setGraphic(icon);
        }
        else
        {
            String[] data = node.getName().split(" ");
            String numberOfName = data[data.length-1];
            this.setText(numberOfName);
            this.setStyle("-fx-font-weight: bold;");
        }



        float xPos = AppManagement.getPosXRelative(node.getPosition().getX());
        float yPos = AppManagement.getPosYRelative(node.getPosition().getY());
        this.setLayoutX(xPos - 9);
        this.setLayoutY(yPos - 9);

        this.setOnAction(event -> onClickEvent());
    }

    public void onClickEvent()
    {
        System.out.println("id:" + node.getId());
        List<Integer> route = AppManagement.getRouteRecommendation(node.getId());
        if (route == null)
        {
            System.out.println("Route is null");
            return;
        }
        AppManagement.refreshMapRoute(route);
    }

    public Node getNode(){
        return node;
    }
}
