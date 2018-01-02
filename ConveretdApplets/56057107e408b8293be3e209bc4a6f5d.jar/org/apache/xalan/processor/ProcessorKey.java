// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import javax.xml.transform.SourceLocator;
import org.apache.xalan.templates.KeyDeclaration;
import org.xml.sax.SAXException;
import org.apache.xalan.res.XSLMessages;
import java.util.Vector;
import org.xml.sax.Attributes;

class ProcessorKey extends XSLTElementProcessor
{
    void setPropertiesFromAttributes(final StylesheetHandler handler, final String rawName, final Attributes attributes, final Object target) throws SAXException {
        final XSLTElementDef def = this.getElemDef();
        final Vector processedDefs = new Vector();
        for (int nAttrs = attributes.getLength(), i = 0; i < nAttrs; ++i) {
            final String attrUri = attributes.getURI(i);
            final String attrLocalName = attributes.getLocalName(i);
            final XSLTAttributeDef attrDef = def.getAttributeDef(attrUri, attrLocalName);
            if (attrDef == null) {
                handler.error(String.valueOf(attributes.getQName(i)) + "attribute is not allowed on the " + rawName + " element!", null);
            }
            else {
                final String valueString = attributes.getValue(i);
                if (valueString.indexOf("key(") >= 0) {
                    handler.error(XSLMessages.createMessage(106, null), null);
                }
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
    }
    
    public void startElement(final StylesheetHandler handler, final String uri, final String localName, final String rawName, final Attributes attributes) throws SAXException {
        final KeyDeclaration kd = new KeyDeclaration(handler.getStylesheet(), handler.nextUid());
        kd.setDOMBackPointer(handler.getOriginatingNode());
        kd.setLocaterInfo(handler.getLocator());
        this.setPropertiesFromAttributes(handler, rawName, attributes, kd);
        handler.getStylesheet().setKey(kd);
    }
}
