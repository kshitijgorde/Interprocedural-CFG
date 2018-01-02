// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient;

import org.apache.commons.httpclient.cookie.CookiePolicy;
import java.text.Collator;
import java.util.Locale;
import java.util.Date;
import java.text.RuleBasedCollator;
import java.util.Comparator;
import java.io.Serializable;

public class Cookie extends NameValuePair implements Serializable, Comparator
{
    private static final RuleBasedCollator STRING_COLLATOR;
    private Date cookieExpiryDate;
    private String cookieComment;
    private String cookieDomain;
    private String cookiePath;
    private boolean hasDomainAttribute;
    private boolean hasPathAttribute;
    private boolean isSecure;
    private int cookieVersion;
    
    static {
        STRING_COLLATOR = (RuleBasedCollator)Collator.getInstance(new Locale("en", "US", ""));
    }
    
    public Cookie() {
        this(null, "noname", null, null, null, false);
    }
    
    public Cookie(final String domain, final String name, final String value) {
        this(domain, name, value, null, null, false);
    }
    
    public Cookie(final String domain, final String name, final String value, final String path, final Date expires, final boolean secure) {
        super(name, value);
        this.hasDomainAttribute = false;
        this.hasPathAttribute = false;
        this.cookieVersion = 0;
        if (name == null) {
            throw new IllegalArgumentException("Cookie name may not be null");
        }
        if (name.equals("")) {
            throw new IllegalArgumentException("Cookie name may not be blank");
        }
        if (name.indexOf(32) != -1) {
            throw new IllegalArgumentException("Cookie name may not contain blanks");
        }
        if (name.startsWith("$")) {
            throw new IllegalArgumentException("Cookie name may not start with $");
        }
        this.setPath(path);
        this.setDomain(domain);
        this.setExpiryDate(expires);
        this.setSecure(secure);
    }
    
    public Cookie(final String domain, final String name, final String value, final String path, final int maxAge, final boolean secure) {
        this(domain, name, value, path, null, secure);
        if (maxAge < -1) {
            throw new IllegalArgumentException("Invalid max age:  " + Integer.toString(maxAge));
        }
        if (maxAge >= 0) {
            this.setExpiryDate(new Date(System.currentTimeMillis() + maxAge * 1000L));
        }
    }
    
    public void setComment(final String comment) {
        this.cookieComment = comment;
    }
    
    public String getComment() {
        return this.cookieComment;
    }
    
    public void setDomain(String domain) {
        if (domain != null) {
            final int ndx = domain.indexOf(":");
            if (ndx != -1) {
                domain = domain.substring(0, ndx);
            }
            this.cookieDomain = domain.toLowerCase();
        }
    }
    
    public String getDomain() {
        return this.cookieDomain;
    }
    
    public void setDomainAttributeSpecified(final boolean value) {
        this.hasDomainAttribute = value;
    }
    
    public boolean isDomainAttributeSpecified() {
        return this.hasDomainAttribute;
    }
    
    public boolean isExpired() {
        return this.cookieExpiryDate != null && this.cookieExpiryDate.getTime() <= System.currentTimeMillis();
    }
    
    public boolean isExpired(final Date now) {
        return this.cookieExpiryDate != null && this.cookieExpiryDate.getTime() <= now.getTime();
    }
    
    public void setExpiryDate(final Date expiryDate) {
        this.cookieExpiryDate = expiryDate;
    }
    
    public Date getExpiryDate() {
        return this.cookieExpiryDate;
    }
    
    public void setPath(final String path) {
        this.cookiePath = path;
    }
    
    public String getPath() {
        return this.cookiePath;
    }
    
    public void setPathAttributeSpecified(final boolean value) {
        this.hasPathAttribute = value;
    }
    
    public boolean isPathAttributeSpecified() {
        return this.hasPathAttribute;
    }
    
    public boolean isPersistent() {
        return this.cookieExpiryDate != null;
    }
    
    public void setSecure(final boolean secure) {
        this.isSecure = secure;
    }
    
    public boolean getSecure() {
        return this.isSecure;
    }
    
    public void setVersion(final int version) {
        this.cookieVersion = version;
    }
    
    public int getVersion() {
        return this.cookieVersion;
    }
    
    public int compare(final Object o1, final Object o2) {
        if (!(o1 instanceof Cookie)) {
            throw new ClassCastException(o1.getClass().getName());
        }
        if (!(o2 instanceof Cookie)) {
            throw new ClassCastException(o2.getClass().getName());
        }
        final Cookie c1 = (Cookie)o1;
        final Cookie c2 = (Cookie)o2;
        if (c1.getPath() == null && c2.getPath() == null) {
            return 0;
        }
        if (c1.getPath() == null) {
            if (c2.getPath().equals("/")) {
                return 0;
            }
            return -1;
        }
        else {
            if (c2.getPath() != null) {
                return Cookie.STRING_COLLATOR.compare(c1.getPath(), c2.getPath());
            }
            if (c1.getPath().equals("/")) {
                return 0;
            }
            return 1;
        }
    }
    
    public boolean equals(final Object obj) {
        if (obj != null && obj instanceof Cookie) {
            final Cookie that = (Cookie)obj;
            if (this.getName() == null) {
                if (that.getName() != null) {
                    return false;
                }
            }
            else if (!this.getName().equals(that.getName())) {
                return false;
            }
            if (this.getPath() == null) {
                if (that.getPath() != null) {
                    return false;
                }
            }
            else if (!this.getPath().equals(that.getPath())) {
                return false;
            }
            if (this.getDomain() == null) {
                if (that.getDomain() != null) {
                    return false;
                }
            }
            else if (!this.getDomain().equals(that.getDomain())) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    public int hashCode() {
        return super.hashCode() ^ ((this.cookiePath == null) ? 0 : this.cookiePath.hashCode()) ^ ((this.cookieDomain == null) ? 0 : this.cookieDomain.hashCode());
    }
    
    public String toExternalForm() {
        return CookiePolicy.getSpecByVersion(this.getVersion()).formatCookie(this);
    }
    
    public String toString() {
        return this.toExternalForm();
    }
}
