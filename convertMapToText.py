name = "map2"
fileNodeName = name + ".txt"
fileEdgeName = name + "_edge.txt"

fileNode = open("A-Star_Hospital-Recommendation/src/map/" + fileNodeName, "w")
fileEdge = open("A-Star_Hospital-Recommendation/src/map/" + fileEdgeName, "w")


scale = 100000
nodes = [
    [-6.900177, 107.599281, "Node1"], #0
    [-6.897587, 107.599340, "Poliklinik RHS"], #1
    [-6.896220, 107.599383, "Node2"], #2
    [-6.896050, 107.598702, "RS Unpad"], #3
    [-6.895941, 107.598225, "Node3"], #4
    [-6.896772, 107.597308, "Node4"], #5
    [-6.900207, 107.597396, "Node5"] #6
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
    nodeName = node[2]
    fileNode.write(str(x) + "," + str(y) + "," + nodeName + "," + "\n")


fileEdge.write(str(numOfEdge) + "," + "\n")
for edge in edges:
    id1 = edge[0]
    id2 = edge[1]
    fileEdge.write(str(id1) + "," + str(id2) + "," + "\n")