// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import com.daysofwonder.b.a;
import java.awt.Point;
import java.util.StringTokenizer;
import java.util.Vector;
import com.daysofwonder.util.UIProperties;
import java.awt.Rectangle;

public abstract class am
{
    protected String F;
    protected Rectangle G;
    protected Rectangle H;
    protected Rectangle I;
    protected boolean J;
    protected boolean K;
    protected String L;
    protected Object M;
    protected ap N;
    protected UIProperties O;
    protected String P;
    protected Object Q;
    protected int R;
    protected int S;
    protected boolean T;
    protected Vector U;
    protected String V;
    
    public am(final ap n, final String f, final UIProperties uiProperties, final UIProperties o) {
        this.R = -1;
        this.S = 0;
        this.K = true;
        this.J = true;
        this.F = f;
        this.N = n;
        this.O = o;
        if (uiProperties.a(f + ".draw.r") != null) {
            this.G = aL.a(uiProperties, f + ".draw.r");
            this.H = aL.a(uiProperties, f + ".focus.r");
            this.I = aL.a(uiProperties, f + ".click.r");
        }
        else {
            this.G = new Rectangle();
            this.H = new Rectangle();
            this.I = new Rectangle();
        }
        this.Q = this;
        if (uiProperties.a(f + ".action") != null) {
            this.P = uiProperties.a(f + ".action");
        }
        if (uiProperties.a(f + ".tooltip") != null) {
            this.L = this.O.b(uiProperties.a(f + ".tooltip"));
        }
        if (uiProperties.a(f + ".viewid") != null) {
            this.R = Integer.parseInt(uiProperties.a(f + ".viewid"));
        }
        if (uiProperties.a(f + ".layer") != null) {
            this.S = Integer.parseInt(uiProperties.a(f + ".layer"));
        }
        if (uiProperties.a(f + ".group") != null) {
            final String a = uiProperties.a(f + ".group");
            if (a.indexOf(44) != -1) {
                this.U = new Vector();
                final StringTokenizer stringTokenizer = new StringTokenizer(a, ", ");
                while (stringTokenizer.hasMoreTokens()) {
                    this.U.addElement(stringTokenizer.nextToken());
                }
            }
            else {
                this.V = uiProperties.a(f + ".group");
            }
        }
    }
    
    public boolean h() {
        return this.K;
    }
    
    public void a(final boolean k) {
        this.K = k;
    }
    
    public int i() {
        return this.R;
    }
    
    public void c(final boolean j) {
        this.J = j;
    }
    
    public String j() {
        return this.P;
    }
    
    public void b(final String p) {
        this.P = p;
    }
    
    public Object k() {
        return this.M;
    }
    
    public String a(final Point point) {
        return this.L;
    }
    
    public String l() {
        return this.F;
    }
    
    public int m() {
        return this.S;
    }
    
    public void c(final int s) {
        this.S = s;
    }
    
    public Rectangle n() {
        return this.I;
    }
    
    public Rectangle o() {
        return this.H;
    }
    
    public Rectangle p() {
        return this.G;
    }
    
    public String q() {
        return (this.U == null) ? this.V : this.U.elementAt(0);
    }
    
    public Vector r() {
        return this.U;
    }
    
    public void a(final int n, final int n2) {
        this.G.setLocation(n, n2);
        this.I.setLocation(n, n2);
        this.H.setLocation(n, n2);
    }
    
    public abstract void a();
    
    public abstract void a(final a p0);
    
    public abstract boolean a(final MouseEvent p0);
    
    public boolean d_() {
        return false;
    }
    
    public void c(final MouseEvent mouseEvent) {
    }
    
    public void d(final MouseEvent mouseEvent) {
    }
    
    public void e(final MouseEvent mouseEvent) {
    }
    
    public void a(final KeyEvent keyEvent) {
    }
    
    public void b(final KeyEvent keyEvent) {
    }
    
    public void s() {
    }
    
    public void t() {
    }
    
    public boolean b(final Point point) {
        return false;
    }
    
    public Object c(final Point point) {
        return null;
    }
    
    public String d(final Point point) {
        return "";
    }
    
    public aE a(final z z, final Point point, final Object o, final String s) {
        return null;
    }
    
    public boolean a(final Point point, final Object o, final String s) {
        return false;
    }
    
    public boolean a(final Point point, final Object o, final am am, final String s) {
        return this.a(point, o, s);
    }
    
    public void a(final z z, final Object o, final String s, final am am, final Point point, final int n, final boolean b, final boolean b2) {
    }
    
    public void a(final z z, final Object o, final String s, final am am, final Point point) {
    }
    
    public void a(final z z, final Object o, final String s, final Point point, final Point point2, final aE ae) {
    }
    
    public boolean c_() {
        return false;
    }
    
    public void b(final MouseEvent mouseEvent) {
    }
    
    public boolean a(final a a, final int n) {
        return false;
    }
    
    public void u() {
        this.N.d(this.G);
    }
    
    public void v() {
        this.N.a(this.G.x, this.G.y, this.G.width, this.G.height);
    }
    
    public void a(final Dimension dimension, final Dimension dimension2) {
    }
    
    public void a(final am am) {
        this.T = true;
    }
    
    public void b(final am am) {
        this.T = false;
    }
    
    public boolean w() {
        return false;
    }
    
    public Point e(final Point point) {
        final Point location = this.H.getLocation();
        location.translate(15, 15);
        return location;
    }
}
