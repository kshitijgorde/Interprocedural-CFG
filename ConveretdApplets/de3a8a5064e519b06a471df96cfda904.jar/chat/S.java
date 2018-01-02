// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Choice;

public final class S extends Choice
{
    public S() {
        if (aZ.b != 2 || !aZ.b || aZ.c >= 65792) {
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
