// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.dtd;

import org.apache.xerces.utils.StringPool;

public class DFAContentModel implements XMLContentModel
{
    private static final String fEpsilonString = "<<CMNODE_EPSILON>>";
    private static final String fEOCString = "<<CMNODE_EOC>>";
    private static final boolean DEBUG_VALIDATE_CONTENT = false;
    private int[] fElemMap;
    private int fElemMapSize;
    private int fEOCIndex;
    private int fEOCPos;
    private int fEpsilonIndex;
    private boolean[] fFinalStateFlags;
    private CMStateSet[] fFollowList;
    private CMNode fHeadNode;
    private int fLeafCount;
    private CMLeaf[] fLeafList;
    private StringPool fStringPool;
    private int[][] fTransTable;
    private int fTransTableSize;
    private boolean fEmptyContentIsValid;
    
    public DFAContentModel(final StringPool fStringPool, final CMNode cmNode, final int fLeafCount) throws CMException {
        this.fEmptyContentIsValid = false;
        this.fStringPool = fStringPool;
        this.fLeafCount = fLeafCount;
        this.fEpsilonIndex = this.fStringPool.addSymbol("<<CMNODE_EPSILON>>");
        this.fEOCIndex = this.fStringPool.addSymbol("<<CMNODE_EOC>>");
        this.buildDFA(cmNode);
    }
    
    public int validateContent(final int n, final int[] array) throws CMException {
        if (n == 0) {
            if (this.fEmptyContentIsValid) {
                return -1;
            }
            return 0;
        }
        else {
            int n2 = 0;
            for (int i = 0; i < n; ++i) {
                int n3;
                int n4;
                for (n3 = array[i], n4 = 0; n4 < this.fElemMapSize && this.fElemMap[n4] != n3; ++n4) {}
                if (n4 == this.fElemMapSize) {
                    return i;
                }
                n2 = this.fTransTable[n2][n4];
                if (n2 == -1) {
                    return i;
                }
            }
            if (!this.fFinalStateFlags[n2]) {
                return n;
            }
            return -1;
        }
    }
    
    public int whatCanGoHere(final boolean b, final InsertableElementsInfo insertableElementsInfo) throws CMException {
        int n = 0;
        for (int i = 0; i < insertableElementsInfo.insertAt; ++i) {
            int n2;
            int n3;
            for (n2 = insertableElementsInfo.curChildren[i], n3 = 0; n3 < this.fElemMapSize && this.fElemMap[n3] != n2; ++n3) {}
            if (n3 == this.fElemMapSize) {
                return i;
            }
            n = this.fTransTable[n][n3];
            if (n == -1) {
                return i;
            }
        }
        final int n4 = n;
        insertableElementsInfo.canHoldPCData = false;
        insertableElementsInfo.isValidEOC = this.fFinalStateFlags[n4];
        insertableElementsInfo.resultsCount = this.fElemMapSize;
        if (insertableElementsInfo.results == null || insertableElementsInfo.results.length < insertableElementsInfo.resultsCount) {
            insertableElementsInfo.results = new boolean[insertableElementsInfo.resultsCount];
        }
        if (insertableElementsInfo.possibleChildren == null || insertableElementsInfo.possibleChildren.length < insertableElementsInfo.resultsCount) {
            insertableElementsInfo.possibleChildren = new int[insertableElementsInfo.resultsCount];
        }
        for (int j = 0; j < this.fElemMapSize; ++j) {
            insertableElementsInfo.possibleChildren[j] = this.fElemMap[j];
            insertableElementsInfo.results[j] = (this.fTransTable[n4][j] != -1);
        }
        if (b) {
            for (int k = 0; k < insertableElementsInfo.resultsCount; ++k) {
                if (insertableElementsInfo.results[k]) {
                    insertableElementsInfo.curChildren[insertableElementsInfo.insertAt] = insertableElementsInfo.possibleChildren[k];
                    if (this.validateContent(insertableElementsInfo.childCount, insertableElementsInfo.curChildren) != -1) {
                        insertableElementsInfo.results[k] = false;
                    }
                }
            }
        }
        return -1;
    }
    
    private void buildDFA(final CMNode cmNode) throws CMException {
        final CMLeaf cmLeaf = new CMLeaf(0, this.fEOCIndex);
        this.fHeadNode = new CMBinOp(5, cmNode, cmLeaf);
        this.fEOCPos = this.fLeafCount;
        cmLeaf.setPosition(this.fLeafCount++);
        this.fLeafList = new CMLeaf[this.fLeafCount];
        this.postTreeBuildInit(this.fHeadNode, 0);
        this.fFollowList = new CMStateSet[this.fLeafCount];
        for (int i = 0; i < this.fLeafCount; ++i) {
            this.fFollowList[i] = new CMStateSet(this.fLeafCount);
        }
        this.calcFollowList(this.fHeadNode);
        this.fElemMap = new int[this.fLeafCount];
        this.fElemMapSize = 0;
        for (int j = 0; j < this.fLeafCount; ++j) {
            int elemIndex;
            int n;
            for (elemIndex = this.fLeafList[j].getElemIndex(), n = 0; n < this.fElemMapSize && this.fElemMap[n] != elemIndex; ++n) {}
            if (n == this.fElemMapSize) {
                this.fElemMap[this.fElemMapSize++] = elemIndex;
            }
        }
        int n2 = this.fLeafCount * 4;
        CMStateSet[] array = new CMStateSet[n2];
        this.fFinalStateFlags = new boolean[n2];
        this.fTransTable = new int[n2][];
        final CMStateSet firstPos = this.fHeadNode.firstPos();
        int k = 0;
        int n3 = 0;
        this.fTransTable[n3] = this.makeDefStateList();
        array[n3] = firstPos;
        ++n3;
        while (k < n3) {
            final CMStateSet set = array[k];
            final int[] array2 = this.fTransTable[k];
            this.fFinalStateFlags[k] = set.getBit(this.fEOCPos);
            ++k;
            CMStateSet set2 = null;
            for (int l = 0; l < this.fElemMapSize; ++l) {
                if (set2 == null) {
                    set2 = new CMStateSet(this.fLeafCount);
                }
                else {
                    set2.zeroBits();
                }
                for (int n4 = 0; n4 < this.fLeafCount; ++n4) {
                    if (set.getBit(n4) && this.fLeafList[n4].getElemIndex() == this.fElemMap[l]) {
                        set2.union(this.fFollowList[n4]);
                    }
                }
                if (!set2.isEmpty()) {
                    int n5;
                    for (n5 = 0; n5 < n3 && !array[n5].isSameSet(set2); ++n5) {}
                    if (n5 == n3) {
                        array[n3] = set2;
                        this.fTransTable[n3] = this.makeDefStateList();
                        ++n3;
                        set2 = null;
                    }
                    array2[l] = n5;
                    if (n3 == n2) {
                        final int n6 = (int)(n2 * 1.5);
                        final CMStateSet[] array3 = new CMStateSet[n6];
                        final boolean[] fFinalStateFlags = new boolean[n6];
                        final int[][] fTransTable = new int[n6][];
                        for (int n7 = 0; n7 < n2; ++n7) {
                            array3[n7] = array[n7];
                            fFinalStateFlags[n7] = this.fFinalStateFlags[n7];
                            fTransTable[n7] = this.fTransTable[n7];
                        }
                        n2 = n6;
                        array = array3;
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
            return;
        }
        if (cmNode.type() == 5) {
            this.calcFollowList(((CMBinOp)cmNode).getLeft());
            this.calcFollowList(((CMBinOp)cmNode).getRight());
            final CMStateSet lastPos = ((CMBinOp)cmNode).getLeft().lastPos();
            final CMStateSet firstPos = ((CMBinOp)cmNode).getRight().firstPos();
            for (int i = 0; i < this.fLeafCount; ++i) {
                if (lastPos.getBit(i)) {
                    this.fFollowList[i].union(firstPos);
                }
            }
            return;
        }
        if (cmNode.type() == 2) {
            this.calcFollowList(((CMUniOp)cmNode).getChild());
            final CMStateSet firstPos2 = cmNode.firstPos();
            final CMStateSet lastPos2 = cmNode.lastPos();
            for (int j = 0; j < this.fLeafCount; ++j) {
                if (lastPos2.getBit(j)) {
                    this.fFollowList[j].union(firstPos2);
                }
            }
            return;
        }
        if (cmNode.type() == 3 || cmNode.type() == 1) {
            throw new CMException(10);
        }
    }
    
    private void dumpTree(final CMNode cmNode, final int n) throws CMException {
        for (int i = 0; i < n; ++i) {
            System.out.print("   ");
        }
        final int type = cmNode.type();
        if (type == 4 || type == 5) {
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
            return;
        }
        if (cmNode.type() == 2) {
            System.out.print("Rep Node ");
            if (cmNode.isNullable()) {
                System.out.print("Nullable ");
            }
            System.out.print("firstPos=");
            System.out.print(cmNode.firstPos().toString());
            System.out.print(" lastPos=");
            System.out.println(cmNode.lastPos().toString());
            this.dumpTree(((CMUniOp)cmNode).getChild(), n + 1);
            return;
        }
        if (cmNode.type() == 0) {
            System.out.print("Leaf: (pos=" + ((CMLeaf)cmNode).getPosition() + "), " + this.fStringPool.toString(((CMLeaf)cmNode).getElemIndex()) + "(elemIndex=" + ((CMLeaf)cmNode).getElemIndex() + ") ");
            if (cmNode.isNullable()) {
                System.out.print(" Nullable ");
            }
            System.out.print("firstPos=");
            System.out.print(cmNode.firstPos().toString());
            System.out.print(" lastPos=");
            System.out.println(cmNode.lastPos().toString());
            return;
        }
        throw new CMException(10);
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
        if (cmNode.type() == 4 || cmNode.type() == 5) {
            n = this.postTreeBuildInit(((CMBinOp)cmNode).getLeft(), n);
            n = this.postTreeBuildInit(((CMBinOp)cmNode).getRight(), n);
        }
        else if (cmNode.type() == 2) {
            n = this.postTreeBuildInit(((CMUniOp)cmNode).getChild(), n);
        }
        else {
            if (cmNode.type() != 0) {
                throw new CMException(10);
            }
            if (((CMLeaf)cmNode).getElemIndex() != this.fEpsilonIndex) {
                this.fLeafList[n++] = (CMLeaf)cmNode;
            }
        }
        return n;
    }
}
