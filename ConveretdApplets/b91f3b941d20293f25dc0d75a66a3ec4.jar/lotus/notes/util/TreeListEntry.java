// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.util;

public class TreeListEntry
{
    TreeListEntry prev;
    TreeListEntry next;
    TreeListEntry Child;
    TreeListEntry Parent;
    
    public TreeListEntry() {
        final TreeListEntry treeListEntry = null;
        this.next = treeListEntry;
        this.prev = treeListEntry;
        final TreeListEntry treeListEntry2 = null;
        this.Parent = treeListEntry2;
        this.Child = treeListEntry2;
    }
    
    public void setParent(final TreeListEntry parent) {
        this.Parent = parent;
    }
    
    public TreeListEntry getParent() {
        return this.Parent;
    }
    
    public void setChild(final TreeListEntry child) {
        this.Child = child;
    }
    
    public TreeListEntry getChild() {
        return this.Child;
    }
    
    public void setPrev(final TreeListEntry prev) {
        this.prev = prev;
    }
    
    public TreeListEntry getPrev() {
        return this.prev;
    }
    
    public void setNext(final TreeListEntry next) {
        this.next = next;
    }
    
    public TreeListEntry getNext() {
        return this.next;
    }
}
