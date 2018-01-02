// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.remoteServer;

import org.xmodel.log.Log;

public class Request
{
    protected String toid;
    private boolean wait;
    private String method;
    private String target;
    protected Object[] params;
    public static final Log log;
    
    static {
        log = Log.getLog(Request.class);
    }
    
    public Request(final String toid, final boolean wait, final String target, final String method, final Object[] params) {
        this.toid = toid;
        this.wait = wait;
        this.target = target;
        this.method = method;
        this.params = params;
    }
    
    public String getTo() {
        return this.toid;
    }
    
    public boolean shouldWait() {
        return this.wait;
    }
    
    public String getMethod() {
        return this.method;
    }
    
    public Object[] getParams() {
        return this.params;
    }
    
    public String getTarget() {
        return this.target;
    }
    
    public Correlation getCorrelation() {
        return null;
    }
    
    public void handleException(final Exception e) {
        Request.log.error(this, e);
    }
    
    public void fail(final String reason) throws Exception {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append(this.target);
        buf.append(".");
        buf.append(this.method);
        buf.append("(");
        buf.append(this.params);
        buf.append(")");
        return buf.toString();
    }
}
