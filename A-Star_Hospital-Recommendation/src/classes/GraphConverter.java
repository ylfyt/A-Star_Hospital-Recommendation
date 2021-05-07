package classes;

import java.io.File;
import java.util.Scanner;

public class GraphConverter {

    public static String path = "src/map/";
    public static String format = ".txt";

    public static Graph textToGraph(String fileName){
        try {
            Scanner scNode = new Scanner(new File(path + fileName));
            Scanner scEdge = new Scanner(new File(path + "edge/"+ fileName.substring(0, fileName.length()-4) + "_edge" + format));

            String scaleTemp = scNode.nextLine().split(",")[0];
            String numOfNodeTemp = scNode.nextLine().split(",")[0];

            float scale = Float.parseFloat(scaleTemp);
            int numOfNode = Integer.parseInt(numOfNodeTemp);
            Graph graph = new Graph(scale);

            int numOfdataNode = 4;
            for (int i = 0; i < numOfNode; i++) {
                String[] data = scNode.nextLine().split(",");
                if (data.length >= numOfdataNode)
                {
                    String xTemp = data[0];
                    String yTemp = data[1];
                    String typeTemp = data[2];
                    String nameTemp = data[3];

                    float x = Float.parseFloat(xTemp);
                    float y = Float.parseFloat(yTemp);
                    String type = typeTemp;
                    String name = nameTemp;

                    graph.addNode(x, y, type, name);
                }
            }

            String numOfEdgeTemp = scEdge.nextLine().split(",")[0];
            int numOfEdge = Integer.parseInt(numOfEdgeTemp);

            int numOfdataEdge = 2;
            for (int i = 0; i < numOfEdge; i++) {
                String data[] = scEdge.nextLine().split(",");
                if (data.length >= numOfdataEdge)
                {
                    String id1Temp = data[0];
                    String id2Temp = data[1];

                    int id1 = Integer.parseInt(id1Temp);
                    int id2 = Integer.parseInt(id2Temp);

                    graph.addEdge(id1, id2);
                }
            }

            return graph;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
