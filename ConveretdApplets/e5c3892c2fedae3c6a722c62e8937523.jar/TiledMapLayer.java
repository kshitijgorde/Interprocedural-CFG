import java.awt.Point;
import java.awt.Color;
import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class TiledMapLayer extends MapLayer
{
    private String baseFilename;
    protected MapLayerFileCache fileCache;
    private final int projCoordGridDivisor = 1000000;
    private final int projCoordGridIncrement = 5;
    
    TiledMapLayer(final Component component, final String s, final String baseFilename, final Color color, final int n, final boolean b) {
        super(component, s, color, n, b);
        this.baseFilename = baseFilename;
        this.fileCache = new MapLayerFileCache();
    }
    
    protected int to15deg(final double n, final boolean b) {
        final int n2 = (int)n;
        int n3 = n2 / 15;
        final int n4 = n2 % 15;
        if (n4 < 0 || (n4 == 0 && b)) {
            --n3;
        }
        return n3 * 15;
    }
    
    @Override
    public int setDisplayAreaUsingLatLong(final LatLong latLong, final LatLong latLong2, final int n) {
        int n2 = 0;
        this.fileCache.age();
        if (latLong == null || latLong2 == null) {
            return 0;
        }
        int to15deg = this.to15deg(latLong.longitude, true);
        int to15deg2 = this.to15deg(latLong2.longitude, false);
        final int to15deg3 = this.to15deg(latLong.latitude, false);
        final int n3 = this.to15deg(latLong2.latitude, true) + 15;
        final int n4 = to15deg3 + 15;
        if (to15deg2 - to15deg > 180 || to15deg - to15deg2 > 180) {
            to15deg = 165;
            to15deg2 = 180;
        }
        if (to15deg > to15deg2) {
            final int n5 = to15deg2;
            to15deg2 = to15deg;
            to15deg = n5;
        }
        for (int i = to15deg; i <= to15deg2; i += 15) {
            for (int j = n4; j >= n3; j -= 15) {
                final StringBuffer sb = new StringBuffer("linework/");
                if (j < 0) {
                    sb.append("S");
                }
                else {
                    sb.append("N");
                }
                sb.append(Integer.toString(Math.abs(j)));
                if (i < 0) {
                    sb.append("W");
                }
                else {
                    sb.append("E");
                }
                sb.append(Integer.toString(Math.abs(i)));
                final StringBuffer sb2 = new StringBuffer(sb.toString());
                sb2.append(this.baseFilename);
                sb2.append(".");
                sb2.append(Integer.toString(n));
                if (this.fileCache.addFile(sb2.toString())) {
                    ++n2;
                }
            }
        }
        return n2;
    }
    
    private Point findProjCoordGridTile(final Point point) {
        final Point point2 = new Point(0, 0);
        final int n = 5000000;
        int n2 = point.x / n;
        final int n3 = point.x % n;
        int n4 = point.y / n;
        final int n5 = point.y % n;
        if (n2 <= 0 && n3 < 0) {
            --n2;
        }
        if (n4 >= 0 && n5 > 0) {
            ++n4;
        }
        point2.x = n2 * 5;
        point2.y = n4 * 5;
        return point2;
    }
    
    @Override
    public int setDisplayAreaUsingProjCoords(final Point point, final Point point2, final int n) {
        int n2 = 0;
        this.fileCache.age();
        if (point == null || point2 == null) {
            return 0;
        }
        final Point projCoordGridTile = this.findProjCoordGridTile(point);
        final Point projCoordGridTile2 = this.findProjCoordGridTile(point2);
        for (int i = projCoordGridTile.x; i <= projCoordGridTile2.x; i += 5) {
            for (int j = projCoordGridTile.y; j >= projCoordGridTile2.y; j -= 5) {
                final StringBuffer sb = new StringBuffer("linework/");
                if (j < 0) {
                    sb.append("S");
                }
                else {
                    sb.append("N");
                }
                sb.append(Integer.toString(Math.abs(j)));
                if (i < 0) {
                    sb.append("W");
                }
                else {
                    sb.append("E");
                }
                sb.append(Integer.toString(Math.abs(i)));
                sb.append(this.baseFilename);
                sb.append(".");
                sb.append(Integer.toString(n));
                if (this.fileCache.addFile(sb.toString())) {
                    ++n2;
                }
            }
        }
        return n2;
    }
}
