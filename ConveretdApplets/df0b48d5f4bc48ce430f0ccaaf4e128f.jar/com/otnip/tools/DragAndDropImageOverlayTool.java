// 
// Decompiled by Procyon v0.5.30
// 

package com.otnip.tools;

import java.awt.image.ImageObserver;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.MouseInfo;
import org.jdesktop.animation.timing.Animator;
import javax.swing.JRootPane;
import java.awt.Graphics2D;
import java.awt.dnd.DragSourceListener;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragGestureEvent;
import java.awt.LayoutManager;
import java.awt.Component;
import javax.swing.SwingUtilities;
import java.awt.Point;
import javax.swing.Icon;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import java.awt.Container;
import org.jdesktop.animation.timing.TimingTarget;
import java.awt.dnd.DragSourceAdapter;

public class DragAndDropImageOverlayTool extends DragSourceAdapter implements TimingTarget, Runnable
{
    private static final int SNAP_BACK_TIME = 500;
    private static DragAndDropImageOverlayTool dragNDropImageComponent;
    private final Container glassPane;
    private final JLabel dragComponent;
    private final BufferedImage image;
    private final int startX;
    private final int startY;
    private final int dx;
    private final int dy;
    private int currentX;
    private int currentY;
    private boolean keepRunning;
    private boolean accepted;
    
    private DragAndDropImageOverlayTool(final Container glassPane, final BufferedImage image, final int startX, final int startY, final int dx, final int dy) {
        this.keepRunning = true;
        this.accepted = true;
        this.glassPane = glassPane;
        this.image = image;
        (this.dragComponent = new JLabel(new ImageIcon(image))).setBounds(0, 0, image.getWidth(), image.getHeight());
        this.dx = dx;
        this.dy = dy;
        final Point p = new Point(startX, startY);
        SwingUtilities.convertPointFromScreen(p, glassPane);
        this.startX = (int)p.getX();
        this.startY = (int)p.getY();
        glassPane.setLayout(null);
        glassPane.add(this.dragComponent);
        this.updateImageLocation();
        glassPane.setVisible(true);
        glassPane.repaint();
        final Thread thread = new Thread(this);
        thread.setPriority(1);
        thread.start();
    }
    
    public static synchronized void startDrag(final DragGestureEvent dragGestureEvent, final Transferable transferable, BufferedImage image, int... offset) {
        if (image == null) {
            final Component c = dragGestureEvent.getComponent();
            image = new BufferedImage(c.getWidth(), c.getHeight(), 2);
            final Graphics2D g = image.createGraphics();
            c.paint(g);
            g.dispose();
        }
        if (offset.length == 0) {
            final Point temp = new Point(dragGestureEvent.getDragOrigin());
            offset = new int[] { temp.x, temp.y };
        }
        final JRootPane rootPane = SwingUtilities.getRootPane(dragGestureEvent.getComponent());
        final Point start = new Point(dragGestureEvent.getDragOrigin());
        SwingUtilities.convertPointToScreen(start, dragGestureEvent.getComponent());
        if (DragAndDropImageOverlayTool.dragNDropImageComponent != null) {
            DragAndDropImageOverlayTool.dragNDropImageComponent.dispose();
        }
        dragGestureEvent.startDrag(null, transferable, DragAndDropImageOverlayTool.dragNDropImageComponent = new DragAndDropImageOverlayTool((Container)rootPane.getGlassPane(), image, start.x, start.y, offset[0], offset[1]));
    }
    
    public static synchronized void stopDrag(final boolean accepted) {
        if (DragAndDropImageOverlayTool.dragNDropImageComponent != null) {
            DragAndDropImageOverlayTool.dragNDropImageComponent.accepted = accepted;
            final Animator animator = new Animator(500, (TimingTarget)DragAndDropImageOverlayTool.dragNDropImageComponent);
            animator.start();
        }
    }
    
    private void updateImageLocation() {
        final Point point = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(point, this.glassPane);
        final int newX = (int)point.getX() - this.dx;
        final int newY = (int)point.getY() - this.dy;
        if (newX != this.dragComponent.getX() && newY != this.dragComponent.getY()) {
            this.currentX = (int)point.getX();
            this.currentY = (int)point.getY();
            this.dragComponent.setBounds(newX, newY, this.dragComponent.getWidth(), this.dragComponent.getHeight());
            this.dragComponent.repaint();
        }
    }
    
    public void dragDropEnd(final DragSourceDropEvent dsde) {
        stopDrag(dsde.getDropSuccess());
    }
    
    private void dispose() {
        this.keepRunning = false;
        this.glassPane.setVisible(false);
        this.glassPane.remove(this.dragComponent);
    }
    
    public void timingEvent(final float x) {
        final BufferedImage newImage = new BufferedImage(this.image.getWidth(), this.image.getHeight(), 2);
        final Graphics2D g2 = newImage.createGraphics();
        g2.setColor(new Color(1.0f, 1.0f, 1.0f, 1.0f - x));
        g2.fillRect(0, 0, this.image.getWidth(), this.image.getHeight());
        g2.setComposite(AlphaComposite.SrcIn);
        g2.drawImage(this.image, 0, 0, null);
        final ImageIcon icon = new ImageIcon(newImage);
        this.dragComponent.setIcon(icon);
        if (!this.accepted) {
            final int X = (int)(this.currentX + (this.startX - this.currentX) * x);
            final int Y = (int)(this.currentY + (this.startY - this.currentY) * x);
            this.dragComponent.setBounds(X, Y, this.dragComponent.getWidth(), this.dragComponent.getHeight());
        }
        this.dragComponent.repaint();
    }
    
    public void begin() {
        this.keepRunning = false;
    }
    
    public void end() {
        this.dispose();
    }
    
    public void repeat() {
    }
    
    public void run() {
        while (this.keepRunning) {
            try {
                final Runnable runnable = new Runnable() {
                    public void run() {
                        DragAndDropImageOverlayTool.this.updateImageLocation();
                    }
                };
                SwingUtilities.invokeAndWait(runnable);
                Thread.sleep(33L);
            }
            catch (Exception ex) {
                System.err.println("Error Updating Image Location on Drag And Drop:  " + ex.getMessage());
            }
        }
    }
    
    static {
        DragAndDropImageOverlayTool.dragNDropImageComponent = null;
    }
}
