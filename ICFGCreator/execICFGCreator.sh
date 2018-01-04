#!/bin/bash

testerPath="./Tester"
currentPath=$(pwd)
echo "Current Path : $currentPath"
echo "PATH : $1"
files=$(cd "$1"; ls -l --color=none *.jar | awk -F " " '{print $NF}'; cd $currentPath)
for jarFile in $files
do
    echo "$jarFile"
    echo cp "$1/$jarFile" "$testerPath"
    # echo ./ICFGCreator.sh "$testerPath/$jarFile"
    # echo rm -rf "$testerPath/$jarFile"
    # ./ICFGCreator.sh "$testerPath/$jarFile"
    cp "$1/$jarFile" "$testerPath"
    jarFile=$(echo $jarFile | awk -F "/" '{print $NF}')
    ./ICFGCreator.sh "$testerPath/$jarFile"
    rm -rf $testerPath/$jarFile
done
