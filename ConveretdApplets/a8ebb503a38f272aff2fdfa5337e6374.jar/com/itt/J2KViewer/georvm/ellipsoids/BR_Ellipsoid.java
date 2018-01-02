// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.ellipsoids;

public class BR_Ellipsoid extends Ellipsoid
{
    public byte get_id() {
        return 4;
    }
    
    public BR_Ellipsoid() {
        this.a = 6377397.155;
        this.f = 299.1528128;
    }
}
