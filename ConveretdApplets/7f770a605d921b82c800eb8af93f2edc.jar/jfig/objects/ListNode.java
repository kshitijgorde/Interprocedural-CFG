// 
// Decompiled by Procyon v0.5.30
// 

package jfig.objects;

public final class ListNode
{
    public FigObject obj;
    public ListNode prev;
    public ListNode next;
    
    public final ListNode get_next() {
        return this.next;
    }
    
    public final ListNode get_prev() {
        return this.prev;
    }
    
    public final FigObject get_obj() {
        return this.obj;
    }
    
    public final void set_next(final ListNode next) {
        this.next = next;
    }
    
    public final void set_prev(final ListNode prev) {
        this.prev = prev;
    }
    
    public final void set_obj(final FigObject obj) {
        this.obj = obj;
    }
    
    public ListNode() {
        this.obj = null;
        this.prev = null;
        this.next = null;
    }
    
    public ListNode(final FigObject obj, final ListNode prev, final ListNode next) {
        this.obj = obj;
        this.prev = prev;
        this.next = next;
    }
}
