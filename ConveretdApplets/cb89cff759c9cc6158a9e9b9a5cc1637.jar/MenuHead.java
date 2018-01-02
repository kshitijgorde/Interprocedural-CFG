import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.LayoutManager;
import java.awt.Polygon;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class MenuHead extends Panel implements MouseListener
{
    private CascadeMenu cm;
    private boolean active;
    private String label;
    private Color color1;
    private Color color2;
    private Color stColor;
    private Color textColor;
    private Color textOverColor;
    private Color tColor;
    private boolean done;
    private Font font1;
    private Font font2;
    private Font font;
    private ItemCanvas ic;
    private Item item;
    private MenuBar mb;
    private boolean ad;
    private Polygon p;
    
    public MenuHead(final Item item, final CascadeMenu cm, final MenuBar mb) {
        this.done = false;
        this.setLayout(null);
        this.item = item;
        this.mb = mb;
        this.color1 = cm.getColor("Bar", 0, 0);
        this.color2 = cm.getColor("Bar", 1, 0);
        this.textColor = cm.getColor("Bar", 0, 1);
        this.textOverColor = cm.getColor("Bar", 1, 1);
        this.font1 = cm.getFont("Bar", 0);
        this.font2 = cm.getFont("Bar", 1);
        this.font = this.font1;
        this.stColor = this.color1;
        this.tColor = this.textColor;
        this.setBackground(this.stColor);
        this.label = item.getText();
        this.cm = cm;
        this.addMouseListener(this);
        if (item.hasSub()) {
            this.ic = new ItemCanvas(item.getMenuSet(), cm, true);
        }
    }
    
    public int fontWidth() {
        return this.cm.getFontMetrics(this.font).stringWidth(this.label);
    }
    
    public int fontAlign() {
        final FontMetrics fontMetrics = this.cm.getFontMetrics(this.font);
        return (this.getSize().height - fontMetrics.getMaxDescent() + fontMetrics.getMaxAscent()) / 2;
    }
    
    public int fontCenter() {
        return this.getSize().width / 2 - this.fontWidth() / 2;
    }
    
    public int getPrefferedWidth() {
        return this.fontWidth() + 20;
    }
    
    private void doAction(final boolean b) {
        this.ic.setLocation(this.getBestXPosition(), this.getBestYPosition());
        this.ic.setSize(0, 0);
        this.cm.add(this.ic);
        this.ic.animate(this.ic.getItems() * this.cm.getItemHeight() + 4);
    }
    
    private int getBestXPosition() {
        int x = this.getLocation().x;
        final int prefferedWidth = this.ic.getPrefferedWidth();
        final int width = this.cm.getWidth();
        if (x + prefferedWidth > width - 5) {
            x = width - prefferedWidth - 5;
        }
        return x;
    }
    
    private int getBestYPosition() {
        int n = this.getLocation().y + this.getSize().height - 2;
        final int n2 = this.ic.getItems() * this.cm.getItemHeight() + 4;
        final int height = this.cm.getHeight();
        if (n + n2 > height - 5) {
            n = height - n2 - 5;
        }
        return n;
    }
    
    public void paint(final Graphics graphics) {
        final int height = this.getSize().height;
        this.p = new Polygon(new int[] { 5, 8, 11 }, new int[] { height / 2 - 3, height / 2 + 3, height / 2 - 3 }, 3);
        graphics.setFont(this.font);
        graphics.setColor(this.stColor);
        if (this.active) {
            if (this.cm.useShadow) {
                graphics.setColor(this.stColor.darker());
                graphics.fillRect(0, 0, this.getSize().width, height);
                graphics.setColor(this.stColor);
                graphics.fillRect(2, 2, this.getSize().width, height);
            }
            else {
                graphics.setColor(this.stColor);
                graphics.fillRect(0, 0, this.getSize().width, height);
            }
        }
        graphics.setColor(this.tColor);
        graphics.fillPolygon(this.p);
        graphics.drawString(this.label, 5 + this.fontCenter(), this.fontAlign());
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.item.hasAction()) {
            this.cm.performAction(this.item);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (this.item.hasAction()) {
            this.cm.show(this.item.getAction().toString());
        }
        this.active = true;
        this.stColor = this.color2;
        this.tColor = this.textOverColor;
        this.font = this.font2;
        this.mb.removeAll();
        if (this.item.hasSub()) {
            this.doAction(true);
        }
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.cm.show("");
        this.active = false;
        this.stColor = this.color2;
        this.tColor = this.textColor;
        this.font = this.font1;
        this.repaint();
    }
    
    public void removeMenu() {
        if (this.item.hasSub()) {
            this.cm.remove(this.ic);
        }
        if (this.item.hasSub()) {
            this.ic.removeAll();
        }
    }
}
