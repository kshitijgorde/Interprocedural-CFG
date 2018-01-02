// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

final class NamespaceKey
{
    private static final String CVS_ID = "@(#) $RCSfile: NamespaceKey.java,v $ $Revision: 1.2 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
    private String prefix;
    private String uri;
    private int hash;
    
    public NamespaceKey(final String prefix, final String uri) {
        this.prefix = prefix;
        this.uri = uri;
        this.hash = prefix.hashCode();
    }
    
    public NamespaceKey(final Namespace namespace) {
        this(namespace.getPrefix(), namespace.getURI());
    }
    
    public boolean equals(final Object ob) {
        if (this == ob) {
            return true;
        }
        if (ob instanceof NamespaceKey) {
            final NamespaceKey other = (NamespaceKey)ob;
            return this.prefix.equals(other.prefix) && this.uri.equals(other.uri);
        }
        return false;
    }
    
    public int hashCode() {
        return this.hash;
    }
    
    public String toString() {
        return "[NamespaceKey: prefix \"" + this.prefix + "\" is mapped to URI \"" + this.uri + "\"]";
    }
}
