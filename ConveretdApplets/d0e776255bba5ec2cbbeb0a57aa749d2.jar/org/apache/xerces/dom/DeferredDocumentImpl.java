// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.util.Hashtable;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.apache.xerces.framework.XMLAttrList;
import org.apache.xerces.utils.StringPool;

public class DeferredDocumentImpl extends DocumentImpl implements DeferredNode
{
    static final long serialVersionUID = 5186323580749626857L;
    private static final boolean DEBUG_PRINT_REF_COUNTS = false;
    private static final boolean DEBUG_PRINT_TABLES = false;
    private static final boolean DEBUG_IDS = false;
    protected static final int CHUNK_SHIFT = 11;
    protected static final int CHUNK_SIZE = 2048;
    protected static final int CHUNK_MASK = 2047;
    protected static final int INITIAL_CHUNK_COUNT = 32;
    protected transient int fNodeCount;
    protected transient int[][] fNodeType;
    protected transient int[][] fNodeName;
    protected transient int[][] fNodeValue;
    protected transient int[][] fNodeParent;
    protected transient int[][] fNodeLastChild;
    protected transient int[][] fNodePrevSib;
    protected transient int[][] fNodeURI;
    protected transient int fIdCount;
    protected transient int[] fIdName;
    protected transient int[] fIdElement;
    protected transient StringPool fStringPool;
    protected boolean fNamespacesEnabled;
    
    public DeferredDocumentImpl(final StringPool stringPool) {
        this(stringPool, false);
    }
    
    public DeferredDocumentImpl(final StringPool stringPool, final boolean b) {
        this(stringPool, b, false);
    }
    
    public DeferredDocumentImpl(final StringPool fStringPool, final boolean fNamespacesEnabled, final boolean b) {
        super(b);
        this.fNodeCount = 0;
        this.fNamespacesEnabled = false;
        this.fStringPool = fStringPool;
        this.needsSyncData(true);
        this.needsSyncChildren(true);
        this.fNamespacesEnabled = fNamespacesEnabled;
    }
    
    boolean getNamespacesEnabled() {
        return this.fNamespacesEnabled;
    }
    
    public int createDocument() {
        return this.createNode((short)9);
    }
    
    public int createDocumentType(final int n, final int n2, final int n3) {
        final int node = this.createNode((short)10);
        final int n4 = node >> 11;
        final int n5 = node & 0x7FF;
        final int node2 = this.createNode((short)0);
        final int n6 = node2 >> 11;
        final int n7 = node2 & 0x7FF;
        this.setChunkIndex(this.fNodeName, n, n4, n5);
        this.setChunkIndex(this.fNodeValue, node2, n4, n5);
        this.setChunkIndex(this.fNodeName, n2, n6, n7);
        this.setChunkIndex(this.fNodeValue, n3, n6, n7);
        return node;
    }
    
    public void setInternalSubset(final int n, final int n2) {
        final int n3 = this.fNodeValue[n >> 11][n & 0x7FF];
        this.fNodeLastChild[n3 >> 11][n3 & 0x7FF] = n2;
    }
    
    public int createNotation(final int n, final int n2, final int n3) throws Exception {
        final int node = this.createNode((short)12);
        final int n4 = node >> 11;
        final int n5 = node & 0x7FF;
        final int node2 = this.createNode((short)0);
        final int n6 = node2 >> 11;
        final int n7 = node2 & 0x7FF;
        this.setChunkIndex(this.fNodeName, n, n4, n5);
        this.setChunkIndex(this.fNodeValue, node2, n4, n5);
        this.setChunkIndex(this.fNodeName, n2, n6, n7);
        this.setChunkIndex(this.fNodeValue, n3, n6, n7);
        return node;
    }
    
    public int createEntity(final int n, final int n2, final int n3, final int n4) throws Exception {
        final int node = this.createNode((short)6);
        final int n5 = node >> 11;
        final int n6 = node & 0x7FF;
        final int node2 = this.createNode((short)0);
        final int n7 = node2 >> 11;
        final int n8 = node2 & 0x7FF;
        final int node3 = this.createNode((short)0);
        final int n9 = node3 >> 11;
        final int n10 = node3 & 0x7FF;
        this.setChunkIndex(this.fNodeName, n, n5, n6);
        this.setChunkIndex(this.fNodeValue, node2, n5, n6);
        this.setChunkIndex(this.fNodeLastChild, n4, n7, n8);
        this.setChunkIndex(this.fNodeName, n2, n7, n8);
        this.setChunkIndex(this.fNodeValue, node3, n7, n8);
        this.setChunkIndex(this.fNodeName, n3, n9, n10);
        this.setChunkIndex(this.fNodeValue, -1, n9, n10);
        this.setChunkIndex(this.fNodeLastChild, -1, n9, n10);
        return node;
    }
    
    public void setEntityInfo(final int n, final int n2, final int n3) {
        final int nodeValue = this.getNodeValue(this.getNodeValue(n, false), false);
        if (nodeValue != -1) {
            final int n4 = nodeValue >> 11;
            final int n5 = nodeValue & 0x7FF;
            this.setChunkIndex(this.fNodeValue, n2, n4, n5);
            this.setChunkIndex(this.fNodeLastChild, n3, n4, n5);
        }
    }
    
    public int createEntityReference(final int n) throws Exception {
        final int node = this.createNode((short)5);
        this.setChunkIndex(this.fNodeName, n, node >> 11, node & 0x7FF);
        return node;
    }
    
    public int createElement(final int n, final XMLAttrList list, final int n2) {
        return this.createElement(n, -1, list, n2);
    }
    
    public int createElement(final int n, final int n2, final XMLAttrList list, final int n3) {
        final int node = this.createNode((short)1);
        final int n4 = node >> 11;
        final int n5 = node & 0x7FF;
        this.setChunkIndex(this.fNodeName, n, n4, n5);
        this.setChunkIndex(this.fNodeURI, n2, n4, n5);
        if (n3 != -1) {
            final int firstAttr = list.getFirstAttr(n3);
            int n6 = -1;
            int n7 = -1;
            for (int i = firstAttr; i != -1; i = list.getNextAttr(i)) {
                final int attribute = this.createAttribute(list.getAttrName(i), list.getAttrURI(i), list.getAttValue(i), list.isSpecified(i));
                final int n8 = attribute >> 11;
                final int n9 = attribute & 0x7FF;
                this.setChunkIndex(this.fNodeParent, node, n8, n9);
                if (i == firstAttr) {
                    this.setChunkIndex(this.fNodeValue, attribute, n4, n5);
                }
                else {
                    this.setChunkIndex(this.fNodePrevSib, attribute, n6, n7);
                }
                n6 = n8;
                n7 = n9;
            }
        }
        return node;
    }
    
    public int createAttribute(final int n, final int n2, final boolean b) {
        return this.createAttribute(n, -1, n2, b);
    }
    
    public int createAttribute(final int n, final int n2, final int n3, final boolean b) {
        final int node = this.createNode((short)2);
        final int n4 = node >> 11;
        final int n5 = node & 0x7FF;
        this.setChunkIndex(this.fNodeName, n, n4, n5);
        this.setChunkIndex(this.fNodeURI, n2, n4, n5);
        this.setChunkIndex(this.fNodeValue, b ? 1 : 0, n4, n5);
        this.appendChild(node, this.createTextNode(n3, false));
        return node;
    }
    
    public int createElementDefinition(final int n) {
        final int node = this.createNode((short)(-1));
        this.setChunkIndex(this.fNodeName, n, node >> 11, node & 0x7FF);
        return node;
    }
    
    public int createTextNode(final int n, final boolean b) {
        final int node = this.createNode((short)3);
        final int n2 = node >> 11;
        final int n3 = node & 0x7FF;
        this.setChunkIndex(this.fNodeValue, n, n2, n3);
        this.setChunkIndex(this.fNodeLastChild, b ? 1 : 0, n2, n3);
        return node;
    }
    
    public int createCDATASection(final int n, final boolean b) {
        final int node = this.createNode((short)4);
        final int n2 = node >> 11;
        final int n3 = node & 0x7FF;
        this.setChunkIndex(this.fNodeValue, n, n2, n3);
        this.setChunkIndex(this.fNodeLastChild, b ? 1 : 0, n2, n3);
        return node;
    }
    
    public int createProcessingInstruction(final int n, final int n2) {
        final int node = this.createNode((short)7);
        final int n3 = node >> 11;
        final int n4 = node & 0x7FF;
        this.setChunkIndex(this.fNodeName, n, n3, n4);
        this.setChunkIndex(this.fNodeValue, n2, n3, n4);
        return node;
    }
    
    public int createComment(final int n) {
        final int node = this.createNode((short)8);
        this.setChunkIndex(this.fNodeValue, n, node >> 11, node & 0x7FF);
        return node;
    }
    
    public void appendChild(final int n, final int n2) {
        final int n3 = n >> 11;
        final int n4 = n & 0x7FF;
        final int n5 = n2 >> 11;
        final int n6 = n2 & 0x7FF;
        this.setChunkIndex(this.fNodeParent, n, n5, n6);
        this.setChunkIndex(this.fNodePrevSib, this.getChunkIndex(this.fNodeLastChild, n3, n4), n5, n6);
        this.setChunkIndex(this.fNodeLastChild, n2, n3, n4);
    }
    
    public int setAttributeNode(final int n, final int n2) {
        final int n3 = n >> 11;
        final int n4 = n & 0x7FF;
        final int n5 = n2 >> 11;
        final int n6 = n2 & 0x7FF;
        final String string = this.fStringPool.toString(this.getChunkIndex(this.fNodeName, n5, n6));
        int i = this.getChunkIndex(this.fNodeValue, n3, n4);
        int n7 = -1;
        int n8;
        int n9;
        for (n8 = -1, n9 = -1; i != -1; i = this.getChunkIndex(this.fNodePrevSib, n8, n9)) {
            n8 = i >> 11;
            n9 = (i & 0x7FF);
            if (this.fStringPool.toString(this.getChunkIndex(this.fNodeName, n8, n9)).equals(string)) {
                break;
            }
            n7 = i;
        }
        if (i != -1) {
            final int chunkIndex = this.getChunkIndex(this.fNodePrevSib, n8, n9);
            if (n7 == -1) {
                this.setChunkIndex(this.fNodeValue, chunkIndex, n3, n4);
            }
            else {
                this.setChunkIndex(this.fNodePrevSib, chunkIndex, n7 >> 11, n7 & 0x7FF);
            }
            this.clearChunkIndex(this.fNodeType, n8, n9);
            this.clearChunkIndex(this.fNodeName, n8, n9);
            this.clearChunkIndex(this.fNodeValue, n8, n9);
            this.clearChunkIndex(this.fNodeParent, n8, n9);
            this.clearChunkIndex(this.fNodePrevSib, n8, n9);
            final int clearChunkIndex = this.clearChunkIndex(this.fNodeLastChild, n8, n9);
            final int n10 = clearChunkIndex >> 11;
            final int n11 = clearChunkIndex & 0x7FF;
            this.clearChunkIndex(this.fNodeType, n10, n11);
            this.clearChunkIndex(this.fNodeValue, n10, n11);
            this.clearChunkIndex(this.fNodeParent, n10, n11);
            this.clearChunkIndex(this.fNodeLastChild, n10, n11);
        }
        final int chunkIndex2 = this.getChunkIndex(this.fNodeValue, n3, n4);
        this.setChunkIndex(this.fNodeValue, n2, n3, n4);
        this.setChunkIndex(this.fNodePrevSib, chunkIndex2, n5, n6);
        return i;
    }
    
    public int insertBefore(final int n, final int n2, final int n3) {
        if (n3 == -1) {
            this.appendChild(n, n2);
            return n2;
        }
        final int n4 = n2 >> 11;
        final int n5 = n2 & 0x7FF;
        final int n6 = n3 >> 11;
        final int n7 = n3 & 0x7FF;
        final int chunkIndex = this.getChunkIndex(this.fNodePrevSib, n6, n7);
        this.setChunkIndex(this.fNodePrevSib, n2, n6, n7);
        this.setChunkIndex(this.fNodePrevSib, chunkIndex, n4, n5);
        return n2;
    }
    
    public void setAsLastChild(final int n, final int n2) {
        this.setChunkIndex(this.fNodeLastChild, n2, n >> 11, n & 0x7FF);
    }
    
    public int getParentNode(final int n) {
        return this.getParentNode(n, false);
    }
    
    public int getParentNode(final int n, final boolean b) {
        if (n == -1) {
            return -1;
        }
        final int n2 = n >> 11;
        final int n3 = n & 0x7FF;
        return b ? this.clearChunkIndex(this.fNodeParent, n2, n3) : this.getChunkIndex(this.fNodeParent, n2, n3);
    }
    
    public int getLastChild(final int n) {
        return this.getLastChild(n, true);
    }
    
    public int getLastChild(final int n, final boolean b) {
        if (n == -1) {
            return -1;
        }
        final int n2 = n >> 11;
        final int n3 = n & 0x7FF;
        return b ? this.clearChunkIndex(this.fNodeLastChild, n2, n3) : this.getChunkIndex(this.fNodeLastChild, n2, n3);
    }
    
    public int getPrevSibling(final int n) {
        return this.getPrevSibling(n, true);
    }
    
    public int getPrevSibling(int n, final boolean b) {
        if (n == -1) {
            return -1;
        }
        int n2 = n >> 11;
        int n3 = n & 0x7FF;
        if (this.getChunkIndex(this.fNodeType, n2, n3) == 3) {
            do {
                n = this.getChunkIndex(this.fNodePrevSib, n2, n3);
                if (n == -1) {
                    break;
                }
                n2 = n >> 11;
                n3 = (n & 0x7FF);
            } while (this.getChunkIndex(this.fNodeType, n2, n3) == 3);
        }
        else {
            n = this.getChunkIndex(this.fNodePrevSib, n2, n3);
        }
        return n;
    }
    
    public int getRealPrevSibling(final int n) {
        return this.getRealPrevSibling(n, true);
    }
    
    public int getRealPrevSibling(final int n, final boolean b) {
        if (n == -1) {
            return -1;
        }
        final int n2 = n >> 11;
        final int n3 = n & 0x7FF;
        return b ? this.clearChunkIndex(this.fNodePrevSib, n2, n3) : this.getChunkIndex(this.fNodePrevSib, n2, n3);
    }
    
    public int lookupElementDefinition(final int n) {
        if (this.fNodeCount > 1) {
            int n2 = -1;
            int n3;
            int n4;
            for (int i = this.getChunkIndex(this.fNodeLastChild, 0, 0); i != -1; i = this.getChunkIndex(this.fNodePrevSib, n3, n4)) {
                n3 = i >> 11;
                n4 = (i & 0x7FF);
                if (this.getChunkIndex(this.fNodeType, n3, n4) == 10) {
                    n2 = i;
                    break;
                }
            }
            if (n2 == -1) {
                return -1;
            }
            int n5;
            int n6;
            for (int j = this.getChunkIndex(this.fNodeLastChild, n2 >> 11, n2 & 0x7FF); j != -1; j = this.getChunkIndex(this.fNodePrevSib, n5, n6)) {
                n5 = j >> 11;
                n6 = (j & 0x7FF);
                if (this.getChunkIndex(this.fNodeName, n5, n6) == n) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    public DeferredNode getNodeObject(final int n) {
        if (n == -1) {
            return null;
        }
        final int n2 = n >> 11;
        final int n3 = n & 0x7FF;
        final int chunkIndex = this.getChunkIndex(this.fNodeType, n2, n3);
        if (chunkIndex != 3) {
            this.clearChunkIndex(this.fNodeType, n2, n3);
        }
        DeferredNode deferredNode = null;
        switch (chunkIndex) {
            case 2: {
                if (this.fNamespacesEnabled) {
                    deferredNode = new DeferredAttrNSImpl(this, n);
                    break;
                }
                deferredNode = new DeferredAttrImpl(this, n);
                break;
            }
            case 4: {
                deferredNode = new DeferredCDATASectionImpl(this, n);
                break;
            }
            case 8: {
                deferredNode = new DeferredCommentImpl(this, n);
                break;
            }
            case 9: {
                deferredNode = this;
                break;
            }
            case 10: {
                deferredNode = new DeferredDocumentTypeImpl(this, n);
                super.docType = (DocumentTypeImpl)deferredNode;
                break;
            }
            case 1: {
                if (this.fNamespacesEnabled) {
                    deferredNode = new DeferredElementNSImpl(this, n);
                }
                else {
                    deferredNode = new DeferredElementImpl(this, n);
                }
                if (super.docElement == null) {
                    super.docElement = (ElementImpl)deferredNode;
                }
                if (this.fIdElement != null) {
                    int i = binarySearch(this.fIdElement, 0, this.fIdCount - 1, n);
                    while (i != -1) {
                        final int n4 = this.fIdName[i];
                        if (n4 != -1) {
                            this.putIdentifier0(this.fStringPool.toString(n4), (Element)deferredNode);
                            this.fIdName[i] = -1;
                        }
                        if (i + 1 < this.fIdCount && this.fIdElement[i + 1] == n) {
                            ++i;
                        }
                        else {
                            i = -1;
                        }
                    }
                    break;
                }
                break;
            }
            case 6: {
                deferredNode = new DeferredEntityImpl(this, n);
                break;
            }
            case 5: {
                deferredNode = new DeferredEntityReferenceImpl(this, n);
                break;
            }
            case 12: {
                deferredNode = new DeferredNotationImpl(this, n);
                break;
            }
            case 7: {
                deferredNode = new DeferredProcessingInstructionImpl(this, n);
                break;
            }
            case 3: {
                deferredNode = new DeferredTextImpl(this, n);
                break;
            }
            case -1: {
                deferredNode = new DeferredElementDefinitionImpl(this, n);
                break;
            }
            default: {
                throw new IllegalArgumentException("type: " + chunkIndex);
            }
        }
        if (deferredNode != null) {
            return deferredNode;
        }
        throw new IllegalArgumentException();
    }
    
    public String getNodeNameString(final int n) {
        return this.getNodeNameString(n, true);
    }
    
    public String getNodeNameString(final int n, final boolean b) {
        if (n == -1) {
            return null;
        }
        final int n2 = n >> 11;
        final int n3 = n & 0x7FF;
        final int n4 = b ? this.clearChunkIndex(this.fNodeName, n2, n3) : this.getChunkIndex(this.fNodeName, n2, n3);
        if (n4 == -1) {
            return null;
        }
        return this.fStringPool.toString(n4);
    }
    
    public String getNodeValueString(final int n) {
        return this.getNodeValueString(n, true);
    }
    
    public String getNodeValueString(final int n, final boolean b) {
        if (n == -1) {
            return null;
        }
        final int n2 = n >> 11;
        final int n3 = n & 0x7FF;
        final int n4 = b ? this.clearChunkIndex(this.fNodeValue, n2, n3) : this.getChunkIndex(this.fNodeValue, n2, n3);
        if (n4 == -1) {
            return null;
        }
        if (this.getChunkIndex(this.fNodeType, n2, n3) == 3) {
            int n5 = this.getRealPrevSibling(n);
            if (n5 != -1 && this.getNodeType(n5, false) == 3) {
                final StringBuffer sb = new StringBuffer();
                sb.append(this.fStringPool.toString(n4));
                do {
                    final int n6 = n5 >> 11;
                    final int n7 = n5 & 0x7FF;
                    sb.insert(0, this.fStringPool.toString(this.getChunkIndex(this.fNodeValue, n6, n7)));
                    n5 = this.getChunkIndex(this.fNodePrevSib, n6, n7);
                    if (n5 == -1) {
                        break;
                    }
                } while (this.getNodeType(n5, false) == 3);
                return sb.toString();
            }
        }
        return this.fStringPool.toString(n4);
    }
    
    public int getNodeName(final int n) {
        return this.getNodeName(n, true);
    }
    
    public int getNodeName(final int n, final boolean b) {
        if (n == -1) {
            return -1;
        }
        final int n2 = n >> 11;
        final int n3 = n & 0x7FF;
        return b ? this.clearChunkIndex(this.fNodeName, n2, n3) : this.getChunkIndex(this.fNodeName, n2, n3);
    }
    
    public int getNodeValue(final int n) {
        return this.getNodeValue(n, true);
    }
    
    public int getNodeValue(final int n, final boolean b) {
        if (n == -1) {
            return -1;
        }
        final int n2 = n >> 11;
        final int n3 = n & 0x7FF;
        return b ? this.clearChunkIndex(this.fNodeValue, n2, n3) : this.getChunkIndex(this.fNodeValue, n2, n3);
    }
    
    public short getNodeType(final int n) {
        return this.getNodeType(n, true);
    }
    
    public short getNodeType(final int n, final boolean b) {
        if (n == -1) {
            return -1;
        }
        final int n2 = n >> 11;
        final int n3 = n & 0x7FF;
        if (b) {
            return (short)this.clearChunkIndex(this.fNodeType, n2, n3);
        }
        return (short)this.getChunkIndex(this.fNodeType, n2, n3);
    }
    
    public int getAttribute(final int n, final int n2) {
        if (n == -1 || n2 == -1) {
            return -1;
        }
        int n3;
        int n4;
        for (int i = this.getChunkIndex(this.fNodeValue, n >> 11, n & 0x7FF); i != -1; i = this.getChunkIndex(this.fNodePrevSib, n3, n4)) {
            n3 = i >> 11;
            n4 = (i & 0x7FF);
            if (this.getChunkIndex(this.fNodeName, n3, n4) == n2) {
                return this.getChunkIndex(this.fNodeValue, n3, n4);
            }
        }
        return -1;
    }
    
    public short getNodeURI(final int n) {
        return this.getNodeURI(n, true);
    }
    
    public short getNodeURI(final int n, final boolean b) {
        if (n == -1) {
            return -1;
        }
        final int n2 = n >> 11;
        final int n3 = n & 0x7FF;
        if (b) {
            return (short)this.clearChunkIndex(this.fNodeURI, n2, n3);
        }
        return (short)this.getChunkIndex(this.fNodeURI, n2, n3);
    }
    
    public void putIdentifier(final int n, final int n2) {
        if (this.fIdName == null) {
            this.fIdName = new int[64];
            this.fIdElement = new int[64];
        }
        if (this.fIdCount == this.fIdName.length) {
            final int[] fIdName = new int[this.fIdCount * 2];
            System.arraycopy(this.fIdName, 0, fIdName, 0, this.fIdCount);
            this.fIdName = fIdName;
            final int[] fIdElement = new int[fIdName.length];
            System.arraycopy(this.fIdElement, 0, fIdElement, 0, this.fIdCount);
            this.fIdElement = fIdElement;
        }
        this.fIdName[this.fIdCount] = n;
        this.fIdElement[this.fIdCount] = n2;
        ++this.fIdCount;
    }
    
    public void print() {
    }
    
    public int getNodeIndex() {
        return 0;
    }
    
    protected StringPool getStringPool() {
        return this.fStringPool;
    }
    
    protected void synchronizeData() {
        this.needsSyncData(false);
        if (this.fIdElement != null) {
            final IntVector intVector = new IntVector();
            for (int i = 0; i < this.fIdCount; ++i) {
                final int n = this.fIdElement[i];
                final int n2 = this.fIdName[i];
                if (n2 != -1) {
                    intVector.removeAllElements();
                    int j = n;
                    do {
                        intVector.addElement(j);
                        j = this.getChunkIndex(this.fNodeParent, j >> 11, j & 0x7FF);
                    } while (j != -1);
                    Object o = this;
                    for (int k = intVector.size() - 2; k >= 0; --k) {
                        final int element = intVector.elementAt(k);
                        for (Node node = ((Node)o).getLastChild(); node != null; node = node.getPreviousSibling()) {
                            if (node instanceof DeferredNode && ((DeferredNode)node).getNodeIndex() == element) {
                                o = node;
                                break;
                            }
                        }
                    }
                    final Element element2 = (Element)o;
                    this.putIdentifier0(this.fStringPool.toString(n2), element2);
                    this.fIdName[i] = -1;
                    while (i + 1 < this.fIdCount && this.fIdElement[i + 1] == n) {
                        final int n3 = this.fIdName[++i];
                        if (n3 == -1) {
                            continue;
                        }
                        this.putIdentifier0(this.fStringPool.toString(n3), element2);
                    }
                }
            }
        }
    }
    
    protected void synchronizeChildren() {
        if (this.needsSyncData()) {
            this.synchronizeData();
            if (!this.needsSyncChildren()) {
                return;
            }
        }
        final boolean mutationEvents = super.mutationEvents;
        this.needsSyncChildren(super.mutationEvents = false);
        this.getNodeType(0);
        ChildNode childNode = null;
        ChildNode childNode2 = null;
        for (int i = this.getLastChild(0); i != -1; i = this.getPrevSibling(i)) {
            final ChildNode previousSibling = (ChildNode)this.getNodeObject(i);
            if (childNode2 == null) {
                childNode2 = previousSibling;
            }
            else {
                childNode.previousSibling = previousSibling;
            }
            previousSibling.ownerNode = this;
            previousSibling.isOwned(true);
            previousSibling.nextSibling = childNode;
            childNode = previousSibling;
            final short nodeType = previousSibling.getNodeType();
            if (nodeType == 1) {
                super.docElement = (ElementImpl)previousSibling;
            }
            else if (nodeType == 10) {
                super.docType = (DocumentTypeImpl)previousSibling;
            }
        }
        if (childNode != null) {
            (super.firstChild = childNode).isFirstChild(true);
            this.lastChild(childNode2);
        }
        super.mutationEvents = mutationEvents;
    }
    
    protected boolean ensureCapacity(final int n, final int n2) {
        if (this.fNodeType == null) {
            this.fNodeType = new int[32][];
            this.fNodeName = new int[32][];
            this.fNodeValue = new int[32][];
            this.fNodeParent = new int[32][];
            this.fNodeLastChild = new int[32][];
            this.fNodePrevSib = new int[32][];
            this.fNodeURI = new int[32][];
        }
        try {
            return this.fNodeType[n][n2] != 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            final int n3 = n * 2;
            final int[][] fNodeType = new int[n3][];
            System.arraycopy(this.fNodeType, 0, fNodeType, 0, n);
            this.fNodeType = fNodeType;
            final int[][] fNodeName = new int[n3][];
            System.arraycopy(this.fNodeName, 0, fNodeName, 0, n);
            this.fNodeName = fNodeName;
            final int[][] fNodeValue = new int[n3][];
            System.arraycopy(this.fNodeValue, 0, fNodeValue, 0, n);
            this.fNodeValue = fNodeValue;
            final int[][] fNodeParent = new int[n3][];
            System.arraycopy(this.fNodeParent, 0, fNodeParent, 0, n);
            this.fNodeParent = fNodeParent;
            final int[][] fNodeLastChild = new int[n3][];
            System.arraycopy(this.fNodeLastChild, 0, fNodeLastChild, 0, n);
            this.fNodeLastChild = fNodeLastChild;
            final int[][] fNodePrevSib = new int[n3][];
            System.arraycopy(this.fNodePrevSib, 0, fNodePrevSib, 0, n);
            this.fNodePrevSib = fNodePrevSib;
            final int[][] fNodeURI = new int[n3][];
            System.arraycopy(this.fNodeURI, 0, fNodeURI, 0, n);
            this.fNodeURI = fNodeURI;
        }
        catch (NullPointerException ex2) {}
        this.createChunk(this.fNodeType, n);
        this.createChunk(this.fNodeName, n);
        this.createChunk(this.fNodeValue, n);
        this.createChunk(this.fNodeParent, n);
        this.createChunk(this.fNodeLastChild, n);
        this.createChunk(this.fNodePrevSib, n);
        this.createChunk(this.fNodeURI, n);
        return true;
    }
    
    protected int createNode(final short n) {
        final int n2 = this.fNodeCount >> 11;
        final int n3 = this.fNodeCount & 0x7FF;
        this.ensureCapacity(n2, n3);
        this.setChunkIndex(this.fNodeType, n, n2, n3);
        return this.fNodeCount++;
    }
    
    protected static int binarySearch(final int[] array, int i, int n, final int n2) {
        while (i <= n) {
            int n3 = (i + n) / 2;
            final int n4 = array[n3];
            if (n4 == n2) {
                while (n3 > 0 && array[n3 - 1] == n2) {
                    --n3;
                }
                return n3;
            }
            if (n4 > n2) {
                n = n3 - 1;
            }
            else {
                i = n3 + 1;
            }
        }
        return -1;
    }
    
    private final void createChunk(final int[][] array, final int n) {
        array[n] = new int[2050];
        for (int i = 0; i < 2048; ++i) {
            array[n][i] = -1;
        }
    }
    
    private final int setChunkIndex(final int[][] array, final int n, final int n2, final int n3) {
        if (n == -1) {
            return this.clearChunkIndex(array, n2, n3);
        }
        final int n4 = array[n2][n3];
        if (n4 == -1) {
            final int[] array2 = array[n2];
            final int n5 = 2048;
            ++array2[n5];
        }
        array[n2][n3] = n;
        return n4;
    }
    
    private final int getChunkIndex(final int[][] array, final int n, final int n2) {
        return (array[n] != null) ? array[n][n2] : -1;
    }
    
    private final int clearChunkIndex(final int[][] array, final int n, final int n2) {
        final int n3 = (array[n] != null) ? array[n][n2] : -1;
        if (n3 != -1) {
            final int[] array2 = array[n];
            final int n4 = 2049;
            ++array2[n4];
            array[n][n2] = -1;
            if (array[n][2048] == array[n][2049]) {
                array[n] = null;
            }
        }
        return n3;
    }
    
    private final void putIdentifier0(final String s, final Element element) {
        if (super.identifiers == null) {
            super.identifiers = new Hashtable();
        }
        super.identifiers.put(s, element);
    }
    
    private static void print(final int[] array, final int n, final int n2, final int n3, final int n4) {
    }
    
    static class IntVector
    {
        private int[] data;
        private int size;
        
        public int size() {
            return this.size;
        }
        
        public int elementAt(final int n) {
            return this.data[n];
        }
        
        public void addElement(final int n) {
            this.ensureCapacity(this.size + 1);
            this.data[this.size++] = n;
        }
        
        public void removeAllElements() {
            this.size = 0;
        }
        
        private void ensureCapacity(final int n) {
            if (this.data == null) {
                this.data = new int[n + 15];
            }
            else if (n > this.data.length) {
                final int[] data = new int[n + 15];
                System.arraycopy(this.data, 0, data, 0, this.data.length);
                this.data = data;
            }
        }
    }
}
