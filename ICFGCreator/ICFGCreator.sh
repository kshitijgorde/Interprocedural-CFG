#!/bin/bash

if [ $# -lt 1 ]
then
    echo "Usage : $0 <JAR File>"
    exit 1
fi
jarFile=$1
tempFolder="TempDecompiledJar/$1"


#Decompile JarFile
echo "java -jar decompiler.jar $jarFile -o $tempFolder"
java -jar decompiler.jar "$jarFile" -o "$tempFolder"

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
pkg=$(grep "package" $fileName | awk -F " " '{print $NF}' | cut -d';' -f1)
# echo "Package : $pkg"
java -jar myIcfg.jar "$pkg.$className" "$jarFile.dot" "$jarFile"
# echo java -jar myIcfg.jar "$pkg.$className" "DotFiles/$jarFile.dot" "$jarFile"
rm -rf $tempFolder
