// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Graphics;
import java.util.StringTokenizer;
import java.util.Stack;
import java.util.Vector;

public class ap
{
    static final int if = 5;
    Vector do;
    Stack a;
    
    ap() {
        this.do = new Vector();
        this.a = new Stack();
    }
    
    void do(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        while (stringTokenizer.hasMoreTokens()) {
            this.a.push(AppletChart.try(stringTokenizer.nextToken()));
        }
    }
    
    boolean a(final CompositeAnalysis compositeAnalysis) {
        if (this.do.size() >= 5) {
            return false;
        }
        if (!compositeAnalysis.a(this.a)) {
            return false;
        }
        this.do.addElement(compositeAnalysis);
        return true;
    }
    
    void a(final int n) {
        this.do.elementAt(n).if(this.a);
        this.do.removeElementAt(n);
    }
    
    void if(final String s) {
        final int a;
        if ((a = this.a(s)) < 0) {
            return;
        }
        this.a(a);
    }
    
    int a(final String s) {
        for (int i = 0; i < this.do.size(); ++i) {
            if (((CompositeAnalysis)this.do.elementAt(i)).g().compareTo(s) == 0) {
                return i;
            }
        }
        return -1;
    }
    
    void a(final Graphics graphics) {
        for (int i = 0; i < this.do.size(); ++i) {
            ((CompositeAnalysis)this.do.elementAt(i)).do(graphics);
        }
    }
    
    void a() {
        for (int i = 0; i < this.do.size(); ++i) {
            ((CompositeAnalysis)this.do.elementAt(i)).j();
        }
    }
    
    void a(final ChartBody b3) {
        for (int i = 0; i < this.do.size(); ++i) {
            ((CompositeAnalysis)this.do.elementAt(i)).b3 = b3;
        }
    }
    
    CompositeAnalysis if(final int n) {
        return this.do.elementAt(n);
    }
    
    int if() {
        return this.do.size();
    }
}
