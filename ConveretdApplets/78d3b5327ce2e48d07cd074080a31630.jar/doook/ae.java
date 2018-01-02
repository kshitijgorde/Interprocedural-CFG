// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Choice;

public class ae extends Choice
{
    public ae() {
        if (bs.t != 2 || !bs.d || bs.g >= 65792) {
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
