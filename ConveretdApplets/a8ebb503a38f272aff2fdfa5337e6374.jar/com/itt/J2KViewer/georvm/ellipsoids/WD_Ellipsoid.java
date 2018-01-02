// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.ellipsoids;

public class WD_Ellipsoid extends Ellipsoid
{
    public byte get_id() {
        return 19;
    }
    
    public WD_Ellipsoid() {
        this.a = 6378135.0;
        this.f = 298.26;
    }
}
