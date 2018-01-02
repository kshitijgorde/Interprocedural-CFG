import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class HotSpot extends Canvas implements MouseListener
{
    private static HotSpotListener hsl;
    private Color fgcolor1;
    private Color fgcolor2;
    private Color fgcolor3;
    private Color bgcolor1;
    private Color bgcolor2;
    private Color bgcolor3;
    private String mesg1;
    private String mesg2;
    private String mesg3;
    private Image bgimage1;
    private Image bgimage2;
    private Image bgimage3;
    private int width;
    private int height;
    private boolean mouseOver;
    private boolean mousePressed;
    private boolean enabled;
    private boolean useImages;
    private boolean action;
    private FontMetrics fm;
    private int fontwidth;
    private int fontsize;
    private Image buff;
    private Graphics g;
    
    public HotSpot(final HotSpotListener hsl) {
        this.bgcolor1 = new Color(122, 122, 122);
        this.bgcolor2 = new Color(188, 188, 188);
        this.bgcolor3 = new Color(0, 0, 0);
        this.fgcolor1 = new Color(0, 0, 0);
        this.fgcolor2 = new Color(48, 48, 48);
        this.fgcolor3 = new Color(255, 255, 255);
        this.mesg1 = "";
        this.mesg2 = "";
        this.mesg3 = "";
        this.setBackground(Color.yellow);
        HotSpot.hsl = hsl;
        this.addMouseListener(this);
    }
    
    public HotSpot(final String mesg1, final HotSpotListener hotSpotListener) {
        this(hotSpotListener);
        this.mesg1 = mesg1;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.mouseOver = true;
        this.setCursor(new Cursor(12));
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.mouseOver = false;
        this.mousePressed = false;
        this.setCursor(new Cursor(0));
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.mousePressed = true;
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        HotSpot.hsl.hotSpotEvent(this);
        this.mousePressed = false;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.buff == null) {
            this.buff = this.createImage(size.width, size.height);
            this.g = this.buff.getGraphics();
        }
        if (this.mousePressed) {
            this.g.setColor(this.bgcolor3);
            this.g.fillRect(0, 0, size.width, size.height);
            if (this.bgimage3 != null) {
                this.g.drawImage(this.bgimage3, size.width / 2 - this.bgimage3.getWidth(this) / 2, size.height / 2 - this.bgimage3.getHeight(this) / 2, this);
            }
        }
        else if (this.mouseOver) {
            this.g.setColor(this.bgcolor2);
            this.g.fillRect(0, 0, size.width, size.height);
            if (this.bgimage2 != null) {
                this.g.drawImage(this.bgimage2, size.width / 2 - this.bgimage2.getWidth(this) / 2, size.height / 2 - this.bgimage2.getHeight(this) / 2, this);
            }
        }
        else {
            this.g.setColor(this.bgcolor1);
            this.g.fillRect(0, 0, size.width, size.height);
            if (this.bgimage1 != null) {
                this.g.drawImage(this.bgimage1, size.width / 2 - this.bgimage1.getWidth(this) / 2, size.height / 2 - this.bgimage1.getHeight(this) / 2, this);
            }
        }
        graphics.drawImage(this.buff, 0, 0, this);
    }
    
    public void setImage(final Image image, final int n) {
        switch (n) {
            case 1: {
                this.bgimage1 = image;
                break;
            }
            case 2: {
                this.bgimage2 = image;
                break;
            }
            case 3: {
                this.bgimage3 = image;
                break;
            }
            default: {
                this.bgimage1 = image;
                break;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
