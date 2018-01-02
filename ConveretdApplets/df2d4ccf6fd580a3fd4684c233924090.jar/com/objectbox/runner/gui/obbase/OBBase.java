// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.runner.gui.obbase;

import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.AdjustmentEvent;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Scrollbar;
import java.awt.event.AdjustmentListener;
import java.awt.Panel;

public class OBBase extends Panel implements AdjustmentListener
{
    protected Scrollbar vs;
    protected Scrollbar hs;
    protected int m_nVScrollbarWidth;
    protected int m_nHScrollbarHeight;
    protected transient Image offscreen;
    protected int m_nTopRow;
    protected int m_nLeftCol;
    protected boolean m_bAlwaysShowScrollbar;
    protected boolean m_bHideHScrollbar;
    protected boolean m_bHideVScrollbar;
    protected boolean m_bDoubleBuffering;
    protected transient String OSName;
    
    public OBBase() {
        this(false);
        this.setLayout(null);
    }
    
    public OBBase(final boolean bAlwaysShowScrollbar) {
        this.m_nVScrollbarWidth = 15;
        this.m_nHScrollbarHeight = 15;
        this.offscreen = null;
        this.m_nTopRow = 0;
        this.m_nLeftCol = 0;
        this.m_bAlwaysShowScrollbar = false;
        this.m_bHideHScrollbar = false;
        this.m_bHideVScrollbar = false;
        this.m_bDoubleBuffering = true;
        this.m_bAlwaysShowScrollbar = bAlwaysShowScrollbar;
        this.vs = new Scrollbar(1);
        this.hs = new Scrollbar(0);
        this.vs.addAdjustmentListener(this);
        this.hs.addAdjustmentListener(this);
        this.setLayout(null);
        this.add(this.vs);
        this.add(this.hs);
        this.enableEvents(4L);
        this.enableEvents(16L);
        this.enableEvents(8L);
        this.enableEvents(256L);
        this.enableEvents(32L);
        this.OSName = System.getProperty("os.name");
    }
    
    public void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        if (adjustmentEvent.getID() == 601) {
            if (adjustmentEvent.getSource() instanceof Scrollbar && ((Scrollbar)adjustmentEvent.getSource()).getOrientation() == 1) {
                final int value = this.getVScrollbar().getValue();
                if (this.onTopRowChange(value)) {
                    this.onTopRowChanged(this.m_nTopRow = value);
                }
            }
            else {
                final int value2 = this.getHScrollbar().getValue();
                if (this.onLeftColChange(value2)) {
                    this.onLeftColChanged(this.m_nLeftCol = value2);
                }
            }
        }
    }
    
    public void doLayout() {
        synchronized (this.getTreeLock()) {
            final Dimension size = this.getSize();
            final int width = size.width;
            final int height = size.height;
            final Scrollbar vScrollbar = this.getVScrollbar();
            final Scrollbar hScrollbar = this.getHScrollbar();
            if (this.m_bAlwaysShowScrollbar || (this.getMaxTopRow() != 0 && !this.m_bHideVScrollbar)) {
                hScrollbar.setBounds(this.getInsets().left, height - this.m_nHScrollbarHeight - this.getInsets().bottom, width - this.m_nVScrollbarWidth - this.getInsets().left - this.getInsets().right, this.m_nHScrollbarHeight);
            }
            else {
                hScrollbar.setBounds(this.getInsets().left, height - this.m_nHScrollbarHeight - this.getInsets().bottom, width - this.getInsets().left - this.getInsets().right, this.m_nHScrollbarHeight);
            }
            if (this.m_bAlwaysShowScrollbar || (this.getMaxLeftCol() != 0 && !this.m_bHideHScrollbar)) {
                vScrollbar.setBounds(width - this.m_nVScrollbarWidth - this.getInsets().right, this.getInsets().top, this.m_nVScrollbarWidth, height - this.m_nHScrollbarHeight - this.getInsets().top - this.getInsets().bottom);
            }
            else {
                vScrollbar.setBounds(width - this.m_nVScrollbarWidth - this.getInsets().right, this.getInsets().top, this.m_nVScrollbarWidth, height - this.getInsets().top - this.getInsets().bottom);
            }
            if (this.m_bAlwaysShowScrollbar || (this.getMaxTopRow() != 0 && !this.m_bHideVScrollbar)) {
                vScrollbar.setVisible(true);
            }
            else {
                vScrollbar.setVisible(false);
            }
            if (this.m_bAlwaysShowScrollbar || (this.getMaxLeftCol() != 0 && !this.m_bHideHScrollbar)) {
                hScrollbar.setVisible(true);
            }
            else {
                hScrollbar.setVisible(false);
            }
        }
        // monitorexit(this.getTreeLock())
    }
    
    protected void draw(final Graphics graphics) {
    }
    
    public boolean getAlwaysShowScrollbars() {
        return this.m_bAlwaysShowScrollbar;
    }
    
    public Scrollbar getHScrollbar() {
        return this.hs;
    }
    
    public int getHScrollbarHeight() {
        return this.m_nHScrollbarHeight;
    }
    
    protected int getMaxLeftCol() {
        return 0;
    }
    
    protected int getMaxTopRow() {
        return 0;
    }
    
    public Dimension getMinimumSize() {
        return new Dimension(100, 80);
    }
    
    public Dimension getPreferredSize() {
        synchronized (this.getTreeLock()) {
            final Dimension size = this.getSize();
            if (size.width > 0 && size.height > 0) {
                // monitorexit(this.getTreeLock())
                return size;
            }
            // monitorexit(this.getTreeLock())
            return this.getMinimumSize();
        }
    }
    
    public Scrollbar getVScrollbar() {
        return this.vs;
    }
    
    public int getVScrollbarWidth() {
        return this.m_nVScrollbarWidth;
    }
    
    public void hideHScrollbar() {
        this.m_bHideHScrollbar = true;
        this.getHScrollbar().setVisible(false);
    }
    
    public void hideScrollbar() {
        this.m_bHideHScrollbar = true;
        this.m_bHideVScrollbar = true;
        this.hideVScrollbar();
        this.hideHScrollbar();
    }
    
    public void hideVScrollbar() {
        this.m_bHideVScrollbar = true;
        this.getVScrollbar().setVisible(false);
    }
    
    public boolean isDoubleBuffering(final boolean b) {
        return this.m_bDoubleBuffering;
    }
    
    protected boolean onLeftColChange(final int n) {
        return true;
    }
    
    protected void onLeftColChanged(final int n) {
        this.repaint();
    }
    
    protected boolean onTopRowChange(final int n) {
        return true;
    }
    
    protected void onTopRowChanged(final int n) {
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (!this.isShowing()) {
            return;
        }
        if (this.m_bDoubleBuffering && this.validateImage()) {
            final Dimension size = this.getSize();
            final Rectangle clipBounds = graphics.getClipBounds();
            final Graphics graphics2 = this.offscreen.getGraphics();
            if (clipBounds != null) {
                graphics2.clipRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
            }
            graphics2.setFont(graphics.getFont());
            graphics2.setColor(this.getBackground());
            graphics2.fillRect(0, 0, size.width, size.height);
            graphics2.setColor(graphics.getColor());
            this.draw(graphics2);
            graphics.drawImage(this.offscreen, 0, 0, this);
            graphics2.dispose();
        }
        else if (graphics != null && graphics.getClipBounds() != null) {
            this.draw(graphics);
        }
    }
    
    public void repaint() {
        final Dimension size = this.getSize();
        final int height = size.height;
        final int width = size.width;
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            if (!this.m_bDoubleBuffering) {
                graphics.setColor(this.getBackground());
                graphics.fillRect(0, 0, size.width, size.height);
            }
            this.update(graphics, 0, 0, width, height);
            graphics.dispose();
        }
    }
    
    public void setAlwaysShowScrollbars(final boolean bAlwaysShowScrollbar) {
        this.m_bAlwaysShowScrollbar = bAlwaysShowScrollbar;
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        if (n3 <= 0 || n4 <= 0) {
            return;
        }
        synchronized (this.getTreeLock()) {
            super.setBounds(n, n2, n3, n4);
            this.updateScrollbar();
        }
        // monitorexit(this.getTreeLock())
    }
    
    public void setDoubleBuffering(final boolean bDoubleBuffering) {
        this.m_bDoubleBuffering = bDoubleBuffering;
    }
    
    public void setHScrollbarHeight(final int nhScrollbarHeight) {
        if (nhScrollbarHeight > 0) {
            this.m_nHScrollbarHeight = nhScrollbarHeight;
            this.doLayout();
        }
    }
    
    public void setVScrollbarWidth(final int nvScrollbarWidth) {
        if (nvScrollbarWidth > 0) {
            this.m_nVScrollbarWidth = nvScrollbarWidth;
            this.doLayout();
        }
    }
    
    public void showHScrollbar() {
        this.m_bHideHScrollbar = false;
        this.getHScrollbar().setVisible(true);
    }
    
    public void showScrollbar() {
        this.m_bHideHScrollbar = false;
        this.m_bHideVScrollbar = false;
        this.showVScrollbar();
        this.showHScrollbar();
    }
    
    public void showVScrollbar() {
        this.m_bHideVScrollbar = false;
        this.getVScrollbar().setVisible(true);
    }
    
    public void update() {
        final Dimension size = this.getSize();
        final int height = size.height;
        final int width = size.width;
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.update(graphics, 0, 0, width, height);
            graphics.dispose();
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.m_bDoubleBuffering) {
            this.paint(graphics);
        }
        else {
            super.update(graphics);
        }
    }
    
    public void update(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        graphics.clipRect(n, n2, n3, n4);
        this.paint(graphics);
    }
    
    protected void updateScrollbar() {
        final int maxTopRow = this.getMaxTopRow();
        final int maxLeftCol = this.getMaxLeftCol();
        final Scrollbar vScrollbar = this.getVScrollbar();
        final Scrollbar hScrollbar = this.getHScrollbar();
        if (maxTopRow == 0) {
            this.m_nTopRow = 0;
            if (this.m_bAlwaysShowScrollbar && (this.OSName.equals("Windows NT") || this.OSName.equals("Windows 95"))) {
                vScrollbar.setEnabled(false);
            }
        }
        else {
            if (!vScrollbar.isEnabled()) {
                vScrollbar.setEnabled(true);
            }
            int blockIncrement = maxTopRow / 10;
            if (blockIncrement < 5) {
                blockIncrement = 5;
            }
            vScrollbar.setValues(vScrollbar.getValue(), 1, 0, maxTopRow);
            vScrollbar.setBlockIncrement(blockIncrement);
            vScrollbar.setUnitIncrement(1);
        }
        if (maxLeftCol == 0) {
            this.m_nLeftCol = 0;
            if (this.m_bAlwaysShowScrollbar && (this.OSName.equals("Windows NT") || this.OSName.equals("Windows 95"))) {
                vScrollbar.setEnabled(false);
            }
        }
        else {
            if (!hScrollbar.isEnabled()) {
                hScrollbar.setEnabled(true);
            }
            int blockIncrement2 = maxLeftCol / 10;
            if (blockIncrement2 < 5) {
                blockIncrement2 = 5;
            }
            hScrollbar.setValues(hScrollbar.getValue(), 1, 0, maxLeftCol);
            hScrollbar.setBlockIncrement(blockIncrement2);
            hScrollbar.setUnitIncrement(1);
        }
    }
    
    protected boolean validateImage() {
        try {
            final Dimension size = this.getSize();
            if (this.offscreen == null || this.offscreen.getWidth(this) != size.width || this.offscreen.getHeight(this) != size.height) {
                if (size.width <= 0 || size.height <= 0) {
                    return false;
                }
                this.offscreen = this.createImage(size.width, size.height);
            }
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
