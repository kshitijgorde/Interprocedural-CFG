// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.BitSet;

public class URI
{
    protected static BitSet alphanumChar;
    protected static BitSet markChar;
    protected static BitSet reservedChar;
    protected static BitSet unreservedChar;
    protected static BitSet uricChar;
    protected static BitSet pcharChar;
    protected static BitSet userinfoChar;
    protected static BitSet schemeChar;
    protected static BitSet reg_nameChar;
    protected boolean is_generic;
    protected String scheme;
    protected String opaque;
    protected String userinfo;
    protected String host;
    protected int port;
    protected String path;
    protected String query;
    protected String fragment;
    protected URL url;
    private static final char[] hex;
    
    public URI(final String s) throws ParseException {
        this((URI)null, s);
    }
    
    public URI(final URI uri, final String s) throws ParseException {
        this.port = -1;
        this.url = null;
        final char[] charArray = s.toCharArray();
        int i;
        int length;
        for (i = 0, length = charArray.length; i < length; ++i) {
            if (!Character.isWhitespace(charArray[i])) {
                break;
            }
        }
        while (length > 0 && Character.isWhitespace(charArray[length - 1])) {
            --length;
        }
        if (i < length - 3 && charArray[i + 3] == ':' && (charArray[i + 0] == 'u' || charArray[i + 0] == 'U') && (charArray[i + 1] == 'r' || charArray[i + 1] == 'R') && (charArray[i + 2] == 'i' || charArray[i + 2] == 'I' || charArray[i + 2] == 'l' || charArray[i + 2] == 'L')) {
            i += 4;
        }
        int n;
        for (n = i; n < length && charArray[n] != ':' && charArray[n] != '/' && charArray[n] != '?' && charArray[n] != '#'; ++n) {}
        if (n < length && charArray[n] == ':') {
            this.scheme = s.substring(i, n).trim().toLowerCase();
            i = n + 1;
        }
        String s2 = this.scheme;
        if (this.scheme == null) {
            if (uri == null) {
                throw new ParseException("No scheme found");
            }
            s2 = uri.scheme;
        }
        if (this.is_generic = usesGenericSyntax(s2)) {
            if (i < length - 1 && charArray[i] == '/' && charArray[i + 1] == '/') {
                i += 2;
                int n2;
                for (n2 = i; n2 < length && charArray[n2] != '/' && charArray[n2] != '?' && charArray[n2] != '#'; ++n2) {}
                this.parse_authority(s.substring(i, n2), s2);
                i = n2;
            }
            int n3;
            for (n3 = i; n3 < length && charArray[n3] != '?' && charArray[n3] != '#'; ++n3) {}
            this.path = s.substring(i, n3);
            int n4 = n3;
            if (n4 < length && charArray[n4] == '?') {
                int n5;
                for (n5 = ++n4; n5 < length && charArray[n5] != '#'; ++n5) {}
                this.query = unescape(s.substring(n4, n5));
                n4 = n5;
            }
            if (n4 < length && charArray[n4] == '#') {
                this.fragment = unescape(s.substring(n4 + 1, length));
            }
            if (uri != null) {
                if (this.scheme != null) {
                    return;
                }
                this.scheme = uri.scheme;
                if (this.host != null) {
                    return;
                }
                this.userinfo = uri.userinfo;
                this.host = uri.host;
                this.port = uri.port;
                if (this.path.length() == 0 && this.query == null) {
                    this.path = uri.path;
                    this.query = uri.query;
                    return;
                }
                if (this.path.length() == 0 || this.path.charAt(0) != '/') {
                    final int lastIndex = uri.path.lastIndexOf(47);
                    if (lastIndex == -1) {
                        return;
                    }
                    this.path = uri.path.substring(0, lastIndex + 1) + this.path;
                    int length2 = this.path.length();
                    final int index;
                    if ((index = this.path.indexOf("/.")) == -1 || (index != length2 - 2 && this.path.charAt(index + 2) != '/' && (this.path.charAt(index + 2) != '.' || (index != length2 - 3 && this.path.charAt(index + 3) != '/')))) {
                        return;
                    }
                    final char[] array = new char[this.path.length()];
                    this.path.getChars(0, array.length, array, 0);
                    for (int j = 1; j < length2; ++j) {
                        if (array[j] == '.' && array[j - 1] == '/') {
                            int n6;
                            if (j == length2 - 1) {
                                n6 = j;
                                ++j;
                            }
                            else if (array[j + 1] == '/') {
                                n6 = j - 1;
                                ++j;
                            }
                            else {
                                if (array[j + 1] != '.' || (j != length2 - 2 && array[j + 2] != '/')) {
                                    continue;
                                }
                                for (n6 = j - 2; n6 > 0 && array[n6] != '/'; --n6) {}
                                if (array[n6] != '/') {
                                    continue;
                                }
                                if (j == length2 - 2) {
                                    ++n6;
                                }
                                j += 2;
                            }
                            System.arraycopy(array, j, array, n6, length2 - j);
                            length2 -= j - n6;
                            j = n6;
                        }
                    }
                    this.path = new String(array, 0, length2);
                }
            }
            return;
        }
        if (uri != null && this.scheme == null) {
            throw new ParseException("Can't resolve relative URI for scheme " + s2);
        }
        this.opaque = s.substring(i);
    }
    
    private void parse_authority(final String s, final String s2) throws ParseException {
        final char[] charArray = s.toCharArray();
        int n = 0;
        int length;
        int n2;
        for (length = charArray.length, n2 = n; n2 < length && charArray[n2] != '@'; ++n2) {}
        if (n2 < length && charArray[n2] == '@') {
            this.userinfo = unescape(s.substring(n, n2));
            n = n2 + 1;
        }
        int n3;
        for (n3 = n; n3 < length && charArray[n3] != ':'; ++n3) {}
        this.host = s.substring(n, n3);
        final int n4 = n3;
        if (n4 < length - 1 && charArray[n4] == ':') {
            int int1;
            try {
                int1 = Integer.parseInt(s.substring(n4 + 1, length));
                if (int1 < 0) {
                    throw new NumberFormatException();
                }
            }
            catch (NumberFormatException ex) {
                throw new ParseException(s.substring(n4 + 1, length) + " is an invalid port number");
            }
            if (int1 == defaultPort(s2)) {
                this.port = -1;
            }
            else {
                this.port = int1;
            }
        }
    }
    
    public URI(final String s, final String s2, final String s3) throws ParseException {
        this(s, null, s2, -1, s3, null, null);
    }
    
    public URI(final String s, final String s2, final int n, final String s3) throws ParseException {
        this(s, null, s2, n, s3, null, null);
    }
    
    public URI(final String s, final String s2, final String s3, final int port, final String s4, final String s5, final String s6) throws ParseException {
        this.port = -1;
        this.url = null;
        if (s == null) {
            throw new ParseException("missing scheme");
        }
        this.scheme = s.trim();
        if (s2 != null) {
            this.userinfo = unescape(s2.trim());
        }
        if (s3 != null) {
            this.host = s3.trim();
        }
        if (port != defaultPort(s)) {
            this.port = port;
        }
        if (s4 != null) {
            this.path = s4.trim();
        }
        if (s5 != null) {
            this.query = s5.trim();
        }
        if (s6 != null) {
            this.fragment = s6.trim();
        }
        this.is_generic = true;
    }
    
    public URI(final String s, final String opaque) throws ParseException {
        this.port = -1;
        this.url = null;
        if (s == null) {
            throw new ParseException("missing scheme");
        }
        this.scheme = s.trim().toLowerCase();
        this.opaque = opaque;
        this.is_generic = false;
    }
    
    public static boolean usesGenericSyntax(String trim) {
        trim = trim.trim();
        return trim.equalsIgnoreCase("http") || trim.equalsIgnoreCase("https") || trim.equalsIgnoreCase("shttp") || trim.equalsIgnoreCase("coffee") || trim.equalsIgnoreCase("ftp") || trim.equalsIgnoreCase("file") || trim.equalsIgnoreCase("gopher") || trim.equalsIgnoreCase("nntp") || trim.equalsIgnoreCase("telnet") || trim.equalsIgnoreCase("imap") || trim.equalsIgnoreCase("wais") || trim.equalsIgnoreCase("nfs") || trim.equalsIgnoreCase("ldap") || trim.equalsIgnoreCase("prospero");
    }
    
    public static final int defaultPort(final String s) {
        final String trim = s.trim();
        if (trim.equalsIgnoreCase("http") || trim.equalsIgnoreCase("shttp") || trim.equalsIgnoreCase("http-ng") || trim.equalsIgnoreCase("coffee")) {
            return 80;
        }
        if (trim.equalsIgnoreCase("https")) {
            return 443;
        }
        if (trim.equalsIgnoreCase("ftp")) {
            return 21;
        }
        if (trim.equalsIgnoreCase("telnet")) {
            return 23;
        }
        if (trim.equalsIgnoreCase("nntp")) {
            return 119;
        }
        if (trim.equalsIgnoreCase("smtp")) {
            return 25;
        }
        if (trim.equalsIgnoreCase("gopher")) {
            return 70;
        }
        if (trim.equalsIgnoreCase("wais")) {
            return 210;
        }
        if (trim.equalsIgnoreCase("whois")) {
            return 43;
        }
        if (trim.equalsIgnoreCase("imap")) {
            return 143;
        }
        if (trim.equalsIgnoreCase("prospero")) {
            return 1525;
        }
        if (trim.equalsIgnoreCase("ldap")) {
            return 389;
        }
        if (trim.equalsIgnoreCase("nfs")) {
            return 2049;
        }
        return 0;
    }
    
    public String getScheme() {
        return this.scheme;
    }
    
    public String getOpaque() {
        return this.opaque;
    }
    
    public String getHost() {
        return this.host;
    }
    
    public int getPort() {
        return this.port;
    }
    
    public String getUserinfo() {
        return this.userinfo;
    }
    
    public String getPath() {
        if (this.query == null) {
            return this.path;
        }
        if (this.path != null) {
            return this.path + "?" + this.query;
        }
        return "?" + this.query;
    }
    
    public String getQueryString() {
        return this.query;
    }
    
    public String getFragment() {
        return this.fragment;
    }
    
    public boolean isGenericURI() {
        return this.is_generic;
    }
    
    public URL toURL() throws MalformedURLException {
        if (this.url != null) {
            return this.url;
        }
        if (this.opaque != null) {
            return this.url = new URL(this.scheme + ":" + this.opaque);
        }
        final StringBuffer sb = new StringBuffer(100);
        if (this.path != null) {
            sb.append(escape(this.path.toCharArray(), URI.uricChar));
        }
        if (this.query != null) {
            sb.append('?');
            sb.append(escape(this.query.toCharArray(), URI.uricChar));
        }
        if (this.fragment != null) {
            sb.append('#');
            sb.append(escape(this.fragment.toCharArray(), URI.uricChar));
        }
        return this.url = new URL(this.scheme, this.host, this.port, sb.toString());
    }
    
    public String toExternalForm() {
        final StringBuffer sb = new StringBuffer(100);
        if (this.scheme != null) {
            sb.append(this.scheme);
            sb.append(':');
        }
        if (this.opaque != null) {
            sb.append(escape(this.opaque.toCharArray(), URI.uricChar));
            return sb.toString();
        }
        if (this.userinfo != null || this.host != null || this.port != -1) {
            sb.append("//");
        }
        if (this.userinfo != null) {
            sb.append(escape(this.userinfo.toCharArray(), URI.userinfoChar));
            sb.append('@');
        }
        if (this.host != null) {
            sb.append(this.host.toCharArray());
        }
        if (this.port != -1) {
            sb.append(':');
            sb.append(this.port);
        }
        if (this.path != null) {
            sb.append(this.path.toCharArray());
        }
        if (this.query != null) {
            sb.append('?');
            sb.append(escape(this.query.toCharArray(), URI.uricChar));
        }
        if (this.fragment != null) {
            sb.append('#');
            sb.append(escape(this.fragment.toCharArray(), URI.uricChar));
        }
        return sb.toString();
    }
    
    public String toString() {
        return this.toExternalForm();
    }
    
    public boolean equals(final Object o) {
        if (o instanceof URI) {
            final URI uri = (URI)o;
            return this.scheme.equalsIgnoreCase(uri.scheme) && ((!this.is_generic && ((this.opaque == null && uri.opaque == null) || (this.opaque != null && uri.opaque != null && this.opaque.equals(uri.opaque)))) || (this.is_generic && ((this.userinfo == null && uri.userinfo == null) || (this.userinfo != null && uri.userinfo != null && this.userinfo.equals(uri.userinfo))) && ((this.host == null && uri.host == null) || (this.host != null && uri.host != null && this.host.equalsIgnoreCase(uri.host))) && this.port == uri.port && ((this.path == null && uri.path == null) || (this.path != null && uri.path != null && unescapeNoPE(this.path).equals(unescapeNoPE(uri.path)))) && ((this.query == null && uri.query == null) || (this.query != null && uri.query != null && unescapeNoPE(this.query).equals(unescapeNoPE(uri.query)))) && ((this.fragment == null && uri.fragment == null) || (this.fragment != null && uri.fragment != null && unescapeNoPE(this.fragment).equals(unescapeNoPE(uri.fragment))))));
        }
        if (o instanceof URL) {
            final URL url = (URL)o;
            String s;
            if (this.userinfo != null) {
                s = this.userinfo + "@" + this.host;
            }
            else {
                s = this.host;
            }
            String s2;
            if (this.query != null) {
                s2 = this.path + "?" + this.query;
            }
            else {
                s2 = this.path;
            }
            return this.scheme.equalsIgnoreCase(url.getProtocol()) && ((!this.is_generic && this.opaque.equals(url.getFile())) || (this.is_generic && ((s == null && url.getHost() == null) || (s != null && url.getHost() != null && s.equalsIgnoreCase(url.getHost()))) && (this.port == url.getPort() || url.getPort() == defaultPort(this.scheme)) && ((s2 == null && url.getFile() == null) || (s2 != null && url.getFile() != null && unescapeNoPE(s2).equals(unescapeNoPE(url.getFile())))) && ((this.fragment == null && url.getRef() == null) || (this.fragment != null && url.getRef() != null && unescapeNoPE(this.fragment).equals(unescapeNoPE(url.getRef()))))));
        }
        return false;
    }
    
    private static char[] escape(final char[] array, final BitSet set) {
        int n = 0;
        for (int i = 0; i < array.length; ++i) {
            if (!set.get(array[i])) {
                ++n;
            }
        }
        if (n == 0) {
            return array;
        }
        final char[] array2 = new char[array.length + 2 * n];
        for (int j = 0, n2 = 0; j < array.length; ++j, ++n2) {
            if (set.get(array[j])) {
                array2[n2] = array[j];
            }
            else {
                if (array[j] > '\u00ff') {
                    throw new RuntimeException("Can't handle non 8-bt chars");
                }
                array2[n2++] = '%';
                array2[n2++] = URI.hex[array[j] >> 4 & '\u000f'];
                array2[n2] = URI.hex[array[j] & '\u000f'];
            }
        }
        return array2;
    }
    
    static final String unescape(final String s) throws ParseException {
        if (s == null || s.indexOf(37) == -1) {
            return s;
        }
        final char[] charArray = s.toCharArray();
        final char[] array = new char[charArray.length];
        int n = 0;
        for (int i = 0; i < charArray.length; ++i, ++n) {
            if (charArray[i] == '%') {
                int int1;
                try {
                    int1 = Integer.parseInt(s.substring(i + 1, i + 3), 16);
                    if (int1 < 0) {
                        throw new NumberFormatException();
                    }
                }
                catch (NumberFormatException ex) {
                    throw new ParseException(s.substring(i, i + 3) + " is an invalid code");
                }
                array[n] = (char)int1;
                i += 2;
            }
            else {
                array[n] = charArray[i];
            }
        }
        return new String(array, 0, n);
    }
    
    private static final String unescapeNoPE(final String s) {
        try {
            return unescape(s);
        }
        catch (ParseException ex) {
            return s;
        }
    }
    
    public static void main(final String[] array) throws Exception {
        System.err.println();
        System.err.println("*** URI Tests ...");
        final URI uri = new URI("http://a/b/c/d;p?q");
        testParser(uri, "g:h", "g:h");
        testParser(uri, "g", "http://a/b/c/g");
        testParser(uri, "./g", "http://a/b/c/g");
        testParser(uri, "g/", "http://a/b/c/g/");
        testParser(uri, "/g", "http://a/g");
        testParser(uri, "//g", "http://g");
        testParser(uri, "?y", "http://a/b/c/?y");
        testParser(uri, "g?y", "http://a/b/c/g?y");
        testParser(uri, "g?y", "http://a/b/c/g?y");
        testParser(uri, "#s", "http://a/b/c/d;p?q#s");
        testParser(uri, "g#s", "http://a/b/c/g#s");
        testParser(uri, "g?y#s", "http://a/b/c/g?y#s");
        testParser(uri, ";x", "http://a/b/c/;x");
        testParser(uri, "g;x", "http://a/b/c/g;x");
        testParser(uri, "g;x?y#s", "http://a/b/c/g;x?y#s");
        testParser(uri, ".", "http://a/b/c/");
        testParser(uri, "./", "http://a/b/c/");
        testParser(uri, "..", "http://a/b/");
        testParser(uri, "../", "http://a/b/");
        testParser(uri, "../g", "http://a/b/g");
        testParser(uri, "../..", "http://a/");
        testParser(uri, "../../", "http://a/");
        testParser(uri, "../../g", "http://a/g");
        testParser(uri, "", "http://a/b/c/d;p?q");
        testParser(uri, "/./g", "http://a/./g");
        testParser(uri, "/../g", "http://a/../g");
        testParser(uri, "g.", "http://a/b/c/g.");
        testParser(uri, ".g", "http://a/b/c/.g");
        testParser(uri, "g..", "http://a/b/c/g..");
        testParser(uri, "..g", "http://a/b/c/..g");
        testParser(uri, "./../g", "http://a/b/g");
        testParser(uri, "./g/.", "http://a/b/c/g/");
        testParser(uri, "g/./h", "http://a/b/c/g/h");
        testParser(uri, "g/../h", "http://a/b/c/h");
        testParser(uri, "g;x=1/./y", "http://a/b/c/g;x=1/y");
        testParser(uri, "g;x=1/../y", "http://a/b/c/y");
        testParser(uri, "g?y/./x", "http://a/b/c/g?y/./x");
        testParser(uri, "g?y/../x", "http://a/b/c/g?y/../x");
        testParser(uri, "g#s/./x", "http://a/b/c/g#s/./x");
        testParser(uri, "g#s/../x", "http://a/b/c/g#s/../x");
        testParser(uri, "http:g", "http:g");
        testNotEqual("http://a/", "nntp://a/");
        testNotEqual("http://a/", "https://a/");
        testNotEqual("http://a/", "shttp://a/");
        testEqual("http://a/", "Http://a/");
        testEqual("http://a/", "hTTP://a/");
        testEqual("url:http://a/", "hTTP://a/");
        testEqual("urI:http://a/", "hTTP://a/");
        testEqual("http://a/", "Http://A/");
        testEqual("http://a.b.c/", "Http://A.b.C/");
        testEqual("http:///", "Http:///");
        testNotEqual("http:///", "Http://a/");
        testEqual("http://a.b.c/", "Http://A.b.C:80/");
        testEqual("nntp://a", "nntp://a:119");
        testEqual("nntp://a/", "nntp://a:119/");
        testNotEqual("nntp://a", "nntp://a:118");
        testNotEqual("nntp://a", "nntp://a:0");
        testEqual("telnet://:23/", "telnet:///");
        testPE("ftp://:a/");
        testPE("ftp://:-1/");
        testPE("ftp://::1/");
        testNotEqual("ftp://me@a", "ftp://a");
        testNotEqual("ftp://me@a", "ftp://Me@a");
        testEqual("ftp://Me@a", "ftp://Me@a");
        testEqual("ftp://Me:My@a:21", "ftp://Me:My@a");
        testNotEqual("ftp://Me:My@a:21", "ftp://Me:my@a");
        testEqual("ftp://a/b%2b/", "ftp://a/b+/");
        testEqual("ftp://a/b%2b/", "ftp://a/b+/");
        testEqual("ftp://a/b%5E/", "ftp://a/b^/");
        testNotEqual("ftp://a/b%3f/", "ftp://a/b?/");
        System.err.println("*** Tests finished successfuly");
    }
    
    private static void testParser(final URI uri, final String s, final String s2) throws Exception {
        if (!new URI(uri, s).toString().equals(s2)) {
            final String property = System.getProperty("line.separator");
            throw new Exception("Test failed: " + property + "  base-URI = <" + uri + ">" + property + "  rel-URI  = <" + s + ">" + property + "  expected   <" + s2 + ">" + property + "  but got    <" + new URI(uri, s) + ">");
        }
    }
    
    private static void testEqual(final String s, final String s2) throws Exception {
        if (!new URI(s).equals(new URI(s2))) {
            throw new Exception("Test failed: " + System.getProperty("line.separator") + "  <" + s + "> != <" + s2 + ">");
        }
    }
    
    private static void testNotEqual(final String s, final String s2) throws Exception {
        if (new URI(s).equals(new URI(s2))) {
            throw new Exception("Test failed: " + System.getProperty("line.separator") + "  <" + s + "> == <" + s2 + ">");
        }
    }
    
    private static void testPE(final String s) throws Exception {
        boolean b = false;
        try {
            new URI(s);
        }
        catch (ParseException ex) {
            b = true;
        }
        if (!b) {
            throw new Exception("Test failed: " + System.getProperty("line.separator") + "  <" + s + "> should be invalid");
        }
    }
    
    static {
        URI.alphanumChar = new BitSet(128);
        for (int i = 48; i <= 57; ++i) {
            URI.alphanumChar.set(i);
        }
        for (int j = 65; j <= 90; ++j) {
            URI.alphanumChar.set(j);
        }
        for (int k = 97; k <= 122; ++k) {
            URI.alphanumChar.set(k);
        }
        (URI.markChar = new BitSet(128)).set(45);
        URI.markChar.set(95);
        URI.markChar.set(46);
        URI.markChar.set(33);
        URI.markChar.set(126);
        URI.markChar.set(42);
        URI.markChar.set(39);
        URI.markChar.set(40);
        URI.markChar.set(41);
        (URI.reservedChar = new BitSet(128)).set(59);
        URI.reservedChar.set(47);
        URI.reservedChar.set(63);
        URI.reservedChar.set(58);
        URI.reservedChar.set(64);
        URI.reservedChar.set(38);
        URI.reservedChar.set(61);
        URI.reservedChar.set(43);
        URI.reservedChar.set(36);
        URI.reservedChar.set(44);
        (URI.unreservedChar = new BitSet(128)).or(URI.alphanumChar);
        URI.unreservedChar.or(URI.markChar);
        (URI.uricChar = new BitSet(128)).or(URI.unreservedChar);
        URI.uricChar.or(URI.reservedChar);
        (URI.pcharChar = new BitSet(128)).or(URI.unreservedChar);
        URI.pcharChar.set(58);
        URI.pcharChar.set(64);
        URI.pcharChar.set(38);
        URI.pcharChar.set(61);
        URI.pcharChar.set(43);
        URI.pcharChar.set(36);
        URI.pcharChar.set(44);
        (URI.userinfoChar = new BitSet(128)).or(URI.unreservedChar);
        URI.userinfoChar.set(59);
        URI.userinfoChar.set(58);
        URI.userinfoChar.set(38);
        URI.userinfoChar.set(61);
        URI.userinfoChar.set(43);
        URI.userinfoChar.set(36);
        URI.userinfoChar.set(44);
        (URI.schemeChar = new BitSet(128)).or(URI.alphanumChar);
        URI.schemeChar.set(43);
        URI.schemeChar.set(45);
        URI.schemeChar.set(46);
        (URI.reg_nameChar = new BitSet(128)).or(URI.unreservedChar);
        URI.reg_nameChar.set(36);
        URI.reg_nameChar.set(44);
        URI.reg_nameChar.set(59);
        URI.reg_nameChar.set(58);
        URI.reg_nameChar.set(64);
        URI.reg_nameChar.set(38);
        URI.reg_nameChar.set(61);
        URI.reg_nameChar.set(43);
        hex = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
}
