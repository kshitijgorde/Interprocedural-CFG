// 
// Decompiled by Procyon v0.5.30
// 

package dk.scanmaps.ditkort;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument
{
    static final long serialVersionUID = 0L;
    private int limit;
    private boolean toUppercase;
    
    JTextFieldLimit(final int limit) {
        this.toUppercase = false;
        this.limit = limit;
    }
    
    JTextFieldLimit(final int limit, final boolean upper) {
        this.toUppercase = false;
        this.limit = limit;
        this.toUppercase = upper;
    }
    
    public void insertString(final int offset, String str, final AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }
        if (this.getLength() + str.length() <= this.limit) {
            if (this.toUppercase) {
                str = str.toUpperCase();
            }
            super.insertString(offset, str, attr);
        }
    }
}
