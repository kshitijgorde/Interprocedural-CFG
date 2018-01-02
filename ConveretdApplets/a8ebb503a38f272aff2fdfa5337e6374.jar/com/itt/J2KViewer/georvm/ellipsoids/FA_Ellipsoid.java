// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.ellipsoids;

public class FA_Ellipsoid extends Ellipsoid
{
    public byte get_id() {
        return 12;
    }
    
    public FA_Ellipsoid() {
        this.a = 6378155.0;
        this.f = 298.3;
    }
}
