// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.dtd;

public class MixedContentModel implements XMLContentModel
{
    private int fCount;
    private int[] fChildren;
    
    public MixedContentModel(final int fCount, final int[] array) throws CMException {
        this.fCount = fCount;
        System.arraycopy(array, 0, this.fChildren = new int[this.fCount], 0, fCount);
    }
    
    public int validateContent(final int n, final int[] array) throws Exception {
        for (int i = 0; i < n; ++i) {
            final int n2 = array[i];
            if (n2 != -1) {
                int n3;
                for (n3 = 0; n3 < this.fCount && n2 != this.fChildren[n3]; ++n3) {}
                if (n3 == this.fCount) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public int whatCanGoHere(final boolean b, final InsertableElementsInfo insertableElementsInfo) throws Exception {
        for (int i = insertableElementsInfo.insertAt; i < insertableElementsInfo.childCount; ++i) {
            insertableElementsInfo.curChildren[i] = insertableElementsInfo.curChildren[i + 1];
        }
        --insertableElementsInfo.childCount;
        final int validateContent = this.validateContent(insertableElementsInfo.childCount, insertableElementsInfo.curChildren);
        if (validateContent != -1 && validateContent < insertableElementsInfo.insertAt) {
            return validateContent;
        }
        insertableElementsInfo.canHoldPCData = true;
        insertableElementsInfo.isValidEOC = true;
        insertableElementsInfo.resultsCount = this.fCount;
        if (insertableElementsInfo.results == null || insertableElementsInfo.results.length < insertableElementsInfo.resultsCount) {
            insertableElementsInfo.results = new boolean[insertableElementsInfo.resultsCount];
        }
        if (insertableElementsInfo.possibleChildren == null || insertableElementsInfo.possibleChildren.length < insertableElementsInfo.resultsCount) {
            insertableElementsInfo.possibleChildren = new int[insertableElementsInfo.resultsCount];
        }
        boolean b2 = true;
        if (b && validateContent < insertableElementsInfo.childCount) {
            b2 = false;
        }
        for (int j = 0; j < this.fCount; ++j) {
            insertableElementsInfo.possibleChildren[j] = this.fChildren[j];
            insertableElementsInfo.results[j] = b2;
        }
        return -1;
    }
}
