import java.awt.Dimension;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class suiListpanel extends Panel
{
    Scrollbar sbVer;
    int width;
    int height;
    int iHeight;
    int originY;
    public int count;
    public suiListitem[] litem;
    int itemN;
    public boolean Multi;
    
    suiListpanel(final Scrollbar sbVer, final int width, final int height, final int iHeight, final int itemN) {
        this.Multi = true;
        this.sbVer = sbVer;
        this.width = width;
        this.height = height;
        this.iHeight = iHeight;
        this.itemN = itemN;
        this.litem = new suiListitem[itemN];
        this.setBackground(Color.white);
        this.setLayout(new FlowLayout(0));
    }
    
    public void sbChanged() {
        this.originY = -this.sbVer.getValue();
        this.repaint();
    }
    
    public void addItem(final String s) {
        this.litem[this.count] = new suiListitem(s);
        ++this.count;
        if (this.count * this.iHeight > this.height) {
            this.sbVer.setValues(0, this.height, 0, this.count * this.iHeight);
        }
        this.repaint();
    }
    
    public void addItem(final String s, final Image image) {
        this.litem[this.count] = new suiListitem(s, image);
        ++this.count;
        if (this.count * this.iHeight > this.height) {
            this.sbVer.setValues(0, this.height, 0, this.count * this.iHeight);
        }
        this.repaint();
    }
    
    public void delItem(final int n) {
        for (int i = n; i < this.count - 1; ++i) {
            this.litem[i] = this.litem[i + 1];
        }
        --this.count;
        this.repaint();
    }
    
    public void delItems(final int n, final int n2) {
        for (int i = n; i < this.count - (n2 - n + 1); ++i) {
            this.litem[i] = this.litem[i + n2 - n + 1];
        }
        this.count -= n2 - n + 1;
        this.repaint();
    }
    
    public void clear() {
        this.count = 0;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        graphics.translate(0, this.originY);
        final int height = graphics.getFontMetrics().getHeight();
        final int descent = graphics.getFontMetrics().getDescent();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.width - 1, this.height - 1);
        for (int i = 0; i < this.count; ++i) {
            if (this.litem[i].select) {
                graphics.setColor(new Color(100, 100, 255));
                graphics.fillRect(0, this.iHeight * i, this.width - 1, this.iHeight - 1);
                graphics.setColor(new Color(255, 255, 0));
                graphics.drawRect(0, this.iHeight * i, this.width - 1, this.iHeight - 1);
                graphics.setColor(Color.white);
            }
            else {
                graphics.setColor(Color.black);
            }
            if (this.litem[i].image != null) {
                graphics.drawImage(this.litem[i].image, 0, this.iHeight * i + this.iHeight / 2 - this.litem[i].image.getHeight(this) / 2, this);
                graphics.drawString(this.litem[i].title, this.litem[i].image.getWidth(this) + 2, this.iHeight * i + this.iHeight / 2 + height / 2 - descent);
            }
            else {
                graphics.drawString(this.litem[i].title, 2, this.iHeight * i + this.iHeight / 2 + height / 2 - descent);
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int n, int n2) {
        n2 -= this.originY;
        if (!this.Multi && n2 / this.iHeight < this.count) {
            for (int i = 0; i < this.count; ++i) {
                this.litem[i].select = false;
            }
        }
        if (n2 / this.iHeight < this.count) {
            this.litem[n2 / this.iHeight].select = !this.litem[n2 / this.iHeight].select;
        }
        this.repaint();
        return true;
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
}
