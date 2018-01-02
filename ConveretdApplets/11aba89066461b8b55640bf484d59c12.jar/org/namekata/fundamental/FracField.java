// 
// Decompiled by Procyon v0.5.30
// 

package org.namekata.fundamental;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;
import javax.swing.text.Document;
import java.awt.Component;
import javax.swing.JOptionPane;
import java.util.ResourceBundle;
import javax.swing.JTextField;

public class FracField extends JTextField
{
    static boolean slash;
    static boolean minus;
    static boolean decimal;
    
    static {
        FracField.slash = false;
        FracField.minus = false;
        FracField.decimal = false;
    }
    
    public FracField(final Frac f, final int cols) {
        super(cols);
        this.setHorizontalAlignment(4);
        this.setValue(f);
    }
    
    public FracField() {
        this(Frac.Zero, 4);
    }
    
    public void setValue(final Object f) {
        this.setValue((Frac)f);
    }
    
    public void setValue(final Frac f) {
        final int format = f.getFormat();
        f.setFormat(0);
        this.setText(f.toString());
        f.setFormat(format);
    }
    
    public Frac getValue() {
        final String str = this.getText();
        Frac f;
        try {
            f = Frac.parseFrac(str);
        }
        catch (ArithmeticException ex) {
            this.message(ResourceBundle.getBundle("org.namekata.fundamental/Frac").getString("CannotDivideBy0"));
            return Frac.Zero;
        }
        catch (NumberFormatException ex2) {
            this.message(ResourceBundle.getBundle("org.namekata.fundamental/Frac").getString("InputWithFractionOrDecimal"));
            return Frac.Zero;
        }
        return f;
    }
    
    private void message(final String msg) {
        final JOptionPane p = new JOptionPane();
        JOptionPane.showMessageDialog(this, "FracField: " + msg, ResourceBundle.getBundle("org.namekata.fundamental/Frac").getString("error"), 0);
    }
    
    @Override
    protected Document createDefaultModel() {
        return new FracDocument();
    }
    
    protected class FracDocument extends PlainDocument
    {
        @Override
        public void insertString(final int offs, final String str, final AttributeSet a) throws BadLocationException {
            final char[] source = str.toCharArray();
            final char[] result = new char[source.length];
            int j = 0;
            FracField.slash = false;
            FracField.minus = false;
            FracField.decimal = false;
            for (final char ch : super.getText(0, offs + 1).toCharArray()) {
                if (ch == '/') {
                    FracField.slash = true;
                }
                else if (ch == '-') {
                    FracField.minus = true;
                }
                else if (ch == '.') {
                    FracField.decimal = true;
                }
            }
            for (int i = 0; i < result.length; ++i) {
                final char ch = source[i];
                if (Character.isDigit(ch)) {
                    result[j++] = ch;
                }
                else if (ch == '/') {
                    if (!FracField.slash && offs + j != 0) {
                        result[j++] = ch;
                        FracField.slash = true;
                    }
                }
                else if (ch == '-') {
                    if (!FracField.minus && offs + j == 0) {
                        result[j++] = ch;
                        FracField.minus = true;
                    }
                }
                else if (ch == '.' && !FracField.decimal) {
                    result[j++] = ch;
                    FracField.decimal = true;
                }
            }
            super.insertString(offs, new String(result, 0, j), a);
        }
    }
}
