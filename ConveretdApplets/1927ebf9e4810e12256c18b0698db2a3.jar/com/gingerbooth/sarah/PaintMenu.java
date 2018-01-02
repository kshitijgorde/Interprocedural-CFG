// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Dimension;
import java.net.URL;
import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Color;
import com.gingerbooth.websafe.SubsetPalette;
import courseware.jfcdep.BufferCanvas;

public class PaintMenu extends BufferCanvas
{
    private ShapeGame controller;
    protected SubsetPalette palette;
    private SubsetPalette backPalette;
    private SubsetPalette olPalette;
    private Color paintBackground;
    boolean needRepaint;
    protected int swatchSize;
    private int columns;
    private int rows;
    private int swatch0x;
    private int swatch0y;
    private int backRows;
    private int bswatch0x;
    private int bswatch0y;
    private int olRows;
    private int olswatch0x;
    private int olswatch0y;
    private String paletteOnString;
    private String paletteOffString;
    private String bkgndString;
    private String edgeString;
    private Image paletteOn;
    private Image paletteOff;
    private Image edgeImage;
    private Image bkgndImage;
    private MediaTracker tracker;
    
    public PaintMenu(final ShapeGame controller) {
        this.paintBackground = new Color(204, 204, 204);
        this.needRepaint = true;
        this.swatchSize = 15;
        this.columns = 2;
        this.rows = 13;
        this.swatch0x = 0;
        this.swatch0y = 31;
        this.backRows = 6;
        this.olRows = 3;
        this.paletteOnString = "icons/palette30x30on.gif";
        this.paletteOffString = "icons/palette30x30off.gif";
        this.bkgndString = "icons/bkgnd30x15.gif";
        this.edgeString = "icons/edge30x15.gif";
        this.controller = controller;
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                PaintMenu.this.pickSwatch(mouseEvent.getX(), mouseEvent.getY());
            }
        });
    }
    
    public PaintMenu(final ShapeGame shapeGame, final SubsetPalette palette, final SubsetPalette backPalette, final SubsetPalette olPalette) {
        this(shapeGame);
        this.palette = palette;
        this.backPalette = backPalette;
        this.olPalette = olPalette;
        final URL documentBase = shapeGame.getDocumentBase();
        this.tracker = new MediaTracker(this);
        this.paletteOn = shapeGame.getImage(documentBase, this.paletteOnString);
        this.paletteOff = shapeGame.getImage(documentBase, this.paletteOffString);
        this.edgeImage = shapeGame.getImage(documentBase, this.edgeString);
        this.bkgndImage = shapeGame.getImage(documentBase, this.bkgndString);
        this.tracker.addImage(this.paletteOn, 0);
        this.tracker.addImage(this.paletteOff, 1);
        this.tracker.addImage(this.edgeImage, 2);
        this.tracker.addImage(this.bkgndImage, 3);
    }
    
    private void drawBackground() {
        final Dimension preferredSize = this.getPreferredSize();
        preferredSize.width = this.getPreferredWidth(preferredSize.height);
        final Graphics offscreen = this.getOffscreen();
        offscreen.setColor(this.paintBackground);
        offscreen.fillRect(0, 0, preferredSize.width, preferredSize.height);
        offscreen.setColor(Tableau.borderColor);
        offscreen.drawRect(0, 0, preferredSize.width - 1, preferredSize.height - 1);
    }
    
    public void drawSwatchesBackground(final Graphics graphics) {
        this.bswatch0x = 0;
        this.bswatch0y = this.getSize().height - this.backRows * this.swatchSize - 1;
        graphics.drawImage(this.bkgndImage, 1, this.bswatch0y - 15, this);
        for (int i = 0; i < this.columns; ++i) {
            for (int j = 0; j < this.backRows; ++j) {
                final int n = i * this.backRows + j;
                if (n >= this.backPalette.getNumColors()) {
                    return;
                }
                graphics.setColor(this.backPalette.getOrderedColor(n));
                graphics.fillRect(this.bswatch0x + this.swatchSize * i, this.bswatch0y + this.swatchSize * j, this.swatchSize, this.swatchSize);
                graphics.setColor(Tableau.borderColor);
                graphics.drawRect(this.bswatch0x + this.swatchSize * i, this.bswatch0y + this.swatchSize * j, this.swatchSize, this.swatchSize);
            }
        }
        graphics.setColor(Color.yellow);
        final int order = this.backPalette.getOrder(this.controller.getBackgroundColor());
        final int n2 = order / this.backRows;
        final int n3 = order - n2 * this.backRows;
        graphics.drawRect(this.bswatch0x + this.swatchSize * n2, this.bswatch0y + this.swatchSize * n3, this.swatchSize, this.swatchSize);
        graphics.drawRect(this.bswatch0x + this.swatchSize * n2 + 1, this.bswatch0y + this.swatchSize * n3 + 1, this.swatchSize - 2, this.swatchSize - 2);
    }
    
    public void drawSwatchesForeground(final Graphics graphics) {
        for (int i = 0; i < this.columns; ++i) {
            for (int j = 0; j < this.rows; ++j) {
                final int n = i * this.rows + j;
                if (n >= this.palette.getNumColors()) {
                    return;
                }
                graphics.setColor(this.palette.getOrderedColor(n));
                graphics.fillRect(this.swatch0x + this.swatchSize * i, this.swatch0y + this.swatchSize * j, this.swatchSize, this.swatchSize);
                graphics.setColor(Tableau.borderColor);
                graphics.drawRect(this.swatch0x + this.swatchSize * i, this.swatch0y + this.swatchSize * j, this.swatchSize, this.swatchSize);
            }
        }
        if (this.controller.isPaintMode()) {
            graphics.setColor(Color.yellow);
            final int order = this.palette.getOrder(this.controller.getPaintColor());
            final int n2 = order / this.rows;
            final int n3 = order - n2 * this.rows;
            graphics.drawRect(this.swatch0x + this.swatchSize * n2, this.swatch0y + this.swatchSize * n3, this.swatchSize, this.swatchSize);
            graphics.drawRect(this.swatch0x + this.swatchSize * n2 + 1, this.swatch0y + this.swatchSize * n3 + 1, this.swatchSize - 2, this.swatchSize - 2);
            graphics.drawImage(this.paletteOn, 1, 1, this);
        }
        else {
            graphics.drawImage(this.paletteOff, 1, 1, this);
        }
    }
    
    public void drawSwatchesOutline(final Graphics graphics) {
        this.olswatch0x = 0;
        this.olswatch0y = this.bswatch0y - 15 - this.olRows * this.swatchSize - 1;
        graphics.drawImage(this.edgeImage, 1, this.olswatch0y - 15, this);
        for (int i = 0; i < this.columns; ++i) {
            for (int j = 0; j < this.olRows; ++j) {
                final int n = i * this.olRows + j;
                if (n >= this.olPalette.getNumColors()) {
                    graphics.setColor(Tableau.borderColor);
                    graphics.drawLine(this.olswatch0x + this.swatchSize * i, this.olswatch0y + this.swatchSize * j, this.olswatch0x + this.swatchSize * i + this.swatchSize, this.olswatch0y + this.swatchSize * j + this.swatchSize);
                    graphics.drawLine(this.olswatch0x + this.swatchSize * i + this.swatchSize, this.olswatch0y + this.swatchSize * j, this.olswatch0x + this.swatchSize * i, this.olswatch0y + this.swatchSize * j + this.swatchSize);
                    graphics.drawRect(this.olswatch0x + this.swatchSize * i, this.olswatch0y + this.swatchSize * j, this.swatchSize, this.swatchSize);
                }
                else {
                    graphics.setColor(this.olPalette.getOrderedColor(n));
                    graphics.fillRect(this.olswatch0x + this.swatchSize * i, this.olswatch0y + this.swatchSize * j, this.swatchSize, this.swatchSize);
                    graphics.setColor(Tableau.borderColor);
                    graphics.drawRect(this.olswatch0x + this.swatchSize * i, this.olswatch0y + this.swatchSize * j, this.swatchSize, this.swatchSize);
                }
            }
        }
        graphics.setColor(Color.yellow);
        int order = this.olPalette.getOrder(this.controller.getTableau().outlineColor);
        if (!this.controller.getTableau().useOutline) {
            order = 5;
        }
        final int n2 = order / this.olRows;
        final int n3 = order - n2 * this.olRows;
        graphics.drawRect(this.olswatch0x + this.swatchSize * n2, this.olswatch0y + this.swatchSize * n3, this.swatchSize, this.swatchSize);
        graphics.drawRect(this.olswatch0x + this.swatchSize * n2 + 1, this.olswatch0y + this.swatchSize * n3 + 1, this.swatchSize - 2, this.swatchSize - 2);
    }
    
    public int getPreferredWidth(final int n) {
        return 31;
    }
    
    private void iconCheck() {
        for (int i = 0; i < 4; ++i) {
            try {
                this.tracker.waitForID(i);
            }
            catch (InterruptedException ex) {}
            if (this.tracker.isErrorID(i)) {
                System.err.println("Warning--unable to load paint icon " + i);
            }
        }
    }
    
    protected void newBackColor(final int backgroundColor) {
        this.needRepaint = true;
        this.controller.setBackgroundColor(backgroundColor);
        this.repaint();
    }
    
    protected void newOLColor(final int outline) {
        this.needRepaint = true;
        this.repaint();
        this.controller.setOutline(outline);
    }
    
    protected void newPaintColor(final int paintColor) {
        this.controller.setPaintColor(paintColor);
        this.controller.setPaintMode(true);
        this.needRepaint = true;
        this.repaint();
    }
    
    public synchronized void paint(final Graphics graphics) {
        this.iconCheck();
        synchronized (graphics) {
            super.offg = this.getOffscreen();
            final Graphics offg = super.offg;
            // monitorenter(offg)
            try {
                final Image offscreen = super.offscreen;
                // monitorenter(offscreen)
                try {
                    if (super.offg == null || super.offscreen == null) {
                        // monitorexit(offscreen)
                        // monitorexit(offg)
                        // monitorexit(graphics)
                        return;
                    }
                    if (this.needRepaint) {
                        this.drawBackground();
                        this.drawSwatchesForeground(super.offg);
                        this.drawSwatchesBackground(super.offg);
                        this.drawSwatchesOutline(super.offg);
                    }
                    graphics.drawImage(super.offscreen, 0, 0, this);
                }
                // monitorexit(offscreen)
                finally {}
            }
            // monitorexit(offg)
            finally {}
        }
        this.needRepaint = false;
    }
    
    public void pickSwatch(final int n, final int n2) {
        if (n2 < this.swatch0y) {
            this.controller.paintOff();
            return;
        }
        final int n3 = this.swatchSize * this.rows + this.swatch0y;
        final int n4 = n / this.swatchSize;
        if (n4 > this.columns) {
            return;
        }
        final int n5 = n2 - 30;
        if (n2 <= n3) {
            final int n6 = n5 / this.swatchSize;
            if (n6 > this.rows) {
                return;
            }
            final int n7 = n4 * this.rows + n6;
            if (n7 > this.palette.getNumColors()) {
                return;
            }
            this.newPaintColor(this.palette.getOrderedID(n7));
        }
        else if (n2 > this.olswatch0y && n2 < this.bswatch0y - 15) {
            final int n8 = (n2 - this.olswatch0y) / this.swatchSize;
            if (n8 > this.olRows) {
                return;
            }
            final int n9 = n4 * this.olRows + n8;
            if (n9 == this.olPalette.getNumColors()) {
                this.newOLColor(-1);
            }
            else {
                this.newOLColor(this.olPalette.getOrderedID(n9));
            }
        }
        else if (n2 >= this.bswatch0y) {
            final int n10 = (n2 - this.bswatch0y) / this.swatchSize;
            if (n10 > this.backRows) {
                return;
            }
            final int n11 = n4 * this.backRows + n10;
            if (n11 > this.backPalette.getNumColors()) {
                return;
            }
            this.newBackColor(this.backPalette.getOrderedID(n11));
        }
    }
    
    public void setNeedRepaint(final boolean needRepaint) {
        this.needRepaint = needRepaint;
    }
}
