// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.extensions;

import java.io.IOException;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xpath.functions.FuncExtFunction;
import javax.xml.transform.TransformerException;
import java.util.Vector;

public abstract class ExtensionHandler
{
    protected String m_namespaceUri;
    protected String m_scriptLang;
    
    static Class getClassForName(String className) throws ClassNotFoundException {
        if (className.equals("org.apache.xalan.xslt.extensions.Redirect")) {
            className = "org.apache.xalan.lib.Redirect";
        }
        return ObjectFactory.findProviderClass(className, ObjectFactory.findClassLoader(), true);
    }
    
    protected ExtensionHandler(final String namespaceUri, final String scriptLang) {
        this.m_namespaceUri = namespaceUri;
        this.m_scriptLang = scriptLang;
    }
    
    public abstract boolean isFunctionAvailable(final String p0);
    
    public abstract boolean isElementAvailable(final String p0);
    
    public abstract Object callFunction(final String p0, final Vector p1, final Object p2, final ExpressionContext p3) throws TransformerException;
    
    public abstract Object callFunction(final FuncExtFunction p0, final Vector p1, final ExpressionContext p2) throws TransformerException;
    
    public abstract void processElement(final String p0, final ElemTemplateElement p1, final TransformerImpl p2, final Stylesheet p3, final Object p4) throws TransformerException, IOException;
}
