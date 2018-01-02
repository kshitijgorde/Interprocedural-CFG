// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

import org.apache.xerces.utils.QName;

public final class ContentLeafNameTypeVector
{
    public QName[] leafNames;
    public int[] leafTypes;
    public int leafCount;
    
    public ContentLeafNameTypeVector() {
        this.leafNames = null;
        this.leafTypes = null;
        this.leafCount = 0;
    }
    
    public ContentLeafNameTypeVector(final QName[] array, final int[] array2, final int n) {
        this.leafNames = null;
        this.leafTypes = null;
        this.leafCount = 0;
        this.setValues(array, array2, n);
    }
    
    public ContentLeafNameTypeVector(final ContentLeafNameTypeVector contentLeafNameTypeVector) {
        this(contentLeafNameTypeVector.leafNames, contentLeafNameTypeVector.leafTypes, contentLeafNameTypeVector.leafCount);
    }
    
    public void setValues(final QName[] array, final int[] array2, final int leafCount) {
        if (leafCount > 0) {
            this.leafNames = new QName[leafCount];
            this.leafTypes = new int[leafCount];
            this.leafCount = leafCount;
            System.arraycopy(array, 0, this.leafNames, 0, leafCount);
            System.arraycopy(array2, 0, this.leafTypes, 0, leafCount);
        }
    }
}
