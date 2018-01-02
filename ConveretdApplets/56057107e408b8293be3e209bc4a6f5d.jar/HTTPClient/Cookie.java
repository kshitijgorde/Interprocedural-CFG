// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.net.ProtocolException;
import java.util.Date;
import java.io.Serializable;

public class Cookie implements Serializable
{
    protected String name;
    protected String value;
    protected Date expires;
    protected String domain;
    protected String path;
    protected boolean secure;
    
    public Cookie(final String name, final String value, final String domain, final String path, final Date expires, final boolean secure) {
        if (name == null) {
            throw new NullPointerException("missing name");
        }
        if (value == null) {
            throw new NullPointerException("missing value");
        }
        if (domain == null) {
            throw new NullPointerException("missing domain");
        }
        if (path == null) {
            throw new NullPointerException("missing path");
        }
        this.name = name;
        this.value = value;
        this.domain = domain;
        this.path = path;
        this.expires = expires;
        this.secure = secure;
        if (this.domain.indexOf(46) == -1) {
            this.domain += ".local";
        }
    }
    
    protected Cookie(final RoRequest roRequest) {
        this.name = null;
        this.value = null;
        this.expires = null;
        this.domain = roRequest.getConnection().getHost();
        if (this.domain.indexOf(46) == -1) {
            this.domain += ".local";
        }
        this.path = Util.getPath(roRequest.getRequestURI());
        final String protocol = roRequest.getConnection().getProtocol();
        if (protocol.equals("https") || protocol.equals("shttp")) {
            this.secure = true;
        }
        else {
            this.secure = false;
        }
    }
    
    protected static Cookie[] parse(final String s, final RoRequest roRequest) throws ProtocolException {
        int n = 0;
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        Cookie[] resizeArray = new Cookie[0];
        while (true) {
            n = Util.skipSpace(charArray, n);
            if (n >= length) {
                return resizeArray;
            }
            if (charArray[n] == ',') {
                ++n;
            }
            else {
                final Cookie cookie = new Cookie(roRequest);
                final int n2 = n;
                boolean b = true;
                while (n < length && charArray[n] != ',') {
                    if (charArray[n] == ';') {
                        n = Util.skipSpace(charArray, n + 1);
                    }
                    else if (s.regionMatches(true, n, "secure", 0, 6)) {
                        cookie.secure = true;
                        n += 6;
                        n = Util.skipSpace(charArray, n);
                        if (n < length && charArray[n] == ';') {
                            n = Util.skipSpace(charArray, n + 1);
                        }
                        else {
                            if (n < length && charArray[n] != ',') {
                                throw new ProtocolException("Bad Set-Cookie header: " + s + "\nExpected " + "';' or ',' at position " + n);
                            }
                            continue;
                        }
                    }
                    else {
                        final int index = s.indexOf(61, n);
                        if (index == -1) {
                            throw new ProtocolException("Bad Set-Cookie header: " + s + "\nNo '=' found " + "for token starting at " + "position " + n);
                        }
                        final String trim = s.substring(n, index).trim();
                        int skipSpace = Util.skipSpace(charArray, index + 1);
                        if (trim.equalsIgnoreCase("expires")) {
                            final int index2 = s.indexOf(44, skipSpace);
                            if (index2 != -1) {
                                skipSpace = index2 + 1;
                            }
                        }
                        final int index3 = s.indexOf(44, skipSpace);
                        final int index4 = s.indexOf(59, skipSpace);
                        int min;
                        if (index3 == -1 && index4 == -1) {
                            min = length;
                        }
                        else if (index3 == -1) {
                            min = index4;
                        }
                        else if (index4 == -1) {
                            min = index3;
                        }
                        else {
                            min = Math.min(index3, index4);
                        }
                        final String trim2 = s.substring(skipSpace, min).trim();
                        if (trim.equalsIgnoreCase("expires")) {
                            try {
                                cookie.expires = Util.parseDate(trim2);
                            }
                            catch (IllegalArgumentException ex) {}
                        }
                        else if (trim.equalsIgnoreCase("domain")) {
                            String domain = trim2.toLowerCase();
                            if (domain.charAt(0) != '.' && !domain.equals(cookie.domain)) {
                                domain = '.' + domain;
                            }
                            if (!cookie.domain.endsWith(domain)) {
                                b = false;
                            }
                            if (!domain.equals(".local") && domain.indexOf(46, 1) == -1) {
                                b = false;
                            }
                            String substring = null;
                            if (domain.length() > 3) {
                                substring = domain.substring(domain.length() - 4);
                            }
                            if ((substring == null || (!substring.equalsIgnoreCase(".com") && !substring.equalsIgnoreCase(".edu") && !substring.equalsIgnoreCase(".net") && !substring.equalsIgnoreCase(".org") && !substring.equalsIgnoreCase(".gov") && !substring.equalsIgnoreCase(".mil") && !substring.equalsIgnoreCase(".int"))) && cookie.domain.substring(0, cookie.domain.length() - domain.length()).indexOf(46) != -1) {
                                b = false;
                            }
                            cookie.domain = domain;
                        }
                        else if (trim.equalsIgnoreCase("path")) {
                            cookie.path = trim2;
                        }
                        else {
                            cookie.name = trim;
                            cookie.value = trim2;
                        }
                        n = min;
                        if (n >= length || charArray[n] != ';') {
                            continue;
                        }
                        n = Util.skipSpace(charArray, n + 1);
                    }
                }
                if (cookie.name == null || cookie.value == null) {
                    throw new ProtocolException("Bad Set-Cookie header: " + s + "\nNo Name=Value found" + " for cookie starting at " + "posibition " + n2);
                }
                if (!b) {
                    continue;
                }
                resizeArray = Util.resizeArray(resizeArray, resizeArray.length + 1);
                resizeArray[resizeArray.length - 1] = cookie;
            }
        }
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public Date expires() {
        return this.expires;
    }
    
    public boolean discard() {
        return this.expires == null;
    }
    
    public String getDomain() {
        return this.domain;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public boolean isSecure() {
        return this.secure;
    }
    
    public boolean hasExpired() {
        return this.expires != null && this.expires().before(new Date());
    }
    
    protected boolean sendWith(final RoRequest roRequest) {
        final HTTPConnection connection = roRequest.getConnection();
        String s = connection.getHost();
        if (s.indexOf(46) == -1) {
            s += ".local";
        }
        return ((this.domain.charAt(0) == '.' && s.endsWith(this.domain)) || (this.domain.charAt(0) != '.' && s.equals(this.domain))) && Util.getPath(roRequest.getRequestURI()).startsWith(this.path) && (!this.secure || connection.getProtocol().equals("https") || connection.getProtocol().equals("shttp"));
    }
    
    public int hashCode() {
        return this.name.hashCode() + this.path.hashCode() + this.domain.hashCode();
    }
    
    public boolean equals(final Object o) {
        if (o != null && o instanceof Cookie) {
            final Cookie cookie = (Cookie)o;
            return this.name.equals(cookie.name) && this.path.equals(cookie.path) && this.domain.equals(cookie.domain);
        }
        return false;
    }
    
    protected String toExternalForm() {
        return this.name + "=" + this.value;
    }
    
    public String toString() {
        String s = this.name + "=" + this.value;
        if (this.expires != null) {
            s = s + "; expires=" + this.expires;
        }
        if (this.path != null) {
            s = s + "; path=" + this.path;
        }
        if (this.domain != null) {
            s = s + "; domain=" + this.domain;
        }
        if (this.secure) {
            s += "; secure";
        }
        return s;
    }
}
