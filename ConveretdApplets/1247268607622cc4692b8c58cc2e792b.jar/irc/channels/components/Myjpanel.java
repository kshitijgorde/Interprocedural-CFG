// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components;

import java.awt.image.ImageObserver;
import irc.EIRC;
import irc.main;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JComponent;

public class Myjpanel extends JComponent
{
    public Myjpanel() {
        this.setOpaque(true);
        this.setBackground(Color.decode("0xADD8E6"));
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        this.setOpaque(false);
        graphics.drawImage(main.image5, 0, 0, this.getSize().width, this.getSize().height, EIRC.mainbg, null);
    }
}
