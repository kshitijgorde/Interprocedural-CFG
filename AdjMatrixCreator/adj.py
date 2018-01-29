#!/bin/python

import re
import sys
import pprint
import pickle

if len(sys.argv) < 4:
    print ("USAGE: " + sys.argv[0] +
           " <File with unique systemcalls> <DOT File> " +
           "<File to write ADJACENCY MATRIX>")
    sys.exit(-1)

# Read file
f = open(sys.argv[2])
data = f.readlines()
f.close()

# Compile pattern for better performance
pattern = re.compile("((com|java|java|javax|org|sun|sunw)\.[\w+\.?]+)", re.I)

pp = pprint.PrettyPrinter(indent=4, depth=3, width=100)
systemCalls = []

# Create empty adjacency matrix with System APIs
f = open(sys.argv[1])
systemAPIs = f.readlines()
f.close()

systemAPIs = [x.strip() for x in systemAPIs]
tempAdjMatrix = {}
for api in systemAPIs:
    tempAdjMatrix[api.strip()] = [0]*len(systemAPIs)

for line in data:
    temp = line.strip().split("->")
    if len(temp) > 1:
        lhs = temp[0]
        rhs = temp[1]
        lhsPattern = re.findall(pattern, lhs)
        rhsPattern = re.findall(pattern, rhs)
        for l1, _ in lhsPattern:
            for r1, _ in rhsPattern:
                index = systemAPIs.index(r1)
                tempAdjMatrix[l1][index] = 1
                # For weighted adj matrix, use the line below
                # tempAdjMatrix[l1][index] += 1
adjMatrix = []
for api in systemAPIs:
    adjMatrix.append(tempAdjMatrix[api])
f = open(sys.argv[3], "wb")
pickle.dump(adjMatrix, f, protocol=pickle.HIGHEST_PROTOCOL)
f.close()
# print ("systemAPIs : " + str(len(systemAPIs)))
# print ("tempAdjMatrix : " + str(len(tempAdjMatrix.keys())))
# t1 = list(tempAdjMatrix.keys())
# for x in range(519):
#     print (systemAPIs[x]+"\t_______\t"+t1[x])
# print (systemAPIs == t1)
