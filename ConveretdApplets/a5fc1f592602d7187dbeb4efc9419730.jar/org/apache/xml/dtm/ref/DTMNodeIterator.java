// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.dtm.ref;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.apache.xml.dtm.DTMDOMException;
import org.w3c.dom.traversal.NodeFilter;
import org.apache.xml.utils.WrappedRuntimeException;
import org.apache.xml.dtm.DTMIterator;
import org.w3c.dom.traversal.NodeIterator;

public class DTMNodeIterator implements NodeIterator
{
    private DTMIterator dtm_iter;
    private boolean valid;
    
    public DTMNodeIterator(final DTMIterator dtmIterator) {
        this.valid = true;
        try {
            this.dtm_iter = (DTMIterator)dtmIterator.clone();
        }
        catch (CloneNotSupportedException cnse) {
            throw new WrappedRuntimeException(cnse);
        }
    }
    
    public DTMIterator getDTMIterator() {
        return this.dtm_iter;
    }
    
    public void detach() {
        this.valid = false;
    }
    
    public boolean getExpandEntityReferences() {
        return false;
    }
    
    public NodeFilter getFilter() {
        throw new DTMDOMException((short)9);
    }
    
    public Node getRoot() {
        final int handle = this.dtm_iter.getRoot();
        return this.dtm_iter.getDTM(handle).getNode(handle);
    }
    
    public int getWhatToShow() {
        return this.dtm_iter.getWhatToShow();
    }
    
    public Node nextNode() throws DOMException {
        if (!this.valid) {
            throw new DTMDOMException((short)11);
        }
        final int handle = this.dtm_iter.nextNode();
        if (handle == -1) {
            return null;
        }
        return this.dtm_iter.getDTM(handle).getNode(handle);
    }
    
    public Node previousNode() {
        if (!this.valid) {
            throw new DTMDOMException((short)11);
        }
        final int handle = this.dtm_iter.previousNode();
        if (handle == -1) {
            return null;
        }
        return this.dtm_iter.getDTM(handle).getNode(handle);
    }
}
