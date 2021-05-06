name = "map2"
fileNodeName = name + ".txt"
fileEdgeName = name + "_edge.txt"

fileNode = open("A-Star_Hospital-Recommendation/src/map/" + fileNodeName, "w")
fileEdge = open("A-Star_Hospital-Recommendation/src/map/" + fileEdgeName, "w")


scale = 100000
nodes = [
    [-6.900177, 107.599281, "none", "Node1"], #0
    [-6.897587, 107.599340, "rs", "Poliklinik RHS"], #1
    [-6.896220, 107.599383, "none", "Node2"], #2
    [-6.896050, 107.598702, "rs", "RS Unpad"], #3
    [-6.895941, 107.598225, "none", "Node3"], #4
    [-6.896772, 107.597308, "none", "Node4"], #5
    [-6.900207, 107.597396, "none", "Node5"] #6
]

edges = [
    [0, 1],
    [1, 2],
    [2, 3],
    [3, 4],
    [4, 5],
    [5, 6],
    [6, 0]
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