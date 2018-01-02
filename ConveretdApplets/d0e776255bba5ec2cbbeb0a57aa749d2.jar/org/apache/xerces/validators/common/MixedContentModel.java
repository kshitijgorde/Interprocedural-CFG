// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

import org.apache.xerces.validators.schema.SubstitutionGroupComparator;
import org.apache.xerces.utils.QName;

public class MixedContentModel implements XMLContentModel
{
    private int fCount;
    private QName[] fChildren;
    private int[] fChildrenType;
    private SubstitutionGroupComparator comparator;
    private boolean fOrdered;
    private boolean fDTD;
    
    public MixedContentModel(final QName[] array, final int[] array2, final int n, final int n2) throws CMException {
        this(array, array2, n, n2, false, false);
    }
    
    public MixedContentModel(final QName[] array, final int[] array2, final int n, final int n2, final boolean b) throws CMException {
        this(array, array2, n, n2, b, false);
    }
    
    public MixedContentModel(final QName[] array, final int[] array2, final int n, final int fCount, final boolean fOrdered, final boolean fdtd) throws CMException {
        this.comparator = null;
        this.fCount = fCount;
        this.fChildren = new QName[this.fCount];
        this.fChildrenType = new int[this.fCount];
        for (int i = 0; i < this.fCount; ++i) {
            this.fChildren[i] = new QName(array[n + i]);
            this.fChildrenType[i] = array2[n + i];
        }
        this.fOrdered = fOrdered;
        this.fDTD = fdtd;
    }
    
    public int validateContent(final QName[] array, final int n, final int n2) throws Exception {
        if (this.fOrdered) {
            int n3 = 0;
            for (int i = 0; i < n2; ++i) {
                if (array[n + i].localpart != -1) {
                    final int n4 = this.fChildrenType[n3];
                    if (n4 == 0) {
                        if (this.fDTD) {
                            if (this.fChildren[n3].rawname != array[n + i].rawname) {
                                return i;
                            }
                        }
                        else if (this.fChildren[n3].uri != array[n + i].uri && this.fChildren[n3].localpart != array[n + i].localpart) {
                            return i;
                        }
                    }
                    else if (n4 == 6) {
                        final int uri = this.fChildren[n3].uri;
                        if (uri != 0 && uri != array[i].uri) {
                            return i;
                        }
                    }
                    else if (n4 == 8) {
                        if (array[i].uri != 0) {
                            return i;
                        }
                    }
                    else if (n4 == 7 && this.fChildren[n3].uri == array[i].uri) {
                        return i;
                    }
                    ++n3;
                }
            }
        }
        else {
            for (int j = 0; j < n2; ++j) {
                final QName qName = array[n + j];
                if (qName.localpart != -1) {
                    int k;
                    for (k = 0; k < this.fCount; ++k) {
                        final int n5 = this.fChildrenType[k];
                        if (n5 == 0) {
                            if (this.fDTD) {
                                if (qName.rawname == this.fChildren[k].rawname) {
                                    break;
                                }
                            }
                            else if (qName.uri == this.fChildren[k].uri && qName.localpart == this.fChildren[k].localpart) {
                                break;
                            }
                        }
                        else if (n5 == 6) {
                            final int uri2 = this.fChildren[k].uri;
                            if (uri2 == 0) {
                                break;
                            }
                            if (uri2 == array[j].uri) {
                                break;
                            }
                        }
                        else if (n5 == 8) {
                            if (array[j].uri == 0) {
                                break;
                            }
                        }
                        else if (n5 == 7 && this.fChildren[k].uri != array[j].uri) {
                            break;
                        }
                    }
                    if (k == this.fCount) {
                        return j;
                    }
                }
            }
        }
        return -1;
    }
    
    public int validateContentSpecial(final QName[] array, final int n, final int n2) throws Exception {
        return this.validateContent(array, n, n2);
    }
    
    public void setSubstitutionGroupComparator(final SubstitutionGroupComparator comparator) {
        this.comparator = comparator;
    }
    
    public int whatCanGoHere(final boolean b, final InsertableElementsInfo insertableElementsInfo) throws Exception {
        for (int i = insertableElementsInfo.insertAt; i < insertableElementsInfo.childCount; ++i) {
            insertableElementsInfo.curChildren[i] = insertableElementsInfo.curChildren[i + 1];
        }
        --insertableElementsInfo.childCount;
        final int validateContent = this.validateContent(insertableElementsInfo.curChildren, 0, insertableElementsInfo.childCount);
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
            insertableElementsInfo.possibleChildren = new QName[insertableElementsInfo.resultsCount];
            for (int j = 0; j < insertableElementsInfo.possibleChildren.length; ++j) {
                insertableElementsInfo.possibleChildren[j] = new QName();
            }
        }
        boolean b2 = true;
        if (b && validateContent < insertableElementsInfo.childCount) {
            b2 = false;
        }
        for (int k = 0; k < this.fCount; ++k) {
            insertableElementsInfo.possibleChildren[k].setValues(this.fChildren[k]);
            insertableElementsInfo.results[k] = b2;
        }
        return -1;
    }
    
    public ContentLeafNameTypeVector getContentLeafNameTypeVector() {
        return null;
    }
}
