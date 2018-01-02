// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.remoteServer;

import java.util.List;
import java.util.ArrayList;

public abstract class AsyncRequest extends Request
{
    protected Correlation correlation;
    
    public AsyncRequest(final String toid, final Correlation correlation, final String target, final String method, final Object[] params) {
        super(toid, false, target, method, params);
        this.correlation = correlation;
    }
    
    public abstract Callback getCallback();
    
    @Override
    public Correlation getCorrelation() {
        return this.correlation;
    }
    
    @Override
    public Object[] getParams() {
        final List<Object> objects = new ArrayList<Object>();
        if (this.params != null) {
            for (int i = 0; i < this.params.length; ++i) {
                objects.add(this.params[i]);
            }
        }
        objects.add(this.correlation);
        return objects.toArray();
    }
    
    @Override
    public String toString() {
        final String s = super.toString();
        final StringBuffer buf = new StringBuffer(s);
        buf.append("-correlation:");
        buf.append(this.correlation);
        return buf.toString();
    }
}
