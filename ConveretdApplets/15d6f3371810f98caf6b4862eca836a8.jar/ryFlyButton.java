import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;
import java.awt.Button;

// 
// Decompiled by Procyon v0.5.30
// 

public class ryFlyButton extends Button implements ActionListener
{
    String \u0111;
    PopupMenu \u0110;
    int \u010f;
    protected boolean \u010e;
    protected ryFlyer \u010d;
    protected boolean \u010c;
    protected boolean \u010b;
    private String \u010a;
    private Color \u0109;
    private Color \u0108;
    private Color \u0107;
    private Font \u0106;
    private boolean \u0105;
    private int \u0104;
    private Image \u0103;
    protected String \u0102;
    protected String \u0101;
    
    public ryFlyButton(final ryFlyer \u010d, final String \u010b, final int \u010f, final PopupMenu \u0111, final boolean \u010f2, final Font \u0107, final Color \u0109, final Color \u01092, final Color \u01072, final boolean \u0105, final int \u01052, final Image \u0103, final String \u01032, final String \u0101) {
        super("                    " + \u010b + "                    ");
        this.\u010a = \u010b;
        this.\u010f = \u010f;
        this.\u0110 = \u0111;
        (this.\u010d = \u010d).add(this.\u0110);
        this.\u010e = \u010f2;
        this.\u010c = false;
        this.\u010b = false;
        this.\u0109 = \u0109;
        this.\u0108 = \u01092;
        this.\u0107 = \u01072;
        this.\u0106 = \u0107;
        this.\u0105 = \u0105;
        this.\u0104 = \u01052;
        this.\u0103 = \u0103;
        this.\u0102 = \u01032;
        this.\u0101 = \u0101;
        if (this.\u010d.\u011d) {
            this.addMouseListener(new ryFlyMouse(this));
        }
        this.addActionListener(this);
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        if (!this.\u010e) {
            this.\u010d.showStatus("UNREGISTERED Flyer by Cool Focus [www.coolfocus.com]");
        }
        if (this.\u010f == 0) {
            this.\u0110.show(this, 0, this.bounds().height);
        }
        else if (this.\u010f == 1) {
            this.\u0110.show(this, this.bounds().width, 0);
        }
        if (this.\u0102.length() > 0) {
            this.\u010d.\u012e(this.\u0102, this.\u0101);
        }
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        if (!this.\u010d.\u011d) {
            return;
        }
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        graphics.setFont(this.\u0106);
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        graphics.setColor(this.\u0109);
        if (this.\u0104 == 0 || this.\u010b) {
            graphics.fillRect(0, 0, width, height);
            this.\u0114(graphics, new Rectangle(0, 0, width - 1, height - 1), !this.\u010c, this.\u0109);
            if (this.\u0105) {
                this.\u0114(graphics, new Rectangle(1, 1, width - 3, height - 3), !this.\u010c, this.\u0109);
            }
        }
        else if (this.\u0104 > 0 && !this.\u010b) {
            if (this.\u0104 == 1) {
                graphics.fillRect(0, 0, width, height);
            }
            else if (this.\u0104 == 2) {
                graphics.setColor(this.\u010d.\u012c);
                graphics.fillRect(0, 0, width, height);
                graphics.setColor(new Color(this.\u0113(this.\u0109.getRed()), this.\u0113(this.\u0109.getGreen()), this.\u0113(this.\u0109.getBlue())));
                graphics.drawRoundRect(0, 0, width - 1, height - 1, 3, 3);
            }
        }
        final int \u010d = this.\u010c ? 1 : 0;
        graphics.setColor(this.\u010b ? (this.\u010c ? this.\u0107.darker() : this.\u0107) : this.\u0108);
        if (this.\u0103 != null) {
            final int n = this.\u0105 ? 4 : 3;
            graphics.drawImage(this.\u0103, \u010d + n, \u010d + (height - this.\u0103.getHeight(this)) / 2, this);
            graphics.drawString(this.\u010a, \u010d + Math.max(n + this.\u0103.getWidth(this) + 3, (width - fontMetrics.stringWidth(this.\u010a)) / 2), \u010d + (height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent());
            return;
        }
        graphics.drawString(this.\u010a, \u010d + (width - fontMetrics.stringWidth(this.\u010a)) / 2, \u010d + (height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent());
    }
    
    private void \u0114(final Graphics graphics, final Rectangle rectangle, final boolean b, final Color color) {
        graphics.draw3DRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height, b);
        graphics.setColor(new Color(this.\u0113(color.getRed()), this.\u0113(color.getGreen()), this.\u0113(color.getBlue())));
        if (b) {
            graphics.drawLine(rectangle.x, rectangle.y, rectangle.x + rectangle.width - 1, rectangle.y);
            graphics.drawLine(rectangle.x, rectangle.y, rectangle.x, rectangle.y + rectangle.height);
            return;
        }
        graphics.drawLine(rectangle.x + rectangle.width, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height);
        graphics.drawLine(rectangle.x + rectangle.width, rectangle.y + rectangle.height, rectangle.x + 1, rectangle.y + rectangle.height);
    }
    
    private int \u0113(final int n) {
        return Math.min(n + 17 * n / 100, 255);
    }
    
    private int \u0112(final int n) {
        return Math.max(n - 17 * n / 100, 0);
    }
}
