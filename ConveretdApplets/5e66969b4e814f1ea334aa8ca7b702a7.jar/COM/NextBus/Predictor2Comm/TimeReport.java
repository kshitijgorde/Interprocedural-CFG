// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2Comm;

import java.util.Date;
import java.text.DateFormat;
import java.io.Serializable;

public class TimeReport implements Serializable
{
    private static final long serialVersionUID = -375124318053013771L;
    private final long _currentTime;
    private final int _timeZone;
    
    public TimeReport() {
        this._currentTime = System.currentTimeMillis();
        this._timeZone = 0;
    }
    
    public String toString() {
        return "Time: " + this._currentTime + " Zone: " + this._timeZone + " (" + DateFormat.getDateTimeInstance(0, 0).format(new Date(this._currentTime)) + ")";
    }
    
    public final long a() {
        return this._currentTime;
    }
}
