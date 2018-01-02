// 
// Decompiled by Procyon v0.5.30
// 

package anon.proxy;

import anon.infoservice.HttpResponseStructure;

public class HTTPHeaderParseException extends ProxyCallbackNotProcessableException
{
    private int errorCode;
    private String statusMessage;
    private String errorDescription;
    
    public HTTPHeaderParseException(final int errorCode, final int n) {
        this.errorCode = 0;
        this.statusMessage = "";
        this.errorDescription = "";
        this.errorCode = errorCode;
        if (n == 0) {
            switch (errorCode) {
                default: {
                    this.statusMessage = "400 Bad Request";
                    break;
                }
            }
        }
        else {
            switch (errorCode) {
                default: {
                    this.statusMessage = "500 Internal Server Error";
                    break;
                }
            }
        }
    }
    
    public HTTPHeaderParseException(final int n, final int n2, final String errorDescription) {
        this(n, n2);
        this.errorDescription = errorDescription;
    }
    
    public String getMessage() {
        return "<html><head><title>" + this.statusMessage + "</title></head>" + "<body><h1>" + this.statusMessage + "</h1>" + (this.errorDescription.equals("") ? "" : ("<p>" + this.errorDescription + "</p>")) + "</html>";
    }
    
    public byte[] getErrorResponse() {
        return new HttpResponseStructure(this.errorCode, this.getMessage()).getResponseData();
    }
}
