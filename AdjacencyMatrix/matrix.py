'''
	This python program reads the 'SystemCallsFromPrunedDotFiles.txt' file in the AdjacencyMatrix folder, separates each function call and amkes a adjacency matrix for all the unique function calls

'''
import numpy as np



fname = 'SystemCallsFromPrunedDotFiles.txt'

#cleaning the file with unique calls from commas, whitespace and quotes and wrtitng in another file
with open(fname) as f, open('cleaned'+ fname,'w') as fo:
	line = f.readline()
	while line:
		line = line.strip().replace("'","").replace(',','')
		fo.write(line+'\n')
		line=f.readline()

f.close()
fo.close()


#transfering the function calls from the cleaned .txt file to a list 
myFile =  open('cleaned'+ fname,'r')
temp = [line[:-1] for line in myFile]

#transfering the list to a numpy array
lst = np.array(temp)

#list size - 497 in this case
n = lst.size

#creating a n*n matrix which will be our adjacency matrix for all the PrunedDotFiles
matrix = np.zeros((n,n))
print(matrix.shape)









