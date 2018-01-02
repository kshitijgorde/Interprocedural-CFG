// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.apache.xalan.templates.ElemTemplateElement;
import javax.xml.transform.SourceLocator;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.templates.StylesheetComposed;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.xalan.templates.StylesheetRoot;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

class ProcessorStylesheetElement extends XSLTElementProcessor
{
    public void endElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName) throws SAXException {
        handler.popElemTemplateElement();
        handler.popStylesheet();
    }
    
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        try {
            final int stylesheetType = handler.getStylesheetType();
            Stylesheet stylesheet = null;
            Label_0102: {
                if (stylesheetType == 1) {
                    try {
                        stylesheet = new StylesheetRoot(handler.getSchema(), handler.getStylesheetProcessor().getErrorListener());
                        break Label_0102;
                    }
                    catch (TransformerConfigurationException tfe) {
                        throw new TransformerException(tfe);
                    }
                }
                final Stylesheet parent = handler.getStylesheet();
                if (stylesheetType == 3) {
                    final StylesheetComposed sc = new StylesheetComposed(parent);
                    parent.setImport(sc);
                    stylesheet = sc;
                }
                else {
                    stylesheet = new Stylesheet(parent);
                    parent.setInclude(stylesheet);
                }
            }
            stylesheet.setDOMBackPointer(handler.getOriginatingNode());
            stylesheet.setLocaterInfo(handler.getLocator());
            stylesheet.setPrefixes(handler.getNamespaceSupport());
            handler.pushStylesheet(stylesheet);
            this.setPropertiesFromAttributes(handler, rawName, attributes, handler.getStylesheet());
            handler.pushElemTemplateElement(handler.getStylesheet());
        }
        catch (TransformerException e) {
            throw new SAXException(e);
        }
    }
}
