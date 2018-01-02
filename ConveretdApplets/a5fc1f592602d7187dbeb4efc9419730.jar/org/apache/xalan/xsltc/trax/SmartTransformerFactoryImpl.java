// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.trax;

import javax.xml.transform.TransformerException;
import org.xml.sax.XMLFilter;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.sax.TemplatesHandler;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.URIResolver;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.sax.SAXTransformerFactory;

public class SmartTransformerFactoryImpl extends SAXTransformerFactory
{
    private SAXTransformerFactory _xsltcFactory;
    private SAXTransformerFactory _xalanFactory;
    private SAXTransformerFactory _currFactory;
    private ErrorListener _errorlistener;
    private URIResolver _uriresolver;
    
    public SmartTransformerFactoryImpl() {
        this._xsltcFactory = null;
        this._xalanFactory = null;
        this._currFactory = null;
        this._errorlistener = null;
        this._uriresolver = null;
    }
    
    private void createXSLTCTransformerFactory() {
        this._xsltcFactory = new TransformerFactoryImpl();
        this._currFactory = this._xsltcFactory;
    }
    
    private void createXalanTransformerFactory() {
        final String xalanMessage = "org.apache.xalan.xsltc.trax.SmartTransformerFactoryImpl could not create an org.apache.xalan.processor.TransformerFactoryImpl.";
        try {
            final Class xalanFactClass = ObjectFactory.findProviderClass("org.apache.xalan.processor.TransformerFactoryImpl", ObjectFactory.findClassLoader(), true);
            this._xalanFactory = xalanFactClass.newInstance();
        }
        catch (ClassNotFoundException e) {
            System.err.println("org.apache.xalan.xsltc.trax.SmartTransformerFactoryImpl could not create an org.apache.xalan.processor.TransformerFactoryImpl.");
        }
        catch (InstantiationException e2) {
            System.err.println("org.apache.xalan.xsltc.trax.SmartTransformerFactoryImpl could not create an org.apache.xalan.processor.TransformerFactoryImpl.");
        }
        catch (IllegalAccessException e3) {
            System.err.println("org.apache.xalan.xsltc.trax.SmartTransformerFactoryImpl could not create an org.apache.xalan.processor.TransformerFactoryImpl.");
        }
        this._currFactory = this._xalanFactory;
    }
    
    public void setErrorListener(final ErrorListener listener) throws IllegalArgumentException {
        this._errorlistener = listener;
    }
    
    public ErrorListener getErrorListener() {
        return this._errorlistener;
    }
    
    public Object getAttribute(final String name) throws IllegalArgumentException {
        if (name.equals("translet-name") || name.equals("debug")) {
            if (this._xsltcFactory == null) {
                this.createXSLTCTransformerFactory();
            }
            return this._xsltcFactory.getAttribute(name);
        }
        if (this._xalanFactory == null) {
            this.createXalanTransformerFactory();
        }
        return this._xalanFactory.getAttribute(name);
    }
    
    public void setAttribute(final String name, final Object value) throws IllegalArgumentException {
        if (name.equals("translet-name") || name.equals("debug")) {
            if (this._xsltcFactory == null) {
                this.createXSLTCTransformerFactory();
            }
            this._xsltcFactory.setAttribute(name, value);
        }
        else {
            if (this._xalanFactory == null) {
                this.createXalanTransformerFactory();
            }
            this._xalanFactory.setAttribute(name, value);
        }
    }
    
    public boolean getFeature(final String name) {
        final String[] features = { "http://javax.xml.transform.dom.DOMSource/feature", "http://javax.xml.transform.dom.DOMResult/feature", "http://javax.xml.transform.sax.SAXSource/feature", "http://javax.xml.transform.sax.SAXResult/feature", "http://javax.xml.transform.stream.StreamSource/feature", "http://javax.xml.transform.stream.StreamResult/feature" };
        for (int i = 0; i < features.length; ++i) {
            if (name.equals(features[i])) {
                return true;
            }
        }
        return false;
    }
    
    public URIResolver getURIResolver() {
        return this._uriresolver;
    }
    
    public void setURIResolver(final URIResolver resolver) {
        this._uriresolver = resolver;
    }
    
    public Source getAssociatedStylesheet(final Source source, final String media, final String title, final String charset) throws TransformerConfigurationException {
        if (this._currFactory == null) {
            this.createXSLTCTransformerFactory();
        }
        return this._currFactory.getAssociatedStylesheet(source, media, title, charset);
    }
    
    public Transformer newTransformer() throws TransformerConfigurationException {
        if (this._xalanFactory == null) {
            this.createXalanTransformerFactory();
        }
        if (this._errorlistener != null) {
            this._xalanFactory.setErrorListener(this._errorlistener);
        }
        if (this._uriresolver != null) {
            this._xalanFactory.setURIResolver(this._uriresolver);
        }
        this._currFactory = this._xalanFactory;
        return this._currFactory.newTransformer();
    }
    
    public Transformer newTransformer(final Source source) throws TransformerConfigurationException {
        if (this._xalanFactory == null) {
            this.createXalanTransformerFactory();
        }
        if (this._errorlistener != null) {
            this._xalanFactory.setErrorListener(this._errorlistener);
        }
        if (this._uriresolver != null) {
            this._xalanFactory.setURIResolver(this._uriresolver);
        }
        this._currFactory = this._xalanFactory;
        return this._currFactory.newTransformer(source);
    }
    
    public Templates newTemplates(final Source source) throws TransformerConfigurationException {
        if (this._xsltcFactory == null) {
            this.createXSLTCTransformerFactory();
        }
        if (this._errorlistener != null) {
            this._xsltcFactory.setErrorListener(this._errorlistener);
        }
        if (this._uriresolver != null) {
            this._xsltcFactory.setURIResolver(this._uriresolver);
        }
        this._currFactory = this._xsltcFactory;
        return this._currFactory.newTemplates(source);
    }
    
    public TemplatesHandler newTemplatesHandler() throws TransformerConfigurationException {
        if (this._xsltcFactory == null) {
            this.createXSLTCTransformerFactory();
        }
        if (this._errorlistener != null) {
            this._xsltcFactory.setErrorListener(this._errorlistener);
        }
        if (this._uriresolver != null) {
            this._xsltcFactory.setURIResolver(this._uriresolver);
        }
        return this._xsltcFactory.newTemplatesHandler();
    }
    
    public TransformerHandler newTransformerHandler() throws TransformerConfigurationException {
        if (this._xalanFactory == null) {
            this.createXalanTransformerFactory();
        }
        if (this._errorlistener != null) {
            this._xalanFactory.setErrorListener(this._errorlistener);
        }
        if (this._uriresolver != null) {
            this._xalanFactory.setURIResolver(this._uriresolver);
        }
        return this._xalanFactory.newTransformerHandler();
    }
    
    public TransformerHandler newTransformerHandler(final Source src) throws TransformerConfigurationException {
        if (this._xalanFactory == null) {
            this.createXalanTransformerFactory();
        }
        if (this._errorlistener != null) {
            this._xalanFactory.setErrorListener(this._errorlistener);
        }
        if (this._uriresolver != null) {
            this._xalanFactory.setURIResolver(this._uriresolver);
        }
        return this._xalanFactory.newTransformerHandler(src);
    }
    
    public TransformerHandler newTransformerHandler(final Templates templates) throws TransformerConfigurationException {
        if (this._xsltcFactory == null) {
            this.createXSLTCTransformerFactory();
        }
        if (this._errorlistener != null) {
            this._xsltcFactory.setErrorListener(this._errorlistener);
        }
        if (this._uriresolver != null) {
            this._xsltcFactory.setURIResolver(this._uriresolver);
        }
        return this._xsltcFactory.newTransformerHandler(templates);
    }
    
    public XMLFilter newXMLFilter(final Source src) throws TransformerConfigurationException {
        if (this._xsltcFactory == null) {
            this.createXSLTCTransformerFactory();
        }
        if (this._errorlistener != null) {
            this._xsltcFactory.setErrorListener(this._errorlistener);
        }
        if (this._uriresolver != null) {
            this._xsltcFactory.setURIResolver(this._uriresolver);
        }
        final Templates templates = this._xsltcFactory.newTemplates(src);
        if (templates == null) {
            return null;
        }
        return this.newXMLFilter(templates);
    }
    
    public XMLFilter newXMLFilter(final Templates templates) throws TransformerConfigurationException {
        try {
            return new TrAXFilter(templates);
        }
        catch (TransformerConfigurationException e1) {
            if (this._xsltcFactory == null) {
                this.createXSLTCTransformerFactory();
            }
            final ErrorListener errorListener = this._xsltcFactory.getErrorListener();
            if (errorListener != null) {
                try {
                    errorListener.fatalError(e1);
                    return null;
                }
                catch (TransformerException e2) {
                    new TransformerConfigurationException(e2);
                }
            }
            throw e1;
        }
    }
}
