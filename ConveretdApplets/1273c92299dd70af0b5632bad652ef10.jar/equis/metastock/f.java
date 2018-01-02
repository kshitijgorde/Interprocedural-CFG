// 
// Decompiled by Procyon v0.5.30
// 

package equis.metastock;

public class f
{
    private g a;
    private MS4Java b;
    
    public f(final MS4Java b) {
        this.b = b;
        this.a = new g(550);
    }
    
    public String a() {
        return this.a.a;
    }
    
    private void a(final String s, final char c) {
        if (s.equals(this.a.a) && c == this.a.f()) {
            return;
        }
        final int u = MS4Java.u();
        try {
            final String text = MS4Java.f.f.getText();
            this.a.a(s, c);
            MS4Java.f.b(text);
        }
        catch (Exception ex) {
            MS4Java.a("Exception getting index prices in CalcComparativeRelativeStrength()");
        }
        this.b.SetPriceStyle(u);
    }
    
    private void a(final g g, final h h) {
        for (int i = 0; i <= g.k.b; ++i) {
            final double n = g.i.c[i];
            final double n2 = g.j.c[i];
            final double n3 = g.k.c[i];
            final double n4 = n3 - n2 - (n - n3);
            double n5;
            if (n != n2) {
                n5 = n4 / (n - n2) * g.l.c[i];
            }
            else {
                n5 = 0.0;
            }
            h.c[i] = n5;
            if (i > 0) {
                final double[] c = h.c;
                final int n6 = i;
                c[n6] += h.c[i - 1];
            }
        }
        h.a = g.k.a;
        h.b = g.k.b;
    }
    
    private void a(final g g, final h h, final h h2, final int n, final double n2) {
        final h h3 = new h(550);
        final h h4 = new h(550);
        this.a(g.k, h3, n, 83);
        if (n < 1) {
            return;
        }
        this.a(h4, n, false, g.k);
        for (int i = n; i <= g.k.b; ++i) {
            h.c[i] = h3.c[i] + h4.c[i] * n2;
            h2.c[i] = h3.c[i] - h4.c[i] * n2;
        }
        h2.a = n;
        h.a = n;
        final int b = g.k.b;
        h2.b = b;
        h.b = b;
    }
    
    private double a(final g g, final int n) {
        return (g.i.c[n] + g.j.c[n] + g.k.c[n]) / 3.0;
    }
    
    private void a(final g g, final h h, final int a) {
        final h h2 = new h(550);
        final h h3 = new h(550);
        double n = 0.0;
        h2.c[0] = 0.0;
        h3.c[0] = 0.0;
        for (int i = 0; i <= g.k.b; ++i) {
            if (i < a - 1) {
                n += this.a(g, i);
                h2.c[i] = 0.0;
            }
            else {
                if (i == a - 1) {
                    n += this.a(g, i);
                }
                else {
                    n = n + this.a(g, i) - this.a(g, i - a);
                }
                h2.c[i] = n / a;
                h3.c[i] = 0.0;
                for (int j = 0; j < a; ++j) {
                    if (i - j < 0) {
                        final double[] c = h3.c;
                        final int n2 = i;
                        c[n2] += Math.abs(0.0 - h2.c[i]);
                    }
                    else {
                        final double[] c2 = h3.c;
                        final int n3 = i;
                        c2[n3] += Math.abs(this.a(g, i - j) - h2.c[i]);
                    }
                }
                final double[] c3 = h3.c;
                final int n4 = i;
                c3[n4] /= a;
            }
            final double n5 = 0.015 * h3.c[i];
            if (n5 != 0.0) {
                h.c[i] = (this.a(g, i) - h2.c[i]) / n5;
            }
            else {
                h.c[i] = 0.0;
            }
        }
        h.a = a;
        h.b = g.k.b;
    }
    
    private void b(final g g, final h h) {
        final h h2 = new h(550);
        final h h3 = new h(550);
        final h h4 = new h(550);
        this.a(g, h2);
        this.a(h2, h3, 3, 69);
        this.a(h2, h4, 10, 69);
        for (int i = g.k.a; i <= g.k.b; ++i) {
            h.c[i] = h3.c[i] - h4.c[i];
        }
        h.a = h4.a;
        h.b = g.k.b;
    }
    
    private void b(final g g, final h h, final h h2, final int n, final double n2) {
        this.a(g.k, h, n, 83);
        for (int i = g.k.a; i <= g.k.b; ++i) {
            h2.c[i] = h.c[i] * (1.0 - n2);
            h.c[i] *= 1.0 + n2;
        }
        final int n3 = n - 1;
        h.a = n3;
        h2.a = n3;
        final int b = g.k.b;
        h.b = b;
        h2.b = b;
    }
    
    private void c(final g g, final h h) {
        final double n = 0.15;
        final double n2 = 0.075;
        if (g.k.b + 1 < 25) {
            return;
        }
        double n3 = g.k.c[0];
        double n4 = g.k.c[0];
        for (int i = 0; i <= g.k.b; ++i) {
            n3 = g.k.c[i] * n + n3 * (1.0 - n);
            n4 = g.k.c[i] * n2 + n4 * (1.0 - n2);
            h.c[i] = n3 - n4;
        }
        h.f = 1;
        h.a = 25;
        h.b = g.k.b;
    }
    
    private void d(final g g, final h h) {
        for (int i = 0; i <= g.k.b; ++i) {
            h.c[i] = (g.i.c[i] + g.j.c[i]) / 2.0;
        }
        h.a = 0;
        h.b = g.k.b;
    }
    
    private void b(final g g, final h h, final int a) {
        if (g.k.b < a) {
            return;
        }
        for (int i = a; i <= g.k.b; ++i) {
            if (g.k.c[i - a] != 0.0) {
                h.c[i] = g.k.c[i] / g.k.c[i - a] * 100.0;
            }
            else {
                h.c[i] = 0.0;
            }
        }
        h.a = a;
        h.b = g.k.b;
    }
    
    private void a(final h h, final h h2, final int n, final int n2) {
        double n3 = 0.0;
        if (h.b - h.a < n + 1) {
            return;
        }
        if (n2 == 83) {
            for (int i = h.a; i <= h.b; ++i) {
                if (i < h.a + n) {
                    n3 += h.c[i];
                }
                else {
                    n3 += h.c[i] - h.c[i - n];
                }
                h2.c[i] = n3 / n;
            }
        }
        else {
            if (n2 != 69) {
                MS4Java.a("Invalid moving average type in CalcMovingAverage!");
                return;
            }
            final double n4 = 2.0 / (n + 1);
            final double n5 = 1.0 - n4;
            for (int j = h.a; j <= h.b; ++j) {
                if (j <= h.a) {
                    h2.c[j] = h.c[j] * n4 + h.c[h.a] * n5;
                }
                else {
                    h2.c[j] = h.c[j] * n4 + h2.c[j - 1] * n5;
                }
            }
        }
        h2.a = h.a + n - 1;
        h2.b = h.b;
    }
    
    private void e(final g g, final h h) {
        h.c[0] = 0.0;
        for (int i = 1; i <= g.k.b; ++i) {
            if (g.k.c[i] > g.k.c[i - 1]) {
                h.c[i] = h.c[i - 1] + g.l.c[i];
            }
            else if (g.k.c[i] < g.k.c[i - 1]) {
                h.c[i] = h.c[i - 1] - g.l.c[i];
            }
            else {
                h.c[i] = h.c[i - 1];
            }
        }
        h.a = 1;
        h.b = g.k.b;
    }
    
    private void a(final g g, final h h, final h h2, final int n, final int n2) {
        final h h3 = new h(550);
        final h h4 = new h(550);
        final int n3 = 37;
        final double n4 = g.k.b - 1;
        if (n > n4 || n2 > n4) {
            return;
        }
        this.a(h2, h3, n, 83);
        this.a(h2, h4, n2, 83);
        for (int i = 0; i <= g.k.b; ++i) {
            switch (n3) {
                default: {
                    h.c[i] = h3.c[i] - h4.c[i];
                    break;
                }
                case 37: {
                    if (h4.c[i] != 0.0) {
                        h.c[i] = (h3.c[i] - h4.c[i]) / h4.c[i] * 100.0;
                        break;
                    }
                    h.c[i] = 0.0;
                    break;
                }
            }
        }
        h.a = n2 - 1;
        h.b = g.k.b;
    }
    
    private void f(final g g, final h h) {
        if (g.k.c[g.k.a] != 0.0) {
            for (int i = g.k.a; i <= g.k.b; ++i) {
                h.c[i] = (g.k.c[i] - g.k.c[g.k.a]) / g.k.c[g.k.a] * 100.0;
            }
        }
        h.a = g.k.a;
        h.b = g.k.b;
    }
    
    private void g(final g g, final h h) {
        h.c[g.k.a] = 0.0;
        for (int i = g.k.a + 1; i <= g.k.b; ++i) {
            double n;
            if (g.k.c[i - 1] != 0.0) {
                n = (g.k.c[i] - g.k.c[i - 1]) / g.k.c[i - 1];
            }
            else {
                n = 0.0;
            }
            h.c[i] = h.c[i - 1] + n * g.l.c[i];
        }
        h.a = g.k.a + 1;
        h.b = g.k.b;
    }
    
    private void a(final h h, final h h2, final int a, final char c) {
        for (int i = h.a + a; i <= h.b; ++i) {
            h2.c[i] = h.c[i] - h.c[i - a];
            if (c == '%') {
                if (h.c[i - a] != 0.0) {
                    final double[] c2 = h2.c;
                    final int n = i;
                    c2[n] /= h.c[i - a] / 100.0;
                }
                else {
                    h2.c[i] = 0.0;
                }
            }
        }
        h2.a = a;
        h2.b = h.b;
    }
    
    private void a(final h h, final h h2, final int n) {
        if (n < 2) {
            return;
        }
        double n3;
        double n2 = n3 = 0.0;
        for (int i = 1; i <= h.b; ++i) {
            final double n5;
            double n4 = n5 = h.c[i] - h.c[i - 1];
            if (n4 < 0.0) {
                n4 = 0.0;
            }
            double abs;
            if (n5 > 0.0) {
                abs = 0.0;
            }
            else {
                abs = Math.abs(n5);
            }
            if (i <= n) {
                n3 += n4;
                n2 += abs;
                if (i == n) {
                    n3 /= n;
                    n2 /= n;
                }
            }
            else {
                n3 = (n3 * (n - 1) + n4) / n;
                n2 = (n2 * (n - 1) + abs) / n;
            }
            if (n2 != 0.0) {
                h2.c[i] = n3 / n2;
                ++h2.c[i];
            }
            else {
                h2.c[i] = 0.0;
            }
            if (h2.c[i] != 0.0) {
                h2.c[i] = 100.0 / h2.c[i];
            }
            h2.c[i] = 100.0 - h2.c[i];
        }
        h2.a = n - 1;
        h2.b = h.b;
    }
    
    private void a(final g g, final h h, final String s) {
        this.a(s, MS4Java.g.b());
        if (s.equals("")) {
            MS4Java.a("Missing index for Compartive Relative Strength.");
            return;
        }
        if (this.a.k.a < this.a.k.b) {
            int a = g.k.a;
            int a2 = this.a.k.a;
            final double n = g.k.c[a] / this.a.k.c[a2];
            while (a <= g.k.b && a2 <= this.a.k.b) {
                g.a(a);
                this.a.a(a2);
                if (g.a(a) > this.a.a(a2)) {
                    ++a2;
                }
                else if (g.a(a) < this.a.a(a2)) {
                    if (a > g.k.a) {
                        h.c[a] = h.c[a - 1];
                    }
                    else {
                        h.c[a] = 0.0;
                    }
                    ++a;
                }
                else {
                    if (this.a.k.c[a2] != 0.0) {
                        h.c[a] = (g.k.c[a] / this.a.k.c[a2] - n) * 100.0;
                    }
                    else {
                        h.c[a] = 0.0;
                    }
                    ++a;
                    ++a2;
                }
            }
            h.a = g.k.a;
            h.b = g.k.b;
        }
    }
    
    private void a(final h h, final int n, final boolean b, final h h2) {
        final h h3 = new h(550);
        if (n < 1) {
            return;
        }
        this.a(h2, h3, n, 83);
        for (int i = n; i <= h2.b; ++i) {
            double n2 = 0.0;
            for (int j = 0; j < n; ++j) {
                double n3;
                if (i - j < 0) {
                    n3 = h3.c[i];
                }
                else {
                    n3 = h3.c[i] - h2.c[i - j];
                }
                n2 += n3 * n3;
            }
            h.c[i] = n2 / n;
            if (!b) {
                h.c[i] = Math.sqrt(h.c[i]);
            }
        }
        h.a = n - 1;
        h.b = h2.b;
        for (int k = 0; k <= n - 1; ++k) {
            h.c[k] = 0.0;
        }
    }
    
    private void a(int n, final h h, final h h2) {
        if (n <= 0) {
            n = 1;
        }
        h2.a = h.a + n - 1;
        h2.b = h.b;
        for (int i = n - 1; i <= h.b; ++i) {
            final int n2 = i - (n - 1);
            double n3 = -1.7976931348623157E308;
            for (int j = n2; j <= i; ++j) {
                if (h.c[j] > n3) {
                    n3 = h.c[j];
                }
            }
            h2.c[i] = n3;
        }
    }
    
    private void b(int n, final h h, final h h2) {
        if (n <= 0) {
            n = 1;
        }
        h2.a = h2.a + n - 1;
        h2.b = h2.b;
        for (int i = n - 1; i <= h.b; ++i) {
            final int n2 = i - (n - 1);
            double n3 = Double.MAX_VALUE;
            for (int j = n2; j <= i; ++j) {
                if (h.c[j] < n3) {
                    n3 = h.c[j];
                }
            }
            h2.c[i] = n3;
        }
    }
    
    private void a(final g g, final h h, final int n, final int n2) {
        final h h2 = new h(550);
        final h h3 = new h(550);
        this.a(n, g.i, h2);
        this.b(n, g.j, h3);
        for (int i = n + n2; i <= g.k.b; ++i) {
            double n3 = 0.0;
            double n4 = 0.0;
            for (int j = i; j >= i - (n2 - 1); --j) {
                n3 += g.k.c[j] - h3.c[j];
                n4 += h2.c[j] - h3.c[j];
                if (n4 != 0.0) {
                    h.c[i] = n3 / n4 * 100.0;
                }
                else {
                    h.c[i] = 0.0;
                }
            }
        }
        h.a = n + n2;
        h.b = g.k.b;
    }
    
    private void c(final g g, final h h, final int a) {
        final h h2 = new h(550);
        final h h3 = new h(550);
        this.a(a, g.i, h2);
        this.b(a, g.j, h3);
        for (int i = 1; i <= g.k.b; ++i) {
            if (i >= a) {
                if (h2.c[i] != h3.c[i]) {
                    h.c[i] = (h2.c[i] - g.k.c[i]) / (h2.c[i] - h3.c[i]) * -100.0;
                }
                else {
                    h.c[i] = 0.0;
                }
            }
            else {
                h.c[i] = 0.0;
            }
        }
        h.a = a;
        h.b = g.k.b;
    }
    
    public void a(final String s, final g g, final h h, final h h2, final String s2, final int n, final int n2) {
        new h(550);
        if (g.k.b <= g.k.a) {
            return;
        }
        h.a();
        h2.a();
        h.h = MS4Java.y;
        h2.h = MS4Java.z;
        if (s.equals(MS4Java.bf[4])) {
            h.a = -1;
            return;
        }
        if (s.equals(MS4Java.bf[5])) {
            this.a(g, h);
            return;
        }
        if (s.equals(MS4Java.bf[6])) {
            this.a(g, h, h2, n, (double)n2);
            h2.h = MS4Java.y;
            return;
        }
        if (s.equals(MS4Java.bf[7])) {
            this.a(g, h, n);
            return;
        }
        if (s.equals(MS4Java.bf[8])) {
            this.b(g, h, h2, n, n2 / 100.0);
            h2.h = MS4Java.y;
            return;
        }
        if (s.equals(MS4Java.bf[50])) {
            this.b(g, h);
            return;
        }
        if (s.equals(MS4Java.bf[9])) {
            this.c(g, h);
            this.a(h, h2, n, 69);
            return;
        }
        if (s.equals(MS4Java.bf[51])) {
            this.d(g, h);
            return;
        }
        if (s.equals(MS4Java.bf[10])) {
            this.b(g, h, n);
            return;
        }
        if (s.equals(MS4Java.bf[11])) {
            this.a(g.k, h, n, 83);
            return;
        }
        if (s.equals(MS4Java.bf[12])) {
            this.a(g.k, h, n, 83);
            this.a(g.k, h2, n2, 83);
            return;
        }
        if (s.equals(MS4Java.bf[13])) {
            this.e(g, h);
            return;
        }
        if (s.equals(MS4Java.bf[52])) {
            this.f(g, h);
            return;
        }
        if (s.equals(MS4Java.bf[14])) {
            this.a(g, h, g.k, n, n2);
            return;
        }
        if (s.equals(MS4Java.bf[53])) {
            this.g(g, h);
            return;
        }
        if (s.equals(MS4Java.bf[15])) {
            this.a(g.k, h, n, '%');
            return;
        }
        if (s.equals(MS4Java.bf[16])) {
            this.a(g.k, h, n);
            return;
        }
        if (s.equals(MS4Java.bf[49])) {
            this.a(g, h, s2);
            return;
        }
        if (s.equals(MS4Java.bf[17])) {
            this.a(g, h, n, 3);
            this.a(h, h2, n2, 83);
            return;
        }
        if (s.equals(MS4Java.bf[54])) {
            for (int i = 0; i <= g.l.b; ++i) {
                h.c[i] = g.l.c[i];
            }
            h.a = g.l.a;
            h.b = g.l.b;
            return;
        }
        if (s.equals(MS4Java.bf[18])) {
            this.a(g, h, g.l, n, n2);
            return;
        }
        if (s.equals(MS4Java.bf[55])) {
            this.c(g, h, n);
            return;
        }
        MS4Java.a("Indicator::CalcIndicator: Unknown Indicator \"" + s + "\"");
    }
}
