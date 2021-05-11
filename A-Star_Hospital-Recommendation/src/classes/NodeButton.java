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
//        this.setMaxSize(20, 20);
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
//            this.setText(node.getId() + "");
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
        System.out.print("id:" + node.getId() + " Pos:");
        node.getPosition().printPoint();
        System.out.println();
//        System.out.println(AppManagement.graph.getNumOfNode());

        List<List<Integer>> routes = AppManagement.getRouteRecommendation(node.getId());
        if (routes == null)
        {
            System.out.println("Route is null");
            AppManagement.routeTextField.setText("Route is not found!");
            return;
        }
        AppManagement.refreshMapRoute(routes.get(0));

        if(routes.size() > 1)
        {
            StringBuilder text = new StringBuilder(AppManagement.routeTextField.getText() + "\n\n");
            text.append("Another Routes: \n");
            for (int i = 1; i < routes.size(); i++) {
                List<Integer> route = routes.get(i);
                float dis = AppManagement.graph.getMapRouteDistance(route);
                dis = (float) Math.round(dis * 100) / 100;
                text.append(dis);
                text.append(" m : ");
                for (int j = 0; j < route.size(); j++) {
                    text.append(AppManagement.graph.getNode(route.get(j)).getName());
                    if (j != route.size()-1)
                        text.append(" --> ");
                }
                if (i != routes.size()-1)
                    text.append("\n");
            }

            AppManagement.routeTextField.setText(text.toString());
        }
    }

    public Node getNode(){
        return node;
    }
}
