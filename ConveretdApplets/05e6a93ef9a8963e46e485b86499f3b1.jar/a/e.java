// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Choice;

public final class e extends Choice
{
    public e() {
        if (bE.w != 2 || !bE.w || bE.e >= 65792) {
            final String[] fontList = this.getToolkit().getFontList();
            for (int i = 0; i < fontList.length; ++i) {
                this.addItem(fontList[i]);
            }
            return;
        }
        this.addItem("Courier");
        this.addItem("Dialog");
        this.addItem("Helvetica");
        this.addItem("TimesRoman");
    }
}
