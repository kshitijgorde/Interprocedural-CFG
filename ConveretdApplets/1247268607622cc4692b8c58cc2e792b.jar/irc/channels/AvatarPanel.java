// 
// Decompiled by Procyon v0.5.30
// 

package irc.channels;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import irc.managers.Resources;
import java.awt.Image;
import javax.swing.JPanel;

public class AvatarPanel extends JPanel
{
    private Image _photo_top;
    private Image _photo_bottom;
    private Image cadre;
    
    public AvatarPanel() {
        this.setOpaque(true);
        this.cadre = Resources.getImages("cadre_image1");
    }
    
    public void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        if (this._photo_bottom != null) {
            graphics.drawImage(this._photo_bottom, this.getSize().width - 112, this.getSize().height - 112, 104, 104, this);
        }
        graphics.drawImage(this.cadre, this.getSize().width - 120, this.getSize().height - 120, 120, 120, this);
        if (this._photo_top != null) {
            graphics.drawImage(this._photo_top, 18, 18, 104, 104, this);
        }
        graphics.drawImage(this.cadre, 10, 10, 120, 120, this);
    }
    
    public void setBottomPhoto(final Image photo_bottom) {
        this._photo_bottom = photo_bottom;
    }
    
    public void setTopPhoto(final Image photo_top) {
        this._photo_top = photo_top;
    }
}
