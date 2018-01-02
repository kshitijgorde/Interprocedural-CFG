// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.models;

import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.impl.xs.XSConstraints;
import java.util.Hashtable;
import org.apache.xerces.impl.xs.XSWildcardDecl;
import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.impl.xs.SubstitutionGroupHandler;
import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.dtd.models.CMNode;
import org.apache.xerces.impl.dtd.models.CMStateSet;

public class XSDFACM implements XSCMValidator
{
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_VALIDATE_CONTENT = false;
    private Object[] fElemMap;
    private int[] fElemMapType;
    private int[] fElemMapId;
    private int fElemMapSize;
    private int fEOCPos;
    private boolean[] fFinalStateFlags;
    private CMStateSet[] fFollowList;
    private CMNode fHeadNode;
    private int fLeafCount;
    private XSCMLeaf[] fLeafList;
    private int[] fLeafListType;
    private int[][] fTransTable;
    private int fTransTableSize;
    private boolean fEmptyContentIsValid;
    private static long time;
    
    public XSDFACM(final CMNode syntaxTree, final int leafCount) {
        this.fElemMap = null;
        this.fElemMapType = null;
        this.fElemMapId = null;
        this.fElemMapSize = 0;
        this.fEOCPos = 0;
        this.fFinalStateFlags = null;
        this.fFollowList = null;
        this.fHeadNode = null;
        this.fLeafCount = 0;
        this.fLeafList = null;
        this.fLeafListType = null;
        this.fTransTable = null;
        this.fTransTableSize = 0;
        this.fEmptyContentIsValid = false;
        this.fLeafCount = leafCount;
        this.buildDFA(syntaxTree);
    }
    
    public boolean isFinalState(final int state) {
        return state >= 0 && this.fFinalStateFlags[state];
    }
    
    public Object oneTransition(final QName curElem, final int[] state, final SubstitutionGroupHandler subGroupHandler) {
        final int curState = state[0];
        if (curState == -1 || curState == -2) {
            if (curState == -1) {
                state[0] = -2;
            }
            return this.findMatchingDecl(curElem, subGroupHandler);
        }
        int nextState = 0;
        int elemIndex = 0;
        Object matchingDecl = null;
        while (elemIndex < this.fElemMapSize) {
            nextState = this.fTransTable[curState][elemIndex];
            if (nextState != -1) {
                final int type = this.fElemMapType[elemIndex];
                if (type == 1) {
                    matchingDecl = subGroupHandler.getMatchingElemDecl(curElem, (XSElementDecl)this.fElemMap[elemIndex]);
                    if (matchingDecl != null) {
                        break;
                    }
                }
                else if (type == 2 && ((XSWildcardDecl)this.fElemMap[elemIndex]).allowNamespace(curElem.uri)) {
                    matchingDecl = this.fElemMap[elemIndex];
                    break;
                }
            }
            ++elemIndex;
        }
        if (elemIndex == this.fElemMapSize) {
            state[0] = -1;
            return this.findMatchingDecl(curElem, subGroupHandler);
        }
        state[0] = nextState;
        return matchingDecl;
    }
    
    Object findMatchingDecl(final QName curElem, final SubstitutionGroupHandler subGroupHandler) {
        Object matchingDecl = null;
        for (int elemIndex = 0; elemIndex < this.fElemMapSize; ++elemIndex) {
            final int type = this.fElemMapType[elemIndex];
            if (type == 1) {
                matchingDecl = subGroupHandler.getMatchingElemDecl(curElem, (XSElementDecl)this.fElemMap[elemIndex]);
                if (matchingDecl != null) {
                    return matchingDecl;
                }
            }
            else if (type == 2 && ((XSWildcardDecl)this.fElemMap[elemIndex]).allowNamespace(curElem.uri)) {
                return this.fElemMap[elemIndex];
            }
        }
        return null;
    }
    
    public int[] startContentModel() {
        final int[] val = { 0 };
        return val;
    }
    
    public boolean endContentModel(final int[] state) {
        return this.fFinalStateFlags[state[0]];
    }
    
    private void buildDFA(final CMNode syntaxTree) {
        this.fEOCPos = this.fLeafCount;
        final XSCMLeaf nodeEOC = new XSCMLeaf(1, null, -1, this.fLeafCount++);
        this.fHeadNode = new XSCMBinOp(102, syntaxTree, nodeEOC);
        this.fLeafList = new XSCMLeaf[this.fLeafCount];
        this.fLeafListType = new int[this.fLeafCount];
        this.postTreeBuildInit(this.fHeadNode);
        this.fFollowList = new CMStateSet[this.fLeafCount];
        for (int index = 0; index < this.fLeafCount; ++index) {
            this.fFollowList[index] = new CMStateSet(this.fLeafCount);
        }
        this.calcFollowList(this.fHeadNode);
        this.fElemMap = new Object[this.fLeafCount];
        this.fElemMapType = new int[this.fLeafCount];
        this.fElemMapId = new int[this.fLeafCount];
        this.fElemMapSize = 0;
        for (int outIndex = 0; outIndex < this.fLeafCount; ++outIndex) {
            this.fElemMap[outIndex] = null;
            int inIndex;
            int id;
            for (inIndex = 0, id = this.fLeafList[outIndex].getParticleId(); inIndex < this.fElemMapSize && id != this.fElemMapId[inIndex]; ++inIndex) {}
            if (inIndex == this.fElemMapSize) {
                this.fElemMap[this.fElemMapSize] = this.fLeafList[outIndex].getLeaf();
                this.fElemMapType[this.fElemMapSize] = this.fLeafListType[outIndex];
                this.fElemMapId[this.fElemMapSize] = id;
                ++this.fElemMapSize;
            }
        }
        --this.fElemMapSize;
        final int[] fLeafSorter = new int[this.fLeafCount + this.fElemMapSize];
        int fSortCount = 0;
        for (int elemIndex = 0; elemIndex < this.fElemMapSize; ++elemIndex) {
            final int id2 = this.fElemMapId[elemIndex];
            for (int leafIndex = 0; leafIndex < this.fLeafCount; ++leafIndex) {
                if (id2 == this.fLeafList[leafIndex].getParticleId()) {
                    fLeafSorter[fSortCount++] = leafIndex;
                }
            }
            fLeafSorter[fSortCount++] = -1;
        }
        int curArraySize = this.fLeafCount * 4;
        CMStateSet[] statesToDo = new CMStateSet[curArraySize];
        this.fFinalStateFlags = new boolean[curArraySize];
        this.fTransTable = new int[curArraySize][];
        CMStateSet setT = this.fHeadNode.firstPos();
        int unmarkedState = 0;
        int curState = 0;
        this.fTransTable[curState] = this.makeDefStateList();
        statesToDo[curState] = setT;
        ++curState;
        final Hashtable stateTable = new Hashtable();
        while (unmarkedState < curState) {
            setT = statesToDo[unmarkedState];
            final int[] transEntry = this.fTransTable[unmarkedState];
            this.fFinalStateFlags[unmarkedState] = setT.getBit(this.fEOCPos);
            ++unmarkedState;
            CMStateSet newSet = null;
            int sorterIndex = 0;
            for (int elemIndex2 = 0; elemIndex2 < this.fElemMapSize; ++elemIndex2) {
                if (newSet == null) {
                    newSet = new CMStateSet(this.fLeafCount);
                }
                else {
                    newSet.zeroBits();
                }
                for (int leafIndex2 = fLeafSorter[sorterIndex++]; leafIndex2 != -1; leafIndex2 = fLeafSorter[sorterIndex++]) {
                    if (setT.getBit(leafIndex2)) {
                        newSet.union(this.fFollowList[leafIndex2]);
                    }
                }
                if (!newSet.isEmpty()) {
                    final Integer stateObj = stateTable.get(newSet);
                    final int stateIndex = (stateObj == null) ? curState : stateObj;
                    if (stateIndex == curState) {
                        statesToDo[curState] = newSet;
                        this.fTransTable[curState] = this.makeDefStateList();
                        stateTable.put(newSet, new Integer(curState));
                        ++curState;
                        newSet = null;
                    }
                    transEntry[elemIndex2] = stateIndex;
                    if (curState == curArraySize) {
                        final int newSize = (int)(curArraySize * 1.5);
                        final CMStateSet[] newToDo = new CMStateSet[newSize];
                        final boolean[] newFinalFlags = new boolean[newSize];
                        final int[][] newTransTable = new int[newSize][];
                        for (int expIndex = 0; expIndex < curArraySize; ++expIndex) {
                            newToDo[expIndex] = statesToDo[expIndex];
                            newFinalFlags[expIndex] = this.fFinalStateFlags[expIndex];
                            newTransTable[expIndex] = this.fTransTable[expIndex];
                        }
                        curArraySize = newSize;
                        statesToDo = newToDo;
                        this.fFinalStateFlags = newFinalFlags;
                        this.fTransTable = newTransTable;
                    }
                }
            }
        }
        this.fEmptyContentIsValid = ((XSCMBinOp)this.fHeadNode).getLeft().isNullable();
        this.fHeadNode = null;
        this.fLeafList = null;
        this.fFollowList = null;
        this.fLeafListType = null;
        this.fElemMapId = null;
    }
    
    private void calcFollowList(final CMNode nodeCur) {
        if (nodeCur.type() == 101) {
            this.calcFollowList(((XSCMBinOp)nodeCur).getLeft());
            this.calcFollowList(((XSCMBinOp)nodeCur).getRight());
        }
        else if (nodeCur.type() == 102) {
            this.calcFollowList(((XSCMBinOp)nodeCur).getLeft());
            this.calcFollowList(((XSCMBinOp)nodeCur).getRight());
            final CMStateSet last = ((XSCMBinOp)nodeCur).getLeft().lastPos();
            final CMStateSet first = ((XSCMBinOp)nodeCur).getRight().firstPos();
            for (int index = 0; index < this.fLeafCount; ++index) {
                if (last.getBit(index)) {
                    this.fFollowList[index].union(first);
                }
            }
        }
        else if (nodeCur.type() == 4 || nodeCur.type() == 6) {
            this.calcFollowList(((XSCMUniOp)nodeCur).getChild());
            final CMStateSet first2 = nodeCur.firstPos();
            final CMStateSet last2 = nodeCur.lastPos();
            for (int index = 0; index < this.fLeafCount; ++index) {
                if (last2.getBit(index)) {
                    this.fFollowList[index].union(first2);
                }
            }
        }
        else if (nodeCur.type() == 5) {
            this.calcFollowList(((XSCMUniOp)nodeCur).getChild());
        }
    }
    
    private void dumpTree(final CMNode nodeCur, final int level) {
        for (int index = 0; index < level; ++index) {
            System.out.print("   ");
        }
        final int type = nodeCur.type();
        switch (type) {
            case 101:
            case 102: {
                if (type == 101) {
                    System.out.print("Choice Node ");
                }
                else {
                    System.out.print("Seq Node ");
                }
                if (nodeCur.isNullable()) {
                    System.out.print("Nullable ");
                }
                System.out.print("firstPos=");
                System.out.print(nodeCur.firstPos().toString());
                System.out.print(" lastPos=");
                System.out.println(nodeCur.lastPos().toString());
                this.dumpTree(((XSCMBinOp)nodeCur).getLeft(), level + 1);
                this.dumpTree(((XSCMBinOp)nodeCur).getRight(), level + 1);
                break;
            }
            case 4:
            case 5:
            case 6: {
                System.out.print("Rep Node ");
                if (nodeCur.isNullable()) {
                    System.out.print("Nullable ");
                }
                System.out.print("firstPos=");
                System.out.print(nodeCur.firstPos().toString());
                System.out.print(" lastPos=");
                System.out.println(nodeCur.lastPos().toString());
                this.dumpTree(((XSCMUniOp)nodeCur).getChild(), level + 1);
                break;
            }
            case 1: {
                System.out.print("Leaf: (pos=" + ((XSCMLeaf)nodeCur).getPosition() + "), " + "(elemIndex=" + ((XSCMLeaf)nodeCur).getLeaf() + ") ");
                if (nodeCur.isNullable()) {
                    System.out.print(" Nullable ");
                }
                System.out.print("firstPos=");
                System.out.print(nodeCur.firstPos().toString());
                System.out.print(" lastPos=");
                System.out.println(nodeCur.lastPos().toString());
                break;
            }
            case 2: {
                System.out.print("Any Node: ");
                System.out.print("firstPos=");
                System.out.print(nodeCur.firstPos().toString());
                System.out.print(" lastPos=");
                System.out.println(nodeCur.lastPos().toString());
                break;
            }
            default: {
                throw new RuntimeException("ImplementationMessages.VAL_NIICM");
            }
        }
    }
    
    private int[] makeDefStateList() {
        final int[] retArray = new int[this.fElemMapSize];
        for (int index = 0; index < this.fElemMapSize; ++index) {
            retArray[index] = -1;
        }
        return retArray;
    }
    
    private void postTreeBuildInit(final CMNode nodeCur) throws RuntimeException {
        nodeCur.setMaxStates(this.fLeafCount);
        XSCMLeaf leaf = null;
        int pos = 0;
        if (nodeCur.type() == 2) {
            leaf = (XSCMLeaf)nodeCur;
            pos = leaf.getPosition();
            this.fLeafList[pos] = leaf;
            this.fLeafListType[pos] = 2;
        }
        else if (nodeCur.type() == 101 || nodeCur.type() == 102) {
            this.postTreeBuildInit(((XSCMBinOp)nodeCur).getLeft());
            this.postTreeBuildInit(((XSCMBinOp)nodeCur).getRight());
        }
        else if (nodeCur.type() == 4 || nodeCur.type() == 6 || nodeCur.type() == 5) {
            this.postTreeBuildInit(((XSCMUniOp)nodeCur).getChild());
        }
        else {
            if (nodeCur.type() != 1) {
                throw new RuntimeException("ImplementationMessages.VAL_NIICM");
            }
            leaf = (XSCMLeaf)nodeCur;
            pos = leaf.getPosition();
            this.fLeafList[pos] = leaf;
            this.fLeafListType[pos] = 1;
        }
    }
    
    public boolean checkUniqueParticleAttribution(final SubstitutionGroupHandler subGroupHandler) throws XMLSchemaException {
        final byte[][] conflictTable = new byte[this.fElemMapSize][this.fElemMapSize];
        for (int i = 0; i < this.fTransTable.length && this.fTransTable[i] != null; ++i) {
            for (int j = 0; j < this.fElemMapSize; ++j) {
                for (int k = j + 1; k < this.fElemMapSize; ++k) {
                    if (this.fTransTable[i][j] != -1 && this.fTransTable[i][k] != -1 && conflictTable[j][k] == 0) {
                        conflictTable[j][k] = (byte)(XSConstraints.overlapUPA(this.fElemMap[j], this.fElemMap[k], subGroupHandler) ? 1 : -1);
                    }
                }
            }
        }
        for (int l = 0; l < this.fElemMapSize; ++l) {
            for (int m = 0; m < this.fElemMapSize; ++m) {
                if (conflictTable[l][m] == 1) {
                    throw new XMLSchemaException("cos-nonambig", new Object[] { this.fElemMap[l].toString(), this.fElemMap[m].toString() });
                }
            }
        }
        for (int i2 = 0; i2 < this.fElemMapSize; ++i2) {
            if (this.fElemMapType[i2] == 2) {
                final XSWildcardDecl wildcard = (XSWildcardDecl)this.fElemMap[i2];
                if (wildcard.fType == 3 || wildcard.fType == 2) {
                    return true;
                }
            }
        }
        return false;
    }
    
    static {
        XSDFACM.time = 0L;
    }
}
