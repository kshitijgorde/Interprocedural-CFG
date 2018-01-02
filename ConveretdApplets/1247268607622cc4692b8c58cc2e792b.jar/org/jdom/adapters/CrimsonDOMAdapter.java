// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.adapters;

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.io.IOException;
import org.jdom.JDOMException;
import org.xml.sax.SAXParseException;
import org.w3c.dom.Document;
import java.io.InputStream;

public class CrimsonDOMAdapter extends AbstractDOMAdapter
{
    private static final String CVS_ID = "@(#) $RCSfile: CrimsonDOMAdapter.java,v $ $Revision: 1.17 $ $Date: 2007/11/10 05:28:59 $ $Name: jdom_1_1_1 $";
    
    public Document getDocument(final InputStream in, final boolean validate) throws IOException, JDOMException {
        try {
            final Class[] parameterTypes = { Class.forName("java.io.InputStream"), Boolean.TYPE };
            final Object[] args = { in, new Boolean(false) };
            final Class parserClass = Class.forName("org.apache.crimson.tree.XmlDocument");
            final Method createXmlDocument = parserClass.getMethod("createXmlDocument", (Class[])parameterTypes);
            final Document doc = (Document)createXmlDocument.invoke(null, args);
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
            return (Document)Class.forName("org.apache.crimson.tree.XmlDocument").newInstance();
        }
        catch (Exception e) {
            throw new JDOMException(e.getClass().getName() + ": " + e.getMessage() + " when creating document", e);
        }
    }
}
