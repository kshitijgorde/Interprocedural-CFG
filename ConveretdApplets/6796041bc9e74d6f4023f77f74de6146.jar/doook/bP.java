// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Choice;

public class bP extends Choice
{
    public bP() {
        if (f.h != 2 || !f.d || f.i >= 65792) {
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
