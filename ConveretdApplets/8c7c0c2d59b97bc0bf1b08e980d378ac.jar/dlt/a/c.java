// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import dlt.a.a.m;
import dlt.a.a.f;
import dlt.a.a.l;
import dlt.a.d.d;
import dlt.a.e.b;
import java.awt.Container;
import dlt.a.a.a;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;

public abstract class c extends JFrame implements MouseMotionListener, KeyListener
{
    protected a a;
    protected int for;
    protected int do;
    protected Container try;
    protected b case;
    protected d byte;
    protected dlt.a.d.b else;
    protected dlt.a.e.a int;
    protected l new;
    protected f char;
    protected m if;
    
    public c() {
        this.for = 0;
        this.do = 0;
        this.a();
    }
    
    private void a() {
        this.enableEvents(1L);
        this.enableEvents(8L);
        (this.try = this.getContentPane()).setLayout(new BorderLayout());
        (this.a = new a(400, 400)).addMouseMotionListener(this);
        this.a.addKeyListener(this);
        this.try.add(this.a, "Center");
        this.setVisible(true);
        this.setSize(400, 400);
        this.setBackground(Color.black);
        this.a.setBackground(Color.black);
        this.a.getGraphics().setColor(Color.cyan);
        this.for = 200;
        this.do = 200;
    }
    
    public a if() {
        return this.a;
    }
    
    public abstract void int();
    
    public abstract void for();
    
    public abstract void do();
    
    public void processWindowEvent(final WindowEvent windowEvent) {
        if (windowEvent.getID() == 201) {
            System.exit(0);
        }
        super.processWindowEvent(windowEvent);
    }
    
    public abstract void mouseDragged(final MouseEvent p0);
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
}
