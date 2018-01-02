// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.cookie;

public abstract class CookiePolicy
{
    private static final String SYSTEM_PROPERTY = "apache.commons.httpclient.cookiespec";
    public static final int COMPATIBILITY = 0;
    public static final int NETSCAPE_DRAFT = 1;
    public static final int RFC2109 = 2;
    private static int defaultPolicy;
    
    static {
        CookiePolicy.defaultPolicy = 2;
        final String s = System.getProperty("apache.commons.httpclient.cookiespec");
        if ("COMPATIBILITY".equalsIgnoreCase(s)) {
            setDefaultPolicy(0);
        }
        else if ("NETSCAPE_DRAFT".equalsIgnoreCase(s)) {
            setDefaultPolicy(1);
        }
        else if ("RFC2109".equalsIgnoreCase(s)) {
            setDefaultPolicy(2);
        }
        else {
            if (s != null) {
                LOG.warn("Unrecognized cookiespec property '" + s + "' - using default");
            }
            setDefaultPolicy(CookiePolicy.defaultPolicy);
        }
    }
    
    public static int getDefaultPolicy() {
        return CookiePolicy.defaultPolicy;
    }
    
    public static void setDefaultPolicy(final int policy) {
        CookiePolicy.defaultPolicy = policy;
    }
    
    public static CookieSpec getSpecByPolicy(final int policy) {
        switch (policy) {
            case 0: {
                return new CookieSpecBase();
            }
            case 1: {
                return new NetscapeDraftSpec();
            }
            case 2: {
                return new RFC2109Spec();
            }
            default: {
                return getSpecByPolicy(CookiePolicy.defaultPolicy);
            }
        }
    }
    
    public static CookieSpec getDefaultSpec() {
        return getSpecByPolicy(CookiePolicy.defaultPolicy);
    }
    
    public static CookieSpec getSpecByVersion(final int ver) {
        switch (ver) {
            case 0: {
                return new NetscapeDraftSpec();
            }
            case 1: {
                return new RFC2109Spec();
            }
            default: {
                return getDefaultSpec();
            }
        }
    }
    
    public static CookieSpec getCompatibilitySpec() {
        return getSpecByPolicy(0);
    }
}
