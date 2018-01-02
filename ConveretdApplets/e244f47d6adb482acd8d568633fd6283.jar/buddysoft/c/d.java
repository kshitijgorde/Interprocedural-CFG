// 
// Decompiled by Procyon v0.5.30
// 

package buddysoft.c;

import java.awt.Polygon;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

public class d extends b
{
    private int width;
    private int height;
    private Color try;
    private Color int;
    private Color new;
    
    public d(final String if1, final Color background, final String for1) {
        super.if = if1;
        this.setBackground(background);
        super.for = for1;
    }
    
    public void paint(final Graphics graphics) {
        this.width = this.size().width - 1;
        this.height = this.size().height - 1;
        graphics.setColor(this.getParent().getBackground());
        graphics.fillRect(0, 0, this.width + 1, this.height + 1);
        graphics.setColor(this.getBackground());
        graphics.fillRect(1, 1, this.width - 2, this.height - 2);
        graphics.setColor(super.do ? this.getBackground().darker() : this.getBackground().brighter());
        graphics.drawLine(2, 1, this.width - 2, 1);
        graphics.drawLine(2, 2, this.width - 3, 2);
        graphics.drawLine(1, 2, 1, this.height - 2);
        graphics.drawLine(2, 3, 2, this.height - 3);
        graphics.fillRect(3, 3, 1, 1);
        graphics.setColor(super.do ? this.getBackground().brighter() : this.getBackground().darker());
        graphics.drawLine(3, this.height - 2, this.width - 3, this.height - 2);
        graphics.drawLine(2, this.height - 1, this.width - 2, this.height - 1);
        graphics.drawLine(this.width - 2, 3, this.width - 2, this.height - 1);
        graphics.drawLine(this.width - 1, 2, this.width - 1, this.height - 2);
        graphics.fillRect(this.width - 3, this.height - 3, 1, 1);
        graphics.fillRect(this.width - 3, 3, 1, 1);
        graphics.fillRect(3, this.height - 3, 1, 1);
        graphics.fillRect(this.width - 2, this.height - 2, 1, 1);
        graphics.setColor(Color.black);
        graphics.drawRoundRect(0, 0, this.width, this.height, 7, 7);
        this.a(graphics);
    }
    
    public Dimension minimumSize() {
        return new Dimension(41, 19);
    }
    
    private void a(final Graphics graphics) {
        graphics.setColor(this.getForeground());
        if (!super.do) {
            if (super.if.equals("stop")) {
                graphics.fillRect(this.width / 2 - 4, this.height / 2 - 4, 9, 9);
            }
            else if (super.if.equals("pause")) {
                graphics.fillRect(this.width / 2 - 3, this.height / 2 - 4, 3, 9);
                graphics.fillRect(this.width / 2 + 2, this.height / 2 - 4, 3, 9);
            }
            else if (super.if.equals("play")) {
                graphics.fillPolygon(new Polygon(new int[] { this.width / 2 - 4, this.width / 2 - 4, this.width / 2 + 5 }, new int[] { this.height / 2 - 5, this.height / 2 + 5, this.height / 2 }, 3));
            }
            else if (super.if.equals("reset")) {
                graphics.fillPolygon(new Polygon(new int[] { this.width / 2 - 2, this.width / 2 + 8, this.width / 2 + 8 }, new int[] { this.height / 2, this.height / 2 - 5, this.height / 2 + 5 }, 3));
                graphics.fillRect(this.width / 2 - 6, this.height / 2 - 4, 3, 9);
            }
            else if (super.if.equals("next")) {
                graphics.fillPolygon(new Polygon(new int[] { this.width / 2, this.width / 2, this.width / 2 + 10 }, new int[] { this.height / 2 - 5, this.height / 2 + 5, this.height / 2 }, 3));
                graphics.fillRect(this.width / 2 - 5, this.height / 2 - 4, 3, 9);
            }
            else if (super.if.equals("prev")) {
                graphics.fillPolygon(new Polygon(new int[] { this.width / 2 - 8, this.width / 2 + 2, this.width / 2 + 2 }, new int[] { this.height / 2, this.height / 2 - 5, this.height / 2 + 5 }, 3));
                graphics.fillRect(this.width / 2 + 4, this.height / 2 - 4, 3, 9);
            }
        }
        else if (super.if.equals("stop")) {
            graphics.fillRect(this.width / 2 - 3, this.height / 2 - 3, 9, 9);
        }
        else if (super.if.equals("pause")) {
            graphics.fillRect(this.width / 2 - 2, this.height / 2 - 3, 3, 9);
            graphics.fillRect(this.width / 2 + 3, this.height / 2 - 3, 3, 9);
        }
        else if (super.if.equals("play")) {
            graphics.fillPolygon(new Polygon(new int[] { this.width / 2 - 3, this.width / 2 - 3, this.width / 2 + 6 }, new int[] { this.height / 2 - 4, this.height / 2 + 6, this.height / 2 + 1 }, 3));
        }
        else if (super.if.equals("reset")) {
            graphics.fillPolygon(new Polygon(new int[] { this.width / 2 - 2, this.width / 2 + 8, this.width / 2 + 8 }, new int[] { this.height / 2 + 1, this.height / 2 - 4, this.height / 2 + 6 }, 3));
            graphics.fillRect(this.width / 2 - 6, this.height / 2 - 3, 3, 9);
        }
        else if (super.if.equals("next")) {
            graphics.fillPolygon(new Polygon(new int[] { this.width / 2, this.width / 2, this.width / 2 + 10 }, new int[] { this.height / 2 - 4, this.height / 2 + 6, this.height / 2 + 1 }, 3));
            graphics.fillRect(this.width / 2 - 5, this.height / 2 - 3, 3, 9);
        }
        else if (super.if.equals("prev")) {
            graphics.fillPolygon(new Polygon(new int[] { this.width / 2 - 7, this.width / 2 + 3, this.width / 2 + 3 }, new int[] { this.height / 2 + 1, this.height / 2 - 4, this.height / 2 + 6 }, 3));
            graphics.fillRect(this.width / 2 + 5, this.height / 2 - 3, 3, 9);
        }
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
}
