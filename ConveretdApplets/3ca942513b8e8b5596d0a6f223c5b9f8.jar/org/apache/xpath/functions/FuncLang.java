// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncLang extends FunctionOneArg
{
    static final long serialVersionUID = -7868705139354872185L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final String lang = super.m_arg0.execute(xctxt).str();
        int parent = xctxt.getCurrentNode();
        boolean isLang = false;
        for (DTM dtm = xctxt.getDTM(parent); -1 != parent; parent = dtm.getParent(parent)) {
            if (1 == dtm.getNodeType(parent)) {
                final int langAttr = dtm.getAttributeNode(parent, "http://www.w3.org/XML/1998/namespace", "lang");
                if (-1 != langAttr) {
                    final String langVal = dtm.getNodeValue(langAttr);
                    if (langVal.toLowerCase().startsWith(lang.toLowerCase())) {
                        final int valLen = lang.length();
                        if (langVal.length() == valLen || langVal.charAt(valLen) == '-') {
                            isLang = true;
                        }
                        break;
                    }
                    break;
                }
            }
        }
        return isLang ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
}
