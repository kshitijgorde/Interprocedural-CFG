// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.AWTEvent;
import java.awt.AWTEventMulticaster;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

class ImageButtonCanvas extends Canvas implements MouseListener
{
    static int sid;
    protected int id;
    private String cmd;
    private Image activeImage;
    private Image overImage;
    private Image inactiveImage;
    private Image pressedImage;
    private boolean mouse_pressed;
    private boolean enable_button;
    private boolean mouse_over;
    private String actionCommand;
    private transient ActionListener actionListener;
    private Applet applet;
    
    static {
        ImageButtonCanvas.sid = 1;
    }
    
    protected ImageButtonCanvas(final Applet a, final Image active, final Image inactive, final Image pressed, final Image over) {
        this.id = ImageButtonCanvas.sid++;
        this.cmd = "clicked";
        this.mouse_pressed = false;
        this.enable_button = true;
        this.mouse_over = false;
        this.actionCommand = new StringBuffer().append(this.id).toString();
        this.applet = null;
        this.applet = a;
        this.activeImage = active;
        this.overImage = over;
        this.inactiveImage = inactive;
        this.pressedImage = pressed;
        this.addMouseListener(this);
        this.checkImages();
    }
    
    protected boolean checkImages() {
        final MediaTracker t = new MediaTracker(this.applet);
        if (this.activeImage != null) {
            t.addImage(this.activeImage, 1);
        }
        if (this.overImage != null) {
            t.addImage(this.overImage, 2);
        }
        if (this.inactiveImage != null) {
            t.addImage(this.inactiveImage, 3);
        }
        if (this.pressedImage != null) {
            t.addImage(this.pressedImage, 4);
        }
        try {
            t.waitForAll(5000L);
        }
        catch (Exception e) {
            Debug.report(e);
        }
        final boolean result = t.isErrorAny();
        if (result) {
            Debug.report("Error loading button images.");
        }
        return t.isErrorAny();
    }
    
    protected ImageButtonCanvas(final Applet a, final Image active, final Image over) {
        this(a, active, null, null, over);
    }
    
    protected ImageButtonCanvas(final Image active, final Image pressed, final String inHelp, final Applet inApplet) {
        this(inApplet, active, null, pressed, null);
        this.cmd = inHelp;
        if (inApplet instanceof ActionListener) {
            this.addActionListener((ActionListener)inApplet);
        }
    }
    
    protected void changeFunction(final Image upImage, final Image downImage, final String text) {
        this.activeImage = upImage;
        this.pressedImage = downImage;
        this.cmd = text;
        this.repaint();
    }
    
    protected void setCommand(final String s) {
        this.cmd = s;
    }
    
    protected String getCommand() {
        return this.cmd;
    }
    
    public void mousePressed(final MouseEvent me) {
        this.mouse_pressed = true;
        this.processActionEvent(new ActionEvent(this, 1001, "mousePressed"));
        this.repaint();
    }
    
    public void mouseReleased(final MouseEvent me) {
        if (this.mouse_pressed) {
            this.mouse_pressed = false;
            this.processActionEvent(new ActionEvent(this, 1001, "mouseReleased"));
            this.repaint();
        }
    }
    
    public void mouseClicked(final MouseEvent me) {
    }
    
    public void mouseEntered(final MouseEvent me) {
        this.mouse_over = true;
        if (this.cmd != null && this.applet != null) {
            this.applet.getAppletContext().showStatus(this.cmd);
        }
        this.deliverEvent(new Event(this, 1001, this.cmd));
        this.processActionEvent(new ActionEvent(this, 1001, "mouseEntered"));
        this.repaint();
    }
    
    public void mouseExited(final MouseEvent me) {
        this.mouse_over = false;
        this.processActionEvent(new ActionEvent(this, 1001, "mouseExited"));
        this.repaint();
    }
    
    public String getActionCommand() {
        return (this.actionCommand == null) ? this.toString() : this.actionCommand;
    }
    
    public void setActionCommand(final String actionCommand) {
        this.actionCommand = actionCommand;
    }
    
    public void addNotify() {
        super.addNotify();
    }
    
    public synchronized void addActionListener(final ActionListener l) {
        if (l == null) {
            return;
        }
        this.actionListener = AWTEventMulticaster.add(this.actionListener, l);
    }
    
    public synchronized void removeActionListener(final ActionListener l) {
        if (l == null) {
            return;
        }
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, l);
    }
    
    protected void enableButton() {
        super.setEnabled(this.enable_button = true);
        this.repaint();
    }
    
    public void disableButton() {
        super.setEnabled(this.enable_button = false);
        this.repaint();
    }
    
    protected void processActionEvent(final ActionEvent e) {
        if (this.actionListener != null) {
            this.actionListener.actionPerformed(e);
        }
    }
    
    protected void processEvent(final AWTEvent e) {
        if (e instanceof ActionEvent) {
            this.processActionEvent((ActionEvent)e);
            return;
        }
        super.processEvent(e);
    }
    
    public void paint(final Graphics g) {
        if (!this.enable_button) {
            if (this.inactiveImage != null) {
                g.drawImage(this.inactiveImage, 0, 0, this);
            }
            else {
                g.drawImage(this.activeImage, 0, 0, this);
            }
        }
        else if (this.mouse_pressed) {
            if (this.pressedImage != null) {
                g.drawImage(this.pressedImage, 0, 0, this);
            }
            else if (this.overImage != null) {
                g.drawImage(this.overImage, 0, 0, this);
            }
            else {
                g.drawImage(this.activeImage, 1, 1, this);
            }
        }
        else if (this.mouse_over && this.overImage != null) {
            g.drawImage(this.overImage, 0, 0, this);
        }
        else {
            g.drawImage(this.activeImage, 0, 0, this);
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
