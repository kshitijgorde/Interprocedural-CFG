// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels.components;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import irc.managers.Resources;
import irc.main;
import java.awt.Image;
import javax.swing.JComponent;

public class MyCadre extends JComponent
{
    private Image cadre;
    private main m;
    private Image avatarInconnuH;
    private Image avatarInconnuF;
    
    public MyCadre(final main m) {
        this.setOpaque(true);
        this.cadre = Resources.getImages("cadre_image1");
        this.m = m;
        this.avatarInconnuH = Resources.getImages("avatar.inconnuh");
        this.avatarInconnuF = Resources.getImages("avatar.inconnuf");
    }
    
    public void paintComponent(final Graphics graphics) {
        if (main.imagecadre != null) {
            graphics.drawImage(main.imagecadre, 8, 8, 104, 104, this);
        }
        else if (this.m.homme.isSelected()) {
            graphics.drawImage(this.avatarInconnuH, 8, 8, 104, 104, this);
        }
        else {
            graphics.drawImage(this.avatarInconnuF, 8, 8, 104, 104, this);
        }
        graphics.drawImage(this.cadre, 0, 0, 120, 120, this);
    }
}
