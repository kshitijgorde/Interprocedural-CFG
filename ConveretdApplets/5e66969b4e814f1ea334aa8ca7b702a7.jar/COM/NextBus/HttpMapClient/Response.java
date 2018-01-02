// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.util.Enumeration;
import java.util.Vector;
import java.io.Serializable;

public class Response implements Serializable
{
    private static final long serialVersionUID = 7143088657274012345L;
    private Vector _components;
    
    public Response() {
        this._components = new Vector();
    }
    
    public final Enumeration a() {
        return this._components.elements();
    }
    
    public String toString() {
        return "Components: " + this._components + ".";
    }
}
