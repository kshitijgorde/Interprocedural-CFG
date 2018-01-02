// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.DBModel;

import COM.NextBus.Predictor2Comm.Location;
import java.awt.Color;
import java.io.Serializable;

public class YardZone implements Serializable
{
    private static final long serialVersionUID = 6855766207491694091L;
    private String _tag;
    private String _name;
    private Color _color;
    private Polygon _polygon;
    
    public final boolean a(final double n, final double n2) {
        final Location[] a = this._polygon.a();
        boolean b = false;
        int i = 0;
        int n3 = a.length - 1;
        while (i < a.length) {
            final double a2 = a[n3].a();
            final double b2 = a[n3].b();
            final double a3 = a[i].a();
            final double b4;
            final boolean b3 = (b4 = a[i].b()) <= n2 && n2 < b2;
            final boolean b5 = b2 <= n2 && n2 < b4;
            if ((b3 || b5) && n < (a2 - a3) * (n2 - b4) / (b2 - b4) + a3) {
                b = !b;
            }
            n3 = i++;
        }
        return b;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("name=" + this._name + ",polygon=(");
        final Location[] a = this._polygon.a();
        for (int i = 0; i < a.length; ++i) {
            sb.append("(" + a[i].a() + "," + a[i].b() + "),");
        }
        sb.setCharAt(sb.length() - 1, ')');
        return sb.toString();
    }
}
