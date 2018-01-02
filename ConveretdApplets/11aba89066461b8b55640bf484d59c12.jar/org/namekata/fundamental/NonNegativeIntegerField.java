// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.Document;
import javax.swing.JTextField;

public class NonNegativeIntegerField extends JTextField
{
    private int columns;
    
    public NonNegativeIntegerField() {
        super(4);
        super.setHorizontalAlignment(this.columns = 4);
        this.setText("0");
    }
    
    public NonNegativeIntegerField(final int cols) {
        super(cols);
        super.setHorizontalAlignment(this.columns = 4);
        this.setText("0");
        this.columns = cols;
    }
    
    @Override
    public void setColumns(final int i) {
        super.setColumns(i);
        this.setText("0");
        this.columns = i;
    }
    
    public void setValue(final Object obj) {
        this.setText(((Integer)obj).toString());
    }
    
    public void setValue(final int i) {
        this.setText(Integer.toString(i));
    }
    
    public Integer getValue() {
        return Integer.valueOf(this.getText());
    }
    
    @Override
    protected Document createDefaultModel() {
        return new IntegerDocument();
    }
    
    protected class IntegerDocument extends PlainDocument
    {
        @Override
        public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
            final char[] source = str.toCharArray();
            final char[] result = new char[source.length];
            int j = 0;
            for (int i = 0; i < result.length; ++i) {
                final char ch = source[i];
                if (ch >= '0' && ch <= '9') {
                    result[j++] = ch;
                }
            }
            super.insertString(offs, new String(result, 0, j), a);
        }
    }
}
