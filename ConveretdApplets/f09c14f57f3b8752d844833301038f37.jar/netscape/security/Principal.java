// 
// Decompiled by Procyon v0.5.30
// 

package netscape.security;

import java.net.URL;

public final class Principal
{
    public static final int CODEBASE_REGEXP = 11;
    public static final int CERT = 12;
    public static final int CERT_FINGERPRINT = 13;
    public static final int CODEBASE_EXACT = 10;
    public static final int CERT_KEY = 14;
    
    public Principal(final int n, final byte[] array) {
    }
    
    public boolean isCodebaseRegexp() {
        return true;
    }
    
    public Principal() {
    }
    
    public boolean isCertFingerprint() {
        return true;
    }
    
    public Principal(final URL url) {
    }
    
    public String toVerboseString() {
        return "";
    }
    
    public String getNickname() {
        return "";
    }
    
    public boolean isCodebaseExact() {
        return true;
    }
    
    public boolean isCert() {
        return true;
    }
    
    public String getVendor() {
        return "";
    }
    
    public Principal(final int n, final byte[] array, final Class clazz) {
    }
    
    public boolean equals(final Object o) {
        return true;
    }
    
    public Principal(final int n, final String s) {
    }
    
    public String toVerboseHtml() {
        return "";
    }
    
    public boolean isCodebase() {
        return true;
    }
    
    public boolean isSystemPrincipal() {
        return true;
    }
    
    public String getFingerPrint() {
        return "";
    }
    
    public String toString() {
        return "";
    }
    
    public int hashCode() {
        return 0;
    }
}
