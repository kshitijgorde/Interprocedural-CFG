// 
// Decompiled by Procyon v0.5.30
// 

public class Tree
{
    public Node[] nodes;
    public String[] imageIndex;
    
    public Tree() {
        this.nodes = new Node[500];
        this.imageIndex = new String[500];
        for (int i = 0; i < 500; ++i) {
            this.nodes[i] = new Node();
            this.imageIndex[i] = new String("");
        }
    }
    
    public void Open(final int index) {
        if (!this.nodes[index].isFolder) {
            return;
        }
        this.nodes[index].isOpened = true;
    }
    
    public void Close(final int index) {
        if (!this.nodes[index].isFolder) {
            return;
        }
        this.nodes[index].isOpened = false;
    }
    
    public int WhatLevel(final int index) {
        if (!this.nodes[index].hasParent) {
            return 0;
        }
        int i = index;
        int count = 0;
        final boolean done = false;
        while (this.nodes[i].hasParent) {
            ++count;
            i = this.nodes[i].parentIndex;
        }
        return count;
    }
    
    public int getActiveNode(final int y, final int image_size, final int initial_y, final int abstand_y) {
        final int y_count = (y - initial_y) / (image_size + abstand_y) + 1;
        int i = 0;
        int visible_count = 0;
        while (this.nodes[i].isValid && i < 500) {
            if (this.isNodeVisible(i)) {
                ++visible_count;
            }
            if (visible_count == y_count) {
                return i;
            }
            ++i;
        }
        return 999;
    }
    
    public boolean isNodeVisible(final int index) {
        if (!this.nodes[index].hasParent) {
            return true;
        }
        int i = index;
        while (this.nodes[i].hasParent) {
            i = this.nodes[i].parentIndex;
            if (!this.nodes[i].isOpened || !this.nodes[i].isValid || !this.nodes[i].isFolder) {
                return false;
            }
        }
        return true;
    }
    
    public int countVisibleChildren(final int index) {
        if (!this.nodes[index].isFolder || (!this.nodes[index].isOpened && this.nodes[index].isFolder)) {
            return 0;
        }
        int count = 0;
        for (int zaehler = 1; this.WhatLevel(index) < this.WhatLevel(index + zaehler); ++zaehler) {
            if (this.isNodeVisible(index + zaehler)) {
                ++count;
            }
        }
        return count;
    }
}
