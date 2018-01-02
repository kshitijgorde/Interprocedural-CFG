// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref;

import org.w3c.dom.Node;
import org.apache.xml.utils.IntVector;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.dtm.DTM;

public class DTMAxisIterNodeList extends DTMNodeListBase
{
    private DTM m_dtm;
    private DTMAxisIterator m_iter;
    private IntVector m_cachedNodes;
    private int m_last;
    
    private DTMAxisIterNodeList() {
        this.m_last = -1;
    }
    
    public DTMAxisIterNodeList(final DTM dtm, final DTMAxisIterator dtmAxisIterator) {
        this.m_last = -1;
        if (dtmAxisIterator == null) {
            this.m_last = 0;
        }
        else {
            this.m_cachedNodes = new IntVector();
            this.m_dtm = dtm;
        }
        this.m_iter = dtmAxisIterator;
    }
    
    public DTMAxisIterator getDTMAxisIterator() {
        return this.m_iter;
    }
    
    public Node item(final int index) {
        if (this.m_iter != null) {
            int count = this.m_cachedNodes.size();
            if (count > index) {
                final int node = this.m_cachedNodes.elementAt(index);
                return this.m_dtm.getNode(node);
            }
            if (this.m_last == -1) {
                int node;
                while ((node = this.m_iter.next()) != -1 && count <= index) {
                    this.m_cachedNodes.addElement(node);
                    ++count;
                }
                if (node != -1) {
                    return this.m_dtm.getNode(node);
                }
                this.m_last = count;
            }
        }
        return null;
    }
    
    public int getLength() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0         /* this */
        //     1: getfield        org/apache/xml/dtm/ref/DTMAxisIterNodeList.m_last:I
        //     4: iconst_m1      
        //     5: if_icmpne       45
        //     8: goto            19
        //    11: aload_0         /* this */
        //    12: getfield        org/apache/xml/dtm/ref/DTMAxisIterNodeList.m_cachedNodes:Lorg/apache/xml/utils/IntVector;
        //    15: iload_1        
        //    16: invokevirtual   org/apache/xml/utils/IntVector.addElement:(I)V
        //    19: aload_0         /* this */
        //    20: getfield        org/apache/xml/dtm/ref/DTMAxisIterNodeList.m_iter:Lorg/apache/xml/dtm/DTMAxisIterator;
        //    23: invokeinterface org/apache/xml/dtm/DTMAxisIterator.next:()I
        //    28: dup            
        //    29: istore_1        /* node */
        //    30: iconst_m1      
        //    31: if_icmpne       11
        //    34: aload_0         /* this */
        //    35: aload_0         /* this */
        //    36: getfield        org/apache/xml/dtm/ref/DTMAxisIterNodeList.m_cachedNodes:Lorg/apache/xml/utils/IntVector;
        //    39: invokevirtual   org/apache/xml/utils/IntVector.size:()I
        //    42: putfield        org/apache/xml/dtm/ref/DTMAxisIterNodeList.m_last:I
        //    45: aload_0         /* this */
        //    46: getfield        org/apache/xml/dtm/ref/DTMAxisIterNodeList.m_last:I
        //    49: ireturn        
        //    LocalVariableTable:
        //  Start  Length  Slot  Name  Signature
        //  -----  ------  ----  ----  --------------------------------------------
        //  0      50      0     this  Lorg/apache/xml/dtm/ref/DTMAxisIterNodeList;
        //  30     15      1     node  I
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
