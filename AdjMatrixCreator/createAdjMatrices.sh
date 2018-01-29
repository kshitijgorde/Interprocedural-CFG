#!/bin/bash

if [ $# -lt 2 ]
then
	echo "USAGE : $0 <File with unique System APIs> <DOT File>"
	exit -1
fi

# strip system API file that contan ,' and { }
sed -i -e "s/[', }{]//g"  "$1"

pickleFileName=$(echo "$2" | awk -F "/" '{print $NF}' | awk -F "_" '{print $NF}' | cut -d'.' -f1) 
pickleFileName=$(echo "$pickleFileName""_ADJ.pickle") 
if [ $? == 0 ]
then 
	python adj.py "$1" "$2" "$pickleFileName"
else
	echo "Unable to read file with System APIs"
	exit -1
fi
