// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.util;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Container;
import java.awt.Component;
import java.io.Serializable;
import java.awt.LayoutManager;

public class MenuGridLayout implements LayoutManager, Serializable
{
    int hgap;
    int vgap;
    int rows;
    int cols;
    
    public MenuGridLayout() {
        this(1, 0, 0, 0);
    }
    
    public MenuGridLayout(final int n, final int n2) {
        this(n, n2, 0, 0);
    }
    
    public MenuGridLayout(final int rows, final int cols, final int hgap, final int vgap) {
        if (rows == 0 && cols == 0) {
            throw new IllegalArgumentException("rows and cols cannot both be zero");
        }
        this.rows = rows;
        this.cols = cols;
        this.hgap = hgap;
        this.vgap = vgap;
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public int getColumns() {
        return this.cols;
    }
    
    public int getHgap() {
        return this.hgap;
    }
    
    public int getRows() {
        return this.rows;
    }
    
    public int getVgap() {
        return this.vgap;
    }
    
    public void layoutContainer(final Container container) {
        synchronized (container.getTreeLock()) {
            final Insets insets = container.getInsets();
            final int componentCount = container.getComponentCount();
            int rows = this.rows;
            int cols = this.cols;
            if (componentCount == 0) {
                // monitorexit(container.getTreeLock())
                return;
            }
            if (rows > 0) {
                cols = (componentCount + rows - 1) / rows;
            }
            else {
                rows = (componentCount + cols - 1) / cols;
            }
            final int n = container.getSize().width - (insets.left + insets.right);
            final int n2 = container.getSize().height - (insets.top + insets.bottom);
            final int n3 = (n - (cols - 1) * this.hgap) / cols;
            for (int n4 = (n2 - (rows - 1) * this.vgap) / rows, i = 0, top = insets.top; i < rows; ++i, top += n4 + this.vgap) {
                for (int j = 0, left = insets.left; j < cols; ++j, left += n3 + this.hgap) {
                    final int n5 = j * rows + i;
                    if (n5 < componentCount) {
                        container.getComponent(n5).setBounds(left, top, n3, n4);
                    }
                }
            }
        }
        // monitorexit(container.getTreeLock())
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        synchronized (container.getTreeLock()) {
            final Insets insets = container.getInsets();
            final int componentCount = container.getComponentCount();
            int rows = this.rows;
            int cols = this.cols;
            if (rows > 0) {
                cols = (componentCount + rows - 1) / rows;
            }
            else {
                rows = (componentCount + cols - 1) / cols;
            }
            int width = 0;
            int height = 0;
            for (int i = 0; i < componentCount; ++i) {
                final Dimension minimumSize = container.getComponent(i).getMinimumSize();
                if (width < minimumSize.width) {
                    width = minimumSize.width;
                }
                if (height < minimumSize.height) {
                    height = minimumSize.height;
                }
            }
            // monitorexit(container.getTreeLock())
            return new Dimension(insets.left + insets.right + cols * width + (cols - 1) * this.hgap, insets.top + insets.bottom + rows * height + (rows - 1) * this.vgap);
        }
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        synchronized (container.getTreeLock()) {
            final Insets insets = container.getInsets();
            final int componentCount = container.getComponentCount();
            int rows = this.rows;
            int cols = this.cols;
            if (rows > 0) {
                cols = (componentCount + rows - 1) / rows;
            }
            else {
                rows = (componentCount + cols - 1) / cols;
            }
            int width = 0;
            int height = 0;
            for (int i = 0; i < componentCount; ++i) {
                final Dimension preferredSize = container.getComponent(i).getPreferredSize();
                if (width < preferredSize.width) {
                    width = preferredSize.width;
                }
                if (height < preferredSize.height) {
                    height = preferredSize.height;
                }
            }
            // monitorexit(container.getTreeLock())
            return new Dimension(insets.left + insets.right + cols * width + (cols - 1) * this.hgap, insets.top + insets.bottom + rows * height + (rows - 1) * this.vgap);
        }
    }
    
    public void removeLayoutComponent(final Component component) {
    }
    
    public void setColumns(final int cols) {
        if (cols == 0 && this.rows == 0) {
            throw new IllegalArgumentException("rows and cols cannot both be zero");
        }
        this.cols = cols;
    }
    
    public void setHgap(final int hgap) {
        this.hgap = hgap;
    }
    
    public void setRows(final int rows) {
        if (rows == 0 && this.cols == 0) {
            throw new IllegalArgumentException("rows and cols cannot both be zero");
        }
        this.rows = rows;
    }
    
    public void setVgap(final int vgap) {
        this.vgap = vgap;
    }
    
    public String toString() {
        return String.valueOf(this.getClass().getName()) + "[hgap=" + this.hgap + ",vgap=" + this.vgap + ",rows=" + this.rows + ",cols=" + this.cols + "]";
    }
}
