// 
// Decompiled by Procyon v0.5.30
// 

package com.zylom.net.client;

import java.util.Hashtable;

public class Location
{
    private static final String PACKAGE_NAME = "com.zylom.net.client";
    private Protocol protocol;
    private String host;
    private int port;
    private String file;
    private Hashtable parameters;
    
    private Location() {
        this.port = 80;
        this.parameters = new Hashtable();
    }
    
    public Location(final String s, final Hashtable parameters) throws MalformedLocationException {
        this(s);
        this.parameters = parameters;
    }
    
    public Location(String file) throws MalformedLocationException {
        this.port = 80;
        this.parameters = new Hashtable();
        try {
            file = file.trim();
            if (!file.endsWith("/")) {
                file = String.valueOf(String.valueOf(file)).concat("/");
            }
            final int index = file.indexOf("://");
            if (index <= 0) {
                throw new MalformedLocationException();
            }
            if (file.substring(0, index).toUpperCase().equals("HTTP")) {
                this.protocol = new HTTPProtocol();
            }
            else if (file.substring(0, index).toUpperCase().equals("ZTP")) {
                this.protocol = new ZTPProtocol();
            }
            else {
                this.protocol = (Protocol)Class.forName(String.valueOf(String.valueOf(new StringBuffer("com.zylom.net.client.").append(file.substring(0, index).toUpperCase()).append("Protocol")))).newInstance();
            }
            file = file.substring(index + 3);
            final int index2 = file.indexOf("/");
            if (index2 <= 0) {
                throw new MalformedLocationException();
            }
            this.host = file.substring(0, index2).toLowerCase();
            file = file.substring(index2 + 1);
            final int index3 = this.host.indexOf(":");
            if (index3 == 0) {
                throw new MalformedLocationException();
            }
            if (index3 > 0) {
                this.port = Integer.parseInt(this.host.substring(index3 + 1));
                this.host = this.host.substring(0, index3);
            }
            this.file = file;
            if (this.file.indexOf("?") >= 0) {
                throw new MalformedLocationException();
            }
            if (this.file.endsWith("/")) {
                this.file = this.file.substring(0, this.file.length() - 1);
            }
        }
        catch (Exception ex) {
            throw new MalformedLocationException();
        }
    }
    
    public Protocol getProtocol() {
        return this.protocol;
    }
    
    public String getHost() {
        return this.host;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public String getFile() {
        return this.file;
    }
    
    public Hashtable getParameters() {
        return this.parameters;
    }
}
