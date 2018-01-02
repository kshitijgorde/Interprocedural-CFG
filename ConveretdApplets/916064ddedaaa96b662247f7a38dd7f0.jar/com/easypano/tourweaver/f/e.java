// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.awt.Shape;
import java.awt.Rectangle;

public interface e
{
    boolean e();
    
    String f();
    
    int g();
    
    void a(final h p0);
    
    void b(final h p0);
    
    void setName(final String p0);
    
    String getName();
    
    void a(final int p0, final int p1);
    
    int getX();
    
    int getY();
    
    void setBounds(final int p0, final int p1, final int p2, final int p3);
    
    void setBounds(final Rectangle p0);
    
    Rectangle getBounds();
    
    void a(final Shape p0);
    
    Shape h();
    
    void a(final double p0, final double p1);
    
    void destroy();
}
