// 
// Decompiled by Procyon v0.5.30
// 

package MudFE;

import java.awt.Color;

class Attribute
{
    public static Color _defaultbg;
    public static Color _defaultfg;
    public static Attribute _defaultat;
    private Color fg;
    private Color bg;
    
    private void Create(final Color f, final Color g) {
        if (f.equals(Attribute._defaultfg)) {
            this.fg = Attribute._defaultfg;
        }
        else if (f.equals(Attribute._defaultbg)) {
            this.fg = Attribute._defaultbg;
        }
        else {
            this.fg = f;
        }
        if (g.equals(Attribute._defaultbg)) {
            this.bg = Attribute._defaultbg;
        }
        else if (g.equals(Attribute._defaultfg)) {
            this.bg = Attribute._defaultfg;
        }
        else {
            this.bg = g;
        }
    }
    
    void set(final Color f, final Color b) {
        this.fg = new Color(f.getRed(), f.getGreen(), f.getBlue());
        this.bg = new Color(b.getRed(), b.getGreen(), b.getBlue());
    }
    
    void set(final Attribute a) {
        this.Create(a.getFg(), a.getBg());
    }
    
    public String toString() {
        return "R:" + this.fg.getRed() + " B:" + this.fg.getBlue() + " G:" + this.fg.getGreen() + " r: " + this.bg.getRed() + " b:" + this.bg.getBlue() + " g:" + this.bg.getGreen() + ".";
    }
    
    public Attribute() {
        this.Create(Attribute._defaultfg, Attribute._defaultbg);
    }
    
    public Attribute(final Attribute att) {
        this.Create(att.getFg(), att.getBg());
    }
    
    public Attribute(final Color f, final Color b) {
        this.Create(f, b);
    }
    
    public static Color getdfbg() {
        return Attribute._defaultbg;
    }
    
    Color getFg() {
        return this.fg;
    }
    
    void setFg(final Color f) {
        this.fg = f;
    }
    
    static {
        Attribute._defaultbg = Color.black;
        Attribute._defaultfg = Color.white;
        Attribute._defaultat = new Attribute(Attribute._defaultfg, Attribute._defaultbg);
    }
    
    boolean eq(final Attribute a) {
        return this.fg.equals(a.getFg()) && this.bg.equals(a.getBg());
    }
    
    public static Color getdffg() {
        return Attribute._defaultfg;
    }
    
    Color getBg() {
        return this.bg;
    }
    
    void setBg(final Color f) {
        this.bg = f;
    }
}
