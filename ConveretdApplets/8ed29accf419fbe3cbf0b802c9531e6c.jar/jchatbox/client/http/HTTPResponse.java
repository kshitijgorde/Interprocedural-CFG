// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client.http;

import jchatbox.client.util.Debug;
import java.util.Vector;

public class HTTPResponse
{
    private StringBuffer _$15293;
    private Vector _$15294;
    private String _$15295;
    
    public HTTPResponse() {
        this._$15293 = null;
        this._$15294 = null;
        this._$15295 = null;
    }
    
    public void setHeaders(final Vector $15294) {
        this._$15294 = $15294;
    }
    
    public Vector getHeaders() {
        return this._$15294;
    }
    
    public String getCode() {
        return this._$15294.elementAt(0).substring(9, 12);
    }
    
    public void setBody(final StringBuffer $15293) {
        this._$15293 = $15293;
    }
    
    public StringBuffer getBody() {
        return this._$15293;
    }
    
    private void _$619(final int n, final String s, final String s2) {
        Debug.log(n, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(":").append(s2))));
    }
}
