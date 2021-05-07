name = "map2"
fileNodeName = name + ".txt"
fileEdgeName = name + "_edge.txt"

fileNode = open("A-Star_Hospital-Recommendation/src/map/" + fileNodeName, "w")
fileEdge = open("A-Star_Hospital-Recommendation/src/map/edge/" + fileEdgeName, "w")


scale = 100000
nodes = [
    [-6.900177, 107.599281, "none", "Node"], #0
    [-6.897587, 107.599340, "rs", "Poliklinik RHS"], #1
    [-6.896220, 107.599383, "none", "Node"], #2
    [-6.896050, 107.598702, "rs", "RS Unpad"], #3
    [-6.895941, 107.598225, "none", "Node"], #4
    [-6.896772, 107.597308, "none", "Node"], #5
    [-6.900207, 107.597396, "none", "Node"], #6
    [-6.893059, 107.597044, "none", "Node"], #7
    [-6.892846, 107.595124, "none", "Node"], #8
    [-6.892400, 107.594747, "rs", "Puskesmas Sukajadi"], #9
    [-6.894535, 107.597161, "none", "Node"], #10
    [-6.893973, 107.595463, "none", "Node"] #11
]

edges = [
    [0, 1],
    [1, 2],
    [2, 3],
    [3, 4],
    [4, 5],
    [5, 6],
    [6, 0],
    [5, 10],
    [10, 7],
    [7, 8],
    [8, 9],
    [10, 11],
    [11, 8]
]


numOfNode = len(nodes)
numOfEdge = len(edges)

fileNode.write(str(scale) + "," + "\n")
fileNode.write(str(numOfNode) + "," + "\n")
for node in nodes:
    x = node[0]
    y = node[1]
    tp = node[2]
    nodeName = node[3]
    fileNode.write(str(x) + "," + str(y) + "," + tp + "," + nodeName + "," + "\n")


fileEdge.write(str(numOfEdge) + "," + "\n")
for edge in edges:
    id1 = edge[0]
    id2 = edge[1]
    fileEdge.write(str(id1) + "," + str(id2) + "," + "\n")