// 
// Decompiled by Procyon v0.5.30
// 

package Sirius.tree;

import java.io.Serializable;

public class Areadata implements Serializable
{
    private int nodeindex;
    private int topy;
    private int leftx;
    private int bottomy;
    private int rightx;
    private int action;
    
    public int getAction() {
        return this.action;
    }
    
    public Areadata(final int nodeindex, final int topy, final int leftx, final int bottomy, final int rightx, final int action) {
        this.nodeindex = nodeindex;
        this.topy = topy;
        this.leftx = leftx;
        this.bottomy = bottomy;
        this.rightx = rightx;
        this.action = action;
    }
    
    public int getLeftx() {
        return this.leftx;
    }
    
    public int getRightx() {
        return this.rightx;
    }
    
    public int getTopy() {
        return this.topy;
    }
    
    public int getNodeindex() {
        return this.nodeindex;
    }
    
    public int getBottomy() {
        return this.bottomy;
    }
}
