// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2Comm;

import java.io.Serializable;

public class PathInfo implements Serializable
{
    private static final long serialVersionUID = 4115584440753745618L;
    private int _id;
    private String _pathTag;
    private int _pathLength;
    private Location[] _locations;
    
    private PathInfo() {
        this._id = 0;
        this._pathTag = null;
        this._pathLength = 0;
        this._locations = null;
    }
    
    public final Location[] a() {
        return this._locations;
    }
    
    public final String b() {
        return this._pathTag;
    }
    
    public String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer(200)).append("PathID=");
        sb.append(this._id);
        sb.append(",PathTag=\"");
        sb.append(this._pathTag);
        sb.append("\",PathLength=");
        sb.append(this._pathLength);
        sb.append(",NumLocations=");
        final int length = this._locations.length;
        sb.append(length);
        sb.append(",Locations=[");
        for (int i = 0; i < length; ++i) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(this._locations[i].toString());
        }
        sb.append("]");
        return sb.toString();
    }
}
