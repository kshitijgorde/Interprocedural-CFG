// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.io.IOException;
import java.io.InputStream;

public interface HttpMethod
{
    void setDoAuthentication(final boolean p0);
    
    boolean getDoAuthentication();
    
    void setFollowRedirects(final boolean p0);
    
    boolean getFollowRedirects();
    
    HostConfiguration getHostConfiguration();
    
    String getName();
    
    void setPath(final String p0);
    
    String getPath();
    
    void setQueryString(final String p0);
    
    void setQueryString(final NameValuePair[] p0);
    
    String getQueryString();
    
    void setRequestHeader(final String p0, final String p1);
    
    void setRequestHeader(final Header p0);
    
    Header getRequestHeader(final String p0);
    
    Header[] getRequestHeaders();
    
    Header[] getRequestHeaders(final String p0);
    
    byte[] getResponseBody();
    
    InputStream getResponseBodyAsStream() throws IOException;
    
    String getResponseBodyAsString();
    
    Header getResponseFooter(final String p0);
    
    Header[] getResponseFooters();
    
    Header getResponseHeader(final String p0);
    
    Header[] getResponseHeaders();
    
    Header[] getResponseHeaders(final String p0);
    
    int getStatusCode();
    
    StatusLine getStatusLine();
    
    String getStatusText();
    
    void setStrictMode(final boolean p0);
    
    boolean isStrictMode();
    
    void setURI(final URI p0) throws URIException;
    
    URI getURI() throws URIException;
    
    void addRequestHeader(final String p0, final String p1);
    
    void addRequestHeader(final Header p0);
    
    void addResponseFooter(final Header p0);
    
    int execute(final HttpState p0, final HttpConnection p1) throws HttpException, IOException;
    
    boolean hasBeenUsed();
    
    void recycle();
    
    void releaseConnection();
    
    void removeRequestHeader(final String p0);
    
    boolean validate();
}
