// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;

public class p
{
    static final int long = 10;
    public Color do;
    Font byte;
    static FontMetrics char;
    int new;
    int if;
    Image case;
    Graphics int;
    av else;
    private boolean goto;
    int for;
    int a;
    bi try;
    
    p(final av else1, final int new1, final int if1) {
        this.do = Color.blue;
        this.byte = new Font("Arial", 0, 10);
        this.goto = false;
        this.else = else1;
        this.new = new1;
        this.byte = a0.f;
        this.if = if1;
    }
    
    void a(final int new1, final int if1) {
        this.new = new1;
        this.if = if1;
        this.case = null;
        this.a(true);
    }
    
    public Dimension if() {
        return new Dimension(this.new, this.if);
    }
    
    public void a(final boolean goto1) {
        this.goto = goto1;
    }
    
    public boolean a() {
        return this.goto;
    }
}
