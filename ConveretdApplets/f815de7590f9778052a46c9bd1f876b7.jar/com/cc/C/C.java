// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.C;

import java.awt.Image;

public class C
{
    public static final int H = 8;
    private boolean _;
    private String f;
    private Image G;
    private boolean A;
    private String B;
    private Image L;
    private boolean O;
    private String e;
    private Image D;
    private boolean T;
    private String P;
    private Image Y;
    private boolean Z;
    private String I;
    private Image N;
    private boolean M;
    private String X;
    private Image c;
    private boolean C;
    private String K;
    private Image U;
    private boolean V;
    private String J;
    private Image Q;
    public static final int S = 7;
    public static final int F = 6;
    public static final int d = 5;
    public static final int R = 4;
    public static final int E = 3;
    public static final int W = 2;
    public static final int a = 1;
    public static final int b = 0;
    
    public int U() {
        return (this._ + this.A + this.O + this.T + this.Z + this.M + this.C + this.V) ? 1 : 0;
    }
    
    public void H(final String j) {
        this.V = true;
        this.J = j;
    }
    
    public void F(final String b) {
        this.A = true;
        this.B = b;
    }
    
    public void D(final String e) {
        this.O = true;
        this.e = e;
    }
    
    public void E(final String p) {
        this.T = true;
        this.P = p;
    }
    
    public void B(final String i) {
        this.Z = true;
        this.I = i;
    }
    
    public void C(final String f) {
        this._ = true;
        this.f = f;
    }
    
    public void G(final String x) {
        this.M = true;
        this.X = x;
    }
    
    public void A(final String k) {
        this.C = true;
        this.K = k;
    }
    
    public boolean S() {
        return this._;
    }
    
    public String M() {
        return this.f;
    }
    
    public boolean E() {
        return this.A;
    }
    
    public String K() {
        return this.B;
    }
    
    public boolean Y() {
        return this.O;
    }
    
    public String P() {
        return this.e;
    }
    
    public boolean V() {
        return this.T;
    }
    
    public String O() {
        return this.P;
    }
    
    public boolean J() {
        return this.Z;
    }
    
    public String F() {
        return this.I;
    }
    
    public boolean H() {
        return this.M;
    }
    
    public String T() {
        return this.X;
    }
    
    public boolean Q() {
        return this.C;
    }
    
    public String N() {
        return this.K;
    }
    
    public Image X() {
        return this.G;
    }
    
    public Image I() {
        return this.L;
    }
    
    public Image L() {
        return this.D;
    }
    
    public Image W() {
        return this.Y;
    }
    
    public Image Z() {
        return this.N;
    }
    
    public Image A() {
        return this.c;
    }
    
    public Image D() {
        return this.U;
    }
    
    public void B(final Image g) {
        this.G = g;
    }
    
    public void H(final Image l) {
        this.L = l;
    }
    
    public void F(final Image d) {
        this.D = d;
    }
    
    public void E(final Image y) {
        this.Y = y;
    }
    
    public void C(final Image n) {
        this.N = n;
    }
    
    public void D(final Image c) {
        this.c = c;
    }
    
    public void A(final Image u) {
        this.U = u;
    }
    
    public Image C() {
        return this.Q;
    }
    
    public void G(final Image q) {
        this.Q = q;
    }
    
    public boolean G() {
        return this.V;
    }
    
    public String B() {
        return this.J;
    }
    
    public void R() {
        this.C = false;
    }
}
