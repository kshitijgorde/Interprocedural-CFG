// 
// Decompiled by Procyon v0.5.30
// 

public class LinkedList
{
    protected LinkedList prev;
    protected LinkedList next;
    protected Object data;
    
    public LinkedList() {
    }
    
    public LinkedList(final Object o) {
        this();
        this.setData(o);
    }
    
    public LinkedList add(final Object o) {
        final LinkedList l = new LinkedList();
        l.prev = this;
        l.next = this.next;
        l.data = o;
        return this.next = l;
    }
    
    public void insert(final LinkedList node) {
        node.next = this.next;
        node.prev = this;
        this.next = node;
        if (node.next != null) {
            node.next.prev = node;
        }
    }
    
    public void setData(final Object data) {
        this.data = data;
    }
    
    public Object getData() {
        return this.data;
    }
    
    public LinkedList getNext() {
        return this.next;
    }
    
    public LinkedList getPrev() {
        return this.prev;
    }
    
    public LinkedList remove() {
        LinkedList ret = null;
        if (this.prev != null) {
            ret = this.prev;
            this.prev.next = this.next;
        }
        if (ret == null) {
            ret = this.next;
        }
        if (this.next != null) {
            this.next.prev = this.prev;
        }
        this.prev = null;
        this.next = null;
        return ret;
    }
}
