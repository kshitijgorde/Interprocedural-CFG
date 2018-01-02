// 
// Decompiled by Procyon v0.5.30
// 

package ji.image;

import java.awt.Color;
import java.awt.Font;
import ji.util.cm;
import ji.io.h;
import ji.util.i;
import ji.util.e;
import ji.util.d;
import java.awt.Component;
import ji.awt.dc;
import java.awt.Graphics;
import ji.util.jiPrinti;
import java.awt.PrintJob;
import ji.awt.da;

public class rz
{
    cy a;
    da b;
    da c;
    da d;
    da e;
    da f;
    da g;
    da h;
    String i;
    
    public rz() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
    }
    
    public final void a(final cy a) {
        this.a = a;
    }
    
    public final void a() {
        this.a = null;
    }
    
    public final void a(final String i) {
        this.i = i;
    }
    
    public final void a(final PrintJob printJob, final jiPrinti jiPrinti, final Graphics graphics, final int n, dc a, final boolean b, final boolean b2, final int n2, final int n3, final da da, final Component component, final String s) {
        if (this.a == null) {
            return;
        }
        boolean b3 = ji.util.d.b;
        if (!this.a.bl()) {
            b3 = false;
        }
        this.a.bh(b3);
        a = this.a.a(a, n);
        final long a2 = a.a;
        final long b4 = a.b;
        final dc dc = new dc(a2, b4);
        final dc dc2 = new dc(a2, b4);
        this.a.as(true);
        final boolean a3 = this.a.a9();
        this.a.aq(false);
        dc dc3 = new dc(this.a.a(dc, n));
        final dc dc4 = new dc(this.a.a(dc3, n));
        final boolean em = ji.util.d.em();
        this.a.ec();
        int eo = this.a.eo();
        int ep = this.a.ep();
        if (this.a.bm()) {
            if (this.a.bl() || !ji.util.e.av()) {
                this.a.a(new da(0L, 0L, this.a.eo(), this.a.ep()).a());
            }
            else {
                this.a.a(new da(0L, 0L, eo, ep).a());
            }
        }
        this.a.eh();
        if (this.a.bl()) {
            dc3 = new dc(this.a.a(new dc(this.a.eo(), this.a.ep()), n));
            final String a4 = this.a.a8(false);
            final String bn = this.a.bn();
            jiPrinti.setOriginalSize(false, 0.0, 0.0);
            if (!this.a.f() && a4 != null) {
                jiPrinti.startPage(a4, bn, component);
            }
            else if (!this.a.f()) {
                jiPrinti.startPage("", "", component);
            }
        }
        final da da2 = new da(this.a.ee());
        this.a.ef();
        this.a.eg();
        this.a.ai(true);
        this.d = new da(this.a.a(da2.a(), true));
        this.a.ai(false);
        this.a.bk(true);
        final dc dc5 = new dc(0L, 0L);
        double n4 = 1.0;
        if (b3) {
            n4 = dc4.a / eo;
            eo *= (int)n4;
            ep *= (int)n4;
        }
        dc dc6;
        if (this.a.f8() == 90 || this.a.f8() == 270) {
            dc6 = new dc(ep, eo);
        }
        else {
            dc6 = new dc(eo, ep);
        }
        if (this.a.er()) {
            final long b5 = dc6.b;
            final long a5 = dc6.a;
            final long b6 = 95 * dc6.b / 100;
            if (b6 < dc6.b - 16) {
                dc6.b = b6;
            }
            else {
                dc6.b -= 16;
            }
        }
        this.a.a(dc6);
        try {
            this.g = new da(this.d);
            this.g.a = 0L;
            this.g.b = 0L;
            this.a.ec();
            if (this.a.f8() == 90 || this.a.f8() == 270) {
                this.g.c = this.d.d;
                this.g.d = this.d.c;
            }
            if (b3) {
                this.g.c = (int)(n4 * this.g.c);
                this.g.d = (int)(n4 * this.g.d);
                this.d.a = (int)(n4 * this.d.a);
                this.d.b = (int)(n4 * this.d.b);
                this.d.c = (int)(n4 * this.d.c);
                this.d.d = (int)(n4 * this.d.d);
            }
            this.a.b(this.g);
            final boolean b7 = false;
            if (this.a.bl()) {
                this.a.ec();
            }
            else if (em) {
                if (ji.util.i.c(40)) {
                    ji.io.h.d(s, "PRINT: setting IE print area.");
                }
                final long c = this.g.c;
                final long d = this.g.d;
                final double n5 = d / c;
                final double n6 = dc3.b / dc3.a;
                final double n7 = this.g.d / this.g.c;
                final double n8 = 8.27 / (a.a / printJob.getPageResolution());
                final double n9 = a.b / a.a;
                int n10 = (int)(c * n8);
                int n11 = (int)(n10 * 11.69 / 8.27);
                final int n12 = (int)(d / c * a.a);
                if (n12 > a.b) {
                    final double n13 = a.b / n12;
                    n10 /= (int)n13;
                    n11 /= (int)n13;
                }
                if (!this.a.bl() && ji.util.d.em() && ji.util.e.t(s)) {
                    final double n14 = 1.07;
                    n10 *= (int)n14;
                    n11 *= (int)n14;
                }
                cm.a(printJob, n10, n11);
            }
            else {
                if (ji.util.i.c(40)) {
                    ji.io.h.d(s, "PRINT: setting non-IE print area.");
                }
                final double n15 = dc3.b / dc3.a;
                final double n16 = this.g.d / this.g.c;
                if (b7) {
                    final double n17 = dc6.b / dc6.a;
                    if (n17 > n15) {
                        this.g.c = (int)(dc3.b / n17);
                        this.g.d = dc3.b;
                    }
                    else {
                        final int n18 = (int)(n17 * dc3.a);
                        this.g.c = dc3.a;
                        this.g.d = n18;
                    }
                }
                else if (n16 > n15) {
                    this.g.c = (int)(dc3.b / n16);
                    this.g.d = dc3.b;
                }
                else {
                    final int n19 = (int)(n16 * dc3.a);
                    this.g.c = dc3.a;
                    this.g.d = n19;
                }
                if (!ji.util.e.av()) {
                    final da g = this.g;
                    g.c -= (long)(this.g.c * 7.5 / 100);
                    final da g2 = this.g;
                    g2.d -= (long)(this.g.d * 7.5 / 100);
                }
                if (ji.util.i.c(40)) {
                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("PRINT: print area: (").append(this.g.a).append(",").append(this.g.b).append(")"))));
                }
                this.a.b(this.g);
                this.a.ec();
            }
            if (da == null) {
                this.a.a(new da(0L, 0L, this.g.c, this.g.d));
            }
            this.f = new da(this.a.bo());
            da da3;
            if (da != null) {
                da3 = new da(da);
            }
            else {
                da3 = new da(0L, 0L, this.f.c, this.f.d);
            }
            da3.c = Math.min(da3.c, this.f.c);
            da3.d = Math.min(da3.d, this.f.d);
            this.a.b(new dc(this.g.b()));
            this.a.a0(true);
            final long n20 = da3.b + da3.d;
            final long n21 = 64L;
            final long n22 = n21 + 1;
            long n23 = 128L;
            long n24;
            if (ji.util.d.ay(s) && ji.util.e.av()) {
                n24 = 524288L;
            }
            else {
                n24 = 1048576L;
            }
            if (n23 * da3.c > n24) {
                n23 = n24 / da3.c;
            }
            final long min = Math.min(Math.max(n23, 1L), 64L);
            if (ji.util.i.c(40)) {
                ji.io.h.d(s, "PRINT: bandLineSize:".concat(String.valueOf(String.valueOf(min))));
            }
            if (b3) {
                this.a.a(n4);
            }
            if (this.a.bl()) {
                final long n25 = Math.max((int)(this.a.bs() * da3.d / 64.0), 1);
            }
            else if (da3.d > min) {
                final long n25 = da3.d / min;
            }
            else {
                final long n25 = 1L;
            }
            long n25;
            long n26;
            if (da3.d >= n21) {
                n26 = Math.max(n22, 4L);
                if (da3.d > n26) {
                    da3.d = n26;
                }
                else {
                    n26 = n20;
                }
            }
            else {
                n25 = 1L;
                n26 = da3.d;
            }
            if (!true) {
                n25 = 1L;
                n26 = n20;
                da3.d = n26;
            }
            if (ji.util.i.c(40)) {
                ji.io.h.d(s, "PRINT: number of bands: ".concat(String.valueOf(String.valueOf(n25))));
            }
            if (da == null) {
                if (this.a.f8() == 180) {
                    final da d2 = this.d;
                    d2.b -= n26;
                }
                else if (this.a.f8() == 270) {
                    final da d3 = this.d;
                    d3.a -= n26;
                }
            }
            this.b = new da(this.g);
            this.c = new da(this.d);
            int n27 = 0;
            this.a.at(false);
            if (da == null) {
                this.a.a(System.currentTimeMillis());
            }
            if (this.a.n() == null && !this.a.bh()) {
                try {
                    this.a.ce(true);
                    this.a.di();
                    this.a.at(false);
                    this.a.ay(true);
                    this.a.o(true);
                    this.a.b3(false);
                    this.a.ax(true);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            final String a6 = this.a.a8(false);
            final String bn2 = this.a.bn();
            if (a6 != null && graphics != null) {
                final int n28 = (int)(da3.c / 72L);
                if (n28 > 3) {
                    boolean b8 = true;
                    this.a.e(n28);
                    int n29 = 1;
                    final char[] charArray = a6.toCharArray();
                    for (int i = 0; i < charArray.length; ++i) {
                        if (charArray[i] == '\n' || charArray[i] == '\r') {
                            ++n29;
                        }
                    }
                    this.a.f((1 + n29) * n28);
                    if (da != null && da.b >= this.a.bg()) {
                        b8 = false;
                    }
                    if (b8) {
                        graphics.setFont(new Font("Helvetica", 0, n28));
                        graphics.setColor(Color.black);
                        int n30 = 0;
                        String concat = "";
                        for (int j = 0; j < charArray.length; ++j) {
                            if (charArray[j] == '\n' || charArray[j] == '\r') {
                                if (concat.length() > 0) {
                                    graphics.drawString(concat, (int)da3.a + 2, (int)da3.b + n28 + n30);
                                }
                                n30 += n28;
                                concat = "";
                            }
                            else {
                                concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(charArray[j])));
                            }
                        }
                        if (concat.length() > 0) {
                            graphics.drawString(concat, (int)da3.a + 2, (int)da3.b + n28 + n30);
                        }
                    }
                    else {
                        this.a.f(0);
                    }
                }
            }
            long n31 = 0L;
            final long n32 = n20 - da3.b;
            double n33 = 0.0;
            switch (n3) {
                case 90: {
                    n33 = this.c.d / this.f.c;
                    break;
                }
                case 180: {
                    n33 = this.c.c / this.f.c;
                    break;
                }
                case 270: {
                    n33 = this.c.c / this.f.d;
                    break;
                }
                default: {
                    n33 = this.c.c / this.f.c;
                    break;
                }
            }
            this.a.b(n33);
            if (this.i != null) {
                this.a.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i))).append(" (").append(0).append("%)..."))));
            }
            int ep2 = this.a.ep();
            int eo2 = this.a.eo();
            if (b3) {
                ep2 *= (int)n4;
                eo2 *= (int)n4;
            }
            while (n27 == 0 && !this.a.bd() && !this.a.bc() && !ji.util.d.c4()) {
                if (da3.b + da3.d >= n20) {
                    n27 = 1;
                }
                this.e = new da(da3);
                this.b();
                if (this.d.b + this.d.d > ep2) {
                    this.d.d = ep2 - this.d.b;
                }
                if (ji.util.d.av(s)) {
                    this.g.a = this.b.a + (int)(n33 * (this.b.c * this.e.a / this.f.c));
                    this.g.b = this.b.b + (int)(n33 * (this.b.d * this.e.b / this.f.d));
                    this.g.c = this.b.c * this.e.c / this.f.c;
                    this.g.d = this.b.d * this.e.d / this.f.d;
                }
                else {
                    this.g.a = this.b.a + this.b.c * this.e.a / this.f.c;
                    this.g.b = this.b.b + this.b.d * this.e.b / this.f.d;
                    this.g.c = this.b.c * this.e.c / this.f.c;
                    this.g.d = this.b.d * this.e.d / this.f.d;
                }
                this.a.b(this.g);
                this.a.av(true);
                if (da == null && this.a.bi() + this.a.bt() <= System.currentTimeMillis()) {
                    this.a.a(System.currentTimeMillis());
                    this.a.bk();
                }
                if ((this.a.f7() & 0x1) > 0) {
                    this.d.a = eo - this.d.a - this.d.c;
                }
                if ((this.a.f7() & 0x2) > 0) {
                    this.d.b = ep - this.d.b - this.d.d;
                }
                switch (n3) {
                    case 90: {
                        this.a.b((int)(-(ep2 - this.d.d - this.d.b)), (int)(-this.d.a));
                        break;
                    }
                    case 180: {
                        this.a.b((int)(-(eo2 - this.d.c - this.d.a)), (int)(-(ep2 - this.d.d - this.d.b)));
                        break;
                    }
                    case 270: {
                        this.a.b((int)(-this.d.b), (int)(-(eo2 - this.d.c - this.d.a)));
                        break;
                    }
                    default: {
                        this.a.b((int)(-this.d.a), (int)(-this.d.b));
                        break;
                    }
                }
                this.a.a(graphics, this.d.a(), false);
                n31 += n26;
                this.a.ao(Math.min((int)(100L * n31 / n32), 100));
                final da da4 = da3;
                da4.b += n26;
                final da da5 = da3;
                --da5.b;
                ji.util.d.bv = System.currentTimeMillis();
                if (this.i != null) {
                    this.a.i(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.i))).append(" (").append(100 * da3.b / n20).append("%)..."))));
                }
            }
            if (bn2 != null && graphics != null) {
                if (bn2.length() > 0) {
                    final int n34 = (int)(da3.c / 72L);
                    if (n34 > 3) {
                        boolean b9 = true;
                        int n35 = 1;
                        final char[] charArray2 = bn2.toCharArray();
                        for (int k = 0; k < charArray2.length; ++k) {
                            if (charArray2[k] == '\n' || charArray2[k] == '\r') {
                                ++n35;
                            }
                        }
                        this.a.g((1 + n35) * n34);
                        final da cf = this.a.cf();
                        final int n36 = (int)(cf.b + cf.d - n34);
                        if (da != null && da.b + da.d < n36) {
                            b9 = false;
                        }
                        if (b9) {
                            graphics.setFont(new Font("Helvetica", 1, n34));
                            graphics.setColor(Color.black);
                            final int stringWidth = graphics.getFontMetrics().stringWidth(bn2);
                            final int stringWidth2 = graphics.getFontMetrics().stringWidth("WW");
                            int n37 = 0;
                            String concat2 = "";
                            for (int l = 0; l < charArray2.length; ++l) {
                                if (charArray2[l] == '\n' || charArray2[l] == '\r') {
                                    if (concat2.length() > 0) {
                                        graphics.drawString(concat2, (int)(da3.a + da3.c - stringWidth - stringWidth2), n36 + n37);
                                    }
                                    n37 += n34;
                                    concat2 = "";
                                }
                                else {
                                    concat2 = String.valueOf(String.valueOf(concat2)).concat(String.valueOf(String.valueOf(charArray2[l])));
                                }
                            }
                            if (concat2.length() > 0) {
                                graphics.drawString(concat2, (int)(da3.a + da3.c - stringWidth - stringWidth2), n36 + n37);
                            }
                        }
                        else {
                            this.a.g(0);
                        }
                    }
                }
                if (this.i != null) {
                    this.a.i(this.i);
                }
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        finally {
            if (da == null) {
                this.a.ao(0);
            }
            this.a.bk(false);
            this.a.aq(a3);
        }
        if (da != null) {
            this.a.a(this.a.bi() + 1);
            if (this.a.bi() > 4) {
                this.a.bk();
                this.a.a(0L);
            }
        }
        if (this.a.bl() && !this.a.f()) {
            jiPrinti.endPage();
            ji.util.e.i(true);
        }
    }
    
    private final void b() {
        try {
            switch (this.a.f8()) {
                case 90: {
                    this.d.a = this.c.a + this.c.c * this.e.b / this.f.d;
                    this.d.b = this.c.b;
                    break;
                }
                case 270: {
                    this.d.a = this.c.a + this.c.c - this.c.c * this.e.b / this.f.d;
                    this.d.b = this.c.b;
                    break;
                }
                case 180: {
                    this.d.a = this.c.a + this.c.c * this.e.a / this.f.c;
                    this.d.b = this.c.b + this.c.d - this.c.d * this.e.b / this.f.d;
                    break;
                }
                default: {
                    this.d.a = this.c.a + this.c.c * this.e.a / this.f.c;
                    this.d.b = this.c.b + this.c.d * this.e.b / this.f.d;
                    break;
                }
            }
            int n = 1;
            int n2 = 0;
            while (n != 0 && n2 < 100) {
                if (this.a.f8() == 90 || this.a.f8() == 270) {
                    this.d.c = this.c.c * this.e.d / this.f.d;
                    this.d.d = this.c.d * this.e.c / this.f.c;
                }
                else {
                    this.d.c = this.c.c * this.e.c / this.f.c;
                    this.d.d = this.c.d * this.e.d / this.f.d;
                }
                if (this.d.c > 2 && this.d.d > 2) {
                    n = 0;
                }
                else {
                    final da e = this.e;
                    ++e.c;
                    final da e2 = this.e;
                    ++e2.d;
                    this.e.c = Math.min(this.e.c, this.g.c);
                    this.e.d = Math.min(this.e.d, this.g.d);
                    ++n2;
                }
            }
        }
        catch (Exception ex) {}
    }
}
