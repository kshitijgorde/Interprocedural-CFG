// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;
import java.awt.Color;
import java.util.StringTokenizer;
import java.util.Stack;
import java.util.Vector;

public class ae
{
    static final int if = 5;
    Vector do;
    Stack a;
    
    ae() {
        this.do = new Vector();
        this.a = new Stack();
    }
    
    void if(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        while (stringTokenizer.hasMoreTokens()) {
            this.a.push(AppletChart.try(stringTokenizer.nextToken()));
        }
    }
    
    boolean a(final MovAvg movAvg) {
        if (this.do.size() < 5) {
            movAvg.bU = this.a.pop();
            this.do.addElement(movAvg);
            return true;
        }
        return false;
    }
    
    void a(final int n) {
        this.a.push(this.do.elementAt(n).bU);
        this.do.removeElementAt(n);
    }
    
    void do(final String s) {
        final int a;
        if ((a = this.a(s)) < 0) {
            return;
        }
        this.a(a);
    }
    
    int a(final String s) {
        for (int i = 0; i < this.do.size(); ++i) {
            if (((MovAvg)this.do.elementAt(i)).g().compareTo(s) == 0) {
                return i;
            }
        }
        return -1;
    }
    
    void a(final Graphics graphics) {
        for (int i = 0; i < this.do.size(); ++i) {
            ((MovAvg)this.do.elementAt(i)).do(graphics);
        }
    }
    
    void a() {
        for (int i = 0; i < this.do.size(); ++i) {
            ((MovAvg)this.do.elementAt(i)).j();
        }
    }
    
    MovAvg if(final int n) {
        return this.do.elementAt(n);
    }
    
    int if() {
        return this.do.size();
    }
}
