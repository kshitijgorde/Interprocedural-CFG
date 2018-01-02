// 
// Decompiled by Procyon v0.5.30
// 

package jchatbox.client.gui;

import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Component;

public class jcbLabel extends Component
{
    String _txt;
    Image _image;
    
    public jcbLabel() {
        this._txt = null;
        this._image = null;
    }
    
    public jcbLabel(final String txt) {
        this._txt = null;
        this._image = null;
        this._txt = txt;
    }
    
    public jcbLabel(final Image image) {
        this._txt = null;
        this._image = null;
        this._image = image;
    }
    
    public void setText(final String txt) {
        this._txt = txt;
        this.repaint();
    }
    
    public void setImage(final Image image) {
        this._image = image;
        this.repaint();
    }
    
    public String getText() {
        return this._txt;
    }
    
    public void paint(final Graphics graphics) {
        if (this._image != null) {
            graphics.drawImage(this._image, 0, 0, this.getSize().width, this.getSize().height, null);
        }
        if (this.getFont() != null && this._txt != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            graphics.setColor(this.getForeground());
            final StringTokenizer stringTokenizer = new StringTokenizer(this._txt, "|");
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                graphics.drawString((String)stringTokenizer.nextElement(), 0, n + fontMetrics.getHeight());
                n += fontMetrics.getHeight();
            }
        }
    }
}
