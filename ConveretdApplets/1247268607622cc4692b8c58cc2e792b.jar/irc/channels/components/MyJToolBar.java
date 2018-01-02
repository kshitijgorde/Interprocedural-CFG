// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components;

import java.awt.image.ImageObserver;
import irc.managers.Resources;
import java.awt.Graphics;
import javax.swing.JToolBar;

public class MyJToolBar extends JToolBar
{
    public MyJToolBar() {
        this.setFloatable(false);
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(Resources.getImages("nonstar"), 0, 0, this.getSize().width, this.getSize().height, null);
    }
}
