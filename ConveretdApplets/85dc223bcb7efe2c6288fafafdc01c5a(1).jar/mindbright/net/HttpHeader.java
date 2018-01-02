// 
// Decompiled by Procyon v0.5.30
// 

package mindbright.net;

import java.util.Enumeration;
import mindbright.util.Base64;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Hashtable;

public class HttpHeader
{
    String startLine;
    Hashtable headerFields;
    boolean isResponse;
    
    private final String processLine(final String line, final String lastHeaderName) throws IOException {
        final char c = line.charAt(0);
        String name;
        String value;
        if (c == ' ' || c == '\t') {
            name = lastHeaderName;
            value = this.getHeaderField(lastHeaderName) + " " + line.trim();
        }
        else {
            final int n = line.indexOf(58);
            if (n == -1) {
                throw new IOException("HttpHeader, corrupt header-field: '" + line + "'");
            }
            name = line.substring(0, n).toLowerCase();
            value = line.substring(n + 1).trim();
        }
        this.setHeaderField(name, value);
        return name;
    }
    
    public HttpHeader(final String fullHeader) throws IOException {
        this();
        String lastHeaderName = null;
        boolean readStart = false;
        int l = 0;
        int r = 0;
        final int n = 0;
        while (true) {
            r = fullHeader.indexOf("\r\n", l);
            if (l == r || r == -1) {
                break;
            }
            final String line = fullHeader.substring(l, r);
            if (readStart) {
                lastHeaderName = this.processLine(line, lastHeaderName);
            }
            else {
                this.startLine = line;
                readStart = true;
            }
            l = r + 2;
        }
    }
    
    public HttpHeader(final InputStream input) throws IOException {
        this();
        final StringBuffer lineBuf = new StringBuffer();
        boolean readStart = false;
        String lastHeaderName = null;
        while (true) {
            int c = input.read();
            if (c == -1) {
                throw new IOException("HttpHeader, corrupt header, input stream closed");
            }
            if (c == 10) {
                continue;
            }
            if (c != 13) {
                lineBuf.append((char)c);
            }
            else {
                if (lineBuf.length() == 0) {
                    c = input.read();
                    return;
                }
                final String line = new String(lineBuf);
                if (readStart) {
                    lastHeaderName = this.processLine(line, lastHeaderName);
                }
                else {
                    this.startLine = line;
                    readStart = true;
                }
                lineBuf.setLength(0);
            }
        }
    }
    
    public HttpHeader() {
        this.headerFields = new Hashtable();
    }
    
    public String getStartLine() {
        return this.startLine;
    }
    
    public void setStartLine(final String startLine) {
        this.startLine = startLine;
    }
    
    public Hashtable getHeaderFields() {
        return this.headerFields;
    }
    
    public String getHeaderField(final String headerName) {
        return this.headerFields.get(headerName.toLowerCase());
    }
    
    public void setHeaderField(final String headerName, final String value) {
        this.headerFields.put(headerName.toLowerCase(), value);
    }
    
    public void writeTo(final OutputStream output) throws IOException {
        final String fullHeader = this.toString();
        output.write(fullHeader.getBytes());
        output.flush();
    }
    
    public int getStatus() {
        int status = -1;
        final int pos;
        if ((pos = this.startLine.indexOf(" ")) > 0) {
            try {
                status = new Integer(this.startLine.substring(pos + 1, pos + 4));
            }
            catch (NumberFormatException e) {
                status = -1;
            }
        }
        return status;
    }
    
    public void setBasicProxyAuth(final String username, final String password) {
        final String authStr = username + ":" + password;
        final byte[] authB64enc = Base64.encode(authStr.getBytes());
        this.setHeaderField("Proxy-Authorization", "Basic " + new String(authB64enc));
    }
    
    public String getProxyAuthMethod() {
        final String challenge = this.getHeaderField("Proxy-Authenticate");
        String method = null;
        if (challenge != null) {
            final int n = challenge.indexOf(32);
            method = challenge.substring(0, n);
        }
        return method;
    }
    
    public String getProxyAuthRealm() {
        final String challenge = this.getHeaderField("Proxy-Authenticate");
        String realm = null;
        if (challenge != null) {
            for (int r = challenge.indexOf(61); r >= 0; r = challenge.indexOf(61, r + 1)) {
                int l = challenge.lastIndexOf(32, r);
                realm = challenge.substring(l + 1, r);
                if (realm.equalsIgnoreCase("realm")) {
                    l = r + 2;
                    r = challenge.indexOf(34, l);
                    realm = challenge.substring(l, r);
                    break;
                }
            }
        }
        return realm;
    }
    
    public String toString() {
        String fullHeader = this.startLine + "\r\n";
        final Enumeration headerNames = this.headerFields.keys();
        while (headerNames.hasMoreElements()) {
            final String fieldName = headerNames.nextElement();
            fullHeader = fullHeader + fieldName + ": " + this.headerFields.get(fieldName) + "\r\n";
        }
        fullHeader += "\r\n";
        return fullHeader;
    }
}
