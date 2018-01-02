// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Choice;

public class aJ extends Choice
{
    public aJ() {
        if (F.f != 2 || !F.b || F.a >= 65792) {
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
