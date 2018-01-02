// 
// Decompiled by Procyon v0.5.30
// 

package HTTPClient;

import java.net.InetAddress;
import java.util.StringTokenizer;
import java.util.Vector;
import java.io.IOException;
import ie.brd.crypto.algorithms.DES.DESAlgorithm;

public class DefaultAuthHandler implements AuthorizationHandler, GlobalConstants
{
    private static final byte[] NUL;
    private static final byte[] zeros;
    private static AuthorizationPrompter prompter;
    private static DESAlgorithm DES;
    private static byte[] digest_secret;
    private static String[] ordering;
    
    public AuthorizationInfo[] orderChallenges(final AuthorizationInfo[] array, final RoRequest roRequest, final RoResponse roResponse, final boolean b) {
        final AuthorizationInfo[] array2 = new AuthorizationInfo[array.length];
        int n = 0;
        for (int i = 0; i < DefaultAuthHandler.ordering.length; ++i) {
            for (int j = 0; j < array.length; ++j) {
                if (array[j] != null && array[j].getScheme().equalsIgnoreCase(DefaultAuthHandler.ordering[i])) {
                    array2[n++] = array[j];
                    array[j] = null;
                }
            }
        }
        for (int k = 0; k < array.length; ++k) {
            if (array[k] != null) {
                array2[n++] = array[k];
            }
        }
        return array2;
    }
    
    public AuthorizationInfo fixupAuthInfo(final AuthorizationInfo authorizationInfo, final Request request, final AuthorizationInfo authorizationInfo2, final RoResponse roResponse, final boolean b) throws AuthSchemeNotImplException {
        if (authorizationInfo.getScheme().equalsIgnoreCase("Basic") || authorizationInfo.getScheme().equalsIgnoreCase("SOCKS5")) {
            return authorizationInfo;
        }
        if (!authorizationInfo.getScheme().equalsIgnoreCase("Digest") && !authorizationInfo.getScheme().equalsIgnoreCase("NTLM")) {
            throw new AuthSchemeNotImplException(authorizationInfo.getScheme());
        }
        if (GlobalConstants.DebugAuth) {
            Util.logLine("Auth:  fixing up Authorization for host " + authorizationInfo.getHost() + ":" + authorizationInfo.getPort() + "; scheme: " + authorizationInfo.getScheme() + "; realm: " + authorizationInfo.getRealm());
        }
        if (authorizationInfo.getScheme().equalsIgnoreCase("Digest")) {
            return digest_fixup(authorizationInfo, request, authorizationInfo2, roResponse, b);
        }
        return ntlm_fixup(authorizationInfo, request, authorizationInfo2, roResponse);
    }
    
    public AuthorizationInfo getAuthorization(final AuthorizationInfo authorizationInfo, final RoRequest roRequest, final RoResponse roResponse, final boolean b) throws AuthSchemeNotImplException {
        if (GlobalConstants.DebugAuth) {
            Util.logLine("Auth:  Requesting Authorization for host " + authorizationInfo.getHost() + ":" + authorizationInfo.getPort() + "; challenge: " + authorizationInfo);
        }
        if (!authorizationInfo.getScheme().equalsIgnoreCase("Basic") && !authorizationInfo.getScheme().equalsIgnoreCase("Digest") && !authorizationInfo.getScheme().equalsIgnoreCase("NTLM") && !authorizationInfo.getScheme().equalsIgnoreCase("SOCKS5")) {
            throw new AuthSchemeNotImplException(authorizationInfo.getScheme());
        }
        if (authorizationInfo.getScheme().equalsIgnoreCase("Digest")) {
            final AuthorizationInfo digest_check_stale = digest_check_stale(authorizationInfo, roRequest, roResponse);
            if (digest_check_stale != null) {
                return digest_check_stale;
            }
        }
        else if (authorizationInfo.getScheme().equalsIgnoreCase("NTLM")) {
            final AuthorizationInfo ntlm_check_step2 = ntlm_check_step2(authorizationInfo, roRequest, roResponse);
            if (ntlm_check_step2 != null) {
                return ntlm_check_step2;
            }
        }
        if (!roRequest.allowUI() || DefaultAuthHandler.prompter == null) {
            return null;
        }
        final NVPair usernamePassword = DefaultAuthHandler.prompter.getUsernamePassword(authorizationInfo);
        if (usernamePassword == null) {
            return null;
        }
        AuthorizationInfo authorizationInfo2;
        if (authorizationInfo.getScheme().equalsIgnoreCase("Basic")) {
            authorizationInfo2 = basic_gen_auth_info(authorizationInfo.getHost(), authorizationInfo.getPort(), authorizationInfo.getRealm(), usernamePassword.getName(), usernamePassword.getValue());
        }
        else if (authorizationInfo.getScheme().equalsIgnoreCase("Digest")) {
            authorizationInfo2 = digest_fixup(digest_gen_auth_info(authorizationInfo.getHost(), authorizationInfo.getPort(), authorizationInfo.getRealm(), usernamePassword.getName(), usernamePassword.getValue(), roRequest.getConnection().getContext()), roRequest, authorizationInfo, null, b);
        }
        else if (authorizationInfo.getScheme().equalsIgnoreCase("NTLM")) {
            authorizationInfo2 = ntlm_fixup(ntlm_gen_auth_info(authorizationInfo.getHost(), authorizationInfo.getPort(), authorizationInfo.getRealm(), usernamePassword.getName(), usernamePassword.getValue()), roRequest, authorizationInfo, null);
        }
        else {
            authorizationInfo2 = socks5_gen_auth_info(authorizationInfo.getHost(), authorizationInfo.getPort(), authorizationInfo.getRealm(), usernamePassword.getName(), usernamePassword.getValue());
        }
        System.gc();
        if (GlobalConstants.DebugAuth) {
            Util.logLine("Auth:  Got Authorization");
        }
        return authorizationInfo2;
    }
    
    public void handleAuthHeaders(final Response response, final RoRequest roRequest, final AuthorizationInfo authorizationInfo, final AuthorizationInfo authorizationInfo2) throws IOException {
        String header = response.getHeader("Authentication-Info");
        String header2 = response.getHeader("Proxy-Authentication-Info");
        if (header == null && authorizationInfo != null && hasParam(authorizationInfo.getParams(), "qop", "auth-int")) {
            header = "";
        }
        if (header2 == null && authorizationInfo2 != null && hasParam(authorizationInfo2.getParams(), "qop", "auth-int")) {
            header2 = "";
        }
        try {
            handleAuthInfo(header, "Authentication-Info", authorizationInfo, response, roRequest, true);
            handleAuthInfo(header2, "Proxy-Authentication-Info", authorizationInfo2, response, roRequest, true);
        }
        catch (ParseException ex) {
            throw new IOException(ex.toString());
        }
    }
    
    public void handleAuthTrailers(final Response response, final RoRequest roRequest, final AuthorizationInfo authorizationInfo, final AuthorizationInfo authorizationInfo2) throws IOException {
        final String trailer = response.getTrailer("Authentication-Info");
        final String trailer2 = response.getTrailer("Proxy-Authentication-Info");
        try {
            handleAuthInfo(trailer, "Authentication-Info", authorizationInfo, response, roRequest, false);
            handleAuthInfo(trailer2, "Proxy-Authentication-Info", authorizationInfo2, response, roRequest, false);
        }
        catch (ParseException ex) {
            throw new IOException(ex.toString());
        }
    }
    
    private static void handleAuthInfo(final String s, final String s2, final AuthorizationInfo authorizationInfo, final Response response, final RoRequest roRequest, final boolean b) throws ParseException, IOException {
        if (s == null) {
            return;
        }
        final Vector header = Util.parseHeader(s);
        final HttpHeaderElement element;
        if (handle_nextnonce(authorizationInfo, roRequest, element = Util.getElement(header, "nextnonce"))) {
            header.removeElement(element);
        }
        final HttpHeaderElement element2;
        if (handle_discard(authorizationInfo, roRequest, element2 = Util.getElement(header, "discard"))) {
            header.removeElement(element2);
        }
        if (b) {
            HttpHeaderElement element3 = null;
            if (header != null && (element3 = Util.getElement(header, "qop")) != null && element3.getValue() != null) {
                handle_rspauth(authorizationInfo, response, roRequest, header, s2);
            }
            else if (authorizationInfo != null && ((Util.hasToken(response.getHeader("Trailer"), s2) && hasParam(authorizationInfo.getParams(), "qop", null)) || hasParam(authorizationInfo.getParams(), "qop", "auth-int"))) {
                handle_rspauth(authorizationInfo, response, roRequest, null, s2);
            }
            else if ((header != null && element3 == null && header.contains(new HttpHeaderElement("digest"))) || (Util.hasToken(response.getHeader("Trailer"), s2) && authorizationInfo != null && !hasParam(authorizationInfo.getParams(), "qop", null))) {
                handle_digest(authorizationInfo, response, roRequest, s2);
            }
        }
        if (header.size() > 0) {
            response.setHeader(s2, Util.assembleHeader(header));
        }
        else {
            response.deleteHeader(s2);
        }
    }
    
    private static final boolean hasParam(final NVPair[] array, final String s, final String s2) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].getName().equalsIgnoreCase(s) && (s2 == null || array[i].getValue().equalsIgnoreCase(s2))) {
                return true;
            }
        }
        return false;
    }
    
    public void addAuthorizationInfo(final String s, final String s2, final int n, final String s3, final Object o, final Object o2, final Object o3) throws AuthSchemeNotImplException {
        AuthorizationInfo authorizationInfo;
        if (s.equalsIgnoreCase("Basic")) {
            authorizationInfo = basic_gen_auth_info(s2, n, s3, (String)o, (String)o2);
        }
        else if (s.equalsIgnoreCase("Digest")) {
            authorizationInfo = digest_gen_auth_info(s2, n, s3, (String)o, (String)o2, o3);
        }
        else if (s.equalsIgnoreCase("NTLM")) {
            authorizationInfo = ntlm_gen_auth_info(s2, n, s3, (String)o, (String)o2);
        }
        else {
            if (!s.equalsIgnoreCase("SOCKS5")) {
                throw new AuthSchemeNotImplException(s);
            }
            authorizationInfo = socks5_gen_auth_info(s2, n, s3, (String)o, (String)o2);
        }
        AuthorizationInfo.addAuthorization(authorizationInfo, o3);
    }
    
    private static AuthorizationInfo basic_gen_auth_info(final String s, final int n, final String s2, final String s3, final String s4) {
        return new AuthorizationInfo(s, n, "Basic", s2, Codecs.base64Encode(s3 + ":" + s4));
    }
    
    private static AuthorizationInfo socks5_gen_auth_info(final String s, final int n, final String s2, final String s3, final String s4) {
        return new AuthorizationInfo(s, n, "SOCKS5", s2, new NVPair[] { new NVPair(s3, s4) }, null);
    }
    
    private static AuthorizationInfo digest_gen_auth_info(final String s, final int n, final String s2, final String s3, final String s4, final Object o) {
        final String[] array = { new MD5(s3 + ":" + s2 + ":" + s4).asHex(), null };
        final AuthorizationInfo authorization = AuthorizationInfo.getAuthorization(s, n, "Digest", s2, o);
        NVPair[] params;
        if (authorization == null) {
            params = new NVPair[] { new NVPair("username", s3), new NVPair("uri", ""), new NVPair("nonce", ""), new NVPair("response", "") };
        }
        else {
            params = authorization.getParams();
            for (int i = 0; i < params.length; ++i) {
                if (params[i].getName().equalsIgnoreCase("username")) {
                    params[i] = new NVPair("username", s3);
                    break;
                }
            }
        }
        return new AuthorizationInfo(s, n, "Digest", s2, params, array);
    }
    
    private static AuthorizationInfo digest_fixup(final AuthorizationInfo authorizationInfo, final RoRequest roRequest, final AuthorizationInfo authorizationInfo2, final RoResponse roResponse, final boolean b) throws AuthSchemeNotImplException {
        int n = -1;
        int n2 = -1;
        int n3 = -1;
        int n4 = -1;
        int n5 = -1;
        int n6 = -1;
        int n7 = -1;
        NVPair[] params = null;
        if (authorizationInfo2 != null) {
            params = authorizationInfo2.getParams();
            for (int i = 0; i < params.length; ++i) {
                final String lowerCase = params[i].getName().toLowerCase();
                if (lowerCase.equals("domain")) {
                    n = i;
                }
                else if (lowerCase.equals("nonce")) {
                    n2 = i;
                }
                else if (lowerCase.equals("opaque")) {
                    n4 = i;
                }
                else if (lowerCase.equals("algorithm")) {
                    n3 = i;
                }
                else if (lowerCase.equals("stale")) {
                    n5 = i;
                }
                else if (lowerCase.equals("digest-required")) {
                    n6 = i;
                }
                else if (lowerCase.equals("qop")) {
                    n7 = i;
                }
            }
        }
        int n8 = -1;
        int n9 = -1;
        int n10 = -1;
        int n11 = -1;
        int n12 = -1;
        int n13 = -1;
        int n14 = -1;
        int length = -1;
        int n15 = -1;
        int n16 = -1;
        NVPair[] params2;
        final String[] extraInfo;
        synchronized (authorizationInfo) {
            params2 = authorizationInfo.getParams();
            for (int j = 0; j < params2.length; ++j) {
                final String lowerCase2 = params2[j].getName().toLowerCase();
                if (lowerCase2.equals("uri")) {
                    n8 = j;
                }
                else if (!lowerCase2.equals("username")) {
                    if (lowerCase2.equals("algorithm")) {
                        n9 = j;
                    }
                    else if (lowerCase2.equals("nonce")) {
                        n11 = j;
                    }
                    else if (lowerCase2.equals("cnonce")) {
                        n12 = j;
                    }
                    else if (lowerCase2.equals("nc")) {
                        n13 = j;
                    }
                    else if (lowerCase2.equals("response")) {
                        n10 = j;
                    }
                    else if (lowerCase2.equals("opaque")) {
                        n14 = j;
                    }
                    else if (lowerCase2.equals("digest")) {
                        length = j;
                    }
                    else if (lowerCase2.equals("digest-required")) {
                        n15 = j;
                    }
                    else if (lowerCase2.equals("qop")) {
                        n16 = j;
                    }
                }
            }
            if (n9 != -1 && !params2[n9].getValue().equalsIgnoreCase("MD5") && !params2[n9].getValue().equalsIgnoreCase("MD5-sess")) {
                throw new AuthSchemeNotImplException("Digest auth scheme: Algorithm " + params2[n9].getValue() + " not implemented");
            }
            if (n3 != -1 && !params[n3].getValue().equalsIgnoreCase("MD5") && !params[n3].getValue().equalsIgnoreCase("MD5-sess")) {
                throw new AuthSchemeNotImplException("Digest auth scheme: Algorithm " + params[n3].getValue() + " not implemented");
            }
            params2[n8] = new NVPair("uri", roRequest.getRequestURI());
            final String value = params2[n11].getValue();
            if (n2 != -1 && !value.equals(params[n2].getValue())) {
                params2[n11] = params[n2];
            }
            if (n4 != -1) {
                if (n14 == -1) {
                    params2 = Util.resizeArray(params2, params2.length + 1);
                    n14 = params2.length - 1;
                }
                params2[n14] = params[n4];
            }
            if (n3 != -1) {
                if (n9 == -1) {
                    params2 = Util.resizeArray(params2, params2.length + 1);
                    n9 = params2.length - 1;
                }
                params2[n9] = params[n3];
            }
            if (n7 != -1 || (n3 != -1 && params[n3].getValue().equalsIgnoreCase("MD5-sess"))) {
                if (n12 == -1) {
                    params2 = Util.resizeArray(params2, params2.length + 1);
                    n12 = params2.length - 1;
                }
                if (DefaultAuthHandler.digest_secret == null) {
                    DefaultAuthHandler.digest_secret = gen_random_bytes(20);
                }
                final long currentTimeMillis = System.currentTimeMillis();
                final byte[] array = { (byte)(currentTimeMillis & 0xFFL), (byte)(currentTimeMillis >> 8 & 0xFFL), (byte)(currentTimeMillis >> 16 & 0xFFL), (byte)(currentTimeMillis >> 24 & 0xFFL), (byte)(currentTimeMillis >> 32 & 0xFFL), (byte)(currentTimeMillis >> 40 & 0xFFL), (byte)(currentTimeMillis >> 48 & 0xFFL), (byte)(currentTimeMillis >> 56 & 0xFFL) };
                final MD5 md5 = new MD5(DefaultAuthHandler.digest_secret);
                md5.Update(array);
                params2[n12] = new NVPair("cnonce", md5.asHex());
            }
            if (n7 != -1) {
                if (n16 == -1) {
                    params2 = Util.resizeArray(params2, params2.length + 1);
                    n16 = params2.length - 1;
                }
                final String[] splitList = Util.splitList(params[n7].getValue(), ",");
                String s = null;
                for (int k = 0; k < splitList.length; ++k) {
                    if (splitList[k].equalsIgnoreCase("auth-int") && roRequest.getStream() == null) {
                        s = "auth-int";
                        break;
                    }
                    if (splitList[k].equalsIgnoreCase("auth")) {
                        s = "auth";
                    }
                }
                if (s == null) {
                    for (int l = 0; l < splitList.length; ++l) {
                        if (splitList[l].equalsIgnoreCase("auth-int")) {
                            throw new AuthSchemeNotImplException("Digest auth scheme: Can't comply with qop option 'auth-int' because data not available");
                        }
                    }
                    throw new AuthSchemeNotImplException("Digest auth scheme: None of the available qop options '" + params[n7].getValue() + "' implemented");
                }
                params2[n16] = new NVPair("qop", s, false);
            }
            if (n16 != -1) {
                if (n13 == -1) {
                    params2 = Util.resizeArray(params2, params2.length + 1);
                    n13 = params2.length - 1;
                    params2[n13] = new NVPair("nc", "00000001", false);
                }
                else if (value.equals(params2[n11].getValue())) {
                    final String hexString = Long.toHexString(Long.parseLong(params2[n13].getValue(), 16) + 1L);
                    params2[n13] = new NVPair("nc", "00000000".substring(hexString.length()) + hexString, false);
                }
                else {
                    params2[n13] = new NVPair("nc", "00000001", false);
                }
            }
            extraInfo = (String[])authorizationInfo.getExtraInfo();
            if (authorizationInfo2 != null && (n5 == -1 || !params[n5].getValue().equalsIgnoreCase("true")) && n9 != -1 && params2[n9].getValue().equalsIgnoreCase("MD5-sess")) {
                extraInfo[1] = new MD5(extraInfo[0] + ":" + params2[n11].getValue() + ":" + params2[n12].getValue()).asHex();
                authorizationInfo.setExtraInfo(extraInfo);
            }
            authorizationInfo.setParams(params2);
        }
        String s2;
        if (n9 != -1 && params2[n9].getValue().equalsIgnoreCase("MD5-sess")) {
            s2 = extraInfo[1];
        }
        else {
            s2 = extraInfo[0];
        }
        String s3 = roRequest.getMethod() + ":" + params2[n8].getValue();
        if (n16 != -1 && params2[n16].getValue().equalsIgnoreCase("auth-int")) {
            final MD5 md6 = new MD5();
            md6.Update((roRequest.getData() == null) ? DefaultAuthHandler.NUL : roRequest.getData());
            s3 = s3 + ":" + md6.asHex();
        }
        final String hex = new MD5(s3).asHex();
        String s4;
        if (n16 == -1) {
            s4 = new MD5(s2 + ":" + params2[n11].getValue() + ":" + hex).asHex();
        }
        else {
            s4 = new MD5(s2 + ":" + params2[n11].getValue() + ":" + params2[n13].getValue() + ":" + params2[n12].getValue() + ":" + params2[n16].getValue() + ":" + hex).asHex();
        }
        params2[n10] = new NVPair("response", s4);
        boolean b2 = false;
        if (n6 != -1 && (params[n6].getValue() == null || params[n6].getValue().equalsIgnoreCase("true"))) {
            b2 = true;
        }
        AuthorizationInfo authorizationInfo3;
        if ((b2 || length != -1) && roRequest.getStream() == null) {
            NVPair[] array2;
            if (length == -1) {
                array2 = Util.resizeArray(params2, params2.length + 1);
                length = params2.length;
            }
            else {
                array2 = params2;
            }
            array2[length] = new NVPair("digest", calc_digest(roRequest, extraInfo[0], params2[n11].getValue()));
            if (n15 == -1) {
                final int length2 = array2.length;
                array2 = Util.resizeArray(array2, array2.length + 1);
                array2[length2] = new NVPair("digest-required", "true");
            }
            authorizationInfo3 = new AuthorizationInfo(authorizationInfo.getHost(), authorizationInfo.getPort(), authorizationInfo.getScheme(), authorizationInfo.getRealm(), array2, extraInfo);
        }
        else if (b2) {
            authorizationInfo3 = null;
        }
        else {
            authorizationInfo3 = new AuthorizationInfo(authorizationInfo.getHost(), authorizationInfo.getPort(), authorizationInfo.getScheme(), authorizationInfo.getRealm(), params2, extraInfo);
        }
        if (n != -1) {
            URI uri = null;
            try {
                uri = new URI(roRequest.getConnection().getProtocol(), roRequest.getConnection().getHost(), roRequest.getConnection().getPort(), roRequest.getRequestURI());
            }
            catch (ParseException ex) {}
            final StringTokenizer stringTokenizer = new StringTokenizer(params[n].getValue());
            while (stringTokenizer.hasMoreTokens()) {
                URI uri2;
                try {
                    uri2 = new URI(uri, stringTokenizer.nextToken());
                }
                catch (ParseException ex2) {
                    continue;
                }
                AuthorizationInfo authorization = AuthorizationInfo.getAuthorization(uri2.getHost(), uri2.getPort(), authorizationInfo.getScheme(), authorizationInfo.getRealm(), roRequest.getConnection().getContext());
                if (authorization == null) {
                    params2[n8] = new NVPair("uri", uri2.getPath());
                    authorization = new AuthorizationInfo(uri2.getHost(), uri2.getPort(), authorizationInfo.getScheme(), authorizationInfo.getRealm(), params2, extraInfo);
                    AuthorizationInfo.addAuthorization(authorization);
                }
                if (!b) {
                    authorization.addPath(uri2.getPath());
                }
            }
        }
        else if (!b && authorizationInfo2 != null) {
            final AuthorizationInfo authorization2 = AuthorizationInfo.getAuthorization(authorizationInfo2.getHost(), authorizationInfo2.getPort(), authorizationInfo.getScheme(), authorizationInfo.getRealm(), roRequest.getConnection().getContext());
            if (authorization2 != null) {
                authorization2.addPath("/");
            }
        }
        return authorizationInfo3;
    }
    
    private static AuthorizationInfo digest_check_stale(final AuthorizationInfo authorizationInfo, final RoRequest roRequest, final RoResponse roResponse) throws AuthSchemeNotImplException {
        AuthorizationInfo authorization = null;
        final NVPair[] params = authorizationInfo.getParams();
        int i = 0;
        while (i < params.length) {
            if (params[i].getName().equalsIgnoreCase("stale") && params[i].getValue().equalsIgnoreCase("true")) {
                authorization = AuthorizationInfo.getAuthorization(authorizationInfo, roRequest, roResponse, false, false);
                if (authorization != null) {
                    return digest_fixup(authorization, roRequest, authorizationInfo, roResponse, false);
                }
                break;
            }
            else {
                ++i;
            }
        }
        return authorization;
    }
    
    private static boolean handle_nextnonce(final AuthorizationInfo authorizationInfo, final RoRequest roRequest, final HttpHeaderElement httpHeaderElement) {
        if (authorizationInfo == null || httpHeaderElement == null || httpHeaderElement.getValue() == null) {
            return false;
        }
        AuthorizationInfo authorization;
        try {
            authorization = AuthorizationInfo.getAuthorization(authorizationInfo, roRequest, null, false, false);
        }
        catch (AuthSchemeNotImplException ex) {
            authorization = authorizationInfo;
        }
        synchronized (authorization) {
            authorization.setParams(Util.setValue(Util.setValue(authorization.getParams(), "nonce", httpHeaderElement.getValue()), "nc", "00000000", false));
        }
        return true;
    }
    
    private static boolean handle_digest(final AuthorizationInfo authorizationInfo, final Response response, final RoRequest roRequest, final String s) throws IOException {
        if (authorizationInfo == null) {
            return false;
        }
        final NVPair[] params = authorizationInfo.getParams();
        final VerifyDigest verifyDigest = new VerifyDigest(((String[])authorizationInfo.getExtraInfo())[0], Util.getValue(params, "nonce"), roRequest.getMethod(), Util.getValue(params, "uri"), s, response);
        if (response.hasEntity()) {
            if (GlobalConstants.DebugAuth) {
                Util.logLine("Auth:  pushing md5-check-stream to verify digest from " + s);
            }
            response.inp_stream = new MD5InputStream(response.inp_stream, verifyDigest);
        }
        else {
            if (GlobalConstants.DebugAuth) {
                Util.logLine("Auth:  verifying digest from " + s);
            }
            verifyDigest.verifyHash(new MD5().Final(), 0L);
        }
        return true;
    }
    
    private static boolean handle_rspauth(final AuthorizationInfo authorizationInfo, final Response response, final RoRequest roRequest, final Vector vector, final String s) throws IOException {
        if (authorizationInfo == null) {
            return false;
        }
        final NVPair[] params = authorizationInfo.getParams();
        int n = -1;
        int n2 = -1;
        int n3 = -1;
        int n4 = -1;
        int n5 = -1;
        for (int i = 0; i < params.length; ++i) {
            final String lowerCase = params[i].getName().toLowerCase();
            if (lowerCase.equals("uri")) {
                n = i;
            }
            else if (lowerCase.equals("algorithm")) {
                n2 = i;
            }
            else if (lowerCase.equals("nonce")) {
                n3 = i;
            }
            else if (lowerCase.equals("cnonce")) {
                n4 = i;
            }
            else if (lowerCase.equals("nc")) {
                n5 = i;
            }
        }
        final VerifyRspAuth verifyRspAuth = new VerifyRspAuth(params[n].getValue(), ((String[])authorizationInfo.getExtraInfo())[0], (n2 == -1) ? null : params[n2].getValue(), params[n3].getValue(), (n4 == -1) ? "" : params[n4].getValue(), (n5 == -1) ? "" : params[n5].getValue(), s, response);
        final HttpHeaderElement element;
        if (vector != null && (element = Util.getElement(vector, "qop")) != null && element.getValue() != null && (element.getValue().equalsIgnoreCase("auth") || (!response.hasEntity() && element.getValue().equalsIgnoreCase("auth-int")))) {
            if (GlobalConstants.DebugAuth) {
                Util.logLine("Auth:  verifying rspauth from " + s);
            }
            verifyRspAuth.verifyHash(new MD5().Final(), 0L);
        }
        else {
            if (GlobalConstants.DebugAuth) {
                Util.logLine("Auth:  pushing md5-check-stream to verify rspauth from " + s);
            }
            response.inp_stream = new MD5InputStream(response.inp_stream, verifyRspAuth);
        }
        return true;
    }
    
    private static String calc_digest(final RoRequest roRequest, final String s, final String s2) {
        if (roRequest.getStream() != null) {
            return "";
        }
        int n = -1;
        int n2 = -1;
        int n3 = -1;
        int n4 = -1;
        int n5 = -1;
        for (int i = 0; i < roRequest.getHeaders().length; ++i) {
            final String name = roRequest.getHeaders()[i].getName();
            if (name.equalsIgnoreCase("Content-type")) {
                n = i;
            }
            else if (name.equalsIgnoreCase("Content-Encoding")) {
                n2 = i;
            }
            else if (name.equalsIgnoreCase("Last-Modified")) {
                n3 = i;
            }
            else if (name.equalsIgnoreCase("Expires")) {
                n4 = i;
            }
            else if (name.equalsIgnoreCase("Date")) {
                n5 = i;
            }
        }
        final NVPair[] headers = roRequest.getHeaders();
        final byte[] array = (roRequest.getData() == null) ? DefaultAuthHandler.NUL : roRequest.getData();
        final MD5 md5 = new MD5();
        md5.Update(array);
        final String string = s + ":" + s2 + ":" + roRequest.getMethod() + ":" + ((n5 == -1) ? "" : headers[n5].getValue()) + ":" + new MD5(roRequest.getRequestURI() + ":" + ((n == -1) ? "" : headers[n].getValue()) + ":" + array.length + ":" + ((n2 == -1) ? "" : headers[n2].getValue()) + ":" + ((n3 == -1) ? "" : headers[n3].getValue()) + ":" + ((n4 == -1) ? "" : headers[n4].getValue())).asHex() + ":" + md5.asHex();
        if (GlobalConstants.DebugAuth) {
            Util.logLine("Auth:  Entity-Info: '" + roRequest.getRequestURI() + ":" + ((n == -1) ? "" : headers[n].getValue()) + ":" + array.length + ":" + ((n2 == -1) ? "" : headers[n2].getValue()) + ":" + ((n3 == -1) ? "" : headers[n3].getValue()) + ":" + ((n4 == -1) ? "" : headers[n4].getValue()) + "'");
            Util.logLine("Auth:  Entity-Body: '" + md5.asHex() + "'");
            Util.logLine("Auth:  Entity-Digest: '" + string + "'");
        }
        return new MD5(string).asHex();
    }
    
    private static boolean handle_discard(final AuthorizationInfo authorizationInfo, final RoRequest roRequest, final HttpHeaderElement httpHeaderElement) {
        if (httpHeaderElement != null && authorizationInfo != null) {
            AuthorizationInfo.removeAuthorization(authorizationInfo, roRequest.getConnection().getContext());
            return true;
        }
        return false;
    }
    
    private static byte[] gen_random_bytes(final int n) {
        final byte[] array = new byte[n];
        try {
            final long freeMemory = Runtime.getRuntime().freeMemory();
            array[0] = (byte)(freeMemory & 0xFFL);
            array[1] = (byte)(freeMemory >> 8 & 0xFFL);
            final int hashCode = array.hashCode();
            array[2] = (byte)(hashCode & 0xFF);
            array[3] = (byte)(hashCode >> 8 & 0xFF);
            array[4] = (byte)(hashCode >> 16 & 0xFF);
            array[5] = (byte)(hashCode >> 24 & 0xFF);
            final long currentTimeMillis = System.currentTimeMillis();
            array[6] = (byte)(currentTimeMillis & 0xFFL);
            array[7] = (byte)(currentTimeMillis >> 8 & 0xFFL);
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        return array;
    }
    
    private static AuthorizationInfo ntlm_gen_auth_info(final String s, final int n, final String s2, String substring, final String s3) {
        final byte[] calc_lm_hpw = calc_lm_hpw(s3);
        final byte[] calc_ntcr_hpw = calc_ntcr_hpw(s3);
        String s4 = null;
        try {
            s4 = System.getProperty("HTTPClient.defAuthHandler.NTLM.host");
        }
        catch (SecurityException ex) {}
        if (s4 == null) {
            try {
                s4 = InetAddress.getLocalHost().getHostName();
            }
            catch (Exception ex2) {}
        }
        if (s4 == null) {
            s4 = "localhost";
        }
        final int index = s4.indexOf(46);
        if (index != -1) {
            s4 = s4.substring(0, index);
        }
        String s5 = null;
        final int index2;
        if ((index2 = substring.indexOf(92)) != -1) {
            s5 = substring.substring(0, index2);
        }
        else {
            try {
                s5 = System.getProperty("HTTPClient.defAuthHandler.NTLM.domain");
            }
            catch (SecurityException ex3) {}
            if (s5 == null) {
                s5 = s4;
            }
        }
        substring = substring.substring(index2 + 1);
        return new AuthorizationInfo(s, n, "NTLM", s2, null, new Object[] { substring, s4.toUpperCase().trim(), s5.toUpperCase().trim(), calc_lm_hpw, calc_ntcr_hpw });
    }
    
    private static AuthorizationInfo ntlm_fixup(final AuthorizationInfo authorizationInfo, final RoRequest roRequest, final AuthorizationInfo authorizationInfo2, final RoResponse roResponse) throws AuthSchemeNotImplException {
        if (authorizationInfo2 == null) {
            return authorizationInfo;
        }
        final Object[] extraInfo = (Object[])authorizationInfo.getExtraInfo();
        final String s = (String)extraInfo[0];
        final String s2 = (String)extraInfo[1];
        final String s3 = (String)extraInfo[2];
        final byte[] array = (byte[])extraInfo[3];
        final byte[] array2 = (byte[])extraInfo[4];
        byte[] array3;
        if (authorizationInfo2.getCookie() == null) {
            array3 = new byte[32 + s2.length() + s3.length()];
            Util.getBytes("NTLMSSP", array3, 0);
            array3[8] = 1;
            final int n = 32;
            array3[12] = 7;
            array3[13] = -78;
            final int length = s2.length();
            array3[24] = (byte)length;
            array3[25] = (byte)(length >> 8);
            array3[26] = (byte)length;
            array3[27] = (byte)(length >> 8);
            array3[28] = (byte)n;
            array3[29] = (byte)(n >> 8);
            Util.getBytes(s2, length, array3, n);
            final int n2 = n + length;
            final int length2 = s3.length();
            array3[16] = (byte)length2;
            array3[17] = (byte)(length2 >> 8);
            array3[18] = (byte)length2;
            array3[19] = (byte)(length2 >> 8);
            array3[20] = (byte)n2;
            array3[21] = (byte)(n2 >> 8);
            Util.getBytes(s3, length2, array3, n2);
        }
        else {
            final byte[] base64Decode = Codecs.base64Decode(authorizationInfo2.getCookie().getBytes());
            if (base64Decode.length < 32) {
                throw new AuthSchemeNotImplException("NTLM auth scheme: Received invalid type-2 message (too short).");
            }
            final byte[] array4 = new byte[12];
            System.arraycopy(new String("NTLMSSP").getBytes(), 0, array4, 0, 7);
            array4[7] = 0;
            array4[8] = 2;
            array4[9] = 0;
            array4[11] = (array4[10] = 0);
            for (int i = 0; i < 12; ++i) {
                if (base64Decode[i] != array4[i]) {
                    throw new AuthSchemeNotImplException("NTLM auth scheme: Received invalid type-2 message (Byte " + Integer.toString(i) + " is invalid.");
                }
            }
            boolean b = false;
            if ((base64Decode[20] & 0x1) == 0x1) {
                b = true;
            }
            boolean b2 = false;
            if ((base64Decode[21] & 0x2) == 0x2) {
                b2 = true;
            }
            final byte[] array5 = new byte[8];
            System.arraycopy(base64Decode, 24, array5, 0, 8);
            int length3 = s3.length();
            int length4 = s.length();
            int length5 = s2.length();
            if (b) {
                length3 *= 2;
                length4 *= 2;
                length5 *= 2;
            }
            array3 = new byte[64 + length3 + length4 + length5 + 48];
            System.arraycopy(new String("NTLMSSP").getBytes(), 0, array3, 0, 7);
            array3[7] = 0;
            array3[8] = 3;
            array3[9] = 0;
            array3[11] = (array3[10] = 0);
            array3[12] = 24;
            array3[13] = 0;
            array3[14] = 24;
            array3[15] = 0;
            final int n3 = array3.length - 48;
            array3[16] = (byte)n3;
            array3[17] = (byte)(n3 >> 8);
            array3[18] = (byte)(n3 >> 16);
            array3[19] = (byte)(n3 >> 24);
            array3[20] = 24;
            array3[21] = 0;
            array3[22] = 24;
            array3[23] = 0;
            final int n4 = array3.length - 24;
            array3[24] = (byte)n4;
            array3[25] = (byte)(n4 >> 8);
            array3[26] = (byte)(n4 >> 16);
            array3[27] = (byte)(n4 >> 24);
            array3[28] = (byte)length3;
            array3[29] = (byte)(length3 >> 8);
            array3[30] = (byte)length3;
            array3[31] = (byte)(length3 >> 8);
            array3[32] = 64;
            array3[33] = 0;
            array3[35] = (array3[34] = 0);
            array3[36] = (byte)length4;
            array3[37] = (byte)(length4 >> 8);
            array3[38] = (byte)length4;
            array3[39] = (byte)(length4 >> 8);
            final int n5 = 64 + length3;
            array3[40] = (byte)n5;
            array3[41] = (byte)(n5 >> 8);
            array3[42] = (byte)(n5 >> 16);
            array3[43] = (byte)(n5 >> 24);
            array3[44] = (byte)length5;
            array3[45] = (byte)(length5 >> 8);
            array3[46] = (byte)length5;
            array3[47] = (byte)(length5 >> 8);
            final int n6 = 64 + length3 + length4;
            array3[48] = (byte)n6;
            array3[49] = (byte)(n6 >> 8);
            array3[50] = (byte)(n6 >> 16);
            array3[51] = (byte)(n6 >> 24);
            array3[53] = (array3[52] = 0);
            array3[55] = (array3[54] = 0);
            array3[56] = (byte)array3.length;
            array3[57] = (byte)(array3.length >> 8);
            array3[58] = (byte)(array3.length >> 16);
            array3[59] = (byte)(array3.length >> 24);
            if (b) {
                array3[60] = 1;
            }
            else {
                array3[60] = 2;
            }
            if (b2) {
                array3[61] = 2;
            }
            else {
                array3[61] = 0;
            }
            array3[63] = (array3[62] = 0);
            if (b) {
                writeUnicode(s3, array3, 64);
                writeUnicode(s, array3, n5);
                writeUnicode(s2, array3, n6);
            }
            else {
                System.arraycopy(s3.getBytes(), 0, array3, 64, length3);
                System.arraycopy(s.getBytes(), 0, array3, n5, length4);
                System.arraycopy(s2.getBytes(), 0, array3, n6, length5);
            }
            System.arraycopy(calc_ntcr_resp(array, array5), 0, array3, n3, 24);
            System.arraycopy(calc_ntcr_resp(array2, array5), 0, array3, n4, 24);
        }
        final String cookie = new String(Codecs.base64Encode(array3));
        final AuthorizationInfo authorizationInfo3 = new AuthorizationInfo(authorizationInfo2.getHost(), authorizationInfo2.getPort(), authorizationInfo2.getScheme(), authorizationInfo2.getRealm(), cookie);
        authorizationInfo3.setExtraInfo(extraInfo);
        authorizationInfo.setCookie(cookie);
        return authorizationInfo3;
    }
    
    private static AuthorizationInfo ntlm_check_step2(final AuthorizationInfo authorizationInfo, final RoRequest roRequest, final RoResponse roResponse) throws AuthSchemeNotImplException {
        final String value = Util.getValue(roRequest.getHeaders(), "Authorization");
        final AuthorizationInfo authorization = AuthorizationInfo.getAuthorization(authorizationInfo, roRequest, roResponse, false, false);
        if (authorizationInfo.getCookie() != null && authorization != null && value != null && value.startsWith("NTLM TlRMTVNTUAAB")) {
            return ntlm_fixup(authorization, roRequest, authorizationInfo, null);
        }
        return null;
    }
    
    private static int writeUnicode(final String s, final byte[] array, int n) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            array[n++] = (byte)char1;
            array[n++] = (byte)(char1 >> 8);
        }
        return n;
    }
    
    private static byte[] calc_ntcr_hpw(final String s) {
        final byte[] array = new byte[s.length() * 2];
        int i = 0;
        int n = 0;
        while (i < s.length()) {
            final char char1 = s.charAt(i);
            array[n++] = (byte)(char1 & '\u00ff');
            array[n++] = (byte)(char1 >>> 8);
            ++i;
        }
        return Util.resizeArray(new MD4(array).getHash(), 21);
    }
    
    private static byte[] calc_lm_hpw(String upperCase) {
        upperCase = upperCase.toUpperCase();
        final byte[] array = new byte[14];
        Util.getBytes(upperCase, Math.min(upperCase.length(), 14), array, 0);
        final byte[] array2 = new byte[21];
        final byte[] array3 = { 75, 71, 83, 33, 64, 35, 36, 37 };
        final byte[] array4 = new byte[8];
        DefaultAuthHandler.DES.des_ecb_encrypt(array3, array4, setup_key(array, 0), true);
        System.arraycopy(array4, 0, array2, 0, 8);
        DefaultAuthHandler.DES.des_ecb_encrypt(array3, array4, setup_key(array, 7), true);
        System.arraycopy(array4, 0, array2, 8, 8);
        return array2;
    }
    
    private static byte[] calc_ntcr_resp(final byte[] array, final byte[] array2) {
        final byte[] array3 = new byte[24];
        final byte[] array4 = new byte[8];
        DefaultAuthHandler.DES.des_ecb_encrypt(array2, array4, setup_key(array, 0), true);
        System.arraycopy(array4, 0, array3, 0, 8);
        DefaultAuthHandler.DES.des_ecb_encrypt(array2, array4, setup_key(array, 7), true);
        System.arraycopy(array4, 0, array3, 8, 8);
        DefaultAuthHandler.DES.des_ecb_encrypt(array2, array4, setup_key(array, 14), true);
        System.arraycopy(array4, 0, array3, 16, 8);
        return array3;
    }
    
    private static int[] setup_key(final byte[] array, final int n) {
        final byte[] array2 = new byte[8];
        final int[] array3 = new int[32];
        array2[0] = array[n];
        array2[1] = (byte)(array[n + 0] << 7 | (array[n + 1] & 0xFF) >> 1);
        array2[2] = (byte)(array[n + 1] << 6 | (array[n + 2] & 0xFF) >> 2);
        array2[3] = (byte)(array[n + 2] << 5 | (array[n + 3] & 0xFF) >> 3);
        array2[4] = (byte)(array[n + 3] << 4 | (array[n + 4] & 0xFF) >> 4);
        array2[5] = (byte)(array[n + 4] << 3 | (array[n + 5] & 0xFF) >> 5);
        array2[6] = (byte)(array[n + 5] << 2 | (array[n + 6] & 0xFF) >> 6);
        array2[7] = (byte)(array[n + 6] << 1);
        DefaultAuthHandler.DES.des_set_odd_parity(array2);
        DefaultAuthHandler.DES.des_set_key(array2, array3);
        return array3;
    }
    
    public static AuthorizationPrompter setAuthorizationPrompter(final AuthorizationPrompter prompter) {
        final AuthorizationPrompter prompter2 = DefaultAuthHandler.prompter;
        DefaultAuthHandler.prompter = prompter;
        return prompter2;
    }
    
    private static final byte[] unHex(final String s) {
        final byte[] array = new byte[s.length() / 2];
        for (int i = 0; i < array.length; ++i) {
            array[i] = (byte)(0xFF & Integer.parseInt(s.substring(2 * i, 2 * (i + 1)), 16));
        }
        return array;
    }
    
    private static String hex(final byte[] array) {
        final StringBuffer sb = new StringBuffer(array.length * 3);
        for (int i = 0; i < array.length; ++i) {
            sb.append(Character.forDigit(array[i] >>> 4 & 0xF, 16));
            sb.append(Character.forDigit(array[i] & 0xF, 16));
            sb.append(':');
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
    
    static {
        NUL = new byte[0];
        zeros = new byte[24];
        DefaultAuthHandler.prompter = null;
        DefaultAuthHandler.DES = new DESAlgorithm(false);
        DefaultAuthHandler.digest_secret = null;
        DefaultAuthHandler.ordering = new String[] { "Digest", "NTLM", "Basic" };
    }
    
    private static class VerifyRspAuth implements HashVerifier, GlobalConstants
    {
        private String uri;
        private String HA1;
        private String alg;
        private String nonce;
        private String cnonce;
        private String nc;
        private String hdr;
        private RoResponse resp;
        
        public VerifyRspAuth(final String uri, final String ha1, final String alg, final String nonce, final String cnonce, final String nc, final String hdr, final RoResponse resp) {
            this.uri = uri;
            this.HA1 = ha1;
            this.alg = alg;
            this.nonce = nonce;
            this.cnonce = cnonce;
            this.nc = nc;
            this.hdr = hdr;
            this.resp = resp;
        }
        
        public void verifyHash(byte[] final1, final long n) throws IOException {
            String s = this.resp.getHeader(this.hdr);
            if (s == null) {
                s = this.resp.getTrailer(this.hdr);
            }
            if (s == null) {
                return;
            }
            Vector header;
            try {
                header = Util.parseHeader(s);
            }
            catch (ParseException ex) {
                throw new IOException(ex.toString());
            }
            final HttpHeaderElement element = Util.getElement(header, "qop");
            final String value;
            if (element == null || (value = element.getValue()) == null || (!value.equalsIgnoreCase("auth") && !value.equalsIgnoreCase("auth-int"))) {
                return;
            }
            final HttpHeaderElement element2 = Util.getElement(header, "rspauth");
            if (element2 == null || element2.getValue() == null) {
                return;
            }
            final byte[] access$000 = unHex(element2.getValue());
            final HttpHeaderElement element3 = Util.getElement(header, "cnonce");
            if (element3 != null && element3.getValue() != null && !element3.getValue().equals(this.cnonce)) {
                throw new IOException("Digest auth scheme: received wrong client-nonce '" + element3.getValue() + "' - expected '" + this.cnonce + "'");
            }
            final HttpHeaderElement element4 = Util.getElement(header, "nc");
            if (element4 != null && element4.getValue() != null && !element4.getValue().equals(this.nc)) {
                throw new IOException("Digest auth scheme: received wrong nonce-count '" + element4.getValue() + "' - expected '" + this.nc + "'");
            }
            String s2;
            if (this.alg != null && this.alg.equalsIgnoreCase("MD5-sess")) {
                s2 = new MD5(this.HA1 + ":" + this.nonce + ":" + this.cnonce).asHex();
            }
            else {
                s2 = this.HA1;
            }
            String s3 = ":" + this.uri;
            if (value.equalsIgnoreCase("auth-int")) {
                s3 = s3 + ":" + MD5.asHex(final1);
            }
            final1 = new MD5(s2 + ":" + this.nonce + ":" + this.nc + ":" + this.cnonce + ":" + value + ":" + new MD5(s3).asHex()).Final();
            for (int i = 0; i < final1.length; ++i) {
                if (final1[i] != access$000[i]) {
                    throw new IOException("MD5-Digest mismatch: expected " + hex(access$000) + " but calculated " + hex(final1));
                }
            }
            if (GlobalConstants.DebugAuth) {
                Util.logLine("Auth:  rspauth from " + this.hdr + " successfully verified");
            }
        }
    }
    
    private static class VerifyDigest implements HashVerifier, GlobalConstants
    {
        private String HA1;
        private String nonce;
        private String method;
        private String uri;
        private String hdr;
        private RoResponse resp;
        
        public VerifyDigest(final String ha1, final String nonce, final String method, final String uri, final String hdr, final RoResponse resp) {
            this.HA1 = ha1;
            this.nonce = nonce;
            this.method = method;
            this.uri = uri;
            this.hdr = hdr;
            this.resp = resp;
        }
        
        public void verifyHash(byte[] final1, final long n) throws IOException {
            String s = this.resp.getHeader(this.hdr);
            if (s == null) {
                s = this.resp.getTrailer(this.hdr);
            }
            if (s == null) {
                return;
            }
            Vector header;
            try {
                header = Util.parseHeader(s);
            }
            catch (ParseException ex) {
                throw new IOException(ex.toString());
            }
            final HttpHeaderElement element = Util.getElement(header, "digest");
            if (element == null || element.getValue() == null) {
                return;
            }
            final byte[] access$000 = unHex(element.getValue());
            final1 = new MD5(this.HA1 + ":" + this.nonce + ":" + this.method + ":" + this.header_val("Date", this.resp) + ":" + new MD5(this.uri + ":" + this.header_val("Content-type", this.resp) + ":" + this.header_val("Content-length", this.resp) + ":" + this.header_val("Content-Encoding", this.resp) + ":" + this.header_val("Last-Modified", this.resp) + ":" + this.header_val("Expires", this.resp)).asHex() + ":" + MD5.asHex(final1)).Final();
            for (int i = 0; i < final1.length; ++i) {
                if (final1[i] != access$000[i]) {
                    throw new IOException("MD5-Digest mismatch: expected " + hex(access$000) + " but calculated " + hex(final1));
                }
            }
            if (GlobalConstants.DebugAuth) {
                Util.logLine("Auth:  digest from " + this.hdr + "successfully verified");
            }
        }
        
        private final String header_val(final String s, final RoResponse roResponse) throws IOException {
            final String header = roResponse.getHeader(s);
            final String trailer = roResponse.getTrailer(s);
            return (header != null) ? header : ((trailer != null) ? trailer : "");
        }
    }
}
