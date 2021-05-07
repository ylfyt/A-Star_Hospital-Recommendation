package app;

import classes.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class MainWindowController {

    @FXML
    private Pane mapPanel;
    @FXML
    private TextField routeTextField;

    public void initGraph()
    {
        String fileName = "map2";
        Graph graph = GraphConverter.textToGraph(fileName);
        AppManagement.AppManagementInit(graph, mapPanel, routeTextField);
    }

    public void handleBrowseButton()
    {
        System.out.println("Browse");
        initGraph();
        AppManagement.printGraphToWindow();
    }

    public void handleRefreshButton()
    {
        System.out.println("Refresh");
        AppManagement.printGraphToWindow();
        routeTextField.setText("");
    }


}
