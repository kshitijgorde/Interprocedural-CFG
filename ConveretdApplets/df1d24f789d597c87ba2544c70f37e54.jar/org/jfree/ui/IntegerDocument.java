// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.ui;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

public class IntegerDocument extends PlainDocument
{
    public void insertString(final int n, final String s, final AttributeSet set) throws BadLocationException {
        super.insertString(n, s, set);
        if (s != null) {
            if (s.equals("-") && n == 0) {
                if (s.length() < 2) {
                    return;
                }
            }
            try {
                Integer.parseInt(this.getText(0, this.getLength()));
            }
            catch (NumberFormatException ex) {
                this.remove(n, s.length());
            }
        }
    }
}
