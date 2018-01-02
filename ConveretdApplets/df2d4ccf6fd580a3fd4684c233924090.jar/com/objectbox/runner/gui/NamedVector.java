// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui;

import java.util.Vector;

public class NamedVector extends Vector
{
    public String name;
    
    public NamedVector() {
    }
    
    public NamedVector(final int n) {
        super(n);
    }
    
    public NamedVector(final int n, final int n2) {
        super(n, n2);
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
}
