// 
// Decompiled by Procyon v0.5.30
// 

package com.tn.components;

public interface PD765ADisk
{
    PD765ASector getSector(final int p0, final int p1, final int p2);
    
    boolean isWriteProtected();
    
    void setSector(final int p0, final int p1, final int p2, final PD765ASector p3);
}
