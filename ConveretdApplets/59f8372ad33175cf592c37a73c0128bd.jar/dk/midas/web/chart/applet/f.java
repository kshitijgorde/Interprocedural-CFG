// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import dk.midas.web.chart.applet.a.c;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class f extends p
{
    private static final int K = 1;
    private static final int q = 30;
    private static final int F = 60;
    private static final int J = 120;
    private static final int u = 300;
    private static final int G = 600;
    private static final int A = 900;
    private static final int L = 1800;
    private static final int y = 3600;
    private static final int p = 7200;
    private static final int w = 14400;
    private static final int v = 28800;
    private static final int C = 86400;
    private static final int t = 604800;
    private static final int z = 2592000;
    private static final int B = 7776000;
    private static final int H = 15811200;
    private static final int x = 31536000;
    private static final int o = 82944000;
    private static final b[] E;
    private static final int D = 5;
    private static final int I = 15;
    private be s;
    private SimpleDateFormat r;
    
    f(final be s, final int n, final int n2) {
        super(s, n + 30, n2);
        this.s = s;
        super.for = s.case().left - 15;
        super.a = s.case().top + s.char() + 1;
        (this.r = (SimpleDateFormat)DateFormat.getDateTimeInstance(1, 1)).setCalendar(Calendar.getInstance());
    }
    
    void a(final int n, final int n2) {
        super.a(n + 30, n2);
        super.for = this.s.case().left - 15;
        super.a = this.s.case().top + this.s.char() + 1;
    }
    
    private b a(final Date date) {
        long if1;
        int i;
        for (if1 = this.s.dO.int().al().C().if(), i = 0; i < f.E.length && if1 > f.E[i].if; ++i) {}
        while (i < f.E.length) {
            final int n = (int)(f.E[i].if / if1 * this.s.bJ);
            this.r.applyPattern(f.E[i].do);
            if (n > dk.midas.web.chart.applet.p.char.stringWidth(this.r.format(date)) + 5) {
                return f.E[i];
            }
            ++i;
        }
        return null;
    }
    
    void new() {
        boolean b = true;
        final DataSource al = this.s.dO.int().al();
        final a a = new a();
        final a a2 = new a();
        final a a3 = new a();
        a2.for = -15;
        a3.for = -5;
        final int[] fp = this.s.dO.int().fp;
        if (fp == null) {
            return;
        }
        a.a(al.new(al.u()), fp[0]);
        final b a4 = this.a(a.byte());
        if (a4 == null) {
            return;
        }
        super.int.setColor(super.try.gY);
        super.int.fillRect(0, 0, super.new, super.if);
        super.int.setColor(super.try.g2);
        final a a5 = new a();
        for (int i = al.u() + 1; i < al.l(); ++i) {
            a5.a(al.new(i), fp[i - al.u()]);
            if (a4.a(a, a5)) {
                b = this.a(b, a4, a, a2, a3, a5);
            }
            a.a(a5);
        }
        if (al.y().a()) {
            final int n = fp[1] - fp[0];
            final double n2 = al.new(al.u() + 1) - al.new(al.u());
            for (int j = 0; j < this.s.parent.O(); ++j) {
                a5.a(a.do + n2, a.a + n);
                if (a4.a(a, a5)) {
                    b = this.a(b, a4, a, a2, a3, a5);
                }
                a.a(a5);
            }
        }
        if (b) {
            this.r.applyPattern(a4.a);
            final String format = this.r.format(a.byte());
            super.int.setColor(super.try.g2);
            super.int.drawString(format, (super.new - dk.midas.web.chart.applet.p.char.stringWidth(format)) / 2, 2 * dk.midas.web.chart.applet.p.char.getHeight() + 5);
        }
        this.a(false);
    }
    
    private boolean a(boolean b, final b b2, final a a, final a a2, final a a3, final a a4) {
        a4.a(b2, a);
        this.r.applyPattern(b2.do);
        final String format = this.r.format(a4.byte());
        final int stringWidth = dk.midas.web.chart.applet.p.char.stringWidth(format);
        if (a4.for - a2.for > stringWidth + 5) {
            super.int.setColor(super.try.gy);
            super.int.drawLine(a4.for, 0, a4.for, 4);
            super.int.setColor(super.try.g2);
            super.int.drawString(format, a4.for - stringWidth / 2, dk.midas.web.chart.applet.p.char.getHeight() + 5);
            String access$400 = b2.if(a2, a4);
            if (access$400 != null) {
                if (b) {
                    access$400 = "MM/dd/yy";
                }
                this.r.applyPattern(access$400);
                final String format2 = this.r.format(a4.byte());
                final int stringWidth2 = dk.midas.web.chart.applet.p.char.stringWidth(format2);
                if (a4.for - -a3.for > stringWidth2 + 5) {
                    super.int.drawString(format2, a4.for - stringWidth2 / 2, 2 * dk.midas.web.chart.applet.p.char.getHeight() + 5);
                    a3.a(a4);
                    b = false;
                }
            }
            a2.a(a4);
        }
        return b;
    }
    
    void try() {
        int n = 1;
        boolean b = false;
        int n2 = 15;
        int n3 = -10;
        final DataSource al = this.s.dO.int().al();
        final int[] fp = this.s.dO.int().fp;
        final int u = al.u();
        final int l = al.l();
        final Date date = new Date(0L);
        final b int1 = this.int();
        if (int1 == null) {
            return;
        }
        super.int.setColor(super.try.gY);
        super.int.fillRect(0, 0, super.new, super.if);
        super.int.setColor(super.try.g2);
        int n4 = 10000;
        int i = u;
        while (i <= l) {
            final int n5 = 15 + fp[i - u];
            super.int.setColor(super.try.gy);
            super.int.drawLine(n5, 0, n5, 4);
            super.int.setColor(super.try.g2);
            final double new1 = al.new(i);
            date.setTime((long)(new1 * 1000.0));
            this.r.applyPattern(int1.do);
            final String format = this.r.format(date);
            if (n5 - dk.midas.web.chart.applet.p.char.stringWidth(format) / 2 - 5 > n3) {
                n3 = n5 + dk.midas.web.chart.applet.p.char.stringWidth(format) / 2;
                super.int.drawString(format, n5 - dk.midas.web.chart.applet.p.char.stringWidth(format) / 2, dk.midas.web.chart.applet.p.char.getHeight() + 5);
                this.r.applyPattern(int1.do);
                final int int2 = Integer.parseInt(this.r.format(date));
                if (int2 != n4) {
                    n4 = int2;
                    if (n != 0) {
                        n = 0;
                    }
                    else {
                        b = true;
                        this.r.applyPattern(int1.do);
                        final String format2 = this.r.format(date);
                        if (n5 - dk.midas.web.chart.applet.p.char.stringWidth(format2) / 2 - 10 > n2) {
                            b = true;
                            super.int.drawString(format2, n5 - dk.midas.web.chart.applet.p.char.stringWidth(format2) / 2, 2 * dk.midas.web.chart.applet.p.char.getHeight() + 5);
                            n2 = n5 + dk.midas.web.chart.applet.p.char.stringWidth(format2) / 2;
                        }
                    }
                }
            }
            ++i;
            while (i <= l && al.new(i) - new1 <= int1.if) {
                ++i;
            }
        }
        if (al.y().a()) {
            final int n6 = fp[1] - fp[0];
            final double n7 = al.new(al.u() + 1) - al.new(al.u());
            double new2 = al.new(l);
            int n8 = fp[al.y().new() - 1];
            for (int j = 0; j < this.s.parent.O(); ++j, ++j) {
                final int n9 = 15 + n8;
                new2 += n7;
                n8 += n6;
                super.int.setColor(super.try.gy);
                super.int.drawLine(n9, 0, n9, 4);
                super.int.setColor(super.try.g2);
                date.setTime((long)(new2 * 1000.0));
                this.r.applyPattern(int1.do);
                final String format3 = this.r.format(date);
                if (n9 - dk.midas.web.chart.applet.p.char.stringWidth(format3) / 2 - 5 > n3) {
                    n3 = n9 + dk.midas.web.chart.applet.p.char.stringWidth(format3) / 2;
                    super.int.drawString(format3, n9 - dk.midas.web.chart.applet.p.char.stringWidth(format3) / 2, dk.midas.web.chart.applet.p.char.getHeight() + 5);
                    this.r.applyPattern(int1.do);
                    final int int3 = Integer.parseInt(this.r.format(date));
                    if (int3 != n4) {
                        n4 = int3;
                        if (n != 0) {
                            n = 0;
                        }
                        else {
                            b = true;
                            this.r.applyPattern(int1.do);
                            final String format4 = this.r.format(date);
                            if (n9 - dk.midas.web.chart.applet.p.char.stringWidth(format4) / 2 - 10 > n2) {
                                b = true;
                                super.int.drawString(format4, n9 - dk.midas.web.chart.applet.p.char.stringWidth(format4) / 2, 2 * dk.midas.web.chart.applet.p.char.getHeight() + 5);
                                n2 = n9 + dk.midas.web.chart.applet.p.char.stringWidth(format4) / 2;
                            }
                        }
                    }
                }
            }
        }
        if (!b) {
            this.r.applyPattern(int1.a);
            final String format5 = this.r.format(date);
            super.int.setColor(super.try.g2);
            super.int.drawString(format5, (super.new - dk.midas.web.chart.applet.p.char.stringWidth(format5)) / 2, 2 * dk.midas.web.chart.applet.p.char.getHeight() + 5);
        }
        this.a(false);
    }
    
    private b int() {
        final DataSource al = this.s.dO.int().al();
        final int[] fp = this.s.dO.int().fp;
        final int u = al.u();
        final int l = al.l();
        final double new1 = al.new(u);
        final Date date = new Date(0L);
        int n = 0;
        date.setTime((long)(new1 * 1000.0));
        this.r.applyPattern(f.E[n].do);
        String s = this.r.format(date);
        for (int n2 = u + 1; n2 < l && n < f.E.length; ++n2) {
            final double new2 = al.new(n2);
            if (new2 - new1 > f.E[n].if) {
                date.setTime((long)(new2 * 1000.0));
                this.r.applyPattern(f.E[n].do);
                if ((dk.midas.web.chart.applet.p.char.stringWidth(s) + dk.midas.web.chart.applet.p.char.stringWidth(this.r.format(date))) / 2 + 5 < fp[n2 - u] - fp[0]) {
                    return f.E[n];
                }
                ++n;
                date.setTime((long)(new1 * 1000.0));
                this.r.applyPattern(f.E[n].do);
                s = this.r.format(date);
            }
        }
        return null;
    }
    
    public void if(final Graphics graphics) {
        final DataSource al = this.s.dO.int().al();
        if (super.case == null) {
            super.case = this.s.createImage(super.new, super.if);
            (super.int = super.case.getGraphics()).setFont(super.byte);
            dk.midas.web.chart.applet.p.char = super.int.getFontMetrics();
        }
        if (this.a()) {
            if (!al.C().do()) {
                this.new();
            }
            else {
                this.try();
            }
        }
        graphics.drawImage(super.case, super.for, super.a, this.s);
    }
    
    static {
        E = new b[] { new b(1, "ss's'", "MM/dd/yy H'h':mm'm'", "mm"), new b(30, "ss's'", "MM/dd/yy H'h':mm'm'", "mm"), new b(60, "HH:mm", "MM/dd/yy", "HH"), new b(300, "HH:mm", "MM/dd/yy", "HH"), new b(600, "HH:mm", "MM/dd/yy", "HH"), new b(900, "HH:mm", "MM/dd/yy", "HH"), new b(1800, "HH:mm", "MM/dd/yy", "HH"), new b(3600, "HH:mm", "MM/dd/yy", "d"), new b(7200, "HH:mm", "MM/dd/yy", "d"), new b(14400, "HH:mm", "MM/dd/yy", "d"), new b(28800, "HH:mm", "MM/dd/yy", "d"), new b(86400, "dd", "MMM/yyyy", "M"), new b(604800, "dd", "MMM/yyyy", "M"), new b(2592000, "MM", "yyyy", "y"), new b(7776000, "MM", "yyyy", "y"), new b(15811200, "MM", "yyyy", "y"), new b(31536000, "MM", "yyyy", "y"), new b(82944000, "MM", "yyyy", "y") };
    }
    
    private static class b
    {
        public int if;
        public String do;
        public String a;
        public String for;
        
        public b(final int if1, final String do1, final String a, final String for1) {
            this.if = if1;
            this.do = do1;
            this.a = a;
            this.for = for1;
        }
        
        private boolean a(final a a, final a a2) {
            if (this.if < 60) {
                return false;
            }
            if (this.if < 86400) {
                return (long)a2.try() / this.if != (long)a.try() / this.if;
            }
            switch (this.if) {
                case 86400: {
                    return a.case() != a2.case();
                }
                case 604800: {
                    return (a.new() != a2.new() || a.for() != a2.for()) && a2.a() <= a2.case();
                }
                case 2592000: {
                    return a.new() != a2.new();
                }
                case 7776000: {
                    return a.do() != a2.do() || !c.a(a2.new(), a.new(), 3);
                }
                case 15811200: {
                    return a.do() != a2.do() || !c.a(a2.new(), a.new(), 6);
                }
                case 31536000: {
                    return a.do() != a2.do();
                }
                case 82944000: {
                    return !c.a(a2.do(), a.do(), 2);
                }
                default: {
                    return false;
                }
            }
        }
        
        private String if(final a a, final a a2) {
            String s = null;
            if (this.if < 86400) {
                if (a2.do() != a.do()) {
                    s = "MM/dd/yy";
                }
                else if (a2.new() != a.new()) {
                    s = "MMM/yy";
                }
                else if (a2.case() != a.case()) {
                    s = "dd";
                }
            }
            else if (this.if < 2592000) {
                if (a2.do() != a.do()) {
                    s = "MM/dd/yy";
                }
                else if (a2.new() != a.new()) {
                    s = "MMM/yy";
                }
            }
            else if (a2.do() != a.do()) {
                s = "yyyy";
            }
            return s;
        }
    }
    
    private static class a
    {
        private int a;
        private double do;
        private int for;
        private Calendar if;
        
        public a() {
            this.if = Calendar.getInstance();
        }
        
        public void a(final a a) {
            this.a = a.a;
            this.do = a.try();
            this.for = a.for;
            this.if.setTime(a.if.getTime());
        }
        
        public void a(final double do1, final int a) {
            this.do = do1;
            this.a = a;
            this.if.setTime(new Date((long)(do1 * 1000.0)));
        }
        
        public int do() {
            return this.if.get(1);
        }
        
        public int new() {
            return this.if.get(2);
        }
        
        public int for() {
            return this.if.get(3);
        }
        
        public int a() {
            return this.if.get(7);
        }
        
        public int case() {
            return this.if.get(5);
        }
        
        public int int() {
            return this.if.get(11);
        }
        
        public int if() {
            return this.if.get(12);
        }
        
        public Date byte() {
            return this.if.getTime();
        }
        
        public double try() {
            return this.do;
        }
        
        public void a(final b b, final a a) {
            this.a(b);
            this.for = 15 + a.a + (int)((this.if.getTime().getTime() / 1000L - a.try()) * (this.a - a.a) / (this.try() - a.try()));
        }
        
        private void a(final b b) {
            if (b.if <= 86400) {
                this.if.setTime(new Date((long)(c.a((long)this.try(), b.if) * 1000.0)));
            }
            else {
                switch (b.if) {
                    case 604800: {
                        this.if.set(5, this.case() - this.a() + 1);
                        break;
                    }
                    case 2592000: {
                        this.if.set(5, 1);
                        break;
                    }
                    case 7776000: {
                        this.if.set(5, 1);
                        this.if.set(2, c.a(this.new(), 3));
                        break;
                    }
                    case 15811200: {
                        this.if.set(5, 1);
                        this.if.set(2, c.a(this.new(), 6));
                        break;
                    }
                    case 31536000: {
                        this.if.set(5, 1);
                        this.if.set(2, 0);
                        break;
                    }
                    case 82944000: {
                        this.if.set(5, 1);
                        this.if.set(2, 0);
                        this.if.set(1, c.a(this.do(), 2));
                        break;
                    }
                }
            }
        }
    }
}
