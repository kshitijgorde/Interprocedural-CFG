// 
// Decompiled by Procyon v0.5.30
// 

package Sirius.tree;

import java.io.Serializable;

public class SIRpoint implements Serializable
{
    private int x;
    private int y;
    
    public int getX() {
        return this.x;
    }
    
    public SIRpoint(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getY() {
        return this.y;
    }
}
