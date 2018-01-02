// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.ellipsoids;

public class AM_Ellipsoid extends Ellipsoid
{
    public byte get_id() {
        return 1;
    }
    
    public AM_Ellipsoid() {
        this.a = 6377340.189;
        this.f = 299.3249646;
    }
}
