// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.Document;
import javax.swing.JTextField;

public class CharField extends JTextField
{
    private char baseChar;
    private int columns;
    boolean lower;
    
    public CharField() {
        this(4);
    }
    
    public CharField(final boolean lower) {
        this(4, lower);
    }
    
    public CharField(final int cols) {
        this(cols, false);
    }
    
    public CharField(final int cols, final boolean lower) {
        super(cols);
        this.baseChar = 'A';
        this.columns = 4;
        this.lower = false;
        this.columns = cols;
        if (lower) {
            this.lower = lower;
            this.baseChar = 'a';
        }
    }
    
    public void toLower() {
        this.lower = true;
        this.baseChar = 'a';
    }
    
    public void toUpper() {
        this.lower = false;
        this.baseChar = 'A';
    }
    
    @Override
    public void setColumns(final int i) {
        super.setColumns(i);
        this.setText("");
        this.columns = i;
    }
    
    @Override
    protected Document createDefaultModel() {
        return new CharDocument();
    }
    
    protected class CharDocument extends PlainDocument
    {
        @Override
        public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
            final char[] source = str.toCharArray();
            final char[] result = new char[source.length];
            int j = 0;
            for (int i = 0; i < result.length; ++i) {
                char ch = source[i];
                ch = (CharField.this.lower ? Character.toLowerCase(ch) : Character.toUpperCase(ch));
                if (ch >= CharField.this.baseChar && ch < CharField.this.baseChar + CharField.this.columns) {
                    boolean hasChar = false;
                    for (int ii = 0; ii < i; ++ii) {
                        if (ch == source[ii]) {
                            hasChar = true;
                        }
                    }
                    if (!hasChar) {
                        final int len = super.getLength();
                        final String s = super.getText(0, len);
                        final char[] sc = s.toCharArray();
                        hasChar = false;
                        for (int ij = 0; ij < len; ++ij) {
                            if (sc[ij] == ch) {
                                hasChar = true;
                            }
                        }
                        if (!hasChar) {
                            result[j++] = ch;
                        }
                    }
                }
            }
            super.insertString(offs, new String(result, 0, j), a);
        }
    }
}
