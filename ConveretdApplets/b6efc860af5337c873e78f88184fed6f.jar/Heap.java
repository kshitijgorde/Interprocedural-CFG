import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class Heap extends Drawable
{
    protected int numberOfNodes;
    protected HeapNode root;
    protected HeapNode[] heap;
    protected HeapList list;
    
    public Heap(final Position rootPosition, final Position listPosition) {
        this.numberOfNodes = 0;
        this.heap = new HeapNode[64];
        this.root = new HeapNode(1, 1, rootPosition, null, this.heap, this);
        this.list = new HeapList(listPosition, 32);
    }
    
    public void draw(final Graphics g) {
        this.root.draw(g);
        this.list.draw(g);
    }
    
    public void finishSwap(final int left, final int right) {
        this.list.finishSwap(left, right);
    }
    
    public boolean inside(final Position position) {
        return this.root.inside(position);
    }
    
    public void markSorted(final int index) {
        this.list.markSorted(index);
    }
    
    public void startSwap(final int left, final int right) {
        this.list.startSwap(left, right);
    }
}
