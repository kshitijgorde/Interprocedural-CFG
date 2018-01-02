// 
// Decompiled by Procyon v0.5.30
// 

package javautil;

public class Version
{
    int _version;
    int _revision;
    int _subrevision;
    int _suffix;
    
    public Version(final String s) {
        this._version = 0;
        this._revision = 0;
        this._subrevision = 0;
        this._suffix = 0;
        this.parse(s);
    }
    
    public void parse(final String s) {
        this._version = 0;
        this._revision = 0;
        this._subrevision = 0;
        this._suffix = -1;
        int n = 0;
        final int n2 = 0;
        int length;
        for (length = s.length(); n < length && Character.isDigit(s.charAt(n)); ++n) {}
        this._version = Integer.parseInt(s.substring(n2, n));
        if (n < length && s.charAt(n) == '.') {
            int n3;
            for (n3 = ++n; n < length && Character.isDigit(s.charAt(n)); ++n) {}
            this._revision = Integer.parseInt(s.substring(n3, n));
        }
        if (n < length && s.charAt(n) == '.') {
            int n4;
            for (n4 = ++n; n < length && Character.isDigit(s.charAt(n)); ++n) {}
            this._subrevision = Integer.parseInt(s.substring(n4, n));
        }
        if (n < length) {
            final int n5 = ++n;
            try {
                this._suffix = Integer.parseInt(s.substring(n5));
            }
            catch (Exception ex) {
                this._suffix = -1;
            }
        }
    }
    
    public int compare(final Version version) {
        if (this._version < version._version) {
            return -1;
        }
        if (this._version > version._version) {
            return 1;
        }
        if (this._revision < version._revision) {
            return -1;
        }
        if (this._revision > version._revision) {
            return 1;
        }
        if (this._subrevision < version._subrevision) {
            return -1;
        }
        if (this._subrevision > version._subrevision) {
            return 1;
        }
        if (this._suffix < version._suffix) {
            return -1;
        }
        if (this._suffix > version._suffix) {
            return 1;
        }
        return 0;
    }
    
    public boolean isInRange(final Version version, final Version version2) {
        return this.compare(version) >= 0 && this.compare(version2) <= 0;
    }
    
    public boolean isInRange(final String s, final String s2) {
        final Version version = new Version(s);
        final Version version2 = new Version(s2);
        return this.compare(version) >= 0 && this.compare(version2) <= 0;
    }
}
