import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class MenItem extends Panel implements MouseListener
{
    private CascadeMenu cm;
    private String label;
    private Color color1;
    private Color color2;
    private Color stColor;
    private Color textColor;
    private Color textOverColor;
    private Color tColor;
    private Font font1;
    private Font font2;
    private Font font;
    private ItemCanvas ic;
    private boolean active;
    private ItemCanvas subIc;
    private boolean hasSub;
    private Item item;
    private Polygon p;
    private int offsetx;
    private Graphics gbuf;
    private Image buf;
    
    public MenItem(final Item item, final CascadeMenu cm, final ItemCanvas ic) {
        this.offsetx = 20;
        this.ic = ic;
        this.item = item;
        this.setLayout(null);
        this.color1 = cm.getColor("Item", 0, 0);
        this.color2 = cm.getColor("Item", 1, 0);
        this.textColor = cm.getColor("Item", 0, 1);
        this.textOverColor = cm.getColor("Item", 1, 1);
        this.font1 = cm.getFont("Item", 0);
        this.font2 = cm.getFont("Item", 1);
        this.font = this.font1;
        this.stColor = this.color1;
        this.tColor = this.textColor;
        this.setBackground(this.stColor);
        this.label = item.getText();
        this.cm = cm;
        this.addMouseListener(this);
        if (item.hasSub()) {
            this.giveSub(item);
        }
    }
    
    public int fontAlign() {
        final FontMetrics fontMetrics = this.cm.getFontMetrics(this.font);
        return (this.getSize().height - fontMetrics.getMaxDescent() + fontMetrics.getMaxAscent()) / 2;
    }
    
    public int fontCenter() {
        return this.getSize().width / 2 - this.fontWidth() / 2;
    }
    
    public int fontWidth() {
        return this.cm.getFontMetrics(this.font).stringWidth(this.label);
    }
    
    private int getBestXPosition() {
        int n = this.getLocation().x + this.ic.getSize().width + this.ic.getLocation().x - 10;
        final int prefferedWidth = this.subIc.getPrefferedWidth();
        if (n + prefferedWidth > this.cm.getWidth() - 5) {
            n = this.getLocation().x + this.ic.getLocation().x - prefferedWidth;
        }
        return n;
    }
    
    private int getBestYPosition() {
        int n = this.getLocation().y + this.ic.getLocation().y - 2;
        final int n2 = this.subIc.getItems() * 15 + 4;
        final int height = this.cm.getHeight();
        if (n + n2 > height - 5) {
            n = height - n2 - 5;
        }
        return n;
    }
    
    public void giveSub(final Item item) {
        this.hasSub = true;
        this.subIc = new ItemCanvas(item.getMenuSet(), this.cm, false);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.removeSubs();
        this.active = true;
        this.stColor = this.color2;
        this.tColor = this.textOverColor;
        this.font = this.font2;
        this.repaint();
        if (this.item.hasAction()) {
            this.cm.show(this.item.getAction().toString());
        }
        if (this.hasSub) {
            this.subIc.setLocation(this.getBestXPosition(), this.getBestYPosition());
            this.subIc.setSize(0, 0);
            this.cm.add(this.subIc);
            this.subIc.animate(this.subIc.getItems() * this.cm.getItemHeight() + 4);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.cm.show("");
        this.active = false;
        this.stColor = this.color1;
        this.tColor = this.textColor;
        this.font = this.font1;
        this.repaint();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.item.hasAction()) {
            this.cm.performAction(this.item);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (!this.ic.animating) {
            final int width = this.getSize().width;
            final int height = this.getSize().height;
            if (this.buf == null) {
                this.buf = this.cm.createImage(width, height);
                this.gbuf = this.buf.getGraphics();
            }
            this.p = new Polygon(new int[] { width - 10, width - 10, width - 6 }, new int[] { height / 2 - 3, height / 2 + 3, height / 2 }, 3);
            this.gbuf.setFont(this.font);
            if (this.active) {
                this.gbuf.setColor(this.stColor);
                this.gbuf.fillRect(0, 0, width, height);
            }
            else {
                this.gbuf.setColor(this.stColor);
                this.gbuf.fillRect(0, 0, width, height);
            }
            this.gbuf.setColor(this.tColor);
            if (this.hasSub) {
                this.gbuf.fillPolygon(this.p);
            }
            if (this.item.getIcon() != null) {
                this.gbuf.drawImage(this.item.getIcon(), 0, height / 2 - 8, this);
            }
            this.gbuf.drawString(this.label, this.offsetx, this.fontAlign());
        }
        if (this.buf != null && !this.ic.animating) {
            graphics.drawImage(this.buf, 0, 0, this);
        }
    }
    
    public void removeAll() {
        if (this.subIc != null) {
            this.subIc.removeAll();
            this.cm.remove(this.subIc);
        }
    }
    
    public void removeSubs() {
        this.ic.removeNestedSubs();
    }
    
    public String toString() {
        return "Label: " + this.label + " Size: " + this.getBounds();
    }
    
    public void update(final Graphics graphics) {
        if (!this.ic.animating) {
            this.paint(graphics);
        }
    }
}
