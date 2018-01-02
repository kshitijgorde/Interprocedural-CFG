// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom;

import java.util.HashMap;

public final class Namespace
{
    private static final String CVS_ID = "@(#) $RCSfile: Namespace.java,v $ $Revision: 1.44 $ $Date: 2008/12/17 23:22:48 $ $Name: jdom_1_1_1 $";
    private static HashMap namespaces;
    public static final Namespace NO_NAMESPACE;
    public static final Namespace XML_NAMESPACE;
    private String prefix;
    private String uri;
    
    public static Namespace getNamespace(String prefix, String uri) {
        if (prefix == null || prefix.trim().equals("")) {
            if (uri == null || uri.trim().equals("")) {
                return Namespace.NO_NAMESPACE;
            }
            prefix = "";
        }
        else if (uri == null || uri.trim().equals("")) {
            uri = "";
        }
        final NamespaceKey lookup = new NamespaceKey(prefix, uri);
        final Namespace preexisting;
        synchronized (Namespace.namespaces) {
            preexisting = Namespace.namespaces.get(lookup);
        }
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
        if (prefix.equals("xml")) {
            throw new IllegalNameException(prefix, "Namespace prefix", "The xml prefix can only be bound to http://www.w3.org/XML/1998/namespace");
        }
        if (uri.equals("http://www.w3.org/XML/1998/namespace")) {
            throw new IllegalNameException(uri, "Namespace URI", "The http://www.w3.org/XML/1998/namespace must be bound to the xml prefix.");
        }
        final Namespace ns = new Namespace(prefix, uri);
        synchronized (Namespace.namespaces) {
            Namespace.namespaces.put(lookup, ns);
        }
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
        return this == ob || (ob instanceof Namespace && this.uri.equals(((Namespace)ob).uri));
    }
    
    public String toString() {
        return "[Namespace: prefix \"" + this.prefix + "\" is mapped to URI \"" + this.uri + "\"]";
    }
    
    public int hashCode() {
        return this.uri.hashCode();
    }
    
    static {
        NO_NAMESPACE = new Namespace("", "");
        XML_NAMESPACE = new Namespace("xml", "http://www.w3.org/XML/1998/namespace");
        (Namespace.namespaces = new HashMap(16)).put(new NamespaceKey(Namespace.NO_NAMESPACE), Namespace.NO_NAMESPACE);
        Namespace.namespaces.put(new NamespaceKey(Namespace.XML_NAMESPACE), Namespace.XML_NAMESPACE);
    }
}
