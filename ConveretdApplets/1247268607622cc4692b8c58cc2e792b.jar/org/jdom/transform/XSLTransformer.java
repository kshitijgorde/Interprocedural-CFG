// 
// Decompiled by Procyon v0.5.30
// 

package org.jdom.transform;

import org.xml.sax.EntityResolver;
import javax.xml.transform.Result;
import java.util.List;
import org.jdom.Document;
import java.io.File;
import java.io.Reader;
import java.io.InputStream;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Source;
import org.jdom.JDOMFactory;
import javax.xml.transform.Templates;

public class XSLTransformer
{
    private static final String CVS_ID = "@(#) $RCSfile: XSLTransformer.java,v $ $Revision: 1.5 $ $Date: 2007/11/14 04:36:54 $ $Name: jdom_1_1_1 $";
    private Templates templates;
    private JDOMFactory factory;
    
    private XSLTransformer(final Source stylesheet) throws XSLTransformException {
        this.factory = null;
        try {
            this.templates = TransformerFactory.newInstance().newTemplates(stylesheet);
        }
        catch (TransformerException e) {
            throw new XSLTransformException("Could not construct XSLTransformer", e);
        }
    }
    
    public XSLTransformer(final String stylesheetSystemId) throws XSLTransformException {
        this(new StreamSource(stylesheetSystemId));
    }
    
    public XSLTransformer(final InputStream stylesheet) throws XSLTransformException {
        this(new StreamSource(stylesheet));
    }
    
    public XSLTransformer(final Reader stylesheet) throws XSLTransformException {
        this(new StreamSource(stylesheet));
    }
    
    public XSLTransformer(final File stylesheet) throws XSLTransformException {
        this(new StreamSource(stylesheet));
    }
    
    public XSLTransformer(final Document stylesheet) throws XSLTransformException {
        this(new JDOMSource(stylesheet));
    }
    
    public List transform(final List inputNodes) throws XSLTransformException {
        final JDOMSource source = new JDOMSource(inputNodes);
        final JDOMResult result = new JDOMResult();
        result.setFactory(this.factory);
        try {
            this.templates.newTransformer().transform(source, result);
            return result.getResult();
        }
        catch (TransformerException e) {
            throw new XSLTransformException("Could not perform transformation", e);
        }
    }
    
    public Document transform(final Document inputDoc) throws XSLTransformException {
        return this.transform(inputDoc, null);
    }
    
    public Document transform(final Document inputDoc, final EntityResolver resolver) throws XSLTransformException {
        final JDOMSource source = new JDOMSource(inputDoc, resolver);
        final JDOMResult result = new JDOMResult();
        result.setFactory(this.factory);
        try {
            this.templates.newTransformer().transform(source, result);
            return result.getDocument();
        }
        catch (TransformerException e) {
            throw new XSLTransformException("Could not perform transformation", e);
        }
    }
    
    public void setFactory(final JDOMFactory factory) {
        this.factory = factory;
    }
    
    public JDOMFactory getFactory() {
        return this.factory;
    }
}
