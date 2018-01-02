// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import org.apache.xalan.res.XSLMessages;
import java.util.Vector;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
import org.apache.xml.utils.IntStack;
import org.apache.xalan.templates.ElemTemplateElement;

public class XSLTElementProcessor extends ElemTemplateElement
{
    private IntStack m_savedLastOrder;
    private XSLTElementDef m_elemDef;
    
    XSLTElementDef getElemDef() {
        return this.m_elemDef;
    }
    
    void setElemDef(final XSLTElementDef def) {
        this.m_elemDef = def;
    }
    
    public InputSource resolveEntity(final StylesheetHandler handler, final String publicId, final String systemId) throws SAXException {
        return null;
    }
    
    public void notationDecl(final StylesheetHandler handler, final String name, final String publicId, final String systemId) {
    }
    
    public void unparsedEntityDecl(final StylesheetHandler handler, final String name, final String publicId, final String systemId, final String notationName) {
    }
    
    public void startNonText(final StylesheetHandler handler) throws SAXException {
    }
    
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        if (this.m_savedLastOrder == null) {
            this.m_savedLastOrder = new IntStack();
        }
        this.m_savedLastOrder.push(this.getElemDef().getLastOrder());
        this.getElemDef().setLastOrder(-1);
    }
    
    public void endElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName) throws SAXException {
        if (this.m_savedLastOrder != null && !this.m_savedLastOrder.empty()) {
            this.getElemDef().setLastOrder(this.m_savedLastOrder.pop());
        }
        if (!this.getElemDef().getRequiredFound()) {
            handler.error("ER_REQUIRED_ELEM_NOT_FOUND", new Object[] { this.getElemDef().getRequiredElem() }, null);
        }
    }
    
    public void characters(final StylesheetHandler handler, final char[] ch, final int start, final int length) throws SAXException {
        handler.error("ER_CHARS_NOT_ALLOWED", null, null);
    }
    
    public void ignorableWhitespace(final StylesheetHandler handler, final char[] ch, final int start, final int length) throws SAXException {
    }
    
    public void processingInstruction(final StylesheetHandler handler, final String target, final String data) throws SAXException {
    }
    
    public void skippedEntity(final StylesheetHandler handler, final String name) throws SAXException {
    }
    
    void setPropertiesFromAttributes(final StylesheetHandler handler, final String rawName, final Attributes attributes, final ElemTemplateElement target) throws SAXException {
        this.setPropertiesFromAttributes(handler, rawName, attributes, target, true);
    }
    
    Attributes setPropertiesFromAttributes(final StylesheetHandler handler, final String rawName, final Attributes attributes, final ElemTemplateElement target, final boolean throwError) throws SAXException {
        final XSLTElementDef def = this.getElemDef();
        AttributesImpl undefines = null;
        final boolean isCompatibleMode = (null != handler.getStylesheet() && handler.getStylesheet().getCompatibleMode()) || !throwError;
        if (isCompatibleMode) {
            undefines = new AttributesImpl();
        }
        final Vector processedDefs = new Vector();
        final Vector errorDefs = new Vector();
        for (int nAttrs = attributes.getLength(), i = 0; i < nAttrs; ++i) {
            String attrUri = attributes.getURI(i);
            if (null != attrUri && attrUri.length() == 0 && (attributes.getQName(i).startsWith("xmlns:") || attributes.getQName(i).equals("xmlns"))) {
                attrUri = "http://www.w3.org/XML/1998/namespace";
            }
            final String attrLocalName = attributes.getLocalName(i);
            final XSLTAttributeDef attrDef = def.getAttributeDef(attrUri, attrLocalName);
            if (null == attrDef) {
                if (!isCompatibleMode) {
                    handler.error("ER_ATTR_NOT_ALLOWED", new Object[] { attributes.getQName(i), rawName }, null);
                }
                else {
                    undefines.addAttribute(attrUri, attrLocalName, attributes.getQName(i), attributes.getType(i), attributes.getValue(i));
                }
            }
            else {
                final boolean success = attrDef.setAttrValue(handler, attrUri, attrLocalName, attributes.getQName(i), attributes.getValue(i), target);
                if (success) {
                    processedDefs.addElement(attrDef);
                }
                else {
                    errorDefs.addElement(attrDef);
                }
            }
        }
        for (final XSLTAttributeDef attrDef2 : def.getAttributes()) {
            final String defVal = attrDef2.getDefault();
            if (null != defVal && !processedDefs.contains(attrDef2)) {
                attrDef2.setDefAttrValue(handler, target);
            }
            if (attrDef2.getRequired() && !processedDefs.contains(attrDef2) && !errorDefs.contains(attrDef2)) {
                handler.error(XSLMessages.createMessage("ER_REQUIRES_ATTRIB", new Object[] { rawName, attrDef2.getName() }), null);
            }
        }
        return undefines;
    }
}
