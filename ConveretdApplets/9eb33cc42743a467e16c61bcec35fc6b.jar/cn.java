import java.util.Enumeration;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class cn
{
    public String n0;
    public Hashtable n_;
    
    private final String nz(final String s, final String s2) throws IOException {
        final char char1 = s.charAt(0);
        String lowerCase;
        String s3;
        if (char1 == ' ' || char1 == '\t') {
            lowerCase = s2;
            s3 = String.valueOf(this.nw(s2)) + " " + s.trim();
        }
        else {
            final int index = s.indexOf(58);
            if (index == -1) {
                throw new IOException("HttpHeader, corrupt header-field: '" + s + "'");
            }
            lowerCase = s.substring(0, index).toLowerCase();
            s3 = s.substring(index + 1).trim();
        }
        this.nv(lowerCase, s3);
        return lowerCase;
    }
    
    public cn(final InputStream inputStream) throws IOException {
        this();
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        String nz = null;
        while (true) {
            final int read = inputStream.read();
            if (read == -1) {
                throw new IOException("HttpHeader, corrupt header, input stream closed");
            }
            if (read == 10) {
                continue;
            }
            if (read != 13) {
                sb.append((char)read);
            }
            else {
                if (sb.length() == 0) {
                    inputStream.read();
                    return;
                }
                final String n2 = new String(sb);
                if (n != 0) {
                    nz = this.nz(n2, nz);
                }
                else {
                    this.n0 = n2;
                    n = 1;
                }
                sb.setLength(0);
            }
        }
    }
    
    public cn() {
        this.n_ = new Hashtable();
    }
    
    public final String ny() {
        return this.n0;
    }
    
    public final void nx(final String n0) {
        this.n0 = n0;
    }
    
    public final String nw(final String s) {
        return this.n_.get(s.toLowerCase());
    }
    
    public final void nv(final String s, final String s2) {
        this.n_.put(s.toLowerCase(), s2);
    }
    
    public final void hz(final OutputStream outputStream) throws IOException {
        outputStream.write(this.toString().getBytes());
        outputStream.flush();
    }
    
    public final int nu() {
        int intValue = -1;
        final int index;
        if ((index = this.n0.indexOf(" ")) > 0) {
            try {
                intValue = new Integer(this.n0.substring(index + 1, index + 4));
            }
            catch (NumberFormatException ex) {
                intValue = -1;
            }
        }
        return intValue;
    }
    
    public final void nt(final String s, final String s2) {
        this.nv("Proxy-Authorization", "Basic " + new String(c.j((String.valueOf(s) + ":" + s2).getBytes())));
    }
    
    public final String ns() {
        final String nw = this.nw("Proxy-Authenticate");
        String substring = null;
        if (nw != null) {
            substring = nw.substring(0, nw.indexOf(32));
        }
        return substring;
    }
    
    public final String nr() {
        final String nw = this.nw("Proxy-Authenticate");
        String s = null;
        if (nw != null) {
            for (int i = nw.indexOf(61); i >= 0; i = nw.indexOf(61, i + 1)) {
                s = nw.substring(nw.lastIndexOf(32, i) + 1, i);
                if (s.equalsIgnoreCase("realm")) {
                    final int n = i + 2;
                    s = nw.substring(n, nw.indexOf(34, n));
                    break;
                }
            }
        }
        return s;
    }
    
    public final String toString() {
        String s = String.valueOf(this.n0) + "\r\n";
        final Enumeration<String> keys = this.n_.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            s = String.valueOf(s) + s2 + ": " + this.n_.get(s2) + "\r\n";
        }
        return String.valueOf(s) + "\r\n";
    }
}
