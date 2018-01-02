// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.ellipsoids;

public class AN_Ellipsoid extends Ellipsoid
{
    public byte get_id() {
        return 2;
    }
    
    public AN_Ellipsoid() {
        this.a = 6378160.0;
        this.f = 298.25;
    }
}
