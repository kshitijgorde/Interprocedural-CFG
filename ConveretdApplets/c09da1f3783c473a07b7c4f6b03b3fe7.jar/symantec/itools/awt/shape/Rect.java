// 
// Decompiled by Procyon v0.5.30
// 

package symantec.itools.awt.shape;

import java.awt.Graphics;

public class Rect extends Shape
{
    public boolean contains(final int x, final int y) {
        if (super.fill) {
            return super.contains(x, y);
        }
        if (y == 0 || y == super.height - 1) {
            return x >= 0 && x < super.width;
        }
        return (x == 0 || x == super.width - 1) && y >= 0 && y < super.height;
    }
    
    public void paint(final Graphics g) {
        super.paint(g);
        g.clipRect(0, 0, super.width, super.height);
        final int w = super.width - 1;
        final int h = super.height - 1;
        switch (super.style) {
            case 2: {
                if (super.fill) {
                    g.setColor(super.fillColor);
                    g.fillRect(0, 0, w, h);
                }
                g.setColor(this.getForeground());
                g.drawRect(0, 0, w, h);
                break;
            }
            case 0: {
                if (super.fill) {
                    g.setColor(super.fillColor);
                    g.fillRect(1, 1, w - 1, h - 1);
                }
                g.setColor(super.bevelDarkerColor);
                g.drawLine(0, h, 0, 0);
                g.drawLine(0, 0, w, 0);
                g.setColor(super.bevelLighterColor);
                g.drawLine(w, 0, w, h);
                g.drawLine(w, h, 0, h);
                break;
            }
            case 1: {
                if (super.fill) {
                    g.setColor(super.fillColor);
                    g.fillRect(1, 1, w - 1, h - 1);
                }
                g.setColor(super.bevelLighterColor);
                g.drawLine(0, h, 0, 0);
                g.drawLine(0, 0, w, 0);
                g.setColor(super.bevelDarkerColor);
                g.drawLine(w, 0, w, h);
                g.drawLine(w, h, 0, h);
                break;
            }
            default: {
                if (super.fill) {
                    g.setColor(super.fillColor);
                    g.fillRect(0, 0, super.width, super.height);
                    break;
                }
                g.setColor(this.getForeground());
                g.drawRect(0, 0, w, h);
                break;
            }
        }
    }
}
