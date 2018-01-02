// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.trax;

import org.apache.xalan.xsltc.DOM;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.URIResolver;
import java.util.Properties;
import javax.xml.transform.Templates;

public final class TemplatesImplProxy implements Templates
{
    private TemplatesImpl templatesImpl;
    
    protected TemplatesImplProxy(final byte[][] bytecodes, final String transletName, final Properties outputProperties, final int indentNumber, final TransformerFactoryImpl tfactory) {
        this.templatesImpl = null;
        this.templatesImpl = new TemplatesImpl(bytecodes, transletName, outputProperties, indentNumber, tfactory);
    }
    
    protected TemplatesImplProxy(final Class[] transletClasses, final String transletName, final Properties outputProperties, final int indentNumber, final TransformerFactoryImpl tfactory) {
        this.templatesImpl = null;
        this.templatesImpl = new TemplatesImpl(transletClasses, transletName, outputProperties, indentNumber, tfactory);
    }
    
    public void setURIResolver(final URIResolver resolver) {
        this.templatesImpl.setURIResolver(resolver);
    }
    
    public byte[][] getTransletBytecodes() {
        return this.templatesImpl.getTransletBytecodes();
    }
    
    public Class[] getTransletClasses() {
        return this.templatesImpl.getTransletClasses();
    }
    
    public int getTransletIndex() {
        return this.templatesImpl.getTransletIndex();
    }
    
    public Transformer newTransformer() throws TransformerConfigurationException {
        return this.templatesImpl.newTransformer();
    }
    
    public Properties getOutputProperties() {
        return this.templatesImpl.getOutputProperties();
    }
    
    public DOM getStylesheetDOM() {
        return this.templatesImpl.getStylesheetDOM();
    }
    
    public void setStylesheetDOM(final DOM sdom) {
        this.templatesImpl.setStylesheetDOM(sdom);
    }
}
