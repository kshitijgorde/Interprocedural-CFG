// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.Document;
import javax.swing.JTextField;

public class CharIntField extends JTextField
{
    private boolean upper;
    private int range;
    
    public CharIntField() {
        this.upper = true;
        this.range = 3;
    }
    
    public CharIntField(final boolean lower) {
        this.upper = true;
        this.range = 3;
        this.upper = !lower;
    }
    
    public void toUpper() {
        this.upper = true;
    }
    
    public void toLower() {
        this.upper = false;
    }
    
    public void setRange(final int i) {
        this.range = i;
    }
    
    @Override
    protected Document createDefaultModel() {
        return new CharDocument();
    }
    
    protected class CharDocument extends PlainDocument
    {
        @Override
        public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
            final char[] chs = str.toCharArray();
            int len = str.length();
            final char[] insertingChs = new char[len];
            int j = 0;
            for (int i = 0; i < len; ++i) {
                final char ch = Character.toUpperCase(chs[i]);
                if (ch >= 'A' && ch <= 'Z') {
                    insertingChs[j++] = (CharIntField.this.upper ? ch : Character.toLowerCase(ch));
                }
            }
            final String insertingString = new String(insertingChs, 0, j);
            final StringBuffer original = new StringBuffer(super.getText(0, super.getLength()));
            int k;
            for (len = (k = insertingString.length()); k > 0; --k) {
                final String s0 = insertingString.substring(0, k);
                final String s2 = original.insert(offs, s0).toString();
                if (MyUtil.stringToInt(s2) < CharIntField.this.range) {
                    super.insertString(offs, s0, a);
                }
            }
        }
    }
}
