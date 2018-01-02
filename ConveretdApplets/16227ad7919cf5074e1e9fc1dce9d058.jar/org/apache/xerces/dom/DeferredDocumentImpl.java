// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import java.util.Hashtable;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.apache.xerces.framework.XMLAttrList;
import org.apache.xerces.utils.StringPool;

public class DeferredDocumentImpl extends DocumentImpl
{
    static final long serialVersionUID = 5186323580749626857L;
    private static final boolean DEBUG_PRINT_TABLES = false;
    private static final boolean DEBUG_IDS = false;
    protected static final int CHUNK_SHIFT = 11;
    protected static final int CHUNK_SIZE = 2048;
    protected static final int CHUNK_MASK = 2047;
    protected static final int INITIAL_CHUNK_COUNT = 32;
    protected transient int fNodeCount;
    protected transient byte[][] fNodeType;
    protected transient int[][] fNodeName;
    protected transient int[][] fNodeValue;
    protected transient int[][] fNodeParent;
    protected transient int[][] fNodeFirstChild;
    protected transient int[][] fNodeLastChild;
    protected transient int[][] fNodePrevSib;
    protected transient int[][] fNodeNextSib;
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
        this.fNamespacesEnabled = false;
        this.fStringPool = fStringPool;
        super.syncData = true;
        super.syncChildren = true;
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
        this.fNodeName[n4][n5] = n;
        final int node2 = this.createNode((short)3);
        final int n6 = node2 >> 11;
        final int n7 = node2 & 0x7FF;
        this.fNodeValue[n4][n5] = node2;
        this.fNodeFirstChild[n6][n7] = n2;
        this.fNodeLastChild[n6][n7] = n3;
        return node;
    }
    
    public int createNotation(final int n, final int n2, final int n3) throws Exception {
        final int node = this.createNode((short)12);
        final int n4 = node >> 11;
        final int n5 = node & 0x7FF;
        final int node2 = this.createNode((short)3);
        final int n6 = node2 >> 11;
        final int n7 = node2 & 0x7FF;
        this.fNodeValue[n4][n5] = node2;
        this.fNodeName[n4][n5] = n;
        this.fNodeFirstChild[n6][n7] = n2;
        this.fNodeLastChild[n6][n7] = n3;
        return node;
    }
    
    public int createEntity(final int n, final int n2, final int n3, final int n4) throws Exception {
        final int node = this.createNode((short)6);
        final int n5 = node >> 11;
        final int n6 = node & 0x7FF;
        final int node2 = this.createNode((short)3);
        final int n7 = node2 >> 11;
        final int n8 = node2 & 0x7FF;
        this.fNodeValue[n5][n6] = node2;
        this.fNodeName[n5][n6] = n;
        this.fNodeFirstChild[n7][n8] = n2;
        this.fNodeLastChild[n7][n8] = n3;
        this.fNodePrevSib[n7][n8] = n4;
        return node;
    }
    
    public int createEntityReference(final int n) throws Exception {
        final int node = this.createNode((short)5);
        this.fNodeName[node >> 11][node & 0x7FF] = n;
        return node;
    }
    
    public int createElement(final int n, final XMLAttrList list, final int n2) {
        final int node = this.createNode((short)1);
        final int n3 = node >> 11;
        final int n4 = node & 0x7FF;
        this.fNodeName[n3][n4] = n;
        if (n2 != -1) {
            final int firstAttr = list.getFirstAttr(n2);
            int n5 = -1;
            int n6 = -1;
            int n7 = -1;
            for (int i = firstAttr; i != -1; i = list.getNextAttr(i)) {
                final int attribute = this.createAttribute(list.getAttrName(i), list.getAttValue(i), list.isSpecified(i));
                final int n8 = attribute >> 11;
                final int n9 = attribute & 0x7FF;
                this.fNodeParent[n8][n9] = node;
                if (i == firstAttr) {
                    this.fNodeValue[n3][n4] = attribute;
                }
                else {
                    this.fNodeNextSib[n6][n7] = attribute;
                    this.fNodePrevSib[n8][n9] = n5;
                }
                n5 = attribute;
                n6 = n8;
                n7 = n9;
            }
        }
        return node;
    }
    
    public int createAttribute(final int n, final int n2, final boolean b) {
        final int node = this.createNode((short)2);
        final int n3 = node >> 11;
        final int n4 = node & 0x7FF;
        this.fNodeName[n3][n4] = n;
        this.fNodeValue[n3][n4] = (b ? 1 : 0);
        this.appendChild(node, this.createTextNode(n2, false));
        return node;
    }
    
    public int createElementDefinition(final int n) {
        final int node = this.createNode((short)(-1));
        this.fNodeName[node >> 11][node & 0x7FF] = n;
        return node;
    }
    
    public int createTextNode(final int n, final boolean b) {
        final int node = this.createNode((short)3);
        final int n2 = node >> 11;
        final int n3 = node & 0x7FF;
        this.fNodeValue[n2][n3] = n;
        this.fNodeFirstChild[n2][n3] = (b ? 1 : 0);
        return node;
    }
    
    public int createCDATASection(final int n, final boolean b) {
        final int node = this.createNode((short)4);
        final int n2 = node >> 11;
        final int n3 = node & 0x7FF;
        this.fNodeValue[n2][n3] = n;
        this.fNodeFirstChild[n2][n3] = (b ? 1 : 0);
        return node;
    }
    
    public int createProcessingInstruction(final int n, final int n2) {
        final int node = this.createNode((short)7);
        final int n3 = node >> 11;
        final int n4 = node & 0x7FF;
        this.fNodeName[n3][n4] = n;
        this.fNodeValue[n3][n4] = n2;
        return node;
    }
    
    public int createComment(final int n) {
        final int node = this.createNode((short)8);
        this.fNodeValue[node >> 11][node & 0x7FF] = n;
        return node;
    }
    
    public void appendChild(final int n, final int n2) {
        final int n3 = n >> 11;
        final int n4 = n & 0x7FF;
        final int n5 = n2 >> 11;
        final int n6 = n2 & 0x7FF;
        this.fNodeParent[n5][n6] = n;
        final int n7 = this.fNodeLastChild[n3][n4];
        this.fNodePrevSib[n5][n6] = n7;
        if (n7 == -1) {
            this.fNodeFirstChild[n3][n4] = n2;
        }
        else {
            this.fNodeNextSib[n7 >> 11][n7 & 0x7FF] = n2;
        }
        this.fNodeLastChild[n3][n4] = n2;
    }
    
    public int setAttributeNode(final int n, final int n2) {
        final int n3 = n >> 11;
        final int n4 = n & 0x7FF;
        final int n5 = n2 >> 11;
        final int n6 = n2 & 0x7FF;
        final String string = this.fStringPool.toString(this.fNodeName[n5][n6]);
        int i;
        int n7;
        int n8;
        for (i = this.fNodeValue[n3][n4], n7 = -1, n8 = -1; i != -1; i = this.fNodeNextSib[n7][n8]) {
            n7 = i >> 11;
            n8 = (i & 0x7FF);
            if (this.fStringPool.toString(this.fNodeName[n7][n8]).equals(string)) {
                break;
            }
        }
        if (i != -1) {
            final int n9 = this.fNodePrevSib[n7][n8];
            final int n10 = this.fNodeNextSib[n7][n8];
            if (n9 == -1) {
                this.fNodeValue[n3][n4] = n10;
            }
            else {
                this.fNodeNextSib[n9 >> 11][n9 & 0x7FF] = n10;
            }
            if (n10 != -1) {
                this.fNodePrevSib[n10 >> 11][n10 & 0x7FF] = n9;
            }
            this.fNodePrevSib[n7][n8] = -1;
            this.fNodeNextSib[n7][n8] = -1;
        }
        final int n11 = this.fNodeValue[n3][n4];
        this.fNodeValue[n3][n4] = n2;
        if ((this.fNodeNextSib[n5][n6] = n11) != -1) {
            this.fNodePrevSib[n11 >> 11][n11 & 0x7FF] = n2;
        }
        return i;
    }
    
    public int insertBefore(final int n, final int n2, final int n3) {
        if (n3 == -1) {
            this.appendChild(n, n2);
            return n2;
        }
        final int n4 = n >> 11;
        final int n5 = n & 0x7FF;
        final int n6 = n2 >> 11;
        final int n7 = n2 & 0x7FF;
        final int n8 = n3 >> 11;
        final int n9 = n3 & 0x7FF;
        if (this.getFirstChild(n) == n3) {
            this.fNodeFirstChild[n4][n5] = n2;
        }
        final int previousSibling = this.getPreviousSibling(n3);
        if (previousSibling != -1) {
            this.fNodeNextSib[previousSibling >> 11][previousSibling & 0x7FF] = n2;
        }
        this.fNodePrevSib[n6][n7] = previousSibling;
        this.fNodeNextSib[n6][n7] = n3;
        return this.fNodePrevSib[n8][n9] = n2;
    }
    
    public void setAsFirstChild(final int n, int n2) {
        final int n3 = n >> 11;
        final int n4 = n & 0x7FF;
        int n5 = n2 >> 11;
        int n6 = n2 & 0x7FF;
        this.fNodeFirstChild[n3][n4] = n2;
        for (int i = n2; i != -1; i = this.fNodeNextSib[n5][n6], n5 = i >> 11, n6 = (i & 0x7FF)) {
            n2 = i;
        }
        this.fNodeLastChild[n3][n4] = n2;
    }
    
    public int getParentNode(final int n) {
        if (n == -1) {
            return -1;
        }
        return this.fNodeParent[n >> 11][n & 0x7FF];
    }
    
    public int getFirstChild(final int n) {
        if (n == -1) {
            return -1;
        }
        return this.fNodeFirstChild[n >> 11][n & 0x7FF];
    }
    
    public int getLastChild(int n) {
        if (n == -1) {
            return -1;
        }
        final int n2 = n >> 11;
        final int n3 = n & 0x7FF;
        final int n4 = this.fNodeLastChild[n2][n3];
        if (n4 != -1 && this.fNodeType[n2][n3] == 3) {
            int n5 = this.fNodePrevSib[n2][n3];
            int n6 = n5 >> 11;
            int n7 = n5 & 0x7FF;
            if (n5 != -1 && this.fNodeType[n6][n7] == 3) {
                while (n5 != -1 && this.fNodeType[n6][n7] == 3) {
                    n = n5;
                    n5 = this.fNodePrevSib[n6][n7];
                    n6 = n5 >> 11;
                    n7 = (n5 & 0x7FF);
                }
                return n;
            }
        }
        return n4;
    }
    
    public int getPreviousSibling(int n) {
        if (n == -1) {
            return -1;
        }
        final int n2 = n >> 11;
        final int n3 = n & 0x7FF;
        int n4 = this.fNodePrevSib[n2][n3];
        if (n4 != -1 && this.fNodeType[n2][n3] != 3) {
            int n5 = n4 >> 11;
            int n6 = n4 & 0x7FF;
            if (this.fNodeType[n5][n6] == 3) {
                while (n4 != -1 && this.fNodeType[n5][n6] == 3) {
                    n = n4;
                    n4 = this.fNodePrevSib[n5][n6];
                    n5 = n4 >> 11;
                    n6 = (n4 & 0x7FF);
                }
                return n;
            }
        }
        return n4;
    }
    
    public int getNextSibling(int n) {
        if (n == -1) {
            return -1;
        }
        int n2;
        int n3;
        for (n2 = n >> 11, n3 = (n & 0x7FF), n = this.fNodeNextSib[n2][n3]; n != -1 && this.fNodeType[n2][n3] == 3; n = this.fNodeNextSib[n2][n3], n2 = n >> 11, n3 = (n & 0x7FF)) {}
        return n;
    }
    
    public int getRealNextSibling(final int n) {
        if (n == -1) {
            return -1;
        }
        return this.fNodeNextSib[n >> 11][n & 0x7FF];
    }
    
    public int lookupElementDefinition(final int n) {
        if (this.fNodeCount > 1) {
            int n2 = -1;
            for (int i = this.getFirstChild(0); i != -1; i = this.getNextSibling(i)) {
                if (this.getNodeType(i) == 10) {
                    n2 = i;
                    break;
                }
            }
            for (int j = this.getFirstChild(n2); j != -1; j = this.getNextSibling(j)) {
                if (this.getNodeName(j) == n) {
                    return j;
                }
            }
        }
        return -1;
    }
    
    public int getAttributeList(final int n) {
        if (n == -1) {
            return -1;
        }
        return this.fNodeValue[n >> 11][n & 0x7FF];
    }
    
    public DeferredNode getNodeObject(final int n) {
        if (n == -1) {
            return null;
        }
        final byte b = this.fNodeType[n >> 11][n & 0x7FF];
        NodeImpl nodeImpl = null;
        switch (b) {
            case 2: {
                nodeImpl = new DeferredAttrImpl(this, n);
                break;
            }
            case 4: {
                nodeImpl = new DeferredCDATASectionImpl(this, n);
                break;
            }
            case 8: {
                nodeImpl = new DeferredCommentImpl(this, n);
                break;
            }
            case 10: {
                nodeImpl = new DeferredDocumentTypeImpl(this, n);
                super.docType = (DocumentTypeImpl)nodeImpl;
                break;
            }
            case 1: {
                nodeImpl = new DeferredElementImpl(this, n);
                if (super.docElement == null) {
                    super.docElement = (ElementImpl)nodeImpl;
                }
                if (this.fIdElement != null) {
                    int i = binarySearch(this.fIdElement, 0, this.fIdCount, n);
                    while (i != -1) {
                        final int n2 = this.fIdName[i];
                        if (n2 != -1) {
                            this.putIdentifier0(this.fStringPool.toString(n2), (Element)nodeImpl);
                            this.fIdName[i] = -1;
                        }
                        if (i < this.fIdCount && this.fIdElement[i + 1] == n) {
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
                nodeImpl = new DeferredEntityImpl(this, n);
                break;
            }
            case 5: {
                nodeImpl = new DeferredEntityReferenceImpl(this, n);
                break;
            }
            case 12: {
                nodeImpl = new DeferredNotationImpl(this, n);
                break;
            }
            case 7: {
                nodeImpl = new DeferredProcessingInstructionImpl(this, n);
                break;
            }
            case 3: {
                nodeImpl = new DeferredTextImpl(this, n);
                break;
            }
            case -1: {
                nodeImpl = new DeferredElementDefinitionImpl(this, n);
                break;
            }
        }
        if (nodeImpl != null) {
            return (DeferredNode)nodeImpl;
        }
        throw new IllegalArgumentException();
    }
    
    public String getNodeNameString(final int n) {
        if (n == -1) {
            return null;
        }
        final int n2 = this.fNodeName[n >> 11][n & 0x7FF];
        if (n2 == -1) {
            return null;
        }
        return this.fStringPool.toString(n2);
    }
    
    public String getNodeValueString(final int n) {
        if (n == -1) {
            return null;
        }
        final int n2 = this.fNodeValue[n >> 11][n & 0x7FF];
        if (n2 == -1) {
            return null;
        }
        return this.fStringPool.toString(n2);
    }
    
    public int getNodeName(final int n) {
        if (n == -1) {
            return -1;
        }
        return this.fNodeName[n >> 11][n & 0x7FF];
    }
    
    public int getNodeValue(final int n) {
        if (n == -1) {
            return -1;
        }
        return this.fNodeValue[n >> 11][n & 0x7FF];
    }
    
    public short getNodeType(final int n) {
        if (n == -1) {
            return -1;
        }
        return this.fNodeType[n >> 11][n & 0x7FF];
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
    
    protected StringPool getStringPool() {
        return this.fStringPool;
    }
    
    protected void synchronizeData() {
        super.syncData = false;
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
                        j = this.getParentNode(j);
                    } while (j != -1);
                    Object o = this;
                    for (int k = intVector.size() - 2; k >= 0; --k) {
                        final int element = intVector.elementAt(k);
                        for (Node node = ((Node)o).getFirstChild(); node != null; node = node.getNextSibling()) {
                            if (node instanceof DeferredNode && ((DeferredNode)node).getNodeIndex() == element) {
                                o = node;
                                break;
                            }
                        }
                    }
                    final Element element2 = (Element)o;
                    this.putIdentifier0(this.fStringPool.toString(n2), element2);
                    this.fIdName[i] = -1;
                    while (this.fIdElement[i + 1] == n) {
                        this.putIdentifier0(this.fStringPool.toString(this.fIdName[++i]), element2);
                    }
                }
            }
        }
    }
    
    protected void synchronizeChildren() {
        super.syncChildren = false;
        NodeImpl nodeImpl = null;
        for (int i = this.getFirstChild(0); i != -1; i = this.getNextSibling(i)) {
            final NodeImpl nodeImpl2 = (NodeImpl)this.getNodeObject(i);
            if (nodeImpl == null) {
                super.firstChild = nodeImpl2;
            }
            else {
                nodeImpl.nextSibling = nodeImpl2;
            }
            nodeImpl2.parentNode = this;
            nodeImpl2.previousSibling = nodeImpl;
            nodeImpl = nodeImpl2;
            final short nodeType = nodeImpl2.getNodeType();
            if (nodeType == 1) {
                super.docElement = (ElementImpl)nodeImpl2;
            }
            else if (nodeType == 10) {
                super.docType = (DocumentTypeImpl)nodeImpl2;
            }
        }
        if (nodeImpl != null) {
            super.lastChild = nodeImpl;
        }
    }
    
    protected boolean ensureCapacity(final int n, final int n2) {
        if (this.fNodeType == null) {
            this.fNodeType = new byte[32][];
            this.fNodeName = new int[32][];
            this.fNodeValue = new int[32][];
            this.fNodeParent = new int[32][];
            this.fNodeFirstChild = new int[32][];
            this.fNodeLastChild = new int[32][];
            this.fNodePrevSib = new int[32][];
            this.fNodeNextSib = new int[32][];
        }
        try {
            return this.fNodeType[n][n2] != 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            final int n3 = n * 2;
            final byte[][] fNodeType = new byte[n3][];
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
            final int[][] fNodeFirstChild = new int[n3][];
            System.arraycopy(this.fNodeFirstChild, 0, fNodeFirstChild, 0, n);
            this.fNodeFirstChild = fNodeFirstChild;
            final int[][] fNodeLastChild = new int[n3][];
            System.arraycopy(this.fNodeLastChild, 0, fNodeLastChild, 0, n);
            this.fNodeLastChild = fNodeLastChild;
            final int[][] fNodePrevSib = new int[n3][];
            System.arraycopy(this.fNodePrevSib, 0, fNodePrevSib, 0, n);
            this.fNodePrevSib = fNodePrevSib;
            final int[][] fNodeNextSib = new int[n3][];
            System.arraycopy(this.fNodeNextSib, 0, fNodeNextSib, 0, n);
            this.fNodeNextSib = fNodeNextSib;
        }
        catch (NullPointerException ex2) {}
        this.fNodeType[n] = new byte[2048];
        this.fNodeName[n] = new int[2048];
        this.fNodeValue[n] = new int[2048];
        this.fNodeParent[n] = new int[2048];
        this.fNodeFirstChild[n] = new int[2048];
        this.fNodeLastChild[n] = new int[2048];
        this.fNodePrevSib[n] = new int[2048];
        this.fNodeNextSib[n] = new int[2048];
        return true;
    }
    
    protected int createNode(final short n) {
        final int n2 = this.fNodeCount >> 11;
        final int n3 = this.fNodeCount & 0x7FF;
        this.ensureCapacity(n2, n3);
        this.fNodeType[n2][n3] = (byte)n;
        this.fNodeName[n2][n3] = -1;
        this.fNodeValue[n2][n3] = -1;
        this.fNodeParent[n2][n3] = -1;
        this.fNodeFirstChild[n2][n3] = -1;
        this.fNodeLastChild[n2][n3] = -1;
        this.fNodePrevSib[n2][n3] = -1;
        this.fNodeNextSib[n2][n3] = -1;
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
    
    private void putIdentifier0(final String s, final Element element) {
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
                return;
            }
            if (n > this.data.length) {
                final int[] data = new int[n + 15];
                System.arraycopy(this.data, 0, data, 0, this.data.length);
                this.data = data;
            }
        }
    }
}
