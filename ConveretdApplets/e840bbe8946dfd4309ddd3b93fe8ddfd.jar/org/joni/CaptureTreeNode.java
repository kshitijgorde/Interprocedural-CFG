// 
// Decompiled by Procyon v0.5.30
// 

package org.joni;

public class CaptureTreeNode
{
    int group;
    int beg;
    int end;
    int numChildren;
    CaptureTreeNode[] children;
    static final int HISTORY_TREE_INIT_ALLOC_SIZE = 8;
    
    CaptureTreeNode() {
        this.beg = -1;
        this.end = -1;
        this.group = -1;
    }
    
    void addChild(final CaptureTreeNode child) {
        if (this.children == null) {
            this.children = new CaptureTreeNode[8];
        }
        else if (this.numChildren >= this.children.length) {
            final CaptureTreeNode[] tmp = new CaptureTreeNode[this.children.length << 1];
            System.arraycopy(this.children, 0, tmp, 0, this.children.length);
            this.children = tmp;
        }
        this.children[this.numChildren] = child;
        ++this.numChildren;
    }
    
    void clear() {
        for (int i = 0; i < this.numChildren; ++i) {
            this.children[i] = null;
        }
        this.numChildren = 0;
        final int n = -1;
        this.end = n;
        this.beg = n;
        this.group = -1;
    }
    
    CaptureTreeNode cloneTree() {
        final CaptureTreeNode clone = new CaptureTreeNode();
        clone.beg = this.beg;
        clone.end = this.end;
        for (int i = 0; i < this.numChildren; ++i) {
            final CaptureTreeNode child = this.children[i].cloneTree();
            clone.addChild(child);
        }
        return clone;
    }
}
