// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.apache.xpath.XPathContext;
import org.apache.xml.dtm.DTMAxisIterator;

public class ReverseAxesWalker extends AxesWalker
{
    protected DTMAxisIterator m_iterator;
    
    ReverseAxesWalker(final LocPathIterator locPathIterator, final int axis) {
        super(locPathIterator, axis);
    }
    
    public void setRoot(final int root) {
        super.setRoot(root);
        (this.m_iterator = this.getDTM(root).getAxisIterator(super.m_axis)).setStartNode(root);
    }
    
    public void detach() {
        this.m_iterator = null;
        super.detach();
    }
    
    protected int getNextNode() {
        if (super.m_foundLast) {
            return -1;
        }
        final int next = this.m_iterator.next();
        if (super.m_isFresh) {
            super.m_isFresh = false;
        }
        if (-1 == next) {
            super.m_foundLast = true;
        }
        return next;
    }
    
    public boolean isReverseAxes() {
        return true;
    }
    
    protected int getProximityPosition(final int predicateIndex) {
        if (predicateIndex < 0) {
            return -1;
        }
        int count = super.m_proximityPositions[predicateIndex];
        if (count <= 0) {
            final AxesWalker savedWalker = this.wi().getLastUsedWalker();
            try {
                final ReverseAxesWalker clone = (ReverseAxesWalker)this.clone();
                clone.setRoot(this.getRoot());
                clone.setPredicateCount(predicateIndex);
                clone.setPrevWalker(null);
                clone.setNextWalker(null);
                this.wi().setLastUsedWalker(clone);
                ++count;
                int next;
                while (-1 != (next = clone.nextNode())) {
                    ++count;
                }
                super.m_proximityPositions[predicateIndex] = count;
            }
            catch (CloneNotSupportedException cnse) {}
            finally {
                this.wi().setLastUsedWalker(savedWalker);
            }
        }
        return count;
    }
    
    protected void countProximityPosition(final int i) {
        if (i < super.m_proximityPositions.length) {
            final int[] proximityPositions = super.m_proximityPositions;
            --proximityPositions[i];
        }
    }
    
    public int getLastPos(final XPathContext xctxt) {
        int count = 0;
        final AxesWalker savedWalker = this.wi().getLastUsedWalker();
        try {
            final ReverseAxesWalker clone = (ReverseAxesWalker)this.clone();
            clone.setRoot(this.getRoot());
            clone.setPredicateCount(this.getPredicateCount() - 1);
            clone.setPrevWalker(null);
            clone.setNextWalker(null);
            this.wi().setLastUsedWalker(clone);
            int next;
            while (-1 != (next = clone.nextNode())) {
                ++count;
            }
        }
        catch (CloneNotSupportedException cnse) {}
        finally {
            this.wi().setLastUsedWalker(savedWalker);
        }
        return count;
    }
    
    public boolean isDocOrdered() {
        return false;
    }
}
