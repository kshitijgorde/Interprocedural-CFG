// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2.rmi;

import java.io.Serializable;

public class StopTupleBean implements Serializable
{
    private static final long serialVersionUID = -990060462251392694L;
    private String _routeTag;
    private String _stopTag;
    private String _dirTag;
    
    public StopTupleBean(final String routeTag, final String stopTag, final String dirTag) {
        this._routeTag = routeTag;
        this._stopTag = stopTag;
        this._dirTag = dirTag;
    }
    
    public final String a() {
        return this._routeTag + "-" + this._stopTag + "-" + this._dirTag;
    }
    
    public String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer()).append(this._routeTag).append("-");
        sb.append(this._stopTag).append("-");
        sb.append(this._dirTag);
        return sb.toString();
    }
    
    public int hashCode() {
        return ((31 + ((this._dirTag == null) ? 0 : this._dirTag.hashCode())) * 31 + ((this._routeTag == null) ? 0 : this._routeTag.hashCode())) * 31 + ((this._stopTag == null) ? 0 : this._stopTag.hashCode());
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        final StopTupleBean stopTupleBean = (StopTupleBean)o;
        if (this._dirTag == null) {
            if (stopTupleBean._dirTag != null) {
                return false;
            }
        }
        else if (!this._dirTag.equals(stopTupleBean._dirTag)) {
            return false;
        }
        if (this._routeTag == null) {
            if (stopTupleBean._routeTag != null) {
                return false;
            }
        }
        else if (!this._routeTag.equals(stopTupleBean._routeTag)) {
            return false;
        }
        if (this._stopTag == null) {
            if (stopTupleBean._stopTag != null) {
                return false;
            }
        }
        else if (!this._stopTag.equals(stopTupleBean._stopTag)) {
            return false;
        }
        return true;
    }
}
