// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xml.utils.WrappedRuntimeException;
import java.util.Vector;
import org.apache.xml.dtm.DTMIterator;
import java.io.Serializable;

public class IteratorPool implements Serializable
{
    private final DTMIterator m_orig;
    private final Vector m_freeStack;
    
    public IteratorPool(final DTMIterator original) {
        this.m_orig = original;
        this.m_freeStack = new Vector();
    }
    
    public synchronized DTMIterator getInstanceOrThrow() throws CloneNotSupportedException {
        if (this.m_freeStack.isEmpty()) {
            return (DTMIterator)this.m_orig.clone();
        }
        final DTMIterator result = this.m_freeStack.lastElement();
        this.m_freeStack.setSize(this.m_freeStack.size() - 1);
        return result;
    }
    
    public synchronized DTMIterator getInstance() {
        if (this.m_freeStack.isEmpty()) {
            try {
                return (DTMIterator)this.m_orig.clone();
            }
            catch (Exception ex) {
                throw new WrappedRuntimeException(ex);
            }
        }
        final DTMIterator result = this.m_freeStack.lastElement();
        this.m_freeStack.setSize(this.m_freeStack.size() - 1);
        return result;
    }
    
    public synchronized void freeInstance(final DTMIterator obj) {
        this.m_freeStack.addElement(obj);
    }
}
