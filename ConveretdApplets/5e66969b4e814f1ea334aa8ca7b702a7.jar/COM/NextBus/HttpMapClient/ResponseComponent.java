// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.io.Serializable;

public class ResponseComponent implements Serializable
{
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    public static int e;
    public static int f;
    public static int g;
    public static int h;
    private static final long serialVersionUID = 7143088657274067890L;
    private long _time;
    private int _errorCode;
    private String _agencyName;
    private Object _responseObject;
    
    public final String a() {
        return this._agencyName;
    }
    
    public final long b() {
        return this._time;
    }
    
    public final int c() {
        return this._errorCode;
    }
    
    public final Object d() {
        return this._responseObject;
    }
    
    public String toString() {
        return "Time: " + this._time + ", is error: " + ((this._errorCode == 0) ? ("" + this._errorCode) : "no") + ", response object: " + this._responseObject + ".";
    }
    
    static {
        ResponseComponent.a = -1;
        ResponseComponent.b = 0;
        ResponseComponent.c = 1;
        ResponseComponent.d = 2;
        ResponseComponent.e = 4;
        ResponseComponent.f = 5;
        ResponseComponent.g = 9;
        ResponseComponent.h = 10;
    }
}
