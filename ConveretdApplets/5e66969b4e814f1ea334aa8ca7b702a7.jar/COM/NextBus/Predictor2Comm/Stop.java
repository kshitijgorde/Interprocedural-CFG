// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2Comm;

import java.util.Iterator;
import java.util.Map;
import java.io.Serializable;

public class Stop implements Serializable
{
    private static final long serialVersionUID = 1291406144554623895L;
    private final Location _location;
    private final int _stopOrder;
    private final String _stopTag;
    private final String _dirTag;
    private final String _stopName;
    private final boolean _hidden;
    private Map _stopOrdersForTripPatterns;
    private final boolean _isSchedArrival;
    private final Integer _phoneCodeForStop;
    
    public final Location a() {
        return this._location;
    }
    
    public final int b() {
        return this._stopOrder;
    }
    
    public final String c() {
        return this._stopTag;
    }
    
    public final String d() {
        return this._dirTag;
    }
    
    public final Integer a(final String s) {
        return this._stopOrdersForTripPatterns.get(s);
    }
    
    public final boolean e() {
        return this._hidden;
    }
    
    public String toString() {
        final StringBuilder sb;
        (sb = new StringBuilder()).append("stopOrder=").append(this._stopOrder);
        sb.append(",stopTag=\"").append(this._stopTag);
        sb.append("\",dirTag=\"").append(this._dirTag);
        sb.append("\",name=\"").append(this._stopName);
        sb.append("\",").append(this._location);
        sb.append(",hidden=").append(this._hidden);
        sb.append(",stopOrdersForTripPatternTags={");
        for (final String s : this._stopOrdersForTripPatterns.keySet()) {
            sb.append(s).append("->").append(this._stopOrdersForTripPatterns.get(s)).append(",");
        }
        sb.append("}");
        sb.append(",schedArrival=").append(this._isSchedArrival);
        sb.append(",phoneCode=" + this._phoneCodeForStop);
        return sb.toString();
    }
}
