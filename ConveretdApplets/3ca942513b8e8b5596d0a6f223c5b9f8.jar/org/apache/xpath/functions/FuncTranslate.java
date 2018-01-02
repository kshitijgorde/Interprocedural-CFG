// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncTranslate extends Function3Args
{
    static final long serialVersionUID = -1672834340026116482L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final String theFirstString = super.m_arg0.execute(xctxt).str();
        final String theSecondString = super.m_arg1.execute(xctxt).str();
        final String theThirdString = super.m_arg2.execute(xctxt).str();
        final int theFirstStringLength = theFirstString.length();
        final int theThirdStringLength = theThirdString.length();
        final StringBuffer sbuffer = new StringBuffer();
        for (int i = 0; i < theFirstStringLength; ++i) {
            final char theCurrentChar = theFirstString.charAt(i);
            final int theIndex = theSecondString.indexOf(theCurrentChar);
            if (theIndex < 0) {
                sbuffer.append(theCurrentChar);
            }
            else if (theIndex < theThirdStringLength) {
                sbuffer.append(theThirdString.charAt(theIndex));
            }
        }
        return new XString(sbuffer.toString());
    }
}
