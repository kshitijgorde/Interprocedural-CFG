// 
// Decompiled by Procyon v0.5.30
// 

package poemsprice.FXClass;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.IOException;
import java.awt.Image;
import java.awt.Canvas;

public class ImgCanvas extends Canvas
{
    private Image imgUp;
    private Image imgDown;
    private Image imgNormal;
    private Image imgNoChange;
    private int ShowImage;
    private int myWidth;
    private int myHeight;
    
    public ImgCanvas(final Image up, final Image down, final Image nochange, final Image normal) throws IOException {
        this.ShowImage = 0;
        this.myWidth = 18;
        this.myHeight = 18;
        this.imgUp = up;
        this.imgDown = down;
        this.imgNoChange = nochange;
        this.imgNormal = normal;
        this.ShowImage = -2;
        super.setSize(this.myWidth, this.myHeight);
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
        if (this.ShowImage != -2) {
            if (this.ShowImage == 1) {
                g.drawImage(this.imgUp, 0, 0, this.myWidth, this.myHeight, this);
            }
            else if (this.ShowImage == -1) {
                g.drawImage(this.imgDown, 0, 0, this.myWidth, this.myHeight, this);
            }
            else if (this.ShowImage == 0) {
                g.drawImage(this.imgNoChange, 0, 0, this.myWidth, this.myHeight, this);
            }
            else {
                g.drawImage(this.imgNormal, 0, 0, this.myWidth, this.myHeight, this);
            }
        }
    }
    
    public void setImageType(final int myShowImage) {
        this.ShowImage = myShowImage;
        this.repaint();
    }
}
