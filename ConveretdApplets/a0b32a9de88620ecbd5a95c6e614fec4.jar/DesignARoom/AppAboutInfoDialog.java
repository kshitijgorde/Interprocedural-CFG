// 
// Decompiled by Procyon v0.5.30
// 

package DesignARoom;

import java.awt.Graphics;
import java.awt.Color;

public class AppAboutInfoDialog extends AppinfoDialog
{
    public AppAboutInfoDialog(final String s, final int n, final int n2) {
        super(s, n, n2, Color.black);
    }
    
    public void printing(final Graphics graphics) {
        super.aidC.setBackground(super.color);
        super.y = 0;
        this.printText("Design-A-Room", Color.gray, 28, graphics);
        this.printText("", Color.black, 8, graphics);
        this.printText("Version 2.43 beta", Color.gray, 16, graphics);
        this.printText("", Color.black, 16, graphics);
        this.printText("Harris Rappaport", Color.red, 14, graphics);
        this.printText("harris.rappaport@hbo.com", Color.cyan.darker(), 12, graphics);
        this.printText("www2.digitalent.net/~harris", Color.cyan.darker(), 12, graphics);
        this.printText("", Color.black, 16, graphics);
        this.printText("Written in Java 1.1 with Borland JBuilder", Color.gray.brighter(), 10, graphics);
    }
}
