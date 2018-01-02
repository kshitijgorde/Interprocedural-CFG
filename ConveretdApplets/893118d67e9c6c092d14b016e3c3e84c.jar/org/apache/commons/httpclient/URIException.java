// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

public class URIException extends HttpException
{
    public static final int UNKNOWN = 0;
    public static final int PARSING = 1;
    public static final int UNSUPPORTED_ENCODING = 2;
    public static final int ESCAPING = 3;
    public static final int PUNYCODE = 4;
    protected String reason;
    protected int reasonCode;
    
    public URIException() {
    }
    
    public URIException(final int reasonCode) {
        this.setReasonCode(reasonCode);
    }
    
    public URIException(final int reasonCode, final String reason) {
        super(reason);
        this.reason = reason;
        this.setReasonCode(reasonCode);
    }
    
    public URIException(final String reason) {
        super(reason);
        this.reason = reason;
        this.setReasonCode(0);
    }
    
    public void setReason(final String reason) {
        this.reason = reason;
    }
    
    public String getReason() {
        return this.reason;
    }
    
    public void setReasonCode(final int reasonCode) {
        this.reasonCode = reasonCode;
    }
    
    public int getReasonCode() {
        return this.reasonCode;
    }
}
