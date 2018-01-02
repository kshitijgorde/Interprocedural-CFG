// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.xni;

public class QName implements Cloneable
{
    public String prefix;
    public String localpart;
    public String rawname;
    public String uri;
    
    public QName() {
        this.clear();
    }
    
    public QName(final String prefix, final String localpart, final String rawname, final String uri) {
        this.setValues(prefix, localpart, rawname, uri);
    }
    
    public QName(final QName qname) {
        this.setValues(qname);
    }
    
    public void setValues(final QName qname) {
        this.prefix = qname.prefix;
        this.localpart = qname.localpart;
        this.rawname = qname.rawname;
        this.uri = qname.uri;
    }
    
    public void setValues(final String prefix, final String localpart, final String rawname, final String uri) {
        this.prefix = prefix;
        this.localpart = localpart;
        this.rawname = rawname;
        this.uri = uri;
    }
    
    public void clear() {
        this.prefix = null;
        this.localpart = null;
        this.rawname = null;
        this.uri = null;
    }
    
    public Object clone() {
        return new QName(this);
    }
    
    public int hashCode() {
        if (this.uri != null) {
            return this.uri.hashCode() + this.localpart.hashCode();
        }
        return this.rawname.hashCode();
    }
    
    public boolean equals(final Object object) {
        if (object instanceof QName) {
            final QName qname = (QName)object;
            if (qname.uri != null) {
                return this.uri == qname.uri && this.localpart == qname.localpart;
            }
            if (this.uri == null) {
                return this.rawname == qname.rawname;
            }
        }
        return false;
    }
    
    public String toString() {
        final StringBuffer str = new StringBuffer();
        boolean comma = false;
        if (this.prefix != null) {
            str.append("prefix=\"" + this.prefix + '\"');
            comma = true;
        }
        if (this.localpart != null) {
            if (comma) {
                str.append(',');
            }
            str.append("localpart=\"" + this.localpart + '\"');
            comma = true;
        }
        if (this.rawname != null) {
            if (comma) {
                str.append(',');
            }
            str.append("rawname=\"" + this.rawname + '\"');
            comma = true;
        }
        if (this.uri != null) {
            if (comma) {
                str.append(',');
            }
            str.append("uri=\"" + this.uri + '\"');
        }
        return str.toString();
    }
}
