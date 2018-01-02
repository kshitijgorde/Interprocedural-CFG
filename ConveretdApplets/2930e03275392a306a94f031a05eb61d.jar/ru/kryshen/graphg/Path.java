// 
// Decompiled by Procyon v0.5.30
// 

package ru.kryshen.graphg;

import java.util.Vector;

public class Path extends Vector
{
    public boolean marked;
    
    public Path() {
        this.marked = false;
    }
    
    public Path(final int n) {
        super(n);
        this.marked = false;
    }
    
    public synchronized Edge getEdge(final int n) {
        return this.elementAt(super.elementCount - n - 1);
    }
}
