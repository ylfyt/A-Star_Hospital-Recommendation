package app;

import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class MainWindowController {

    @FXML
    private Pane mapPanel;
    @FXML
    private TextArea routeTextArea;
    @FXML
    private TextField filePathText;

    public void initGraph(String fileName)
    {
//        String fileName = "map2";
        Graph graph = GraphConverter.textToGraph(fileName);
        AppManagement.AppManagementInit(graph, mapPanel, routeTextArea);
    }

    public void handleBrowseButton(ActionEvent event)
    {
        System.out.println("Browse");

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        fileChooser.setInitialDirectory(new File(currentPath));
        //Show save file dialog
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if(file != null){
//            System.out.println(file.getName());
            filePathText.setText(file.getPath());
            initGraph(file.getName());
            AppManagement.printGraphToWindow();
//            List<Integer> rute = AppManagement.astarPathFinding(13, 22);
//            AppManagement.refreshMapRoute(rute);
        }
    }

    public void handleRefreshButton()
    {
        System.out.println("Refresh");
        AppManagement.printGraphToWindow();
        routeTextArea.setText("");
    }


}
