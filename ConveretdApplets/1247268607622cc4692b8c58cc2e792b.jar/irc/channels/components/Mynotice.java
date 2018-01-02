// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import irc.managers.Resources;
import irc.EIRC;
import java.awt.Image;
import javax.swing.JPanel;

public class Mynotice extends JPanel
{
    Image image;
    EIRC eirc;
    
    public Mynotice(final EIRC eirc) {
        this.eirc = eirc;
        this.setOpaque(true);
        this.setBackground(EIRC.mainbg);
        this.image = Resources.getImages("Mynotice");
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        final EIRC eirc = this.eirc;
        this.setBackground(EIRC.mainbg);
        graphics.drawImage(this.image, 0, 0, this.getSize().width, this.getSize().height, EIRC.mainbg, null);
    }
}
