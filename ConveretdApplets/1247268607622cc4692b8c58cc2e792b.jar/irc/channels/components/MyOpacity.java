// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components;

import java.awt.image.ImageObserver;
import irc.EIRC;
import java.awt.Graphics;
import irc.managers.Resources;
import java.awt.Image;
import javax.swing.JPanel;

public class MyOpacity extends JPanel
{
    private Image bright;
    
    public MyOpacity() {
        this.bright = Resources.getImages("bg_messenger");
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(this.bright, 0, 0, this.getSize().width, this.getSize().height, EIRC.mainbg, null);
    }
}
