// 
// Decompiled by Procyon v0.5.30
// 

package com.yahoo.games.client.common.remotetable;

import java.awt.Event;
import netscape.javascript.JSObject;
import java.awt.Graphics;
import y.dx;
import y.ew;
import y.cr;
import java.applet.Applet;

public class RemoteTableApplet extends Applet implements cr
{
    private ew a;
    private String a;
    private String b;
    private int a;
    private String c;
    private dx a;
    private Object a;
    private boolean a;
    private long a;
    
    public RemoteTableApplet() {
        this.a = ew.a();
    }
    
    public void init() {
        this.a.b("Reading in parameters");
        this.a = this.getParameter("rmn");
        if (null == this.a) {
            this.a.e("Could not read in rmn");
        }
        else {
            this.a.b("rmn=" + this.a);
        }
        this.b = this.getParameter("roomname");
        if (null == this.b) {
            this.a.e("Could not read in roomname");
        }
        else {
            this.a.b("roomname=" + this.b);
        }
        this.c = this.getParameter("login");
        if (null == this.c) {
            this.a.e("Could not read in login");
        }
        else {
            this.a.b("login=" + this.c);
        }
        final String parameter = this.getParameter("tablenumber");
        if (null != parameter) {
            this.a.b("tablenumber=" + parameter);
            try {
                this.a = Integer.parseInt(parameter);
            }
            catch (NumberFormatException ex) {
                this.a.c("Could not parse tablenumber", ex);
            }
        }
        else {
            this.a.e("Could not read in tablenumber");
        }
        final String parameter2 = this.getParameter("ts");
        if (null != parameter2) {
            this.a.b("ts=" + parameter2);
            try {
                this.a = Long.parseLong(parameter2);
            }
            catch (NumberFormatException ex2) {
                this.a.c("Could not parse ts", ex2);
            }
        }
        try {
            Thread.sleep(100L);
        }
        catch (InterruptedException ex3) {}
        this.a.b("Initiailzing SlaveWindow");
        (this.a = new dx(this.a, this, this.b, this.a, this.c, this.a)).b(6, this);
    }
    
    public void start() {
    }
    
    public void destroy() {
        this.a.b("RemoteTableApplet.destroy()");
        this.a = true;
        final dx a = this.a;
        a.b(1, a);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void onExitTableApplet() {
        if (!this.a) {
            this.a.b("RemoteTableApplet.onExitTableApplet()");
            this.a("closeWindow()");
            this.a = true;
            return;
        }
        this.a.b("Ingoring onExitTableApplet() as window is closed");
    }
    
    public void onSetWindowTitle(final String s) {
        this.a.b("Got new window title:" + s);
        this.a("updateTitle(\"" + s + "\")");
    }
    
    private Object a(final String s) {
        Object eval = null;
        try {
            eval = JSObject.getWindow((Applet)this).eval(s);
        }
        catch (Exception ex) {
            this.a.b("cmd=" + s, ex);
        }
        return eval;
    }
    
    public void onSetExitComponent(final Object a) {
        this.a.b("onSetExitComponent");
        this.a = a;
    }
    
    public boolean handleEvent(final Event event) {
        boolean handleEvent;
        if (1001 == event.id && event.target == this.a) {
            this.a.b(8, null);
            handleEvent = true;
        }
        else {
            handleEvent = super.handleEvent(event);
        }
        return handleEvent;
    }
    
    public void onRotateAd() {
        this.a.b("RemoteTableApplet.onRotateAd()");
        this.a("rotateTableAd()");
    }
    
    public void onRequestWindowClose() {
        this.a.b("RemoteTableApplet.onRequestWindowClose()");
        this.onExitTableApplet();
    }
}
