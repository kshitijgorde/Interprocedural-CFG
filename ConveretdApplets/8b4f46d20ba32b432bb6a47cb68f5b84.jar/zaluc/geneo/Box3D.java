// 
// Decompiled by Procyon v0.5.30
// 

package zaluc.geneo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

class Box3D
{
    private Globals globals;
    private Rectangle rect;
    private Rectangle fillRect;
    
    public Box3D(final Globals globals, final int x, final int y, final int width, final int height) {
        this.globals = globals;
        this.rect = new Rectangle();
        this.rect.x = x;
        this.rect.y = y;
        this.rect.width = width;
        this.rect.height = height;
        this.fillRect = new Rectangle();
        this.fillRect.x = this.rect.x + this.globals.peopleBoxBorderWidth;
        this.fillRect.y = this.rect.y + this.globals.peopleBoxBorderWidth;
        this.fillRect.width = this.rect.width - 2 * this.globals.peopleBoxBorderWidth + 1;
        this.fillRect.height = this.rect.height - 2 * this.globals.peopleBoxBorderWidth + 1;
    }
    
    public void fill(final Graphics graphics, final int n, final int n2) {
        graphics.fillRect(this.fillRect.x, this.fillRect.y, this.fillRect.width * n / n2, this.fillRect.height);
    }
    
    public void draw(final Graphics graphics) {
        if (this.globals.peopleBoxBkg != null) {
            graphics.setColor(this.globals.peopleBoxBkg);
            graphics.fillRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
        }
        if (this.globals.peopleBoxBorderWidth > 1) {
            final int y = this.rect.y;
            final int x = this.rect.x;
            final int n = this.rect.y + this.rect.height;
            final int n2 = this.rect.x + this.rect.width;
            int n3;
            int n4;
            int n5;
            if (this.globals.peopleBoxBkg != null && this.globals.backgroundImage != null) {
                n3 = this.globals.peopleBoxBkg.getRed();
                n4 = this.globals.peopleBoxBkg.getGreen();
                n5 = this.globals.peopleBoxBkg.getBlue();
            }
            else {
                n3 = this.globals.backgroundColor.getRed();
                n4 = this.globals.backgroundColor.getGreen();
                n5 = this.globals.backgroundColor.getBlue();
            }
            final Color color = new Color(n3 + 3 * ((255 - n3) / 5), n4 + 3 * ((255 - n4) / 5), n5 + 3 * ((255 - n5) / 5));
            final int n6 = n3 / 5;
            final int n7 = n4 / 5;
            final int n8 = n5 / 5;
            final Color color2 = new Color(3 * n6, 3 * n7, 3 * n8);
            final Color color3 = new Color(4 * n6, 4 * n7, 4 * n8);
            graphics.setColor(color);
            int i;
            for (i = 0; i < this.globals.peopleBoxBorderWidth - 1; ++i) {
                graphics.drawLine(n2 - i, n - i, n2 - i, y + i);
                graphics.drawLine(n2 - i, n - i, x + i, n - i);
            }
            graphics.setColor(color3);
            graphics.drawLine(n2 - i, n - i, n2 - i, y + i);
            graphics.drawLine(n2 - i, n - i, x + i, n - i);
            graphics.setColor(color3);
            int j;
            for (j = 0; j < this.globals.peopleBoxBorderWidth - 1; ++j) {
                graphics.drawLine(x + j, y + j, n2 - j, y + j);
                graphics.drawLine(x + j, y + j, x + j, n - j);
            }
            graphics.setColor(color2);
            graphics.drawLine(x + j, y + j, n2 - j, y + j);
            graphics.drawLine(x + j, y + j, x + j, n - j);
            graphics.setColor(this.globals.foregroundColor);
            return;
        }
        graphics.setColor(this.globals.foregroundColor);
        graphics.drawRect(this.rect.x, this.rect.y, this.rect.width, this.rect.height);
    }
}
