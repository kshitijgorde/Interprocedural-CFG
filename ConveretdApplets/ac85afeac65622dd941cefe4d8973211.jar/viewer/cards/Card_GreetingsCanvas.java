// 
// Decompiled by Procyon v0.5.30
// 

package viewer.cards;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

public class Card_GreetingsCanvas extends Canvas
{
    public Image pic;
    
    public Card_GreetingsCanvas() {
        this.setBackground(Color.white);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.pic, (this.size().width - this.pic.getWidth(this)) / 2, (this.size().height - this.pic.getHeight(this)) / 2, this);
    }
}
