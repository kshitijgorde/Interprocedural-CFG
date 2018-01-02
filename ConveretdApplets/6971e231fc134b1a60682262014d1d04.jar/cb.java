import java.util.Observable;
import java.awt.Graphics;
import java.util.Observer;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class cb extends Applet implements Observer
{
    public ae a;
    public String _fldfor;
    public String _fldif;
    public String _flddo;
    public String chk_str;
    private String[][] _fldint;
    
    public cb() {
        this._fldint = new String[][] { { "type", "\uad6c\ubd84", "\uc790\ub8cc \uc885\ub958\uc5d0 \ub300\ud55c \uad6c\ubd84" }, { "key", "\ucf54\ub4dc", "\ud0a4 \uac12\uc73c\ub85c \uc0ac\uc6a9\ub418\ub294 \ucf54\ub4dc" }, { "sec", "\uc99d\uad8c\uc0ac", "\uc99d\uad8c\uc0ac \uc601\ubb38 \uc57d\uc5b4" } };
    }
    
    public void init() {
        this._fldfor = new String(this.getParameter("type"));
        this._fldif = new String(this.getParameter("key"));
        this._flddo = new String(this.getParameter("sec"));
        this.a = new ae(this._fldfor, this._fldif, this._flddo);
        this.a.F.addObserver(this);
        this.chk_str = "1";
        this.a.start();
        this.chk_str = "2";
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void destroy() {
        this.a.interrupt();
        this.a.F.deleteObserver(this);
        this.a = null;
    }
    
    public void paint(final Graphics graphics) {
        this.paintComponents(graphics);
    }
    
    public String getAppletInfo() {
        return "ChartApplet v0.5 Copyright (c) 1999-2000, C-cube Technology";
    }
    
    public String[][] getParameterInfo() {
        return this._fldint;
    }
    
    public void update(final Observable observable, final Object o) {
        if (((ay)observable)._flddo == 2) {
            ((ay)observable)._fldif = -1;
            this.repaint();
        }
        else if (((ay)observable)._flddo == 1) {
            this.a.y = ((ay)observable)._fldif;
        }
    }
}
