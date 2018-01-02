// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;
import java.util.Stack;
import java.awt.Color;

public abstract class CompositeAnalysis implements q
{
    ChartBody b3;
    private String bV;
    int[] bY;
    int[] b1;
    int b0;
    float[] b2;
    float[] bX;
    int bW;
    Color bU;
    Study bZ;
    
    CompositeAnalysis(final Study bz, final ChartBody b3) {
        this.bW = 10;
        this.b3 = b3;
        this.bZ = bz;
        this.b2 = new float[1200];
    }
    
    boolean a(final Stack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        this.bU = stack.pop();
        return true;
    }
    
    void if(final Stack stack) {
        stack.push(this.bU);
    }
    
    public String g() {
        return this.bV;
    }
    
    void for(final String s) {
        this.bV = this.f().int() + " " + s;
    }
    
    abstract void try(final int p0);
    
    boolean byte(final int n) {
        return true;
    }
    
    abstract void j();
    
    void h() {
        final DataSource al = this.b3.al();
        final int b0 = al.l() - al.u() + 1;
        if (this.bY == null || this.bY.length < this.b2.length) {
            final int length = this.b2.length;
            this.bY = new int[length];
            this.b1 = new int[length];
        }
        for (int i = al.u(); i <= al.l(); ++i) {
            this.bY[i - al.u()] = Math.round((i - al.u()) * this.b3.fe);
            float n = this.b2[i];
            if (this.b3.e8) {
                n = (float)(Math.log(n) / ChartBody.fo);
            }
            this.b1[i - al.u()] = Math.round(this.b3.fq - this.b3.fi - (n - this.b3.e7) * this.b3.fc);
        }
        this.b0 = b0;
    }
    
    void do(final Graphics graphics) {
        final Color color = graphics.getColor();
        this.h();
        graphics.setColor(this.bU);
        final int bw = this.bW;
        int n;
        if (this.b3.al().u() >= bw) {
            n = 1;
        }
        else {
            n = bw - this.b3.al().u();
        }
        int n2 = this.bY[n - 1];
        int n3 = this.b1[n - 1];
        for (int i = n; i < this.b0; ++i) {
            final int n4 = this.bY[i];
            final int n5 = this.b1[i];
            graphics.drawLine(n2, n3, n4, n5);
            n2 = n4;
            n3 = n5;
        }
        graphics.setColor(color);
    }
    
    float i() {
        return this.b2[this.b3.al().l()];
    }
    
    public Study f() {
        return this.bZ;
    }
    
    public void a(final Object[] array) {
    }
    
    public float[] new(final int n) {
        switch (n) {
            case 0: {
                return this.b2;
            }
            case 1: {
                return this.bX;
            }
            default: {
                return null;
            }
        }
    }
    
    public int e() {
        return ((this.b2 != null) + (this.bX != null)) ? 1 : 0;
    }
}
