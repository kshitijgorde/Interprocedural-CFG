import java.awt.image.ImageObserver;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

final class HotSpot extends Canvas implements MouseListener
{
    static HotSpotListener hsl;
    private Color fgcolor1;
    private Color fgcolor2;
    private Color fgcolor3;
    private Color bgcolor1;
    private Color bgcolor2;
    private Color bgcolor3;
    private String mesg1;
    private String mesg2;
    private String mesg3;
    private Font font1;
    private Font font2;
    private Font font3;
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
    private Dimension dim;
    private Image buff;
    private Graphics g;
    private int bug;
    
    public HotSpot(final HotSpotListener hsl) {
        this.bgcolor1 = new Color(0, 0, 0);
        this.bgcolor2 = new Color(0, 0, 0);
        this.bgcolor3 = new Color(0, 0, 0);
        this.fgcolor1 = new Color(0, 0, 0);
        this.fgcolor2 = new Color(48, 48, 48);
        this.fgcolor3 = new Color(255, 255, 255);
        this.mesg1 = "Click Here";
        this.mesg2 = "Click now!";
        this.mesg3 = "Thanks!";
        this.font1 = new Font("Arial", 0, 14);
        this.font2 = new Font("Arial", 0, 14);
        this.font3 = new Font("Arial", 0, 14);
        this.setBackground(Color.black);
        HotSpot.hsl = hsl;
        this.dim = this.getSize();
        this.addMouseListener(this);
    }
    
    public HotSpot(final String mesg1, final HotSpotListener hotSpotListener) {
        this(hotSpotListener);
        this.mesg1 = mesg1;
        this.dim = this.getSize();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.repaint();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.mouseOver = true;
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.mouseOver = false;
        this.mousePressed = false;
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
    
    public void noText(final boolean b) {
        if (b) {
            this.mesg1 = "";
            this.mesg2 = "";
            this.mesg3 = "";
        }
        else {
            this.mesg1 = "Click Here";
            this.mesg2 = "Click Now!";
            this.mesg3 = "Thanks!";
        }
    }
    
    public void paint(final Graphics graphics) {
        this.dim = this.getSize();
        if (this.buff == null) {
            this.buff = this.createImage(this.dim.width, this.dim.height);
            this.g = this.buff.getGraphics();
        }
        if (this.mousePressed) {
            this.g.setColor(this.bgcolor3);
            this.g.fillRect(0, 0, this.dim.width, this.dim.height);
            if (this.bgimage3 != null) {
                this.g.drawImage(this.bgimage3, this.dim.width / 2 - this.bgimage3.getWidth(this) / 2, this.dim.height / 2 - this.bgimage3.getHeight(this) / 2, this);
            }
            this.g.setColor(this.fgcolor3);
            this.g.setFont(this.font3);
            this.fm = this.getFontMetrics(this.g.getFont());
            this.fontwidth = this.fm.stringWidth(this.mesg3);
            this.g.setFont(this.font3);
            this.g.drawString(this.mesg3, this.dim.width / 2 - this.fontwidth / 2, this.dim.height / 2);
        }
        else if (this.mouseOver) {
            this.g.setColor(this.bgcolor2);
            this.g.fillRect(0, 0, this.dim.width, this.dim.height);
            if (this.bgimage2 != null) {
                this.g.drawImage(this.bgimage2, this.dim.width / 2 - this.bgimage2.getWidth(this) / 2, this.dim.height / 2 - this.bgimage2.getHeight(this) / 2, this);
            }
            this.g.setColor(this.fgcolor2);
            this.g.setFont(this.font2);
            this.fm = this.getFontMetrics(this.g.getFont());
            this.fontwidth = this.fm.stringWidth(this.mesg2);
            this.g.drawString(this.mesg2, this.dim.width / 2 - this.fontwidth / 2, this.dim.height / 2);
        }
        else {
            this.g.setColor(this.bgcolor1);
            this.g.fillRect(0, 0, this.dim.width, this.dim.height);
            if (this.bgimage1 != null) {
                this.g.drawImage(this.bgimage1, this.dim.width / 2 - this.bgimage1.getWidth(this) / 2, this.dim.height / 2 - this.bgimage1.getHeight(this) / 2, this);
            }
            this.g.setColor(this.fgcolor1);
            this.g.setFont(this.font1);
            this.fm = this.getFontMetrics(this.g.getFont());
            this.fontwidth = this.fm.stringWidth(this.mesg1);
            this.g.drawString(this.mesg1, this.dim.width / 2 - this.fontwidth / 2, this.dim.height / 2);
        }
        graphics.drawImage(this.buff, 0, 0, this);
    }
    
    public void setBackground(final Color color, final int n) {
        switch (n) {
            case 1: {
                this.bgcolor1 = color;
                break;
            }
            case 2: {
                this.bgcolor2 = color;
                break;
            }
            case 3: {
                this.bgcolor3 = color;
                break;
            }
            default: {
                this.bgcolor1 = color;
                break;
            }
        }
    }
    
    public void setFont(final Font font, final int n) {
        switch (n) {
            case 1: {
                this.font1 = font;
                break;
            }
            case 2: {
                this.font2 = font;
                break;
            }
            case 3: {
                this.font3 = font;
                break;
            }
            default: {
                this.font1 = font;
                break;
            }
        }
    }
    
    public void setForeground(final Color color, final int n) {
        switch (n) {
            case 1: {
                this.fgcolor1 = color;
                break;
            }
            case 2: {
                this.fgcolor2 = color;
                break;
            }
            case 3: {
                this.fgcolor3 = color;
                break;
            }
            default: {
                this.fgcolor1 = color;
                break;
            }
        }
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
    
    public void setMessage(final String s, final int n) {
        switch (n) {
            case 1: {
                this.mesg1 = s;
                break;
            }
            case 2: {
                this.mesg2 = s;
                break;
            }
            case 3: {
                this.mesg3 = s;
                break;
            }
            default: {
                this.mesg1 = s;
                break;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
