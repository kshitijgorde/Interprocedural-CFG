#!/bin/python

import re
import os
import sys
import pprint


pp = pprint.PrettyPrinter(indent=4)


#  Read dot file and store it as a list
f = open(sys.argv[1])    # file name
data = [x.strip() for x in f.readlines()]
f.close()
contents = " ".join(data)


#  Identify head and tail
head = str(contents.split('{')[0]) + '{'
contents = " ".join(contents.split('{')[1:])
tail = '}' + str(contents.split('}')[-1])
contents = contents.split('}')[:-1]
contents = " ".join([x.strip() for x in contents])
print (contents)

#  parse each node to find if they are using system APIs
contents = contents.replace('\\\"', '\'')
contents = contents.split('\"')
finalContents = ""
for node in contents:
    aaa = re.findall(r'(java|javax|com|org|sun|sunw|return|goto|->)', node)
    if len(aaa) > 0:
        print ("NODE : " + node + "-------------------> " + str(len(aaa)) +
               "____" + str(aaa))
        finalContents += '\"' + node + '\"' + " \n "
    else:
        print ("--- SKIPPED : " + node + " --------> " + str(len(aaa)) +
               "-----" + str(aaa))
print(head + " " + finalContents.strip('-> ').strip() + " " + tail)
