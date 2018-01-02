// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.auth;

import org.apache.commons.httpclient.HttpConstants;
import java.security.MessageDigest;
import java.util.Map;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.Credentials;

public class DigestScheme extends RFC2617Scheme
{
    private static final char[] HEXADECIMAL;
    
    static {
        HEXADECIMAL = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    }
    
    public DigestScheme(final String challenge) throws MalformedChallengeException {
        super(challenge);
        this.getParameters().put("nc", "00000001");
    }
    
    public String getSchemeName() {
        return "digest";
    }
    
    public String authenticate(final Credentials credentials, final String method, final String uri) throws AuthenticationException {
        LOG.trace("enter DigestScheme.authenticate(Credentials, String, String)");
        UsernamePasswordCredentials usernamepassword = null;
        try {
            usernamepassword = (UsernamePasswordCredentials)credentials;
        }
        catch (ClassCastException e) {
            throw new InvalidCredentialsException("Credentials cannot be used for digest authentication: " + credentials.getClass().getName());
        }
        this.getParameters().put("cnonce", createCnonce());
        this.getParameters().put("methodname", method);
        this.getParameters().put("uri", uri);
        return authenticate(usernamepassword, this.getParameters());
    }
    
    public static String authenticate(final UsernamePasswordCredentials credentials, final Map params) throws AuthenticationException {
        LOG.trace("enter DigestScheme.authenticate(UsernamePasswordCredentials, Map)");
        final String digest = createDigest(credentials.getUserName(), credentials.getPassword(), params);
        return "Digest " + createDigestHeader(credentials.getUserName(), params, digest);
    }
    
    public static String createCnonce() throws AuthenticationException {
        LOG.trace("enter DigestScheme.createCnonce()");
        final String digAlg = "MD5";
        MessageDigest md5Helper;
        try {
            md5Helper = MessageDigest.getInstance("MD5");
        }
        catch (Exception e) {
            throw new AuthenticationException("Unsupported algorithm in HTTP Digest authentication: MD5");
        }
        String cnonce = Long.toString(System.currentTimeMillis());
        cnonce = encode(md5Helper.digest(HttpConstants.getBytes(cnonce)));
        return cnonce;
    }
    
    public static String createDigest(final String uname, final String pwd, final Map params) throws AuthenticationException {
        LOG.trace("enter DigestScheme.createDigest(String, String, Map)");
        final String digAlg = "MD5";
        final String uri = params.get("uri");
        final String realm = params.get("realm");
        final String nonce = params.get("nonce");
        final String nc = params.get("nc");
        final String cnonce = params.get("cnonce");
        String qop = params.get("qop");
        final String method = params.get("methodname");
        if (qop != null) {
            qop = "auth";
        }
        MessageDigest md5Helper;
        try {
            md5Helper = MessageDigest.getInstance("MD5");
        }
        catch (Exception e) {
            throw new AuthenticationException("Unsupported algorithm in HTTP Digest authentication: MD5");
        }
        final String a2 = String.valueOf(method) + ":" + uri;
        final String md5a2 = encode(md5Helper.digest(HttpConstants.getBytes(a2)));
        final String digestValue = String.valueOf(uname) + ":" + realm + ":" + pwd;
        final String md5a3 = encode(md5Helper.digest(HttpConstants.getBytes(digestValue)));
        String serverDigestValue;
        if (qop == null) {
            serverDigestValue = String.valueOf(md5a3) + ":" + nonce + ":" + md5a2;
        }
        else {
            serverDigestValue = String.valueOf(md5a3) + ":" + nonce + ":" + nc + ":" + cnonce + ":" + qop + ":" + md5a2;
        }
        final String serverDigest = encode(md5Helper.digest(HttpConstants.getBytes(serverDigestValue)));
        return serverDigest;
    }
    
    public static String createDigestHeader(final String uname, final Map params, final String digest) {
        LOG.trace("enter DigestScheme.createDigestHeader(String, Map, String)");
        final StringBuffer sb = new StringBuffer();
        final String uri = params.get("uri");
        final String realm = params.get("realm");
        final String nonce = params.get("nonce");
        final String nc = params.get("nc");
        final String cnonce = params.get("cnonce");
        final String opaque = params.get("opaque");
        String qop = params.get("qop");
        if (qop != null) {
            qop = "auth";
        }
        final String algorithm = "MD5";
        sb.append("username=\"" + uname + "\"").append(", realm=\"" + realm + "\"").append(", nonce=\"" + nonce + "\"").append(", uri=\"" + uri + "\"").append((qop == null) ? "" : (", qop=\"" + qop + "\"")).append(", algorithm=\"" + algorithm + "\"").append((qop == null) ? "" : (", nc=" + nc)).append((qop == null) ? "" : (", cnonce=\"" + cnonce + "\"")).append(", response=\"" + digest + "\"").append((opaque == null) ? "" : (", opaque=\"" + opaque + "\""));
        return sb.toString();
    }
    
    private static String encode(final byte[] binaryData) {
        LOG.trace("enter DigestScheme.encode(byte[])");
        if (binaryData.length != 16) {
            return null;
        }
        final char[] buffer = new char[32];
        for (int i = 0; i < 16; ++i) {
            final int low = binaryData[i] & 0xF;
            final int high = (binaryData[i] & 0xF0) >> 4;
            buffer[i * 2] = DigestScheme.HEXADECIMAL[high];
            buffer[i * 2 + 1] = DigestScheme.HEXADECIMAL[low];
        }
        return new String(buffer);
    }
}
