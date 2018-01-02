// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.xml.sax.Locator;
import org.apache.xalan.templates.ElemExtensionCall;
import org.apache.xalan.templates.Stylesheet;
import org.apache.xml.utils.PrefixResolver;
import org.apache.xpath.XPath;
import org.apache.xalan.templates.ElemTemplate;
import org.xml.sax.helpers.AttributesImpl;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.SAXSourceLocator;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.xalan.templates.StylesheetRoot;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.apache.xalan.templates.ElemTemplateElement;
import org.apache.xalan.templates.ElemLiteralResult;

public class ProcessorLRE extends ProcessorTemplateElem
{
    public void endElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName) throws SAXException {
        final ElemTemplateElement elem = handler.getElemTemplateElement();
        if (elem instanceof ElemLiteralResult && ((ElemLiteralResult)elem).getIsLiteralResultAsStylesheet()) {
            handler.popStylesheet();
        }
        super.endElement(handler, uri, localName, rawName);
    }
    
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, Attributes attributes) throws SAXException {
        try {
            ElemTemplateElement p = handler.getElemTemplateElement();
            boolean excludeXSLDecl = false;
            boolean isLREAsStyleSheet = false;
            if (p == null) {
                final XSLTElementProcessor lreProcessor = handler.popProcessor();
                final XSLTElementProcessor stylesheetProcessor = handler.getProcessorFor("http://www.w3.org/1999/XSL/Transform", "stylesheet", "xsl:stylesheet");
                handler.pushProcessor(lreProcessor);
                Stylesheet stylesheet;
                try {
                    stylesheet = new StylesheetRoot(handler.getSchema(), handler.getStylesheetProcessor().getErrorListener());
                }
                catch (TransformerConfigurationException tfe) {
                    throw new TransformerException(tfe);
                }
                final SAXSourceLocator slocator = new SAXSourceLocator();
                final Locator locator = handler.getLocator();
                if (locator != null) {
                    slocator.setLineNumber(locator.getLineNumber());
                    slocator.setColumnNumber(locator.getColumnNumber());
                    slocator.setPublicId(locator.getPublicId());
                    slocator.setSystemId(locator.getSystemId());
                }
                stylesheet.setLocaterInfo(slocator);
                stylesheet.setPrefixes(handler.getNamespaceSupport());
                handler.pushStylesheet(stylesheet);
                isLREAsStyleSheet = true;
                final AttributesImpl stylesheetAttrs = new AttributesImpl();
                final AttributesImpl lreAttrs = new AttributesImpl();
                for (int n = attributes.getLength(), i = 0; i < n; ++i) {
                    final String attrLocalName = attributes.getLocalName(i);
                    final String attrUri = attributes.getURI(i);
                    final String value = attributes.getValue(i);
                    if (attrUri != null && attrUri.equals("http://www.w3.org/1999/XSL/Transform")) {
                        stylesheetAttrs.addAttribute(null, attrLocalName, attrLocalName, attributes.getType(i), attributes.getValue(i));
                    }
                    else if ((!attrLocalName.startsWith("xmlns:") && !attrLocalName.equals("xmlns")) || !value.equals("http://www.w3.org/1999/XSL/Transform")) {
                        lreAttrs.addAttribute(attrUri, attrLocalName, attributes.getQName(i), attributes.getType(i), attributes.getValue(i));
                    }
                }
                attributes = lreAttrs;
                stylesheetProcessor.setPropertiesFromAttributes(handler, "stylesheet", stylesheetAttrs, stylesheet);
                handler.pushElemTemplateElement(stylesheet);
                final ElemTemplate template = new ElemTemplate();
                this.appendAndPush(handler, template);
                final XPath rootMatch = new XPath("/", stylesheet, stylesheet, 1, handler.getStylesheetProcessor().getErrorListener());
                template.setMatch(rootMatch);
                stylesheet.setTemplate(template);
                p = handler.getElemTemplateElement();
                excludeXSLDecl = true;
            }
            final XSLTElementDef def = this.getElemDef();
            final Class classObject = def.getClassObject();
            boolean isExtension = false;
            boolean isComponentDecl = false;
            boolean isUnknownTopLevel = false;
            while (p != null) {
                if (p instanceof ElemLiteralResult) {
                    final ElemLiteralResult parentElem = (ElemLiteralResult)p;
                    isExtension = parentElem.containsExtensionElementURI(uri);
                }
                else if (p instanceof Stylesheet) {
                    final Stylesheet parentElem2 = (Stylesheet)p;
                    isExtension = parentElem2.containsExtensionElementURI(uri);
                    if (!isExtension && uri != null && uri.equals("http://xml.apache.org/xslt")) {
                        isComponentDecl = true;
                    }
                    else {
                        isUnknownTopLevel = true;
                    }
                }
                if (isExtension) {
                    break;
                }
                p = p.getParentElem();
            }
            ElemTemplateElement elem = null;
            try {
                if (isExtension) {
                    elem = new ElemExtensionCall();
                }
                else if (isComponentDecl) {
                    elem = classObject.newInstance();
                }
                else if (isUnknownTopLevel) {
                    elem = classObject.newInstance();
                }
                else {
                    elem = classObject.newInstance();
                }
                elem.setDOMBackPointer(handler.getOriginatingNode());
                elem.setLocaterInfo(handler.getLocator());
                elem.setPrefixes(handler.getNamespaceSupport(), excludeXSLDecl);
                if (elem instanceof ElemLiteralResult) {
                    ((ElemLiteralResult)elem).setNamespace(uri);
                    ((ElemLiteralResult)elem).setLocalName(localName);
                    ((ElemLiteralResult)elem).setRawName(rawName);
                    ((ElemLiteralResult)elem).setIsLiteralResultAsStylesheet(isLREAsStyleSheet);
                }
            }
            catch (InstantiationException ie) {
                handler.error("Failed creating ElemLiteralResult instance!", ie);
            }
            catch (IllegalAccessException iae) {
                handler.error("Failed creating ElemLiteralResult instance!", iae);
            }
            this.setPropertiesFromAttributes(handler, rawName, attributes, elem);
            if (!isExtension && elem instanceof ElemLiteralResult) {
                isExtension = ((ElemLiteralResult)elem).containsExtensionElementURI(uri);
                if (isExtension) {
                    elem = new ElemExtensionCall();
                    elem.setLocaterInfo(handler.getLocator());
                    elem.setPrefixes(handler.getNamespaceSupport());
                    ((ElemLiteralResult)elem).setNamespace(uri);
                    ((ElemLiteralResult)elem).setLocalName(localName);
                    ((ElemLiteralResult)elem).setRawName(rawName);
                    this.setPropertiesFromAttributes(handler, rawName, attributes, elem);
                }
            }
            this.appendAndPush(handler, elem);
        }
        catch (TransformerException e) {
            throw new SAXException(e);
        }
    }
}
