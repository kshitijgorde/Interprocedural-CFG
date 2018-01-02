import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.AdjustmentEvent;
import java.awt.Component;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class suiList extends Panel implements AdjustmentListener
{
    Scrollbar sbVer;
    suiListpanel list;
    int width;
    int height;
    int iHeight;
    int itemN;
    
    suiList(final int width, final int height, final int iHeight, final int itemN) {
        this.sbVer = new Scrollbar(1, 0, height - 4, 0, height - 4);
        this.add(this.list = new suiListpanel(this.sbVer, width - 17, height - 4, iHeight, itemN), "West");
        this.add(this.sbVer, "East");
        this.sbVer.addAdjustmentListener(this);
        this.width = width;
        this.height = height;
        this.iHeight = iHeight;
        this.itemN = itemN;
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.list.sbChanged();
    }
    
    public void addItem(final String s) {
        this.list.addItem(s);
    }
    
    public void addItem(final String s, final Image image) {
        this.list.addItem(s, image);
    }
    
    public void clear() {
        this.list.clear();
    }
    
    public int countItems() {
        return this.list.count;
    }
    
    public int getRows() {
        return this.list.itemN;
    }
    
    public int getSelectedIndex() {
        if (this.list.Multi) {
            return -1;
        }
        for (int i = 0; i < this.list.count; ++i) {
            if (this.list.litem[i].select) {
                return i;
            }
        }
        return -1;
    }
    
    public void select(final int n) {
        if (!this.list.Multi) {
            for (int i = 0; i < this.list.count; ++i) {
                this.list.litem[i].select = false;
            }
            this.list.litem[n].select = true;
        }
        else {
            this.list.litem[n].select = true;
        }
        this.list.repaint();
    }
    
    public void deselect(final int n) {
        this.list.litem[n].select = false;
        this.list.repaint();
    }
    
    public void setSingleSelection() {
        this.list.Multi = false;
    }
    
    public void delItem(final int n) {
        this.list.delItem(n);
    }
    
    public void delItems(final int n, final int n2) {
        this.list.delItems(n, n2);
    }
    
    public boolean isSelect(final int n) {
        return this.list.litem[n].select;
    }
    
    public void replaceItem(final String title, final int n) {
        this.list.litem[n].title = title;
        this.list.litem[n].image = null;
        this.list.repaint();
    }
    
    public void replaceItem(final String title, final Image image, final int n) {
        this.list.litem[n].title = title;
        this.list.litem[n].image = image;
        this.list.repaint();
    }
    
    public int[] getSelectIndexes() {
        int n = 0;
        for (int i = 0; i < this.list.count; ++i) {
            if (this.list.litem[i].select) {
                ++n;
            }
        }
        final int[] array = new int[n];
        int n2 = 0;
        for (int j = 0; j < this.list.count; ++j) {
            if (this.list.litem[j].select) {
                array[n2] = j;
                ++n2;
            }
        }
        return array;
    }
    
    public void paint(final Graphics graphics) {
        this.list.move(2, 2);
        this.sbVer.reshape(this.width - 15, 0, 15, this.height);
        graphics.setColor(new Color(130, 130, 130));
        graphics.drawLine(0, 0, this.width - 1, 0);
        graphics.drawLine(0, 0, 0, this.height - 1);
        graphics.setColor(new Color(50, 50, 50));
        graphics.drawLine(1, 1, this.width - 1, 1);
        graphics.drawLine(1, 1, 1, this.height - 2);
        graphics.setColor(new Color(192, 192, 192));
        graphics.drawLine(1, this.height - 2, this.width - 1, this.height - 2);
        graphics.setColor(new Color(230, 230, 230));
        graphics.drawLine(0, this.height - 1, this.width - 1, this.height - 1);
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
}
