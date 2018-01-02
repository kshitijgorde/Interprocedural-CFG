// 
// Decompiled by Procyon v0.5.30
// 

package com.objectbox.gui.lwcomp;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.Button;
import com.objectbox.runner.gui.CloseableFrame;
import java.awt.Container;
import com.objectbox.runner.util.JBLogger;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.applet.Applet;
import com.objectbox.runner.gui.IAppletWindow;
import java.awt.Window;

public class JBSmallWindow extends Window implements IAppletWindow
{
    BorderPanel centerpanel;
    LWWindowMover mover;
    public static int header_height;
    private boolean done;
    private Applet applettorun;
    
    static {
        JBSmallWindow.header_height = 8;
    }
    
    public JBSmallWindow(final Frame frame) {
        super(frame);
        this.centerpanel = new BorderPanel(new BorderLayout());
        this.mover = null;
        this.done = false;
        this.applettorun = null;
        this.setLayout(null);
        (this.mover = new LWWindowMover(this, "")).setHeaderheight(JBSmallWindow.header_height);
        this.add(this.mover, "North");
        this.mover.setLocation(0, 0);
        this.mover.setSize(this.getSize().width, JBSmallWindow.header_height);
        this.add(this.centerpanel, "Center");
        this.centerpanel.setLocation(1, JBSmallWindow.header_height);
        this.centerpanel.setSize(this.getSize().width - 2, this.getSize().height - JBSmallWindow.header_height - 1);
    }
    
    public void addApplet(final Applet applettorun) {
        this.applettorun = applettorun;
        this.setSize(this.applettorun.getSize().width + 2, this.applettorun.getSize().height + JBSmallWindow.header_height + 1);
        this.getContentPanel().add(applettorun, "Center");
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        JBLogger.log("finalize JBSmallWindow");
    }
    
    public Container getContentPanel() {
        return this.centerpanel;
    }
    
    public boolean isDone() {
        return this.done;
    }
    
    public void kill() {
        try {
            if (this.applettorun != null) {
                this.applettorun.stop();
                this.applettorun.destroy();
                this.remove(this.applettorun);
            }
            System.gc();
            this.done = true;
            JBLogger.log("JBSmallWindow Stopping applet");
        }
        catch (Exception ex) {
            JBLogger.log("JBSMallWindow.kill " + ex.toString());
        }
        finally {
            this.done = true;
        }
        this.done = true;
    }
    
    public static void main(final String[] array) {
        final CloseableFrame closeableFrame = new CloseableFrame("Mother");
        closeableFrame.setSize(100, 100);
        final Button button = new Button("Run");
        final JBSmallWindow jbSmallWindow = new JBSmallWindow(closeableFrame);
        jbSmallWindow.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - 200, 0);
        jbSmallWindow.setSize(200, 200);
        final Panel panel = new Panel(new BorderLayout());
        panel.add(new Button("North"), "North");
        panel.add(new Button("Hei"), "Center");
        panel.add(new Button("Syden"), "South");
        panel.add(new Button("Vest"), "West");
        jbSmallWindow.getContentPanel().add(panel, "Center");
        jbSmallWindow.setVisible(true);
        jbSmallWindow.validate();
        closeableFrame.add(button);
        closeableFrame.setVisible(true);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, this.getSize().width - 1, this.getSize().height - 1);
    }
    
    public void setSize(final int n, final int n2) {
        super.setSize(n, n2);
        this.mover.setLocation(0, 0);
        this.mover.setSize(this.getSize().width, JBSmallWindow.header_height);
        this.centerpanel.setLocation(1, JBSmallWindow.header_height);
        this.centerpanel.setSize(this.getSize().width - 2, this.getSize().height - JBSmallWindow.header_height - 1);
        JBLogger.log("SmallWindow.setSize(w,h) " + n + "," + n2);
    }
    
    public void setSize(final Dimension dimension) {
        this.setSize(dimension.width, dimension.height);
        JBLogger.log("SmallWindow.setSize " + dimension);
    }
    
    public String toString() {
        return super.toString();
    }
}
