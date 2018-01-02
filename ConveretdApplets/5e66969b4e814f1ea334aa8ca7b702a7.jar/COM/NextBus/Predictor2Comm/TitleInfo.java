// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2Comm;

import java.util.Enumeration;
import java.util.Hashtable;
import java.io.Serializable;

public class TitleInfo implements Serializable
{
    private static final long serialVersionUID = -3564078297634351670L;
    private TitleGroup _titleGroup;
    private Hashtable _routes;
    private Hashtable _stops;
    private Hashtable _dirs;
    
    public final String a(final String s) {
        return this._routes.get(s);
    }
    
    public final String b(final String s) {
        return this._stops.get(s);
    }
    
    public final String c(final String s) {
        if (s == null) {
            return null;
        }
        return this._dirs.get(s);
    }
    
    public String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer(500)).append("TitleGroup=");
        sb.append(this._titleGroup.a());
        sb.append("\nRoutes=");
        final Enumeration<String> keys = (Enumeration<String>)this._routes.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final String s2 = this._routes.get(s);
            sb.append("RouteTag=");
            sb.append(s);
            sb.append(",Title=");
            sb.append(s2);
        }
        sb.append("\nStops:");
        final Enumeration<String> keys2 = (Enumeration<String>)this._stops.keys();
        while (keys2.hasMoreElements()) {
            final String s3 = keys2.nextElement();
            final String s4 = this._stops.get(s3);
            sb.append("StopTag=");
            sb.append(s3);
            sb.append(",Title=");
            sb.append(s4);
        }
        sb.append("\nDirs:");
        final Enumeration<String> keys3 = (Enumeration<String>)this._dirs.keys();
        while (keys3.hasMoreElements()) {
            final String s5 = keys3.nextElement();
            final String s6 = this._dirs.get(s5);
            sb.append("DirTag=");
            sb.append(s5);
            sb.append(",Title=");
            sb.append(s6);
        }
        return sb.toString();
    }
}
