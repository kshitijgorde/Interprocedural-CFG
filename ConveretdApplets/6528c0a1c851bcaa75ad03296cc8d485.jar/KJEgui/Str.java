// 
// Decompiled by Procyon v0.5.30
// 

package KJEgui;

import java.applet.Applet;

public class Str extends Nbr
{
    public boolean UPPER;
    public boolean NUMBERS;
    
    public Str(final String s, final String s2, final double n, final double n2, final Applet applet) {
        super(s, s2, n, n2, 0, 0, applet);
        this.UPPER = false;
        this.NUMBERS = false;
        if (this.getText().equals("0")) {
            this.setText("");
        }
    }
    
    public static String Strip(final String s) {
        final char[] array = new char[s.length()];
        int n = 0;
        if (s == null) {
            return "";
        }
        if (s.equals("")) {
            return "";
        }
        int i = 0;
        while (i < array.length) {
            switch (s.charAt(i)) {
                default: {
                    array[n] = s.charAt(i);
                    ++n;
                }
                case '<':
                case '>': {
                    ++i;
                    continue;
                }
            }
        }
        return String.valueOf(array, 0, n);
    }
    
    public boolean keypressOk(final char c) {
        String text = this.getText();
        String selectedText = this.getSelectedText();
        if (text == null) {
            text = "";
        }
        if (selectedText == null) {
            selectedText = "";
        }
        return (((c < ' ' || c > 'z') && c != '!' && c != '@' && c != '~' && c != '|' && c != '{' && c != '}') || selectedText.length() != 0 || text.length() < super._dDoubleRangeHigh) && (!this.NUMBERS || (c >= '0' && c <= '9') || ((c < ' ' || c > 'z') && c != '!' && c != '@' && c != '~' && c != '|' && c != '{' && c != '}'));
    }
    
    public String toString() throws NumberFormatException {
        String s = this.getText();
        if (s.length() < super._dDoubleRangeLow || s.length() > super._dDoubleRangeHigh) {
            throw new NumberFormatException(String.valueOf(Nbr.INPUT_ERROR_MSG) + super._sName + ".");
        }
        if (this.UPPER) {
            s = s.toUpperCase();
        }
        final String strip = Strip(s);
        this.setText(strip);
        return strip;
    }
}
