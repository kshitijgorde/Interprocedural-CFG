// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.net.ProtocolException;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Hashtable;

public class AuthorizationInfo implements GlobalConstants, Cloneable
{
    private static Hashtable CntxtList;
    private static AuthorizationHandler AuthHandler;
    private String Host;
    private int Port;
    private String Scheme;
    private String Realm;
    private String cookie;
    private NVPair[] auth_params;
    private Object extra_info;
    private String[] paths;
    
    AuthorizationInfo(final String s, final int port) {
        this.auth_params = new NVPair[0];
        this.extra_info = null;
        this.paths = new String[0];
        this.Host = s.trim().toLowerCase();
        this.Port = port;
    }
    
    public AuthorizationInfo(final String s, final int port, final String s2, final String realm, final NVPair[] array, final Object extra_info) {
        this.auth_params = new NVPair[0];
        this.extra_info = null;
        this.paths = new String[0];
        this.Scheme = s2.trim();
        this.Host = s.trim().toLowerCase();
        this.Port = port;
        this.Realm = realm;
        this.cookie = null;
        if (array != null) {
            this.auth_params = Util.resizeArray(array, array.length);
        }
        this.extra_info = extra_info;
    }
    
    public AuthorizationInfo(final String s, final int port, final String s2, final String realm, final String s3) {
        this.auth_params = new NVPair[0];
        this.extra_info = null;
        this.paths = new String[0];
        this.Scheme = s2.trim();
        this.Host = s.trim().toLowerCase();
        this.Port = port;
        this.Realm = realm;
        if (s3 != null) {
            this.cookie = s3.trim();
        }
        else {
            this.cookie = null;
        }
    }
    
    AuthorizationInfo(final AuthorizationInfo authorizationInfo) {
        this.auth_params = new NVPair[0];
        this.extra_info = null;
        this.paths = new String[0];
        this.Scheme = authorizationInfo.Scheme;
        this.Host = authorizationInfo.Host;
        this.Port = authorizationInfo.Port;
        this.Realm = authorizationInfo.Realm;
        this.cookie = authorizationInfo.cookie;
        this.auth_params = Util.resizeArray(authorizationInfo.auth_params, authorizationInfo.auth_params.length);
        this.extra_info = authorizationInfo.extra_info;
    }
    
    public static AuthorizationHandler setAuthHandler(final AuthorizationHandler authHandler) {
        final AuthorizationHandler authHandler2 = AuthorizationInfo.AuthHandler;
        AuthorizationInfo.AuthHandler = authHandler;
        return authHandler2;
    }
    
    public static AuthorizationHandler getAuthHandler() {
        return AuthorizationInfo.AuthHandler;
    }
    
    public static AuthorizationInfo getAuthorization(final String s, final int n, final String s2, final String s3) {
        return getAuthorization(s, n, s2, s3, HTTPConnection.getDefaultContext());
    }
    
    public static synchronized AuthorizationInfo getAuthorization(final String s, final int n, final String s2, final String s3, final Object o) {
        return Util.getList(AuthorizationInfo.CntxtList, o).get(new AuthorizationInfo(s.trim(), n, s2.trim(), s3, null, null));
    }
    
    static AuthorizationInfo queryAuthHandler(final AuthorizationInfo authorizationInfo, final RoRequest roRequest, final RoResponse roResponse, final boolean b) throws AuthSchemeNotImplException {
        if (AuthorizationInfo.AuthHandler == null) {
            return null;
        }
        final AuthorizationInfo authorization = AuthorizationInfo.AuthHandler.getAuthorization(authorizationInfo, roRequest, roResponse, b);
        if (authorization != null) {
            if (roRequest != null) {
                addAuthorization((AuthorizationInfo)authorization.clone(), roRequest.getConnection().getContext());
            }
            else {
                addAuthorization((AuthorizationInfo)authorization.clone(), HTTPConnection.getDefaultContext());
            }
        }
        return authorization;
    }
    
    static synchronized AuthorizationInfo getAuthorization(final AuthorizationInfo authorizationInfo, final RoRequest roRequest, final RoResponse roResponse, final boolean b, final boolean b2) throws AuthSchemeNotImplException {
        Hashtable hashtable;
        if (roRequest != null) {
            hashtable = Util.getList(AuthorizationInfo.CntxtList, roRequest.getConnection().getContext());
        }
        else {
            hashtable = Util.getList(AuthorizationInfo.CntxtList, HTTPConnection.getDefaultContext());
        }
        AuthorizationInfo queryAuthHandler = hashtable.get(authorizationInfo);
        if (queryAuthHandler == null && b2) {
            queryAuthHandler = queryAuthHandler(authorizationInfo, roRequest, roResponse, b);
        }
        return queryAuthHandler;
    }
    
    static AuthorizationInfo getAuthorization(final String s, final int n, final String s2, final String s3, final boolean b, final boolean b2) throws AuthSchemeNotImplException {
        return getAuthorization(new AuthorizationInfo(s.trim(), n, s2.trim(), s3, null, null), null, null, b, b2);
    }
    
    public static void addAuthorization(final AuthorizationInfo authorizationInfo) {
        addAuthorization(authorizationInfo, HTTPConnection.getDefaultContext());
    }
    
    public static void addAuthorization(final AuthorizationInfo authorizationInfo, final Object o) {
        final Hashtable list = Util.getList(AuthorizationInfo.CntxtList, o);
        final AuthorizationInfo authorizationInfo2 = list.get(authorizationInfo);
        if (authorizationInfo2 != null) {
            final int length = authorizationInfo2.paths.length;
            final int length2 = authorizationInfo.paths.length;
            if (length2 == 0) {
                authorizationInfo.paths = authorizationInfo2.paths;
            }
            else {
                authorizationInfo.paths = Util.resizeArray(authorizationInfo.paths, length2 + length);
                System.arraycopy(authorizationInfo2.paths, 0, authorizationInfo.paths, length2, length);
            }
        }
        list.put(authorizationInfo, authorizationInfo);
    }
    
    public static void addAuthorization(final String s, final int n, final String s2, final String s3, final String s4, final NVPair[] array, final Object o) {
        addAuthorization(s, n, s2, s3, s4, array, o, HTTPConnection.getDefaultContext());
    }
    
    public static void addAuthorization(final String s, final int n, final String s2, final String s3, final String s4, final NVPair[] array, final Object extra_info, final Object o) {
        final AuthorizationInfo authorizationInfo = new AuthorizationInfo(s, n, s2, s3, s4);
        if (array != null && array.length > 0) {
            authorizationInfo.auth_params = Util.resizeArray(array, array.length);
        }
        authorizationInfo.extra_info = extra_info;
        addAuthorization(authorizationInfo, o);
    }
    
    public static void addBasicAuthorization(final String s, final int n, final String s2, final String s3, final String s4) throws AuthSchemeNotImplException {
        addAuthorization("Basic", s, n, s2, s3, s4, HTTPConnection.getDefaultContext());
    }
    
    public static void addBasicAuthorization(final String s, final int n, final String s2, final String s3, final String s4, final Object o) throws AuthSchemeNotImplException {
        addAuthorization("Basic", s, n, s2, s3, s4, o);
    }
    
    public static void addDigestAuthorization(final String s, final int n, final String s2, final String s3, final String s4) throws AuthSchemeNotImplException {
        addAuthorization("Digest", s, n, s2, s3, s4, HTTPConnection.getDefaultContext());
    }
    
    public static void addDigestAuthorization(final String s, final int n, final String s2, final String s3, final String s4, final Object o) throws AuthSchemeNotImplException {
        addAuthorization("Digest", s, n, s2, s3, s4, o);
    }
    
    public static void addAuthorization(final String s, final String s2, final int n, final String s3, final Object o, final Object o2) throws AuthSchemeNotImplException {
        addAuthorization(s, s2, n, s3, o, o2, HTTPConnection.getDefaultContext());
    }
    
    public static void addAuthorization(final String s, final String s2, final int n, final String s3, final Object o, final Object o2, final Object o3) throws AuthSchemeNotImplException {
        if (AuthorizationInfo.AuthHandler == null) {
            throw new AuthSchemeNotImplException("no authorization handler installed");
        }
        AuthorizationInfo.AuthHandler.addAuthorizationInfo(s, s2, n, s3, o, o2, o3);
    }
    
    public static void removeAuthorization(final AuthorizationInfo authorizationInfo) {
        removeAuthorization(authorizationInfo, HTTPConnection.getDefaultContext());
    }
    
    public static void removeAuthorization(final AuthorizationInfo authorizationInfo, final Object o) {
        Util.getList(AuthorizationInfo.CntxtList, o).remove(authorizationInfo);
    }
    
    public static void removeAuthorization(final String s, final int n, final String s2, final String s3) {
        removeAuthorization(new AuthorizationInfo(s, n, s2, s3, null, null));
    }
    
    public static void removeAuthorization(final String s, final int n, final String s2, final String s3, final Object o) {
        removeAuthorization(new AuthorizationInfo(s, n, s2, s3, null, null), o);
    }
    
    static AuthorizationInfo findBest(final RoRequest roRequest) {
        final String path = Util.getPath(roRequest.getRequestURI());
        final String host = roRequest.getConnection().getHost();
        final int port = roRequest.getConnection().getPort();
        final Hashtable list = Util.getList(AuthorizationInfo.CntxtList, roRequest.getConnection().getContext());
        final Enumeration<AuthorizationInfo> elements = list.elements();
        while (elements.hasMoreElements()) {
            final AuthorizationInfo authorizationInfo = elements.nextElement();
            if (authorizationInfo.Host.equals(host)) {
                if (authorizationInfo.Port != port) {
                    continue;
                }
                final String[] paths = authorizationInfo.paths;
                for (int i = 0; i < paths.length; ++i) {
                    if (path.equals(paths[i])) {
                        return authorizationInfo;
                    }
                }
            }
        }
        AuthorizationInfo authorizationInfo2 = null;
        final String substring = path.substring(0, path.lastIndexOf(47) + 1);
        int n = Integer.MAX_VALUE;
        final Enumeration<AuthorizationInfo> elements2 = list.elements();
        while (elements2.hasMoreElements()) {
            final AuthorizationInfo authorizationInfo3 = elements2.nextElement();
            if (authorizationInfo3.Host.equals(host)) {
                if (authorizationInfo3.Port != port) {
                    continue;
                }
                final String[] paths2 = authorizationInfo3.paths;
                for (int j = 0; j < paths2.length; ++j) {
                    final String substring2 = paths2[j].substring(0, paths2[j].lastIndexOf(47) + 1);
                    if (substring.equals(substring2)) {
                        return authorizationInfo3;
                    }
                    if (substring.startsWith(substring2)) {
                        int n2 = 0;
                        int index = substring2.length() - 1;
                        while ((index = substring.indexOf(47, index + 1)) != -1) {
                            ++n2;
                        }
                        if (n2 < n) {
                            n = n2;
                            authorizationInfo2 = authorizationInfo3;
                        }
                    }
                    else if (substring2.startsWith(substring)) {
                        int n3 = 0;
                        int n4 = substring.length();
                        while ((n4 = substring2.indexOf(47, n4 + 1)) != -1) {
                            ++n3;
                        }
                        if (n3 < n) {
                            n = n3;
                            authorizationInfo2 = authorizationInfo3;
                        }
                    }
                }
            }
        }
        return authorizationInfo2;
    }
    
    synchronized void addPath(final String s) {
        final String path = Util.getPath(s);
        for (int i = 0; i < this.paths.length; ++i) {
            if (this.paths[i].equals(path)) {
                return;
            }
        }
        (this.paths = Util.resizeArray(this.paths, this.paths.length + 1))[this.paths.length - 1] = path;
    }
    
    static AuthorizationInfo[] parseAuthString(final String s, final String s2, final int n) throws ProtocolException {
        int n2 = 0;
        final char[] charArray = s.toCharArray();
        int length = charArray.length;
        AuthorizationInfo[] resizeArray = new AuthorizationInfo[0];
        while (Character.isWhitespace(charArray[length - 1])) {
            --length;
        }
        while (true) {
            final int skipSpace = Util.skipSpace(charArray, n2);
            if (skipSpace == length) {
                return resizeArray;
            }
            int n3 = Util.findSpace(charArray, skipSpace + 1);
            final AuthorizationInfo authorizationInfo = new AuthorizationInfo(s2, n);
            authorizationInfo.Scheme = s.substring(skipSpace, n3);
            int n4 = 1;
            final Vector<NVPair> vector = new Vector<NVPair>();
            while (true) {
                n2 = Util.skipSpace(charArray, n3);
                if (n2 == length) {
                    break;
                }
                if (n4 == 0) {
                    if (charArray[n2] != ',') {
                        throw new ProtocolException("Bad Authentication header format: '" + s + "'\nExpected \",\" at position " + n2);
                    }
                    n2 = Util.skipSpace(charArray, n2 + 1);
                    if (n2 == length) {
                        break;
                    }
                    if (charArray[n2] == ',') {
                        n3 = n2;
                        continue;
                    }
                }
                final int n5 = n2;
                for (n3 = n2 + 1; n3 < length && !Character.isWhitespace(charArray[n3]) && charArray[n3] != '=' && charArray[n3] != ','; ++n3) {}
                if (n4 != 0 && (n3 == length || (charArray[n3] == '=' && (n3 + 1 == length || (charArray[n3 + 1] == '=' && n3 + 2 == length))))) {
                    authorizationInfo.cookie = s.substring(n2, length);
                    n2 = length;
                    break;
                }
                final String substring = s.substring(n2, n3);
                final int skipSpace2 = Util.skipSpace(charArray, n3);
                if (skipSpace2 < length && charArray[skipSpace2] != '=' && charArray[skipSpace2] != ',') {
                    n2 = n5;
                    break;
                }
                String realm;
                if (charArray[skipSpace2] == '=') {
                    int skipSpace3 = Util.skipSpace(charArray, skipSpace2 + 1);
                    if (skipSpace3 == length) {
                        throw new ProtocolException("Bad Authentication header format: " + s + "\nUnexpected EOL after token" + " at position " + (n3 - 1));
                    }
                    if (charArray[skipSpace3] != '\"') {
                        n3 = Util.skipToken(charArray, skipSpace3);
                        if (n3 == skipSpace3) {
                            throw new ProtocolException("Bad Authentication header format: " + s + "\nToken expected at " + "position " + skipSpace3);
                        }
                        realm = s.substring(skipSpace3, n3);
                    }
                    else {
                        n3 = skipSpace3++;
                        do {
                            n3 = s.indexOf(34, n3 + 1);
                        } while (n3 != -1 && s.charAt(n3 - 1) == '\\');
                        if (n3 == -1) {
                            throw new ProtocolException("Bad Authentication header format: " + s + "\nClosing <\"> for " + "quoted-string starting at position " + skipSpace3 + " not found");
                        }
                        realm = Util.dequoteString(s.substring(skipSpace3, n3));
                        ++n3;
                    }
                }
                else {
                    realm = null;
                }
                if (substring.equalsIgnoreCase("realm")) {
                    authorizationInfo.Realm = realm;
                }
                else {
                    vector.addElement(new NVPair(substring, realm));
                }
                n4 = 0;
            }
            if (!vector.isEmpty()) {
                vector.copyInto(authorizationInfo.auth_params = new NVPair[vector.size()]);
            }
            if (authorizationInfo.Realm == null) {
                authorizationInfo.Realm = "";
            }
            resizeArray = Util.resizeArray(resizeArray, resizeArray.length + 1);
            resizeArray[resizeArray.length - 1] = authorizationInfo;
        }
    }
    
    public final String getHost() {
        return this.Host;
    }
    
    public final int getPort() {
        return this.Port;
    }
    
    public final String getScheme() {
        return this.Scheme;
    }
    
    public final String getRealm() {
        return this.Realm;
    }
    
    public final String getCookie() {
        return this.cookie;
    }
    
    public final void setCookie(final String cookie) {
        this.cookie = cookie;
    }
    
    public final NVPair[] getParams() {
        return Util.resizeArray(this.auth_params, this.auth_params.length);
    }
    
    public final void setParams(final NVPair[] array) {
        if (array != null) {
            this.auth_params = Util.resizeArray(array, array.length);
        }
        else {
            this.auth_params = new NVPair[0];
        }
    }
    
    public final Object getExtraInfo() {
        return this.extra_info;
    }
    
    public final void setExtraInfo(final Object extra_info) {
        this.extra_info = extra_info;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer(100);
        sb.append(this.Scheme);
        sb.append(" ");
        if (this.cookie != null) {
            sb.append(this.cookie);
        }
        else {
            if (this.Realm.length() > 0) {
                sb.append("realm=\"");
                sb.append(Util.quoteString(this.Realm, "\\\""));
                sb.append('\"');
            }
            for (int i = 0; i < this.auth_params.length; ++i) {
                sb.append(',');
                sb.append(this.auth_params[i].getName());
                sb.append("=");
                if (this.auth_params[i].quoteValue()) {
                    sb.append('\"');
                    sb.append(Util.quoteString(this.auth_params[i].getValue(), "\\\""));
                    sb.append('\"');
                }
                else {
                    sb.append(this.auth_params[i].getValue());
                }
            }
        }
        return sb.toString();
    }
    
    public int hashCode() {
        return (this.Host + this.Scheme.toLowerCase() + this.Realm).hashCode();
    }
    
    public boolean equals(final Object o) {
        if (o != null && o instanceof AuthorizationInfo) {
            final AuthorizationInfo authorizationInfo = (AuthorizationInfo)o;
            if (this.Host.equals(authorizationInfo.Host) && this.Port == authorizationInfo.Port && this.Scheme.equalsIgnoreCase(authorizationInfo.Scheme) && this.Realm.equals(authorizationInfo.Realm)) {
                return true;
            }
        }
        return false;
    }
    
    public Object clone() {
        AuthorizationInfo authorizationInfo;
        try {
            authorizationInfo = (AuthorizationInfo)super.clone();
            authorizationInfo.auth_params = Util.resizeArray(this.auth_params, this.auth_params.length);
            try {
                authorizationInfo.extra_info = this.extra_info.getClass().getMethod("clone", (Class<?>[])null).invoke(this.extra_info, (Object[])null);
            }
            catch (Throwable t) {}
            authorizationInfo.paths = new String[this.paths.length];
            System.arraycopy(this.paths, 0, authorizationInfo.paths, 0, this.paths.length);
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError(ex.toString());
        }
        return authorizationInfo;
    }
    
    static {
        AuthorizationInfo.CntxtList = new Hashtable();
        AuthorizationInfo.AuthHandler = new DefaultAuthHandler();
        AuthorizationInfo.CntxtList.put(HTTPConnection.getDefaultContext(), new Hashtable<Object, Hashtable>());
    }
}
