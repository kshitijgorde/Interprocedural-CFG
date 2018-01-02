// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.ellipsoids;

public class BN_Ellipsoid extends Ellipsoid
{
    public byte get_id() {
        return 3;
    }
    
    public BN_Ellipsoid() {
        this.a = 6377483.865;
        this.f = 299.1528128;
    }
}
