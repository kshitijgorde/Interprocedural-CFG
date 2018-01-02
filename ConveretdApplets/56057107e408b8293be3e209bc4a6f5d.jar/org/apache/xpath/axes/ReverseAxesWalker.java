// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.axes;

import org.w3c.dom.Node;
import org.apache.xpath.XPathContext;

public class ReverseAxesWalker extends AxesWalker
{
    public ReverseAxesWalker(final LocPathIterator locPathIterator) {
        super(locPathIterator);
    }
    
    protected void countProximityPosition(final int i) {
        if (i < super.m_proximityPositions.length) {
            final int[] proximityPositions = super.m_proximityPositions;
            --proximityPositions[i];
        }
    }
    
    public int getLastPos(final XPathContext xctxt) {
        int count = 0;
        final AxesWalker savedWalker = super.m_lpi.getLastUsedWalker();
        try {
            final ReverseAxesWalker clone = (ReverseAxesWalker)this.clone();
            clone.setRoot(this.getRoot());
            clone.setPredicateCount(this.getPredicateCount() - 1);
            clone.setPrevWalker(null);
            clone.setNextWalker(null);
            super.m_lpi.setLastUsedWalker(clone);
            Node next;
            while ((next = clone.nextNode()) != null) {
                ++count;
            }
        }
        catch (CloneNotSupportedException ex) {}
        finally {
            super.m_lpi.setLastUsedWalker(savedWalker);
        }
        return count;
    }
    
    protected int getProximityPosition(final int predicateIndex) {
        if (predicateIndex < 0) {
            return -1;
        }
        if (super.m_proximityPositions[predicateIndex] <= 0) {
            final AxesWalker savedWalker = super.m_lpi.getLastUsedWalker();
            try {
                final ReverseAxesWalker clone = (ReverseAxesWalker)this.clone();
                clone.setRoot(this.getRoot());
                clone.setPredicateCount(predicateIndex);
                clone.setPrevWalker(null);
                clone.setNextWalker(null);
                super.m_lpi.setLastUsedWalker(clone);
                int count = 1;
                Node next;
                while ((next = clone.nextNode()) != null) {
                    ++count;
                }
                final int[] proximityPositions = super.m_proximityPositions;
                proximityPositions[predicateIndex] += count;
            }
            catch (CloneNotSupportedException ex) {}
            finally {
                super.m_lpi.setLastUsedWalker(savedWalker);
            }
        }
        return super.m_proximityPositions[predicateIndex];
    }
    
    public boolean isReverseAxes() {
        return true;
    }
    
    public void setRoot(final Node root) {
        super.setRoot(root);
    }
}
