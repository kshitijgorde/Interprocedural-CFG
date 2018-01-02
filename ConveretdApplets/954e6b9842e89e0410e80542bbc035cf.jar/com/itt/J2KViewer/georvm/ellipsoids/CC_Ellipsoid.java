// 
// Decompiled by Procyon v0.5.30
// 

package com.itt.J2KViewer.georvm.ellipsoids;

public class CC_Ellipsoid extends Ellipsoid
{
    public byte get_id() {
        return 5;
    }
    
    public CC_Ellipsoid() {
        this.a = 6378206.4;
        this.f = 294.9786982;
    }
}
