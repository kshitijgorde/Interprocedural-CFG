// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client.gui;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public class jcbPanel extends Panel
{
    private Image _$2086;
    
    public jcbPanel() {
        this._$2086 = null;
        this._$2086 = null;
    }
    
    public jcbPanel(final Image $2086) {
        this._$2086 = null;
        this._$2086 = $2086;
    }
    
    public void setImage(final Image $2086) {
        this._$2086 = $2086;
    }
    
    public void paint(final Graphics graphics) {
        if (this._$2086 != null) {
            graphics.drawImage(this._$2086, 0, 0, this.getSize().width, this.getSize().height, null);
        }
        super.paint(graphics);
    }
}
