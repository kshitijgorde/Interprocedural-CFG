// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components.JLimitedTextField;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

public class LimitedStringCaseDocument extends PlainDocument
{
    private int nbInt;
    
    public LimitedStringCaseDocument(final int nbInt) {
        this.nbInt = nbInt;
    }
    
    @Override
    public void insertString(final int n, final String s, final AttributeSet set) throws BadLocationException {
        if (s == null) {
            return;
        }
        final char[] charArray = s.toCharArray();
        String string = "";
        for (int i = 0; i < charArray.length; ++i) {
            if (super.getLength() < this.nbInt) {
                string += charArray[i];
            }
        }
        super.insertString(n, string, set);
    }
}
