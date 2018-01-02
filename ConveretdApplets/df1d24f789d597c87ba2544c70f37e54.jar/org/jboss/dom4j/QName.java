// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.dom4j;

import org.jboss.dom4j.tree.QNameCache;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.jboss.dom4j.util.SingletonStrategy;
import java.io.Serializable;

public class QName implements Serializable
{
    private static SingletonStrategy singleton;
    private String name;
    private String qualifiedName;
    private transient Namespace namespace;
    private int hashCode;
    private DocumentFactory documentFactory;
    static /* synthetic */ Class class$org$dom4j$tree$QNameCache;
    
    public QName(final String name) {
        this(name, Namespace.NO_NAMESPACE);
    }
    
    public QName(final String name, final Namespace namespace) {
        this.name = ((name == null) ? "" : name);
        this.namespace = ((namespace == null) ? Namespace.NO_NAMESPACE : namespace);
    }
    
    public QName(final String name, final Namespace namespace, final String qualifiedName) {
        this.name = ((name == null) ? "" : name);
        this.qualifiedName = qualifiedName;
        this.namespace = ((namespace == null) ? Namespace.NO_NAMESPACE : namespace);
    }
    
    public static QName get(final String name) {
        return getCache().get(name);
    }
    
    public static QName get(final String name, final Namespace namespace) {
        return getCache().get(name, namespace);
    }
    
    public static QName get(final String name, final String prefix, final String uri) {
        if ((prefix == null || prefix.length() == 0) && uri == null) {
            return get(name);
        }
        if (prefix == null || prefix.length() == 0) {
            return getCache().get(name, Namespace.get(uri));
        }
        if (uri == null) {
            return get(name);
        }
        return getCache().get(name, Namespace.get(prefix, uri));
    }
    
    public static QName get(final String qualifiedName, final String uri) {
        if (uri == null) {
            return getCache().get(qualifiedName);
        }
        return getCache().get(qualifiedName, uri);
    }
    
    public static QName get(final String localName, final Namespace namespace, final String qualifiedName) {
        return getCache().get(localName, namespace, qualifiedName);
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getQualifiedName() {
        if (this.qualifiedName == null) {
            final String prefix = this.getNamespacePrefix();
            if (prefix != null && prefix.length() > 0) {
                this.qualifiedName = prefix + ":" + this.name;
            }
            else {
                this.qualifiedName = this.name;
            }
        }
        return this.qualifiedName;
    }
    
    public Namespace getNamespace() {
        return this.namespace;
    }
    
    public String getNamespacePrefix() {
        if (this.namespace == null) {
            return "";
        }
        return this.namespace.getPrefix();
    }
    
    public String getNamespaceURI() {
        if (this.namespace == null) {
            return "";
        }
        return this.namespace.getURI();
    }
    
    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = (this.getName().hashCode() ^ this.getNamespaceURI().hashCode());
            if (this.hashCode == 0) {
                this.hashCode = 47806;
            }
        }
        return this.hashCode;
    }
    
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof QName) {
            final QName that = (QName)object;
            if (this.hashCode() == that.hashCode()) {
                return this.getName().equals(that.getName()) && this.getNamespaceURI().equals(that.getNamespaceURI());
            }
        }
        return false;
    }
    
    public String toString() {
        return super.toString() + " [name: " + this.getName() + " namespace: \"" + this.getNamespace() + "\"]";
    }
    
    public DocumentFactory getDocumentFactory() {
        return this.documentFactory;
    }
    
    public void setDocumentFactory(final DocumentFactory documentFactory) {
        this.documentFactory = documentFactory;
    }
    
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.writeObject(this.namespace.getPrefix());
        out.writeObject(this.namespace.getURI());
        out.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        final String prefix = (String)in.readObject();
        final String uri = (String)in.readObject();
        in.defaultReadObject();
        this.namespace = Namespace.get(prefix, uri);
    }
    
    private static QNameCache getCache() {
        final QNameCache cache = (QNameCache)QName.singleton.instance();
        return cache;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        QName.singleton = null;
        try {
            final String defaultSingletonClass = "org.jboss.dom4j.util.SimpleSingleton";
            Class clazz = null;
            try {
                String singletonClass = defaultSingletonClass;
                singletonClass = System.getProperty("org.jboss.dom4j.QName.singleton.strategy", singletonClass);
                clazz = Class.forName(singletonClass);
            }
            catch (Exception exc1) {
                try {
                    final String singletonClass2 = defaultSingletonClass;
                    clazz = Class.forName(singletonClass2);
                }
                catch (Exception ex) {}
            }
            (QName.singleton = clazz.newInstance()).setSingletonClassName(((QName.class$org$dom4j$tree$QNameCache == null) ? (QName.class$org$dom4j$tree$QNameCache = class$("org.jboss.dom4j.tree.QNameCache")) : QName.class$org$dom4j$tree$QNameCache).getName());
        }
        catch (Exception ex2) {}
    }
}
