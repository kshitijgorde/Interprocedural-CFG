// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2Comm;

import java.util.Date;
import java.text.DateFormat;
import COM.NextBus.AdminMap.x;
import java.io.Serializable;

public class StopPredictionInfo implements Serializable
{
    private static final long serialVersionUID = 8469140216274905660L;
    private final String _vehicleTag;
    private final String _routeTag;
    private final String _stopTag;
    private final String _dirTag;
    private final String _dirDestTag;
    private final String _jobTag;
    private final int _pathIndex;
    private final String _tripTag;
    private final long _predictionTime;
    private final boolean _isDeparture;
    private final boolean _affectedByTimepoint;
    private final boolean _scheduleBased;
    private final double _badness;
    private final int _scheduledTimeForStop;
    
    public StopPredictionInfo(final String vehicleTag, final String routeTag, final String stopTag, final String dirTag, final String dirDestTag, final String jobTag, final int pathIndex, final String tripTag, final long predictionTime, final boolean isDeparture, final boolean affectedByTimepoint, final boolean scheduleBased, final double badness, final int scheduledTimeForStop) {
        this._vehicleTag = vehicleTag;
        this._routeTag = routeTag;
        this._stopTag = stopTag;
        this._dirTag = dirTag;
        this._dirDestTag = dirDestTag;
        this._jobTag = jobTag;
        this._pathIndex = pathIndex;
        this._tripTag = tripTag;
        this._predictionTime = predictionTime;
        this._isDeparture = isDeparture;
        this._affectedByTimepoint = affectedByTimepoint;
        this._scheduleBased = scheduleBased;
        this._badness = badness;
        this._scheduledTimeForStop = scheduledTimeForStop;
    }
    
    public final String a() {
        return this._vehicleTag;
    }
    
    public final String b() {
        return this._routeTag;
    }
    
    public final String c() {
        return this._stopTag;
    }
    
    public final String d() {
        return this._dirDestTag;
    }
    
    public final String e() {
        return this._dirTag;
    }
    
    public final String f() {
        return this._jobTag;
    }
    
    public final int g() {
        return this._pathIndex;
    }
    
    public final String h() {
        return this._tripTag;
    }
    
    public final long i() {
        return this._predictionTime;
    }
    
    public final boolean j() {
        return this._affectedByTimepoint;
    }
    
    public final boolean k() {
        return this._scheduleBased;
    }
    
    public final boolean l() {
        return this._isDeparture;
    }
    
    public final double m() {
        return this._badness;
    }
    
    public int hashCode() {
        return (int)this._predictionTime;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o != null && this.getClass() == o.getClass()) {
            final StopPredictionInfo stopPredictionInfo = (StopPredictionInfo)o;
            if (this._predictionTime == stopPredictionInfo._predictionTime && this._isDeparture == stopPredictionInfo._isDeparture && x.a(this._stopTag, stopPredictionInfo._stopTag) && x.a(this._dirDestTag, stopPredictionInfo._dirDestTag) && x.a(this._vehicleTag, stopPredictionInfo._vehicleTag)) {
                return true;
            }
        }
        return false;
    }
    
    public String toString() {
        return this.a(null);
    }
    
    public final String a(final DateFormat dateFormat) {
        final String s = this._isDeparture ? "departure" : "arrival";
        String s2;
        if (dateFormat != null) {
            s2 = dateFormat.format(new Date(this._predictionTime));
        }
        else {
            s2 = this._predictionTime + "";
        }
        String s3 = "";
        if (this._affectedByTimepoint) {
            s3 = " (affected by timepoint)";
        }
        String s4 = "";
        if (this._scheduleBased) {
            s4 = " (schedule based)";
        }
        return "type=" + s + " _routeTag=" + this._routeTag + " _stopTag=" + this._stopTag + " _dirTag=" + this._dirTag + " predTime=" + s2 + " _dirDestTag=" + this._dirDestTag + " _pathIndex=" + this._pathIndex + ")" + s3 + s4 + " _vehicleTag=" + this._vehicleTag + " _jobTag=" + this._jobTag + " _tripTag=" + this._tripTag + " _badness=" + this._badness;
    }
}
