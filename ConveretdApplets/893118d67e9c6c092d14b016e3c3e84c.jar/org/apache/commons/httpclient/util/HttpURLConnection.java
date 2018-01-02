// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.util;

import java.io.OutputStream;
import java.security.Permission;
import java.net.ProtocolException;
import org.apache.commons.httpclient.Header;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.commons.httpclient.HttpMethod;

public class HttpURLConnection extends java.net.HttpURLConnection
{
    private HttpMethod method;
    private URL url;
    
    public HttpURLConnection(final HttpMethod method, final URL url) {
        super(url);
        this.method = method;
        this.url = url;
    }
    
    protected HttpURLConnection(final URL url) {
        super(url);
        throw new RuntimeException("An HTTP URL connection can only be constructed from a HttpMethod class");
    }
    
    public InputStream getInputStream() throws IOException {
        LOG.trace("enter HttpURLConnection.getInputStream()");
        return this.method.getResponseBodyAsStream();
    }
    
    public InputStream getErrorStream() {
        LOG.trace("enter HttpURLConnection.getErrorStream()");
        throw new RuntimeException("Not implemented yet");
    }
    
    public void disconnect() {
        LOG.trace("enter HttpURLConnection.disconnect()");
        throw new RuntimeException("Not implemented yet");
    }
    
    public void connect() throws IOException {
        LOG.trace("enter HttpURLConnection.connect()");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }
    
    public boolean usingProxy() {
        LOG.trace("enter HttpURLConnection.usingProxy()");
        throw new RuntimeException("Not implemented yet");
    }
    
    public String getRequestMethod() {
        LOG.trace("enter HttpURLConnection.getRequestMethod()");
        return this.method.getName();
    }
    
    public int getResponseCode() throws IOException {
        LOG.trace("enter HttpURLConnection.getResponseCode()");
        return this.method.getStatusCode();
    }
    
    public String getResponseMessage() throws IOException {
        LOG.trace("enter HttpURLConnection.getResponseMessage()");
        return this.method.getStatusText();
    }
    
    public String getHeaderField(final String name) {
        LOG.trace("enter HttpURLConnection.getHeaderField(String)");
        final Header[] headers = this.method.getResponseHeaders();
        for (int i = headers.length - 1; i >= 0; --i) {
            if (headers[i].getName().equalsIgnoreCase(name)) {
                return headers[i].getValue();
            }
        }
        return null;
    }
    
    public String getHeaderFieldKey(final int keyPosition) {
        LOG.trace("enter HttpURLConnection.getHeaderFieldKey(int)");
        if (keyPosition == 0) {
            return null;
        }
        final Header[] headers = this.method.getResponseHeaders();
        if (keyPosition < 0 || keyPosition > headers.length) {
            return null;
        }
        return headers[keyPosition - 1].getName();
    }
    
    public String getHeaderField(final int position) {
        LOG.trace("enter HttpURLConnection.getHeaderField(int)");
        if (position == 0) {
            return this.method.getStatusLine().toString();
        }
        final Header[] headers = this.method.getResponseHeaders();
        if (position < 0 || position > headers.length) {
            return null;
        }
        return headers[position - 1].getValue();
    }
    
    public URL getURL() {
        LOG.trace("enter HttpURLConnection.getURL()");
        return this.url;
    }
    
    public void setInstanceFollowRedirects(final boolean isFollowingRedirects) {
        LOG.trace("enter HttpURLConnection.setInstanceFollowRedirects(boolean)");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }
    
    public boolean getInstanceFollowRedirects() {
        LOG.trace("enter HttpURLConnection.getInstanceFollowRedirects()");
        throw new RuntimeException("Not implemented yet");
    }
    
    public void setRequestMethod(final String method) throws ProtocolException {
        LOG.trace("enter HttpURLConnection.setRequestMethod(String)");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }
    
    public Permission getPermission() throws IOException {
        LOG.trace("enter HttpURLConnection.getPermission()");
        throw new RuntimeException("Not implemented yet");
    }
    
    public Object getContent() throws IOException {
        LOG.trace("enter HttpURLConnection.getContent()");
        throw new RuntimeException("Not implemented yet");
    }
    
    public Object getContent(final Class[] classes) throws IOException {
        LOG.trace("enter HttpURLConnection.getContent(Class[])");
        throw new RuntimeException("Not implemented yet");
    }
    
    public OutputStream getOutputStream() throws IOException {
        LOG.trace("enter HttpURLConnection.getOutputStream()");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }
    
    public void setDoInput(final boolean isInput) {
        LOG.trace("enter HttpURLConnection.setDoInput()");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }
    
    public boolean getDoInput() {
        LOG.trace("enter HttpURLConnection.getDoInput()");
        throw new RuntimeException("Not implemented yet");
    }
    
    public void setDoOutput(final boolean isOutput) {
        LOG.trace("enter HttpURLConnection.setDoOutput()");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }
    
    public boolean getDoOutput() {
        LOG.trace("enter HttpURLConnection.getDoOutput()");
        throw new RuntimeException("Not implemented yet");
    }
    
    public void setAllowUserInteraction(final boolean isAllowInteraction) {
        LOG.trace("enter HttpURLConnection.setAllowUserInteraction(boolean)");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }
    
    public boolean getAllowUserInteraction() {
        LOG.trace("enter HttpURLConnection.getAllowUserInteraction()");
        throw new RuntimeException("Not implemented yet");
    }
    
    public void setUseCaches(final boolean isUsingCaches) {
        LOG.trace("enter HttpURLConnection.setUseCaches(boolean)");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }
    
    public boolean getUseCaches() {
        LOG.trace("enter HttpURLConnection.getUseCaches()");
        throw new RuntimeException("Not implemented yet");
    }
    
    public void setIfModifiedSince(final long modificationDate) {
        LOG.trace("enter HttpURLConnection.setIfModifiedSince(long)");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }
    
    public long getIfModifiedSince() {
        LOG.trace("enter HttpURLConnection.getIfmodifiedSince()");
        throw new RuntimeException("Not implemented yet");
    }
    
    public boolean getDefaultUseCaches() {
        LOG.trace("enter HttpURLConnection.getDefaultUseCaches()");
        throw new RuntimeException("Not implemented yet");
    }
    
    public void setDefaultUseCaches(final boolean isUsingCaches) {
        LOG.trace("enter HttpURLConnection.setDefaultUseCaches(boolean)");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }
    
    public void setRequestProperty(final String key, final String value) {
        LOG.trace("enter HttpURLConnection.setRequestProperty()");
        throw new RuntimeException("This class can only be used with alreadyretrieved data");
    }
    
    public String getRequestProperty(final String key) {
        LOG.trace("enter HttpURLConnection.getRequestProperty()");
        throw new RuntimeException("Not implemented yet");
    }
}
