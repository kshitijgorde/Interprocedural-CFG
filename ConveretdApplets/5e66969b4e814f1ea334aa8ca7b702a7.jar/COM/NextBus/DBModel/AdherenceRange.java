// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.DBModel;

import java.io.Serializable;

public class AdherenceRange implements Serializable
{
    private static final long serialVersionUID = 7092845201173706021L;
    private int _earliness;
    private int _lateness;
    
    public final int a() {
        return this._earliness;
    }
    
    public final int b() {
        return this._lateness;
    }
    
    public String toString() {
        return "earliness=" + this._earliness + ",lateness=" + this._lateness;
    }
}
