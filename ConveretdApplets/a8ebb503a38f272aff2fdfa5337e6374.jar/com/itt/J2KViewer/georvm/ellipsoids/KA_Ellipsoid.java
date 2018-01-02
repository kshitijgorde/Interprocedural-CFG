// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.ellipsoids;

public class KA_Ellipsoid extends Ellipsoid
{
    public byte get_id() {
        return 16;
    }
    
    public KA_Ellipsoid() {
        this.a = 6378245.0;
        this.f = 298.3;
    }
}
