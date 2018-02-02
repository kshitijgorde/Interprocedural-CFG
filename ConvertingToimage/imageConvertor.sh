#!/bin/bash 

if [ $# -lt 1 ]
then
        echo "USAGE: $0 <Folder with pickle files>"
        exit -1
fi

files=$(ls --color=none "$1")

for file in $files
do
	echo "Processing file: $1/$file"
	python3 pickleToNumpy.py "$1/$file"
done



