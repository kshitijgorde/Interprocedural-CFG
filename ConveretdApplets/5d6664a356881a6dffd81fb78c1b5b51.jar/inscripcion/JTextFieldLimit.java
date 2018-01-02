// 
// Decompiled by Procyon v0.5.30
// 

package inscripcion;

import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument
{
    private int limite;
    private boolean soloNumeros;
    private boolean enMayusculas;
    
    JTextFieldLimit(final int lmt) {
        this.soloNumeros = false;
        this.enMayusculas = true;
        this.limite = lmt;
    }
    
    JTextFieldLimit(final int lmt, final boolean nmr) {
        this.soloNumeros = false;
        this.enMayusculas = true;
        this.limite = lmt;
        this.soloNumeros = nmr;
    }
    
    JTextFieldLimit(final int lmt, final boolean nmr, final boolean myc) {
        this.soloNumeros = false;
        this.enMayusculas = true;
        this.limite = lmt;
        this.soloNumeros = nmr;
        this.enMayusculas = myc;
    }
    
    public void insertString(final int offset, String str, final AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }
        if (this.getLength() + str.length() <= this.limite) {
            if (this.soloNumeros & (str.charAt(0) < '0' | str.charAt(0) > '9')) {
                str = "";
            }
            else {
                if (str.equals("º") | str.equals("ª")) {
                    str = "";
                }
                if (this.enMayusculas) {
                    str = str.toUpperCase();
                    str = str.replace('\u00c1', 'A');
                    str = str.replace('\u00c9', 'E');
                    str = str.replace('\u00cd', 'I');
                    str = str.replace('\u00d3', 'O');
                    str = str.replace('\u00da', 'U');
                }
                else {
                    str = str.toLowerCase();
                    str = str.replace('\u00e1', 'a');
                    str = str.replace('\u00e9', 'e');
                    str = str.replace('\u00ed', 'i');
                    str = str.replace('\u00f3', 'o');
                    str = str.replace('\u00fa', 'u');
                }
            }
            super.insertString(offset, str, attr);
        }
    }
}
