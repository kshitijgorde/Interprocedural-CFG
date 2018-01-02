// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.adapters;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import org.jdom.JDOMException;
import org.xml.sax.SAXParseException;
import org.xml.sax.InputSource;
import org.jdom.input.BuilderErrorHandler;
import org.w3c.dom.Document;
import java.io.InputStream;

public class XML4JDOMAdapter extends AbstractDOMAdapter
{
    private static final String CVS_ID = "@(#) $RCSfile: XML4JDOMAdapter.java,v $ $Revision: 1.18 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$org$xml$sax$ErrorHandler;
    static /* synthetic */ Class class$org$xml$sax$InputSource;
    
    public Document getDocument(final InputStream in, final boolean validate) throws IOException, JDOMException {
        try {
            final Class parserClass = Class.forName("org.apache.xerces.parsers.DOMParser");
            final Object parser = parserClass.newInstance();
            final Method setFeature = parserClass.getMethod("setFeature", (XML4JDOMAdapter.class$java$lang$String == null) ? (XML4JDOMAdapter.class$java$lang$String = class$("java.lang.String")) : XML4JDOMAdapter.class$java$lang$String, Boolean.TYPE);
            setFeature.invoke(parser, "http://xml.org/sax/features/validation", new Boolean(validate));
            setFeature.invoke(parser, "http://xml.org/sax/features/namespaces", new Boolean(false));
            if (validate) {
                final Method setErrorHandler = parserClass.getMethod("setErrorHandler", (XML4JDOMAdapter.class$org$xml$sax$ErrorHandler == null) ? (XML4JDOMAdapter.class$org$xml$sax$ErrorHandler = class$("org.xml.sax.ErrorHandler")) : XML4JDOMAdapter.class$org$xml$sax$ErrorHandler);
                setErrorHandler.invoke(parser, new BuilderErrorHandler());
            }
            final Method parse = parserClass.getMethod("parse", (XML4JDOMAdapter.class$org$xml$sax$InputSource == null) ? (XML4JDOMAdapter.class$org$xml$sax$InputSource = class$("org.xml.sax.InputSource")) : XML4JDOMAdapter.class$org$xml$sax$InputSource);
            parse.invoke(parser, new InputSource(in));
            final Method getDocument = parserClass.getMethod("getDocument", (Class[])null);
            final Document doc = (Document)getDocument.invoke(parser, (Object[])null);
            return doc;
        }
        catch (InvocationTargetException e) {
            final Throwable targetException = e.getTargetException();
            if (targetException instanceof SAXParseException) {
                final SAXParseException parseException = (SAXParseException)targetException;
                throw new JDOMException("Error on line " + parseException.getLineNumber() + " of XML document: " + parseException.getMessage(), parseException);
            }
            if (targetException instanceof IOException) {
                final IOException ioException = (IOException)targetException;
                throw ioException;
            }
            throw new JDOMException(targetException.getMessage(), targetException);
        }
        catch (Exception e2) {
            throw new JDOMException(e2.getClass().getName() + ": " + e2.getMessage(), e2);
        }
    }
    
    public Document createDocument() throws JDOMException {
        try {
            return (Document)Class.forName("org.apache.xerces.dom.DocumentImpl").newInstance();
        }
        catch (Exception e) {
            throw new JDOMException(e.getClass().getName() + ": " + e.getMessage() + " while creating document", e);
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
