#!/bin/bash 

if [ $# -lt 2 ]
then 
	echo "USAGE: $0 <File with unique System APIs> <Folder with DOT files>"
	exit -1
fi 

files=$(ls --color=none "$2")
for file in $files
do 
	echo "Creating Adj Matrix for $file"
	./createAdjMatrices.sh "$1" "$2/$file" 
done
mkdir AdjMatrices
mv *,pickle AdjMatrices
