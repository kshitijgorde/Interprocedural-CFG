// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client.gui;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

public class DBApplet extends Applet
{
    private Image _$9721;
    
    public DBApplet() {
        this._$9721 = null;
    }
    
    public void update(final Graphics graphics) {
        if (this._$9721 == null || this._$9721.getWidth(this) != this.size().width || this._$9721.getHeight(this) != this.size().height) {
            this._$9721 = this.createImage(this.size().width, this.size().height);
        }
        this.paint(this._$9721.getGraphics());
        graphics.drawImage(this._$9721, 0, 0, this);
    }
}
