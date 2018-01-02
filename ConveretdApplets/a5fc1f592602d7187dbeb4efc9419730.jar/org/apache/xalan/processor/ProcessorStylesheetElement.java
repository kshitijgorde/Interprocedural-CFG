// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.xml.sax.SAXException;
import org.apache.xalan.templates.ElemTemplateElement;
import javax.xml.transform.SourceLocator;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xalan.templates.StylesheetComposed;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.xalan.templates.StylesheetRoot;
import org.xml.sax.Attributes;

class ProcessorStylesheetElement extends XSLTElementProcessor
{
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        super.startElement(handler, uri, localName, rawName, attributes);
        try {
            final int stylesheetType = handler.getStylesheetType();
            Stylesheet stylesheet;
            if (stylesheetType == 1) {
                try {
                    stylesheet = new StylesheetRoot(handler.getSchema(), handler.getStylesheetProcessor().getErrorListener());
                }
                catch (TransformerConfigurationException tfe) {
                    throw new TransformerException(tfe);
                }
            }
            else {
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
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
    
    public void endElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName) throws SAXException {
        super.endElement(handler, uri, localName, rawName);
        handler.popElemTemplateElement();
        handler.popStylesheet();
    }
}
