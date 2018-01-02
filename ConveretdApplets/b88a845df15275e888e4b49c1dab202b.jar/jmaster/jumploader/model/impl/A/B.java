// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.jumploader.model.impl.A;

import javax.swing.JLabel;
import java.awt.Component;

public class B
{
    public static final String C = "fileName";
    public static final String D = "text";
    public static final String E = "textarea";
    public static final String B = "checkbox";
    protected String H;
    protected String A;
    protected String J;
    protected String K;
    protected Long L;
    protected Component I;
    protected JLabel F;
    protected Boolean G;
    
    public B() {
        this.H = "text";
        this.L = new Long(4L);
    }
    
    public String D() {
        return this.H;
    }
    
    public void B(final String h) {
        this.H = h;
    }
    
    public String G() {
        return this.A;
    }
    
    public void A(final String a) {
        this.A = a;
    }
    
    public String F() {
        return this.J;
    }
    
    public void C(final String j) {
        this.J = j;
    }
    
    public String I() {
        return this.K;
    }
    
    public void D(final String k) {
        this.K = k;
    }
    
    public Long E() {
        return this.L;
    }
    
    public void A(final Long l) {
        this.L = l;
    }
    
    public Component A() {
        return this.I;
    }
    
    public void A(final Component i) {
        this.I = i;
    }
    
    public Boolean B() {
        return this.G;
    }
    
    public void A(final Boolean g) {
        this.G = g;
    }
    
    public JLabel C() {
        return this.F;
    }
    
    public void A(final JLabel f) {
        this.F = f;
    }
    
    public boolean H() {
        return this.G != null && this.G;
    }
}
