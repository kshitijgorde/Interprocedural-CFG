// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components.JLimitedTextField;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

public class LimitedIntCaseDocument extends PlainDocument
{
    private int nbInt;
    private JTextField parent;
    
    public LimitedIntCaseDocument(final JTextField parent, final int nbInt) {
        this.nbInt = nbInt;
        this.parent = parent;
    }
    
    @Override
    public void insertString(final int n, final String s, final AttributeSet set) throws BadLocationException {
        if (s == null) {
            return;
        }
        final char[] charArray = s.toCharArray();
        String string = "";
        for (int i = 0; i < charArray.length; ++i) {
            switch (charArray[i]) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9': {
                    if (super.getLength() >= this.nbInt) {
                        break;
                    }
                    if (s.startsWith("0") && this.parent.getText().length() == 0 && charArray[i] == '0') {
                        break;
                    }
                    string += charArray[i];
                    break;
                }
            }
        }
        super.insertString(n, string, set);
    }
}
