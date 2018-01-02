// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.rubik.parserAWT;

import java.util.NoSuchElementException;
import ch.randelshofer.util.SingletonEnumeration;
import java.util.Vector;
import ch.randelshofer.util.ReverseVectorEnumeration;
import java.util.Enumeration;

public class RepetitionNode extends ScriptNode
{
    int repeatCount;
    
    public RepetitionNode() {
        this.repeatCount = 1;
    }
    
    public RepetitionNode(final int n, final int n2) {
        super(n, n2);
        this.repeatCount = 1;
    }
    
    public void setRepeatCount(final int repeatCount) {
        this.repeatCount = repeatCount;
    }
    
    public int getRepeatCount() {
        return this.repeatCount;
    }
    
    public int getSymbol() {
        return 118;
    }
    
    public int getFullTurnCount() {
        return super.getFullTurnCount() * this.repeatCount;
    }
    
    public int getQuarterTurnCount() {
        return super.getQuarterTurnCount() * this.repeatCount;
    }
    
    public Enumeration resolvedEnumeration(final boolean b) {
        return new ResolvedEnumeration(this, b, this.repeatCount);
    }
    
    public Enumeration enumerateChildrenReversed() {
        return new ReverseVectorEnumeration(super.children);
    }
    
    private static class ResolvedEnumeration implements Enumeration
    {
        protected RepetitionNode root;
        protected Enumeration children;
        protected Enumeration subtree;
        protected Vector cachedChildren;
        boolean inverse;
        int repeatCount;
        
        public ResolvedEnumeration(final RepetitionNode root, final boolean inverse, final int repeatCount) {
            this.root = root;
            this.inverse = inverse;
            this.repeatCount = repeatCount;
            this.cachedChildren = new Vector();
            this.children = (inverse ? this.root.enumerateChildrenReversed() : this.root.children());
            while (this.children.hasMoreElements()) {
                this.cachedChildren.addElement(this.children.nextElement());
            }
            this.children = this.cachedChildren.elements();
            this.subtree = new SingletonEnumeration(this.root.clone());
        }
        
        public boolean hasMoreElements() {
            return this.subtree.hasMoreElements() || this.children.hasMoreElements() || this.repeatCount > 1;
        }
        
        public Object nextElement() {
            Object o;
            if (this.subtree.hasMoreElements()) {
                o = this.subtree.nextElement();
            }
            else {
                if (!this.children.hasMoreElements()) {
                    throw new NoSuchElementException();
                }
                this.subtree = this.children.nextElement().resolvedEnumeration(this.inverse);
                o = this.subtree.nextElement();
                if (!this.children.hasMoreElements() && this.repeatCount > 1) {
                    --this.repeatCount;
                    this.children = this.cachedChildren.elements();
                }
            }
            return o;
        }
    }
}
