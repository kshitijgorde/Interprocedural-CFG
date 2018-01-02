import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class MenuBar extends Panel
{
    private CascadeMenu cm;
    private Color color;
    protected int width;
    protected int height;
    private MenuHead[] heads;
    private int itemsC;
    private boolean registered;
    
    public MenuBar(final int width, final int height, final MenuSet set, final CascadeMenu cm, final boolean registered) {
        this.setLayout(null);
        this.cm = cm;
        this.registered = registered;
        this.width = width;
        System.out.println("Width is now: " + this.width);
        this.height = height;
        int width2 = width;
        this.color = cm.getColor("Bar", 0, 0);
        this.setBackground(new Color(0, 0, 122));
        this.itemsC = set.getSize();
        this.processMenus(set);
        int n = 5;
        for (int i = 0; i < set.getSize(); ++i) {
            if (!cm.useVert) {
                this.heads[i].setBounds(n, 2, this.heads[i].getPrefferedWidth(), height - 2);
                n += this.heads[i].getPrefferedWidth() + cm.getOffset();
            }
            else {
                final int prefferedWidth = this.heads[i].getPrefferedWidth();
                if (prefferedWidth > width2) {
                    width2 = prefferedWidth;
                }
                this.heads[i].setBounds(2, n, prefferedWidth, this.heads[i].getPrefferedHeight());
                n += this.heads[i].getPrefferedHeight() + cm.getOffset();
            }
            this.add(this.heads[i], 0);
            this.heads[i].repaint();
        }
        try {
            if (!cm.useVert) {
                this.heads[this.itemsC].setBounds(width - this.heads[this.itemsC].getPrefferedWidth() - 5, 2, this.heads[this.itemsC].getPrefferedWidth(), height - 2);
            }
            else {
                this.heads[this.itemsC].setBounds(2, height - this.heads[this.itemsC].getPrefferedHeight() - 5, this.heads[this.itemsC].getPrefferedWidth(), this.heads[this.itemsC].getPrefferedHeight());
            }
            if (!registered) {
                this.add(this.heads[this.itemsC], 0);
            }
            this.heads[this.itemsC].repaint();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
        this.width = width2;
        System.out.println("Width is now: " + this.width);
    }
    
    private void processMenus(final MenuSet set) {
        this.heads = new MenuHead[this.itemsC + 1];
        while (set.hasMoreItems()) {
            this.heads[set.currentElement() - 1] = new MenuHead(set.getNextItem(), this.cm, this);
        }
        try {
            final URL url = new URL("http://www.realapplets.com");
            final Item item = new Item("RA", url, "_blank", true, null);
            final Item item2 = new Item("Free Applet", url, "_blank", false, null);
            final Item item3 = new Item("From RealApplets", url, "_blank", false, null);
            final MenuSet set2 = new MenuSet();
            set2.addItem(item2);
            set2.addItem(item3);
            item.giveSub(set2);
            this.heads[this.itemsC] = new MenuHead(item, this.cm, this);
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void removeAll() {
        for (int i = 0; i < this.itemsC + 1; ++i) {
            this.heads[i].removeMenu();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.cm.useShadow) {
            graphics.setColor(this.color.brighter());
            graphics.fillRect(0, 0, this.width, this.height);
            graphics.setColor(this.color);
            graphics.fillRect(2, 2, this.width, this.height);
        }
        else {
            graphics.setColor(this.color);
            graphics.fillRect(0, 0, this.width, this.height);
        }
    }
}
