package app;

import classes.Graph;
import classes.GraphConverter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
        primaryStage.setTitle("A*");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        Graph graph = GraphConverter.textToGraph("map2");
        if (graph != null)
            graph.printGraph();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
