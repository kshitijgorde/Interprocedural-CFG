// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.dtd;

public class SimpleContentModel implements XMLContentModel
{
    int fFirstChild;
    int fSecondChild;
    int fOp;
    
    public SimpleContentModel(final int fFirstChild, final int fSecondChild, final int fOp) {
        this.fFirstChild = fFirstChild;
        this.fSecondChild = fSecondChild;
        this.fOp = fOp;
    }
    
    public int validateContent(final int n, final int[] array) throws Exception {
        switch (this.fOp) {
            case 0: {
                if (n == 0) {
                    return 0;
                }
                if (array[0] != this.fFirstChild) {
                    return 0;
                }
                if (n > 1) {
                    return 1;
                }
                break;
            }
            case 1: {
                if (n == 1 && array[0] != this.fFirstChild) {
                    return 0;
                }
                if (n > 1) {
                    return 1;
                }
                break;
            }
            case 2: {
                if (n > 0) {
                    for (int i = 0; i < n; ++i) {
                        if (array[i] != this.fFirstChild) {
                            return i;
                        }
                    }
                    break;
                }
                break;
            }
            case 3: {
                if (n == 0) {
                    return 0;
                }
                for (int j = 0; j < n; ++j) {
                    if (array[j] != this.fFirstChild) {
                        return j;
                    }
                }
                break;
            }
            case 4: {
                if (n == 0) {
                    return 0;
                }
                if (array[0] != this.fFirstChild && array[0] != this.fSecondChild) {
                    return 0;
                }
                if (n > 1) {
                    return 1;
                }
                break;
            }
            case 5: {
                if (n == 2) {
                    if (array[0] != this.fFirstChild) {
                        return 0;
                    }
                    if (array[1] != this.fSecondChild) {
                        return 1;
                    }
                    break;
                }
                else {
                    if (n > 2) {
                        return 2;
                    }
                    return n;
                }
                break;
            }
            default: {
                throw new CMException(8);
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
        insertableElementsInfo.canHoldPCData = false;
        if (this.fOp == 0 || this.fOp == 1 || this.fOp == 2 || this.fOp == 3) {
            insertableElementsInfo.resultsCount = 1;
        }
        else {
            if (this.fOp != 4 && this.fOp != 5) {
                throw new CMException(8);
            }
            insertableElementsInfo.resultsCount = 2;
        }
        if (insertableElementsInfo.results == null || insertableElementsInfo.results.length < insertableElementsInfo.resultsCount) {
            insertableElementsInfo.results = new boolean[insertableElementsInfo.resultsCount];
        }
        if (insertableElementsInfo.possibleChildren == null || insertableElementsInfo.possibleChildren.length < insertableElementsInfo.resultsCount) {
            insertableElementsInfo.possibleChildren = new int[insertableElementsInfo.resultsCount];
        }
        insertableElementsInfo.possibleChildren[0] = this.fFirstChild;
        insertableElementsInfo.results[0] = false;
        if (insertableElementsInfo.resultsCount == 2) {
            insertableElementsInfo.possibleChildren[1] = this.fSecondChild;
            insertableElementsInfo.results[1] = false;
        }
        insertableElementsInfo.isValidEOC = false;
        switch (this.fOp) {
            case 0:
            case 1: {
                if (insertableElementsInfo.childCount == 0) {
                    insertableElementsInfo.results[0] = true;
                }
                else if (insertableElementsInfo.childCount > 0 && !b && insertableElementsInfo.insertAt == 0) {
                    insertableElementsInfo.results[0] = true;
                }
                if (this.fOp != 0) {
                    insertableElementsInfo.isValidEOC = true;
                    break;
                }
                if (insertableElementsInfo.insertAt == 0) {
                    insertableElementsInfo.isValidEOC = true;
                    break;
                }
                break;
            }
            case 2:
            case 3: {
                insertableElementsInfo.results[0] = true;
                if (this.fOp == 2 || insertableElementsInfo.insertAt > 0) {
                    insertableElementsInfo.isValidEOC = true;
                    break;
                }
                break;
            }
            case 4: {
                if (insertableElementsInfo.insertAt == 0 && !b && insertableElementsInfo.childCount == 0) {
                    insertableElementsInfo.results[0] = true;
                    insertableElementsInfo.results[1] = true;
                }
                if (insertableElementsInfo.insertAt == 1) {
                    insertableElementsInfo.isValidEOC = true;
                    break;
                }
                break;
            }
            case 5: {
                if (insertableElementsInfo.insertAt == 0) {
                    if (b) {
                        if (insertableElementsInfo.childCount == 1) {
                            insertableElementsInfo.results[0] = (insertableElementsInfo.curChildren[0] == this.fSecondChild);
                        }
                    }
                    else {
                        insertableElementsInfo.results[0] = true;
                    }
                }
                else if (insertableElementsInfo.insertAt == 1 && (!b || insertableElementsInfo.childCount == 1)) {
                    insertableElementsInfo.results[1] = true;
                }
                if (insertableElementsInfo.insertAt == 2) {
                    insertableElementsInfo.isValidEOC = true;
                    break;
                }
                break;
            }
            default: {
                throw new CMException(8);
            }
        }
        return -1;
    }
}
