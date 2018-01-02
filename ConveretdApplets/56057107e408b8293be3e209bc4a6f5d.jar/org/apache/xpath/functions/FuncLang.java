// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.apache.xpath.objects.XBoolean;
import org.w3c.dom.Element;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncLang extends FunctionOneArg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final String lang = super.m_arg0.execute(xctxt).str();
        Node parent = xctxt.getCurrentNode();
        boolean isLang = false;
        while (parent != null) {
            if (parent.getNodeType() == 1) {
                final String langVal = ((Element)parent).getAttribute("xml:lang");
                if (langVal != null && langVal.length() > 0) {
                    if (!langVal.toLowerCase().startsWith(lang.toLowerCase())) {
                        break;
                    }
                    final int valLen = lang.length();
                    if (langVal.length() == valLen || langVal.charAt(valLen) == '-') {
                        isLang = true;
                        break;
                    }
                    break;
                }
            }
            parent = xctxt.getDOMHelper().getParentOfNode(parent);
        }
        return isLang ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
}
