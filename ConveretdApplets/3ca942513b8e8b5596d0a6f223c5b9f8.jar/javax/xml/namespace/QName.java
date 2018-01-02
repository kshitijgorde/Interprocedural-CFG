// 
// Decompiled by Procyon v0.5.30
// 

package javax.xml.namespace;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class QName implements Serializable
{
    static final long serialVersionUID;
    private static final long defaultSerialVersionUID = -9120448754896609940L;
    private static final long compatibleSerialVersionUID = 4418622981026545151L;
    private static boolean useDefaultSerialVersionUID;
    private String namespaceURI;
    private String localPart;
    private String prefix;
    private transient int hashCode;
    private transient String qNameAsString;
    
    public QName(final String s, final String s2) {
        this(s, s2, "");
    }
    
    public QName(final String namespaceURI, final String localPart, final String prefix) {
        if (namespaceURI == null) {
            this.namespaceURI = "";
        }
        else {
            this.namespaceURI = namespaceURI;
        }
        if (localPart == null) {
            throw new IllegalArgumentException("local part cannot be \"null\" when creating a QName");
        }
        this.localPart = localPart;
        if (prefix == null) {
            throw new IllegalArgumentException("prefix cannot be \"null\" when creating a QName");
        }
        this.prefix = prefix;
    }
    
    public QName(final String s) {
        this("", s, "");
    }
    
    public String getNamespaceURI() {
        return this.namespaceURI;
    }
    
    public String getLocalPart() {
        return this.localPart;
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public final boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof QName) {
            final QName qName = (QName)o;
            return this.localPart.equals(qName.localPart) && this.namespaceURI.equals(qName.namespaceURI);
        }
        return false;
    }
    
    public final int hashCode() {
        int hashCode = this.hashCode;
        if (hashCode == 0) {
            hashCode = (this.namespaceURI.hashCode() ^ this.localPart.hashCode());
            this.hashCode = hashCode;
        }
        return hashCode;
    }
    
    public String toString() {
        String qNameAsString = this.qNameAsString;
        if (qNameAsString == null) {
            final int length = this.namespaceURI.length();
            if (length == 0) {
                qNameAsString = this.localPart;
            }
            else {
                final StringBuffer sb = new StringBuffer(length + this.localPart.length() + 2);
                sb.append('{');
                sb.append(this.namespaceURI);
                sb.append('}');
                sb.append(this.localPart);
                qNameAsString = sb.toString();
            }
            this.qNameAsString = qNameAsString;
        }
        return qNameAsString;
    }
    
    public static QName valueOf(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("Cannot create QName from \"null\".");
        }
        if (s.length() == 0 || s.charAt(0) != '{') {
            return new QName("", s, "");
        }
        final int index = s.indexOf(125);
        if (index == -1) {
            throw new IllegalArgumentException("Cannot create QName from \"" + s + "\", missing closing \"}\"");
        }
        if (index == 1) {
            throw new IllegalArgumentException("Cannot create QName from \"" + s + "\", missing namespace URI");
        }
        return new QName(s.substring(1, index), s.substring(index + 1), "");
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        if (this.prefix == null) {
            this.prefix = "";
        }
    }
    
    static {
        QName.useDefaultSerialVersionUID = true;
        try {
            final String s = AccessController.doPrivileged((PrivilegedAction<String>)new PrivilegedAction() {
                public Object run() {
                    return System.getProperty("com.sun.xml.namespace.QName.useCompatibleSerialVersionUID");
                }
            });
            QName.useDefaultSerialVersionUID = (s == null || !s.equals("1.0"));
        }
        catch (Exception ex) {
            QName.useDefaultSerialVersionUID = true;
        }
        if (QName.useDefaultSerialVersionUID) {
            serialVersionUID = -9120448754896609940L;
        }
        else {
            serialVersionUID = 4418622981026545151L;
        }
    }
}
