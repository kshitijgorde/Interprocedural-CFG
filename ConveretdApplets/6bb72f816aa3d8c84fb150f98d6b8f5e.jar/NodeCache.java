// 
// Decompiled by Procyon v0.5.30
// 

final class NodeCache
{
    private final int size;
    private final Node[] cache;
    
    public NodeCache() {
        final int size = 1024;
        this.size = size;
        this.cache = new Node[size];
        for (int i = 0; i < size; ++i) {
            final Node[] cache = this.cache;
            final int n = i;
            final Node node = new Node();
            cache[n] = node;
            final Node node2 = node;
            node2.prev = node2;
            node2.next = node2;
        }
    }
    
    public Node findNodeByID(final long n) {
        for (Node node = this.cache[(int)(n & this.size - 1)], node2 = node.prev; node2 != node; node2 = node2.prev) {
            if (node2.id == n) {
                return node2;
            }
        }
        return null;
    }
    
    public void removeFromCache(final Node node, final long id) {
        try {
            if (node.next != null) {
                node.unlink();
            }
            final Node prev = this.cache[(int)(id & this.size - 1)];
            node.next = prev.next;
            node.prev = prev;
            node.next.prev = node;
            node.prev.next = node;
            node.id = id;
        }
        catch (RuntimeException ex) {
            SignLink.reporterror("91499, " + node + ", " + id + ", " + 7 + ", " + ex.toString());
            throw new RuntimeException();
        }
    }
}
