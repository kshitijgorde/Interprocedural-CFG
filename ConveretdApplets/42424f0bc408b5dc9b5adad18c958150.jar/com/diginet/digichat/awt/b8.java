// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import com.diginet.digichat.util.ch;
import java.awt.Choice;

public class b8 extends Choice
{
    public b8() {
        if (ch.b != 2 || !ch.e || ch.c >= 65792) {
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
