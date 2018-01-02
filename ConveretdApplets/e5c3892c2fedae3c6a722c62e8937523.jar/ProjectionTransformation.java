import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

interface ProjectionTransformation
{
    LatLong projToLatLong(final int p0, final int p1);
    
    Point latLongToProj(final LatLong p0);
}
