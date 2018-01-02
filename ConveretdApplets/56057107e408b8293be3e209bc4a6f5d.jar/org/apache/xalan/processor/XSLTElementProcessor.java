// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.apache.xalan.res.XSLMessages;
import java.util.Vector;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XSLTElementProcessor
{
    private XSLTElementDef m_elemDef;
    
    public void characters(final StylesheetHandler handler, final char[] ch, final int start, final int length) throws SAXException {
        handler.error("Characters are not allowed at this point in the document!", null);
    }
    
    public void endElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName) throws SAXException {
    }
    
    XSLTElementDef getElemDef() {
        return this.m_elemDef;
    }
    
    public void ignorableWhitespace(final StylesheetHandler handler, final char[] ch, final int start, final int length) throws SAXException {
    }
    
    public void notationDecl(final StylesheetHandler handler, final String name, final String publicId, final String systemId) {
    }
    
    public void processingInstruction(final StylesheetHandler handler, final String target, final String data) throws SAXException {
    }
    
    public InputSource resolveEntity(final StylesheetHandler handler, final String publicId, final String systemId) throws SAXException {
        return null;
    }
    
    void setElemDef(final XSLTElementDef def) {
        this.m_elemDef = def;
    }
    
    void setPropertiesFromAttributes(final StylesheetHandler handler, final String rawName, final Attributes attributes, final Object target) throws SAXException {
        this.setPropertiesFromAttributes(handler, rawName, attributes, target, true);
    }
    
    Attributes setPropertiesFromAttributes(final StylesheetHandler handler, final String rawName, final Attributes attributes, final Object target, final boolean throwError) throws SAXException {
        final XSLTElementDef def = this.getElemDef();
        final AttributesImpl undefines = throwError ? null : new AttributesImpl();
        final Vector processedDefs = new Vector();
        for (int nAttrs = attributes.getLength(), i = 0; i < nAttrs; ++i) {
            String attrUri = attributes.getURI(i);
            if (attrUri != null && attrUri.length() == 0 && (attributes.getQName(i).startsWith("xmlns:") || attributes.getQName(i).equals("xmlns"))) {
                attrUri = "http://www.w3.org/XML/1998/namespace";
            }
            final String attrLocalName = attributes.getLocalName(i);
            final XSLTAttributeDef attrDef = def.getAttributeDef(attrUri, attrLocalName);
            if (attrDef == null) {
                if (throwError) {
                    handler.error("\"" + attributes.getQName(i) + "\"" + " attribute is not allowed on the " + rawName + " element!", null);
                }
                else {
                    undefines.addAttribute(attrUri, attrLocalName, attributes.getQName(i), attributes.getType(i), attributes.getValue(i));
                }
            }
            else {
                processedDefs.addElement(attrDef);
                attrDef.setAttrValue(handler, attrUri, attrLocalName, attributes.getQName(i), attributes.getValue(i), target);
            }
        }
        for (final XSLTAttributeDef attrDef2 : def.getAttributes()) {
            final String defVal = attrDef2.getDefault();
            if (defVal != null && !processedDefs.contains(attrDef2)) {
                attrDef2.setDefAttrValue(handler, target);
            }
            if (attrDef2.getRequired() && !processedDefs.contains(attrDef2)) {
                handler.error(XSLMessages.createMessage(9, new Object[] { rawName, attrDef2.getName() }), null);
            }
        }
        return undefines;
    }
    
    public void skippedEntity(final StylesheetHandler handler, final String name) throws SAXException {
    }
    
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
    }
    
    public void startNonText(final StylesheetHandler handler) throws SAXException {
    }
    
    public void unparsedEntityDecl(final StylesheetHandler handler, final String name, final String publicId, final String systemId, final String notationName) {
    }
}
