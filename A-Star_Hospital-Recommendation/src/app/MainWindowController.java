package app;

import classes.Graph;
import classes.GraphConverter;

public class MainWindowController {

    private Graph graph;

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
    }
}
