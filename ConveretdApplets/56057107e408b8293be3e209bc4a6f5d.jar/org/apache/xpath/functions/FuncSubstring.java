// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncSubstring extends Function3Args
{
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum < 2) {
            throw new WrongNumberArgsException("2 or 3");
        }
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final String s1 = super.m_arg0.execute(xctxt).str();
        double start = super.m_arg1.execute(xctxt).num();
        final int lenOfS1 = s1.length();
        if (lenOfS1 <= 0) {
            return XString.EMPTYSTRING;
        }
        int startIndex;
        if (Double.isNaN(start)) {
            start = -1000000.0;
            startIndex = 0;
        }
        else {
            start = Math.round(start);
            startIndex = ((start > 0.0) ? ((int)start - 1) : 0);
        }
        String substr;
        if (super.m_arg2 != null) {
            final double len = super.m_arg2.execute(xctxt).num();
            int end = (int)(Math.round(len) + start) - 1;
            if (end < 0) {
                end = 0;
            }
            else if (end > lenOfS1) {
                end = lenOfS1;
            }
            if (startIndex > lenOfS1) {
                startIndex = lenOfS1;
            }
            substr = s1.substring(startIndex, end);
        }
        else {
            if (startIndex > lenOfS1) {
                startIndex = lenOfS1;
            }
            substr = s1.substring(startIndex);
        }
        return new XString(substr);
    }
}
