// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.ellipsoids;

public class ED_Ellipsoid extends Ellipsoid
{
    public byte get_id() {
        return 10;
    }
    
    public ED_Ellipsoid() {
        this.a = 6377295.664;
        this.f = 300.8017;
    }
}
