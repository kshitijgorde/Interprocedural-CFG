// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.ellipsoids;

public class WE_Ellipsoid extends Ellipsoid
{
    public byte get_id() {
        return 20;
    }
    
    public WE_Ellipsoid() {
        this.a = 6378137.0;
        this.f = 298.257223563;
    }
}
