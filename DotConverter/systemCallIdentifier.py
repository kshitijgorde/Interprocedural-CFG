#!/bin/python

import re
import sys
import pprint

if len(sys.argv) < 2:
    print ("USAGE: " + sys.argv[0] + " <File with all systemcalls>")
    sys.exit(-1)

# Read file
f = open(sys.argv[1])
data = f.readlines()
f.close()

# Compile pattern for better performance
pattern = re.compile("((com|java|java|javax|org|sun|sunw)\.[\w+\.?]+)", re.I)

pp = pprint.PrettyPrinter(indent=4)

systemCalls = []

# Parse each line to find system calls
for line in data:
    temp = re.findall(pattern, line.strip())
#    print(line)
#    pp.pprint(temp)
    for x in temp:
        systemCalls.append(x[0])
#    print("############################")


pp.pprint(set(systemCalls))
