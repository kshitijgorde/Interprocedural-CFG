// 
// Decompiled by Procyon v0.5.30
// 

package com.pluraprocessing.common.domain;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class HttpResponse
{
    private HashMap<String, String> responseHeaderTree;
    private byte[] responseBody;
    private int responseByteArraySize;
    private String statusCodeLine;
    
    public HttpResponse() {
        this.responseHeaderTree = null;
    }
    
    public String getStatusCodeLine() {
        return this.statusCodeLine;
    }
    
    public void setStatusCodeLine(final String statusCodeLine) {
        this.statusCodeLine = statusCodeLine;
    }
    
    public HashMap<String, String> getResponseHeaderTree() {
        return this.responseHeaderTree;
    }
    
    public void setResponseHeaderTree(final HashMap<String, String> responseHeaderTree) {
        this.responseHeaderTree = responseHeaderTree;
    }
    
    public byte[] getResponseBody() {
        return this.responseBody;
    }
    
    public String getResponseBodyAsTrimmedUTF8String() throws UnsupportedEncodingException {
        return new String(this.responseBody, 0, this.responseBody.length, "UTF-8").trim();
    }
    
    public void setResponseBody(final byte[] responseBody) {
        this.responseBody = responseBody;
    }
    
    public int getResponseByteArraySize() {
        return this.responseByteArraySize;
    }
    
    public void setResponseByteArraySize(final int responseByteArraySize) {
        this.responseByteArraySize = responseByteArraySize;
    }
    
    public String getHeader(final String headerKey) {
        return (this.responseHeaderTree == null) ? null : this.responseHeaderTree.get(headerKey);
    }
}
