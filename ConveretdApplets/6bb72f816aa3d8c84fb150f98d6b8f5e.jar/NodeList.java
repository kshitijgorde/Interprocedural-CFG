// 
// Decompiled by Procyon v0.5.30
// 

final class NodeList
{
    private final Node head;
    private Node current;
    
    public NodeList() {
        this.head = new Node();
        this.head.prev = this.head;
        this.head.next = this.head;
    }
    
    public void insertHead(final Node node) {
        if (node.next != null) {
            node.unlink();
        }
        node.next = this.head.next;
        node.prev = this.head;
        node.next.prev = node;
        node.prev.next = node;
    }
    
    public void insertTail(final Node node) {
        if (node.next != null) {
            node.unlink();
        }
        node.next = this.head;
        node.prev = this.head.prev;
        node.next.prev = node;
        node.prev.next = node;
    }
    
    public Node popHead() {
        final Node prev = this.head.prev;
        if (prev == this.head) {
            return null;
        }
        prev.unlink();
        return prev;
    }
    
    public Node reverseGetFirst() {
        final Node prev = this.head.prev;
        if (prev == this.head) {
            return this.current = null;
        }
        this.current = prev.prev;
        return prev;
    }
    
    public Node getFirst() {
        final Node next = this.head.next;
        if (next == this.head) {
            return this.current = null;
        }
        this.current = next.next;
        return next;
    }
    
    public Node reverseGetNext() {
        final Node current = this.current;
        if (current == this.head) {
            return this.current = null;
        }
        this.current = current.prev;
        return current;
    }
    
    public Node getNext() {
        final Node current = this.current;
        if (current == this.head) {
            return this.current = null;
        }
        this.current = current.next;
        return current;
    }
    
    public void removeAll() {
        if (this.head.prev == this.head) {
            return;
        }
        while (true) {
            final Node prev = this.head.prev;
            if (prev == this.head) {
                break;
            }
            prev.unlink();
        }
    }
}
