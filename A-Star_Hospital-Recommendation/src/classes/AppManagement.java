package classes;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppManagement
{
    public static Graph graph;
    public static Pane mapPanel;
    public static TextArea routeTextField;

    public static void AppManagementInit(Graph g, Pane pane, TextArea tx)
    {
        graph = g;
        mapPanel = pane;
        routeTextField = tx;
        mapPanel.getChildren().clear();
        routeTextField.setText("");
    }

    public static List<Integer> astarPathFinding(int idSource, int idTarget)
    {
        try{
            List<Float> heu = getHeuristic(idTarget);

//            System.out.println("Heuristik untuk " + graph.getNode(idTarget).getName());
//            for (int i = 0; i < heu.size(); i++)
//            {
//                float h = heu.get(i);
//                h = (float) Math.round(h * 100) / 100;
//                System.out.println(graph.getNode(i).getName() + " : " + h + " m");
//            }
//            System.out.println("=====================================");

            List<Integer> stack = new ArrayList<>();
            List<List<Integer>> blackList = new ArrayList<>();
            List<List<Integer>> stackDis = new ArrayList<>();
            List<Float> dis = new ArrayList<>();

//            for (Float aFloat : heu) {
//                System.out.println(aFloat);
//            }

//            System.out.println(graph.getMapDistance(0, 1));

            stack.add(idSource);
            List<Integer> path = astar(idTarget, heu, dis, stackDis, stack, blackList);

            return path;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static List<List<Integer>> getRouteRecommendation(int idSource)
    {
        if (graph == null)
        {
            System.out.println("Graph is null");
            return null;
        }

        try {
            List<List<Integer>> routes = new ArrayList<>();
            List<classes.Node> nodes = graph.getListNodes();

            List<classes.Node> rsNodes = nodes.stream().filter(node -> node.getType().equals("rs")).collect(Collectors.toList());

            for (classes.Node rsNode : rsNodes)
            {
                List<Integer> route = astarPathFinding(idSource, rsNode.getId());
                if (route != null)
                {
                    routes.add(route);
                }
            }

            List<Float> distances = new ArrayList<>();
            for (List<Integer> route : routes) {
                float dis = graph.getMapRouteDistance(route);
                distances.add(dis);
            }

            if (routes.size() == 0)
            {
                return null;
            }
            else
            {
                int minIdx = 0;
                for (int i = 1; i < distances.size(); i++)
                {
                    if (distances.get(minIdx) > distances.get(i))
                        minIdx = i;
                }

                for (int i = 0; i < distances.size(); i++) {
                    int idx = i;
                    for (int j = i+1; j < distances.size(); j++) {
                        if (distances.get(idx) > distances.get(j))
                            idx = j;
                    }

                    List<Integer> temp = new ArrayList<>(routes.get(i));
                    routes.set(i, new ArrayList<>(routes.get(idx)));
                    routes.set(idx, temp);

                    Float tempDis = distances.get(i);
                    distances.set(i, distances.get(idx));
                    distances.set(idx, tempDis);

                }


                return routes;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void refreshMapRoute(List<Integer> routeId)
    {
        try {
            printGraphToWindow();
            List<Node> childs = mapPanel.getChildren();

            StringBuilder routeText = new StringBuilder();

            for (int i =0; i < routeId.size(); i++)
            {
                for (Node node : childs)
                {
                    if (node instanceof NodeButton)
                    {
                        NodeButton nb = (NodeButton) node;
                        if (nb.getNode().getId() == routeId.get(i))
                        {
                            if (nb.getNode().getType().equals("rs"))
                                nb.setStyle("-fx-background-color: #00FF00; -fx-font-weight: bold;");
                            else
                                nb.setStyle("-fx-background-color: #FF0000; -fx-font-weight: bold;");

                            routeText.append(nb.getNode().getName());
                            if (i != routeId.size()-1)
                                routeText.append(" --> ");
                        }
                    }
                    else if (node instanceof EdgeLine)
                    {
                        if (i != routeId.size()-1)
                        {
                            EdgeLine el = (EdgeLine) node;
                            int id1 = routeId.get(i);
                            int id2 = routeId.get(i+1);

                            if ((el.getId1() == id1 && el.getId2() == id2) || (el.getId1() == id2 && el.getId2() == id1))
                            {
                                el.setStroke(Color.ORANGE);
                            }
                        }
                    }
                }
            }
            if (routeId.size() > 0){
                float dis = graph.getMapRouteDistance(routeId);
                dis = (float) Math.round(dis * 100) / 100;

                String rsRec = graph.getNode(routeId.get(routeId.size()-1)).getName();

                String recText = "Recommendation : " + rsRec + ",   Distance : " + dis + "m \n";

                routeTextField.setText(recText + "Route: " + routeText.toString());
            }
            else{
                routeTextField.setText(routeText.toString());
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static List<Integer> astar(int idTarget, List<Float> heu, List<Float> distance, List<List<Integer>> stackDistance, List<Integer> stack, List<List<Integer>> blacklist)
    {
        if (stack.get(stack.size()-1) == idTarget)
            return stack;

        int idxFrom = stack.get(stack.size()-1);

        List<Integer> connect = graph.getListConnectedNode(idxFrom);
        int count = 0;
        for (int i = 0; i < connect.size(); i++) {
            int idxConnet = connect.get(i);

            if (idxInList(stack, idxConnet) == -1)
            {
                float gn = graph.getMapDistance(idxFrom, idxConnet);
                float hn = heu.get(idxConnet);
                float fn = gn + hn;

                List<Integer> tempStack = new ArrayList<>(stack);
                tempStack.add(idxConnet);

                if (idxListInList(stackDistance, tempStack) == -1 && idxListInList(blacklist, tempStack) == -1)
                {
                    stackDistance.add(tempStack);
                    distance.add(fn);
                    count++;
                }
            }
        }

        if (count == 0 && stackDistance.size() != 0)
        {
            int idxMinDistance = getIdxMinDistance(distance);
            blacklist.add(stackDistance.get(idxMinDistance));
            stackDistance.remove(idxMinDistance);
            distance.remove(idxMinDistance);
        }

        if (distance.size() == 0)
        {
            return null;
        }

        int idxMinDistance = getIdxMinDistance(distance);
//        System.out.println(distance.get(idxMinDistance));
        stack.clear();
        stack = new ArrayList<>(stackDistance.get(idxMinDistance));

        if (stack.get(stack.size()-1) == idTarget)
            return stack;

        return astar(idTarget, heu, distance, stackDistance, stack, blacklist);
    }

    public static List<Float> getHeuristic(int idTarget)
    {
        List<Float> heu = new ArrayList<>();
        for(int i = 0; i < graph.getNumOfNode(); i++)
        {
            int id1 = graph.getListNodes().get(i).getId();
            float dis = graph.getMapDistance(id1, idTarget);
            heu.add(dis);
        }

        return heu;
    }

    public static int getIdxMinDistance(List<Float> dis)
    {
        if (dis.size() == 0)
        {
            return -1;
        }
        else
        {
            int idxMin = 0;
            for (int i = 1; i < dis.size(); i++) {
                if (dis.get(idxMin) > dis.get(i))
                {
                    idxMin = i;
                }
            }

            return idxMin;
        }
    }
    
    public static int idxListInList(List<List<Integer>> ls, List<Integer> l)
    {
        for (int i = 0; i < ls.size(); i++) {
            if (isEqualList(ls.get(i), l))
                return i;
        }

        return -1;
    }

    public static boolean isEqualList(List<Integer> l1, List<Integer> l2)
    {
        if (l1.size() != l2.size())
            return false;
        for (int i = 0; i < l1.size(); i++) {
            if (!l1.get(i).equals(l2.get(i)))
                return false;
        }
        return true;
    }

    public static int idxInList(List<Integer> ls, int id)
    {
        for (int i = 0; i < ls.size(); i++) {
            if (ls.get(i) == id)
                return i;
        }

        return -1;
    }

    public static float getPosXRelative(float x)
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

    public static float getPosYRelative(float y)
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

    public static void printGraphToWindow()
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
                    EdgeLine el = new EdgeLine(i, id);
                    float xPos = (float) (el.getStartX() + el.getEndX()) /2;
                    float yPos = (float) (el.getStartY() + el.getEndY()) /2;


                    float dis = graph.getMapDistance(i, id);
                    dis = (float) Math.round(dis * 100) / 100;

                    Label disLabel = new Label(dis + "m");
                    disLabel.setLayoutX(xPos);
                    disLabel.setLayoutY(yPos);
                    disLabel.setStyle("-fx-background-color: #42eff5; -fx-font-weight: bold; -fx-text-fill: #28e03b;");
                    mapPanel.getChildren().add(el);
                    mapPanel.getChildren().add(disLabel);
                }
            }
        }

        for (classes.Node node : graph.getListNodes())
        {
            NodeButton nb = new NodeButton(node);
            mapPanel.getChildren().add(nb);
        }
    }
}
