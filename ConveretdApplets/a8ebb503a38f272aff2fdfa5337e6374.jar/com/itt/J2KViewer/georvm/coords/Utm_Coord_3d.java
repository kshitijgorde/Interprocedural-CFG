// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.coords;

public class Utm_Coord_3d
{
    public double x;
    public double y;
    public double z;
    public byte zone;
    public boolean hemisphere_north;
    
    public Utm_Coord_3d() {
    }
    
    public Utm_Coord_3d(final double x, final double y, final double z, final byte zone, final boolean hemisphere_north) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.zone = zone;
        this.hemisphere_north = hemisphere_north;
    }
}
