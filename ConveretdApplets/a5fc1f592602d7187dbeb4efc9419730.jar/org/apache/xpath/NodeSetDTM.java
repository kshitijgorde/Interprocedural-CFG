// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import org.apache.xpath.res.XPATHMessages;
import org.apache.xml.dtm.DTM;
import org.apache.xml.dtm.DTMFilter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xml.dtm.DTMManager;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xml.utils.NodeVector;

public class NodeSetDTM extends NodeVector implements DTMIterator, Cloneable
{
    DTMManager m_manager;
    protected transient int m_next;
    protected transient boolean m_mutable;
    protected transient boolean m_cacheNodes;
    protected int m_root;
    private transient int m_last;
    
    public NodeSetDTM(final DTMManager dtmManager) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_root = -1;
        this.m_last = 0;
        this.m_manager = dtmManager;
    }
    
    public NodeSetDTM(final int blocksize, final int dummy, final DTMManager dtmManager) {
        super(blocksize);
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_root = -1;
        this.m_last = 0;
        this.m_manager = dtmManager;
    }
    
    public NodeSetDTM(final NodeSetDTM nodelist) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_root = -1;
        this.m_last = 0;
        this.m_manager = nodelist.getDTMManager();
        this.m_root = nodelist.getRoot();
        this.addNodes(nodelist);
    }
    
    public NodeSetDTM(final DTMIterator ni) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_root = -1;
        this.m_last = 0;
        this.m_manager = ni.getDTMManager();
        this.m_root = ni.getRoot();
        this.addNodes(ni);
    }
    
    public NodeSetDTM(final NodeIterator iterator, final XPathContext xctxt) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: invokespecial   org/apache/xml/utils/NodeVector.<init>:()V
        //     4: aload_0         /* this */
        //     5: iconst_0       
        //     6: putfield        org/apache/xpath/NodeSetDTM.m_next:I
        //     9: aload_0         /* this */
        //    10: iconst_1       
        //    11: putfield        org/apache/xpath/NodeSetDTM.m_mutable:Z
        //    14: aload_0         /* this */
        //    15: iconst_1       
        //    16: putfield        org/apache/xpath/NodeSetDTM.m_cacheNodes:Z
        //    19: aload_0         /* this */
        //    20: iconst_m1      
        //    21: putfield        org/apache/xpath/NodeSetDTM.m_root:I
        //    24: aload_0         /* this */
        //    25: iconst_0       
        //    26: putfield        org/apache/xpath/NodeSetDTM.m_last:I
        //    29: aload_0         /* this */
        //    30: aload_2         /* xctxt */
        //    31: invokevirtual   org/apache/xpath/XPathContext.getDTMManager:()Lorg/apache/xml/dtm/DTMManager;
        //    34: putfield        org/apache/xpath/NodeSetDTM.m_manager:Lorg/apache/xml/dtm/DTMManager;
        //    37: goto            55
        //    40: aload_2         /* xctxt */
        //    41: aload_3        
        //    42: invokevirtual   org/apache/xpath/XPathContext.getDTMHandleFromNode:(Lorg/w3c/dom/Node;)I
        //    45: istore          handle
        //    47: aload_0         /* this */
        //    48: iload           handle
        //    50: aload_2         /* xctxt */
        //    51: invokevirtual   org/apache/xpath/NodeSetDTM.addNodeInDocOrder:(ILorg/apache/xpath/XPathContext;)I
        //    54: pop            
        //    55: aconst_null    
        //    56: aload_1         /* iterator */
        //    57: invokeinterface org/w3c/dom/traversal/NodeIterator.nextNode:()Lorg/w3c/dom/Node;
        //    62: dup            
        //    63: astore_3        /* node */
        //    64: if_acmpne       40
        //    67: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  ------------------------------------
        //  0      68      0     this      Lorg/apache/xpath/NodeSetDTM;
        //  0      68      1     iterator  Lorg/w3c/dom/traversal/NodeIterator;
        //  0      68      2     xctxt     Lorg/apache/xpath/XPathContext;
        //  64     3       3     node      Lorg/w3c/dom/Node;
        //  47     8       4     handle    I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public NodeSetDTM(final NodeList nodeList, final XPathContext xctxt) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_root = -1;
        this.m_last = 0;
        this.m_manager = xctxt.getDTMManager();
        for (int n = nodeList.getLength(), i = 0; i < n; ++i) {
            final Node node = nodeList.item(i);
            final int handle = xctxt.getDTMHandleFromNode(node);
            this.addNode(handle);
        }
    }
    
    public NodeSetDTM(final int node, final DTMManager dtmManager) {
        this.m_next = 0;
        this.m_mutable = true;
        this.m_cacheNodes = true;
        this.m_root = -1;
        this.m_last = 0;
        this.m_manager = dtmManager;
        this.addNode(node);
    }
    
    public void setEnvironment(final Object environment) {
    }
    
    public int getRoot() {
        if (-1 != this.m_root) {
            return this.m_root;
        }
        if (this.size() > 0) {
            return this.item(0);
        }
        return -1;
    }
    
    public void setRoot(final int context, final Object environment) {
    }
    
    public Object clone() throws CloneNotSupportedException {
        final NodeSetDTM clone = (NodeSetDTM)super.clone();
        return clone;
    }
    
    public DTMIterator cloneWithReset() throws CloneNotSupportedException {
        final NodeSetDTM clone = (NodeSetDTM)this.clone();
        clone.reset();
        return clone;
    }
    
    public void reset() {
        this.m_next = 0;
    }
    
    public int getWhatToShow() {
        return -17;
    }
    
    public DTMFilter getFilter() {
        return null;
    }
    
    public boolean getExpandEntityReferences() {
        return true;
    }
    
    public DTM getDTM(final int nodeHandle) {
        return this.m_manager.getDTM(nodeHandle);
    }
    
    public DTMManager getDTMManager() {
        return this.m_manager;
    }
    
    public int nextNode() {
        if (this.m_next < this.size()) {
            final int next = this.elementAt(this.m_next);
            ++this.m_next;
            return next;
        }
        return -1;
    }
    
    public int previousNode() {
        if (!this.m_cacheNodes) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_CANNOT_ITERATE", null));
        }
        if (this.m_next - 1 > 0) {
            --this.m_next;
            return this.elementAt(this.m_next);
        }
        return -1;
    }
    
    public void detach() {
    }
    
    public void allowDetachToRelease(final boolean allowRelease) {
    }
    
    public boolean isFresh() {
        return this.m_next == 0;
    }
    
    public void runTo(final int index) {
        if (!this.m_cacheNodes) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_CANNOT_INDEX", null));
        }
        if (index >= 0 && this.m_next < super.m_firstFree) {
            this.m_next = index;
        }
        else {
            this.m_next = super.m_firstFree - 1;
        }
    }
    
    public int item(final int index) {
        this.runTo(index);
        return this.elementAt(index);
    }
    
    public int getLength() {
        this.runTo(-1);
        return this.size();
    }
    
    public void addNode(final int n) {
        if (!this.m_mutable) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
        this.addElement(n);
    }
    
    public void insertNode(final int n, final int pos) {
        if (!this.m_mutable) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
        this.insertElementAt(n, pos);
    }
    
    public void removeNode(final int n) {
        if (!this.m_mutable) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
        this.removeElement(n);
    }
    
    public void addNodes(final DTMIterator iterator) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: getfield        org/apache/xpath/NodeSetDTM.m_mutable:Z
        //     4: ifne            21
        //     7: new             Ljava/lang/RuntimeException;
        //    10: dup            
        //    11: ldc             "ER_NODESETDTM_NOT_MUTABLE"
        //    13: aconst_null    
        //    14: invokestatic    org/apache/xpath/res/XPATHMessages.createXPATHMessage:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    17: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //    20: athrow         
        //    21: aconst_null    
        //    22: aload_1         /* iterator */
        //    23: if_acmpeq       46
        //    26: goto            34
        //    29: aload_0         /* this */
        //    30: iload_2        
        //    31: invokevirtual   org/apache/xpath/NodeSetDTM.addElement:(I)V
        //    34: iconst_m1      
        //    35: aload_1         /* iterator */
        //    36: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //    41: dup            
        //    42: istore_2        /* obj */
        //    43: if_icmpne       29
        //    46: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  --------------------------------
        //  0      47      0     this      Lorg/apache/xpath/NodeSetDTM;
        //  0      47      1     iterator  Lorg/apache/xml/dtm/DTMIterator;
        //  43     3       2     obj       I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void addNodesInDocOrder(final DTMIterator iterator, final XPathContext support) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: getfield        org/apache/xpath/NodeSetDTM.m_mutable:Z
        //     4: ifne            28
        //     7: new             Ljava/lang/RuntimeException;
        //    10: dup            
        //    11: ldc             "ER_NODESETDTM_NOT_MUTABLE"
        //    13: aconst_null    
        //    14: invokestatic    org/apache/xpath/res/XPATHMessages.createXPATHMessage:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    17: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //    20: athrow         
        //    21: aload_0         /* this */
        //    22: iload_3        
        //    23: aload_2         /* support */
        //    24: invokevirtual   org/apache/xpath/NodeSetDTM.addNodeInDocOrder:(ILorg/apache/xpath/XPathContext;)I
        //    27: pop            
        //    28: iconst_m1      
        //    29: aload_1         /* iterator */
        //    30: invokeinterface org/apache/xml/dtm/DTMIterator.nextNode:()I
        //    35: dup            
        //    36: istore_3        /* node */
        //    37: if_icmpne       21
        //    40: return         
        //    LocalVariableTable:
        //  Start  Length  Slot  Name      Signature
        //  -----  ------  ----  --------  --------------------------------
        //  0      41      0     this      Lorg/apache/xpath/NodeSetDTM;
        //  0      41      1     iterator  Lorg/apache/xml/dtm/DTMIterator;
        //  0      41      2     support   Lorg/apache/xpath/XPathContext;
        //  37     3       3     node      I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public int addNodeInDocOrder(final int node, final boolean test, final XPathContext support) {
        if (!this.m_mutable) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
        int insertIndex = -1;
        if (test) {
            final int size = this.size();
            int i;
            for (i = size - 1; i >= 0; --i) {
                final int child = this.elementAt(i);
                if (child == node) {
                    i = -2;
                    break;
                }
                final DTM dtm = support.getDTM(node);
                if (!dtm.isNodeAfter(node, child)) {
                    break;
                }
            }
            if (i != -2) {
                insertIndex = i + 1;
                this.insertElementAt(node, insertIndex);
            }
        }
        else {
            insertIndex = this.size();
            boolean foundit = false;
            for (int i = 0; i < insertIndex; ++i) {
                if (i == node) {
                    foundit = true;
                    break;
                }
            }
            if (!foundit) {
                this.addElement(node);
            }
        }
        return insertIndex;
    }
    
    public int addNodeInDocOrder(final int node, final XPathContext support) {
        if (!this.m_mutable) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
        return this.addNodeInDocOrder(node, true, support);
    }
    
    public int size() {
        return super.size();
    }
    
    public void addElement(final int value) {
        if (!this.m_mutable) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
        super.addElement(value);
    }
    
    public void insertElementAt(final int value, final int at) {
        if (!this.m_mutable) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
        super.insertElementAt(value, at);
    }
    
    public void appendNodes(final NodeVector nodes) {
        if (!this.m_mutable) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
        super.appendNodes(nodes);
    }
    
    public void removeAllElements() {
        if (!this.m_mutable) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
        super.removeAllElements();
    }
    
    public boolean removeElement(final int s) {
        if (!this.m_mutable) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
        return super.removeElement(s);
    }
    
    public void removeElementAt(final int i) {
        if (!this.m_mutable) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
        super.removeElementAt(i);
    }
    
    public void setElementAt(final int node, final int index) {
        if (!this.m_mutable) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
        super.setElementAt(node, index);
    }
    
    public void setItem(final int node, final int index) {
        if (!this.m_mutable) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_NOT_MUTABLE", null));
        }
        super.setElementAt(node, index);
    }
    
    public int elementAt(final int i) {
        this.runTo(i);
        return super.elementAt(i);
    }
    
    public boolean contains(final int s) {
        this.runTo(-1);
        return super.contains(s);
    }
    
    public int indexOf(final int elem, final int index) {
        this.runTo(-1);
        return super.indexOf(elem, index);
    }
    
    public int indexOf(final int elem) {
        this.runTo(-1);
        return super.indexOf(elem);
    }
    
    public int getCurrentPos() {
        return this.m_next;
    }
    
    public void setCurrentPos(final int i) {
        if (!this.m_cacheNodes) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_NODESETDTM_CANNOT_INDEX", null));
        }
        this.m_next = i;
    }
    
    public int getCurrentNode() {
        if (!this.m_cacheNodes) {
            throw new RuntimeException("This NodeSetDTM can not do indexing or counting functions!");
        }
        final int saved = this.m_next;
        final int current = (this.m_next > 0) ? (this.m_next - 1) : this.m_next;
        final int n = (current < super.m_firstFree) ? this.elementAt(current) : -1;
        this.m_next = saved;
        return n;
    }
    
    public boolean getShouldCacheNodes() {
        return this.m_cacheNodes;
    }
    
    public void setShouldCacheNodes(final boolean b) {
        if (!this.isFresh()) {
            throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_CANNOT_CALL_SETSHOULDCACHENODE", null));
        }
        this.m_cacheNodes = b;
        this.m_mutable = true;
    }
    
    public boolean isMutable() {
        return this.m_mutable;
    }
    
    public int getLast() {
        return this.m_last;
    }
    
    public void setLast(final int last) {
        this.m_last = last;
    }
    
    public boolean isDocOrdered() {
        return true;
    }
    
    public int getAxis() {
        return -1;
    }
}
