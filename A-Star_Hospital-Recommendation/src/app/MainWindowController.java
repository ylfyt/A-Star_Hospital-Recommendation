package app;

import classes.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class MainWindowController {

    private Graph graph;

    @FXML
    private Pane mapPanel;

    public void initGraph()
    {
        String fileName = "map2";
        graph = GraphConverter.textToGraph(fileName);
    }

    public void handleBrowseButton()
    {
        System.out.println("Browse");
        initGraph();
    }

    public void handleRefreshButton()
    {
        System.out.println("Refresh");
        printGraphToWindow();
    }

    public void printGraphToWindow()
    {
        if (graph == null)
        {
            System.out.println("Graph is null");
            return;
        }
        mapPanel.getChildren().clear();
        for (int i = 0; i < graph.getListNodes().size(); i++)
        {
            for(int id : graph.getListConnectedNode(i))
            {
                if (id > i)
                {
                    EdgeLine el = new EdgeLine(i, id, graph, mapPanel);
                    mapPanel.getChildren().add(el);
                }
            }
        }

        for (Node node : graph.getListNodes())
        {
            NodeButton nb = new NodeButton(node, graph, mapPanel);
            mapPanel.getChildren().add(nb);
        }


    }


}
