// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.ellipsoids;

public class CD_Ellipsoid extends Ellipsoid
{
    public byte get_id() {
        return 6;
    }
    
    public CD_Ellipsoid() {
        this.a = 6378249.145;
        this.f = 293.465;
    }
}
