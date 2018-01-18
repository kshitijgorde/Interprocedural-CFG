#!/bin/bash
 

if [ $# -lt 1 ]
then
	echo "Usage: $0 <DIR of DOT files>"
	exit -1
fi  
if [ ! -d PrunedDotFiles ]
then 
	mkdir PrunedDotFiles
fi 
dotFiles=$(ls --color=none $1/*.dot)
for dot in $dotFiles 
do 
	fileName=$(echo $dot | rev | cut -d"/" -f1 | rev) 
	echo "Processing $1$dot"
	./processDotFile.sh "$1/$dot" "MOD_$fileName" 
	mv "MOD_$fileName" PrunedDotFiles/
	echo ""
done 
