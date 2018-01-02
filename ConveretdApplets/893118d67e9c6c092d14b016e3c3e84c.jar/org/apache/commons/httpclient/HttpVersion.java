// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

public class HttpVersion
{
    public static final HttpVersion HTTP_0_9;
    public static final HttpVersion HTTP_1_0;
    public static final HttpVersion HTTP_1_1;
    private int major;
    private int minor;
    
    static {
        HTTP_0_9 = new HttpVersion(0, 9);
        HTTP_1_0 = new HttpVersion(1, 0);
        HTTP_1_1 = new HttpVersion(1, 1);
    }
    
    public HttpVersion(final int major, final int minor) {
        this.major = 0;
        this.minor = 0;
        if (major < 0) {
            throw new IllegalArgumentException("HTTP major version number may not be negative");
        }
        this.major = major;
        if (minor < 0) {
            throw new IllegalArgumentException("HTTP minor version number may not be negative");
        }
        this.minor = minor;
    }
    
    public int getMajor() {
        return this.major;
    }
    
    public int getMinor() {
        return this.minor;
    }
    
    public boolean equals(final Object obj) {
        return this == obj || (obj instanceof HttpVersion && this.equals((HttpVersion)obj));
    }
    
    public boolean equals(final HttpVersion version) {
        if (version == null) {
            throw new IllegalArgumentException("Version parameter may not be null");
        }
        return this.getMajor() == version.getMajor() && this.getMinor() == version.getMinor();
    }
    
    public boolean greaterEquals(final HttpVersion version) {
        if (version == null) {
            throw new IllegalArgumentException("Version parameter may not be null");
        }
        int delta = this.getMajor() - version.getMajor();
        if (delta == 0) {
            delta = this.getMinor() - version.getMinor();
        }
        return delta >= 0;
    }
    
    public int hashCode() {
        return this.major * 100000 + this.minor;
    }
    
    public boolean lessEquals(final HttpVersion version) {
        int delta = this.getMajor() - version.getMajor();
        if (delta == 0) {
            delta = this.getMinor() - version.getMinor();
        }
        return delta <= 0;
    }
    
    public static HttpVersion parse(final String s) throws ProtocolException {
        if (s == null) {
            throw new IllegalArgumentException("String may not be null");
        }
        if (!s.startsWith("HTTP/")) {
            throw new ProtocolException("Invalid HTTP version string: " + s);
        }
        int i1 = "HTTP/".length();
        int i2 = s.indexOf(".", i1);
        if (i2 == -1) {
            throw new ProtocolException("Invalid HTTP version number: " + s);
        }
        int major;
        try {
            major = Integer.parseInt(s.substring(i1, i2));
        }
        catch (NumberFormatException e) {
            throw new ProtocolException("Invalid HTTP major version number: " + s);
        }
        i1 = i2 + 1;
        i2 = s.length();
        int minor;
        try {
            minor = Integer.parseInt(s.substring(i1, i2));
        }
        catch (NumberFormatException e) {
            throw new ProtocolException("Invalid HTTP minor version number: " + s);
        }
        return new HttpVersion(major, minor);
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("HTTP/");
        buffer.append(this.major);
        buffer.append('.');
        buffer.append(this.minor);
        return buffer.toString();
    }
}
