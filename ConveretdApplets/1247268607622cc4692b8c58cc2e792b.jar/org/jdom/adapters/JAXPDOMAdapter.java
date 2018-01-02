// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.adapters;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import org.jdom.JDOMException;
import java.io.IOException;
import org.jdom.input.BuilderErrorHandler;
import org.w3c.dom.Document;
import java.io.InputStream;

public class JAXPDOMAdapter extends AbstractDOMAdapter
{
    private static final String CVS_ID = "@(#) $RCSfile: JAXPDOMAdapter.java,v $ $Revision: 1.13 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
    static /* synthetic */ Class class$org$xml$sax$ErrorHandler;
    static /* synthetic */ Class class$java$io$InputStream;
    
    public Document getDocument(final InputStream in, final boolean validate) throws IOException, JDOMException {
        try {
            Class.forName("javax.xml.transform.Transformer");
            final Class factoryClass = Class.forName("javax.xml.parsers.DocumentBuilderFactory");
            final Method newParserInstance = factoryClass.getMethod("newInstance", (Class[])null);
            final Object factory = newParserInstance.invoke(null, (Object[])null);
            final Method setValidating = factoryClass.getMethod("setValidating", Boolean.TYPE);
            setValidating.invoke(factory, new Boolean(validate));
            final Method setNamespaceAware = factoryClass.getMethod("setNamespaceAware", Boolean.TYPE);
            setNamespaceAware.invoke(factory, Boolean.TRUE);
            final Method newDocBuilder = factoryClass.getMethod("newDocumentBuilder", (Class[])null);
            final Object jaxpParser = newDocBuilder.invoke(factory, (Object[])null);
            final Class parserClass = jaxpParser.getClass();
            final Method setErrorHandler = parserClass.getMethod("setErrorHandler", (JAXPDOMAdapter.class$org$xml$sax$ErrorHandler == null) ? (JAXPDOMAdapter.class$org$xml$sax$ErrorHandler = class$("org.xml.sax.ErrorHandler")) : JAXPDOMAdapter.class$org$xml$sax$ErrorHandler);
            setErrorHandler.invoke(jaxpParser, new BuilderErrorHandler());
            final Method parse = parserClass.getMethod("parse", (JAXPDOMAdapter.class$java$io$InputStream == null) ? (JAXPDOMAdapter.class$java$io$InputStream = class$("java.io.InputStream")) : JAXPDOMAdapter.class$java$io$InputStream);
            final Document domDoc = (Document)parse.invoke(jaxpParser, in);
            return domDoc;
        }
        catch (InvocationTargetException e) {
            final Throwable targetException = e.getTargetException();
            if (targetException instanceof IOException) {
                throw (IOException)targetException;
            }
            throw new JDOMException(targetException.getMessage(), targetException);
        }
        catch (Exception e2) {
            throw new JDOMException("Reflection failed while parsing a document with JAXP", e2);
        }
    }
    
    public Document createDocument() throws JDOMException {
        try {
            Class.forName("javax.xml.transform.Transformer");
            final Class factoryClass = Class.forName("javax.xml.parsers.DocumentBuilderFactory");
            final Method newParserInstance = factoryClass.getMethod("newInstance", (Class[])null);
            final Object factory = newParserInstance.invoke(null, (Object[])null);
            final Method newDocBuilder = factoryClass.getMethod("newDocumentBuilder", (Class[])null);
            final Object jaxpParser = newDocBuilder.invoke(factory, (Object[])null);
            final Class parserClass = jaxpParser.getClass();
            final Method newDoc = parserClass.getMethod("newDocument", (Class[])null);
            final Document domDoc = (Document)newDoc.invoke(jaxpParser, (Object[])null);
            return domDoc;
        }
        catch (Exception e) {
            throw new JDOMException("Reflection failed while creating new JAXP document", e);
        }
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
}
