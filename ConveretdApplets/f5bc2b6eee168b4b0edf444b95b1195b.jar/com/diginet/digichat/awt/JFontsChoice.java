// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import com.diginet.digichat.util.c3;
import javax.swing.JComboBox;

public class JFontsChoice extends JComboBox
{
    public JFontsChoice() {
        if (c3.b != 2 || !c3.e || c3.c >= 65792) {
            final String[] fontList = this.getToolkit().getFontList();
            for (int i = 0; i < fontList.length; ++i) {
                this.addItem(fontList[i]);
            }
        }
        else {
            this.addItem("Courier");
            this.addItem("Dialog");
            this.addItem("Helvetica");
            this.addItem("TimesRoman");
        }
    }
}
