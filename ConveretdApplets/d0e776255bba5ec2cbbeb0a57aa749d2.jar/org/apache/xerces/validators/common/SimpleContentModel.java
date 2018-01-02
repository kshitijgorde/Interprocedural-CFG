// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

import org.apache.xerces.validators.schema.SubstitutionGroupComparator;
import org.apache.xerces.utils.QName;

public class SimpleContentModel implements XMLContentModel
{
    private QName fFirstChild;
    private QName fSecondChild;
    private int fOp;
    private boolean fDTD;
    private SubstitutionGroupComparator comparator;
    
    public SimpleContentModel(final QName qName, final QName qName2, final int n) {
        this(qName, qName2, n, false);
    }
    
    public SimpleContentModel(final QName values, final QName values2, final int fOp, final boolean fdtd) {
        this.fFirstChild = new QName();
        this.fSecondChild = new QName();
        this.comparator = null;
        this.fFirstChild.setValues(values);
        if (values2 != null) {
            this.fSecondChild.setValues(values2);
        }
        else {
            this.fSecondChild.clear();
        }
        this.fOp = fOp;
        this.fDTD = fdtd;
    }
    
    public int validateContent(final QName[] array, final int n, final int n2) throws Exception {
        switch (this.fOp) {
            case 0: {
                if (n2 == 0) {
                    return 0;
                }
                if (this.fDTD) {
                    if (array[n].rawname != this.fFirstChild.rawname) {
                        return 0;
                    }
                }
                else if (array[n].uri != this.fFirstChild.uri || array[n].localpart != this.fFirstChild.localpart) {
                    return 0;
                }
                if (n2 > 1) {
                    return 1;
                }
                break;
            }
            case 1: {
                if (n2 == 1) {
                    if (this.fDTD) {
                        if (array[n].rawname != this.fFirstChild.rawname) {
                            return 0;
                        }
                    }
                    else if (array[n].uri != this.fFirstChild.uri || array[n].localpart != this.fFirstChild.localpart) {
                        return 0;
                    }
                }
                if (n2 > 1) {
                    return 1;
                }
                break;
            }
            case 2: {
                if (n2 <= 0) {
                    break;
                }
                if (this.fDTD) {
                    for (int i = 0; i < n2; ++i) {
                        if (array[n + i].rawname != this.fFirstChild.rawname) {
                            return i;
                        }
                    }
                    break;
                }
                for (int j = 0; j < n2; ++j) {
                    if (array[n + j].uri != this.fFirstChild.uri || array[n + j].localpart != this.fFirstChild.localpart) {
                        return j;
                    }
                }
                break;
            }
            case 3: {
                if (n2 == 0) {
                    return 0;
                }
                if (this.fDTD) {
                    for (int k = 0; k < n2; ++k) {
                        if (array[n + k].rawname != this.fFirstChild.rawname) {
                            return k;
                        }
                    }
                    break;
                }
                for (int l = 0; l < n2; ++l) {
                    if (array[n + l].uri != this.fFirstChild.uri || array[n + l].localpart != this.fFirstChild.localpart) {
                        return l;
                    }
                }
                break;
            }
            case 4: {
                if (n2 == 0) {
                    return 0;
                }
                if (this.fDTD) {
                    if (array[n].rawname != this.fFirstChild.rawname && array[n].rawname != this.fSecondChild.rawname) {
                        return 0;
                    }
                }
                else if ((array[n].uri != this.fFirstChild.uri || array[n].localpart != this.fFirstChild.localpart) && (array[n].uri != this.fSecondChild.uri || array[n].localpart != this.fSecondChild.localpart)) {
                    return 0;
                }
                if (n2 > 1) {
                    return 1;
                }
                break;
            }
            case 5: {
                if (n2 == 2) {
                    if (this.fDTD) {
                        if (array[n].rawname != this.fFirstChild.rawname) {
                            return 0;
                        }
                        if (array[n + 1].rawname != this.fSecondChild.rawname) {
                            return 1;
                        }
                        break;
                    }
                    else {
                        if (array[n].uri != this.fFirstChild.uri || array[n].localpart != this.fFirstChild.localpart) {
                            return 0;
                        }
                        if (array[n + 1].uri != this.fSecondChild.uri || array[n + 1].localpart != this.fSecondChild.localpart) {
                            return 1;
                        }
                        break;
                    }
                }
                else {
                    if (n2 > 2) {
                        return 2;
                    }
                    return n2;
                }
                break;
            }
            default: {
                throw new CMException(8);
            }
        }
        return -1;
    }
    
    public int validateContentSpecial(final QName[] array, final int n, final int n2) throws Exception {
        if (this.comparator == null) {
            return this.validateContent(array, n, n2);
        }
        switch (this.fOp) {
            case 0: {
                if (n2 == 0) {
                    return 0;
                }
                if ((array[n].uri != this.fFirstChild.uri || array[n].localpart != this.fFirstChild.localpart) && !this.comparator.isEquivalentTo(array[n], this.fFirstChild)) {
                    return 0;
                }
                if (n2 > 1) {
                    return 1;
                }
                break;
            }
            case 1: {
                if (n2 == 1 && (array[n].uri != this.fFirstChild.uri || array[n].localpart != this.fFirstChild.localpart) && !this.comparator.isEquivalentTo(array[n], this.fFirstChild)) {
                    return 0;
                }
                if (n2 > 1) {
                    return 1;
                }
                break;
            }
            case 2: {
                if (n2 > 0) {
                    for (int i = 0; i < n2; ++i) {
                        if ((array[n + i].uri != this.fFirstChild.uri || array[n + i].localpart != this.fFirstChild.localpart) && !this.comparator.isEquivalentTo(array[n + i], this.fFirstChild)) {
                            return i;
                        }
                    }
                    break;
                }
                break;
            }
            case 3: {
                if (n2 == 0) {
                    return 0;
                }
                for (int j = 0; j < n2; ++j) {
                    if ((array[n + j].uri != this.fFirstChild.uri || array[n + j].localpart != this.fFirstChild.localpart) && !this.comparator.isEquivalentTo(array[n + j], this.fFirstChild)) {
                        return j;
                    }
                }
                break;
            }
            case 4: {
                if (n2 == 0) {
                    return 0;
                }
                if ((array[n].uri != this.fFirstChild.uri || array[n].localpart != this.fFirstChild.localpart) && (array[n].uri != this.fSecondChild.uri || array[n].localpart != this.fSecondChild.localpart) && !this.comparator.isEquivalentTo(array[n], this.fFirstChild) && !this.comparator.isEquivalentTo(array[n], this.fSecondChild)) {
                    return 0;
                }
                if (n2 > 1) {
                    return 1;
                }
                break;
            }
            case 5: {
                if (n2 == 2) {
                    if ((array[n].uri != this.fFirstChild.uri || array[n].localpart != this.fFirstChild.localpart) && !this.comparator.isEquivalentTo(array[n], this.fFirstChild)) {
                        return 0;
                    }
                    if ((array[n + 1].uri != this.fSecondChild.uri || array[n + 1].localpart != this.fSecondChild.localpart) && !this.comparator.isEquivalentTo(array[n + 1], this.fSecondChild)) {
                        return 1;
                    }
                    break;
                }
                else {
                    if (n2 > 2) {
                        return 2;
                    }
                    return n2;
                }
                break;
            }
            default: {
                throw new CMException(8);
            }
        }
        return -1;
    }
    
    public void setSubstitutionGroupComparator(final SubstitutionGroupComparator comparator) {
        this.comparator = comparator;
    }
    
    public int whatCanGoHere(final boolean b, final InsertableElementsInfo insertableElementsInfo) throws Exception {
        for (int i = insertableElementsInfo.insertAt; i < insertableElementsInfo.childCount; ++i) {
            insertableElementsInfo.curChildren[i].setValues(insertableElementsInfo.curChildren[i + 1]);
        }
        --insertableElementsInfo.childCount;
        final int validateContent = this.validateContent(insertableElementsInfo.curChildren, 0, insertableElementsInfo.childCount);
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
            insertableElementsInfo.possibleChildren = new QName[insertableElementsInfo.resultsCount];
            for (int j = 0; j < insertableElementsInfo.possibleChildren.length; ++j) {
                insertableElementsInfo.possibleChildren[j] = new QName();
            }
        }
        insertableElementsInfo.possibleChildren[0].setValues(this.fFirstChild);
        insertableElementsInfo.results[0] = false;
        if (insertableElementsInfo.resultsCount == 2) {
            insertableElementsInfo.possibleChildren[1].setValues(this.fSecondChild);
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
                            insertableElementsInfo.results[0] = (insertableElementsInfo.curChildren[0].uri == this.fSecondChild.uri && insertableElementsInfo.curChildren[0].localpart == this.fSecondChild.localpart);
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
    
    public ContentLeafNameTypeVector getContentLeafNameTypeVector() {
        return null;
    }
}
