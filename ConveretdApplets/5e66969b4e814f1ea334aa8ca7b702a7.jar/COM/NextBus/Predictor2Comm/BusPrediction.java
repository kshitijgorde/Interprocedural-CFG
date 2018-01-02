// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2Comm;

import java.text.SimpleDateFormat;
import java.io.DataInputStream;
import COM.NextBus.AdminMap.x;
import java.util.Date;
import java.text.DateFormat;
import java.io.Serializable;

public class BusPrediction implements Serializable
{
    private static final long serialVersionUID = 7996066153778605873L;
    private static final DateFormat a;
    private final String _routeTag;
    private final String _stopTag;
    private final String _dirTag;
    private final long _prediction;
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        final String format;
        synchronized (BusPrediction.a) {
            format = BusPrediction.a.format(new Date(this._prediction));
        }
        sb.append("{RouteTag=\"");
        final BusPrediction busPrediction;
        sb.append(a(busPrediction._routeTag));
        sb.append("\"");
        sb.append(",StopTag=\"");
        sb.append(a(busPrediction._stopTag));
        sb.append("\"");
        sb.append(",DirTag=\"");
        sb.append(a(busPrediction._dirTag));
        sb.append("\"");
        sb.append(",Pred=");
        sb.append(busPrediction._prediction).append(" (").append(format).append(")");
        sb.append("}");
        return sb.toString();
    }
    
    public final String a() {
        return a(this._stopTag);
    }
    
    public final String b() {
        return a(this._dirTag);
    }
    
    public final long c() {
        return this._prediction;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && this.getClass() == o.getClass()) {
            final BusPrediction busPrediction = (BusPrediction)o;
            if (x.a(this._routeTag, busPrediction._routeTag) && x.a(this._stopTag, busPrediction._stopTag) && x.a(this._dirTag, busPrediction._dirTag) && this._prediction == busPrediction._prediction) {
                return true;
            }
        }
        return false;
    }
    
    public static BusPrediction a(final DataInputStream dataInputStream) {
        return new BusPrediction(dataInputStream);
    }
    
    private BusPrediction(final DataInputStream dataInputStream) {
        this._routeTag = dataInputStream.readUTF();
        this._stopTag = dataInputStream.readUTF();
        this._dirTag = dataInputStream.readUTF();
        dataInputStream.readInt();
        this._prediction = (dataInputStream.readInt() & 0xFFFFFFFFL) * 1000L + dataInputStream.readUnsignedShort();
    }
    
    private static String a(final String s) {
        if (s == null) {
            return null;
        }
        if (s.equals("")) {
            return null;
        }
        return s;
    }
    
    static {
        a = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
    }
}
