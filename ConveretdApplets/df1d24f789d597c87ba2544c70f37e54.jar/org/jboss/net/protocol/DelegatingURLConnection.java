// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.net.protocol;

import java.io.OutputStream;
import java.io.InputStream;
import java.security.Permission;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DelegatingURLConnection extends URLConnection
{
    protected URL delegateUrl;
    protected URLConnection delegateConnection;
    
    public DelegatingURLConnection(final URL url) throws MalformedURLException, IOException {
        super(url);
        this.delegateUrl = this.makeDelegateUrl(url);
        this.delegateConnection = this.makeDelegateUrlConnection(this.delegateUrl);
    }
    
    protected URL makeDelegateUrl(final URL url) throws MalformedURLException, IOException {
        return url;
    }
    
    protected URLConnection makeDelegateUrlConnection(final URL url) throws IOException {
        return url.openConnection();
    }
    
    public void connect() throws IOException {
        this.delegateConnection.connect();
    }
    
    public URL getURL() {
        return this.delegateConnection.getURL();
    }
    
    public int getContentLength() {
        return this.delegateConnection.getContentLength();
    }
    
    public String getContentType() {
        return this.delegateConnection.getContentType();
    }
    
    public String getContentEncoding() {
        return this.delegateConnection.getContentEncoding();
    }
    
    public long getExpiration() {
        return this.delegateConnection.getExpiration();
    }
    
    public long getDate() {
        return this.delegateConnection.getDate();
    }
    
    public long getLastModified() {
        return this.delegateConnection.getLastModified();
    }
    
    public String getHeaderField(final String name) {
        return this.delegateConnection.getHeaderField(name);
    }
    
    public int getHeaderFieldInt(final String name, final int _default) {
        return this.delegateConnection.getHeaderFieldInt(name, _default);
    }
    
    public long getHeaderFieldDate(final String name, final long _default) {
        return this.delegateConnection.getHeaderFieldDate(name, _default);
    }
    
    public String getHeaderFieldKey(final int n) {
        return this.delegateConnection.getHeaderFieldKey(n);
    }
    
    public String getHeaderField(final int n) {
        return this.delegateConnection.getHeaderField(n);
    }
    
    public Object getContent() throws IOException {
        return this.delegateConnection.getContent();
    }
    
    public Object getContent(final Class[] classes) throws IOException {
        return this.delegateConnection.getContent(classes);
    }
    
    public Permission getPermission() throws IOException {
        return this.delegateConnection.getPermission();
    }
    
    public InputStream getInputStream() throws IOException {
        return this.delegateConnection.getInputStream();
    }
    
    public OutputStream getOutputStream() throws IOException {
        return this.delegateConnection.getOutputStream();
    }
    
    public String toString() {
        return super.toString() + "{ " + this.delegateConnection + " }";
    }
    
    public void setDoInput(final boolean doinput) {
        this.delegateConnection.setDoInput(doinput);
    }
    
    public boolean getDoInput() {
        return this.delegateConnection.getDoInput();
    }
    
    public void setDoOutput(final boolean dooutput) {
        this.delegateConnection.setDoOutput(dooutput);
    }
    
    public boolean getDoOutput() {
        return this.delegateConnection.getDoOutput();
    }
    
    public void setAllowUserInteraction(final boolean allowuserinteraction) {
        this.delegateConnection.setAllowUserInteraction(allowuserinteraction);
    }
    
    public boolean getAllowUserInteraction() {
        return this.delegateConnection.getAllowUserInteraction();
    }
    
    public void setUseCaches(final boolean usecaches) {
        this.delegateConnection.setUseCaches(usecaches);
    }
    
    public boolean getUseCaches() {
        return this.delegateConnection.getUseCaches();
    }
    
    public void setIfModifiedSince(final long ifmodifiedsince) {
        this.delegateConnection.setIfModifiedSince(ifmodifiedsince);
    }
    
    public long getIfModifiedSince() {
        return this.delegateConnection.getIfModifiedSince();
    }
    
    public boolean getDefaultUseCaches() {
        return this.delegateConnection.getDefaultUseCaches();
    }
    
    public void setDefaultUseCaches(final boolean defaultusecaches) {
        this.delegateConnection.setDefaultUseCaches(defaultusecaches);
    }
    
    public void setRequestProperty(final String key, final String value) {
        this.delegateConnection.setRequestProperty(key, value);
    }
    
    public String getRequestProperty(final String key) {
        return this.delegateConnection.getRequestProperty(key);
    }
}
