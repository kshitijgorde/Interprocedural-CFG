// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver;

import com.easypano.tourweaver.d.b;

public interface i
{
    void updateScene(final int p0, final int p1, final int p2, final int p3, final String p4);
    
    void updateScene(final byte[] p0, final int p1, final int p2, final int p3, final int p4, final String p5);
    
    void updateScene(final byte[] p0, final String p1, final int p2, final int p3);
    
    void updateScene(final int p0, final int p1, final int p2, final String p3);
    
    void updateConfig(final b p0);
    
    void updateObject(final Object p0, final String p1);
}
