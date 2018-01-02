// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.models;

import java.util.Vector;
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
    private boolean[] fFinalStateFlags;
    private CMStateSet[] fFollowList;
    private CMNode fHeadNode;
    private int fLeafCount;
    private XSCMLeaf[] fLeafList;
    private int[] fLeafListType;
    private int[][] fTransTable;
    private int fTransTableSize;
    private static long time;
    
    public XSDFACM(final CMNode cmNode, final int fLeafCount) {
        this.fElemMap = null;
        this.fElemMapType = null;
        this.fElemMapId = null;
        this.fElemMapSize = 0;
        this.fFinalStateFlags = null;
        this.fFollowList = null;
        this.fHeadNode = null;
        this.fLeafCount = 0;
        this.fLeafList = null;
        this.fLeafListType = null;
        this.fTransTable = null;
        this.fTransTableSize = 0;
        this.fLeafCount = fLeafCount;
        this.buildDFA(cmNode);
    }
    
    public boolean isFinalState(final int n) {
        return n >= 0 && this.fFinalStateFlags[n];
    }
    
    public Object oneTransition(final QName qName, final int[] array, final SubstitutionGroupHandler substitutionGroupHandler) {
        final int n = array[0];
        if (n == -1 || n == -2) {
            if (n == -1) {
                array[0] = -2;
            }
            return this.findMatchingDecl(qName, substitutionGroupHandler);
        }
        int n2 = 0;
        int i = 0;
        Object matchingElemDecl = null;
        while (i < this.fElemMapSize) {
            n2 = this.fTransTable[n][i];
            if (n2 != -1) {
                final int n3 = this.fElemMapType[i];
                if (n3 == 1) {
                    matchingElemDecl = substitutionGroupHandler.getMatchingElemDecl(qName, (XSElementDecl)this.fElemMap[i]);
                    if (matchingElemDecl != null) {
                        break;
                    }
                }
                else if (n3 == 2 && ((XSWildcardDecl)this.fElemMap[i]).allowNamespace(qName.uri)) {
                    matchingElemDecl = this.fElemMap[i];
                    break;
                }
            }
            ++i;
        }
        if (i == this.fElemMapSize) {
            array[1] = array[0];
            array[0] = -1;
            return this.findMatchingDecl(qName, substitutionGroupHandler);
        }
        array[0] = n2;
        return matchingElemDecl;
    }
    
    Object findMatchingDecl(final QName qName, final SubstitutionGroupHandler substitutionGroupHandler) {
        for (int i = 0; i < this.fElemMapSize; ++i) {
            final int n = this.fElemMapType[i];
            if (n == 1) {
                final XSElementDecl matchingElemDecl = substitutionGroupHandler.getMatchingElemDecl(qName, (XSElementDecl)this.fElemMap[i]);
                if (matchingElemDecl != null) {
                    return matchingElemDecl;
                }
            }
            else if (n == 2 && ((XSWildcardDecl)this.fElemMap[i]).allowNamespace(qName.uri)) {
                return this.fElemMap[i];
            }
        }
        return null;
    }
    
    public int[] startContentModel() {
        return new int[] { 0, 0 };
    }
    
    public boolean endContentModel(final int[] array) {
        return this.fFinalStateFlags[array[0]];
    }
    
    private void buildDFA(final CMNode cmNode) {
        final int fLeafCount = this.fLeafCount;
        this.fHeadNode = new XSCMBinOp(102, cmNode, new XSCMLeaf(1, null, -1, this.fLeafCount++));
        this.fLeafList = new XSCMLeaf[this.fLeafCount];
        this.fLeafListType = new int[this.fLeafCount];
        this.postTreeBuildInit(this.fHeadNode);
        this.fFollowList = new CMStateSet[this.fLeafCount];
        for (int i = 0; i < this.fLeafCount; ++i) {
            this.fFollowList[i] = new CMStateSet(this.fLeafCount);
        }
        this.calcFollowList(this.fHeadNode);
        this.fElemMap = new Object[this.fLeafCount];
        this.fElemMapType = new int[this.fLeafCount];
        this.fElemMapId = new int[this.fLeafCount];
        this.fElemMapSize = 0;
        for (int j = 0; j < this.fLeafCount; ++j) {
            this.fElemMap[j] = null;
            int n;
            int particleId;
            for (n = 0, particleId = this.fLeafList[j].getParticleId(); n < this.fElemMapSize && particleId != this.fElemMapId[n]; ++n) {}
            if (n == this.fElemMapSize) {
                this.fElemMap[this.fElemMapSize] = this.fLeafList[j].getLeaf();
                this.fElemMapType[this.fElemMapSize] = this.fLeafListType[j];
                this.fElemMapId[this.fElemMapSize] = particleId;
                ++this.fElemMapSize;
            }
        }
        --this.fElemMapSize;
        final int[] array = new int[this.fLeafCount + this.fElemMapSize];
        int n2 = 0;
        for (int k = 0; k < this.fElemMapSize; ++k) {
            final int n3 = this.fElemMapId[k];
            for (int l = 0; l < this.fLeafCount; ++l) {
                if (n3 == this.fLeafList[l].getParticleId()) {
                    array[n2++] = l;
                }
            }
            array[n2++] = -1;
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
            this.fFinalStateFlags[n5] = set.getBit(fLeafCount);
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
        this.fHeadNode = null;
        this.fLeafList = null;
        this.fFollowList = null;
        this.fLeafListType = null;
        this.fElemMapId = null;
    }
    
    private void calcFollowList(final CMNode cmNode) {
        if (cmNode.type() == 101) {
            this.calcFollowList(((XSCMBinOp)cmNode).getLeft());
            this.calcFollowList(((XSCMBinOp)cmNode).getRight());
        }
        else if (cmNode.type() == 102) {
            this.calcFollowList(((XSCMBinOp)cmNode).getLeft());
            this.calcFollowList(((XSCMBinOp)cmNode).getRight());
            final CMStateSet lastPos = ((XSCMBinOp)cmNode).getLeft().lastPos();
            final CMStateSet firstPos = ((XSCMBinOp)cmNode).getRight().firstPos();
            for (int i = 0; i < this.fLeafCount; ++i) {
                if (lastPos.getBit(i)) {
                    this.fFollowList[i].union(firstPos);
                }
            }
        }
        else if (cmNode.type() == 4 || cmNode.type() == 6) {
            this.calcFollowList(((XSCMUniOp)cmNode).getChild());
            final CMStateSet firstPos2 = cmNode.firstPos();
            final CMStateSet lastPos2 = cmNode.lastPos();
            for (int j = 0; j < this.fLeafCount; ++j) {
                if (lastPos2.getBit(j)) {
                    this.fFollowList[j].union(firstPos2);
                }
            }
        }
        else if (cmNode.type() == 5) {
            this.calcFollowList(((XSCMUniOp)cmNode).getChild());
        }
    }
    
    private void dumpTree(final CMNode cmNode, final int n) {
        for (int i = 0; i < n; ++i) {
            System.out.print("   ");
        }
        final int type = cmNode.type();
        switch (type) {
            case 101:
            case 102: {
                if (type == 101) {
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
                this.dumpTree(((XSCMBinOp)cmNode).getLeft(), n + 1);
                this.dumpTree(((XSCMBinOp)cmNode).getRight(), n + 1);
                break;
            }
            case 4:
            case 5:
            case 6: {
                System.out.print("Rep Node ");
                if (cmNode.isNullable()) {
                    System.out.print("Nullable ");
                }
                System.out.print("firstPos=");
                System.out.print(cmNode.firstPos().toString());
                System.out.print(" lastPos=");
                System.out.println(cmNode.lastPos().toString());
                this.dumpTree(((XSCMUniOp)cmNode).getChild(), n + 1);
                break;
            }
            case 1: {
                System.out.print("Leaf: (pos=" + ((XSCMLeaf)cmNode).getPosition() + "), " + "(elemIndex=" + ((XSCMLeaf)cmNode).getLeaf() + ") ");
                if (cmNode.isNullable()) {
                    System.out.print(" Nullable ");
                }
                System.out.print("firstPos=");
                System.out.print(cmNode.firstPos().toString());
                System.out.print(" lastPos=");
                System.out.println(cmNode.lastPos().toString());
                break;
            }
            case 2: {
                System.out.print("Any Node: ");
                System.out.print("firstPos=");
                System.out.print(cmNode.firstPos().toString());
                System.out.print(" lastPos=");
                System.out.println(cmNode.lastPos().toString());
                break;
            }
            default: {
                throw new RuntimeException("ImplementationMessages.VAL_NIICM");
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
    
    private void postTreeBuildInit(final CMNode cmNode) throws RuntimeException {
        cmNode.setMaxStates(this.fLeafCount);
        if (cmNode.type() == 2) {
            final XSCMLeaf xscmLeaf = (XSCMLeaf)cmNode;
            final int position = xscmLeaf.getPosition();
            this.fLeafList[position] = xscmLeaf;
            this.fLeafListType[position] = 2;
        }
        else if (cmNode.type() == 101 || cmNode.type() == 102) {
            this.postTreeBuildInit(((XSCMBinOp)cmNode).getLeft());
            this.postTreeBuildInit(((XSCMBinOp)cmNode).getRight());
        }
        else if (cmNode.type() == 4 || cmNode.type() == 6 || cmNode.type() == 5) {
            this.postTreeBuildInit(((XSCMUniOp)cmNode).getChild());
        }
        else {
            if (cmNode.type() != 1) {
                throw new RuntimeException("ImplementationMessages.VAL_NIICM");
            }
            final XSCMLeaf xscmLeaf2 = (XSCMLeaf)cmNode;
            final int position2 = xscmLeaf2.getPosition();
            this.fLeafList[position2] = xscmLeaf2;
            this.fLeafListType[position2] = 1;
        }
    }
    
    public boolean checkUniqueParticleAttribution(final SubstitutionGroupHandler substitutionGroupHandler) throws XMLSchemaException {
        final byte[][] array = new byte[this.fElemMapSize][this.fElemMapSize];
        for (int n = 0; n < this.fTransTable.length && this.fTransTable[n] != null; ++n) {
            for (int i = 0; i < this.fElemMapSize; ++i) {
                for (int j = i + 1; j < this.fElemMapSize; ++j) {
                    if (this.fTransTable[n][i] != -1 && this.fTransTable[n][j] != -1 && array[i][j] == 0) {
                        array[i][j] = (byte)(XSConstraints.overlapUPA(this.fElemMap[i], this.fElemMap[j], substitutionGroupHandler) ? 1 : -1);
                    }
                }
            }
        }
        for (int k = 0; k < this.fElemMapSize; ++k) {
            for (int l = 0; l < this.fElemMapSize; ++l) {
                if (array[k][l] == 1) {
                    throw new XMLSchemaException("cos-nonambig", new Object[] { this.fElemMap[k].toString(), this.fElemMap[l].toString() });
                }
            }
        }
        for (int n2 = 0; n2 < this.fElemMapSize; ++n2) {
            if (this.fElemMapType[n2] == 2) {
                final XSWildcardDecl xsWildcardDecl = (XSWildcardDecl)this.fElemMap[n2];
                if (xsWildcardDecl.fType == 3 || xsWildcardDecl.fType == 2) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public Vector whatCanGoHere(final int[] array) {
        int n = array[0];
        if (n < 0) {
            n = array[1];
        }
        final Vector<Object> vector = new Vector<Object>();
        for (int i = 0; i < this.fElemMapSize; ++i) {
            if (this.fTransTable[n][i] != -1) {
                vector.addElement(this.fElemMap[i]);
            }
        }
        return vector;
    }
    
    static {
        XSDFACM.time = 0L;
    }
}
