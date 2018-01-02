// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.common;

import java.util.Hashtable;
import org.apache.xerces.utils.QName;
import org.apache.xerces.validators.schema.SubstitutionGroupComparator;

public class DFAContentModel implements XMLContentModel
{
    private static final int EPSILON = -2;
    private static final int EOC = -3;
    private static final boolean DEBUG_VALIDATE_CONTENT = false;
    private SubstitutionGroupComparator comparator;
    private QName[] fElemMap;
    private int[] fElemMapType;
    private int fElemMapSize;
    private boolean fDTD;
    private int fEOCIndex;
    private int fEOCPos;
    private int fEpsilonIndex;
    private boolean[] fFinalStateFlags;
    private CMStateSet[] fFollowList;
    private CMNode fHeadNode;
    private int fLeafCount;
    private CMLeaf[] fLeafList;
    private int[] fLeafListType;
    private ContentLeafNameTypeVector fLeafNameTypeVector;
    private int[][] fTransTable;
    private int fTransTableSize;
    private boolean fEmptyContentIsValid;
    private QName fQName;
    private static long time;
    
    public DFAContentModel(final CMNode cmNode, final int n) throws CMException {
        this(cmNode, n, false);
    }
    
    public DFAContentModel(final CMNode cmNode, final int fLeafCount, final boolean fdtd) throws CMException {
        this.comparator = null;
        this.fElemMap = null;
        this.fElemMapType = null;
        this.fElemMapSize = 0;
        this.fEOCIndex = 0;
        this.fEOCPos = 0;
        this.fEpsilonIndex = 0;
        this.fFinalStateFlags = null;
        this.fFollowList = null;
        this.fHeadNode = null;
        this.fLeafCount = 0;
        this.fLeafList = null;
        this.fLeafListType = null;
        this.fLeafNameTypeVector = null;
        this.fTransTable = null;
        this.fTransTableSize = 0;
        this.fEmptyContentIsValid = false;
        this.fQName = new QName();
        this.fLeafCount = fLeafCount;
        this.fEpsilonIndex = -2;
        this.fEOCIndex = -3;
        this.fDTD = fdtd;
        DFAContentModel.time -= System.currentTimeMillis();
        this.buildDFA(cmNode);
        DFAContentModel.time += System.currentTimeMillis();
    }
    
    public int validateContent(final QName[] array, final int n, final int n2) throws CMException {
        if (n2 == 0) {
            return this.fEmptyContentIsValid ? -1 : 0;
        }
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < n2; ++i) {
            final QName qName = array[n + i];
            int j;
            for (j = 0; j < this.fElemMapSize; ++j) {
                if (this.fDTD) {
                    if (this.fElemMap[j].rawname == qName.rawname) {
                        n4 = this.fTransTable[n3][j];
                        if (n4 != -1) {
                            break;
                        }
                    }
                }
                else {
                    final int n5 = this.fElemMapType[j] & 0xF;
                    if (n5 == 0) {
                        if (this.fElemMap[j].uri == qName.uri && this.fElemMap[j].localpart == qName.localpart) {
                            n4 = this.fTransTable[n3][j];
                            if (n4 != -1) {
                                break;
                            }
                        }
                    }
                    else if (n5 == 6) {
                        final int uri = this.fElemMap[j].uri;
                        if (uri == 0 || uri == qName.uri) {
                            n4 = this.fTransTable[n3][j];
                            if (n4 != -1) {
                                break;
                            }
                        }
                    }
                    else if (n5 == 8) {
                        if (qName.uri == 0) {
                            n4 = this.fTransTable[n3][j];
                            if (n4 != -1) {
                                break;
                            }
                        }
                    }
                    else if (n5 == 7 && this.fElemMap[j].uri != qName.uri) {
                        n4 = this.fTransTable[n3][j];
                        if (n4 != -1) {
                            break;
                        }
                    }
                }
            }
            if (n4 == -1) {
                return i;
            }
            if (j == this.fElemMapSize) {
                return i;
            }
            n3 = n4;
            n4 = 0;
        }
        if (!this.fFinalStateFlags[n3]) {
            return n2;
        }
        return -1;
    }
    
    private boolean isEqual(final QName qName, final QName qName2) {
        return qName.localpart == qName2.localpart && qName.uri == qName2.uri;
    }
    
    public int validateContentSpecial(final QName[] array, final int n, final int n2) throws Exception {
        if (this.comparator == null) {
            return this.validateContent(array, n, n2);
        }
        if (n2 == 0) {
            return this.fEmptyContentIsValid ? -1 : 0;
        }
        int n3 = 0;
        int n4 = 0;
        for (int i = 0; i < n2; ++i) {
            final QName qName = array[n + i];
            int j;
            for (j = 0; j < this.fElemMapSize; ++j) {
                final int n5 = this.fElemMapType[j] & 0xF;
                if (n5 == 0) {
                    if (this.comparator.isEquivalentTo(qName, this.fElemMap[j])) {
                        n4 = this.fTransTable[n3][j];
                        if (n4 != -1) {
                            break;
                        }
                    }
                }
                else if (n5 == 6) {
                    final int uri = this.fElemMap[j].uri;
                    if (uri == 0 || uri == qName.uri) {
                        n4 = this.fTransTable[n3][j];
                        if (n4 != -1) {
                            break;
                        }
                    }
                }
                else if (n5 == 8) {
                    if (qName.uri == 0) {
                        n4 = this.fTransTable[n3][j];
                        if (n4 != -1) {
                            break;
                        }
                    }
                }
                else if (n5 == 7 && this.fElemMap[j].uri != qName.uri) {
                    n4 = this.fTransTable[n3][j];
                    if (n4 != -1) {
                        break;
                    }
                }
            }
            if (n4 == -1) {
                return i;
            }
            if (j == this.fElemMapSize) {
                return i;
            }
            n3 = n4;
            n4 = 0;
        }
        if (!this.fFinalStateFlags[n3]) {
            return n2;
        }
        return -1;
    }
    
    public void setSubstitutionGroupComparator(final SubstitutionGroupComparator comparator) {
        this.comparator = comparator;
    }
    
    public int whatCanGoHere(final boolean b, final InsertableElementsInfo insertableElementsInfo) throws CMException {
        int n = 0;
        for (int i = 0; i < insertableElementsInfo.insertAt; ++i) {
            QName qName;
            int n2;
            for (qName = insertableElementsInfo.curChildren[i], n2 = 0; n2 < this.fElemMapSize && (this.fElemMap[n2].uri != qName.uri || this.fElemMap[n2].localpart != qName.localpart); ++n2) {}
            if (n2 == this.fElemMapSize) {
                return i;
            }
            n = this.fTransTable[n][n2];
            if (n == -1) {
                return i;
            }
        }
        final int n3 = n;
        insertableElementsInfo.canHoldPCData = false;
        insertableElementsInfo.isValidEOC = this.fFinalStateFlags[n3];
        insertableElementsInfo.resultsCount = this.fElemMapSize;
        if (insertableElementsInfo.results == null || insertableElementsInfo.results.length < insertableElementsInfo.resultsCount) {
            insertableElementsInfo.results = new boolean[insertableElementsInfo.resultsCount];
        }
        if (insertableElementsInfo.possibleChildren == null || insertableElementsInfo.possibleChildren.length < insertableElementsInfo.resultsCount) {
            insertableElementsInfo.possibleChildren = new QName[insertableElementsInfo.resultsCount];
            for (int j = 0; j < insertableElementsInfo.possibleChildren.length; ++j) {
                insertableElementsInfo.possibleChildren[j] = new QName();
            }
        }
        for (int k = 0; k < this.fElemMapSize; ++k) {
            insertableElementsInfo.possibleChildren[k].setValues(this.fElemMap[k]);
            insertableElementsInfo.results[k] = (this.fTransTable[n3][k] != -1);
        }
        if (b) {
            for (int l = 0; l < insertableElementsInfo.resultsCount; ++l) {
                if (insertableElementsInfo.results[l]) {
                    insertableElementsInfo.curChildren[insertableElementsInfo.insertAt] = insertableElementsInfo.possibleChildren[l];
                    if (this.validateContent(insertableElementsInfo.curChildren, 0, insertableElementsInfo.childCount) != -1) {
                        insertableElementsInfo.results[l] = false;
                    }
                }
            }
        }
        return -1;
    }
    
    public ContentLeafNameTypeVector getContentLeafNameTypeVector() {
        return this.fLeafNameTypeVector;
    }
    
    private void buildDFA(final CMNode cmNode) throws CMException {
        this.fQName.setValues(-1, this.fEOCIndex, this.fEOCIndex);
        final CMLeaf cmLeaf = new CMLeaf(this.fQName);
        this.fHeadNode = new CMBinOp(5, cmNode, cmLeaf);
        this.fEOCPos = this.fLeafCount;
        cmLeaf.setPosition(this.fLeafCount++);
        this.fLeafList = new CMLeaf[this.fLeafCount];
        this.fLeafListType = new int[this.fLeafCount];
        this.postTreeBuildInit(this.fHeadNode, 0);
        this.fFollowList = new CMStateSet[this.fLeafCount];
        for (int i = 0; i < this.fLeafCount; ++i) {
            this.fFollowList[i] = new CMStateSet(this.fLeafCount);
        }
        this.calcFollowList(this.fHeadNode);
        this.fElemMap = new QName[this.fLeafCount];
        this.fElemMapType = new int[this.fLeafCount];
        this.fElemMapSize = 0;
        for (int j = 0; j < this.fLeafCount; ++j) {
            this.fElemMap[j] = new QName();
            if ((this.fLeafListType[j] & 0xF) != 0x0 && this.fLeafNameTypeVector == null) {
                this.fLeafNameTypeVector = new ContentLeafNameTypeVector();
            }
            final QName element = this.fLeafList[j].getElement();
            int k;
            for (k = 0; k < this.fElemMapSize; ++k) {
                if (this.fDTD) {
                    if (this.fElemMap[k].rawname == element.rawname) {
                        break;
                    }
                }
                else if (this.fElemMap[k].uri == element.uri && this.fElemMap[k].localpart == element.localpart) {
                    break;
                }
            }
            if (k == this.fElemMapSize) {
                this.fElemMap[this.fElemMapSize].setValues(element);
                this.fElemMapType[this.fElemMapSize] = this.fLeafListType[j];
                ++this.fElemMapSize;
            }
        }
        if (this.fLeafNameTypeVector != null) {
            this.fLeafNameTypeVector.setValues(this.fElemMap, this.fElemMapType, this.fElemMapSize);
        }
        final int[] array = new int[this.fLeafCount + this.fElemMapSize];
        int n = 0;
        for (int l = 0; l < this.fElemMapSize; ++l) {
            for (int n2 = 0; n2 < this.fLeafCount; ++n2) {
                final QName element2 = this.fLeafList[n2].getElement();
                final int n3 = this.fLeafListType[n2];
                final QName qName = this.fElemMap[l];
                if (this.fDTD) {
                    if (element2.rawname == qName.rawname) {
                        array[n++] = n2;
                    }
                }
                else if (element2.uri == qName.uri && element2.localpart == qName.localpart) {
                    array[n++] = n2;
                }
            }
            array[n++] = -1;
        }
        int n4 = this.fLeafCount * 4;
        CMStateSet[] array2 = new CMStateSet[n4];
        this.fFinalStateFlags = new boolean[n4];
        this.fTransTable = new int[n4][];
        final CMStateSet firstPos = this.fHeadNode.firstPos();
        int n5 = 0;
        int n6 = 0;
        this.fTransTable[n6] = this.makeDefStateList();
        array2[n6] = firstPos;
        ++n6;
        final Hashtable<CMStateSet, Integer> hashtable = new Hashtable<CMStateSet, Integer>();
        while (n5 < n6) {
            final CMStateSet set = array2[n5];
            final int[] array3 = this.fTransTable[n5];
            this.fFinalStateFlags[n5] = set.getBit(this.fEOCPos);
            ++n5;
            CMStateSet set2 = null;
            int n7 = 0;
            for (int n8 = 0; n8 < this.fElemMapSize; ++n8) {
                if (set2 == null) {
                    set2 = new CMStateSet(this.fLeafCount);
                }
                else {
                    set2.zeroBits();
                }
                for (int n9 = array[n7++]; n9 != -1; n9 = array[n7++]) {
                    if (set.getBit(n9)) {
                        set2.union(this.fFollowList[n9]);
                    }
                }
                if (!set2.isEmpty()) {
                    final Integer n10 = hashtable.get(set2);
                    final int n11 = (n10 == null) ? n6 : n10;
                    if (n11 == n6) {
                        array2[n6] = set2;
                        this.fTransTable[n6] = this.makeDefStateList();
                        hashtable.put(set2, new Integer(n6));
                        ++n6;
                        set2 = null;
                    }
                    array3[n8] = n11;
                    if (n6 == n4) {
                        final int n12 = (int)(n4 * 1.5);
                        final CMStateSet[] array4 = new CMStateSet[n12];
                        final boolean[] fFinalStateFlags = new boolean[n12];
                        final int[][] fTransTable = new int[n12][];
                        for (int n13 = 0; n13 < n4; ++n13) {
                            array4[n13] = array2[n13];
                            fFinalStateFlags[n13] = this.fFinalStateFlags[n13];
                            fTransTable[n13] = this.fTransTable[n13];
                        }
                        n4 = n12;
                        array2 = array4;
                        this.fFinalStateFlags = fFinalStateFlags;
                        this.fTransTable = fTransTable;
                    }
                }
            }
        }
        this.fEmptyContentIsValid = ((CMBinOp)this.fHeadNode).getLeft().isNullable();
        this.fHeadNode = null;
        this.fLeafList = null;
        this.fFollowList = null;
    }
    
    private void calcFollowList(final CMNode cmNode) throws CMException {
        if (cmNode.type() == 4) {
            this.calcFollowList(((CMBinOp)cmNode).getLeft());
            this.calcFollowList(((CMBinOp)cmNode).getRight());
        }
        else if (cmNode.type() == 5) {
            this.calcFollowList(((CMBinOp)cmNode).getLeft());
            this.calcFollowList(((CMBinOp)cmNode).getRight());
            final CMStateSet lastPos = ((CMBinOp)cmNode).getLeft().lastPos();
            final CMStateSet firstPos = ((CMBinOp)cmNode).getRight().firstPos();
            for (int i = 0; i < this.fLeafCount; ++i) {
                if (lastPos.getBit(i)) {
                    this.fFollowList[i].union(firstPos);
                }
            }
        }
        else if (cmNode.type() == 2 || cmNode.type() == 3) {
            this.calcFollowList(((CMUniOp)cmNode).getChild());
            final CMStateSet firstPos2 = cmNode.firstPos();
            final CMStateSet lastPos2 = cmNode.lastPos();
            for (int j = 0; j < this.fLeafCount; ++j) {
                if (lastPos2.getBit(j)) {
                    this.fFollowList[j].union(firstPos2);
                }
            }
        }
        else if (cmNode.type() == 1) {
            this.calcFollowList(((CMUniOp)cmNode).getChild());
        }
    }
    
    private void dumpTree(final CMNode cmNode, final int n) throws CMException {
        for (int i = 0; i < n; ++i) {
            System.out.print("   ");
        }
        final int type = cmNode.type();
        switch (type & 0xF) {
            case 4:
            case 5: {
                if (type == 4) {
                    System.out.print("Choice Node ");
                }
                else {
                    System.out.print("Seq Node ");
                }
                if (cmNode.isNullable()) {
                    System.out.print("Nullable ");
                }
                System.out.print("firstPos=");
                System.out.print(cmNode.firstPos().toString());
                System.out.print(" lastPos=");
                System.out.println(cmNode.lastPos().toString());
                this.dumpTree(((CMBinOp)cmNode).getLeft(), n + 1);
                this.dumpTree(((CMBinOp)cmNode).getRight(), n + 1);
                break;
            }
            case 1:
            case 2:
            case 3: {
                System.out.print("Rep Node ");
                if (cmNode.isNullable()) {
                    System.out.print("Nullable ");
                }
                System.out.print("firstPos=");
                System.out.print(cmNode.firstPos().toString());
                System.out.print(" lastPos=");
                System.out.println(cmNode.lastPos().toString());
                this.dumpTree(((CMUniOp)cmNode).getChild(), n + 1);
                break;
            }
            case 0: {
                System.out.print("Leaf: (pos=" + ((CMLeaf)cmNode).getPosition() + "), " + ((CMLeaf)cmNode).getElement() + "(elemIndex=" + ((CMLeaf)cmNode).getElement() + ") ");
                if (cmNode.isNullable()) {
                    System.out.print(" Nullable ");
                }
                System.out.print("firstPos=");
                System.out.print(cmNode.firstPos().toString());
                System.out.print(" lastPos=");
                System.out.println(cmNode.lastPos().toString());
                break;
            }
            case 6:
            case 7:
            case 8: {
                if (type == 6) {
                    System.out.print("Any Node: ");
                }
                else if (type == 22) {
                    System.out.print("Any lax Node: ");
                }
                else if (type == 38) {
                    System.out.print("Any skip Node: ");
                }
                else if (type == 7) {
                    System.out.print("Any other Node: ");
                }
                else if (type == 23) {
                    System.out.print("Any other lax Node: ");
                }
                else if (type == 39) {
                    System.out.print("Any other skip Node: ");
                }
                else if (type == 8) {
                    System.out.print("Any local Node: ");
                }
                else if (type == 24) {
                    System.out.print("Any local lax Node: ");
                }
                else if (type == 40) {
                    System.out.print("Any local skip Node: ");
                }
                System.out.print("firstPos=");
                System.out.print(cmNode.firstPos().toString());
                System.out.print(" lastPos=");
                System.out.println(cmNode.lastPos().toString());
                break;
            }
            default: {
                throw new CMException(10);
            }
        }
    }
    
    private int[] makeDefStateList() {
        final int[] array = new int[this.fElemMapSize];
        for (int i = 0; i < this.fElemMapSize; ++i) {
            array[i] = -1;
        }
        return array;
    }
    
    private int postTreeBuildInit(final CMNode cmNode, int n) throws CMException {
        cmNode.setMaxStates(this.fLeafCount);
        if ((cmNode.type() & 0xF) == 0x6 || (cmNode.type() & 0xF) == 0x8 || (cmNode.type() & 0xF) == 0x7) {
            this.fLeafList[n] = new CMLeaf(new QName(-1, -1, -1, ((CMAny)cmNode).getURI()), ((CMAny)cmNode).getPosition());
            this.fLeafListType[n] = cmNode.type();
            ++n;
        }
        else if (cmNode.type() == 4 || cmNode.type() == 5) {
            n = this.postTreeBuildInit(((CMBinOp)cmNode).getLeft(), n);
            n = this.postTreeBuildInit(((CMBinOp)cmNode).getRight(), n);
        }
        else if (cmNode.type() == 2 || cmNode.type() == 3 || cmNode.type() == 1) {
            n = this.postTreeBuildInit(((CMUniOp)cmNode).getChild(), n);
        }
        else {
            if (cmNode.type() != 0) {
                throw new CMException(10);
            }
            if (((CMLeaf)cmNode).getElement().localpart != this.fEpsilonIndex) {
                this.fLeafList[n] = (CMLeaf)cmNode;
                this.fLeafListType[n] = 0;
                ++n;
            }
        }
        return n;
    }
    
    static {
        DFAContentModel.time = 0L;
    }
}
