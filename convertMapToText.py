name = "map2"
fileNodeName = name + ".txt"
fileEdgeName = name + "_edge.txt"

fileNode = open("A-Star_Hospital-Recommendation/src/map/" + fileNodeName, "w")
fileEdge = open("A-Star_Hospital-Recommendation/src/map/edge/" + fileEdgeName, "w")


scale = 100000
nodes = [
    [-6.900177, 107.599281, "none", "Node"], #0
    [-6.897587, 107.599340, "rs", "Poliklinik RSHS"], #1
    [-6.896220, 107.599383, "none", "Node"], #2
    [-6.896050, 107.598702, "rs", "RS Unpad"], #3
    [-6.895941, 107.598225, "none", "Node"], #4
    [-6.896772, 107.597308, "none", "Node"], #5
    [-6.900207, 107.597396, "none", "Node"], #6
    [-6.893059, 107.597044, "none", "Node"], #7
    [-6.892846, 107.595124, "none", "Node"], #8
    [-6.892400, 107.594747, "rs", "Puskesmas Sukajadi"], #9
    [-6.894535, 107.597161, "none", "Node"], #10
    [-6.893973, 107.595463, "none", "Node"], #11
    [-6.900140, 107.595924, "none", "Node"], #12
    [-6.898659, 107.594079, "none", "Node"], #13
    [-6.896294, 107.595066, "none", "Node"], #14
    [-6.896436, 107.596048, "none", "Node"], #15
    [-6.895749, 107.601450, "none", "Node"], #16
    [-6.894066, 107.598703, "none", "Node"], #17
    [-6.892065, 107.599309, "none", "Node"], #18
    [-6.890828, 107.599583, "none", "Node"], #19
    [-6.895802, 107.602308, "none", "Node"],
    [-6.891520, 107.602447, "none", "Node"],
    [-6.891499, 107.603262, "rs", "Rumah Sakit Advent Bandung"],
    [-6.891691, 107.604228, "none", "Node"],
    [-6.895717, 107.603938, "none", "Node"],
    [-6.900183, 107.604146, "none", "Node"],
    [-6.900279, 107.602258, "none", "Node"]
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
    [11, 8],
    [6, 12],
    [12, 13],
    [13, 14],
    [14, 11],
    [14, 15],
    [5, 15],
    [2, 16],
    [16, 17],
    [7, 17],
    [17, 18],
    [18, 19],
    [16, 20],
    [20, 21],
    [19, 21],
    [21, 22],
    [22, 23],
    [23, 24],
    [20, 24],
    [24, 25],
    [25, 26],
    [26, 20],
    [0, 26],
    [4, 17]
]


numOfNode = len(nodes)
numOfEdge = len(edges)

fileNode.write(str(scale) + "," + "\n")
fileNode.write(str(numOfNode) + "," + "\n")
count = 1
for node in nodes:
    x = node[0]
    y = node[1]
    tp = node[2]
    nodeName = node[3]
    if (tp == "none"):
        nodeName += " " + str(count)
        count += 1
    fileNode.write(str(x) + "," + str(y) + "," + tp + "," + nodeName + "," + "\n")


fileEdge.write(str(numOfEdge) + "," + "\n")
for edge in edges:
    id1 = edge[0]
    id2 = edge[1]
    fileEdge.write(str(id1) + "," + str(id2) + "," + "\n")