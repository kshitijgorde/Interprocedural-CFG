// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.ellipsoids;

public class EB_Ellipsoid extends Ellipsoid
{
    public byte get_id() {
        return 8;
    }
    
    public EB_Ellipsoid() {
        this.a = 6377298.556;
        this.f = 300.8017;
    }
}
