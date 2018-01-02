import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

// 
// Decompiled by Procyon v0.5.30
// 

public class JTextFieldLimit extends PlainDocument
{
    private int _maxChars;
    
    public JTextFieldLimit(final int maxChars) {
        this._maxChars = maxChars;
    }
    
    public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
        if (str == null || this.getLength() >= this._maxChars) {
            return;
        }
        int numChars = this._maxChars - this.getLength();
        if (numChars > str.length()) {
            numChars = str.length();
        }
        super.insertString(offs, str.substring(0, numChars), a);
    }
}
