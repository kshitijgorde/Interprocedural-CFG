// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.xml.sax.SAXException;
import org.apache.xml.utils.QName;
import org.apache.xml.utils.XML11Char;
import org.apache.xml.serializer.NamespaceMappings;
import javax.xml.transform.TransformerException;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemAttribute extends ElemElement
{
    static final long serialVersionUID = 8817220961566919187L;
    
    public int getXSLToken() {
        return 48;
    }
    
    public String getNodeName() {
        return "attribute";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        final SerializationHandler rhandler = transformer.getSerializationHandler();
        super.execute(transformer);
    }
    
    protected String resolvePrefix(final SerializationHandler rhandler, String prefix, final String nodeNamespace) throws TransformerException {
        if (null != prefix && (prefix.length() == 0 || prefix.equals("xmlns"))) {
            prefix = rhandler.getPrefix(nodeNamespace);
            if (null == prefix || prefix.length() == 0 || prefix.equals("xmlns")) {
                if (nodeNamespace.length() > 0) {
                    final NamespaceMappings prefixMapping = rhandler.getNamespaceMappings();
                    prefix = prefixMapping.generateNextPrefix();
                }
                else {
                    prefix = "";
                }
            }
        }
        return prefix;
    }
    
    protected boolean validateNodeName(final String nodeName) {
        return null != nodeName && !nodeName.equals("xmlns") && XML11Char.isXML11ValidQName(nodeName);
    }
    
    void constructNode(final String nodeName, final String prefix, final String nodeNamespace, final TransformerImpl transformer) throws TransformerException {
        if (null != nodeName && nodeName.length() > 0) {
            final SerializationHandler rhandler = transformer.getSerializationHandler();
            final String val = transformer.transformToString(this);
            try {
                final String localName = QName.getLocalPart(nodeName);
                if (prefix != null && prefix.length() > 0) {
                    rhandler.addAttribute(nodeNamespace, localName, nodeName, "CDATA", val, true);
                }
                else {
                    rhandler.addAttribute("", localName, nodeName, "CDATA", val, true);
                }
            }
            catch (SAXException ex) {}
        }
    }
    
    public ElemTemplateElement appendChild(final ElemTemplateElement newChild) {
        final int type = newChild.getXSLToken();
        switch (type) {
            case 9:
            case 17:
            case 28:
            case 30:
            case 35:
            case 36:
            case 37:
            case 42:
            case 50:
            case 72:
            case 73:
            case 74:
            case 75:
            case 78: {
                break;
            }
            default: {
                this.error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), this.getNodeName() });
                break;
            }
        }
        return super.appendChild(newChild);
    }
    
    public void setName(final AVT v) {
        if (v.isSimple() && v.getSimpleString().equals("xmlns")) {
            throw new IllegalArgumentException();
        }
        super.setName(v);
    }
}
