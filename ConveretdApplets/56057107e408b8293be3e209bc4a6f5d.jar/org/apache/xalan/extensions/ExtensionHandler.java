// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import java.io.IOException;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.transformer.TransformerImpl;
import org.w3c.dom.Element;
import javax.xml.transform.TransformerException;
import java.util.Vector;
import java.lang.reflect.Method;

public abstract class ExtensionHandler
{
    protected String m_namespaceUri;
    protected String m_scriptLang;
    private static final Object[] NO_OBJS;
    private static Method getCCL;
    static /* synthetic */ Class class$java$lang$Thread;
    
    static {
        NO_OBJS = new Object[0];
        try {
            ExtensionHandler.getCCL = ((ExtensionHandler.class$java$lang$Thread != null) ? ExtensionHandler.class$java$lang$Thread : (ExtensionHandler.class$java$lang$Thread = class$("java.lang.Thread"))).getMethod("getContextClassLoader", (Class[])new Class[0]);
        }
        catch (Exception ex) {
            ExtensionHandler.getCCL = null;
        }
    }
    
    protected ExtensionHandler(final String namespaceUri, final String scriptLang) {
        this.m_namespaceUri = namespaceUri;
        this.m_scriptLang = scriptLang;
    }
    
    public abstract Object callFunction(final String p0, final Vector p1, final Object p2, final ExpressionContext p3) throws TransformerException;
    
    static /* synthetic */ Class class$(final String class$) {
        try {
            return Class.forName(class$);
        }
        catch (ClassNotFoundException forName) {
            throw new NoClassDefFoundError(forName.getMessage());
        }
    }
    
    static Class getClassForName(String className) throws ClassNotFoundException {
        Class result = null;
        if (className.equals("org.apache.xalan.xslt.extensions.Redirect")) {
            className = "org.apache.xalan.lib.Redirect";
        }
        if (ExtensionHandler.getCCL != null) {
            try {
                final ClassLoader contextClassLoader = (ClassLoader)ExtensionHandler.getCCL.invoke(Thread.currentThread(), ExtensionHandler.NO_OBJS);
                result = contextClassLoader.loadClass(className);
            }
            catch (ClassNotFoundException ex) {
                result = Class.forName(className);
            }
            catch (Exception ex2) {
                ExtensionHandler.getCCL = null;
                result = Class.forName(className);
            }
        }
        else {
            result = Class.forName(className);
        }
        return result;
    }
    
    public abstract boolean isElementAvailable(final String p0);
    
    public abstract boolean isFunctionAvailable(final String p0);
    
    public abstract void processElement(final String p0, final Element p1, final TransformerImpl p2, final Stylesheet p3, final Node p4, final Node p5, final QName p6, final Object p7) throws TransformerException, IOException;
}
