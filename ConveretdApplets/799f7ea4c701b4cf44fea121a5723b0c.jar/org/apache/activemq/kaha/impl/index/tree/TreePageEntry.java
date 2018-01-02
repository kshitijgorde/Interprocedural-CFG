// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.activemq.kaha.impl.index.tree;

class TreePageEntry
{
    private TreeEntry treeEntry;
    private TreePage treePage;
    private TreePage.Flavour flavour;
    private int index;
    
    TreePageEntry(final TreeEntry treeEntry, final TreePage treePage, final TreePage.Flavour flavour, final int index) {
        this.index = -1;
        this.treeEntry = treeEntry;
        this.treePage = treePage;
        this.flavour = flavour;
        this.index = index;
    }
    
    TreePage.Flavour getFlavour() {
        return this.flavour;
    }
    
    void setFlavour(final TreePage.Flavour flavour) {
        this.flavour = flavour;
    }
    
    TreePage getTreePage() {
        return this.treePage;
    }
    
    void setTreePage(final TreePage treePage) {
        this.treePage = treePage;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setIndex(final int index) {
        this.index = index;
    }
    
    public TreeEntry getTreeEntry() {
        return this.treeEntry;
    }
    
    public void setTreeEntry(final TreeEntry treeEntry) {
        this.treeEntry = treeEntry;
    }
}
