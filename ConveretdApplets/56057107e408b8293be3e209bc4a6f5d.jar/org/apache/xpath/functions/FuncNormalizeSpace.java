// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xml.utils.XMLCharacterRecognizer;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncNormalizeSpace extends FunctionDef1Arg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final String s1 = this.getArg0AsString(xctxt);
        return new XString(this.fixWhiteSpace(s1, true, true, false));
    }
    
    protected String fixWhiteSpace(final String string, final boolean trimHead, final boolean trimTail, final boolean doublePunctuationSpaces) {
        final char[] buf = string.toCharArray();
        final int len = buf.length;
        boolean edit = false;
        int s;
        for (s = 0; s < len && !isSpace(buf[s]); ++s) {}
        int d = s;
        boolean pres = false;
        while (s < len) {
            final char c = buf[s];
            if (isSpace(c)) {
                if (!pres) {
                    if (c != ' ') {
                        edit = true;
                    }
                    buf[d++] = ' ';
                    if (doublePunctuationSpaces && s != 0) {
                        final char prevChar = buf[s - 1];
                        if (prevChar != '.' && prevChar != '!' && prevChar != '?') {
                            pres = true;
                        }
                    }
                    else {
                        pres = true;
                    }
                }
                else {
                    edit = true;
                    pres = true;
                }
            }
            else {
                buf[d++] = c;
                pres = false;
            }
            ++s;
        }
        if (trimTail && d >= 1 && buf[d - 1] == ' ') {
            edit = true;
            --d;
        }
        int start = 0;
        if (trimHead && d > 0 && buf[0] == ' ') {
            edit = true;
            ++start;
        }
        return edit ? new String(buf, start, d - start) : string;
    }
    
    private static boolean isSpace(final char ch) {
        return XMLCharacterRecognizer.isWhiteSpace(ch);
    }
}
