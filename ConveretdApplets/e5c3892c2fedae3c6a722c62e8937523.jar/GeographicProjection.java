import java.awt.Point;

// 
// Decompiled by Procyon v0.5.30
// 

public class GeographicProjection implements ProjectionTransformation
{
    private final double SCALE_CONST = 100000.0;
    
    @Override
    public LatLong projToLatLong(final int n, final int n2) {
        return new LatLong(n2 / 100000.0, n / 100000.0);
    }
    
    @Override
    public Point latLongToProj(final LatLong latLong) {
        return new Point((int)(latLong.longitude * 100000.0), (int)(latLong.latitude * 100000.0));
    }
}
