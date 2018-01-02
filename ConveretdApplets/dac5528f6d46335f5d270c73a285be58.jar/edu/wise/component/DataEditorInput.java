// 
// Decompiled by Procyon v0.5.30
// 

package edu.wise.component;

import java.awt.Color;

public class DataEditorInput extends InputField
{
    public static final boolean DEBUG = false;
    
    public DataEditorInput(final String s, final DataEditor dataEditor, final int n, final int n2, final Color color, final Color color2) {
        super(s, dataEditor, n, n2, color, color2);
    }
    
    public void selected() {
        String s = super.sval;
        try {
            int i;
            for (i = 0; i < s.length(); ++i) {
                final char char1 = s.charAt(i);
                if (!Character.isDigit(char1) && char1 != '.' && char1 != '-') {
                    break;
                }
            }
            s = s.substring(0, i);
            super.de.setCurrentValue(Double.valueOf(s));
        }
        catch (NumberFormatException ex) {
            System.err.println("Not a double: '" + s + "'");
            super.de.update();
        }
    }
}
