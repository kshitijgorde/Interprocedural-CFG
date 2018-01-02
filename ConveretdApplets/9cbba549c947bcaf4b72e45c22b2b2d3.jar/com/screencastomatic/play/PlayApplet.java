// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play;

import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.awt.Image;
import javax.swing.JDialog;
import java.awt.Frame;
import javax.swing.JFrame;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.screencastomatic.play.b.b;
import java.awt.image.BufferedImage;
import java.awt.Window;
import java.applet.Applet;

public class PlayApplet extends Applet
{
    Display m_display;
    Window m_popout;
    BufferedImage m_poppedMsg;
    boolean m_destroyOnStop;
    
    public void init() {
        System.out.println("--- Init called on applet.");
        if (this.getParameter("noproxy") != null) {
            b.a(false);
        }
        this.m_display = new Display(new H(this));
        this.setLayout(new BorderLayout(0, 0));
        this.add(this.m_display, "Center");
        this.validate();
        if (System.getProperty("os.name").toLowerCase().startsWith("mac")) {
            this.m_popout = new JFrame(this.getParameter("title"));
            ((JFrame)this.m_popout).setDefaultCloseOperation(1);
        }
        else {
            this.m_popout = new JDialog((Frame)null, this.getParameter("title"));
            ((Frame)this.m_popout.getOwner()).setIconImage(com.screencastomatic.play.c.b.a("png", "watch_frame_icon.png"));
            ((JDialog)this.m_popout).setDefaultCloseOperation(1);
        }
        this.m_popout.setSize(this.getWidth(), this.getHeight());
        this.m_popout.setLayout(new BorderLayout(0, 0));
        this.m_popout.addWindowListener(new F(this));
        this.m_display.b();
        this.m_poppedMsg = com.screencastomatic.play.c.b.a("png", "watch_poppedout.png");
        this.addMouseListener(new G(this));
        this.m_destroyOnStop = (this.getParameter("destroyonstop") != null);
    }
    
    public void start() {
        System.out.println("--- Start called on applet.");
    }
    
    public void stop() {
        System.out.println("--- Stop called on applet.");
        if (this.m_destroyOnStop) {
            System.out.println("We're told to destroy on stop so calling destroy.");
            this.destroy();
        }
    }
    
    public void destroy() {
        System.out.println("--- Destroy called on applet.");
        this.m_display.c();
        this.m_popout.dispose();
    }
    
    public int getPositionSec() {
        return this.m_display.d();
    }
    
    public boolean isPaused() {
        return this.m_display.e();
    }
    
    public void setPaused(final boolean b) {
        this.m_display.a(b);
    }
    
    public void setPositionSec(final int n) {
        this.m_display.b(n);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.m_popout.isVisible()) {
            super.paint(graphics);
            return;
        }
        graphics.setColor(Color.GRAY);
        graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics.drawImage(this.m_poppedMsg, (this.getWidth() - this.m_poppedMsg.getWidth()) / 2, (this.getHeight() - this.m_poppedMsg.getHeight()) / 2, null);
    }
}
