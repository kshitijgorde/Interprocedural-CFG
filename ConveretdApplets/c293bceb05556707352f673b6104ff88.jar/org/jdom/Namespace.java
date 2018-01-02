// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import com.sun.java.util.collections.HashMap;

public final class Namespace
{
    private static final String CVS_ID = "@(#) $RCSfile: Namespace.java,v $ $Revision: 1.32 $ $Date: 2002/03/12 07:57:06 $ $Name: jdom_1_0_b8 $";
    private static HashMap namespaces;
    public static final Namespace NO_NAMESPACE;
    public static final Namespace XML_NAMESPACE;
    private String prefix;
    private String uri;
    
    static {
        NO_NAMESPACE = new Namespace("", "");
        XML_NAMESPACE = new Namespace("xml", "http://www.w3.org/XML/1998/namespace");
        (Namespace.namespaces = new HashMap()).put("&", Namespace.NO_NAMESPACE);
        Namespace.namespaces.put("xml&http://www.w3.org/XML/1998/namespace", Namespace.XML_NAMESPACE);
    }
    
    public static Namespace getNamespace(String prefix, String uri) {
        if (prefix == null || prefix.trim().equals("")) {
            prefix = "";
        }
        if (uri == null || uri.trim().equals("")) {
            uri = "";
        }
        if (prefix.equals("xml")) {
            return Namespace.XML_NAMESPACE;
        }
        final String lookup = new StringBuffer(64).append(prefix).append('&').append(uri).toString();
        final Namespace preexisting = (Namespace)Namespace.namespaces.get(lookup);
        if (preexisting != null) {
            return preexisting;
        }
        String reason;
        if ((reason = Verifier.checkNamespacePrefix(prefix)) != null) {
            throw new IllegalNameException(prefix, "Namespace prefix", reason);
        }
        if ((reason = Verifier.checkNamespaceURI(uri)) != null) {
            throw new IllegalNameException(uri, "Namespace URI", reason);
        }
        if (!prefix.equals("") && uri.equals("")) {
            throw new IllegalNameException("", "namespace", "Namespace URIs must be non-null and non-empty Strings");
        }
        final Namespace ns = new Namespace(prefix, uri);
        Namespace.namespaces.put(lookup, ns);
        return ns;
    }
    
    public static Namespace getNamespace(final String uri) {
        return getNamespace("", uri);
    }
    
    private Namespace(final String prefix, final String uri) {
        this.prefix = prefix;
        this.uri = uri;
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public String getURI() {
        return this.uri;
    }
    
    public boolean equals(final Object ob) {
        return ob instanceof Namespace && this.uri.equals(((Namespace)ob).uri);
    }
    
    public String toString() {
        return "[Namespace: prefix \"" + this.prefix + "\" is mapped to URI \"" + this.uri + "\"]";
    }
    
    public int hashCode() {
        return this.uri.hashCode();
    }
}
