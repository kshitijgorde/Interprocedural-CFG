// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.utils;

public class SortableHandle
{
    private SortableObject container;
    private SortableHandle next;
    private int index;
    
    public SortableHandle(final SortableObject container) {
        this.container = container;
    }
    
    public SortableObject getContainer() {
        return this.container;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public int compareTo(final SortableHandle sortableHandle, final int n) {
        return this.container.compareTo(sortableHandle.container, n);
    }
    
    public void setIndex(final int index) {
        this.index = index;
    }
    
    public void setNext(final SortableHandle next) {
        this.next = next;
    }
    
    public SortableHandle getNext() {
        return this.next;
    }
}
