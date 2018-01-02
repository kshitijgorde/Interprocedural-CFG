// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import irc.main;
import irc.managers.Resources;
import irc.EIRC;
import java.awt.Image;
import javax.swing.JPanel;

public class MyPvPanel extends JPanel
{
    private Image bright;
    private Image img;
    
    public MyPvPanel() {
        this.setOpaque(true);
        this.setBackground(EIRC.mainbg);
        this.bright = Resources.getImages("bg_messenger");
        this.img = main.image3;
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        this.setBackground(EIRC.mainbg);
        graphics.drawImage(this.img, 0, 0, this.getSize().width, this.getSize().height, EIRC.mainbg, null);
        graphics.drawImage(this.bright, 0, 0, this.getSize().width, this.getSize().height, EIRC.mainbg, null);
    }
}
