// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.c;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Panel;

public class a extends Panel
{
    private boolean int;
    private int width;
    private int height;
    private int for;
    private Insets a;
    private String do;
    private Image if;
    
    public a(final int n, final int n2, final int n3, final int n4, final int for1, final boolean int1) {
        this.a = new Insets(n, n2, n3, n4);
        this.for = for1;
        this.int = int1;
    }
    
    public a(final int n, final int n2, final int n3, final int n4, final Image image) {
        this.a = new Insets(n, n2, n3, n4);
        this.for = 1;
    }
    
    public void paint(final Graphics graphics) {
        this.width = this.size().width - 1;
        this.height = this.size().height - 1;
        if (this.for == 1) {
            graphics.drawImage(this.if, 0, 0, this);
        }
        else if (this.for == 4) {
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(0, 0, this.width, this.height, this.int);
        }
        else if (this.for == 5) {
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(1, 1, this.width - 2, this.height - 2, this.int);
            graphics.setColor(Color.black);
            graphics.drawRoundRect(0, 0, this.width, this.height, 5, 5);
        }
        else if (this.for == 6) {
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(1, 1, this.width - 2, this.height - 2, this.int);
            graphics.draw3DRect(2, 2, this.width - 4, this.height - 4, this.int);
            graphics.setColor(Color.black);
            graphics.drawRoundRect(0, 0, this.width, this.height, 5, 5);
        }
    }
    
    public Insets insets() {
        return this.a;
    }
}
