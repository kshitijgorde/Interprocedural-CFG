#!/bin/bash

testerPath="./Tester"
currentPath=$(pwd)
files=$(cd $1; ls -l --color=none "$1"/*.jar | awk -F " " '{print $NF}'; cd currentPath)
for jarFile in $files
do
    # echo "$jarFile"
	# echo cp "$jarFile" "$testerPath"
    # echo ./ICFGCreator.sh "$testerPath/$jarFile"
    # echo rm -rf "$testerPath/$jarFile"
    # ./ICFGCreator.sh "$testerPath/$jarFile"
	cp "$jarFile" "$testerPath"
    jarFile=$(echo $jarFile | awk -F "/" '{print $NF}')
    rm -rf $testerPath/$jarFile
done