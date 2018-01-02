// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref;

import org.w3c.dom.Node;
import org.apache.xml.dtm.DTM;

public class DTMChildIterNodeList extends DTMNodeListBase
{
    private int m_firstChild;
    private DTM m_parentDTM;
    
    private DTMChildIterNodeList() {
    }
    
    public DTMChildIterNodeList(final DTM parentDTM, final int parentHandle) {
        this.m_parentDTM = parentDTM;
        this.m_firstChild = parentDTM.getFirstChild(parentHandle);
    }
    
    public Node item(int index) {
        int handle;
        for (handle = this.m_firstChild; --index >= 0 && handle != -1; handle = this.m_parentDTM.getNextSibling(handle)) {}
        if (handle == -1) {
            return null;
        }
        return this.m_parentDTM.getNode(handle);
    }
    
    public int getLength() {
        int count = 0;
        for (int handle = this.m_firstChild; handle != -1; handle = this.m_parentDTM.getNextSibling(handle)) {
            ++count;
        }
        return count;
    }
}
