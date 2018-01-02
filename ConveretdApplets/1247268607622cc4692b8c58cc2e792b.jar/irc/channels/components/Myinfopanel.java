// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import irc.main;
import irc.EIRC;
import java.awt.Image;
import javax.swing.JComponent;

public class Myinfopanel extends JComponent
{
    private Image img;
    
    public Myinfopanel() {
        this.setOpaque(true);
        this.setBackground(EIRC.mainbg);
        this.img = main.image3;
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        this.setBackground(EIRC.mainbg);
        graphics.drawImage(this.img, 0, 0, this.getSize().width, this.getSize().height, null);
    }
}
