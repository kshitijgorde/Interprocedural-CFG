// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.coords;

public class Ups_Coord_3d
{
    public char hemisphere;
    public double x;
    public double y;
    
    public Ups_Coord_3d() {
    }
    
    public Ups_Coord_3d(final double x, final double y, final char hemisphere) {
        this.x = x;
        this.y = y;
        this.hemisphere = hemisphere;
    }
}
