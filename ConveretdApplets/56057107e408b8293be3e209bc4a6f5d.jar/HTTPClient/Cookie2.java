// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.util.Vector;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.net.ProtocolException;
import java.util.Date;
import java.net.URL;

public class Cookie2 extends Cookie
{
    protected int version;
    protected boolean discard;
    protected String comment;
    protected URL comment_url;
    protected int[] port_list;
    protected String port_list_str;
    protected boolean path_set;
    protected boolean port_set;
    protected boolean domain_set;
    
    public Cookie2(final String s, final String s2, final String s3, final int[] port_list, final String s4, final Date date, final boolean discard, final boolean b, final String comment, final URL comment_url) {
        super(s, s2, s3, s4, date, b);
        this.discard = discard;
        this.port_list = port_list;
        this.comment = comment;
        this.comment_url = comment_url;
        this.path_set = true;
        this.domain_set = true;
        if (port_list != null && port_list.length > 0) {
            final StringBuffer sb = new StringBuffer();
            sb.append(port_list[0]);
            for (int i = 1; i < port_list.length; ++i) {
                sb.append(',');
                sb.append(port_list[i]);
            }
            this.port_list_str = sb.toString();
            this.port_set = true;
        }
        this.version = 1;
    }
    
    protected Cookie2(final RoRequest roRequest) {
        super(roRequest);
        final int lastIndex = super.path.lastIndexOf(47);
        if (lastIndex != -1) {
            super.path = super.path.substring(0, lastIndex + 1);
        }
        if (super.domain.indexOf(46) == -1) {
            super.domain += ".local";
        }
        this.version = -1;
        this.discard = false;
        this.comment = null;
        this.comment_url = null;
        this.port_list = null;
        this.port_list_str = null;
        this.path_set = false;
        this.port_set = false;
        this.domain_set = false;
    }
    
    protected static Cookie[] parse(final String s, final RoRequest roRequest) throws ProtocolException {
        Vector header;
        try {
            header = Util.parseHeader(s);
        }
        catch (ParseException ex) {
            throw new ProtocolException(ex.getMessage());
        }
        Cookie[] resizeArray = new Cookie[header.size()];
        int n = 0;
        for (int i = 0; i < resizeArray.length; ++i) {
            final HttpHeaderElement httpHeaderElement = header.elementAt(i);
            if (httpHeaderElement.getValue() == null) {
                throw new ProtocolException("Bad Set-Cookie2 header: " + s + "\nMissing value " + "for cookie '" + httpHeaderElement.getName() + "'");
            }
            final Cookie2 cookie2 = new Cookie2(roRequest);
            cookie2.name = httpHeaderElement.getName();
            cookie2.value = httpHeaderElement.getValue();
            final NVPair[] params = httpHeaderElement.getParams();
            int n2 = 0;
            int n3 = 0;
            for (int j = 0; j < params.length; ++j) {
                final String lowerCase = params[j].getName().toLowerCase();
                if ((lowerCase.equals("version") || lowerCase.equals("max-age") || lowerCase.equals("domain") || lowerCase.equals("path") || lowerCase.equals("comment") || lowerCase.equals("commenturl")) && params[j].getValue() == null) {
                    throw new ProtocolException("Bad Set-Cookie2 header: " + s + "\nMissing value " + "for " + params[j].getName() + " attribute in cookie '" + httpHeaderElement.getName() + "'");
                }
                if (lowerCase.equals("version")) {
                    if (cookie2.version != -1) {
                        continue;
                    }
                    try {
                        cookie2.version = Integer.parseInt(params[j].getValue());
                        continue;
                    }
                    catch (NumberFormatException ex2) {
                        throw new ProtocolException("Bad Set-Cookie2 header: " + s + "\nVersion '" + params[j].getValue() + "' not a number");
                    }
                }
                if (lowerCase.equals("path")) {
                    if (!cookie2.path_set) {
                        cookie2.path = params[j].getValue();
                        cookie2.path_set = true;
                    }
                }
                else if (lowerCase.equals("domain")) {
                    if (!cookie2.domain_set) {
                        final String lowerCase2 = params[j].getValue().toLowerCase();
                        if (lowerCase2.charAt(0) != '.' && !lowerCase2.equals(cookie2.domain)) {
                            cookie2.domain = "." + lowerCase2;
                        }
                        else {
                            cookie2.domain = lowerCase2;
                        }
                        cookie2.domain_set = true;
                    }
                }
                else if (lowerCase.equals("max-age")) {
                    if (cookie2.expires == null) {
                        int int1;
                        try {
                            int1 = Integer.parseInt(params[j].getValue());
                        }
                        catch (NumberFormatException ex3) {
                            throw new ProtocolException("Bad Set-Cookie2 header: " + s + "\nMax-Age '" + params[j].getValue() + "' not a number");
                        }
                        cookie2.expires = new Date(System.currentTimeMillis() + int1 * 1000L);
                    }
                }
                else if (lowerCase.equals("port")) {
                    if (!cookie2.port_set) {
                        if (params[j].getValue() == null) {
                            (cookie2.port_list = new int[1])[0] = roRequest.getConnection().getPort();
                            cookie2.port_set = true;
                        }
                        else {
                            cookie2.port_list_str = params[j].getValue();
                            final StringTokenizer stringTokenizer = new StringTokenizer(params[j].getValue(), ",");
                            cookie2.port_list = new int[stringTokenizer.countTokens()];
                            for (int k = 0; k < cookie2.port_list.length; ++k) {
                                final String trim = stringTokenizer.nextToken().trim();
                                try {
                                    cookie2.port_list[k] = Integer.parseInt(trim);
                                }
                                catch (NumberFormatException ex4) {
                                    throw new ProtocolException("Bad Set-Cookie2 header: " + s + "\nPort '" + trim + "' not a number");
                                }
                            }
                            cookie2.port_set = true;
                        }
                    }
                }
                else if (lowerCase.equals("discard")) {
                    if (n2 == 0) {
                        cookie2.discard = true;
                        n2 = 1;
                    }
                }
                else if (lowerCase.equals("secure")) {
                    if (n3 == 0) {
                        cookie2.secure = true;
                        n3 = 1;
                    }
                }
                else if (lowerCase.equals("comment")) {
                    if (cookie2.comment == null) {
                        cookie2.comment = params[j].getValue();
                    }
                }
                else if (lowerCase.equals("commenturl")) {
                    if (cookie2.comment_url == null) {
                        try {
                            cookie2.comment_url = new URL(params[j].getValue());
                        }
                        catch (MalformedURLException ex5) {
                            throw new ProtocolException("Bad Set-Cookie2 header: " + s + "\nCommentURL '" + params[j].getValue() + "' not a valid URL");
                        }
                    }
                }
            }
            if (cookie2.version == -1) {
                throw new ProtocolException("Bad Set-Cookie2 header: " + s + "\nMissing Version " + "attribute");
            }
            if (cookie2.version == 1) {
                if (cookie2.expires == null) {
                    cookie2.discard = true;
                }
                if (Util.getPath(roRequest.getRequestURI()).startsWith(cookie2.path)) {
                    String s2 = roRequest.getConnection().getHost();
                    if (s2.indexOf(46) == -1) {
                        s2 += ".local";
                    }
                    if (cookie2.domain.equals(".local") || cookie2.domain.indexOf(46, 1) != -1) {
                        if (s2.endsWith(cookie2.domain)) {
                            if (s2.substring(0, s2.length() - cookie2.domain.length()).indexOf(46) == -1) {
                                if (cookie2.port_set) {
                                    int n4;
                                    for (n4 = 0; n4 < cookie2.port_list.length && cookie2.port_list[n4] != roRequest.getConnection().getPort(); ++n4) {}
                                    if (n4 == cookie2.port_list.length) {
                                        continue;
                                    }
                                }
                                resizeArray[n++] = cookie2;
                            }
                        }
                    }
                }
            }
        }
        if (n < resizeArray.length) {
            resizeArray = Util.resizeArray(resizeArray, n);
        }
        return resizeArray;
    }
    
    public int getVersion() {
        return this.version;
    }
    
    public String getComment() {
        return this.comment;
    }
    
    public URL getCommentURL() {
        return this.comment_url;
    }
    
    public int[] getPorts() {
        return this.port_list;
    }
    
    public boolean discard() {
        return this.discard;
    }
    
    protected boolean sendWith(final RoRequest roRequest) {
        final HTTPConnection connection = roRequest.getConnection();
        boolean b = !this.port_set;
        if (this.port_set) {
            for (int i = 0; i < this.port_list.length; ++i) {
                if (this.port_list[i] == connection.getPort()) {
                    b = true;
                    break;
                }
            }
        }
        String s = connection.getHost();
        if (s.indexOf(46) == -1) {
            s += ".local";
        }
        return ((super.domain.charAt(0) == '.' && s.endsWith(super.domain)) || (super.domain.charAt(0) != '.' && s.equals(super.domain))) && b && Util.getPath(roRequest.getRequestURI()).startsWith(super.path) && (!super.secure || connection.getProtocol().equals("https") || connection.getProtocol().equals("shttp"));
    }
    
    protected String toExternalForm() {
        final StringBuffer sb = new StringBuffer();
        if (this.version == 1) {
            sb.append(super.name);
            sb.append("=");
            sb.append(super.value);
            if (this.path_set) {
                sb.append("; ");
                sb.append("$Path=");
                sb.append(super.path);
            }
            if (this.domain_set) {
                sb.append("; ");
                sb.append("$Domain=");
                sb.append(super.domain);
            }
            if (this.port_set) {
                sb.append("; ");
                sb.append("$Port");
                if (this.port_list_str != null) {
                    sb.append("=\"");
                    sb.append(this.port_list_str);
                    sb.append('\"');
                }
            }
            return sb.toString();
        }
        throw new Error("Internal Error: unknown version " + this.version);
    }
    
    public String toString() {
        final String string = super.name + "=" + super.value;
        if (this.version == 1) {
            String s = string + "; Version=" + this.version + "; Path=" + super.path + "; Domain=" + super.domain;
            if (this.port_set) {
                String s2 = s + "; Port=\"" + this.port_list[0];
                for (int i = 1; i < this.port_list.length; ++i) {
                    s2 = s2 + "," + this.port_list[i];
                }
                s = s2 + "\"";
            }
            if (super.expires != null) {
                s = s + "; Max-Age=" + (super.expires.getTime() - new Date().getTime()) / 1000L;
            }
            if (this.discard) {
                s += "; Discard";
            }
            if (super.secure) {
                s += "; Secure";
            }
            if (this.comment != null) {
                s = s + "; Comment=\"" + this.comment + "\"";
            }
            if (this.comment_url != null) {
                s = s + "; CommentURL=\"" + this.comment_url + "\"";
            }
            return s;
        }
        throw new Error("Internal Error: unknown version " + this.version);
    }
}
