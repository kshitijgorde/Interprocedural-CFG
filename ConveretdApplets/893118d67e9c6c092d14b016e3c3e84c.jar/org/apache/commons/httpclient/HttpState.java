// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.Iterator;
import org.apache.commons.httpclient.cookie.CookieSpec;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import java.util.HashMap;
import java.util.ArrayList;
import org.apache.commons.httpclient.auth.HttpAuthRealm;

public class HttpState
{
    public static final String PREEMPTIVE_PROPERTY = "httpclient.authentication.preemptive";
    public static final String PREEMPTIVE_DEFAULT = "false";
    public static final HttpAuthRealm DEFAULT_AUTH_REALM;
    private ArrayList cookies;
    private HashMap credMap;
    private HashMap proxyCred;
    private HttpConnectionManager httpConnectionManager;
    private boolean preemptive;
    private int cookiePolicy;
    
    static {
        DEFAULT_AUTH_REALM = new HttpAuthRealm(null, null);
    }
    
    public HttpState() {
        this.cookies = new ArrayList();
        this.credMap = new HashMap();
        this.proxyCred = new HashMap();
        this.cookiePolicy = 2;
        this.cookiePolicy = CookiePolicy.getDefaultPolicy();
        String preemptiveDefault = System.getProperties().getProperty("httpclient.authentication.preemptive", "false");
        preemptiveDefault = preemptiveDefault.trim().toLowerCase();
        if (!preemptiveDefault.equals("true") && !preemptiveDefault.equals("false")) {
            LOG.warn("Configuration property httpclient.authentication.preemptive must be either true or false.  Using default: false");
            preemptiveDefault = "false";
        }
        this.preemptive = "true".equals(preemptiveDefault);
    }
    
    public void setAuthenticationPreemptive(final boolean value) {
        this.preemptive = value;
    }
    
    public boolean isAuthenticationPreemptive() {
        return this.preemptive;
    }
    
    public void setCookiePolicy(final int policy) {
        this.cookiePolicy = policy;
    }
    
    public int getCookiePolicy() {
        return this.cookiePolicy;
    }
    
    public synchronized Cookie[] getCookies() {
        LOG.trace("enter HttpState.getCookies()");
        return this.cookies.toArray(new Cookie[this.cookies.size()]);
    }
    
    public synchronized Cookie[] getCookies(final String domain, final int port, final String path, final boolean secure) {
        LOG.trace("enter HttpState.getCookies(String, int, String, boolean)");
        final CookieSpec matcher = CookiePolicy.getDefaultSpec();
        final ArrayList list = new ArrayList(this.cookies.size());
        for (int i = 0, m = this.cookies.size(); i < m; ++i) {
            final Cookie cookie = this.cookies.get(i);
            if (matcher.match(domain, port, path, secure, cookie)) {
                list.add(cookie);
            }
        }
        return list.toArray(new Cookie[list.size()]);
    }
    
    public synchronized void setCredentials(final String realm, final String host, final Credentials credentials) {
        LOG.trace("enter HttpState.setCredentials(String realm, String host, Credentials credentials)");
        this.credMap.put(new HttpAuthRealm(host, realm), credentials);
    }
    
    public synchronized Credentials getCredentials(final String realm, final String host) {
        LOG.trace("enter HttpState.getCredentials(String, String");
        return matchCredentials(this.credMap, realm, host);
    }
    
    public synchronized void setProxyCredentials(final String realm, final String proxyHost, final Credentials credentials) {
        LOG.trace("enter HttpState.setProxyCredentials(String, String, Credentials");
        this.proxyCred.put(new HttpAuthRealm(proxyHost, realm), credentials);
    }
    
    public synchronized Credentials getProxyCredentials(final String realm, final String proxyHost) {
        LOG.trace("enter HttpState.getCredentials(String, String");
        return matchCredentials(this.proxyCred, realm, proxyHost);
    }
    
    public synchronized void addCookie(final Cookie cookie) {
        LOG.trace("enter HttpState.addCookie(Cookie)");
        if (cookie != null) {
            final Iterator it = this.cookies.iterator();
            while (it.hasNext()) {
                final Cookie tmp = it.next();
                if (cookie.equals(tmp)) {
                    it.remove();
                    break;
                }
            }
            if (!cookie.isExpired()) {
                this.cookies.add(cookie);
            }
        }
    }
    
    public synchronized void addCookies(final Cookie[] newcookies) {
        LOG.trace("enter HttpState.addCookies(Cookie[])");
        if (newcookies != null) {
            for (int i = 0; i < newcookies.length; ++i) {
                this.addCookie(newcookies[i]);
            }
        }
    }
    
    public synchronized boolean purgeExpiredCookies() {
        LOG.trace("enter HttpState.purgeExpiredCookies()");
        return this.purgeExpiredCookies(new Date());
    }
    
    public synchronized boolean purgeExpiredCookies(final Date date) {
        LOG.trace("enter HttpState.purgeExpiredCookies(Date)");
        boolean removed = false;
        final Iterator it = this.cookies.iterator();
        while (it.hasNext()) {
            if (it.next().isExpired(date)) {
                it.remove();
                removed = true;
            }
        }
        return removed;
    }
    
    public synchronized String toString() {
        final StringBuffer sbResult = new StringBuffer();
        sbResult.append("[");
        sbResult.append(getProxyCredentialsStringRepresentation(this.proxyCred));
        sbResult.append(" | ");
        sbResult.append(getCredentialsStringRepresentation(this.proxyCred));
        sbResult.append(" | ");
        sbResult.append(getCookiesStringRepresentation(this.cookies));
        sbResult.append("]");
        final String strResult = sbResult.toString();
        return strResult;
    }
    
    private static StringBuffer getCookiesStringRepresentation(final List cookies) {
        final StringBuffer sbResult = new StringBuffer();
        for (final Cookie ck : cookies) {
            if (sbResult.length() > 0) {
                sbResult.append("#");
            }
            sbResult.append(ck.toExternalForm());
        }
        return sbResult;
    }
    
    private static StringBuffer getCredentialsStringRepresentation(final Map credMap) {
        final StringBuffer sbResult = new StringBuffer();
        for (final Object key : credMap.keySet()) {
            final Credentials cred = credMap.get(key);
            if (sbResult.length() > 0) {
                sbResult.append(", ");
            }
            sbResult.append(key);
            sbResult.append("#");
            sbResult.append(cred.toString());
        }
        return sbResult;
    }
    
    private static StringBuffer getProxyCredentialsStringRepresentation(final Map proxyCredMap) {
        final StringBuffer sbResult = new StringBuffer();
        for (final Object key : proxyCredMap.keySet()) {
            final Credentials cred = proxyCredMap.get(key);
            if (sbResult.length() > 0) {
                sbResult.append(", ");
            }
            sbResult.append(key);
            sbResult.append("#");
            sbResult.append(cred.toString());
        }
        return sbResult;
    }
    
    private static Credentials matchCredentials(final HashMap map, final String realm, final String host) {
        HttpAuthRealm entry = new HttpAuthRealm(host, realm);
        Credentials creds = map.get(entry);
        if (creds == null && host != null && realm != null) {
            entry = new HttpAuthRealm(host, null);
            creds = map.get(entry);
            if (creds == null) {
                entry = new HttpAuthRealm(null, realm);
                creds = map.get(entry);
            }
        }
        if (creds == null) {
            creds = map.get(HttpState.DEFAULT_AUTH_REALM);
        }
        return creds;
    }
}
