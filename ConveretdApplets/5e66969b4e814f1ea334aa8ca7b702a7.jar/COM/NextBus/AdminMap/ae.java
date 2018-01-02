// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.Point;
import java.util.Map;

public final class ae implements Cloneable
{
    private Map a;
    
    public ae(final Map a) {
        this.a = a;
    }
    
    public final void a() {
        this.a(7);
        this.a(new Point(0, 0));
    }
    
    public final int b() {
        final String s;
        if ((s = this.a.get("zoom_level")) == null) {
            throw new NullPointerException("getZoomLevel(): zoomLevel is null");
        }
        return Integer.parseInt(s);
    }
    
    public final void a(final int n) {
        this.a.put("zoom_level", Integer.toString(n));
    }
    
    public final Point c() {
        final String s = this.a.get("origin_x");
        final String s2 = this.a.get("origin_y");
        if (s == null) {
            throw new NullPointerException("getOrigin(): xStr is null");
        }
        if (s2 == null) {
            throw new NullPointerException("getOrigin(): yStr is null");
        }
        return new Point(Integer.parseInt(s), Integer.parseInt(s2));
    }
    
    public final void a(final Point point) {
        this.a.put("origin_x", Integer.toString(point.x));
        this.a.put("origin_y", Integer.toString(point.y));
    }
}
