import re
import sys
import pprint 
pp = pprint.PrettyPrinter(indent=4)
f = open(sys.argv[1])	# file name
data = [x.strip() for x in f.readlines()]
f.close()
contents = " ".join(data)
head = str(contents.split('{')[0]) + '{'
contents = " ".join(contents.split('{')[1:]) 
tail = '}' + str(contents.split('}')[-1])
contents = contents.split('}')[:-1]
contents = " ".join([x.strip() for x in contents])
contents = contents.replace('\\\"' , '\'')
print ('--------------------------------')
contents = contents.split('\"')
print (contents)
finalContents = ""
for node in contents:
	aaa = re.findall(r'(java|javax|com|org|sun|sunw|return|goto)', node) 
	if len(aaa) > 0:
		print ("NODE : " + node + "-------------------> " + str(len(aaa)) + "____" + str(aaa))
	
		finalContents += '\"' + node + '\"' +" -> "
print(head + " " + finalContents.strip('-> ').strip()+ " " + tail)
