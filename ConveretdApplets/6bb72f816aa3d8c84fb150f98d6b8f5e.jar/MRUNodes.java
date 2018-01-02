// 
// Decompiled by Procyon v0.5.30
// 

public final class MRUNodes
{
    private final NodeSub emptyNodeSub;
    private final int initialCount;
    private int spaceLeft;
    private final NodeCache nodeCache;
    private final NodeSubList nodeSubList;
    
    public MRUNodes(final int n) {
        this.emptyNodeSub = new NodeSub();
        this.nodeSubList = new NodeSubList();
        this.initialCount = n;
        this.spaceLeft = n;
        this.nodeCache = new NodeCache();
    }
    
    public NodeSub insertFromCache(final long n) {
        final NodeSub nodeSub = (NodeSub)this.nodeCache.findNodeByID(n);
        if (nodeSub != null) {
            this.nodeSubList.insertHead(nodeSub);
        }
        return nodeSub;
    }
    
    public void removeFromCache(final NodeSub nodeSub, final long n) {
        try {
            if (this.spaceLeft == 0) {
                final NodeSub popTail = this.nodeSubList.popTail();
                popTail.unlink();
                popTail.unlinkSub();
                if (popTail == this.emptyNodeSub) {
                    final NodeSub popTail2 = this.nodeSubList.popTail();
                    popTail2.unlink();
                    popTail2.unlinkSub();
                }
            }
            else {
                --this.spaceLeft;
            }
            this.nodeCache.removeFromCache(nodeSub, n);
            this.nodeSubList.insertHead(nodeSub);
        }
        catch (RuntimeException ex) {
            SignLink.reporterror("47547, " + nodeSub + ", " + n + ", " + 2 + ", " + ex.toString());
            throw new RuntimeException();
        }
    }
    
    public void unlinkAll() {
        while (true) {
            final NodeSub popTail = this.nodeSubList.popTail();
            if (popTail == null) {
                break;
            }
            popTail.unlink();
            popTail.unlinkSub();
        }
        this.spaceLeft = this.initialCount;
    }
}
