// 
// Decompiled by Procyon v0.5.30
// 

package Sirius.tree;

import java.io.Serializable;

public class Tree implements Serializable
{
    private int nodeindex;
    
    public Tree(final int nodeindex) {
        this.nodeindex = nodeindex;
    }
    
    public int getNodeindex() {
        return this.nodeindex;
    }
}
