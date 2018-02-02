#!/bin/python

import numpy as np
import matplotlib as mlt
from PIL import Image
import sys
import os


if len(sys.argv) < 1:
    print ("USAGE: " + sys.argv[0] +
           " <Pickle File>")
    sys.exit(-1)

# Read file
f = sys.argv[1]
data = np.load(f)
a = np.array(data)

img = Image.new('1',(519,519))
pixels = img.load()

for i in range(img.size[0]):
	for j in range(img.size[0]):
		pixels[i,j] = data[i][j]

f1=os.path.splitext(os.path.basename(f))[0]

#img.show()
img.save(f1+'.jpg')
#img.save('/result'+f+'_img.jpeg')
print (f+"is done")
