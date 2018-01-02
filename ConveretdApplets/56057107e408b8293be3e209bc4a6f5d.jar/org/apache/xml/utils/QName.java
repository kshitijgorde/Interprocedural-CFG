// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import java.util.StringTokenizer;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.apache.xalan.res.XSLMessages;
import java.util.Stack;
import java.io.Serializable;

public class QName implements Serializable
{
    protected String _localName;
    protected String _namespaceURI;
    protected String _prefix;
    public static final String S_XMLNAMESPACEURI = "http://www.w3.org/XML/1998/namespace";
    private int m_hashCode;
    
    public QName() {
    }
    
    public QName(final String localName) {
        if (localName == null) {
            throw new IllegalArgumentException("Argument 'localName' is null");
        }
        this._namespaceURI = null;
        this._localName = localName;
        this.m_hashCode = this.toString().hashCode();
    }
    
    public QName(final String namespaceURI, final String localName) {
        if (localName == null) {
            throw new IllegalArgumentException("Argument 'localName' is null");
        }
        this._namespaceURI = namespaceURI;
        this._localName = localName;
        this.m_hashCode = this.toString().hashCode();
    }
    
    public QName(final String namespaceURI, final String prefix, final String localName) {
        if (localName == null) {
            throw new IllegalArgumentException("Argument 'localName' is null");
        }
        this._namespaceURI = namespaceURI;
        this._prefix = prefix;
        this._localName = localName;
        this.m_hashCode = this.toString().hashCode();
    }
    
    public QName(final String qname, final Stack namespaces) {
        String namespace = null;
        String prefix = null;
        final int indexOfNSSep = qname.indexOf(58);
        if (indexOfNSSep > 0) {
            prefix = qname.substring(0, indexOfNSSep);
            if (prefix.equals("xml")) {
                namespace = "http://www.w3.org/XML/1998/namespace";
            }
            else {
                if (prefix.equals("xmlns")) {
                    return;
                }
                final int depth = namespaces.size();
                for (int i = depth - 1; i >= 0; --i) {
                    for (NameSpace ns = namespaces.elementAt(i); ns != null; ns = ns.m_next) {
                        if (ns.m_prefix != null && prefix.equals(ns.m_prefix)) {
                            namespace = ns.m_uri;
                            i = -1;
                            break;
                        }
                    }
                }
            }
            if (namespace == null) {
                throw new RuntimeException(XSLMessages.createXPATHMessage(52, new Object[] { prefix }));
            }
        }
        this._localName = ((indexOfNSSep < 0) ? qname : qname.substring(indexOfNSSep + 1));
        this._namespaceURI = namespace;
        this._prefix = prefix;
        this.m_hashCode = this.toString().hashCode();
    }
    
    public QName(final String qname, final PrefixResolver resolver) {
        this._namespaceURI = null;
        final int indexOfNSSep = qname.indexOf(58);
        if (indexOfNSSep > 0) {
            final String prefix = qname.substring(0, indexOfNSSep);
            if (prefix.equals("xml")) {
                this._namespaceURI = "http://www.w3.org/XML/1998/namespace";
            }
            else {
                this._namespaceURI = resolver.getNamespaceForPrefix(prefix);
            }
            if (this._namespaceURI == null) {
                throw new RuntimeException(XSLMessages.createXPATHMessage(52, new Object[] { prefix }));
            }
        }
        this._localName = ((indexOfNSSep < 0) ? qname : qname.substring(indexOfNSSep + 1));
        this.m_hashCode = this.toString().hashCode();
    }
    
    public QName(final String qname, final Element namespaceContext, final PrefixResolver resolver) {
        this._namespaceURI = null;
        final int indexOfNSSep = qname.indexOf(58);
        if (indexOfNSSep > 0 && namespaceContext != null) {
            final String prefix = qname.substring(0, indexOfNSSep);
            this._prefix = prefix;
            if (prefix.equals("xml")) {
                this._namespaceURI = "http://www.w3.org/XML/1998/namespace";
            }
            else {
                this._namespaceURI = resolver.getNamespaceForPrefix(prefix, namespaceContext);
            }
            if (this._namespaceURI == null) {
                throw new RuntimeException(XSLMessages.createXPATHMessage(52, new Object[] { prefix }));
            }
        }
        this._localName = ((indexOfNSSep < 0) ? qname : qname.substring(indexOfNSSep + 1));
        this.m_hashCode = this.toString().hashCode();
    }
    
    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof QName) {
            final QName qname = (QName)object;
            final String thisnamespace = this.getNamespaceURI();
            final String thatnamespace = qname.getNamespaceURI();
            if (this.getLocalName().equals(qname.getLocalName())) {
                boolean equals;
                if (thisnamespace != null && thatnamespace != null) {
                    equals = thisnamespace.equals(thatnamespace);
                }
                else {
                    if (thisnamespace == null && thatnamespace == null) {
                        return true;
                    }
                    equals = false;
                }
                if (!equals) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }
    
    public boolean equals(final String ns, final String localPart) {
        final String thisnamespace = this.getNamespaceURI();
        if (this.getLocalName().equals(localPart)) {
            boolean equals;
            if (thisnamespace != null && ns != null) {
                equals = thisnamespace.equals(ns);
            }
            else {
                if (thisnamespace == null && ns == null) {
                    return true;
                }
                equals = false;
            }
            if (!equals) {
                return false;
            }
            return true;
        }
        return false;
    }
    
    public String getLocalName() {
        return this._localName;
    }
    
    public String getLocalPart() {
        return this.getLocalName();
    }
    
    public static String getLocalPart(final String qname) {
        final int index = qname.indexOf(58);
        return (index < 0) ? qname : qname.substring(index + 1);
    }
    
    public String getNamespace() {
        return this.getNamespaceURI();
    }
    
    public String getNamespaceURI() {
        return this._namespaceURI;
    }
    
    public String getPrefix() {
        return this._prefix;
    }
    
    public static String getPrefixFromXMLNSDecl(final String attRawName) {
        final int index = attRawName.indexOf(58);
        return (index >= 0) ? attRawName.substring(index + 1) : "";
    }
    
    public static String getPrefixPart(final String qname) {
        final int index = qname.indexOf(58);
        return (index >= 0) ? qname.substring(0, index) : "";
    }
    
    public static QName getQNameFromString(final String name) {
        final StringTokenizer tokenizer = new StringTokenizer(name, "{}", false);
        final String s1 = tokenizer.nextToken();
        final String s2 = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
        QName qname;
        if (s2 == null) {
            qname = new QName(null, s1);
        }
        else {
            qname = new QName(s1, s2);
        }
        return qname;
    }
    
    public int hashCode() {
        return this.m_hashCode;
    }
    
    public static boolean isXMLNSDecl(final String attRawName) {
        return attRawName.startsWith("xmlns") && (attRawName.equals("xmlns") || attRawName.startsWith("xmlns:"));
    }
    
    public String toNamespacedString() {
        return (this._namespaceURI != null) ? ("{" + this._namespaceURI + "}" + this._localName) : this._localName;
    }
    
    public String toString() {
        return (this._prefix != null) ? (String.valueOf(this._prefix) + ":" + this._localName) : ((this._namespaceURI != null) ? ("{" + this._namespaceURI + "}" + this._localName) : this._localName);
    }
}
