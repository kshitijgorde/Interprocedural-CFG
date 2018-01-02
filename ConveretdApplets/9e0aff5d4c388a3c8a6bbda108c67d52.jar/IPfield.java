import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.Document;
import javax.swing.JTextField;

// 
// Decompiled by Procyon v0.5.30
// 

public class IPfield extends JTextField
{
    public IPfield(final int cols) {
        super(cols);
        this.setHorizontalAlignment(11);
    }
    
    protected Document createDefaultModel() {
        return new IPdocument();
    }
    
    static class IPdocument extends PlainDocument
    {
        public void insertString(final int offset, String str, final AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }
            final char[] chars = str.toCharArray();
            final boolean isNums = true;
            for (int i = 0; i < chars.length; ++i) {
                if (!this.isDigit(chars[i])) {
                    return;
                }
            }
            str = new String(chars);
            super.insertString(offset, str, a);
        }
        
        public boolean isDigit(final char a) {
            return a == '0' || a == '1' || a == '2' || a == '3' || a == '4' || a == '5' || a == '6' || a == '7' || a == '8' || a == '9';
        }
    }
}
