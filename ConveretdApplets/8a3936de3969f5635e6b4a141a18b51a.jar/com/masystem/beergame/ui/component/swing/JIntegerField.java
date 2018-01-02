// 
// Decompiled by Procyon v0.5.30
// 

package com.masystem.beergame.ui.component.swing;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.Document;
import javax.swing.JTextField;

public class JIntegerField extends JTextField
{
    private static final long serialVersionUID = -7645475305891046649L;
    private int maxLength;
    private ValueChangeListener valueChangeListener;
    
    public JIntegerField() {
        this.maxLength = Integer.MAX_VALUE;
    }
    
    public final void setValueChangeListener(final ValueChangeListener valueChangeListener) {
        this.valueChangeListener = valueChangeListener;
    }
    
    public final void setMaxLength(final int maxLength) {
        this.maxLength = maxLength;
    }
    
    public final void clearValue() {
        this.setText(null);
    }
    
    public final int getValue() {
        try {
            return Integer.parseInt(this.getText());
        }
        catch (NumberFormatException ex) {
            return -1;
        }
    }
    
    @Override
    protected Document createDefaultModel() {
        return new IntegerDocument();
    }
    
    public final class IntegerDocument extends PlainDocument
    {
        private static final long serialVersionUID = 1L;
        
        @Override
        public final void remove(final int n, final int n2) throws BadLocationException {
            super.remove(n, n2);
            if (JIntegerField.this.valueChangeListener != null) {
                JIntegerField.this.valueChangeListener.onValueChanged(JIntegerField.this.getValue());
            }
        }
        
        @Override
        public final void insertString(final int n, final String s, final AttributeSet set) throws BadLocationException {
            if (s == null) {
                return;
            }
            if (this.getLength() + s.length() > JIntegerField.this.maxLength) {
                return;
            }
            try {
                Integer.parseInt(s);
            }
            catch (NumberFormatException ex) {
                return;
            }
            super.insertString(n, s, set);
            if (JIntegerField.this.valueChangeListener != null) {
                JIntegerField.this.valueChangeListener.onValueChanged(JIntegerField.this.getValue());
            }
        }
    }
}
