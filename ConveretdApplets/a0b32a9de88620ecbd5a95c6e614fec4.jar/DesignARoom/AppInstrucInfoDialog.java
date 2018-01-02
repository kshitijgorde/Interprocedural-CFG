// 
// Decompiled by Procyon v0.5.30
// 

package DesignARoom;

import java.awt.Graphics;
import java.awt.Color;

public class AppInstrucInfoDialog extends AppinfoDialog
{
    public AppInstrucInfoDialog(final String s, final int n, final int n2) {
        super(s, n, n2, Color.white);
    }
    
    public void printing(final Graphics graphics) {
        super.aidC.setBackground(super.color);
        super.y = 0;
        this.printText("Instructions for Design-A-Room", Color.black, 22, graphics);
        this.printText("are located in the folder:", Color.black, 22, graphics);
        this.printText("", Color.black, 12, graphics);
        this.printText("C:\\DesignARoom\\Instructions", Color.blue, 20, graphics);
        this.printText("", Color.black, 12, graphics);
        this.printText("In that folder, double-click on the HTML document:", Color.black, 16, graphics);
        this.printText("'Instructions.html'", Color.red, 18, graphics);
    }
}
