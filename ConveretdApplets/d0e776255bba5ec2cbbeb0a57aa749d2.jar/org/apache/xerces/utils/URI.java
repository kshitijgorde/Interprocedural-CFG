// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

import java.io.IOException;
import java.io.Serializable;

public class URI implements Serializable
{
    private static final String RESERVED_CHARACTERS = ";/?:@&=+$,";
    private static final String MARK_CHARACTERS = "-_.!~*'() ";
    private static final String SCHEME_CHARACTERS = "+-.";
    private static final String USERINFO_CHARACTERS = ";:&=+$,";
    private String m_scheme;
    private String m_userinfo;
    private String m_host;
    private int m_port;
    private String m_path;
    private String m_queryString;
    private String m_fragment;
    private static boolean DEBUG;
    
    public URI() {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
    }
    
    public URI(final URI uri) {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        this.initialize(uri);
    }
    
    public URI(final String s) throws MalformedURIException {
        this((URI)null, s);
    }
    
    public URI(final URI uri, final String s) throws MalformedURIException {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        this.initialize(uri, s);
    }
    
    public URI(final String scheme, final String path) throws MalformedURIException {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        if (scheme == null || scheme.trim().length() == 0) {
            throw new MalformedURIException("Cannot construct URI with null/empty scheme!");
        }
        if (path == null || path.trim().length() == 0) {
            throw new MalformedURIException("Cannot construct URI with null/empty scheme-specific part!");
        }
        this.setScheme(scheme);
        this.setPath(path);
    }
    
    public URI(final String s, final String s2, final String s3, final String s4, final String s5) throws MalformedURIException {
        this(s, null, s2, -1, s3, s4, s5);
    }
    
    public URI(final String scheme, final String userinfo, final String host, final int port, final String path, final String queryString, final String fragment) throws MalformedURIException {
        this.m_scheme = null;
        this.m_userinfo = null;
        this.m_host = null;
        this.m_port = -1;
        this.m_path = null;
        this.m_queryString = null;
        this.m_fragment = null;
        if (scheme == null || scheme.trim().length() == 0) {
            throw new MalformedURIException("Scheme is required!");
        }
        if (host == null) {
            if (userinfo != null) {
                throw new MalformedURIException("Userinfo may not be specified if host is not specified!");
            }
            if (port != -1) {
                throw new MalformedURIException("Port may not be specified if host is not specified!");
            }
        }
        if (path != null) {
            if (path.indexOf(63) != -1 && queryString != null) {
                throw new MalformedURIException("Query string cannot be specified in path and query string!");
            }
            if (path.indexOf(35) != -1 && fragment != null) {
                throw new MalformedURIException("Fragment cannot be specified in both the path and fragment!");
            }
        }
        this.setScheme(scheme);
        this.setHost(host);
        this.setPort(port);
        this.setUserinfo(userinfo);
        this.setPath(path);
        this.setQueryString(queryString);
        this.setFragment(fragment);
    }
    
    private void initialize(final URI uri) {
        this.m_scheme = uri.getScheme();
        this.m_userinfo = uri.getUserinfo();
        this.m_host = uri.getHost();
        this.m_port = uri.getPort();
        this.m_path = uri.getPath();
        this.m_queryString = uri.getQueryString();
        this.m_fragment = uri.getFragment();
    }
    
    private void initialize(final URI uri, final String s) throws MalformedURIException {
        if (uri == null && (s == null || s.trim().length() == 0)) {
            throw new MalformedURIException("Cannot initialize URI with empty parameters.");
        }
        if (s == null || s.trim().length() == 0) {
            this.initialize(uri);
            return;
        }
        final String trim = s.trim();
        final int length = trim.length();
        int i = 0;
        final int index = trim.indexOf(58);
        final int index2 = trim.indexOf(47);
        if (index < 2 || (index > index2 && index2 != -1)) {
            final int index3 = trim.indexOf(35);
            if (uri == null && index3 != 0) {
                throw new MalformedURIException("No scheme found in URI.");
            }
        }
        else {
            this.initializeScheme(trim);
            i = this.m_scheme.length() + 1;
        }
        if (i + 1 < length && trim.substring(i).startsWith("//")) {
            i += 2;
            final int n = i;
            while (i < length) {
                final char char1 = trim.charAt(i);
                if (char1 == '/' || char1 == '?') {
                    break;
                }
                if (char1 == '#') {
                    break;
                }
                ++i;
            }
            if (i > n) {
                this.initializeAuthority(trim.substring(n, i));
            }
            else {
                this.m_host = "";
            }
        }
        this.initializePath(trim.substring(i));
        if (uri != null) {
            if (this.m_path.length() == 0 && this.m_scheme == null && this.m_host == null) {
                this.m_scheme = uri.getScheme();
                this.m_userinfo = uri.getUserinfo();
                this.m_host = uri.getHost();
                this.m_port = uri.getPort();
                this.m_path = uri.getPath();
                if (this.m_queryString == null) {
                    this.m_queryString = uri.getQueryString();
                }
                return;
            }
            if (this.m_scheme != null) {
                return;
            }
            this.m_scheme = uri.getScheme();
            if (this.m_host != null) {
                return;
            }
            this.m_userinfo = uri.getUserinfo();
            this.m_host = uri.getHost();
            this.m_port = uri.getPort();
            if (this.m_path.length() > 0 && this.m_path.startsWith("/")) {
                return;
            }
            String substring = new String();
            final String path = uri.getPath();
            if (path != null) {
                final int lastIndex = path.lastIndexOf(47);
                if (lastIndex != -1) {
                    substring = path.substring(0, lastIndex + 1);
                }
            }
            String path2;
            int index4;
            for (path2 = substring.concat(this.m_path); (index4 = path2.indexOf("/./")) != -1; path2 = path2.substring(0, index4 + 1).concat(path2.substring(index4 + 3))) {}
            if (path2.endsWith("/.")) {
                path2 = path2.substring(0, path2.length() - 1);
            }
            int index5;
            while ((index5 = path2.indexOf("/../")) > 0) {
                final String substring2 = path2.substring(0, path2.indexOf("/../"));
                int lastIndex2 = substring2.lastIndexOf(47);
                if (lastIndex2 != -1 && !substring2.substring(lastIndex2++).equals("..")) {
                    path2 = path2.substring(0, lastIndex2).concat(path2.substring(index5 + 4));
                }
            }
            if (path2.endsWith("/..")) {
                final int lastIndex3 = path2.substring(0, path2.length() - 3).lastIndexOf(47);
                if (lastIndex3 != -1) {
                    path2 = path2.substring(0, lastIndex3 + 1);
                }
            }
            this.m_path = path2;
        }
    }
    
    private void initializeScheme(final String s) throws MalformedURIException {
        int length;
        int i;
        for (length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == ':' || char1 == '/' || char1 == '?') {
                break;
            }
            if (char1 == '#') {
                break;
            }
        }
        final String substring = s.substring(0, i);
        if (substring.length() == 0) {
            throw new MalformedURIException("No scheme found in URI.");
        }
        this.setScheme(substring);
    }
    
    private void initializeAuthority(final String s) throws MalformedURIException {
        int i = 0;
        final int n = 0;
        final int length = s.length();
        char c = '\0';
        String substring = null;
        if (s.indexOf(64, n) != -1) {
            while (i < length) {
                c = s.charAt(i);
                if (c == '@') {
                    break;
                }
                ++i;
            }
            substring = s.substring(n, i);
            ++i;
        }
        final int n2 = i;
        while (i < length) {
            c = s.charAt(i);
            if (c == ':') {
                break;
            }
            ++i;
        }
        final String substring2 = s.substring(n2, i);
        int int1 = -1;
        if (substring2.length() > 0 && c == ':') {
            int n3;
            for (n3 = ++i; i < length; ++i) {}
            final String substring3 = s.substring(n3, i);
            if (substring3.length() > 0) {
                for (int j = 0; j < substring3.length(); ++j) {
                    if (!isDigit(substring3.charAt(j))) {
                        throw new MalformedURIException(substring3 + " is invalid. Port should only contain digits!");
                    }
                }
                try {
                    int1 = Integer.parseInt(substring3);
                }
                catch (NumberFormatException ex) {}
            }
        }
        this.setHost(substring2);
        this.setPort(int1);
        this.setUserinfo(substring);
    }
    
    private void initializePath(final String s) throws MalformedURIException {
        if (s == null) {
            throw new MalformedURIException("Cannot initialize path from null string!");
        }
        int i = 0;
        final int n = 0;
        final int length = s.length();
        char c = '\0';
        while (i < length) {
            c = s.charAt(i);
            if (c == '?') {
                break;
            }
            if (c == '#') {
                break;
            }
            if (c == '%') {
                if (i + 2 >= length || !isHex(s.charAt(i + 1)) || !isHex(s.charAt(i + 2))) {
                    throw new MalformedURIException("Path contains invalid escape sequence!");
                }
            }
            else if (!isReservedCharacter(c) && !isUnreservedCharacter(c)) {
                throw new MalformedURIException("Path contains invalid character: " + c);
            }
            ++i;
        }
        this.m_path = s.substring(n, i);
        if (c == '?') {
            int n2;
            for (n2 = ++i; i < length; ++i) {
                c = s.charAt(i);
                if (c == '#') {
                    break;
                }
                if (c == '%') {
                    if (i + 2 >= length || !isHex(s.charAt(i + 1)) || !isHex(s.charAt(i + 2))) {
                        throw new MalformedURIException("Query string contains invalid escape sequence!");
                    }
                }
                else if (!isReservedCharacter(c) && !isUnreservedCharacter(c)) {
                    throw new MalformedURIException("Query string contains invalid character:" + c);
                }
            }
            this.m_queryString = s.substring(n2, i);
        }
        if (c == '#') {
            int n3;
            for (n3 = ++i; i < length; ++i) {
                final char char1 = s.charAt(i);
                if (char1 == '%') {
                    if (i + 2 >= length || !isHex(s.charAt(i + 1)) || !isHex(s.charAt(i + 2))) {
                        throw new MalformedURIException("Fragment contains invalid escape sequence!");
                    }
                }
                else if (!isReservedCharacter(char1) && !isUnreservedCharacter(char1)) {
                    throw new MalformedURIException("Fragment contains invalid character:" + char1);
                }
            }
            this.m_fragment = s.substring(n3, i);
        }
    }
    
    public String getScheme() {
        return this.m_scheme;
    }
    
    public String getSchemeSpecificPart() {
        final StringBuffer sb = new StringBuffer();
        if (this.m_userinfo != null || this.m_host != null || this.m_port != -1) {
            sb.append("//");
        }
        if (this.m_userinfo != null) {
            sb.append(this.m_userinfo);
            sb.append('@');
        }
        if (this.m_host != null) {
            sb.append(this.m_host);
        }
        if (this.m_port != -1) {
            sb.append(':');
            sb.append(this.m_port);
        }
        if (this.m_path != null) {
            sb.append(this.m_path);
        }
        if (this.m_queryString != null) {
            sb.append('?');
            sb.append(this.m_queryString);
        }
        if (this.m_fragment != null) {
            sb.append('#');
            sb.append(this.m_fragment);
        }
        return sb.toString();
    }
    
    public String getUserinfo() {
        return this.m_userinfo;
    }
    
    public String getHost() {
        return this.m_host;
    }
    
    public int getPort() {
        return this.m_port;
    }
    
    public String getPath(final boolean b, final boolean b2) {
        final StringBuffer sb = new StringBuffer(this.m_path);
        if (b && this.m_queryString != null) {
            sb.append('?');
            sb.append(this.m_queryString);
        }
        if (b2 && this.m_fragment != null) {
            sb.append('#');
            sb.append(this.m_fragment);
        }
        return sb.toString();
    }
    
    public String getPath() {
        return this.m_path;
    }
    
    public String getQueryString() {
        return this.m_queryString;
    }
    
    public String getFragment() {
        return this.m_fragment;
    }
    
    public void setScheme(final String s) throws MalformedURIException {
        if (s == null) {
            throw new MalformedURIException("Cannot set scheme from null string!");
        }
        if (!isConformantSchemeName(s)) {
            throw new MalformedURIException("The scheme is not conformant.");
        }
        this.m_scheme = s.toLowerCase();
    }
    
    public void setUserinfo(final String userinfo) throws MalformedURIException {
        if (userinfo == null) {
            this.m_userinfo = null;
        }
        else {
            if (this.m_host == null) {
                throw new MalformedURIException("Userinfo cannot be set when host is null!");
            }
            for (int i = 0, length = userinfo.length(); i < length; ++i) {
                final char char1 = userinfo.charAt(i);
                if (char1 == '%') {
                    if (i + 2 >= length || !isHex(userinfo.charAt(i + 1)) || !isHex(userinfo.charAt(i + 2))) {
                        throw new MalformedURIException("Userinfo contains invalid escape sequence!");
                    }
                }
                else if (!isUnreservedCharacter(char1) && ";:&=+$,".indexOf(char1) == -1) {
                    throw new MalformedURIException("Userinfo contains invalid character:" + char1);
                }
            }
        }
        this.m_userinfo = userinfo;
    }
    
    public void setHost(final String s) throws MalformedURIException {
        if (s == null || s.trim().length() == 0) {
            this.m_host = s;
            this.m_userinfo = null;
            this.m_port = -1;
        }
        else if (!isWellFormedAddress(s)) {
            throw new MalformedURIException("Host is not a well formed address!");
        }
        this.m_host = s;
    }
    
    public void setPort(final int port) throws MalformedURIException {
        if (port >= 0 && port <= 65535) {
            if (this.m_host == null) {
                throw new MalformedURIException("Port cannot be set when host is null!");
            }
        }
        else if (port != -1) {
            throw new MalformedURIException("Invalid port number!");
        }
        this.m_port = port;
    }
    
    public void setPath(final String s) throws MalformedURIException {
        if (s == null) {
            this.m_path = null;
            this.m_queryString = null;
            this.m_fragment = null;
        }
        else {
            this.initializePath(s);
        }
    }
    
    public void appendPath(final String path) throws MalformedURIException {
        if (path == null || path.trim().length() == 0) {
            return;
        }
        if (!isURIString(path)) {
            throw new MalformedURIException("Path contains invalid character!");
        }
        if (this.m_path == null || this.m_path.trim().length() == 0) {
            if (path.startsWith("/")) {
                this.m_path = path;
            }
            else {
                this.m_path = "/" + path;
            }
        }
        else if (this.m_path.endsWith("/")) {
            if (path.startsWith("/")) {
                this.m_path = this.m_path.concat(path.substring(1));
            }
            else {
                this.m_path = this.m_path.concat(path);
            }
        }
        else if (path.startsWith("/")) {
            this.m_path = this.m_path.concat(path);
        }
        else {
            this.m_path = this.m_path.concat("/" + path);
        }
    }
    
    public void setQueryString(final String queryString) throws MalformedURIException {
        if (queryString == null) {
            this.m_queryString = null;
        }
        else {
            if (!this.isGenericURI()) {
                throw new MalformedURIException("Query string can only be set for a generic URI!");
            }
            if (this.getPath() == null) {
                throw new MalformedURIException("Query string cannot be set when path is null!");
            }
            if (!isURIString(queryString)) {
                throw new MalformedURIException("Query string contains invalid character!");
            }
            this.m_queryString = queryString;
        }
    }
    
    public void setFragment(final String fragment) throws MalformedURIException {
        if (fragment == null) {
            this.m_fragment = null;
        }
        else {
            if (!this.isGenericURI()) {
                throw new MalformedURIException("Fragment can only be set for a generic URI!");
            }
            if (this.getPath() == null) {
                throw new MalformedURIException("Fragment cannot be set when path is null!");
            }
            if (!isURIString(fragment)) {
                throw new MalformedURIException("Fragment contains invalid character!");
            }
            this.m_fragment = fragment;
        }
    }
    
    public boolean equals(final Object o) {
        if (o instanceof URI) {
            final URI uri = (URI)o;
            if (((this.m_scheme == null && uri.m_scheme == null) || (this.m_scheme != null && uri.m_scheme != null && this.m_scheme.equals(uri.m_scheme))) && ((this.m_userinfo == null && uri.m_userinfo == null) || (this.m_userinfo != null && uri.m_userinfo != null && this.m_userinfo.equals(uri.m_userinfo))) && ((this.m_host == null && uri.m_host == null) || (this.m_host != null && uri.m_host != null && this.m_host.equals(uri.m_host))) && this.m_port == uri.m_port && ((this.m_path == null && uri.m_path == null) || (this.m_path != null && uri.m_path != null && this.m_path.equals(uri.m_path))) && ((this.m_queryString == null && uri.m_queryString == null) || (this.m_queryString != null && uri.m_queryString != null && this.m_queryString.equals(uri.m_queryString))) && ((this.m_fragment == null && uri.m_fragment == null) || (this.m_fragment != null && uri.m_fragment != null && this.m_fragment.equals(uri.m_fragment)))) {
                return true;
            }
        }
        return false;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        if (this.m_scheme != null) {
            sb.append(this.m_scheme);
            sb.append(':');
        }
        sb.append(this.getSchemeSpecificPart());
        return sb.toString();
    }
    
    public boolean isGenericURI() {
        return this.m_host != null;
    }
    
    public static boolean isConformantSchemeName(final String s) {
        if (s == null || s.trim().length() == 0) {
            return false;
        }
        if (!isAlpha(s.charAt(0))) {
            return false;
        }
        for (int i = 1; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (!isAlphanum(char1) && "+-.".indexOf(char1) == -1) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isWellFormedAddress(final String s) {
        if (s == null) {
            return false;
        }
        final String trim = s.trim();
        final int length = trim.length();
        if (length == 0 || length > 255) {
            return false;
        }
        if (trim.startsWith(".") || trim.startsWith("-")) {
            return false;
        }
        int n = trim.lastIndexOf(46);
        if (trim.endsWith(".")) {
            n = trim.substring(0, n).lastIndexOf(46);
        }
        if (n + 1 < length && isDigit(s.charAt(n + 1))) {
            int n2 = 0;
            for (int i = 0; i < length; ++i) {
                final char char1 = trim.charAt(i);
                if (char1 == '.') {
                    if (!isDigit(trim.charAt(i - 1)) || (i + 1 < length && !isDigit(trim.charAt(i + 1)))) {
                        return false;
                    }
                    ++n2;
                }
                else if (!isDigit(char1)) {
                    return false;
                }
            }
            if (n2 != 3) {
                return false;
            }
        }
        else {
            for (int j = 0; j < length; ++j) {
                final char char2 = trim.charAt(j);
                if (char2 == '.') {
                    if (!isAlphanum(trim.charAt(j - 1))) {
                        return false;
                    }
                    if (j + 1 < length && !isAlphanum(trim.charAt(j + 1))) {
                        return false;
                    }
                }
                else if (!isAlphanum(char2) && char2 != '-') {
                    return false;
                }
            }
        }
        return true;
    }
    
    private static boolean isDigit(final char c) {
        return c >= '0' && c <= '9';
    }
    
    private static boolean isHex(final char c) {
        return isDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }
    
    private static boolean isAlpha(final char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    private static boolean isAlphanum(final char c) {
        return isAlpha(c) || isDigit(c);
    }
    
    private static boolean isReservedCharacter(final char c) {
        return ";/?:@&=+$,".indexOf(c) != -1;
    }
    
    private static boolean isUnreservedCharacter(final char c) {
        return isAlphanum(c) || "-_.!~*'() ".indexOf(c) != -1;
    }
    
    private static boolean isURIString(final String s) {
        if (s == null) {
            return false;
        }
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '%') {
                if (i + 2 >= length || !isHex(s.charAt(i + 1)) || !isHex(s.charAt(i + 2))) {
                    return false;
                }
                i += 2;
            }
            else if (!isReservedCharacter(char1)) {
                if (!isUnreservedCharacter(char1)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    static {
        URI.DEBUG = false;
    }
    
    public static class MalformedURIException extends IOException
    {
        public MalformedURIException() {
        }
        
        public MalformedURIException(final String s) {
            super(s);
        }
    }
}
