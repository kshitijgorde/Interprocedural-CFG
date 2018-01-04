#!/bin/bash

if [ $# -lt 1 ]
then
    echo "Usage : $0 <JAR File>"
    exit 1
fi
jarFile=$1
tempFolder="TempDecompiledJar/$1"

echo "Working on .. $jarFile"

#Decompile JarFile
# echo "java -jar decompiler.jar $jarFile -o $tempFolder"
java -jar decompiler.jar "$jarFile" -o "$tempFolder" 2> error.txt
x=$(cat error.txt)
if [ -z "$x" ]
then
    inputJarDir=$tempFolder
    files=$(grep -inr "applet" "$inputJarDir" | grep -v "abstract" | grep "extends" | head -n1)
    fileName=$(echo "$files" | cut -d':' -f1)
    echo "File name : $fileName"
    # echo "Search results: $files"
    # filePath=$(echo "$fileName" | rev | cut -d'/' -f2- | rev)
    className=$(echo "$files" | awk -F 'class' '{print $NF}' )
    className=$(sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//' <<< "$className" | cut -d' ' -f1)
    # echo "File path : $filePath"
    # echo "Class Name : $className"
    pkg=$(grep "package" $fileName | awk -F " " '{print $NF}' | cut -d';' -f1 | sed -e 's/^[[:space:]]*//' -e 's/[[:space:]]*$//')
    echo "Package : $pkg"
    if [ -z "$pkg" ]
    then
        echo java -jar myIcfg.jar "$className" "DotFiles/$jarFile.dot" "$jarFile"
        java -jar myIcfg.jar "$className" "$jarFile.dot" "$jarFile"
    else
        echo java -jar myIcfg.jar "$pkg.$className" "DotFiles/$jarFile.dot" "$jarFile"
        java -jar myIcfg.jar "$pkg.$className" "$jarFile.dot" "$jarFile"
    fi
    rm -rf "$tempFolder"
else
    echo "Unable to process $fileName"
fi
echo "#############################################################################################################################################"
