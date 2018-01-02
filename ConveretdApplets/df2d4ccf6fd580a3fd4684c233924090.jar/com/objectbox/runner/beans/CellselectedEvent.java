// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.beans;

import java.util.EventObject;

public class CellselectedEvent extends EventObject
{
    private int rowselected;
    private int colselected;
    
    public CellselectedEvent(final Object o) {
        super(o);
    }
    
    public CellselectedEvent(final Object o, final int colselected, final int rowselected) {
        super(o);
        this.rowselected = rowselected;
        this.colselected = colselected;
    }
    
    public int getColselected() {
        return this.colselected;
    }
    
    public int getRowselected() {
        return this.rowselected;
    }
}
