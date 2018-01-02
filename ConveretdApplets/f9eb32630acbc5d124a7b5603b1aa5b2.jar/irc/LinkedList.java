// 
// Decompiled by Procyon v0.5.30
// 

package irc;

public class LinkedList
{
    private int _size;
    private LinkedListNode _head;
    private LinkedListNode _tail;
    
    public LinkedList() {
        this._head = new LinkedListNode();
        this._tail = new LinkedListNode();
        this._head.next = this._tail;
        this._tail.prev = this._head;
        this._size = 0;
    }
    
    public int size() {
        return this._size;
    }
    
    public void addLast(final Object item) {
        final LinkedListNode linkedListNode = new LinkedListNode();
        linkedListNode.item = item;
        linkedListNode.next = this._tail;
        linkedListNode.prev = this._tail.prev;
        linkedListNode.prev.next = linkedListNode;
        linkedListNode.next.prev = linkedListNode;
        ++this._size;
    }
    
    public Object removeFirst() {
        if (this._size == 0) {
            throw new RuntimeException("List empty");
        }
        final LinkedListNode next = this._head.next;
        next.next.prev = this._head;
        this._head.next = next.next;
        --this._size;
        return next.item;
    }
}
