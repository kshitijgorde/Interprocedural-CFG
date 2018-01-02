// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import irc.managers.Resources;
import java.awt.Image;
import javax.swing.JComponent;

public class MyJpub extends JComponent
{
    Image pub;
    Image font;
    
    public MyJpub() {
        this.setOpaque(true);
        this.font = Resources.getImages("fpub");
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        this.setOpaque(false);
        graphics.drawImage(this.font, 0, 0, this.getSize().width, this.getSize().height, null);
        if (this.pub != null) {
            graphics.drawImage(this.pub, (this.getSize().width - 243) / 2, 58, 243, 60, null);
        }
    }
    
    public void setImgepub(final Image pub) {
        this.pub = pub;
    }
}
