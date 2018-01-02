// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Point;
import java.util.Vector;
import java.awt.Color;

public class y
{
    static final int a = 5;
    int for;
    Color[] if;
    float try;
    float do;
    Vector int;
    be byte;
    boolean case;
    boolean new;
    
    public y(final be byte1, final boolean case1, final boolean new1) {
        this.for = 0;
        this.if = new Color[] { Color.black, new Color(0, 102, 0), Color.blue, Color.red, Color.cyan };
        this.case = false;
        this.new = false;
        this.byte = byte1;
        this.int = new Vector(5);
        this.case = case1;
        this.new = new1;
    }
    
    public int a(final ChartBody chartBody) {
        int n = 0;
        if (this.int.size() >= 5) {
            this.int.removeElementAt(0);
            n = -1;
        }
        this.int.addElement(chartBody);
        chartBody.if(this.case, this.new);
        chartBody.a(this.byte.case().left, this.byte.case().right, this.byte.case().top, this.byte.case().bottom, this.byte.byte().bottom, this.byte.byte().top);
        chartBody.do(this.byte.b(), this.byte.char());
        chartBody.new(this.byte.bE);
        chartBody.if(this.byte.bK);
        chartBody.a(this.if[this.int.size() - 1]);
        this.byte.bD.a(this.int());
        if (this.int.size() == 1) {
            chartBody.al().b2 = true;
        }
        return n;
    }
    
    public void for(final int n) {
        this.int.removeElementAt(n);
    }
    
    public boolean case() {
        int n = 1;
        for (int i = 0; i < this.int.size(); ++i) {
            if (this.int(i).fn) {
                n *= this.int(i).ad();
            }
        }
        return n > 0;
    }
    
    public void a(final ChartBody chartBody, final int n) {
        final ChartBody int1 = this.int(n);
        final Color color = (int1 != null) ? int1.ft : this.if[n];
        this.int.setElementAt(chartBody, n);
        chartBody.a(this.byte.case().left, this.byte.case().right, this.byte.case().top, this.byte.case().bottom, this.byte.byte().bottom, this.byte.byte().top);
        chartBody.do(this.byte.b(), this.byte.char());
        chartBody.if(this.case, this.new);
        chartBody.new(this.byte.bE);
        chartBody.if(this.byte.bK);
        if (int1 != null) {
            chartBody.fn = int1.fn;
        }
        chartBody.a(color);
    }
    
    public void a(final int n, final int n2) {
        for (int i = 0; i < this.int.size(); ++i) {
            this.int(i).do(n, n2);
        }
    }
    
    public int new() {
        return this.int.size();
    }
    
    public ChartBody int(final int n) {
        if (n < this.int.size()) {
            return this.int.elementAt(n);
        }
        return null;
    }
    
    int a(final int n, final Point point, final int n2) {
        return this.int(this.for).a(n, point, n2);
    }
    
    int if(final int n) {
        return this.int(this.for).q(n);
    }
    
    float if(final int n, final int n2) {
        return this.int(this.for).if(n, n2);
    }
    
    float byte(final int n) {
        return this.int(this.for).p(n);
    }
    
    float a(final int n) {
        return this.int(this.for).s(n);
    }
    
    int try(final int n) {
        return this.int(this.for).u(n);
    }
    
    public bk if() {
        return this.int(this.for).ag();
    }
    
    public void do(final int for1) {
        final ChartBody int1 = this.int(for1);
        final ChartBody int2 = this.int(this.for);
        final Color ft = int1.ft;
        int1.ft = int2.ft;
        int2.ft = ft;
        int1.al().b2 = true;
        int2.al().b2 = false;
        this.for = for1;
        this.byte.bD.a(int1);
        this.byte.parent.eR.a(int1.al());
    }
    
    void a(final int n, final boolean fn) {
        this.int(n).fn = fn;
    }
    
    void new(final int n) {
        this.int(n).fn = !this.int(n).fn;
    }
    
    public int do() {
        return this.for;
    }
    
    public ChartBody int() {
        return this.int(this.for);
    }
    
    void char() {
        this.int.removeAllElements();
    }
    
    public void else() {
        for (int i = 0; i < this.int.size(); ++i) {
            this.int(i).al().q();
        }
    }
    
    public void for() {
        for (int i = 0; i < this.int.size(); ++i) {
            this.int(i).al().z();
        }
    }
    
    public void do(final int n, final int n2) {
        for (int i = 0; i < this.int.size(); ++i) {
            this.int(i).al().y().a(n, n2);
        }
    }
    
    public void long() {
        for (int i = 0; i < this.int.size(); ++i) {
            this.int(i).al().y().try();
        }
    }
    
    public void void() {
        for (int i = 0; i < this.int.size(); ++i) {
            this.int(i).al().y().int();
        }
    }
    
    int byte() {
        return this.int().al().u();
    }
    
    int null() {
        return this.int().al().l();
    }
    
    float try() {
        this.try = this.int(this.for).ab();
        if (this.case || this.new) {
            for (int i = 0; i < this.int.size(); ++i) {
                if (this.int(i).fn && this.try > this.int(i).ab()) {
                    this.try = this.int(i).ab();
                }
            }
        }
        return this.try;
    }
    
    float a() {
        this.do = this.int(this.for).ah();
        if (this.case || this.new) {
            for (int i = 0; i < this.int.size(); ++i) {
                if (this.int(i).fn && this.do < this.int(i).ah()) {
                    this.do = this.int(i).ah();
                }
            }
        }
        return this.do;
    }
    
    float goto() {
        return this.int(this.for).am();
    }
}
