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
    
    public boolean keypressOk(final char c) {
        final String text = this.getText();
        return (((c < ' ' || c > 'z') && c != '!' && c != '@' && c != '~' && c != '|' && c != '{' && c != '}') || this.getSelectedText().length() != 0 || text.length() < super._dDoubleRangeHigh) && (!this.NUMBERS || (c >= '0' && c <= '9') || ((c < ' ' || c > 'z') && c != '!' && c != '@' && c != '~' && c != '|' && c != '{' && c != '}'));
    }
    
    public String toString() throws NumberFormatException {
        String text = this.getText();
        if (text.length() < super._dDoubleRangeLow || text.length() > super._dDoubleRangeHigh) {
            throw new NumberFormatException(String.valueOf(Nbr.INPUT_ERROR_MSG) + super._sName + ".");
        }
        if (this.UPPER) {
            text = text.toUpperCase();
        }
        this.setText(text);
        return text;
    }
}
