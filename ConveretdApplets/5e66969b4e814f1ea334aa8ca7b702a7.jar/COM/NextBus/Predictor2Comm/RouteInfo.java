// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2Comm;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.io.Serializable;

public class RouteInfo extends RouteInfoBase implements Serializable
{
    private static final long serialVersionUID = -1161754259054961387L;
    private Vector _pathTags;
    private Vector _stops;
    private Hashtable _tripPatternTags;
    private boolean _initialized;
    private final Integer _phoneCodeForRoute;
    
    public final synchronized int a() {
        return this._stops.size();
    }
    
    public final synchronized Stop a(final int n) {
        return this._stops.get(n);
    }
    
    public final synchronized String[] b() {
        final int size = this._tripPatternTags.size();
        final Enumeration<String> keys = this._tripPatternTags.keys();
        final String[] array = new String[size];
        for (int i = 0; i < array.length; ++i) {
            array[i] = keys.nextElement();
        }
        return array;
    }
    
    public final synchronized Enumeration a(final String s) {
        final Vector c;
        if ((c = this.c(s)) == null) {
            return null;
        }
        return c.elements();
    }
    
    private synchronized Vector c(final String s) {
        final Vector<Stop> vector;
        if ((vector = this._tripPatternTags.get(s)) == null) {
            return null;
        }
        if (vector.size() != 0) {
            return vector;
        }
        for (int i = 0; i < this._stops.size(); ++i) {
            final Stop stop;
            final Integer a;
            if ((a = (stop = (Stop)this._stops.get(i)).a(s)) != null) {
                final int intValue = a;
                boolean b = false;
                for (int j = 0; j < vector.size(); ++j) {
                    if (intValue < vector.elementAt(j).a(s)) {
                        vector.insertElementAt(stop, j);
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    vector.addElement(stop);
                }
            }
        }
        return vector;
    }
    
    public final synchronized int[] b(final String s) {
        final Vector c;
        if ((c = this.c(s)) == null) {
            return null;
        }
        final int[] array = new int[c.size()];
        for (int i = 0; i < c.size(); ++i) {
            array[i] = c.elementAt(i).b();
        }
        return array;
    }
    
    public final Enumeration c() {
        return this._pathTags.elements();
    }
    
    public String toString() {
        final StringBuilder sb;
        (sb = new StringBuilder(200)).append("RouteTag=\"" + this._routeTag + "\",color=" + this._color + "(0x" + Integer.toHexString(this._color) + ")" + ",hidden=" + this._hidden + ",phoneCode=" + this._phoneCodeForRoute);
        sb.append("\n  Stops: total=");
        sb.append(this._stops.size());
        sb.append("\n");
        for (int i = 0; i < this._stops.size(); ++i) {
            sb.append("    ");
            sb.append(this._stops.get(i));
            sb.append("\n");
        }
        final int size = this._pathTags.size();
        sb.append("  PathTags=[");
        for (int j = 0; j < size; ++j) {
            if (j > 0) {
                sb.append(",");
            }
            sb.append((String)this._pathTags.get(j));
        }
        sb.append("]\n");
        sb.append("  TripPatternTags and list of *StopOrders* per Trip Pattern:\n");
        final String[] b = this.b();
        for (int n = 0; b != null && n < b.length; ++n) {
            final String s = b[n];
            final int[] b2 = this.b(s);
            sb.append("  ").append(s).append(":");
            for (int n2 = 0; b2 != null && n2 < b2.length; ++n2) {
                if (n2 > 0) {
                    sb.append(",");
                }
                sb.append(b2[n2]);
            }
            sb.append("\n");
        }
        sb.append("  TripPatternTags and list of *StopTags* per Trip Pattern:\n");
        for (int n3 = 0; b != null && n3 < b.length; ++n3) {
            final String s2 = b[n3];
            final Enumeration a = this.a(s2);
            sb.append("  ").append(s2).append(":");
            while (a.hasMoreElements()) {
                sb.append(a.nextElement().c()).append(",");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
