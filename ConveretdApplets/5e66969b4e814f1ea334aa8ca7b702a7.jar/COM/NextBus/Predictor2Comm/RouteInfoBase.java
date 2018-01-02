// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.Predictor2Comm;

import java.io.Serializable;

public class RouteInfoBase implements Serializable
{
    private static final long serialVersionUID = -1161754259054900000L;
    protected String _routeTag;
    protected String _defName;
    protected int _color;
    protected boolean _hidden;
    
    public final String d() {
        return this._routeTag;
    }
    
    public final String e() {
        return this._defName;
    }
    
    public final int f() {
        return this._color;
    }
    
    public final boolean g() {
        return this._hidden;
    }
}
