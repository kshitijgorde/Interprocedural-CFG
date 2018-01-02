// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.ellipsoids;

public class EA_Ellipsoid extends Ellipsoid
{
    public byte get_id() {
        return 7;
    }
    
    public EA_Ellipsoid() {
        this.a = 6377276.345;
        this.f = 300.8017;
    }
}
