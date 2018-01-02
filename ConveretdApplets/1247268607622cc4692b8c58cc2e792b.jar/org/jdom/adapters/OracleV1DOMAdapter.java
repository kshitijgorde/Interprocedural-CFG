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
import org.w3c.dom.Document;
import java.io.InputStream;

public class OracleV1DOMAdapter extends AbstractDOMAdapter
{
    private static final String CVS_ID = "@(#) $RCSfile: OracleV1DOMAdapter.java,v $ $Revision: 1.20 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
    static /* synthetic */ Class class$org$xml$sax$InputSource;
    
    public Document getDocument(final InputStream in, final boolean validate) throws IOException, JDOMException {
        try {
            final Class parserClass = Class.forName("oracle.xml.parser.XMLParser");
            final Object parser = parserClass.newInstance();
            final Method parse = parserClass.getMethod("parse", (OracleV1DOMAdapter.class$org$xml$sax$InputSource == null) ? (OracleV1DOMAdapter.class$org$xml$sax$InputSource = class$("org.xml.sax.InputSource")) : OracleV1DOMAdapter.class$org$xml$sax$InputSource);
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
            return (Document)Class.forName("oracle.xml.parser.XMLDocument").newInstance();
        }
        catch (Exception e) {
            throw new JDOMException(e.getClass().getName() + ": " + e.getMessage() + " when creating document", e);
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
