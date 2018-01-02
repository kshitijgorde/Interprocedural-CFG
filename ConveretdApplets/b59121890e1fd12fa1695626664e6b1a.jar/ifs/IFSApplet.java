// 
// Decompiled by Procyon v0.5.30
// 

package ifs;

import javax.swing.JMenuBar;
import java.awt.Container;
import javax.swing.JApplet;

public class IFSApplet extends JApplet
{
    public void init() {
        final IFSCanvas ifs = new IFSCanvas();
        final JMenuBar menus = ifs.getMenuBar(true);
        this.setContentPane(ifs);
        this.setJMenuBar(menus);
    }
}
