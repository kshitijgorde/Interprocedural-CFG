// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.util;

public class TreeList
{
    public static int FULL;
    public static int CURRENT;
    public static int CURRENTANDUP;
    public static final int FORWARD = 0;
    public static final int BACKWARD = 1;
    private TreeListEntry head;
    private TreeListEntry tail;
    private TreeListEntry topleveltail;
    private int NumberOfEntries;
    private int NumberOfTopLevelEntries;
    private boolean topLevelCountValid;
    
    public TreeList() {
        this.topLevelCountValid = false;
        this.NumberOfEntries = 0;
        this.NumberOfTopLevelEntries = 0;
        final TreeListEntry head = null;
        this.topleveltail = head;
        this.tail = head;
        this.head = head;
    }
    
    public TreeListEntry getFirst() {
        return this.head;
    }
    
    public TreeListEntry getLast() {
        return this.topleveltail;
    }
    
    public TreeListEntry getTail() {
        if (this.tail == null) {
            this.calculateTail();
        }
        return this.tail;
    }
    
    public void calculateTail() {
        this.tail = findMostDescendedChild(this.topleveltail);
    }
    
    public void AddEntry(final TreeListEntry head, final TreeListEntry parent) {
        if (head == null) {
            return;
        }
        this.topLevelCountValid = false;
        head.setParent(parent);
        if (parent != null) {
            this.AddChild(head, parent);
        }
        else {
            head.setPrev(this.topleveltail);
            if (this.topleveltail != null) {
                this.topleveltail.setNext(head);
            }
            this.topleveltail = head;
        }
        if (this.head == null) {
            this.head = head;
        }
        this.tail = null;
        ++this.NumberOfEntries;
    }
    
    public void InsertEntry(final TreeListEntry head, final TreeListEntry next) {
        if (null == next) {
            return;
        }
        this.topLevelCountValid = false;
        final TreeListEntry prev = next.getPrev();
        head.setParent(next.getParent());
        head.setPrev(next.getPrev());
        head.setNext(next);
        next.setPrev(head);
        if (prev != null) {
            prev.setNext(head);
        }
        if (this.head == next) {
            this.head = head;
        }
        ++this.NumberOfEntries;
    }
    
    public void AddChild(final TreeListEntry treeListEntry, final TreeListEntry parent) {
        TreeListEntry prev = parent.getChild();
        treeListEntry.setParent(parent);
        if (prev == null) {
            parent.setChild(treeListEntry);
        }
        else {
            while (prev.getNext() != null) {
                prev = prev.getNext();
            }
            prev.setNext(treeListEntry);
            treeListEntry.setPrev(prev);
        }
    }
    
    public void RemoveEntry(final TreeListEntry treeListEntry) {
        if (this.head == null || treeListEntry == null) {
            return;
        }
        final TreeListEntry prev = treeListEntry.getPrev();
        final TreeListEntry next = treeListEntry.getNext();
        if (prev != null) {
            prev.setNext(next);
        }
        if (next != null) {
            next.setPrev(prev);
        }
        if (this.tail == treeListEntry) {
            this.tail = prev;
        }
        if (this.head == treeListEntry) {
            final TreeListEntry treeListEntry2 = null;
            this.tail = treeListEntry2;
            this.head = treeListEntry2;
        }
        if (treeListEntry.getParent() == null) {
            --this.NumberOfTopLevelEntries;
        }
        else if (prev == null) {
            treeListEntry.getParent().setChild(next);
        }
        --this.NumberOfEntries;
    }
    
    public int getNumberOfEntries() {
        return this.NumberOfEntries;
    }
    
    public int getTopLevelEntries() {
        if (!this.topLevelCountValid) {
            this.NumberOfTopLevelEntries = this.countTopLevelEntries();
            this.topLevelCountValid = true;
        }
        return this.NumberOfTopLevelEntries;
    }
    
    public int countTopLevelEntries() {
        int n = 0;
        for (TreeListEntry treeListEntry = this.getFirst(); treeListEntry != null; treeListEntry = treeListEntry.getNext()) {
            ++n;
        }
        return n;
    }
    
    public static TreeListEntry Traverse(final TreeListEntry treeListEntry, final int n, final int n2) {
        TreeListEntry treeListEntry2 = null;
        if (treeListEntry == null) {
            return null;
        }
        if (n2 == TreeList.CURRENTANDUP) {
            return TraverseCurrentAndUp(treeListEntry);
        }
        switch (n) {
            case 0: {
                final TreeListEntry child = treeListEntry.getChild();
                if (child != null && n2 == TreeList.FULL) {
                    treeListEntry2 = child;
                    break;
                }
                treeListEntry2 = treeListEntry.getNext();
                break;
            }
            case 1: {
                treeListEntry2 = treeListEntry.getPrev();
                if (treeListEntry2 == null && n2 == TreeList.CURRENT) {
                    treeListEntry2 = treeListEntry.getParent();
                    break;
                }
                break;
            }
        }
        if (n2 != TreeList.CURRENT && treeListEntry2 == null) {
            for (TreeListEntry parent = treeListEntry; treeListEntry2 == null && parent.getParent() != null; parent = parent.getParent(), treeListEntry2 = Traverse(parent, n, TreeList.CURRENT)) {}
        }
        return treeListEntry2;
    }
    
    public static TreeListEntry TraverseBackwardFull(final TreeListEntry treeListEntry) {
        TreeListEntry treeListEntry2 = null;
        if (treeListEntry != null) {
            treeListEntry2 = treeListEntry.getPrev();
            if (treeListEntry2 != null) {
                if (treeListEntry2.getChild() != null) {
                    treeListEntry2 = findMostDescendedChild(treeListEntry.getPrev());
                }
            }
            else {
                treeListEntry2 = treeListEntry.getParent();
            }
        }
        return treeListEntry2;
    }
    
    public static TreeListEntry TraverseCurrentAndUp(final TreeListEntry treeListEntry) {
        TreeListEntry treeListEntry2 = treeListEntry;
        TreeListEntry treeListEntry3;
        TreeListEntry parent;
        for (treeListEntry3 = Traverse(treeListEntry, 0, TreeList.CURRENT); treeListEntry3 == null; treeListEntry3 = parent.getNext()) {
            parent = treeListEntry2.getParent();
            if (parent == null) {
                treeListEntry3 = null;
                break;
            }
            treeListEntry2 = parent;
        }
        return treeListEntry3;
    }
    
    public static TreeListEntry findMostDescendedChild(final TreeListEntry treeListEntry) {
        TreeListEntry treeListEntry2 = null;
        if (treeListEntry != null) {
            for (TreeListEntry traverse = treeListEntry; traverse != treeListEntry.getNext(); traverse = Traverse(traverse, 0, TreeList.FULL)) {
                treeListEntry2 = traverse;
            }
        }
        return treeListEntry2;
    }
    
    static {
        TreeList.FULL = 0;
        TreeList.CURRENT = 1;
        TreeList.CURRENTANDUP = 2;
    }
}
