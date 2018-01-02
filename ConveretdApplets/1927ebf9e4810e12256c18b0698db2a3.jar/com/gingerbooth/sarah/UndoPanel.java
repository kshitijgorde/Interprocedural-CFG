// 
// Decompiled by Procyon v0.5.30
// 

package com.gingerbooth.sarah;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.net.URL;
import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import courseware.jfcdep.BufferCanvas;

public class UndoPanel extends BufferCanvas
{
    private ShapeGame controller;
    private MediaTracker tracker;
    static final int width = 66;
    static final int height = 110;
    private Image undoNeitherI;
    private Image undoBothI;
    private Image undoUndoI;
    private Image undoRedoI;
    private String undoNeitherS;
    private String undoBothS;
    private String undoUndoS;
    private String undoRedoS;
    private ActiveRegion upArrow;
    private ActiveRegion downArrow;
    private ActiveRegion leftArrow;
    private ActiveRegion rightArrow;
    private ActiveRegion center;
    private ActiveRegion zoomIn;
    private ActiveRegion zoomOut;
    private ActiveRegion undo;
    private ActiveRegion redo;
    
    public UndoPanel(final ShapeGame controller) {
        super(66, 110);
        this.undoNeitherS = "icons/undoneither.gif";
        this.undoBothS = "icons/undoboth.gif";
        this.undoUndoS = "icons/undoundo.gif";
        this.undoRedoS = "icons/undoredo.gif";
        this.upArrow = new ActiveRegion(23, 0, 41, 15);
        this.downArrow = new ActiveRegion(23, 40, 41, 55);
        this.leftArrow = new ActiveRegion(3, 19, 19, 36);
        this.rightArrow = new ActiveRegion(45, 18, 61, 35);
        this.center = new ActiveRegion(23, 18, 42, 37);
        this.zoomIn = new ActiveRegion(3, 57, 29, 85);
        this.zoomOut = new ActiveRegion(37, 61, 57, 83);
        this.undo = new ActiveRegion(0, 88, 30, 109);
        this.redo = new ActiveRegion(35, 88, 65, 109);
        this.controller = controller;
        final URL documentBase = controller.getDocumentBase();
        this.tracker = new MediaTracker(this);
        this.undoNeitherI = controller.getImage(documentBase, this.undoNeitherS);
        this.undoBothI = controller.getImage(documentBase, this.undoBothS);
        this.undoUndoI = controller.getImage(documentBase, this.undoUndoS);
        this.undoRedoI = controller.getImage(documentBase, this.undoRedoS);
        this.tracker.addImage(this.undoNeitherI, 0);
        this.tracker.addImage(this.undoBothI, 1);
        this.tracker.addImage(this.undoUndoI, 2);
        this.tracker.addImage(this.undoRedoI, 3);
        this.addListeners();
    }
    
    private void addListeners() {
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(final MouseEvent mouseEvent) {
                final int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                if (UndoPanel.this.upArrow.on(x, y)) {
                    UndoPanel.this.controller.panUp();
                }
                else if (UndoPanel.this.downArrow.on(x, y)) {
                    UndoPanel.this.controller.panDown();
                }
                else if (UndoPanel.this.rightArrow.on(x, y)) {
                    UndoPanel.this.controller.panRight();
                }
                else if (UndoPanel.this.leftArrow.on(x, y)) {
                    UndoPanel.this.controller.panLeft();
                }
                else if (UndoPanel.this.center.on(x, y)) {
                    UndoPanel.this.controller.center();
                }
                else if (UndoPanel.this.zoomIn.on(x, y)) {
                    UndoPanel.this.controller.zoomIn();
                }
                else if (UndoPanel.this.zoomOut.on(x, y)) {
                    UndoPanel.this.controller.zoomOut();
                }
                else if (UndoPanel.this.undo.on(x, y)) {
                    UndoPanel.this.controller.undo();
                }
                else if (UndoPanel.this.redo.on(x, y)) {
                    UndoPanel.this.controller.redo();
                }
                UndoPanel.this.repaint();
            }
        });
    }
    
    private void iconCheck() {
        for (int i = 0; i < 4; ++i) {
            try {
                this.tracker.waitForID(i);
            }
            catch (InterruptedException ex) {}
            if (this.tracker.isErrorID(i)) {
                System.err.println("Warning--unable to load undoPanel icon " + i);
            }
        }
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
                    final UndoableStack undoStack = this.controller.getUndoStack();
                    Image image;
                    if (undoStack.isUndoable()) {
                        if (undoStack.isRedoable()) {
                            image = this.undoBothI;
                        }
                        else {
                            image = this.undoUndoI;
                        }
                    }
                    else if (undoStack.isRedoable()) {
                        image = this.undoRedoI;
                    }
                    else {
                        image = this.undoNeitherI;
                    }
                    super.offg.drawImage(image, 0, 0, this);
                    super.offg.setColor(Color.black);
                    super.offg.drawRect(0, -1, 66, 109);
                    graphics.drawImage(super.offscreen, 0, 0, this);
                }
                // monitorexit(offscreen)
                finally {}
            }
            // monitorexit(offg)
            finally {}
        }
    }
    
    class ActiveRegion
    {
        int lx;
        int ux;
        int ly;
        int uy;
        
        public ActiveRegion(final int lx, final int ly, final int ux, final int uy) {
            this.lx = lx;
            this.ly = ly;
            this.ux = ux;
            this.uy = uy;
        }
        
        public boolean on(final int n, final int n2) {
            return n >= this.lx && n <= this.ux && n2 >= this.ly && n2 <= this.uy;
        }
    }
}
