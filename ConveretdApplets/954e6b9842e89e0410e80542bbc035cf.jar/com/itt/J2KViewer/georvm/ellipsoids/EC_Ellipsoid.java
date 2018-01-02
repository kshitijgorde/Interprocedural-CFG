// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.ellipsoids;

public class EC_Ellipsoid extends Ellipsoid
{
    public byte get_id() {
        return 9;
    }
    
    public EC_Ellipsoid() {
        this.a = 6377301.243;
        this.f = 300.8017;
    }
}
