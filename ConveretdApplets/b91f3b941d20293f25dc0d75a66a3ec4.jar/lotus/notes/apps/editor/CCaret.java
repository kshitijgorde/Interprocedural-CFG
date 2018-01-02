// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.editor;

import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;

class CCaret implements Runnable
{
    private boolean cShowing;
    private CRTEditor cParent;
    private Point cPoint;
    private int cBottom;
    private int cDisabled;
    private boolean cUpdated;
    private boolean cFocus;
    private boolean cHasPosition;
    private boolean cQuit;
    private Thread cThread;
    
    CCaret(final CRTEditor cParent) {
        this.cParent = cParent;
        this.cPoint = new Point(0, 0);
    }
    
    public synchronized void run() {
        try {
            while (!this.cQuit) {
                this.wait(500L);
                while (!this.cFocus) {
                    this.wait();
                }
                if (this.cUpdated) {
                    this.cUpdated = false;
                }
                else {
                    this.toggle();
                }
            }
        }
        catch (InterruptedException ex) {
            System.out.println("catch InterruptedException in cCaret.run()");
        }
    }
    
    synchronized void toggle() {
        if (this.cDisabled == 0) {
            if (this.cShowing) {
                this.hide();
            }
            else {
                this.show();
            }
        }
    }
    
    synchronized void hide() {
        if (this.cShowing && this.cDisabled == 0) {
            this.cShowing = false;
            this.draw();
        }
    }
    
    synchronized void show() {
        if (!this.cShowing && this.cDisabled == 0) {
            this.cShowing = true;
            this.draw();
        }
    }
    
    synchronized void setFocus(final boolean cFocus) {
        if (cFocus != this.cFocus) {
            this.cFocus = cFocus;
            if (cFocus) {
                if (this.cThread == null) {
                    this.cThread = new Thread(this);
                    try {
                        this.cThread.checkAccess();
                        this.cThread.setPriority(2);
                    }
                    catch (SecurityException ex) {
                        System.out.println("Can't access thread to set priority");
                    }
                    this.cThread.start();
                }
                else if (this.cThread.isAlive()) {
                    this.notify();
                }
            }
            else {
                this.hide();
            }
        }
    }
    
    synchronized void setPosition(final Point point, final int n) {
        this.cPoint.move(point.x, point.y);
        this.cBottom = n - 1;
        final boolean b = true;
        this.cUpdated = b;
        this.cHasPosition = b;
        this.notify();
    }
    
    private void draw() {
        if (this.cHasPosition) {
            final Graphics graphics = this.cParent.getParent().getGraphics();
            if (graphics != null) {
                final CContainer container = this.cParent.getContainer();
                if (container != null) {
                    final Insets margins = this.cParent.getMargins();
                    graphics.translate(margins.left, -container.getYOffset() + margins.top);
                    graphics.setColor(this.cParent.getBackground());
                    graphics.setXORMode(Color.black);
                    graphics.drawLine(this.cPoint.x, this.cPoint.y, this.cPoint.x, this.cBottom);
                    graphics.drawLine(this.cPoint.x - 1, this.cPoint.y, this.cPoint.x - 1, this.cBottom);
                    graphics.dispose();
                }
            }
        }
    }
    
    synchronized void setEnabled(final boolean b) {
        if (b) {
            if (this.cDisabled > 0) {
                --this.cDisabled;
            }
        }
        else {
            if (this.cDisabled == 0) {
                this.hide();
            }
            ++this.cDisabled;
        }
    }
    
    final boolean isShowable() {
        return this.cFocus && !this.cShowing && this.cDisabled == 0;
    }
    
    final void quit() {
        this.cQuit = true;
        if (this.cThread != null) {
            this.cThread.stop();
            this.cThread = null;
        }
    }
}
