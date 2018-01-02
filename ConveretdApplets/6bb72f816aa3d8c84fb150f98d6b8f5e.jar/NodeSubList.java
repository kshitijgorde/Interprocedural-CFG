// 
// Decompiled by Procyon v0.5.30
// 

final class NodeSubList
{
    private final NodeSub head;
    private NodeSub current;
    
    public NodeSubList() {
        this.head = new NodeSub();
        this.head.prevNodeSub = this.head;
        this.head.nextNodeSub = this.head;
    }
    
    public void insertHead(final NodeSub nodeSub) {
        if (nodeSub.nextNodeSub != null) {
            nodeSub.unlinkSub();
        }
        nodeSub.nextNodeSub = this.head.nextNodeSub;
        nodeSub.prevNodeSub = this.head;
        nodeSub.nextNodeSub.prevNodeSub = nodeSub;
        nodeSub.prevNodeSub.nextNodeSub = nodeSub;
    }
    
    public NodeSub popTail() {
        final NodeSub prevNodeSub = this.head.prevNodeSub;
        if (prevNodeSub == this.head) {
            return null;
        }
        prevNodeSub.unlinkSub();
        return prevNodeSub;
    }
    
    public NodeSub reverseGetFirst() {
        final NodeSub prevNodeSub = this.head.prevNodeSub;
        if (prevNodeSub == this.head) {
            return this.current = null;
        }
        this.current = prevNodeSub.prevNodeSub;
        return prevNodeSub;
    }
    
    public NodeSub reverseGetNext() {
        final NodeSub current = this.current;
        if (current == this.head) {
            return this.current = null;
        }
        this.current = current.prevNodeSub;
        return current;
    }
    
    public int getNodeCount() {
        int n = 0;
        for (NodeSub nodeSub = this.head.prevNodeSub; nodeSub != this.head; nodeSub = nodeSub.prevNodeSub) {
            ++n;
        }
        return n;
    }
}
