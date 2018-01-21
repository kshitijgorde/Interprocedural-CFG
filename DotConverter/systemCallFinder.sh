#!/bin/bash 

if [ $# -le 2 ]
then
	echo "USAGE: $0 <Folder of DOT files>"
	exit -1
fi

cat $1/*.dot > localDot.txt
sed '/digraph "" {/d' localDot.txt | sed '/}/d' | sed 's/->/ -> /'  > temp.txt
awk -F '->' '{for (i=1; i<=NF; i++) print $i}'  temp.txt  > temp1.txt
./systemCallIdentifier.py temp.txt
rm localDot.txt temp.txt temp1.txt

