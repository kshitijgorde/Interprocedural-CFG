// 
// Decompiled by Procyon v0.5.30
// 

package COM.objectspace.jgl;

import java.io.Serializable;

public final class SListIterator implements ForwardIterator, Serializable
{
    SList mySList;
    SList.SListNode myNode;
    
    public SListIterator() {
    }
    
    SListIterator(final SList mySList, final SList.SListNode myNode) {
        this.mySList = mySList;
        this.myNode = myNode;
    }
    
    public SListIterator(final SListIterator sListIterator) {
        this.mySList = sListIterator.mySList;
        this.myNode = sListIterator.myNode;
    }
    
    public Object clone() {
        return new SListIterator(this);
    }
    
    public boolean equals(final Object o) {
        return o instanceof SListIterator && this.equals((SListIterator)o);
    }
    
    public boolean equals(final SListIterator sListIterator) {
        return this.myNode == sListIterator.myNode;
    }
    
    public boolean atBegin() {
        return this.myNode == this.mySList.myHead;
    }
    
    public boolean atEnd() {
        return this.myNode == null;
    }
    
    public boolean hasMoreElements() {
        return this.myNode != null;
    }
    
    public void advance() {
        this.myNode = this.myNode.next;
    }
    
    public void advance(int n) {
        if (n < 0) {
            throw new InvalidOperationException("Attempt to advance a ForwardIterator in the wrong direction.");
        }
        while (n-- > 0) {
            this.myNode = this.myNode.next;
        }
    }
    
    public Object nextElement() {
        final Object object = this.myNode.object;
        this.myNode = this.myNode.next;
        return object;
    }
    
    public Object get() {
        return this.myNode.object;
    }
    
    public void put(final Object object) {
        this.myNode.object = object;
    }
    
    public int distance(final ForwardIterator forwardIterator) {
        return this.distance(this.myNode, ((SListIterator)forwardIterator).myNode);
    }
    
    public int index() {
        return this.distance(this.mySList.myHead, this.myNode);
    }
    
    private int distance(SList.SListNode next, final SList.SListNode sListNode) {
        int n = 0;
        while (next != sListNode) {
            ++n;
            next = next.next;
        }
        return n;
    }
    
    public Container getContainer() {
        return this.mySList;
    }
}
