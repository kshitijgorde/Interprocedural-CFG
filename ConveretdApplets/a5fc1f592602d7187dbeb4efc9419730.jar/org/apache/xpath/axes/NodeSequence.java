// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import java.util.Vector;
import org.apache.xpath.NodeSetDTM;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.XPathContext;
import org.apache.xml.utils.NodeVector;
import org.apache.xml.dtm.DTMManager;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.objects.XObject;

public class NodeSequence extends XObject implements DTMIterator, Cloneable, PathComponent
{
    protected int m_last;
    protected int m_next;
    protected DTMIterator m_iter;
    protected DTMManager m_dtmMgr;
    
    protected NodeVector getVector() {
        return (NodeVector)super.m_obj;
    }
    
    protected void SetVector(final NodeVector v) {
        super.m_obj = v;
    }
    
    public boolean hasCache() {
        return super.m_obj != null;
    }
    
    public final void setIter(final DTMIterator iter) {
        this.m_iter = iter;
    }
    
    public final DTMIterator getContainedIter() {
        return this.m_iter;
    }
    
    public NodeSequence(final DTMIterator iter, final int context, final XPathContext xctxt, final boolean shouldCacheNodes) {
        this.m_last = -1;
        this.m_next = 0;
        this.setIter(iter);
        this.setRoot(context, xctxt);
        this.setShouldCacheNodes(shouldCacheNodes);
    }
    
    public NodeSequence(final Object nodeVector) {
        super(nodeVector);
        this.m_last = -1;
        this.m_next = 0;
        if (null != nodeVector) {
            this.assertion(nodeVector instanceof NodeVector, "Must have a NodeVector as the object for NodeSequence!");
            if (nodeVector instanceof DTMIterator) {
                this.setIter((DTMIterator)nodeVector);
                this.m_last = ((DTMIterator)nodeVector).getLength();
            }
        }
    }
    
    public NodeSequence(final DTMManager dtmMgr) {
        super(new NodeVector());
        this.m_last = -1;
        this.m_next = 0;
        this.m_last = 0;
        this.m_dtmMgr = dtmMgr;
    }
    
    public NodeSequence() {
        this.m_last = -1;
        this.m_next = 0;
    }
    
    public DTM getDTM(final int nodeHandle) {
        final DTMManager mgr = this.getDTMManager();
        if (null != mgr) {
            return this.getDTMManager().getDTM(nodeHandle);
        }
        this.assertion(false, "Can not get a DTM Unless a DTMManager has been set!");
        return null;
    }
    
    public DTMManager getDTMManager() {
        return this.m_dtmMgr;
    }
    
    public int getRoot() {
        if (null != this.m_iter) {
            return this.m_iter.getRoot();
        }
        return -1;
    }
    
    public void setRoot(final int nodeHandle, final Object environment) {
        if (null != this.m_iter) {
            final XPathContext xctxt = (XPathContext)environment;
            this.m_dtmMgr = xctxt.getDTMManager();
            this.m_iter.setRoot(nodeHandle, environment);
            if (!this.m_iter.isDocOrdered()) {
                if (!this.hasCache()) {
                    this.setShouldCacheNodes(true);
                }
                this.runTo(-1);
                this.m_next = 0;
            }
        }
        else {
            this.assertion(false, "Can not setRoot on a non-iterated NodeSequence!");
        }
    }
    
    public void reset() {
        this.m_next = 0;
    }
    
    public int getWhatToShow() {
        return this.hasCache() ? -17 : this.m_iter.getWhatToShow();
    }
    
    public boolean getExpandEntityReferences() {
        return null == this.m_iter || this.m_iter.getExpandEntityReferences();
    }
    
    public int nextNode() {
        final NodeVector vec = this.getVector();
        if (null != vec) {
            if (this.m_next < vec.size()) {
                final int next = vec.elementAt(this.m_next);
                ++this.m_next;
                return next;
            }
            if (-1 != this.m_last || null == this.m_iter) {
                ++this.m_next;
                return -1;
            }
        }
        if (null == this.m_iter) {
            return -1;
        }
        final int next = this.m_iter.nextNode();
        if (-1 != next) {
            if (this.hasCache()) {
                if (this.m_iter.isDocOrdered()) {
                    this.getVector().addElement(next);
                    ++this.m_next;
                }
                else {
                    final int insertIndex = this.addNodeInDocOrder(next);
                    if (insertIndex >= 0) {
                        ++this.m_next;
                    }
                }
            }
            else {
                ++this.m_next;
            }
        }
        else {
            this.m_last = this.m_next;
            ++this.m_next;
        }
        return next;
    }
    
    public int previousNode() {
        if (!this.hasCache()) {
            final int n = this.m_iter.previousNode();
            return this.m_next = this.m_iter.getCurrentPos();
        }
        if (this.m_next <= 0) {
            return -1;
        }
        --this.m_next;
        return this.item(this.m_next);
    }
    
    public void detach() {
        if (null != this.m_iter) {
            this.m_iter.detach();
        }
        super.detach();
    }
    
    public void allowDetachToRelease(final boolean allowRelease) {
        if (!allowRelease && !this.hasCache()) {
            this.setShouldCacheNodes(true);
        }
        if (null != this.m_iter) {
            this.m_iter.allowDetachToRelease(allowRelease);
        }
        super.allowDetachToRelease(allowRelease);
    }
    
    public int getCurrentNode() {
        if (this.hasCache()) {
            final int currentIndex = this.m_next - 1;
            final NodeVector vec = this.getVector();
            if (currentIndex >= 0 && currentIndex < vec.size()) {
                return vec.elementAt(currentIndex);
            }
            return -1;
        }
        else {
            if (null != this.m_iter) {
                return this.m_iter.getCurrentNode();
            }
            return -1;
        }
    }
    
    public boolean isFresh() {
        return 0 == this.m_next;
    }
    
    public void setShouldCacheNodes(final boolean b) {
        if (b) {
            if (!this.hasCache()) {
                this.SetVector(new NodeVector());
            }
        }
        else {
            this.SetVector(null);
        }
    }
    
    public boolean isMutable() {
        return this.hasCache();
    }
    
    public int getCurrentPos() {
        return this.m_next;
    }
    
    public void runTo(final int index) {
        if (-1 == index) {
            final int pos = this.m_next;
            int n;
            while (-1 != (n = this.nextNode())) {}
            this.m_next = pos;
        }
        else {
            if (this.m_next == index) {
                return;
            }
            if (this.hasCache() && this.m_next < this.getVector().size()) {
                this.m_next = index;
            }
            else if (null == this.getVector() && index < this.m_next) {
                while (this.m_next >= index) {
                    final int n;
                    if (-1 == (n = this.previousNode())) {
                        break;
                    }
                }
            }
            else {
                int n;
                while (this.m_next < index && -1 != (n = this.nextNode())) {}
            }
        }
    }
    
    public void setCurrentPos(final int i) {
        this.runTo(i);
    }
    
    public int item(final int index) {
        this.setCurrentPos(index);
        final int n = this.nextNode();
        this.m_next = index;
        return n;
    }
    
    public void setItem(final int node, final int index) {
        final NodeVector vec = this.getVector();
        if (null != vec) {
            vec.setElementAt(node, index);
            this.m_last = vec.size();
        }
        else {
            this.m_iter.setItem(node, index);
        }
    }
    
    public int getLength() {
        if (!this.hasCache()) {
            return (-1 == this.m_last) ? (this.m_last = this.m_iter.getLength()) : this.m_last;
        }
        if (this.m_iter instanceof NodeSetDTM) {
            return this.m_iter.getLength();
        }
        if (-1 == this.m_last) {
            final int pos = this.m_next;
            this.runTo(-1);
            this.m_next = pos;
        }
        return this.m_last;
    }
    
    public DTMIterator cloneWithReset() throws CloneNotSupportedException {
        final NodeSequence seq = (NodeSequence)super.clone();
        seq.m_next = 0;
        return seq;
    }
    
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
    
    public boolean isDocOrdered() {
        return null == this.m_iter || this.m_iter.isDocOrdered();
    }
    
    public int getAxis() {
        if (null != this.m_iter) {
            return this.m_iter.getAxis();
        }
        this.assertion(false, "Can not getAxis from a non-iterated node sequence!");
        return 0;
    }
    
    public int getAnalysisBits() {
        if (null != this.m_iter && this.m_iter instanceof PathComponent) {
            return ((PathComponent)this.m_iter).getAnalysisBits();
        }
        return 0;
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        super.fixupVariables(vars, globalsSize);
    }
    
    protected int addNodeInDocOrder(final int node) {
        this.assertion(this.hasCache(), "addNodeInDocOrder must be done on a mutable sequence!");
        int insertIndex = -1;
        final NodeVector vec = this.getVector();
        final int size = vec.size();
        int i;
        for (i = size - 1; i >= 0; --i) {
            final int child = vec.elementAt(i);
            if (child == node) {
                i = -2;
                break;
            }
            final DTM dtm = this.m_dtmMgr.getDTM(node);
            if (!dtm.isNodeAfter(node, child)) {
                break;
            }
        }
        if (i != -2) {
            insertIndex = i + 1;
            vec.insertElementAt(node, insertIndex);
        }
        return insertIndex;
    }
}
