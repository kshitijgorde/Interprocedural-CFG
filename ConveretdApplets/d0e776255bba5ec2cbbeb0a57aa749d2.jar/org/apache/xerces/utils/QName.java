// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

public class QName
{
    private static final boolean FIND_URI_IS_MINUS_ONE = false;
    public int prefix;
    public int localpart;
    public int rawname;
    public int uri;
    
    public QName() {
        this.clear();
    }
    
    public QName(final int n, final int n2, final int n3) {
        this.setValues(n, n2, n3, 0);
    }
    
    public QName(final int n, final int n2, final int n3, final int n4) {
        this.setValues(n, n2, n3, n4);
    }
    
    public QName(final QName values) {
        this.setValues(values);
    }
    
    public void setValues(final QName qName) {
        this.prefix = qName.prefix;
        this.localpart = qName.localpart;
        this.rawname = qName.rawname;
        this.uri = qName.uri;
    }
    
    public void setValues(final int n, final int n2, final int n3) {
        this.setValues(n, n2, n3, 0);
    }
    
    public void setValues(final int prefix, final int localpart, final int rawname, final int uri) {
        this.prefix = prefix;
        this.localpart = localpart;
        this.rawname = rawname;
        this.uri = uri;
    }
    
    public void clear() {
        this.prefix = -1;
        this.localpart = -1;
        this.rawname = -1;
        this.uri = 0;
    }
    
    public boolean equals(final Object o) {
        if (o == null || !(o instanceof QName)) {
            return false;
        }
        final QName qName = (QName)o;
        if (this.uri == 0) {
            return this.rawname == qName.rawname;
        }
        return this.localpart == qName.localpart && this.uri == qName.uri;
    }
    
    public int hashCode() {
        return this.localpart << 16 | this.uri;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("prefix: ");
        sb.append(this.prefix);
        sb.append(", ");
        sb.append("localpart: ");
        sb.append(this.localpart);
        sb.append(", ");
        sb.append("rawname: ");
        sb.append(this.rawname);
        sb.append(", ");
        sb.append("uri: ");
        sb.append(this.uri);
        return sb.toString();
    }
    
    public String toString(final StringPool stringPool) {
        final StringBuffer sb = new StringBuffer();
        sb.append("prefix: ");
        sb.append(String.valueOf(stringPool.toString(this.prefix)));
        sb.append(", ");
        sb.append("localpart: ");
        sb.append(String.valueOf(stringPool.toString(this.localpart)));
        sb.append(", ");
        sb.append("rawname: ");
        sb.append(String.valueOf(stringPool.toString(this.rawname)));
        sb.append(", ");
        sb.append("uri: ");
        sb.append(String.valueOf(stringPool.toString(this.uri)));
        return sb.toString();
    }
}
