// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.xpath;

import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import org.jdom.Namespace;
import java.util.List;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;
import org.jdom.JDOMException;
import java.lang.reflect.Constructor;
import java.io.Serializable;

public abstract class XPath implements Serializable
{
    private static final String CVS_ID = "@(#) $RCSfile: XPath.java,v $ $Revision: 1.17 $ $Date: 2007/11/10 05:29:02 $ $Name: jdom_1_1_1 $";
    private static final String XPATH_CLASS_PROPERTY = "org.jdom.xpath.class";
    private static final String DEFAULT_XPATH_CLASS = "org.jdom.xpath.JaxenXPath";
    public static final String JDOM_OBJECT_MODEL_URI = "http://jdom.org/jaxp/xpath/jdom";
    private static Constructor constructor;
    static /* synthetic */ Class class$org$jdom$xpath$XPath;
    static /* synthetic */ Class class$java$lang$String;
    
    public static XPath newInstance(final String path) throws JDOMException {
        try {
            if (XPath.constructor == null) {
                String className;
                try {
                    className = System.getProperty("org.jdom.xpath.class", "org.jdom.xpath.JaxenXPath");
                }
                catch (SecurityException ex4) {
                    className = "org.jdom.xpath.JaxenXPath";
                }
                setXPathClass(Class.forName(className));
            }
            return XPath.constructor.newInstance(path);
        }
        catch (JDOMException ex1) {
            throw ex1;
        }
        catch (InvocationTargetException ex2) {
            final Throwable t = ex2.getTargetException();
            throw (t instanceof JDOMException) ? t : new JDOMException(t.toString(), t);
        }
        catch (Exception ex3) {
            throw new JDOMException(ex3.toString(), ex3);
        }
    }
    
    public static void setXPathClass(final Class aClass) throws JDOMException {
        if (aClass == null) {
            throw new IllegalArgumentException("aClass");
        }
        try {
            if (!((XPath.class$org$jdom$xpath$XPath == null) ? (XPath.class$org$jdom$xpath$XPath = class$("org.jdom.xpath.XPath")) : XPath.class$org$jdom$xpath$XPath).isAssignableFrom(aClass) || Modifier.isAbstract(aClass.getModifiers())) {
                throw new JDOMException(aClass.getName() + " is not a concrete JDOM XPath implementation");
            }
            XPath.constructor = aClass.getConstructor((XPath.class$java$lang$String == null) ? (XPath.class$java$lang$String = class$("java.lang.String")) : XPath.class$java$lang$String);
        }
        catch (JDOMException ex1) {
            throw ex1;
        }
        catch (Exception ex2) {
            throw new JDOMException(ex2.toString(), ex2);
        }
    }
    
    public abstract List selectNodes(final Object p0) throws JDOMException;
    
    public abstract Object selectSingleNode(final Object p0) throws JDOMException;
    
    public abstract String valueOf(final Object p0) throws JDOMException;
    
    public abstract Number numberValueOf(final Object p0) throws JDOMException;
    
    public abstract void setVariable(final String p0, final Object p1);
    
    public abstract void addNamespace(final Namespace p0);
    
    public void addNamespace(final String prefix, final String uri) {
        this.addNamespace(Namespace.getNamespace(prefix, uri));
    }
    
    public abstract String getXPath();
    
    public static List selectNodes(final Object context, final String path) throws JDOMException {
        return newInstance(path).selectNodes(context);
    }
    
    public static Object selectSingleNode(final Object context, final String path) throws JDOMException {
        return newInstance(path).selectSingleNode(context);
    }
    
    protected final Object writeReplace() throws ObjectStreamException {
        return new XPathString(this.getXPath());
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
        XPath.constructor = null;
    }
    
    private static final class XPathString implements Serializable
    {
        private String xPath;
        
        public XPathString(final String xpath) {
            this.xPath = null;
            this.xPath = xpath;
        }
        
        private Object readResolve() throws ObjectStreamException {
            try {
                return XPath.newInstance(this.xPath);
            }
            catch (JDOMException ex1) {
                throw new InvalidObjectException("Can't create XPath object for expression \"" + this.xPath + "\": " + ex1.toString());
            }
        }
    }
}
