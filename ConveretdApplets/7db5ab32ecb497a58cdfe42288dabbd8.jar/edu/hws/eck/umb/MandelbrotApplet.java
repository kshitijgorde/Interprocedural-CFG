// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.eck.umb;

import javax.swing.JMenuBar;
import javax.swing.JFrame;
import java.awt.Container;
import javax.swing.JApplet;

public class MandelbrotApplet extends JApplet
{
    public void init() {
        final MandelbrotPanel contentPane = new MandelbrotPanel();
        final MandelbrotDisplay display = contentPane.getDisplay();
        this.setContentPane(contentPane);
        this.setJMenuBar(new MandelbrotMenus(display, null, contentPane.getStatusBar(), true));
    }
}
